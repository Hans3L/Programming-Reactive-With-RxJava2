package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Example1 {
    public static void main(String[] args) {
        /*
         * La clase Observable es un iterador componible basado en push.
         * Por un hecho Observable<T>, empuja elementos (llamados emisiones)
         * de tipo a <T> través de una serie de operadores hasta que finalmente
         * llegan a un final Observer, que los consume.
         */

        Observable<String> miCadena = Observable.just("Alfa", "Beta", "Gamma");
        //Para que el observable emita o haga push se necesita un objeto observer
        // para subscribirse y recibir los elementos
        miCadena.subscribe(s -> System.out.println(s));
        //cada operacion debe ser diferente por operacion
        //contando la longitud
        miCadena.map(s -> s.length())
                .subscribe(s -> System.out.println(s));

        List<String> cadena = new ArrayList<>();
        cadena.add("Alfa");
        cadena.add("Beta");
        cadena.add("Gamma");
        cadena.stream().forEach(x -> System.out.println(x));

        //La diferencia clave es que Observable empuja(pushea) los elementos,
        // mientras que los stream extraen(pullean) los elementos.
        // Esto puede parecer sutil, pero el impacto de una iteración basada
        // en push es mucho más poderoso que uno basado en pull. Como vimos
        // anteriormente, puede enviar no solo datos sino también eventos.
        System.out.println("=================================================================");

        Observable<Long> secondIntervals = Observable.interval(1, TimeUnit.SECONDS); //separacion por cada 1 seg
        System.out.println("¡Esta emisión(Long) no es solo un dato sino también un evento!");
        secondIntervals.subscribe(s -> System.out.println(s));
        /*Mantenga el hilo principal durante 5 segundos
           por lo que Observable arriba tiene la posibilidad de disparar*/
        sleep(5000);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
