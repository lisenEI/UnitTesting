package com.unit.test.rxjava.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lisen
 * @since 09-04-2018
 */

public class RxJavaRule1 implements TestRule {

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());

                RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

                RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());

                RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }

            }
        };
    }
}
