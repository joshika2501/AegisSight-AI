// All coordinates are in the 600x400 SVG viewBox used by MapSection.
// Swap these arrays for real API data later — the component just maps over them.

export const zones = [
    {
        id: "zoneA",
        label: "Zone A",
        color: "#3B82F6",
        points: "70,70 260,50 300,150 190,210 60,170",
    },
    {
        id: "zoneB",
        label: "Zone B",
        color: "#22C55E",
        points: "300,50 520,70 540,190 380,210 300,150",
    },
    {
        id: "zoneC",
        label: "Zone C",
        color: "#EAB308",
        points: "60,170 190,210 230,320 90,350 20,260",
    },
    {
        id: "zoneD",
        label: "Zone D",
        color: "#A855F7",
        points: "230,320 380,210 480,250 470,350 300,370",
    },
];

export const drones = [
    { id: "d1", x: 140, y: 105, icon: "🚁" }, // head drone — zone A
    { id: "d2", x: 220, y: 145, icon: "🛸" },
    { id: "d3", x: 385, y: 105, icon: "🛸" }, // zone B
    { id: "d4", x: 455, y: 150, icon: "🛸" },
    { id: "d5", x: 120, y: 235, icon: "🛸" }, // zone C
    { id: "d6", x: 150, y: 295, icon: "🛸" },
    { id: "d7", x: 335, y: 270, icon: "🛸" }, // zone D
    { id: "d8", x: 400, y: 305, icon: "🛸" },
];

export const cctvMarkers = [
    { id: "cam1", x: 250, y: 130 },
    { id: "cam2", x: 420, y: 260 },
];

export const crowdDots = [
    { id: "c1", x: 110, y: 95, level: "high" },
    { id: "c2", x: 340, y: 185, level: "medium" },
    { id: "c3", x: 260, y: 300, level: "low" },
    { id: "c4", x: 430, y: 120, level: "medium" },
];

export const crowdColors = {
    high: "#EF4444",
    medium: "#F97316",
    low: "#22C55E",
};