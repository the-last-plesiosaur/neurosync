#!/usr/bin/env bash

exec docker run -it --rm --network postgres-local-network postgres:18 psql -h postgres-local -U postgres

# Needed to exec because of error on MacOS
