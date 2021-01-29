package com.creativity.reactiveprogramming.operator.transforming;

import com.creativity.reactiveprogramming.observer.DemoObserver;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ScanOperator {

    private static int op;

    /*
     * Scan: aplicar una función a cada elemento emitido por un Observable,
     * secuencialmente, y emitir cada valor sucesivo
     */
    public static void main(String[] args) {

        op = 2;

        switch (op) {
            case 1:
                log.info("Scan Operator");
                List<Integer> numbers = RxUtils.positiveNumbers(10);
                Observable.fromIterable(numbers)
                        .scan(0, (accumulator, i) -> {
                            return accumulator + i;
                        }).subscribe(new DemoObserver());
                break;
            case 2:
                //AlgorithmFibonacci(10);
                break;
        }
    }

    /*public static void AlgorithmFibonacci(int count) {
        //[0, 1, 1, 2, 3, 5, 8, 13, 21]
        log.info("Secuencia de Fibonacci");
        Observable.range(1, count)
                .scan(1, (accumulator, integer2) -> {
                    integer2 = accumulator + integer2;
                    return integer2-1;
                }).subscribe(new DemoObserver());
    }*/
}