package com.unit.test.mockito.mock;

import com.unit.test.mockito.model.ModelImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @author lisen
 * @since 12-06-2018
 */
@RunWith(MockitoJUnitRunner.class)
public class MockJunitRunnerTest {
    @Mock
    ModelImpl model;

    @Test
    public void testMock() {
        assertNotNull(model);
    }
}
