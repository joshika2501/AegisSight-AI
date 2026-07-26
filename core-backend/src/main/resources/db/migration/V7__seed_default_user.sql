INSERT INTO users (id, username, password_hash, role, display_name, created_at)
SELECT
    '7f8a4d8d-4c99-47a1-b0d8-9fd37d8c6f21',
    'operator@aegissight.local',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    'OPERATOR',
    'Control Room Operator',
    NOW()
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE username = 'operator@aegissight.local'
);