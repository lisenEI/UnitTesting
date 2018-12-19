package com.unit.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * @author lisen
 * @since 12-11-2018
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    @Test
    public void  useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.unit.test.unittesting", appContext.getPackageName());
    }
}
