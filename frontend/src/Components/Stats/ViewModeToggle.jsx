import { useState } from "react";

function ViewModeToggle() {
    const [mode, setMode] = useState("surveillance");

    return (
        <div className="view-mode-card">

            <span className="view-mode-label">View Mode</span>

            <div className="view-mode-toggle">
                <button
                    className={`view-mode-btn ${mode === "surveillance" ? "active" : ""}`}
                    onClick={() => setMode("surveillance")}
                >
                    SURVEILLANCE
                </button>

                <button
                    className={`view-mode-btn ${mode === "monitoring" ? "active" : ""}`}
                    onClick={() => setMode("monitoring")}
                >
                    MONITORING
                </button>
            </div>

        </div>
    );
}

export default ViewModeToggle;