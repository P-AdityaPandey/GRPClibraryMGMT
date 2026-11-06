function availabilityLabel(book) {
  const value = book?.available ?? book?.isAvailable;
  if (value === true || value === 'true') return { label: 'Available', className: 'available' };
  if (value === false || value === 'false') return { label: 'Checked out', className: 'unavailable' };
  return { label: 'Not specified', className: 'unknown' };
}

export default function BookTable({ books, onSelect, selectedId }) {
  if (!books.length) {
    return (
      <div className="empty-state">
        <div className="empty-icon" aria-hidden="true">▤</div>
        <strong>No books to show yet</strong>
        <p>Load the collection to see the books returned by LibraryService.</p>
      </div>
    );
  }

  return (
    <div className="table-wrap">
      <table className="book-table">
        <thead>
          <tr>
            <th>Book</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Status</th>
            <th><span className="sr-only">Actions</span></th>
          </tr>
        </thead>
        <tbody>
          {books.map((book, index) => {
            const status = availabilityLabel(book);
            const id = book.id ?? book.bookId ?? index + 1;
            return (
              <tr key={id} className={String(selectedId) === String(id) ? 'selected-row' : ''}>
                <td>
                  <div className="book-cell">
                    <span className={`book-cover cover-${index % 5}`} aria-hidden="true">
                      <span />
                    </span>
                    <span className="book-title-wrap">
                      <strong>{book.title || book.name || `Book ${id}`}</strong>
                      <small>Book ID: {id}</small>
                    </span>
                  </div>
                </td>
                <td>{book.author || '—'}</td>
                <td className="isbn-cell">{book.isbn || '—'}</td>
                <td><span className={`availability-pill ${status.className}`}><i />{status.label}</span></td>
                <td>
                  <button className="text-button" type="button" onClick={() => onSelect(id)}>View details <span aria-hidden="true">↗</span></button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}