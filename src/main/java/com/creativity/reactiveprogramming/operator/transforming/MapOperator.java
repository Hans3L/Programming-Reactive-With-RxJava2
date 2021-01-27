package com.creativity.reactiveprogramming.operator.transforming;

import com.creativity.reactiveprogramming.observer.DemoObserver;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;

public class MapOperator {
    /*
     *  Map: transformar los elementos emitidos por un Observable aplicando una función a cada elemento
     */
    public static void main(String[] args) {
        Observable.fromIterable(RxUtils.positiveNumbers(10))
                .map(integer -> {
                    return integer * 2;
                })
                .subscribe(new DemoObserver());
    }
}
