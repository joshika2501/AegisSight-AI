import { cameraThumbnails } from "./thumbnailsData";

function CameraThumbnails() {
    return (
        <div className="section-card">

            {/* Note: your Figma also labels this row "Zone Overview" — kept as-is
                for an exact match, but you'll likely want to rename this to
                something like "Live Camera Feed" */}
            <div className="section-card-header">
                <span>Live Feed</span>
                <span className="section-card-link">View All ›</span>
            </div>

            <div className="thumbnail-grid">
                {cameraThumbnails.map((cam) => (
                    <div className="thumbnail-item" key={cam.id}>
                        <div className="thumbnail-card">
                            <span>🎥</span>
                            <span className="thumbnail-live-tag">LIVE</span>
                        </div>
                        <span className="thumbnail-label">{cam.id}</span>
                    </div>
                ))}
            </div>

        </div>
    );
}

export default CameraThumbnails;