package fenum.solver;

import checkers.inference.InferenceMain;
import checkers.inference.model.Constraint;
import checkers.inference.model.Slot;
import checkers.inference.solver.backend.AbstractSolverFactory;
import checkers.inference.solver.backend.Solver;
import checkers.inference.solver.frontend.Lattice;
import checkers.inference.solver.util.SolverEnvironment;

import java.util.Collection;

public class FenumSolverFactory extends AbstractSolverFactory<FenumFormatTranslator> {
    @Override
    protected FenumFormatTranslator createFormatTranslator(Lattice lattice) {
        return new FenumFormatTranslator(lattice);
    }

    @Override
    public Solver<?> createSolver(SolverEnvironment solverOptions, Collection<Slot> slots,
                                  Collection<Constraint> constraints, Lattice lattice) {

        Lattice fenumLattice = new FenumLatticeBuilder().buildLattice(
                InferenceMain.getInstance().getRealTypeFactory().getQualifierHierarchy(),
                slots);

        FenumFormatTranslator formatTranslator = createFormatTranslator(fenumLattice);
        return new FenumMaxSatSolver(solverOptions, slots, constraints, formatTranslator, fenumLattice);
    }
}
