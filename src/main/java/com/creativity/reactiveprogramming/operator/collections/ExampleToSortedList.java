package com.creativity.reactiveprogramming.operator.collections;

import com.creativity.reactiveprogramming.single.DemoSingleObserver;
import io.reactivex.Observable;

import java.util.Arrays;

public class ExampleToSortedList {
    /**
     * Es un tipo diferente de operador toList() toSortedList(). Recopila los valores
     * emitidos en una List objeto que tiene los elementos ordenados en un orden natural
     * (según su Comparable implementación). Luego, empuja ese List<T> objeto con
     * elementos ordenados en Observer
     */
    private static int op;

    public static void main(String[] args) {

        op = 3;

        switch (op) {
            case 1:
                Observable.just("Beta", "Gamma", "Alpha")
                        .toSortedList()
                        .subscribe(new DemoSingleObserver());
                break;
            case 2:
                Observable.just(120, 10000, 9, -1)
                        .toSortedList()
                        .subscribe(new DemoSingleObserver());
                break;
            case 3:
                Observable<Integer> enteros = Observable.fromIterable(Arrays.asList(93, 7, 10, 15, 3, -50));
                enteros
                        .toSortedList()
                        .subscribe(integers -> System.out.println("Ordenados: " + integers));
        }
        /**
         * Al igual que con el operador sorted(), puede proporcionar Comparator un argumento para
         * aplicar una lógica de clasificación diferente. También puede especificar una capacidad
         * inicial para el respaldo ArrayList, como en el caso del operador toList()
         */
    }
}
