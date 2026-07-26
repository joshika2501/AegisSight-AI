import Navbar from "../../Components/Navbar/Navbar";
import Sidebar from "../../Components/Sidebar/Sidebar";
import StatsBar from "../../Components/Stats/StatsBar";
import MapSection from "../../Components/Map/MapSection";
import DroneDetails from "../../Components/DroneDetails/DroneDetails";
import CctvDetails from "../../Components/CctvDetails/CctvDetails";
import AiAlerts from "../../Components/Alerts/AiAlerts";
import ZoneOverview from "../../Components/ZoneOverview/ZoneOverview";
import CameraThumbnails from "../../Components/CameraThumbnails/CameraThumbnails";

function Dashboard() {
    return (
        <div className="dashboard">
            <Sidebar />

            <main className="main-content">
                <Navbar />

                <div className="dashboard-content">
                    <StatsBar />

                    <div className="dashboard-grid">

                        <div className="dashboard-main">
                            <MapSection />
                            <ZoneOverview />
                            <CameraThumbnails />
                        </div>

                        <div className="dashboard-side">
                            <DroneDetails />
                            <CctvDetails />
                            <AiAlerts />
                        </div>

                    </div>
                </div>
            </main>
        </div>
    );
}

export default Dashboard;
