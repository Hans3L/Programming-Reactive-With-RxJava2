package com.creativity.reactiveprogramming.operator.collections;

import com.creativity.reactiveprogramming.single.DemoSingleObserver;
import io.reactivex.Observable;

public class ExampleToMultiMap {
    /**
     * Si desea que una clave determinada se asigne a varios valores,
     * puede usar toMultiMap() en su lugar, que mantiene una lista de
     * los valores correspondientes para cada clave. Los elementos Marte
     * y Venus luego se colocarán en una lista que está tecleada fuera de la longitud 5
     */
    public static void main(String[] args) {
        Observable.just("Marte", "Saturno", "Venus")
                .toMultimap(String::length)
                .subscribe(new DemoSingleObserver());
    }
}
