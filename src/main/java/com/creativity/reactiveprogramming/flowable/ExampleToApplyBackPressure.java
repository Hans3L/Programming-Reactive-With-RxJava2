package com.creativity.reactiveprogramming.flowable;

import com.creativity.reactiveprogramming.utils.MyItem;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ExampleToApplyBackPressure {
    /*
     * Cuando el procesamiento de cada artículo lleva mucho tiempo, agregar otros hilos
     * para el procesamiento simultáneo de las emisiones es la forma de abordar el problema.
     * Esto se puede hacer  agregando  operaciones concurrentes  a una cadena Observable usando
     * observeOn(), paralelización y operadores como  delay(). Con eso, el procesamiento se vuelve
     * asincrónico. Esto significa que varias partes de la cadena Observable pueden procesar
     * diferentes emisiones al mismo tiempo, y el productor puede superar al consumidor intermedio,
     * ya que ahora operan en diferentes hilos.
     *
     * En el procesamiento asincrónico, estrictamente hablando, una emisión ya no se entrega en sentido
     * descendente de una en una desde la fuente hasta el final Observer antes de comenzar la siguiente.
     * Esto es porque una vez que una emisión se empuja a una diferente Scheduler a través de observeOn()
     * (o otro operador concurrente), la fuente ya no es el encargado de guiar que la emisión a la Observer.
     * Por lo tanto, la fuente empuja a la siguiente emisión aunque la emisión anterior no haya alcanzado
     * Observer todavía.
     *
     */
    public static void main(String[] args) throws InterruptedException {
        Observable.range(1, 999_999_999)
                .map(integer -> {             //.map(MyItem::new)
                    return new MyItem(integer);
                })
                .observeOn(Schedulers.io())
                .subscribe(myItem -> {          //Procesamiento de Observer
                    RxUtils.sleeps(50);
                    System.out.println("RECIBE MyItem " + myItem.id + " en hilo " + Thread.currentThread().getName());
                });
        /**
         * Esta es solo una sección de la salida real de la consola. Tenga en cuenta que cuando
         * MyItem 946 se crea, Observer todavía se está procesando RECIBE MyItem 1. Las emisiones
         * se están impulsando mucho más rápido de lo que Observer pueden procesarlas. Tener dos
         * subprocesos de procesamiento ayuda, pero debido a que las emisiones acumuladas se ponen
         * en cola de observeOn() manera ilimitada, esto podría generar muchos otros problemas,
         * incluidas OutOfMemoryErrorexcepciones.
         *
         * Entonces, ¿cómo mitigamos esto? Puede probar y utilizar herramientas de concurrencia
         * nativas de Java, como semáforos, pero, afortunadamente, RxJava tiene una solución
         * optimizada para este problema: la clase Flowable.
         */
    }
}
