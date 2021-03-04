package com.creativity.reactiveprogramming.flowable;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ConvertObservableToFlowable {

    private static int op;

    public static void main(String[] args) throws InterruptedException {

        op = 2;

        switch (op) {
            case 1:
                /**
                 * Hay otra forma de implementar BackpressureStrategy contra una fuente que no
                 * tiene noción de contrapresión. Puede convertir un Observable en Flowable
                 * llamando a su operador toFlowable(), que acepta a BackpressureStrategy como
                 * argumento. En el siguiente código, pasamos Observable.range() a Flowable usar
                 * BackpressureStrategy.BUFFER. Un  Observable no tiene noción de contrapresión,
                 * por lo que empujará los elementos lo más rápido que pueda, independientemente
                 * de si la corriente descendente puede mantener el ritmo. Pero toFlowable(), con
                 * una estrategia de amortiguación, actuará como un proxy para acumular las emisiones
                 * cuando el flujo descendente no pueda mantener el ritmo
                 */
                Observable<Integer> source = Observable.range(1, 1000);
                source.toFlowable(BackpressureStrategy.BUFFER)
                        .observeOn(Schedulers.io())
                        .subscribe(System.out::println);
                RxUtils.sleeps(10000);
                break;
            case 2:
                /**
                 * Flowable también tiene un operador toObservable(), que convertirá a Flowable<T>
                 * en un Observable<T>. Esto puede ser útil para hacer un Flowableutilizable en
                 * una cadena Observable, especialmente con operadores como  flatMap(), como se
                 * muestra en el siguiente código
                 */
                Flowable<Integer> source2 = Flowable.range(1, 1000)
                        .subscribeOn(Schedulers.computation());
                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                        .flatMap(s -> source2.map(integer -> integer + " - " + s)
                                .toObservable())
                        .subscribe(System.out::println);
                RxUtils.sleeps(10000);
                break;
            /**
             * Si Observable<String> tuviera muchas más de cinco emisiones (por ejemplo, 1,000 o 10,000 emisiones),
             * entonces probablemente sería mejor convertirlo en un Flowable en lugar de convertir el flat-mapped
             * Flowable en un Observable.
             *
             * Incluso si llama toObservable(), el Flowable todavía aprovecha la contrapresión, pero en el
             * punto en que se convierte en un Observable, la corriente descendente ya no tendrá contrapresión
             * y solicitará una Long.MAX_VALUE serie de emisiones. Esto puede estar bien siempre que no se
             * produzcan operaciones más intensivas o cambios de simultaneidad flujo abajo(downstream) y las
             * operaciones Flowable flujo arriba(upstream) restrinjan el número de emisiones.
             *
             * Pero normalmente, cuando se compromete a utilizar un Flowable, debe esforzarse por mantener
             * sus operaciones Flowable.
             */
        }
    }
}
