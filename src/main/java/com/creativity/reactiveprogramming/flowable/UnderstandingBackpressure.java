package com.creativity.reactiveprogramming.flowable;

import com.creativity.reactiveprogramming.utils.MyItem;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;

public class UnderstandingBackpressure {
    /**
     * Empujar elementos de forma sincrónica y uno a la vez desde la fuente hasta el
     * Observer final es de hecho cómo funciona una cadena Observable de operadores de
     * forma predeterminada sin ninguna concurrencia.
     */
    public static void main(String[] args) {
        Observable.range(1, 999_999_999)
                .map(MyItem::new)
                .subscribe(myItem -> {
                    RxUtils.sleeps(50);
                    System.out.println("RECIBIDO myItem " + myItem.id + " en hilo " + Thread.currentThread().getName());
                });
        /**
         * La alternancia de salida entre Construyendo MyItem y Recibido MyItem muestra
         * que cada emisión se procesa una a la vez desde la fuente hasta la terminal
         * Observer. Esto se debe a que un subproceso hace tod0 el trabajo de toda esta
         * operación, lo que hace que tod0 sea sincrónico. Los consumidores y productores
         * están transmitiendo emisiones en un flujo constante y serializado.
         *
         * Es posible que haya notado que  ralentizamos el procesamiento de cada emisión en
         * 50 milisegundos en el  Observer. Podríamos establecer este valor en 500 o 500.000
         * milisegundos. La salida progresaría más lentamente, pero la secuencia no cambia:
         * los mensajes Constructing MyItem y  Received MyItem siempre se alternan. Esto se
         * debe a que tod0 el procesamiento de todos los operadores se realiza en el mismo hilo.
         *
         * Esto muestra que, con el procesamiento de un solo hilo, incluso si el flujo descendente
         * procesa lentamente cada emisión, el flujo ascendente sigue el ritmo de forma síncrona.
         */
    }
}
