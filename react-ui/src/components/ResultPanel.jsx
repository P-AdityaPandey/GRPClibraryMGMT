function prettyKey(key) {
  return key.replace(/([A-Z])/g, ' $1').replace(/^./, (c) => c.toUpperCase());
}

export default function ResultPanel({ result, title = 'Book details', emptyText = 'Choose a book or run an action to see the response here.' }) {
  if (result == null) {
    return (
      <div className="result-empty">
        <span className="result-empty-icon" aria-hidden="true">{'{ }'}</span>
        <p>{emptyText}</p>
      </div>
    );
  }

  const entries = typeof result === 'object' && result !== null
    ? Object.entries(result)
    : [['result', result]];

  return (
    <div className="result-content">
      <div className="result-heading">
        <span className="result-check" aria-hidden="true">✓</span>
        <div><strong>{title}</strong><small>Response received from API</small></div>
      </div>
      <dl className="result-fields">
        {entries.map(([key, value]) => (
          <div className="result-field" key={key}>
            <dt>{prettyKey(key)}</dt>
            <dd>{value == null ? '—' : typeof value === 'object' ? JSON.stringify(value) : String(value)}</dd>
          </div>
        ))}
      </dl>
      <details className="raw-response">
        <summary>View raw JSON</summary>
        <pre>{JSON.stringify(result, null, 2)}</pre>
      </details>
    </div>
  );
}