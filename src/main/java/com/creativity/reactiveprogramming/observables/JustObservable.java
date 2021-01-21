package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;

import java.util.List;

public class JustObservable {
    public static void main(String[] args) {
        //fuente Observable con el operador just()
        Observable<String> source = Observable.just("San Juan de Lurigancho", "La Victoria", "Callao", "Miraflores");
        source
                .map(s -> s.length())
                .filter(i -> i > 10)
                .subscribe(integer -> System.out.println("RECIBE: " + integer));
        listWithObservable();
    }

    //tambien podemos usar Observable.fromIterable() para emitir los elementos de cualquier
    //tipo Iterable como List

    public static void listWithObservable() {
        List<String> items = List.of("Futbol", "Natacion", "Beisbol");
        //Observable.fromIterable llama a onNext() y onComplete() internamente.
        Observable<String> source = Observable.fromIterable(items);
        source
                .map(s -> s.length())
                .filter(integer -> integer > 5)
                .subscribe(integer -> System.out.println("RECIBIDO 3: " + integer));
    }
}
