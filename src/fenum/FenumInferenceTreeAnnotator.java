package fenum;

import checkers.inference.*;
import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreePath;
import org.checkerframework.framework.type.AnnotatedTypeFactory;
import org.checkerframework.framework.type.AnnotatedTypeMirror;
import org.checkerframework.framework.type.QualifierHierarchy;
import org.checkerframework.javacutil.ElementUtils;
import org.checkerframework.javacutil.TreeUtils;
import org.checkerframework.javacutil.TypesUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.VariableElement;
import java.util.Collections;
import java.util.Set;

public class FenumInferenceTreeAnnotator extends InferenceTreeAnnotator {
    private final AnnotationMirror REALTOP;
    private final QualifierHierarchy realQualifierHierarchy;
    private final FenumAnnotatedTypeFactory realTypeFactory;

    public FenumInferenceTreeAnnotator(InferenceAnnotatedTypeFactory atypeFactory,
                                       InferrableChecker realChecker, AnnotatedTypeFactory realAnnotatedTypeFactory,
                                       VariableAnnotator variableAnnotator, SlotManager slotManager)  {
        super(atypeFactory, realChecker, realAnnotatedTypeFactory, variableAnnotator, slotManager);

        this.realTypeFactory = (FenumAnnotatedTypeFactory) realAnnotatedTypeFactory;
        realQualifierHierarchy = realTypeFactory.getQualifierHierarchy();
        Set<? extends AnnotationMirror> tops = realQualifierHierarchy.getTopAnnotations();
        assert tops.size() == 1;
        REALTOP = (AnnotationMirror) tops.toArray()[0];
    }



    private void defaultIfNotIntegral(AnnotatedTypeMirror annotatedTypeMirror) {
        if (!TypesUtils.isIntegralPrimitiveOrBoxed(annotatedTypeMirror.getUnderlyingType())) {
            annotatedTypeMirror.addMissingAnnotations(realQualifierHierarchy.getTopAnnotations());
        }
    }
//
//    @Override
//    protected Void defaultAction(Tree node, AnnotatedTypeMirror annotatedTypeMirror) {
//        defaultIfNotIntegral(annotatedTypeMirror);
//        return super.defaultAction(node, annotatedTypeMirror);
//    }

//    @Override
//    public Void visitVariable(VariableTree varTree, AnnotatedTypeMirror atm) {
//        defaultIfNotIntegral(atm);
//
//        if (TypesUtils.isIntegralPrimitiveOrBoxed(atm.getUnderlyingType()) && varTree.getInitializer() != null) {
//            VariableElement varElement = TreeUtils.elementFromDeclaration(varTree);
//
//            if (ElementUtils.isFinal(varElement) && atm.getAnnotationInHierarchy(REALTOP) != null) {
//
//            }
//        }
//        return super.visitVariable(varTree, atm);
//    }

    @Override
    public Void visitLiteral(LiteralTree literalTree, AnnotatedTypeMirror atm) {
        defaultIfNotIntegral(atm);

        if (TypesUtils.isIntegralPrimitive(atm.getUnderlyingType())) {
//            TreePath path = atypeFactory.getPath(literalTree);
//            // TODO: path cannot be null when each artificial tree is given a path
//            if (path != null) {
//                final TreePath parentPath = path.getParentPath();
//                final Tree parentNode = parentPath.getLeaf();
//                if (parentNode.getKind() == Tree.Kind.VARIABLE) {
//                    // the literal is
//
//                }
//            }

            // Literals are fenum-unqualified, unless being type casted to a fenum
            atm.addMissingAnnotations(Collections.singleton(realTypeFactory.FENUM_UNQUALIFIED));

        }
        return super.visitLiteral(literalTree, atm);
    }

}
