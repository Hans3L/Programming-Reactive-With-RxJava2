package com.creativity.reactiveprogramming.currency.parallelization;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SchedulersNewThread {
    public static void main(String[] args) {
        /**
         * La factory Schedulers.newThread() devuelve un Scheduler que no agrupa subprocesos
         * en absoluto. Crea un nuevo hilo para cada uno Observer y luego destruye el hilo
         * cuando ya no es necesario. Esto es diferente a Schedulers.io() porque no intenta
         * persistir y almacenar en caché los subprocesos para su reutilización.
         */
        Observable.just("Hansel", "Israel", "Kaleb", "Salomon", "Jonas")
                .subscribeOn(Schedulers.newThread());
    }
    /**
     * Esto puede resultar útil en los casos en los que desee crear, utilizar y luego destruir
     * un hilo inmediatamente para que no ocupe memoria. Pero para aplicaciones complejas
     * en general, querrá usarlo Schedulers.io()para que haya algún intento de reutilizar
     * subprocesos si es posible. También debe tener cuidado, ya que Schedulers.newThread()
     * puede funcionar sin control en una aplicación compleja (como puede Schedulers.io())
     * y crear un gran volumen de subprocesos, lo que podría bloquear la aplicación.
     */
}
