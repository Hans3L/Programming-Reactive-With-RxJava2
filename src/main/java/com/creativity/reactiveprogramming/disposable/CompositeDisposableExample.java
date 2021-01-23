package com.creativity.reactiveprogramming.disposable;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class CompositeDisposableExample {

    /*
     * Si tiene varias suscripciones que deben administrarse y
     * eliminarse, su uso puede ser útil  CompositeDisposable.
     * Esto implementa Disposable, pero contiene internamente una
     * colección de Disposable objetos, que puede agregar y luego
     * eliminar todos a la vez
     */

    private static final CompositeDisposable disposables = new CompositeDisposable();

    public static void main(String[] args) throws InterruptedException {
        Observable<Long> longObservable = Observable.interval(1, TimeUnit.SECONDS);
        //subscribe y captura disposable
        Disposable disposable1 = longObservable.subscribe(aLong -> System.out.println("Observer 1 :" + aLong));
        Disposable disposable2 = longObservable.subscribe(aLong -> System.out.println("Observer 2 :" + aLong));

        // poner ambos desechables en CompositeDisposable
        disposables.addAll(disposable1,disposable2);
        //sleep 5 seconds
        RxUtils.sleeps(5000);
        //desechar todos los disposables
        disposables.dispose();
        //dormir por 5 seg y probar
        //no hay mas emisiones
        RxUtils.sleeps(5000);
    }
}
