package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class IntervalObservable {

    static private int op;

    public static void main(String[] args) {

        op = 3;

        switch (op) {
            case 1:
                Observable.interval(1, TimeUnit.SECONDS)
                        .subscribe(aLong -> System.out.println(LocalDateTime.now().getSecond() + " " + aLong + " Lima"));
                sleep(5000);
            case 2:
                whoisTypeObservableIntervalColdorHot();
            case 3:
                convertObservableColdToHot();
        }
    }

    //Este metodo permite ver si el metodo interval es frio o caliente con respecto a las
    //emisiones. Si bien impulsa por eventos(infinito) puede decirse que esta en los
    //observables calientes, pero no es asi.
    public static void whoisTypeObservableIntervalColdorHot() {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        System.out.println("Al comenzar el Observer 2 en 0 indica que cada una obtiene sus propias emisiones indicando el Observable es frio ");
        seconds.subscribe(aLong -> System.out.println("Observer 1: " + aLong));
        sleep(3000);
        seconds.subscribe(aLong -> System.out.println("Observer 2: " + aLong));
        sleep(3000);
    }

    //Este metodo es una conversion de frio a caliente

    public static void convertObservableColdToHot() {
        ConnectableObservable<Long> seconds =  Observable.interval(1, TimeUnit.SECONDS).publish();
        System.out.println("El Observer 2 despues de 5 seg pierde los valores anteriores y luego se sincroniza");
        seconds.subscribe(aLong -> System.out.println("Observer 1: " + aLong));
        seconds.connect();
        sleep(5000);
        seconds.subscribe(aLong -> System.out.println("Observer 2: " + aLong));
        seconds.connect();
        sleep(5000);
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
