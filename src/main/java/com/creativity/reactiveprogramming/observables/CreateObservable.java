package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;

public class CreateObservable {

    public static void main(String[] args) {
        //creamos una fuente Observable
        Observable<String> source = Observable.create(observableEmitter -> {
            observableEmitter.onNext("Piña");  //metodo onNext() indica que enviara otros eventos
            observableEmitter.onNext("Manzana");
            observableEmitter.onNext("Naranja");
            observableEmitter.onNext("Papaya");
            observableEmitter.onComplete(); //onComplete() indica que no llegara mas eventos
        });
        source.forEach(s -> System.out.println("RECIBE: " + s));
        usingObservableonError();
        usingObservableWithFilterAndMap();
        usingObservableWithFilterAndMapRefactoring();
    }

    public static void usingObservableonError() {
        Observable<String> source = Observable.create(observableEmitter -> {
            try {
                observableEmitter.onNext("Peru");
                observableEmitter.onNext("Israel");
                observableEmitter.onNext("Bolivia");
                observableEmitter.onComplete();
            } catch (Throwable e) {
                observableEmitter.onError(e);
            }
        });
        //el metodo subscribe(onNext(),onError()) podemos manejar dos funciones
        source.subscribe(s -> System.out.println(s),
                Throwable::printStackTrace);
    }

    public static void usingObservableWithFilterAndMap() {
        Observable<String> source2 = Observable.create(observableEmitter -> {
            try {
                observableEmitter.onNext("camisa");
                observableEmitter.onNext("zapato");
                observableEmitter.onNext("medias");
                observableEmitter.onNext("pelo");
                observableEmitter.onComplete();
            } catch (Throwable e) {
                observableEmitter.onError(e);
            }
        });
        //el operador map() produce un nuevo Observable<Integer>
        Observable<Integer> lengths = source2.map(s -> s.length());
        //el operador filter() producen nuevos Observables que usan internamente la interface Observer
        Observable<Integer> filtered = lengths.filter(integer -> integer >= 5);
        filtered.subscribe(d -> System.out.println("RECIBE: " + d), Throwable::printStackTrace);
    }

    public static void usingObservableWithFilterAndMapRefactoring() {
        Observable<String> source3 = Observable.create(observableEmitter -> {
            try {
                observableEmitter.onNext("carne");
                observableEmitter.onNext("cerdo");
                observableEmitter.onNext("pato");
                observableEmitter.onNext("pollo");
                observableEmitter.onComplete();
            } catch (Throwable e) {
                observableEmitter.onError(e);
            }
        });
        //refactorizando Observable<Integer> de forma encadenada
        source3
                .map(s -> s.length())
                .filter(i -> i >= 5)
                .subscribe(d -> System.out.println("RECIBE 2 : " + d));
    }
}
