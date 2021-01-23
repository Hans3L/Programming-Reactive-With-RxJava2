package com.creativity.reactiveprogramming.observables;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObservableUsingDefer {

    public static void main(String[] args) {
        // no crea el Observable hasta que el observador se suscriba
        // crea un Observable nuevo para cada observador(subscription)
        Observable<Integer> observableUsingDefer = Observable.defer(()->{
            return Observable.fromIterable(RxUtils.positiveNumbers(5));
        });
        observableUsingDefer.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                log.info("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                log.info("onNext -> {}" , integer);
            }

            @Override
            public void onError(Throwable throwable) {
                log.info("onError", throwable.getMessage());
            }

            @Override
            public void onComplete() {
                log.info("onComplete");
            }
        });
    }
}
