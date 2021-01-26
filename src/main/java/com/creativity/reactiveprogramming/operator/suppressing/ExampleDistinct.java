package com.creativity.reactiveprogramming.operator.suppressing;

import io.reactivex.Observable;

public class ExampleDistinct {

    private static int op;

    public static void main(String[] args) {

        op = 2;

        switch (op) {
            case 1:
                /*
                 * El operador distinct() emite emisiones únicas. Suprime los duplicados que siguen.
                 * La igualdad se basa en sus emisiones únicas. La igualdad se basa en los
                 * métodos hashCode() y equals() implementados por los objetos emitidos.
                 * Si queremos emitir las distintas longitudes de cadenas, esto
                 * se podría hacer de la siguiente manera:
                 */
                Observable.just("tortilla", "tamales", "rebozado")
                        .map(s -> s.length())
                        .distinct()
                        .subscribe(integer -> System.out.println("RECIBE: " + integer));
                break;
            case 2:
                /*
                 * Hay una versión sobrecargada de distinct(Function<T,K> keySelector)que
                 * acepta una función que asigna cada emisión a una clave utilizada para
                 * la lógica de igualdad. Entonces, la singularidad de cada elemento emitido
                 * se basa en la singularidad de esta clave generada, no en el elemento en sí.
                 *  Por ejemplo, podemos usar la longitud de la cadena como clave
                 * utilizada para la unicidad:
                 */
                Observable.just("tortilla", "tamales", "rebozado")
                        .distinct(s -> s.length())
                        .subscribe(s -> System.out.println("RECIBE: " + s));
                break;
        }
        /*
         * Si la clave generada es un objeto, entonces su singularidad se
         * basa en el método equals() implementado por ese objeto.
         */
    }
}
