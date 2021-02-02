package com.creativity.reactiveprogramming.operator.booleans;

import com.creativity.reactiveprogramming.single.DemoSingleObserver;
import io.reactivex.Observable;

import java.util.Objects;

public class ExampleOperatorAll {
    /**
     * El operador all() verifica que todas las emisiones cumplan con el criterio
     * especificado y devuelve un Single<Boolean> objeto. Si todos pasan, devuelve
     * el Single<Boolean> objeto que contiene true. Si encuentra un valor que
     * falla el criterio, inmediatamente llama onComplete() y devuelve el objeto
     * que contiene false.
     */

    private static int op;

    public static void main(String[] args) {

        op = 2;

        switch (op) {
            case 1:
                /**
                 * Si invoca all() un Observable vacío, se emitirá true debido al principio de la
                 * verdad vacía
                 */
                Observable.just(Observable.empty())
                        .all(Objects::isNull)
                        .subscribe(new DemoSingleObserver());
                break;
            case 2:
                System.out.println("Cuando el operador all() encontró 100, inmediatamente emitió false y llamó onComplete() ");
                Observable.just(1, 5, 8, 100, 9, 10)
                        .all(i -> i < 10)
                        .subscribe(new DemoSingleObserver());
        }

    }

}