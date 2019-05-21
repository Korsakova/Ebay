package example.common.services.Listeners;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.slf4j.MDC;

@Slf4j
public class JUnitExecutionListener extends RunListener {
    public void testRunStarted(Description description) {
        log.debug("Class:" + description.getTestClass().getName() + ": to execute in class: " + description.testCount());
    }

    public void testRunFinished(Result result) {
        log.debug("Number of tests executed: " + result.getRunCount());
        log.debug("Number of tests Failure: " + result.getFailureCount());
        log.debug("Number of tests Ignore: " + result.getIgnoreCount());
    }

    public void testStarted(Description description) {
        log.debug("Starting: " + description.getMethodName());
//        MDC.put("ClassName", description.getTestClass().getName());
        MDC.put("MethodName", description.getDisplayName()
                .replace(description.getTestClass().getName(), "")
                .replace("()", ""));
    }

    public void testFinished(Description description) {
        log.debug("Finished: " + description.getMethodName());
//        selenoid.attachAllureVideo(TestProps.sessionId);
//        log.debug("Finished: " + description.getMethodName());
    }

    public void testFailure(Failure failure) {
        log.debug("Failed: " + failure.getDescription().getMethodName());
    }

    public void testAssumptionFailure(Failure failure) {
        log.debug("Failed: " + failure.getDescription().getMethodName());
    }

    public void testIgnored(Description description) {
        log.debug("Ignored: " + description.getMethodName());
    }

}
