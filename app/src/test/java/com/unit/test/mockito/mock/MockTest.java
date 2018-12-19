package com.unit.test.mockito.mock;

import com.unit.test.mockito.model.ModelImpl;
import com.unit.test.mockito.model.PresenterImpl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

/**
 * @author lisen
 * @since 12-06-2018
 */
public class MockTest {

    @Test
    public void testMock() {
        ModelImpl model = mock(ModelImpl.class);
        assertNotNull(model);
    }

    @Test
    public void withSettingsTest() {
        PresenterImpl presenter = mock(PresenterImpl.class, withSettings().useConstructor(new ModelImpl()).defaultAnswer(CALLS_REAL_METHODS));
        presenter.setData("data");
        assertEquals("data", presenter.getData());
    }

}
