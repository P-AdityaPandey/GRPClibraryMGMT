import { useCallback, useEffect, useMemo, useState } from 'react';
import BrandHeader from './components/BrandHeader.jsx';
import MetricCard from './components/MetricCard.jsx';
import ApiControls from './components/ApiControls.jsx';
import BookTable from './components/BookTable.jsx';
import ResultPanel from './components/ResultPanel.jsx';
import StatusBanner from './components/StatusBanner.jsx';
import { bookApi } from './services/bookApi.js';
import './App.css';

function getBookId(book, index) {
  return book?.id ?? book?.bookId ?? index + 1;
}

export default function App() {
  const [bookId, setBookId] = useState('1');
  const [books, setBooks] = useState([]);
  const [result, setResult] = useState(null);
  const [resultTitle, setResultTitle] = useState('Book details');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [notice, setNotice] = useState('');
  const [lastUpdated, setLastUpdated] = useState(null);

  const availableCount = useMemo(
    () => books.filter((book) => book.available === true || book.available === 'true' || book.isAvailable === true).length,
    [books]
  );

  const runAction = useCallback(async (action, successMessage) => {
    setLoading(true);
    setError('');
    setNotice('');
    try {
      await action();
      setLastUpdated(new Date());
      if (successMessage) setNotice(successMessage);
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Something went wrong while contacting the API.');
    } finally {
      setLoading(false);
    }
  }, []);

  const listBooks = useCallback(() => runAction(async () => {
    const data = await bookApi.list();
    if (!Array.isArray(data)) {
      throw new Error('The books endpoint returned an unexpected response. Expected a JSON array.');
    }
    setBooks(data);
    setResult(null);
    setResultTitle('Book details');
  }, 'Book collection refreshed.'), [runAction]);

  const getBookById = useCallback(() => {
    const id = Number(bookId);
    if (!Number.isInteger(id) || id < 1) {
      setError('Enter a valid positive whole-number book ID.');
      setNotice('');
      return;
    }
    runAction(async () => {
      const data = await bookApi.getById(id);
      setResult(data);
      setResultTitle('Book details');
      setBookId(String(id));
    }, `Loaded details for book #${id}.`);
  }, [bookId, runAction]);

  const checkAvailability = useCallback(() => {
    const id = Number(bookId);
    if (!Number.isInteger(id) || id < 1) {
      setError('Enter a valid positive whole-number book ID.');
      setNotice('');
      return;
    }
    runAction(async () => {
      const data = await bookApi.availability(id);
      setResult(data);
      setResultTitle('Availability result');
    }, `Availability checked for book #${id}.`);
  }, [bookId, runAction]);

  const selectBook = useCallback((id) => {
    setBookId(String(id));
    runAction(async () => {
      const data = await bookApi.getById(id);
      setResult(data);
      setResultTitle('Book details');
    }, `Loaded details for book #${id}.`);
  }, [runAction]);

  useEffect(() => {
    listBooks();
  }, [listBooks]);

  return (
    <div className="app-shell">
      <BrandHeader />
      <main className="page-container">
        <section className="hero">
          <div className="hero-copy">
            <div className="hero-eyebrow"><span className="hero-eyebrow-line" /> YOUR READING CATALOGUE</div>
            <h1>A little more <em>knowledge</em><br />in every page.</h1>
            <p>A simple window into your Book Library microservices. Browse titles, inspect book records, and check availability through the REST-to-gRPC flow.</p>
            <div className="hero-tags">
              <span><i className="tag-dot dot-green" /> React UI</span>
              <span><i className="tag-dot dot-blue" /> Spring Boot</span>
              <span><i className="tag-dot dot-violet" /> gRPC</span>
            </div>
          </div>
          <div className="hero-art" aria-hidden="true">
            <div className="art-orbit orbit-one" />
            <div className="art-orbit orbit-two" />
            <div className="art-sun" />
            <div className="art-book art-book-back"><span /><span /><span /></div>
            <div className="art-book art-book-front"><span /><span /><span /><span /></div>
            <div className="art-spark spark-one">✦</div>
            <div className="art-spark spark-two">✧</div>
            <div className="art-caption">READ · DISCOVER · REPEAT</div>
          </div>
        </section>

        <section className="metrics-grid" aria-label="Library summary">
          <MetricCard icon="▤" label="Books loaded" value={books.length} detail="Records in current response" />
          <MetricCard icon="✓" label="Available now" value={availableCount} detail="Based on returned availability fields" />
          <MetricCard icon="⌁" label="API gateway" value="8080" detail="LibraryService · REST" />
          <MetricCard icon="◉" label="Last refreshed" value={lastUpdated ? lastUpdated.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) : '—'} detail={lastUpdated ? lastUpdated.toLocaleDateString() : 'Waiting for first response'} />
        </section>

        <StatusBanner type="error" onDismiss={() => setError('')}>{error}</StatusBanner>
        <StatusBanner type="success" onDismiss={() => setNotice('')}>{notice}</StatusBanner>
        {loading && (
          <div className="loading-strip" role="status">
            <span className="loading-line" /> Connecting to LibraryService…
          </div>
        )}

        <div className="main-grid">
          <div className="main-column">
            <ApiControls
              bookId={bookId}
              setBookId={setBookId}
              onGetBook={getBookById}
              onCheckAvailability={checkAvailability}
              onListBooks={listBooks}
              loading={loading}
            />

            <section className="workspace-card collection-card">
              <div className="section-heading collection-heading">
                <div>
                  <span className="section-kicker">THE SHELVES</span>
                  <h2>Your book collection</h2>
                  <p>Titles returned by the LibraryService API.</p>
                </div>
                <span className="count-badge">{books.length} {books.length === 1 ? 'book' : 'books'}</span>
              </div>
              <BookTable
                books={books}
                onSelect={selectBook}
                selectedId={result?.id ?? result?.bookId}
              />
              <div className="table-footnote">
                <span className="footnote-dot" /> Data is fetched live from your local backend.
              </div>
            </section>
          </div>

          <aside className="workspace-card result-card">
            <div className="section-heading">
              <div>
                <span className="section-kicker">API RESPONSE</span>
                <h2>Selected result</h2>
                <p>Inspect the response from your last action.</p>
              </div>
              <span className="result-braces" aria-hidden="true">{'{ }'}</span>
            </div>
            <ResultPanel result={result} title={resultTitle} />
            <div className="flow-card">
              <span className="flow-title">HOW IT CONNECTS</span>
              <div className="flow-step"><span className="flow-number">01</span><span><strong>React UI</strong><small>localhost:5173</small></span><i>→</i></div>
              <div className="flow-connector" />
              <div className="flow-step"><span className="flow-number">02</span><span><strong>LibraryService</strong><small>REST · localhost:8080</small></span><i>→</i></div>
              <div className="flow-connector" />
              <div className="flow-step"><span className="flow-number">03</span><span><strong>BookService</strong><small>gRPC server</small></span><span className="flow-live">SERVICE</span></div>
            </div>
          </aside>
        </div>

        <footer className="page-footer">
          <span>PAGEWISE <b>·</b> BOOK LIBRARY DEMO</span>
          <span>Built with React + Spring Boot + gRPC</span>
        </footer>
      </main>
    </div>
  );
}