export default function ApiControls({
  bookId, setBookId, onGetBook, onCheckAvailability, onListBooks, loading
}) {
  function handleSubmit(event) {
    event.preventDefault();
    onGetBook();
  }

  return (
    <section className="workspace-card">
      <div className="section-heading">
        <div>
          <span className="section-kicker">LIBRARY TOOLS</span>
          <h2>Explore your collection</h2>
          <p>Look up a specific title or fetch the full book list.</p>
        </div>
        <span className="api-chip"><span /> REST API</span>
      </div>

      <form className="lookup-form" onSubmit={handleSubmit}>
        <label htmlFor="book-id">Find a book by ID</label>
        <div className="lookup-row">
          <div className="input-wrap">
            <span aria-hidden="true">⌕</span>
            <input
              id="book-id"
              type="number"
              min="1"
              step="1"
              value={bookId}
              onChange={(event) => setBookId(event.target.value)}
              placeholder="e.g. 1"
              required
            />
          </div>
          <button className="button button-primary" type="submit" disabled={loading}>
            {loading ? <span className="button-spinner" /> : null}
            Get book
          </button>
          <button className="button button-secondary" type="button" onClick={onCheckAvailability} disabled={loading}>
            Check availability
          </button>
        </div>
      </form>

      <div className="control-divider"><span>OR</span></div>
      <div className="list-action-row">
        <div className="list-action-copy">
          <span className="list-action-icon" aria-hidden="true">▤</span>
          <span><strong>Browse all books</strong><small>Retrieve the current collection from LibraryService.</small></span>
        </div>
        <button className="button button-dark" type="button" onClick={onListBooks} disabled={loading}>
          {loading ? 'Loading…' : 'List all books'} <span aria-hidden="true">→</span>
        </button>
      </div>
    </section>
  );
}