package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;

public class NeverObservable {
    /*
     * Un primo cercano de  Observable.empty() es Observable.never()
     * La única diferencia entre ellos es que el never() método
     * no genera el  onComplete evento, dejando al observador
     * esperando una emisión para siempre
     */
    public static void main(String[] args) {
        Observable<String> empty = Observable.never();
        empty.subscribe(s -> System.out.println(s),
                Throwable::printStackTrace,
                () -> System.out.println("Done!"));
        sleep(3000);
    }
    /*
     * Esto Observablese usa principalmente para pruebas y no tan a
     * menudo en producción. Tenemos que usar  sleep()aquí como
     * Observable.interval()porque el hilo principal no va a esperar después de iniciarlo.
     */

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
