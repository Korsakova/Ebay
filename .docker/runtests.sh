#!/usr/bin/env bash
source ./.projectEnv
docker-compose -f testing.yml up
$(exit $(docker inspect $(docker-compose -f docker-compose.yml ps -q mobile-test) --format='{{.State.ExitCode}}'))
docker-compose -f testing.yml down