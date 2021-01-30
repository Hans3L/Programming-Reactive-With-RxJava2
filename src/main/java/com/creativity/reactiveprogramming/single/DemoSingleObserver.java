package com.creativity.reactiveprogramming.single;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoSingleObserver implements SingleObserver {
    @Override
    public void onSubscribe(Disposable disposable) {
        log.info("onSubscribe");
    }

    @Override
    public void onSuccess(Object o) {
        log.info("onSuccess -> {} ", o);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("onError -> {}", throwable);
    }
}
