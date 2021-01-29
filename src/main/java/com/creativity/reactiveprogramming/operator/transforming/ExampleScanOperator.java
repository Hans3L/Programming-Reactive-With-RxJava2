package com.creativity.reactiveprogramming.operator.transforming;

import com.creativity.reactiveprogramming.observer.DemoObserver;
import io.reactivex.Observable;

public class ExampleScanOperator {

    private static int op;

    public static void main(String[] args) {
        /**
         * El método scan() es un agregador rodante. Agrega cada elemento
         * emitido al acumulador provisto y emite cada valor acumulado incremental.
         */

        op = 2;

        switch (op) {
            case 1:
                Observable.just(5, 3, 7)
                        .scan((acumulador, integer2) -> acumulador + integer2)
                        .subscribe(integer -> System.out.println(integer));
                /**
                 * Este operador no tiene que usarse solo para sumas continuas. Puede
                 * crear muchos tipos de acumuladores, incluso los que no son matemáticos,
                 * como String concatenaciones o boolean reducciones.
                 */
                break;
            case 2:
                /**
                 * También puede proporcionar un valor inicial para el primer argumento y
                 * agregar los valores emitidos en un tipo diferente al que se está emitiendo.
                 * Si quisiéramos emitir el recuento continuo de emisiones, podríamos proporcionar
                 * un valor inicial de 0 y simplemente agregarlo 1 por cada valor emitido.
                 * Tenga en cuenta que el valor inicial se emitirá primero, así que úselo
                 * skip(1) después scan() si no desea que esa emisión inicial se incluya en el
                 * acumulador.
                 */
                Observable.just("Hansel", "Caleb", "Gretel")
                        .scan(0, (acumulador, nextName) -> acumulador + 1)
                        .subscribe(new DemoObserver());
                break;
        }
        /**
         * Tenga en cuenta que scan()es muy similar a reduce().Sin embargo,
         * tenga cuidado de no confundirlos. El operador scan() emite la acumulación
         * rodante para cada emisión, mientras que reduce() produce un único resultado
         * que refleja el valor acumulado final después de que se llama a onComplete().
         * Esto significa que reduce() debe usarse solo con un finito Observable,
         * mientras que el operador scan() también se puede usar con un infinito Observable.
         */
    }
}
