CREATE TABLE interactions(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    interaction_type VARCHAR(50) NOT NULL,
    admin_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    reason TEXT NOT NULL,
    interaction_date TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_admin FOREIGN KEY(admin_id) REFERENCES users(id) ON DELETE CASCADE
);