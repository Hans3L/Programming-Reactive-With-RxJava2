package com.creativity.reactiveprogramming.flowable;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class CreatingFlowable {
    private static int op;

    /**
     * Esto Observable.create() emite los números enteros desde 0 hasta 1000 y luego llama
     * onComplete(). Se puede detener de forma abrupta si dispose() se llama al Disposable
     * devuelto subscribe(). El bucle for comprueba esto.
     * <p>
     * Sin embargo, piense por un momento cómo se puede contrapresionar algo como esto si
     * ejecutamos  Flowable.create(), el Flowable equivalente de Observable.create().
     * Utilizando un bucle for simple como el anterior, no existe la noción de que las
     * emisiones se detengan y reanuden en función de las solicitudes de un flujo descendente
     * Subscriber. Apoyar adecuadamente la contrapresión añade algo de complejidad.
     * <p>
     * Sin embargo, existen formas más sencillas de soportar la contrapresión. A menudo
     * implican estrategias comprometedoras como el almacenamiento en búfer y la eliminación,
     * que cubriremos primero. También hay algunas utilidades para implementar la contrapresión
     * en la fuente, que cubriremos más adelante.
     */
    public static void main(String[] args) throws InterruptedException {

        op = 2;

        switch (op) {
            case 1:
                Observable<Integer> source = Observable.create(emmiter -> {
                    for (int i = 0; i <= 1000; ++i) {
                        if (emmiter.isDisposed()) {  //.isDispose() verifica si sigue enviando emisiones
                            return;
                        }
                        emmiter.onNext(i);
                    }
                    emmiter.onComplete();
                });
                source.observeOn(Schedulers.io())
                        .subscribe(System.out::println);
                RxUtils.sleeps(10000); //10 segundos
                break;
            case 2:
                /**
                 * Aprovechar Flowable.create() para crear un se Flowable siente muy parecido
                 * Observable.create(), pero hay una diferencia fundamental: debe especificar un
                 * BackpressureStrategy como segundo argumento. Indica cuál de los algoritmos de
                 * soporte de contrapresión enlatados utilizar. La opción es almacenar en caché o
                 * eliminar las emisiones o no implementar la contrapresión en absoluto.
                 *
                 * Aquí, usamos Flowable.create() para crear un Flowable. Como estrategia de
                 * soporte de contrapresión, proporcionamos un segundo BackpressureStrategy.BUFFER
                 * argumento para amortiguar las emisiones antes de que se contrapresionen, como se
                 * muestra en el siguiente ejemplo de código:
                 */
                Flowable<Integer> source2 = Flowable.create(flowableEmitter -> {
                    for (int i = 0; i <= 1000; i++) {
                        if (flowableEmitter.isCancelled()) { //isCancelled() verifica si sigue enviando emisiones
                            return;
                        }
                        flowableEmitter.onNext(i);
                    }
                    flowableEmitter.onComplete();
                }, BackpressureStrategy.BUFFER);
                source2.observeOn(Schedulers.io())
                        .subscribe(System.out::println);
                RxUtils.sleeps(10000);
                break;
            /**
             * Esto no es óptimo porque las emisiones se mantendrán en una cola ilimitada, y es posible
             * que cuando Flowable.create() presione demasiadas emisiones, obtenga un OutOfMemoryError.
             *
             * Pero al menos previene MissingBackpressureExceptiony puede hacer que su costumbre sea
             * Flowable viable hasta cierto punto. Aprenderemos sobre una forma más sólida de
             * implementar la contrapresión más adelante en este capítulo en la sección Uso de
             * Flowable.generate ()  .
             */
        }
    }
}
