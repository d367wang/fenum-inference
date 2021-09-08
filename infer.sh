#!/bin/bash

mydir="`dirname $BASH_SOURCE`"
cfDir="${mydir}"/../checker-framework-inference
. "${cfDir}"/scripts/runtime-env-setup.sh

CHECKER=fenum.FenumChecker

SOLVER=fenum.solver.FenumSolverEngine
IS_HACK=true

DEBUG_SOLVER=checkers.inference.solver.DebugSolver
#SOLVER="$DEBUG_SOLVER"
# IS_HACK=false
# DEBUG_CLASSPATH=""

FENUMPATH=$ROOT/fenum-inference/build/classes/java/main
export CLASSPATH=$FENUMPATH:$DEBUG_CLASSPATH:.
export external_checker_classpath=$FENUMPATH

$CFI/scripts/inference-dev --checker "$CHECKER" --solver "$SOLVER" --solverArgs="collectStatistics=true" --hacks="$IS_HACK" -m ROUNDTRIP -afud ./debug "$@"

# TYPE CHECKING
# $CFI/scripts/inference-dev --checker "$CHECKER" --solver "$SOLVER" --solverArgs="collectStatistics=true,solver=z3" --hacks="$IS_HACK" -m TYPECHECK "$@"
