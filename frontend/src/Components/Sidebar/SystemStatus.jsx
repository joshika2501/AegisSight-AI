import { useState, useEffect } from "react";

function StatusRow({ label, status }) {
    const isOnline = status === "online";

    return (
        <div className="status-row">
            <span className="status-label">{label}</span>

            <div className={`status-info ${isOnline ? "online" : "offline"}`}>
                <span className="status-dot">●</span>
                <span className="status-text">
                    {isOnline ? "Online" : "Offline"}
                </span>
                <span className="status-arrow">›</span>
            </div>
        </div>
    );
}


function SystemStatus() {
    const [currentDateTime, setCurrentDateTime] = useState(new Date());

    useEffect(() => {
        const timer = setInterval(() => {
            setCurrentDateTime(new Date());
        }, 1000);

        return () => clearInterval(timer);
    }, []);

    const formattedDate = currentDateTime.toLocaleDateString("en-IN", {
        weekday: "short",
        day: "2-digit",
        month: "short",
        year: "numeric",
    });

    const formattedTime = currentDateTime.toLocaleTimeString("en-IN", {
        hour: "2-digit",
        minute: "2-digit",
        second: "2-digit",
        hour12: true,
    });

    // System statuses
    const systemStatuses = {
        network: "online",
        drones: "offline",
        cctvs: "online",
        server: "online",
    };

    // Check if every system is online
    const allSystemsOnline = Object.values(systemStatuses).every(
        (status) => status === "online"
    );

    return (
        <div className="system-status">

            {/* Header */}
            <div className="system-status-header">
                <div className="system-status-title">
                    <span>System Status</span>
                </div>
            </div>

            {/* Dynamic Overall Status */}
            <div className={`overall-status ${allSystemsOnline ? "overall-online" : "overall-offline"}`}>

                <span className={allSystemsOnline ? "green-dot" : "red-dot"}></span>

                <span className="overall-status-text">
        {allSystemsOnline
            ? "All Systems Operational"
            : "System Degraded"}
    </span>

            </div>


            <div className="status-divider"></div>

            {/* Network */}
            <StatusRow
                label="Network"
                status={systemStatuses.network}
            />

            {/* Drones */}
            <StatusRow
                label="Drones"
                status={systemStatuses.drones}
            />

            {/* CCTVs */}
            <StatusRow
                label="CCTVs"
                status={systemStatuses.cctvs}
            />

            {/* Server */}
            <StatusRow
                label="Server"
                status={systemStatuses.server}
            />

            <div className="status-divider"></div>

            {/* Date */}
            <div className="status-info">
                <span>📅</span>
                <span>Date</span>
                <span style={{ marginLeft: "auto" }}>
                    {formattedDate}
                </span>
            </div>

            {/* Time */}
            <div className="status-info">
                <span>🕒</span>
                <span>Time</span>
                <span style={{ marginLeft: "auto" }}>
                    {formattedTime}
                </span>
            </div>

        </div>
    );
}

export default SystemStatus;