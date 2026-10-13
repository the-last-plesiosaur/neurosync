CREATE DATABASE dev;

CREATE USER neurosync_user WITH PASSWORD 'neurosync_pass';
GRANT CONNECT ON DATABASE dev TO neurosync_user;

\c dev;

CREATE SCHEMA neurosync; 
GRANT USAGE ON SCHEMA neurosync TO neurosync_user;
GRANT CREATE ON SCHEMA neurosync TO neurosync_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA neurosync GRANT ALL PRIVILEGES ON TABLES TO neurosync_user;

ALTER USER neurosync_user SET search_path = neurosync, public;