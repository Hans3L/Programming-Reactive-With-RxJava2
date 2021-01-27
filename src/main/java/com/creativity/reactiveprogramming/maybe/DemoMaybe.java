package com.creativity.reactiveprogramming.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoMaybe implements MaybeObserver {
    @Override
    public void onSubscribe(Disposable disposable) {
        log.info("onSubscribe");
    }

    @Override
    public void onSuccess(Object o) {
        log.info("onSuccess -> {}", o);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("onError -> {} ", throwable);
    }

    @Override
    public void onComplete() {
        log.info("Completado!");
    }
}
