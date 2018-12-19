package com.unit.test.mockito.mockresult;

import com.unit.test.mockito.model.ModelImpl;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * @author lisen
 * @since 12-06-2018
 */
public class MockResultTest {

    @Mock
    ModelImpl model;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    @Ignore
    public void doThrowTest() {
        doThrow(new NullPointerException("do throw exception")).when(model).getData();
        model.getData();
    }

    @Test
    @Ignore
    public void thenThrowTest() {
        when(model.getData()).thenThrow(new NullPointerException("then throw exception"));
        model.getData();
    }

    @Test
    public void doCallRealMethodTest() {
        doCallRealMethod().when(model).getData();
//        when(model.getData()).thenCallRealMethod();

        model.getData();
    }

    @Test
    public void doAnswerTest() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                Method method = invocation.getMethod();
                System.out.println("args: " + args.length + "  method: " + method.getName() + " mock: " + mock);
                return "hehe";
            }
        }).when(model).getData();

        assertEquals("hehe", model.getData());

//        when(model.getData()).thenAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                return null;
//            }
//        });
    }

    @Test
    public void doNothingTest() {
        doNothing().when(model).setData(anyString());
        model.setData("test");
    }

    @Test
    public void doReturnTest() {
        String returnData = "return_data";
        doReturn(returnData).when(model).getData();
        assertEquals(returnData, model.getData());

//        when(model.getData()).thenReturn(returnData);
    }

    @Test
    public void doReturnOrderTest() {
        String returnData = "return_data";
        String returnData1 = "return_data1";

        doReturn(returnData, returnData1).when(model).getData();
//        when(model.getData()).thenReturn(returnData, returnData1);

        assertEquals(returnData, model.getData());
        assertEquals(returnData1, model.getData());
        assertEquals(returnData1, model.getData());
        assertEquals(returnData1, model.getData());

    }

    @Test
    public void givenTest() {
        given(model.getData()).willReturn("given");
        assertEquals("given", model.getData());
    }

    @Test
    public void thenTest() {
        model.getData();
        then(model).should(times(1)).getData();
        then(model).should(only()).getData();
        then(model).should(timeout(100)).getData();

        //验证没有执行过
//        then(model).shouldHaveNoMoreInteractions();
//        then(model).shouldHaveZeroInteractions();
    }

}

