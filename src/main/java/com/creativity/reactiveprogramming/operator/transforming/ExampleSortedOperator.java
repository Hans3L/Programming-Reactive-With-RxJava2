package com.creativity.reactiveprogramming.operator.transforming;

import io.reactivex.Observable;

import java.util.Comparator;

public class ExampleSortedOperator {

    private static int op;

    public static void main(String[] args) {

        /*
         * Si tiene un objeto finito Observable<T> que emite elementos
         * que son de un tipo primitivo, String tipo u objetos que implementan
         * Comparable<T>, puede usarlo sorted() para ordenar las emisiones.
         * Internamente, recolecta todas las emisiones y luego las reemite en
         * el orden especificado.
         */

        op = 4;

        switch (op) {
            /*
             * Por supuesto, esto puede tener algunas implicaciones en el rendimiento y
             * consume la memoria, ya que recopila todos los valores emitidos en la
             * memoria antes de volver a emitirlos. Si usa esto contra un infinito
             * Observable, incluso puede obtener una OutOfMemoryErrorexcepción.
             */
            case 1:
                Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                        .sorted()
                        .subscribe(i -> System.out.print(" " + i));
                break;
            case 2:
                /*
                 * La versión sobrecargada,  sorted(Comparator<T> sortFunction),
                 * se puede utilizar para establecer un orden distinto del orden
                 * natural de los elementos que son emitidos de un primitivo tipo,
                 * String tipo, u objetos que implementan Comparable<T>.
                 */
                Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                        .sorted(Comparator.reverseOrder())
                        .subscribe(i -> System.out.print(" " + i));
                break;
            case 3:
                Observable.just("Gia", "Azul", "Rossy", "Bianca")
                        .sorted()
                        .subscribe(s -> System.out.print(" " + s));
                break;
            case 4:
                /**
                 * Dado que Comparator es una interfaz de método abstracto único, puede
                 * implementarla rápidamente con una expresión lambda. Especificar los
                 * dos parámetros que representan dos emisiones,  T o 1 y T o 2, a continuación,
                 * aplicar la Comparator<T> interfaz funcional  proporcionando el cuerpo para su
                 * método compare(T o 1, T o 2). Por ejemplo, podemos clasificar los elementos
                 * emitidos no de acuerdo con su implementación del  compareTo(T) o método (es decir,
                 * la interfaz Comparable<T>), sino utilizando el comparador proporcionado .
                 * Por ejemplo, podemos ordenar los String elementos de tipo no según su
                 * implementación de la interfaz Comparable<T>, sino según su longitud
                 */
                Observable.just("Bianca", "Gia", "Rossy", "Azul")
                        .sorted(Comparator.comparingInt(value -> value.length()))
                        .subscribe(i -> System.out.print(" " + i));
                break;
        }

    }
}
