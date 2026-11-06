export default function MetricCard({ icon, label, value, detail }) {
  return (
    <article className="metric-card">
      <div className="metric-icon" aria-hidden="true">{icon}</div>
      <div className="metric-content">
        <span className="metric-label">{label}</span>
        <strong className="metric-value">{value}</strong>
        <span className="metric-detail">{detail}</span>
      </div>
    </article>
  );
}