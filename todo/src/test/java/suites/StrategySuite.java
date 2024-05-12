package suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Strategy Suite")
@SelectClasses({
        tests.FindWhatTaskToDoNextTestCoverageTests.class,
        tests.FindWhatTaskToDoNextTestDecisionCoverageTests.class,
        tests.FindWhatTaskToDoNextTestEquivalencePartitioningTests.class,
        tests.FindWhatTaskToDoNextMutationTests.class
})
public class StrategySuite {
}