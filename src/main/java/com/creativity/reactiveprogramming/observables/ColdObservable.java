package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;

public class ColdObservable {

    public static void main(String[] args) {
        /*
         * El Observable primero empuja(push) todas las emisiones al primero Observer
         * y luego llama  onComplete(). Luego, empuja todas las emisiones nuevamente al segundo
         * Observer y llama  onComplete(). Ambos observadores reciben los mismos conjuntos de
         * datos al obtener dos flujos separados cada uno, que es el comportamiento típico de
         * un Observable frio:
         */
        Observable<Object> value = Observable.just("letra",1,-9.0,'A',true,5L);
        //primer observer
        value.subscribe(o -> System.out.println("Observer 1: " + o));
        //segundo observer
        value.subscribe(o -> System.out.println("Observer 2: " +o));

        filterAndMapColdObservable();
    }

    public static void filterAndMapColdObservable(){
        //en el caso de operadores como map y filter vemos que operan independientemente
        Observable<String> value = Observable.just("salud","amor","paz","dinero");
        value.subscribe(s -> System.out.println("Observador Example 1: " + s));
        value
                .map(String::length)         //s -> s.length()
                .filter(integer -> integer >= 5)
                .subscribe(s -> System.out.println("Observador Example 2: " + s));
    }
}
