package com.unit.test.mockito;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author lisen
 * @since 12-07-2018
 * <p>
 * <a href=https://github.com/mockito/mockito/wiki/What%27s-new-in-Mockito-2#mock-the-unmockable-opt-in-mocking-of-final-classesmethods>
 * mocking of final classes/methods
 * </a>
 */
public class MockFinalTest {

    final class FinalClass {
        final String finalMethod() {
            return "final";
        }
    }

    @Test
    public void finalTest() {
        FinalClass finalClass = new FinalClass();
        FinalClass mock = mock(FinalClass.class);
        given(mock.finalMethod()).willReturn("mock final");

        assertNotEquals(mock.finalMethod(), finalClass.finalMethod());
    }
}
