package com.creativity.reactiveprogramming.currency.parallelization;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class KeepingApplicationAlive {
    private static int op;

    public static void main(String[] args) throws InterruptedException {
        op = 2;
        switch (op) {
            case 1:
                /**
                 * Una forma de mantener una aplicación activa indefinidamente
                 * es simplemente pasar Long.MAX_VALUE al método Thread.sleep()
                 * Hay formas de mantener una aplicación activa solo el tiempo suficiente
                 * para que finalice una suscripción. Pero una forma más fácil es usar
                 * operadores de bloqueo en RxJava.
                 */
                Observable.interval(1, TimeUnit.SECONDS)
                        .map(aLong -> UnderstandingConcurrency.intenseCalculation(aLong))
                        .subscribe(System.out::println);
                RxUtils.sleeps(Long.MAX_VALUE);
                break;
            case 2:
                /**
                 * Los operadores de bloqueo detienen el subproceso de declaración
                 * y esperan las emisiones. Por lo general, los operadores de bloqueo
                 * se utilizan para las pruebas unitarias y pueden atraer antipatrones
                 * si se utilizan incorrectamente en producción. Sin embargo, mantener
                 * viva una aplicación en función del ciclo de vida de una Observable
                 * suscripción finita es un caso válido para utilizar un operador de
                 * bloqueo. Como se muestra aquí, blockingSubscribe()se puede usar en
                 * lugar de subscribe() para detener y esperar a onComplete() que se llame
                 * antes de que se permita que el hilo principal continúe y salga de la aplicación.
                 */
                Observable.just("Marte", "Tierra", "Jupiter", "Pluton", "Venus")
                        .subscribeOn(Schedulers.computation())
                        .map(s -> UnderstandingConcurrency.intenseCalculation(s))
                        .blockingSubscribe(System.out::println,
                                Throwable::printStackTrace,
                                () -> System.out.println("HECHO!"));
                break;
        }

    }
}
