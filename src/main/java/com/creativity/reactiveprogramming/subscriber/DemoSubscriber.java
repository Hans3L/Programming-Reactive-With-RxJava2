package com.creativity.reactiveprogramming.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class DemoSubscriber implements Subscriber {
    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("onSubscribe");
    }

    @Override
    public void onNext(Object o) {
        log.info("onNext -> {}", o);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("onError -> {}", throwable);
    }

    @Override
    public void onComplete() {
        log.info("Completado!");
    }
}
