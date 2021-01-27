package com.creativity.reactiveprogramming.operator.suppressing;

import io.reactivex.Observable;

public class ExampleElementAt {
    /*
     * Puede obtener una emisión específica por su índice especificado por el long valor,
     * comenzando en 0.
     */
    public static void main(String[] args) {
        Observable.just("Colombia", "Italia", "Israel", "Perú", "Bolivia")
                .elementAt(3)
                .subscribe(s -> System.out.println("CONSUME: " + s));
    }
    /*
     * Puede que no lo hayas notado, pero elementAt()regresa en Maybe<T> lugar de Observable<T>.
     * Esto se debe a que produce una emisión, pero si hay menos emisiones que el índice buscado,
     * estará vacío.
     * Hay otros metodos de elementAt(), como elementAtOrError(), que devuelve Single y emite un
     * error si no se encuentra un elemento en ese índice. singleElement() convierte un Observable
     * en un Maybe, pero produce un error si hay más de un elemento. Finalmente, firstElement() y
     * lastElement() emiten el primer y último elemento, respectivamente.
     */
}
