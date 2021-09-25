package fenum.solver.encoder;

import checkers.inference.solver.backend.maxsat.encoder.MaxSATSubtypeConstraintEncoder;
import checkers.inference.solver.frontend.Lattice;

import javax.lang.model.element.AnnotationMirror;
import java.util.Map;

public class FenumSATSubtypeConstraintEncoder extends MaxSATSubtypeConstraintEncoder {
    public FenumSATSubtypeConstraintEncoder(Lattice lattice, Map<AnnotationMirror, Integer> typeToInt) {
        super(lattice, typeToInt);
    }
}
