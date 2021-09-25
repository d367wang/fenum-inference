package fenum.solver;

import checkers.inference.solver.SolverEngine;
import checkers.inference.solver.backend.SolverFactory;

public class FenumSolverEngine extends SolverEngine {
    @Override
    protected SolverFactory createSolverFactory() {
        return new FenumSolverFactory();
    }
}
