package suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        tests.BasicBoundaryValueAnalysisTests.class,
        tests.BasicCoverageTests.class,
        tests.BasicEquivalencePartitioningTests.class,
        tests.FindWhatTaskToDoNextTestEquivalencePartitioningTests.class,
        tests.FindWhatTaskToDoNextTestBoundaryValueAnalysisTests.class,
        tests.FindWhatTaskToDoNextTestDecisionCoverageTests.class,
        tests.RecommendTaskEquivalencePartitioning.class
})
public class StrategySuite {
}