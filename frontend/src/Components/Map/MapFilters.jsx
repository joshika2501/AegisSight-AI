import { useState } from "react";

function MapFilters({ onZoneChange }) {
    const [region, setRegion] = useState("City Center");
    const [zone, setZone] = useState("Zone A");
    const [block, setBlock] = useState("All Blocks");
    const [range, setRange] = useState("10 kms");

    const handleZoneChange = (e) => {
        const value = e.target.value;
        setZone(value);
        onZoneChange?.(value);
    };

    return (
        <div className="map-filters">

            <div className="map-filter">
                <label>Select Region</label>
                <select value={region} onChange={(e) => setRegion(e.target.value)}>
                    <option>City Center</option>
                    <option>North District</option>
                    <option>South District</option>
                </select>
            </div>

            <div className="map-filter">
                <label>Zone</label>
                <select value={zone} onChange={handleZoneChange}>
                    <option>Zone A</option>
                    <option>Zone B</option>
                    <option>Zone C</option>
                    <option>Zone D</option>
                </select>
            </div>

            <div className="map-filter">
                <label>Block</label>
                <select value={block} onChange={(e) => setBlock(e.target.value)}>
                    <option>All Blocks</option>
                    <option>Block 1</option>
                    <option>Block 2</option>
                    <option>Block 3</option>
                </select>
            </div>

            <div className="map-filter">
                <label>Range</label>
                <select value={range} onChange={(e) => setRange(e.target.value)}>
                    <option>5 kms</option>
                    <option>10 kms</option>
                    <option>20 kms</option>
                </select>
            </div>

        </div>
    );
}

export default MapFilters;