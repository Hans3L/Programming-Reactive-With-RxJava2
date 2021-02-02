package com.creativity.reactiveprogramming.operator.booleans;

import com.creativity.reactiveprogramming.single.DemoSingleObserver;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContainsOperator {
    public static void main(String[] args) {
        //el resultado es false por que la lista generada no tiene 20
        log.info("Contains Operator");
        Observable<Integer> positiveNumber = Observable.fromIterable(RxUtils.positiveNumbers(10));
        positiveNumber
                .contains(20)
                .subscribe(new DemoSingleObserver());
    }
}
