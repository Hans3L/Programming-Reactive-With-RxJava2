package com.creativity.reactiveprogramming.observables;

import com.creativity.reactiveprogramming.observer.DemoObserver;
import io.reactivex.Observable;

public class ObservableUsingRange {
    //emite una gama de elementos
    //toma 2 argumentos número inicial y rango
    public static void main(String[] args) {
        Observable.range(1,15)
                .subscribe(new DemoObserver());

    }
}
