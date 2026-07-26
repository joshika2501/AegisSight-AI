import { alerts } from "./alertsData";

function AiAlerts() {
    return (
        <div className="panel-dark">

            <div className="panel-title">
                <span>AI ALERTS</span>
                <span className="panel-title-link">View All ›</span>
            </div>

            <div className="alerts-list">
                {alerts.map((alert) => (
                    <div className="alert-item" key={alert.id}>

                        <div className={`alert-icon alert-icon-${alert.severity}`}>
                            {alert.icon}
                        </div>

                        <div className="alert-text">
                            <span className="alert-title">{alert.title}</span>
                            <span className="alert-meta">{alert.meta}</span>
                        </div>

                        <div className="alert-right">
                            <span className="alert-time">{alert.time}</span>
                            <span className={`alert-severity alert-severity-${alert.severity}`}>
                                {alert.severity === "high" ? "High" : "Medium"} ›
                            </span>
                        </div>

                    </div>
                ))}
            </div>

        </div>
    );
}

export default AiAlerts;