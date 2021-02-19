package com.creativity.reactiveprogramming.currency.parallelization;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SchedulersSingle {
    /**
     * Cuando desee ejecutar tareas secuencialmente en un solo hilo, puede usar
     * Schedulers.single() para crear un Scheduler. Esto está respaldado por una
     * implementación de un solo subproceso apropiado para el bucle de eventos.
     * También puede resultar útil aislar las operaciones frágiles que no son
     * seguras para subprocesos en un solo subproceso.
     */
    public static void main(String[] args) {
        Observable.just("Saturno", "Tierra", "Sol", "Luna")
                .subscribeOn(Schedulers.single());
    }
}
