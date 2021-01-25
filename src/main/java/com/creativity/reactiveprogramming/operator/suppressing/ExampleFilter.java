package com.creativity.reactiveprogramming.operator.suppressing;

import io.reactivex.Observable;

public class ExampleFilter {
    /*
     * El operador filter() acepta Predicate<T> por un hecho Observable<T>.
     * Esto significa que le proporciona una lambda que califica cada emisión
     * asignándola a un valor Boolean, y las emisiones con false no irán aguas abajo.
     */
    public static void main(String[] args) {
        Observable.just("ALABAMA", "texas", "FLORIDA")
                .filter(s -> s.length() != 5)
                .subscribe(s -> System.out.println("RECIBIDO: " + s));
    }
    /*
     * Tenga en cuenta que si todas las emisiones no cumplen con sus criterios,
     * la devolución Observable estará vacía, sin que se produzcan emisiones
     * antes de que onComplete()se llame.
     */
}
