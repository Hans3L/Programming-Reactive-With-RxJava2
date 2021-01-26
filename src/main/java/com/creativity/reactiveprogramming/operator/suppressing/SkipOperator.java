package com.creativity.reactiveprogramming.operator.suppressing;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SkipOperator {
    private static int op;

    public static void main(String[] args) {

        op = 2;

        switch (op) {
            case 1:
                log.info("Skip Operator");
                Observable.fromIterable(RxUtils.positiveNumbers(10))
                        .skip(5)
                        .subscribe(i -> System.out.println("RECIBE: " + i));
                break;
            case 2:
                /*
                 * Y hay un skipLast()operador, que omite el último número especificado
                 * de elementos (o tiempo de duración) antes de onCompleteque se genere
                 *  el evento. Solo tenga en cuenta que el skipLast()operador pone en
                 * cola y retrasa las emisiones hasta que identifica el último número
                 * especificado de emisiones en ese alcance.
                 */
                log.info("SkipLast Operator");
                Observable.fromIterable(RxUtils.positiveNumbers(10))
                        .skipLast(5)
                        .subscribe(i -> System.out.println("RECIBE: " + i));
                break;
        }
    }
}
