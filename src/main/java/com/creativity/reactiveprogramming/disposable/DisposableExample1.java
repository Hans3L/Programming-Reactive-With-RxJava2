package com.creativity.reactiveprogramming.disposable;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class DisposableExample1 {
    /*
     * Disposable es un vínculo entre un Observable y un activo Observer.
     * Puede llamar a su método dispose()  para detener las emisiones y deshacerse de
     * todos los recursos utilizados para eso Observer. También tiene un método isDisposed(), que
     * indica si ya se ha eliminado
     */
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable = seconds.subscribe(aLong -> System.out.println("RECIBE : " + aLong));
        //sleep 5 seconds
        RxUtils.sleeps(5000);
        //deseche y pare emisiones
        disposable.dispose();
        // sleep 5 seconds para probar
        // no hay más emisiones
        // comprobamos
        System.out.println("Se eliminaron las emisiones ?: " + disposable.isDisposed());
        RxUtils.sleeps(5000);
    }
}
