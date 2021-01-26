package com.creativity.reactiveprogramming.operator.suppressing;

import com.creativity.reactiveprogramming.observer.DemoObserver;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DistinctOperator {
    /*
     * Remueve los duplicados elementos desde el Observable
     */
    public static void main(String[] args) {
        log.info("Operador Distinto");
        Observable.just(1, 2, 3, 2, 4, 1, 2, 3, 4, 5)
                .distinct()
                .subscribe(new DemoObserver());
    }
}
