#!/bin/sh
# Delegate to the Maven wrapper located in the `p1` project directory.
DIR="$(dirname "$0")/p1"
cd "$DIR" || exit 1
exec ./mvnw "$@"
