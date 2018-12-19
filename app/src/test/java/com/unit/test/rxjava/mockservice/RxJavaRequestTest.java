package com.unit.test.rxjava.mockservice;

import com.unit.test.mockito.model.ModelImpl;
import com.unit.test.rxjava.rule.RxJavaRule1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lisen
 * @since 12-07-2018
 */
@RunWith(RobolectricTestRunner.class)
public class RxJavaRequestTest {

    @Rule
    public RxJavaRule1 rule1 = new RxJavaRule1();

    @Test
    public void requestTest() {
//        RxJavaUtils.initRxJava();
        Observable<Response<ModelImpl>> api = Observable
                .just(new ModelImpl())
                .map(model -> new Response<ModelImpl>(true, 200, model))
//                .map(model -> new Response<ModelImpl>(false, 404, model))
                .delay(100, TimeUnit.MILLISECONDS, Schedulers.io());

        api
//                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseCallback<ModelImpl>() {
                    @Override
                    void onSuccess(ModelImpl model) {
                        System.out.println("onSuccess " + model + " " + Thread.currentThread().getName());
                    }

                    @Override
                    void error(Throwable e) {
                        System.out.println("error " + e.toString() + " " + Thread.currentThread().getName());
                    }

                    @Override
                    void onFailed(int code, String detail) {
                        System.out.println("onFailed " + code + " " + detail + " " + Thread.currentThread().getName());
                    }
                });
    }

    abstract class ResponseCallback<T> implements Observer<Response<T>> {

        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(Response<T> response) {
            if (response.isSuccess) {
                onSuccess(response.data);
            } else {
                onFailed(response.code, response.data.toString());
            }
        }

        @Override
        public void onError(Throwable e) {
            error(e);
        }

        @Override
        public void onComplete() {
        }

        abstract void onSuccess(T t);

        abstract void error(Throwable e);

        abstract void onFailed(int code, String detail);
    }

    class Response<T> {
        public boolean isSuccess;
        public int code;
        public T data;

        public Response(boolean isSuccess, int code, T data) {
            this.isSuccess = isSuccess;
            this.code = code;
            this.data = data;
        }
    }
}
