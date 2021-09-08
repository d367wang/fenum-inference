package fenum;

import checkers.inference.BaseInferenceRealTypeFactory;
import checkers.inference.BaseInferrableChecker;
import checkers.inference.InferenceChecker;
import checkers.inference.InferrableChecker;
import checkers.inference.SlotManager;
import checkers.inference.model.ConstraintManager;
import org.checkerframework.common.basetype.BaseAnnotatedTypeFactory;

public class FenumChecker extends BaseInferrableChecker {
    @Override
    public BaseInferenceRealTypeFactory createRealTypeFactory(boolean infer) {
        return new FenumAnnotatedTypeFactory(this, infer);
    }

    @Override
    public FenumInferenceTypeFactory createInferenceATF(InferenceChecker inferenceChecker,
                                                        InferrableChecker realChecker,
                                                        BaseAnnotatedTypeFactory realTypeFactory,
                                                        SlotManager slotManager,
                                                        ConstraintManager constraintManager) {
        return new FenumInferenceTypeFactory(
                inferenceChecker, realChecker.withCombineConstraints(), realTypeFactory, realChecker,
                slotManager, constraintManager);
    }
}