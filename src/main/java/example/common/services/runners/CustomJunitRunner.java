package example.common.services.runners;

import example.common.services.Listeners.JUnitExecutionListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.annotation.ProfileValueUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class CustomJunitRunner extends SpringJUnit4ClassRunner {
    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @param klass
     * @throws InitializationError if the test class is malformed.
     */
    public CustomJunitRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    /**
     * Check whether the test is enabled in the current execution environment.
     * <p>This prevents classes with a non-matching {@code @IfProfileValue}
     * annotation from running altogether, even skipping the execution of
     * {@code prepareTestInstance()} methods in {@code TestExecutionListeners}.
     *
     * @see ProfileValueUtils#isTestEnabledInThisEnvironment(Class)
     * @see org.springframework.test.annotation.IfProfileValue
     * @see org.springframework.test.context.TestExecutionListener
     */
    @Override
    public void run(RunNotifier notifier) {
        if (!ProfileValueUtils.isTestEnabledInThisEnvironment(getTestClass().getJavaClass())) {
            notifier.fireTestIgnored(getDescription());
            return;
        }
        notifier.addListener(new JUnitExecutionListener());
        notifier.fireTestRunStarted(getDescription());
        super.run(notifier);
    }

}
