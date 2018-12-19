package com.unit.test.mockito.verify;

import com.unit.test.mockito.model.ModelImpl;
import com.unit.test.mockito.model.PresenterImpl;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.after;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author lisen
 * @since 12-06-2018
 * <p>
 * {@link org.mockito.verification.VerificationMode}
 */
public class VerifyTest {

    @Mock
    ModelImpl model;

    @Mock
    PresenterImpl presenter;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    /**
     * 验证执行
     */
    @Test
    public void verifyTest() {
        model.getData();

        verify(model).getData();

        verify(model, times(1).description("getData 方法 执行的次数不是 1")).getData();

        verify(model, atLeastOnce()).getData();

        verify(model, never()).setData(anyString());

        verify(model, atLeast(0)).getData();

        verify(model, atMost(5)).getData();

//        model.setData("data");
        verify(model, only()).getData();

        inOrder(model).verify(model, calls(1)).getData();
    }

    @Test
    public void inOrderTest() {
        model.setData("data1");
        model.setData("data2");

        InOrder inOrder = inOrder(model);

        inOrder.verify(model).setData("data1");
        inOrder.verify(model).setData("data2");
    }

    @Test
    public void inOrderTest1() {
        List list1 = mock(ArrayList.class);
        list1.add("aaa");
        List list2 = mock(ArrayList.class);
        list2.add("bbb");
        InOrder inOrder = Mockito.inOrder(list1, list2);
        inOrder.verify(list1).add("aaa");
        inOrder.verify(list2).add("bbb");
    }

    /**
     * 执行完毕立刻得到结论 < 2s
     * start   time:1544079053755
     * timeout time:1544079054777
     */
    @Test
    public void verifyTimeoutTest() {
        doCallRealMethod().when(model).sleep(anyLong());
        new Thread(() -> model.sleep(1000)).start();

        System.out.println("start   time:" + System.currentTimeMillis());

        verify(model, timeout(2000)).getData();

        System.out.println("timeout time:" + System.currentTimeMillis());

    }

    /**
     * 需要等 2 s 以后才会判断 == 2s
     * start   time:1544079034604
     * after   time:1544079036609
     */
    @Test
    public void verifyAfterTest() {
        doCallRealMethod().when(model).sleep(anyLong());
        new Thread(() -> model.sleep(1000)).start();

        System.out.println("start   time:" + System.currentTimeMillis());

        verify(model, after(2000)).getData();

        System.out.println("after   time:" + System.currentTimeMillis());

    }


    @Test
    public void verifyCombinationTest() {
        doCallRealMethod().when(model).sleep(anyLong());
        new Thread(new Runnable() {
            @Override
            public void run() {
                model.sleep(1000);
            }
        }).start();

        verify(model, after(1100).times(1)).getData();

        verify(model, timeout(1000).times(1)).getData();
    }

    @Test
    public void verifyPresenterTest() {
        doCallRealMethod().when(presenter).setModel(any());
        doCallRealMethod().when(presenter).setData(anyString());

        presenter.setModel(model);
        presenter.setData("msg");

        verify(model).setData(anyString());

        mockingDetails(presenter).printInvocations();
    }

}
