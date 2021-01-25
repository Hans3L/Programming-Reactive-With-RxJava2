package com.creativity.reactiveprogramming.operator.conditional;

import io.reactivex.Observable;

public class ExampleSwitchIfEmpty {
    /*
     * Similar a defaultIfEmpty(), switchIfEmpty() especifica un valor
     * diferente Observable para emitir si la fuente Observable está vacía.
     * Esto le permite especificar una secuencia diferente de emisiones en
     * el caso de que la fuente esté vacía en lugar de emitir un solo valor,
     * como en el caso de defaultIfEmpty().
     */
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .filter(s -> s.startsWith("Z"))
                .switchIfEmpty(Observable.just("Zeta", "Eta", "Theta"))
                .subscribe(s -> System.out.println("RECIBIDO: " + s),
                        throwable -> System.out.println("ERROR RECIBIDO: " + throwable));
    }
}
