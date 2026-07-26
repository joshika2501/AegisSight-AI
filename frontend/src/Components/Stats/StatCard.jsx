function StatCard({ icon, value, label, subtext, color }) {
    return (
        <div className="stat-card">

            <div className={`stat-icon stat-icon-${color}`}>
                <span>{icon}</span>
            </div>

            <div className="stat-info">
                <span className="stat-value">{value}</span>
                <span className="stat-label">{label}</span>
                <span className="stat-subtext">{subtext}</span>
            </div>

        </div>
    );
}

export default StatCard;