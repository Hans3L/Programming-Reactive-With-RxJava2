package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class ExampleConnectableObservable {
    public static void main(String[] args) {
        /*La clase ConnectableObservable<> permite cambiar de estado las emisiones
         * de frio a caliente para que todos los observadores se transmitan tod0 a
         * la vez. Para hacer la conversion necesita llamar a publish() a un observable
         * y producira un ConnectableObservable objeto.
         * Nota: la subscripcion no inicia la emision sin antes llamar a connect() en el
         * objeto ConnectableObservable<>
         */
        ConnectableObservable<String> source = Observable.just("Alfa", "Beta", "Omega").publish();
        System.out.println("En lugar de Observer 1 procesar todas las emisiones antes Observer 2, cada emisión se envía a todos los observadores simultáneamente");
        System.out.println("Esto tambien se conoce como Multidifucion");
        //set up observer 1
        source.subscribe(s -> System.out.println("Observer 1: " + s));
        //set up observer 2
        source
                .map(s -> s.length())
                .subscribe(s -> System.out.println("Observer 2: " + s));
        //Fire
        source.connect();
        // no se mostrara por que perdio las emisiones disparadas antes
        // por que esta despues del metodo connect()
        source.subscribe(s -> System.out.println("Observer 3: " + s));

    }
}
