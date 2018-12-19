package com.unit.test.mockito.spy;

import com.unit.test.mockito.model.ModelImpl;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author lisen
 * @since 12-06-2018
 */
public class SpyTest {

    @Spy
    ModelImpl model;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void testSpy() {
        assertNotNull(model);
    }

    @Test
    public void testGetDefaultData() {
        String data = model.getData();
        assertEquals(ModelImpl.DEFAULT_DATA, data);
    }

    @Test
    public void testGetData() {
        String data = "test_data";
        model.setData(data);
        assertEquals(data, model.getData());
    }

}
