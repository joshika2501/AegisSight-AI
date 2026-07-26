import ZoneCard from "./ZoneCard";
import { zoneOverview } from "./zoneOverviewData";

function ZoneOverview() {
    return (
        <div className="section-card">

            <div className="section-card-header">
                <span>Zone Overview</span>
                <span className="section-card-link">View All ›</span>
            </div>

            <div className="zone-overview-grid">
                {zoneOverview.map((zone) => (
                    <ZoneCard key={zone.id} zone={zone} />
                ))}
            </div>

        </div>
    );
}

export default ZoneOverview;