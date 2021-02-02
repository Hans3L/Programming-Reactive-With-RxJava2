package com.creativity.reactiveprogramming.operator.booleans;

import com.creativity.reactiveprogramming.single.DemoSingleObserver;
import io.reactivex.Observable;

public class ExampleOperatorContains {
    /**
     * El operador contains() comprueba si la fuente ha emitido un elemento especificado
     * (basado en la implementación hashCode()/ equals()) Observable. Devuelve un
     * Single<Boolean> con true si el elemento especificado se emitió y false si no.
     */
    private static int op;

    public static void main(String[] args) {

        op = 2;

        switch (op) {
            case 1:
                Observable.range(1, 10000)
                        .contains(9000)
                        .subscribe(new DemoSingleObserver());
                break;
            case 2:
                Observable.just("Hansel", "Hanselito", "H4ns3L")
                        .contains("Hansel")
                        .subscribe(new DemoSingleObserver());
                break;
        }
        /**
         * Probablemente habrá adivinado, en el momento en que se encuentra el valor
         * especificado, el operador regresa Single<Boolean> con true , llama onComplete()
         * y elimina la canalización de procesamiento. Si la fuente llama onComplete() y
         * no se encuentra el elemento, regresa Single<Boolean> con false .
         */
    }
}
