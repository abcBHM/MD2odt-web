
CREATE TABLE IF NOT EXISTS logs (
    "id"         SERIAL PRIMARY KEY NOT NULL,
    "start-time" BIGINT NOT NULL,
    "end-time"   BIGINT NOT NULL,
    "log"        TEXT NOT NULL,
    "exception"  TEXT
);
