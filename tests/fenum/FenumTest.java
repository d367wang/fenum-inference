package fenum;

import checkers.inference.test.CFInferenceTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fenum.solver.FenumSolverEngine;
import org.checkerframework.framework.test.TestUtilities;
import org.checkerframework.javacutil.Pair;
import org.junit.runners.Parameterized.Parameters;

public class FenumTest extends CFInferenceTest {

    public FenumTest(File testFile) {
        super(
                testFile,
                fenum.FenumChecker.class,
                "testing",
                "-Anomsgtext",
                "-d",
                "tests/build/outputdir");
    }

    @Override
    public boolean useHacks() {
        return true;
    }

    @Override
    public Pair<String, List<String>> getSolverNameAndOptions() {
        final String solverName = FenumSolverEngine.class.getCanonicalName();
        List<String> solverOptions = new ArrayList<>();
        // solverOptions.add("solver=Z3");
        return Pair.of(solverName, solverOptions);
    }

    @Parameters
    public static List<File> getTestFiles() {
        List<File> testfiles = new ArrayList<>();
        testfiles.addAll(TestUtilities.findRelativeNestedJavaFiles("testing", "infer", "typecheck"));
        return testfiles;
    }
}
