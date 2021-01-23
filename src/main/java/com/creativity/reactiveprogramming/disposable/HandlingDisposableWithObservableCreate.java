package com.creativity.reactiveprogramming.disposable;

import io.reactivex.Observable;

public class HandlingDisposableWithObservableCreate {
    /*
     * Si su Observable.create() devolución es de larga
     * duración o infinita Observable, lo ideal es que
     * verifique el método isDisposed() de forma regular
     *  para ver si debe seguir enviando emisiones ObservableEmitter. Esto
     * evita que se realice trabajo innecesario si la suscripción ya no está activa.
     */

    public static void main(String[] args) {
        Observable<Integer> source = Observable.create(observableEmitter -> {
            try {
                for (int i = 0; i < 20; i++) {
                    while (!observableEmitter.isDisposed()) {
                        observableEmitter.onNext(i);
                        break;
                    }
                    if (observableEmitter.isDisposed()) {
                        return;
                    }
                }
                observableEmitter.onComplete();
            } catch (Throwable e) {
                observableEmitter.onError(e);
            }
        });
        source.subscribe(integer -> System.out.println("PROCCESS: " + integer));
    }
}
