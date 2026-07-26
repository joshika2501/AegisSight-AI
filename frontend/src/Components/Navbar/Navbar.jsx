function Navbar() {
    return (
        <nav className="navbar">

            {/* Search */}
            <div className="navbar-search">
                <span className="navbar-search-icon">🔍</span>
                <input
                    type="text"
                    className="navbar-search-input"
                    placeholder="Search for zones, blocks, cameras, drones..."
                />
            </div>

            {/* Actions */}
            <div className="navbar-actions">

                <button className="emergency-alert-btn">
                    <span className="emergency-alert-icon">🚨</span>
                    Emergency Alert
                </button>

                <div className="navbar-icon-group">
                    <button className="navbar-icon-btn">
                        <span>🔔</span>
                        <span className="icon-badge"></span>
                    </button>

                    <button className="navbar-icon-btn">
                        <span>✉</span>
                    </button>

                    <button className="navbar-icon-btn">
                        <span>⚙</span>
                    </button>
                </div>

                <div className="navbar-divider"></div>

                <div className="navbar-profile">
                    <div className="navbar-avatar">👤</div>
                    <div className="navbar-profile-text">
                        <span className="navbar-profile-name">Operator 01</span>
                        <span className="navbar-profile-role">Control Center</span>
                    </div>
                </div>

            </div>

        </nav>
    );
}

export default Navbar;