package com.creativity.reactiveprogramming.single;

import io.reactivex.Observable;
import io.reactivex.Single;

public class ObserverSingle {

    private static int op;

    /*
     * La clase Single<T> es esencialmente una Observable<T> que
     * emite solo un elemento y, como tal, está limitada solo a
     * los operadores que tienen sentido para una sola emisión.
     */

    public static void main(String[] args) {

        op = 2;
        switch (op) {
            case 1:
                Single.just("Hello")
                        .map(s -> s.length())
                        .subscribe((integer) ->
                                        System.out.println(integer),
                                e -> System.out.println("Error Capture: " + e));
                break;
            case 2:
                /*
                 * Hay operadores  Single que lo convierten en un  Observable, como
                 * toObservable(). Y, en la dirección opuesta, con Determinados
                 * operadores Observable devuelven una Single. Por ejemplo, el
                 * first() operador devolverá un  Single porque ese operador
                 * está lógicamente relacionado con un solo elemento.
                 */
                Observable<String> frutas = Observable.just("mango", "platano", "papaya");
                frutas.first("uvas")
                        .subscribe(s -> System.out.println(s));
                break;
        }
    }

    /*
     * A diferencia de la interfaz Observer, no tiene el método onNext() y
     * tiene el  onSuccess() método en lugar de onComplete(). Esto tiene
     * sentido porque Singlepuede emitir un valor como máximo. onSuccess()
     * esencialmente se consolida onNext()y onComplete()en un solo evento.
     */

}
