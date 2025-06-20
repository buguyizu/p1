#!/bin/sh
# Simple Maven wrapper to run Maven in project directory
DIR="$(dirname "$0")/p1"
cd "$DIR" || exit 1
exec mvn "$@"
