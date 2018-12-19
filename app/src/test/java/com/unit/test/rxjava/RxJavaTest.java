package com.unit.test.rxjava;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;

import static com.unit.test.rxjava.RxJavaUtils.initRxJava;

/**
 * @author lisen
 * @since 12-07-2018
 */
public class RxJavaTest {

//    @Rule
//    public RxJavaRule rule = new RxJavaRule();


    @Test
    public void testObserverTest() {
        TestObserver<Integer> observer = TestObserver.create();

        observer.onNext(1);
        observer.assertValue(1);

        observer.onNext(2);
        observer.assertValues(1, 2);

        observer.onComplete();
        observer.assertComplete();
    }

    @Test
    public void justTest() {
        TestSubscriber testSubscriber = new TestSubscriber();

        Flowable.just(1, 2, 3).subscribe(testSubscriber);

        testSubscriber.assertNever(4);
        testSubscriber.assertValues(1, 2, 3);
        testSubscriber.assertValueCount(3);
        testSubscriber.assertTerminated();
    }

    @Test
    public void fromTest() {
        TestSubscriber subscriber = new TestSubscriber();
        Flowable.fromIterable(Arrays.asList(1, 2)).subscribe(subscriber);

        subscriber.assertValues(1, 2);
        subscriber.assertValueCount(2);
        subscriber.assertTerminated();
    }

    @Test
    public void rangeTest() {
        TestSubscriber subscriber = new TestSubscriber();
        Flowable.range(2, 3).subscribe(subscriber);

        subscriber.assertValues(2, 3, 4);
        subscriber.assertValueCount(3);
        subscriber.assertTerminated();
    }

    @Test
    public void repeatTest() {
        TestSubscriber subscriber = new TestSubscriber();
        Flowable.just(1, 2)
                .repeat(2)
                .subscribe(subscriber);

        subscriber.assertValues(1, 2, 1, 2);
        subscriber.assertValueCount(4);
        subscriber.assertTerminated();
    }

    @Test
    public void testBuffer() {
        TestSubscriber subscriber = new TestSubscriber();
        Flowable.just(1, 2, 3, 4)
                .buffer(2)
                .subscribe(subscriber);

        subscriber.assertResult(Arrays.asList(1, 2), Arrays.asList(3, 4));
        subscriber.assertValueCount(2);
        subscriber.assertTerminated();
    }

    @Test
    public void errorTest() {
        TestSubscriber subscriber = new TestSubscriber();
        RuntimeException error = new RuntimeException("error");
        Flowable.error(error).subscribe(subscriber);
        subscriber.assertError(error);
        subscriber.assertErrorMessage("error");
    }

    @Test
    public void intervalTest() {
        initRxJava();
        System.out.println("intervalTest: " + System.currentTimeMillis());
        TestSubscriber subscriber = new TestSubscriber();
        Flowable.interval(1, TimeUnit.SECONDS)
                .take(5)
                .subscribe(subscriber);
//        rule.getTestScheduler().advanceTimeBy(5, TimeUnit.SECONDS);
        subscriber.assertValueCount(5);
        subscriber.assertTerminated();
        System.out.println("intervalTest: " + System.currentTimeMillis());
    }


    @Test
    public void intervalTest1() {
        System.out.println("intervalTest: " + System.currentTimeMillis());
        TestScheduler scheduler = new TestScheduler();
        TestSubscriber subscriber = new TestSubscriber();
        Flowable.interval(1, TimeUnit.SECONDS, scheduler)
                .take(10)
                .subscribe(subscriber);
        scheduler.advanceTimeBy(3, TimeUnit.SECONDS);
        subscriber.assertValues(0l, 1l, 2l);
        subscriber.assertValueCount(3);
        subscriber.assertNotTerminated();

        scheduler.advanceTimeBy(10, TimeUnit.SECONDS);
        subscriber.assertValueCount(10);
        subscriber.assertTerminated();
        System.out.println("intervalTest: " + System.currentTimeMillis());
    }

    @Test
    public void timerTest() {
        TestScheduler scheduler = new TestScheduler();
        TestSubscriber subscriber = new TestSubscriber();
        Flowable.timer(5, TimeUnit.SECONDS, scheduler)
                .subscribe(subscriber);

        subscriber.assertValueCount(0);
        scheduler.advanceTimeTo(5, TimeUnit.SECONDS);
        subscriber.assertValue(0l);
        subscriber.assertValueCount(1);
        subscriber.assertTerminated();
    }
}
