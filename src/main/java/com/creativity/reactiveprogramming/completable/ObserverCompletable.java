package com.creativity.reactiveprogramming.completable;

import io.reactivex.Completable;

public class ObserverCompletable {
    /*
     * Completable se refiere simplemente a la ejecución de
     * una acción, pero no recibe ninguna emisión. Lógicamente,
     * no tiene  onNext() ni onSuccess() recibe emisiones, pero sí
     * tiene onError() y onComplete()
     */
    public static void main(String[] args) {
        /*
         * Completable es algo que probablemente no usará con frecuencia.
         *  Puede construir uno rápidamente llamando Completable.complete() o
         * Completable.fromRunnable(). El primero llama inmediatamente  onComplete() sin
         * hacer nada, mientras que fromRunnable() ejecuta la acción especificada
         * antes de llamar onComplete():
         */
        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {

            }
        })
                .subscribe(() -> System.out.println("Done! "));
    }
}
