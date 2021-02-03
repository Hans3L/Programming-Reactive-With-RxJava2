package com.creativity.reactiveprogramming.operator.collections;

import com.creativity.reactiveprogramming.single.DemoSingleObserver;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExampleToList {
    /**
     * El toList() es probablemente el más utilizado entre todos los operadores de recolección.
     * Para un Observable<T>, recopila los elementos entrantes en un List<T> y luego empuja
     * ese List<T> objeto como un valor único Single<List<T>>.
     */
    private static int op;

    public static void main(String[] args) {

        op = 4;

        switch (op) {
            case 1:
                // Aqui recopilamos valores String en un List<String>. Después precede el Observable
                // señalando onComplete(), esa lista se empuja en el Observer para imprimir
                Observable.just("Alpha", "Beta", "Gamma")
                        .toList()
                        .subscribe(strings ->
                                System.out.println("RECIBIDO: " + strings));
                break;
            case 2:
                /**
                 * De forma predeterminada, toList() utiliza una ArrayList implementación de la
                 * interfaz List. Opcionalmente, puede especificar un argumento entero para que
                 * sirva como capacityHint valor que optimice la inicialización del ArrayList
                 * para esperar aproximadamente esa cantidad de elementos:
                 */
                Observable.range(1, 1000)
                        .toList(1000)
                        .subscribe(integers -> System.out.println("RECIBIDO: " + integers));
                break;
            case 3:
                /**
                 * Si desea utilizar una implementación List diferente, puede proporcionar una función
                 * Callable como argumento para especificar una. En el siguiente fragmento de código,
                 * proporcionamos una CopyOnWriteArrayList instancia para que sirva como implementación List
                 */
                Observable.just("Beta", "Gamma", "Alpha")
                        .toList(CopyOnWriteArrayList::new)
                        .subscribe(strings -> System.out.println("RECIBIDO: " + strings));
                break;
            case 4:
                Single<List<Integer>> number = Observable.fromIterable(RxUtils.positiveNumbers(10)).toList();
                number
                        .map(integers -> integers.contains(5))
                        .subscribe(new DemoSingleObserver());
                break;
        }
        /**
         * Si desea utilizar la lista inmutable de Google Guava, esto es un poco más complicado
         * ya que es inmutable y usa un constructor.
         */
    }
}
