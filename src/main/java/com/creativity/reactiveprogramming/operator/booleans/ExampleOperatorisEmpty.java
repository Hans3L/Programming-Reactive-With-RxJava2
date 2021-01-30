package com.creativity.reactiveprogramming.operator.booleans;

import com.creativity.reactiveprogramming.single.DemoSingleObserver;
import io.reactivex.Observable;

public class ExampleOperatorisEmpty {
    /**
     * El operador isEmpty() comprueba si Observable va a emitir más elementos.
     * Devuelve un Single<Boolean> con true si Observable ya no emite elementos.
     */
    private static int op;

    public static void main(String[] args) {

        /**
         * En el siguiente fragmento de código, un Observable emite cadenas
         * y ninguno contiene la letra z. Sin embargo, el siguiente filtro
         * solo permite un flujo descendente de aquellos elementos que contienen
         * la letra z. Esto significa que, después del filtro, el Observable no
         * emite ningún elemento (queda vacío), pero si la letra z se encuentra
         * en alguna de las cadenas emitidas, el resultado recibido cambia a false
         */

        op = 2;

        switch (op) {
            case 1:
                Observable.just("One", "Two", "Three")
                        .filter(s -> s.contains("z"))
                        .isEmpty()
                        .subscribe(new DemoSingleObserver());
                break;
            case 2:
                Observable.just("One","Twoz","Three")
                        .filter(s -> s.contains("z"))
                        .isEmpty()
                        .subscribe(new DemoSingleObserver());

                break;
        }
    }
}
