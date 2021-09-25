package fenum.solver.encoder;

import checkers.inference.solver.backend.maxsat.encoder.MaxSATEqualityConstraintEncoder;
import checkers.inference.solver.frontend.Lattice;

import javax.lang.model.element.AnnotationMirror;
import java.util.Map;

public class FenumSATEqualityConstraintEncoder extends MaxSATEqualityConstraintEncoder {
    public FenumSATEqualityConstraintEncoder(Lattice lattice, Map<AnnotationMirror, Integer> typeToInt) {
        super(lattice, typeToInt);
    }
}
