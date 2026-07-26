import { useState } from "react";
import MapFilters from "./MapFilters";
import MapLegend from "./MapLegend";
import { zones, drones, cctvMarkers, crowdDots, crowdColors } from "./mapData";
import mapDroneImage from "../../assets/dashboard/map-drone-marker.png";
import mapCctvImage from "../../assets/dashboard/map-cctv-marker.png";

function zoneCentroid(points) {
    const coords = points.split(" ").map((point) => point.split(",").map(Number));
    const cx = coords.reduce((sum, [x]) => sum + x, 0) / coords.length;
    const cy = coords.reduce((sum, [, y]) => sum + y, 0) / coords.length;
    return { cx, cy };
}

function MapSection() {
    const [activeZone, setActiveZone] = useState("Zone A");
    const [zoom, setZoom] = useState(1);

    const zoomIn = () => setZoom((current) => Math.min(current + 0.2, 2));
    const zoomOut = () => setZoom((current) => Math.max(current - 0.2, 0.8));

    return (
        <div className="map-section">
            <MapFilters onZoneChange={setActiveZone} />

            <div className="map-card">
                <svg viewBox="0 0 600 400" className="map-svg" preserveAspectRatio="none">
                    <rect x="0" y="0" width="600" height="400" className="map-bg" />

                    <g transform={`translate(300 200) scale(${zoom}) translate(-300 -200)`}>
                        {Array.from({ length: 12 }).map((_, index) => (
                            <line key={`v-${index}`} x1={index * 50} y1="0" x2={index * 50} y2="400" className="map-grid-line" />
                        ))}
                        {Array.from({ length: 8 }).map((_, index) => (
                            <line key={`h-${index}`} x1="0" y1={index * 50} x2="600" y2={index * 50} className="map-grid-line" />
                        ))}

                        {zones.map((zone) => {
                            const isActive = zone.label === activeZone;
                            return (
                                <polygon
                                    key={zone.id}
                                    points={zone.points}
                                    fill={zone.color}
                                    fillOpacity={isActive ? 0.42 : 0.22}
                                    stroke={zone.color}
                                    strokeWidth={isActive ? 2.5 : 1.5}
                                    strokeDasharray="5 4"
                                    className="map-zone-shape"
                                />
                            );
                        })}

                        {zones.map((zone) => {
                            const { cx, cy } = zoneCentroid(zone.points);
                            return (
                                <text key={`${zone.id}-label`} x={cx} y={cy - 35} textAnchor="middle" className="map-zone-label">
                                    {zone.label}
                                </text>
                            );
                        })}

                        {crowdDots.map((dot) => (
                            <circle key={dot.id} cx={dot.x} cy={dot.y} r="5" fill={crowdColors[dot.level]} className="map-crowd-dot" />
                        ))}

                        {cctvMarkers.map((cam) => (
                            <image key={cam.id} href={mapCctvImage} x={cam.x - 10} y={cam.y - 10} width="20" height="20" className="map-marker-image map-marker-cctv" />
                        ))}

                        {drones.map((drone) => (
                            <image key={drone.id} href={mapDroneImage} x={drone.x - 16} y={drone.y - 12} width="32" height="24" className="map-marker-image map-marker-drone" />
                        ))}
                    </g>
                </svg>

                <MapLegend />

                <div className="map-zoom-controls" aria-label="Map zoom controls">
                    <button className="map-zoom-button" onClick={zoomIn} disabled={zoom >= 2} aria-label="Zoom in">+</button>
                    <button className="map-zoom-button" onClick={zoomOut} disabled={zoom <= 0.8} aria-label="Zoom out">−</button>
                </div>
            </div>
        </div>
    );
}

export default MapSection;
