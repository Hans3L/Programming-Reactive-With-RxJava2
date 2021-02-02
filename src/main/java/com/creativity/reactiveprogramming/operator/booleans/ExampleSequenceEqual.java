package com.creativity.reactiveprogramming.operator.booleans;

import io.reactivex.Observable;

public class ExampleSequenceEqual {
    /**
     * El operador sequenceEqual() comprueba si dos observables emiten los mismos
     * valores en el mismo orden. Devuelve un Single<Boolean> con true si las
     * secuencias emitidas son las mismas por parejas.
     */
    public static void main(String[] args) {
        Observable<String> obs1 = Observable.just("One", "Two", "Three");
        Observable<String> obs2 = Observable.just("One", "Two", "Three");
        Observable<String> obs3 = Observable.just("Two", "One", "Three");
        Observable<String> obs4 = Observable.just("One", "Two");

        Observable.sequenceEqual(obs1, obs2)
                .subscribe(aBoolean -> System.out.println("RECIBIDO: " + aBoolean));

        Observable.sequenceEqual(obs1, obs3)
                .subscribe(aBoolean -> System.out.println("RECIBIDO: " + aBoolean));

        Observable.sequenceEqual(obs1, obs4)
                .subscribe(aBoolean -> System.out.println("RECIBIDO: " + aBoolean));
    }
    /**
     * Como puede ver, la salida confirma que la secuencia de los valores emitidos por
     * los observables obs1 y obs2 son iguales en tamaño, valores y su orden. Los
     * observables obs1 y obs3 emiten secuencias de los mismos valores pero en diferente
     * orden, mientras que los observables obs1 y obs4 tienen diferentes tamaños.
     */
}
