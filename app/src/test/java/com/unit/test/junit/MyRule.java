package com.unit.test.junit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author lisen
 * @since 12-05-2018
 */
public class MyRule implements TestRule {
    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println("- - - - rule 方法执行前 - - - -");
                base.evaluate();
                System.out.println("- - - - rule 方法执行后 - - - -");

            }
        };
    }
}
