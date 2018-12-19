package com.unit.test.mockito.spy;

import com.unit.test.mockito.model.ModelImpl;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;

/**
 * @author lisen
 * @since 12-06-2018
 */
public class MockTest {

    @Mock
    ModelImpl model;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void testMock() {
        assertNotNull(model);
    }

    @Test
    @Ignore
    public void testGetDefaultData() {
        String data = model.getData();
        assertEquals(ModelImpl.DEFAULT_DATA, data);
    }

    @Test
    @Ignore
    public void testGetData() {
        String data = "test_data";
        model.setData(data);
        assertEquals(data, model.getData());
    }

    @Test
    public void testGetDataCallReal() {
        doCallRealMethod().when(model).getData();
        doCallRealMethod().when(model).setData(anyString());

        String data = "test_data";
        model.setData(data);

        assertEquals(data, model.getData());
    }

}
