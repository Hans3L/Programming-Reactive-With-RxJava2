package com.creativity.reactiveprogramming.observables;

import com.creativity.reactiveprogramming.observer.DemoObserver;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ObservableUsingInterval {
    //emite una secuencia de números enteros a intervalos regulares
    //ideal para trabajos repetitivos
    //El operador de intervalo devuelve un Observable que emite una secuencia
    // infinita de números enteros ascendentes, con un intervalo de tiempo
    // constante de su elección entre las emisiones.
    public static void main(String[] args) throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new DemoObserver());
        RxUtils.sleeps(3000);
    }
}
