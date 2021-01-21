package com.creativity.reactiveprogramming.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoObserver implements Observer {

    /*
     * Funcionamiento del Observable
     * Un Observable pasa secuencialmente elementos por la cadena a un Observer.
     * onNext(): Esto pasa cada elemento uno a la vez desde la fuente Observable hasta el Observer.
     * onComplete(): Esto comunica un evento de finalización hasta el Observer, lo que
     * indica que no se producirán más llamadas onNext().
     * onError(): Esto comunica un error en la cadena al Observer, que normalmente define
     * cómo manejarlo. A menos que se utilice un operador retry() para interceptar el error,
     *  la cadena Observable normalmente termina y no se producen más emisiones.
     */

    @Override
    public void onSubscribe(Disposable disposable) {
      log.info("onSubscribe");
    }

    @Override
    public void onNext(Object o) {
        log.info("onNext -> {}", o);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("onError {}",  throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("onComplete");
    }
}
