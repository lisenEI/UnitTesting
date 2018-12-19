package com.unit.test.mockito.mock;

import com.unit.test.mockito.model.ModelImpl;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;

/**
 * @author lisen
 * @since 12-06-2018
 */
public class MockRuleTest {

    @Mock
    ModelImpl model;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void testMock() {
        assertNotNull(model);
    }

}
