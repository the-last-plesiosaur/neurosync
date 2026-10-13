# Database Setup

1. Create an `.env` file in this directory with a single variable `POSTGRES_PASSWORD`. 
2. Run `docker compose up -d` to start the database.
3. (Optional) Connect to the postgreSQL database using the script `postgres-local-client.sh`
4. Execute the commands in `initial-db-local.sql` 

`initial-db-local.sql` hard-codes usernames and passwords for the neurosync account.