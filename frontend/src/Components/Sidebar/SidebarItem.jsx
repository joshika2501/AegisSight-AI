function SidebarItem({ icon, label, active = false }) {
    return (
        <div className={`sidebar-item ${active ? "active" : ""}`}>
            <span className="sidebar-item-icon">{icon}</span>

            <span className="sidebar-item-label">
        {label}
      </span>
        </div>
    );
}

export default SidebarItem;