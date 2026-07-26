INSERT INTO users (id, username, password_hash, role, display_name, created_at)
VALUES (
    '7f8a4d8d-4c99-47a1-b0d8-9fd37d8c6f21',
    'operator@aegissight.local',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    'OPERATOR',
    'Control Room Operator',
    NOW()
)
ON CONFLICT (username) DO NOTHING;
