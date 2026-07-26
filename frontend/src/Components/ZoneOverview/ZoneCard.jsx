function ZoneCard({ zone }) {
    const radius = 24;
    const circumference = 2 * Math.PI * radius;
    const offset = circumference - (zone.activity / 100) * circumference;
    const threatClass = `threat-${zone.threat.toLowerCase()}`;

    return (
        <div className="zone-card">

            <div className="zone-card-info">
                <span className="zone-card-name">{zone.name}</span>
                <span className="zone-card-stat">{zone.cameras} Camera</span>
                <span className="zone-card-stat">{zone.drones} Drone</span>
                <span className="zone-card-stat">Crowd : {zone.crowd}</span>
                <span className={`zone-card-stat ${threatClass}`}>Threat : {zone.threat}</span>
            </div>

            <div className="zone-ring">
                <svg width="56" height="56" viewBox="0 0 56 56">
                    <circle cx="28" cy="28" r={radius} className="zone-ring-track" />
                    <circle
                        cx="28"
                        cy="28"
                        r={radius}
                        className="zone-ring-progress"
                        strokeDasharray={circumference}
                        strokeDashoffset={offset}
                        transform="rotate(-90 28 28)"
                    />
                </svg>
                <div className="zone-ring-value">
                    <span className="zone-ring-percent">{zone.activity}%</span>
                    <span className="zone-ring-label">Activity</span>
                </div>
            </div>

        </div>
    );
}

export default ZoneCard;