#!/bin/bash


GATLING_HOME="/" 
SIMULATION_DIR="$PWD/user-files/simulations"
RESULTS_DIR="$PWD/user-files/results"


"$GATLING_HOME/bin/gatling.sh" \
  -sf "$SIMULATION_DIR" \
  -rf "$RESULTS_DIR" \
  -s RinhaBackendCrebitosSimulation

RELATORIO=$(ls -t "$RESULTS_DIR" | head -n 1)
echo "Relatório gerado: $RESULTS_DIR/$RELATORIO/index.html"