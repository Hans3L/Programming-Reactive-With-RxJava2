package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubscribingObserver {

    public static void main(String[] args) {
        Observable<String> source = Observable.just("10001","100002","10000003");
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Integer integer) {
                log.info("RECIBE 5: " + integer);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                log.info("Done!");
            }
        };
        source.map(s -> s.length())
                .filter(integer -> integer > 3)
                .subscribe(observer);
    }
}
