package com.creativity.reactiveprogramming.operator.booleans;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AllOperator {
    public static void main(String[] args) {
        Observable<Integer> positiveNumbers = Observable.fromIterable(RxUtils.positiveNumbers(10));
        positiveNumbers.all(integer -> integer > 0)
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        log.info("onSubscribe");
                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        log.info("¿Todos los eventos son mayores que 0? -> {}", aBoolean);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        log.info("onError() -> {} ", throwable);
                    }
                });
    }
}
