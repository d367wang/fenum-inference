package fenum.solver;

import checkers.inference.solver.backend.encoder.AbstractConstraintEncoderFactory;
import checkers.inference.solver.backend.encoder.ArithmeticConstraintEncoder;
import checkers.inference.solver.backend.encoder.binary.ComparableConstraintEncoder;
import checkers.inference.solver.backend.encoder.binary.EqualityConstraintEncoder;
import checkers.inference.solver.backend.encoder.binary.InequalityConstraintEncoder;
import checkers.inference.solver.backend.encoder.binary.SubtypeConstraintEncoder;
import checkers.inference.solver.backend.encoder.combine.CombineConstraintEncoder;
import checkers.inference.solver.backend.encoder.existential.ExistentialConstraintEncoder;
import checkers.inference.solver.backend.encoder.implication.ImplicationConstraintEncoder;
import checkers.inference.solver.backend.encoder.preference.PreferenceConstraintEncoder;
import checkers.inference.solver.backend.maxsat.encoder.MaxSATComparableConstraintEncoder;
import checkers.inference.solver.backend.maxsat.encoder.MaxSATInequalityConstraintEncoder;
import checkers.inference.solver.backend.maxsat.encoder.MaxSATPreferenceConstraintEncoder;
import checkers.inference.solver.frontend.Lattice;
import fenum.solver.encoder.FenumSATEqualityConstraintEncoder;
import fenum.solver.encoder.FenumSATSubtypeConstraintEncoder;
import org.sat4j.core.VecInt;

import javax.lang.model.element.AnnotationMirror;
import java.util.Map;

public class FenumConstraintEncoderFactory extends AbstractConstraintEncoderFactory<VecInt[], FenumFormatTranslator> {
    private final Map<AnnotationMirror, Integer> typeToInt;

    public FenumConstraintEncoderFactory(Lattice lattice, Map<AnnotationMirror, Integer> typeToInt, FenumFormatTranslator formatTranslator) {
        super(lattice, formatTranslator);
        this.typeToInt = typeToInt;
    }

    @Override
    public SubtypeConstraintEncoder<VecInt[]> createSubtypeConstraintEncoder() {
        return new FenumSATSubtypeConstraintEncoder(lattice, typeToInt);
    }

    @Override
    public EqualityConstraintEncoder<VecInt[]> createEqualityConstraintEncoder() {
        return new FenumSATEqualityConstraintEncoder(lattice, typeToInt);
    }

    @Override
    public InequalityConstraintEncoder<VecInt[]> createInequalityConstraintEncoder() {
        return null;
    }

    @Override
    public ComparableConstraintEncoder<VecInt[]> createComparableConstraintEncoder() {
        return new MaxSATComparableConstraintEncoder(lattice, typeToInt);
    }

    @Override
    public PreferenceConstraintEncoder<VecInt[]> createPreferenceConstraintEncoder() {
        return new MaxSATPreferenceConstraintEncoder(lattice, typeToInt);
    }

    @Override
    public CombineConstraintEncoder<VecInt[]> createCombineConstraintEncoder() {
        return null;
    }

    @Override
    public ExistentialConstraintEncoder<VecInt[]> createExistentialConstraintEncoder() {
        return null;
    }

    @Override
    public ImplicationConstraintEncoder<VecInt[]> createImplicationConstraintEncoder() {
        return null;
    }

    @Override
    public ArithmeticConstraintEncoder<VecInt[]> createArithmeticConstraintEncoder() {
        return null;
    }
}
