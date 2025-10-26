--long id;
--      private long user_id;
--      private String token;
--      private Date dateExpired;
--      private Date dateCreated;

CREATE UNLOGED TABLE JwtRefreshTokens(
    id BIGINT GENERATED PRIMARY KEY NOT NULL,
    user_id BIGINT NOT NULL,
    dateCreated TIMESTAMP NOT NULL,
    dateExpired TIMESTAMP NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id)
);
