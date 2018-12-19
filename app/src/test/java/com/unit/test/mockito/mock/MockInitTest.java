package com.unit.test.mockito.mock;

import com.unit.test.mockito.model.ModelImpl;
import com.unit.test.mockito.model.PresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.verify;

/**
 * @author lisen
 * @since 12-06-2018
 */
public class MockInitTest {
    @Mock
    ModelImpl model;

    @InjectMocks
    PresenterImpl presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMock() {
        assertNotNull(model);
        assertNotNull(presenter);

//        assertEquals(model.getData(), "default_data");
//
        doCallRealMethod().when(model).setData(anyString());
//
        doCallRealMethod().when(model).getData();

        presenter.setData("data");

//        verify(model).setData(anyString());
        assertEquals("data", model.getData());
    }
}
