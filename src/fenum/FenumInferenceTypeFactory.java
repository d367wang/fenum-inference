package fenum;

import checkers.inference.InferenceAnnotatedTypeFactory;
import checkers.inference.InferenceChecker;
import checkers.inference.InferenceQualifierHierarchy;
import checkers.inference.InferrableChecker;
import checkers.inference.SlotManager;
import checkers.inference.model.ConstraintManager;
import checkers.inference.qual.VarAnnot;
import fenum.qual.FenumBottom;
import org.checkerframework.common.basetype.BaseAnnotatedTypeFactory;
import org.checkerframework.framework.type.QualifierHierarchy;
import org.checkerframework.framework.type.treeannotator.ListTreeAnnotator;
import org.checkerframework.framework.type.treeannotator.LiteralTreeAnnotator;
import org.checkerframework.framework.type.treeannotator.TreeAnnotator;
import org.checkerframework.framework.util.DefaultQualifierKindHierarchy;
import org.checkerframework.framework.util.QualifierKindHierarchy;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.util.Elements;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class FenumInferenceTypeFactory extends InferenceAnnotatedTypeFactory {
    public FenumInferenceTypeFactory(InferenceChecker inferenceChecker,
                                                 boolean withCombineConstraints, BaseAnnotatedTypeFactory realTypeFactory,
                                                 InferrableChecker realChecker, SlotManager slotManager, ConstraintManager constraintManager) {
        super(inferenceChecker, withCombineConstraints, realTypeFactory, realChecker, slotManager,
                constraintManager);
        postInit();
    }

    @Override
    public TreeAnnotator createTreeAnnotator() {
        return new ListTreeAnnotator(new LiteralTreeAnnotator(this),
                new FenumInferenceTreeAnnotator(this, realChecker, realTypeFactory,
                        variableAnnotator, slotManager));
    }

//    @Override
//    public QualifierHierarchy createQualifierHierarchy() {
//        return new FenumQualifierInferenceHierarchy(getSupportedTypeQualifiers(), elements);
//    }
//
//    protected class FenumQualifierInferenceHierarchy extends InferenceQualifierHierarchy {
//        public FenumQualifierInferenceHierarchy(Collection<Class<? extends Annotation>> qualifierClasses, Elements elements) {
//            super(qualifierClasses, elements);
//        }
//
//        @Override
//        protected QualifierKindHierarchy createQualifierKindHierarchy(Collection<Class<? extends Annotation>> qualifierClasses) {
//            return new DefaultQualifierKindHierarchy(qualifierClasses, FenumBottom.class);
//        }
//    }
}
