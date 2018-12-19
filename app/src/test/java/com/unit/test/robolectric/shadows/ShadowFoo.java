package com.unit.test.robolectric.shadows;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * @author lisen
 * @since 12-11-2018
 */
@Implements(Foo.class)
public class ShadowFoo {
    @Implementation
    public void show() {
        System.out.println("shadow foo");
    }
}
