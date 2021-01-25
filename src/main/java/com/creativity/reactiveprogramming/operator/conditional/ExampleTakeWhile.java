package com.creativity.reactiveprogramming.operator.conditional;

import io.reactivex.Observable;

public class ExampleTakeWhile {
    /*
     * Los operadores condicionales emiten o transforman Observable condicionalmente.
     * Esto permite organizar un flujo de control y determinar la ruta de ejecución,
     * lo cual es especialmente importante para agregar capacidad de toma de decisiones
     * a su programa.
     */
    private static int op;

    public static void main(String[] args) {

        op = 2;

        switch (op) {
            case 1:
                /*
                 * Otra variante del operador take() es el operador takeWhile(), que toma
                 * las emisiones mientras que una condición derivada de cada emisión es true.
                 * En el momento en que encuentre uno que no lo sea, generará el onComplete
                 * evento y eliminará los recursos utilizados.
                 */
                System.out.println("Cumple la condicion de 1 < 5 y muestra los numeros menores a el");
                Observable.range(1, 100)
                        .takeWhile(integer -> integer < 5)
                        .subscribe(integer -> System.out.println("RECIBIDO: " + integer));
                break;
            case 2:
                /*
                 * Esto sigue saltando las emisiones mientras cumplen con la condición.
                 * En el momento en que se produce esa condición false, las emisiones comienzan a fluir.
                 */
                System.out.println("Cumple la condicion de 1 < 95 y muestra los numeros mayores a el");
                Observable.range(1, 100)
                        .skipWhile(integer -> integer <= 95)
                        .subscribe(integer -> System.out.println("RECIBIDO: " + integer));
                break;
        }
    }
    /*
     * El takeUntil() operador es similar a takeWhile(), pero acepta otro Observable
     * como parámetro. Sigue tomando emisiones hasta que otro Observableempuja una
     * emisión. El skipUntil() operador tiene un comportamiento similar.
     * También acepta otro Observablecomo argumento, pero sigue saltando hasta que
     * el otro Observable emite algo.
     */
}
