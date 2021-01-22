package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;

public class RangeObservable {
    /*
     * El Observable.range() crea un observable que emite un rango consecutivo
     * de enteros. Emite cada numero desde un valor inicial e incrementa cada valor
     * subsiguiente en uno hasta alcanzar el recuento, todos pasan por los eventos onNext() y
     * terminan en onComplete()
     */

    public static void main(String[] args) {
        Observable<Integer> consecutivos = Observable.range(1, 20);
        consecutivos.subscribe(integer -> System.out.println("RECIBIDO: " + integer));
        recuentoIncialAlConteo();
    }

    public static void recuentoIncialAlConteo() {
        //Nota tenga en cuenta que el rango tiene un inicio
        // y tiene un conteo no un hasta
        Observable
                .range(5, 3)
                .subscribe(integer -> System.out.println("Conteo: " + integer));
        //Tenga en cuenta que también hay un equivalente largo llamado Observable.rangeLong()
        // si necesita emitir números más grandes.
    }
}
