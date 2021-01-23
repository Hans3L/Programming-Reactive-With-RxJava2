package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;

public class DeferObservable {

    private static int op;
    private static int start = 1;
    private static int count = 3;

    public static void main(String[] args) {

        /*
         * Observable.defer() es un operador para crear un estado separado para cada uno
         * observer.
         */

        op=2;

        switch (op){
            case 1:
                Observable<Integer> source = Observable.range(start,count);
                source.subscribe(integer -> System.out.println("Observer 1: " + integer));
                count= 5;
                source.subscribe(integer -> System.out.println("Observer 2: " + integer));
                break;
            case 2:
                Observable<Integer> source2 = Observable.defer(() -> Observable.range(start, count));
                source2.subscribe(integer -> System.out.println("Observer 1: " + integer));
                count= 5;
                source2.subscribe(integer -> System.out.println("Observer 2: " + integer));

                /*
                 *  Cuando su Observable fuente no esté capturando cambios en las cosas que
                 * la impulsan, intente colocarla Observable.defer(). Si su Observable fuente
                 * se implementó de manera ingenua y se comporta de manera incorrecta con
                 * más de una Observer(por ejemplo, reutiliza una Iterator que solo itera
                 * los datos una vez), también Observable.defer()proporciona una solución
                 * rápida para esto.
                 */
        }
    }
}
