package fenum;

import checkers.inference.BaseInferrableChecker;

public class FenumChecker extends BaseInferrableChecker {
    @Override
    public FenumAnnotatedTypeFactory createRealTypeFactory() {
        return new FenumAnnotatedTypeFactory(this);
    }
}
