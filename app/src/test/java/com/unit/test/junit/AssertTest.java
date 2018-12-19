package com.unit.test.junit;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.describedAs;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.CoreMatchers.theInstance;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author lisen
 * @since 12-05-2018
 * {@link org.junit.Assert}
 */
public class AssertTest {

    class A {
    }

    @Test
    public void assertTrueTest() {
        assertTrue(true);
    }

    @Test
    @Ignore
    public void assertTrueErrorTest() {
        assertTrue("出错了", false);
    }

    @Test
    public void assertTest() {
        A a = new A();
        A b = new A();
        A c = a;

        assertTrue(true);
        assertFalse(false);

//        fail("failed")

        assertEquals(a, c);
        assertNotEquals(a, b);

        assertArrayEquals(new A[]{a, b}, new A[]{a, b});

        int i1 = 128;
        Integer i2 = 128;//i2 = Integer.valueOf()
        Integer i3 = new Integer(128);

        assertFalse(i2 == i3);
        assertTrue(i2.equals(i3));

        assertNull(null);
        assertNotNull("");

        assertSame(a, c);
        assertNotSame(a, b);
    }


    /**
     * @see org.hamcrest.CoreMatchers
     */
    @Test
    public void assertThatTest() {
        assertThat(1, is(1));

        assertThat(1, is(equalTo(1)));

        assertThat(1, allOf(is(1), not(0)));

        assertThat(1, anyOf(is(0), not(2)));

        assertThat(1, both(is(1)).and(not(2)));

        assertThat(1, either(is(1)).or(not(1)));

        assertThat(1, describedAs("describeAs error", is(1), "abc"));

        assertThat(Arrays.asList("bar", "baz"), everyItem(startsWith("ba")));

        assertThat(new A(), isA(A.class));

        assertThat(1, equalTo(1));

        assertThat(new A(), any(A.class));

        assertThat(new A(), instanceOf(A.class));

        assertThat(1, not(0));

        assertThat(null, nullValue());

        assertThat(new A(), notNullValue());

        A a = new A();
        A b = a;
        assertThat(a, isA(A.class));

        assertThat(a, anything());

        assertThat(a, any(A.class));

        assertThat(a, not(new A()));

        assertThat(a, not(is(new A())));

        assertThat(a, sameInstance(b));

        assertThat(a, theInstance(b));

        assertThat("abc", containsString("a"));

        assertThat("abc", startsWith("a"));

        assertThat("abc", endsWith("c"));
    }

}
