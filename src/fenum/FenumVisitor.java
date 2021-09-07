package fenum;

import checkers.inference.InferenceChecker;
import checkers.inference.InferenceVisitor;
import org.checkerframework.common.basetype.BaseAnnotatedTypeFactory;
import org.checkerframework.framework.type.AnnotatedTypeMirror;

import javax.lang.model.element.ExecutableElement;

public class FenumVisitor extends InferenceVisitor<FenumChecker, BaseAnnotatedTypeFactory> {
    public FenumVisitor(FenumChecker checker, InferenceChecker ichecker,
                           BaseAnnotatedTypeFactory factory, boolean infer) {
        super(checker, ichecker, factory, infer);
    }

    /**
     * Skip this test because a constructor always produces objects of that underlying type.
     * TODO: validate constructor result appropriately.
     */
    @Override
    protected void checkConstructorResult(
            AnnotatedTypeMirror.AnnotatedExecutableType constructorType, ExecutableElement constructorElement) {
    }
}
