package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;

public class RefactoringTheSubscribingObserver {
    static Observable<String> source = Observable.just("Saturno", "Marte", "Jupiter", "Tierra", "Sol");

    public static void main(String[] args) {

        //en este ejemplo tenemos dos Observables <Boolean> y Observable<String>
        source
                .map(s -> s.length() > 5)
                .map(aBoolean -> aBoolean.toString())
                .subscribe(s -> System.out.println("RESULTADO: " + s),
                        Throwable::printStackTrace,
                        () -> System.out.println("Hecho!"))
        ;
        upperCaseWithSubscribe();
        upperCaseWithMapAndSubscribe();
    }

    public static void upperCaseWithSubscribe() {
        source
                .subscribe(s -> {
                    if (s.length() > 5)
                        System.out.println("RESULTADO 2: " + s.toUpperCase());
                }, Throwable::printStackTrace, () -> System.out.println("Done!"));
    }

    public static void upperCaseWithMapAndSubscribe() {
        source
                .map(s -> {
                    String d = new String();
                    if (s.length() > 5)
                        d = s;
                    return d;
                })
                .subscribe(d ->
                                System.out.println("RESULTADO 3: " + d.toLowerCase())
                        , Throwable::printStackTrace, () -> System.out.println("Complete!"));
    }
}
