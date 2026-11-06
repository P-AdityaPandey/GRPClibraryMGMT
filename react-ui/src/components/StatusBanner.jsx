export default function StatusBanner({ type = 'info', children, onDismiss }) {
  if (!children) return null;

  return (
    <div className={`status-banner status-${type}`} role={type === 'error' ? 'alert' : 'status'}>
      <span className="status-symbol" aria-hidden="true">
        {type === 'error' ? '!' : type === 'success' ? '✓' : 'i'}
      </span>
      <p>{children}</p>
      {onDismiss && (
        <button className="icon-button status-dismiss" type="button" onClick={onDismiss} aria-label="Dismiss message">
          ×
        </button>
      )}
    </div>
  );
}