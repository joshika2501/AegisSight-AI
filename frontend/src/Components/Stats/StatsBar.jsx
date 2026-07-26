import { useEffect, useState } from "react";
import StatCard from "./StatCard";
import ViewModeToggle from "./ViewModeToggle";
import droneStatImage from "../../assets/dashboard/drone-stat.png";
import cameraStatImage from "../../assets/dashboard/camera-stat.png";

function randomStep(max = 1) {
    return Math.floor(Math.random() * (max * 2 + 1)) - max;
}

function clamp(value, min, max) {
    return Math.min(Math.max(value, min), max);
}

function StatsBar() {
    const [stats, setStats] = useState({
        activeDrones: 28,
        headDrones: 12,
        workingCameras: 214,
        camerasOperationalPct: 98,
        activeAlerts: 9,
        criticalAlerts: 3,
        zonesCovered: 12,
        totalBlocks: 48,
    });

    useEffect(() => {
        const interval = setInterval(() => {
            setStats((previous) => ({
                ...previous,
                activeDrones: clamp(previous.activeDrones + randomStep(), 20, 32),
                workingCameras: clamp(previous.workingCameras + randomStep(2), 200, 220),
                activeAlerts: clamp(previous.activeAlerts + randomStep(), 0, 15),
            }));
        }, 5000);

        return () => clearInterval(interval);
    }, []);

    return (
        <div className="stats-bar">
            <div className="stats-cards">
                <StatCard icon={<img src={droneStatImage} alt="Drone" />} value={stats.activeDrones} label="Active Drones" subtext={`${stats.headDrones} Head Drone`} color="blue" />
                <StatCard icon={<img src={cameraStatImage} alt="Security camera" />} value={stats.workingCameras} label="Working Cameras" subtext={`${stats.camerasOperationalPct}% Operational`} color="green" />
                <StatCard icon="🔔" value={stats.activeAlerts} label="Active Alerts" subtext={`${stats.criticalAlerts} Critical`} color="amber" />
                <StatCard icon="▣" value={stats.zonesCovered} label="Zones Covered" subtext={`${stats.totalBlocks} Blocks`} color="purple" />
            </div>
            <ViewModeToggle />
        </div>
    );
}

export default StatsBar;
