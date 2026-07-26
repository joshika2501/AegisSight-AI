import mapDroneImage from "../../assets/dashboard/map-drone.png";
import mapCctvImage from "../../assets/dashboard/map-cctv.png";

const legendMarkers = [
    { src: mapDroneImage, label: "Head Drone" },
    { src: mapDroneImage, label: "Drone" },
    { src: mapCctvImage, label: "CCTV" },
];
const legendBoundaries = [{ symbol: "▬", label: "Zone Boundary" }, { symbol: "─", label: "Block Boundary" }];
const legendCrowd = [{ color: "#EF4444", label: "High Crowd" }, { color: "#F97316", label: "Medium Crowd" }, { color: "#22C55E", label: "Low Crowd" }];

function MapLegend() {
    return <div className="map-legend">
        <span className="map-legend-title">Legend</span>
        {legendMarkers.map((item) => <div className="map-legend-row" key={item.label}><span className="map-legend-icon"><img src={item.src} alt="" /></span><span className="map-legend-label">{item.label}</span></div>)}
        {legendBoundaries.map((item) => <div className="map-legend-row" key={item.label}><span className="map-legend-icon">{item.symbol}</span><span className="map-legend-label">{item.label}</span></div>)}
        {legendCrowd.map((item) => <div className="map-legend-row" key={item.label}><span className="map-legend-dot" style={{ background: item.color }}></span><span className="map-legend-label">{item.label}</span></div>)}
    </div>;
}

export default MapLegend;
