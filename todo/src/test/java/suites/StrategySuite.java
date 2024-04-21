package suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        tests.BasicBoundaryValueAnalysisTests.class,
        tests.BasicCoverageTests.class,
        tests.BasicEquivalencePartitioningTests.class})
public class StrategySuite {
}