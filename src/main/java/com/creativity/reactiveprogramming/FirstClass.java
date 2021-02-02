package com.creativity.reactiveprogramming;

import io.reactivex.Observable;

public class FirstClass {
    /*
     * Los Observables son los que notifican
     * los objetos que son notificados son los Observer
     */

    static String result = "";

    public static void main(String[] args) {
        //declarativo
        Observable<String> observable = Observable.just("Hello");
        observable.subscribe((x) -> result = x);
        System.out.println(result);

        //metodo referencia
        Observable<String> observable1 = Observable.just("Hello Peru");
        //System.out.println(observable1.subscribe(System.out::println)); //DISPOSED
        observable1.subscribe(System.out::println);   //cuando queremos la inforrmación de un observable llamamos a subscribe

        //imperativo
        //Observable<String> observable2 = Observable.just("Hello Peru");
        //observable2.subscribe().dispose();
    }

}
