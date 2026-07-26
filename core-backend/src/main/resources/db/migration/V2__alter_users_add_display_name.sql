ALTER TABLE users
    ADD COLUMN IF NOT EXISTS display_name VARCHAR(255);

ALTER TABLE users
    RENAME COLUMN password TO password_hash;
