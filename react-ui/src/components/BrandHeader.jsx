export default function BrandHeader() {
  return (
    <header className="topbar">
      <a className="brand" href="/" aria-label="Book Library home">
        <span className="brand-mark" aria-hidden="true">
          <svg viewBox="0 0 32 32" role="img">
            <path d="M5 7.5c4.7-1.5 8.4-.7 11 2.1v16c-2.6-2.8-6.3-3.6-11-2.1z" />
            <path d="M27 7.5c-4.7-1.5-8.4-.7-11 2.1v16c2.6-2.8 6.3-3.6 11-2.1z" />
            <path d="M16 9.6v16" />
          </svg>
        </span>
        <span className="brand-copy">
          <strong>Pagewise</strong>
          <small>BOOK LIBRARY</small>
        </span>
      </a>
      <div className="topbar-right">
        <span className="environment-dot" />
        <span>gRPC demo environment</span>
      </div>
    </header>
  );
}