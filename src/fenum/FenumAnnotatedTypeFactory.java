package fenum;

import checkers.inference.BaseInferenceRealTypeFactory;
import fenum.qual.Fenum;
import fenum.qual.FenumBottom;
import fenum.qual.FenumTop;
import fenum.qual.FenumUnqualified;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.framework.type.MostlyNoElementQualifierHierarchy;
import org.checkerframework.framework.type.QualifierHierarchy;
import org.checkerframework.framework.util.DefaultQualifierKindHierarchy;
import org.checkerframework.framework.util.QualifierKind;
import org.checkerframework.framework.util.QualifierKindHierarchy;
import org.checkerframework.javacutil.AnnotationBuilder;
import org.checkerframework.javacutil.AnnotationUtils;
import org.checkerframework.javacutil.TypeSystemError;
import org.checkerframework.javacutil.UserError;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.util.Elements;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Set;

public class FenumAnnotatedTypeFactory extends BaseInferenceRealTypeFactory {
    protected AnnotationMirror FENUM_TOP;
    protected AnnotationMirror FENUM_UNQUALIFIED;
    protected AnnotationMirror FENUM_BOTTOM;

    public FenumAnnotatedTypeFactory(BaseTypeChecker checker, boolean isInfer) {
        super(checker, isInfer);

        FENUM_TOP = AnnotationBuilder.fromClass(elements, FenumTop.class);
        FENUM_BOTTOM = AnnotationBuilder.fromClass(elements, FenumBottom.class);
        FENUM_UNQUALIFIED = AnnotationBuilder.fromClass(elements, FenumUnqualified.class);

        this.postInit();
    }

    /**
     * Copied from SubtypingChecker. Instead of returning an empty set if no "quals" option is
     * given, we return Fenum as the only qualifier.
     */
    @Override
    protected Set<Class<? extends Annotation>> createSupportedTypeQualifiers() {
        // Load everything in qual directory, and top, bottom, unqualified, and fake enum
        Set<Class<? extends Annotation>> qualSet =
                getBundledTypeQualifiers(
                        FenumTop.class,
                        Fenum.class,
                        FenumUnqualified.class,
                        FenumBottom.class);

        return qualSet;
    }

    @Override
    public QualifierHierarchy createQualifierHierarchy() {
        return new FenumQualifierHierarchy(getSupportedTypeQualifiers(), elements);
    }

    protected class FenumQualifierHierarchy extends MostlyNoElementQualifierHierarchy {

        /** QualifierKind for {@link Fenum} qualifier. */
        private final QualifierKind FENUM_KIND;

        public FenumQualifierHierarchy(Collection<Class<? extends Annotation>> qualifierClasses, Elements elements) {
            super(qualifierClasses, elements);

            this.FENUM_KIND =
                    this.qualifierKindHierarchy.getQualifierKind(Fenum.class.getCanonicalName());
        }

        @Override
        protected QualifierKindHierarchy createQualifierKindHierarchy(Collection<Class<? extends Annotation>> qualifierClasses) {
            return new DefaultQualifierKindHierarchy(qualifierClasses, FenumBottom.class);
        }

        @Override
        protected boolean isSubtypeWithElements(AnnotationMirror subAnno, QualifierKind subKind, AnnotationMirror superAnno, QualifierKind superKind) {
            // only one qualifier kind @Fenum has element, just compare the annotation value
            return AnnotationUtils.areSame(superAnno, subAnno);
        }

        @Override
        protected AnnotationMirror leastUpperBoundWithElements(AnnotationMirror a1, QualifierKind qualifierKind1, AnnotationMirror a2, QualifierKind qualifierKind2, QualifierKind lubKind) {
            if (qualifierKind1 == FENUM_KIND && qualifierKind2 == FENUM_KIND) {
                if (AnnotationUtils.areSame(a1, a2)) {
                    return a1;
                }
                return FENUM_TOP;
            } else if (qualifierKind1 == FENUM_KIND) {
                return a1;
            } else if (qualifierKind2 == FENUM_KIND) {
                return a2;
            }
            throw new TypeSystemError(
                    "Unexpected QualifierKinds %s %s", qualifierKind1, qualifierKind2);
        }

        @Override
        protected AnnotationMirror greatestLowerBoundWithElements(AnnotationMirror a1, QualifierKind qualifierKind1, AnnotationMirror a2, QualifierKind qualifierKind2, QualifierKind glbKind) {
            return null;
        }

    }
}
