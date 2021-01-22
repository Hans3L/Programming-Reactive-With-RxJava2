package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;

public class EmptyObservable {

    public static void main(String[] args) {
        //Aunque esto puede no parecer útil todavía, a veces
        // es útil crear un Observableque no emita nada y llame onComplete()
        Observable<String> empty = Observable.empty();
        System.out.println(" También pueden resultar de operadores como filter() cuando ninguno de los valores emitidos pasa el criterio");
        empty.subscribe(s -> System.out.println(s),
                Throwable::printStackTrace,
                () -> System.out.println("Se completo un operador empty()"));

        /*
         * Un vacío Observables esencialmente el concepto de RxJava de null.
         * Representa una ausencia de valor o, técnicamente, valores. Sin embargo,
         * es más elegante que null porque las operaciones no arrojan NullPointerExceptions.
         * Se emite el onComplete evento  y se detiene el procesamiento. Luego, debe
         * rastrear la cadena de operadores para encontrar cuál causó que el flujo
         * de emisiones se vacíe.
         */
    }
}
