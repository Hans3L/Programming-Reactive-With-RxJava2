package com.creativity.reactiveprogramming.operator.transforming;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class ExampleStarWithItem {

    private static int op;

    public static void main(String[] args) {

        op = 3;

        switch (op) {
            /*
             * Para un dado Observable<T>, el operador startWith()
             * (llamado  startWithItem en RxJava 3.x) le permite
             * insertar un valor de tipo T que se emitirá antes que todos los
             * demás valores. Por ejemplo, si tenemos un Observable<String>
             * que emite nombres de bebidas que nos gustaría imprimir, podemos
             * usar startWithItem() para insertar un encabezado como primer
             * valor de la secuencia.
             */
            case 1:
                Observable<String> menu = Observable.just("Cafe", "Anis", "Expreso", "Cappuccino");
                menu.startWith("Menu de Bebidas")
                        .subscribe(s -> System.out.println("RECIBIDO: " + s));
                break;
            case 2:
                /*
                 * Si desea comenzar con más de un valor emitido primero , use startWithArray(),
                 * que acepta varargs(una matriz o cualquier número de String valores como parámetros)
                 */
                Observable<String> menu2 = Observable.just("Cafe", "Anis", "Expreso", "Cappuccino");
                menu2.startWithArray("Menu de Bebidas", "----------------")
                        .subscribe(System.out::println);
                break;
            case 3:
                Observable<String> menu3 = Observable.just("Cafe", "Anis", "Expreso", "Cappuccino");
                List<String> list = Arrays.asList("Menu de Bebidas", "----------------");
                menu3.startWith(list)
                        .subscribe(s -> System.out.println(s));
                break;
        }
        /*
         * El operador startWithItem() es útil para casos como este, donde queremos
         * sembrar un valor inicial o preceder nuestras emisiones con un valor particular.
         * Cuando se debe emitir más de un valor primero, antes de que los valores de la
         * fuente Observable comiencen a fluir, el operador startWithArray() o
         * startWithIterable()(Rxjva 3.x) es su amigo.
         */
    }
}
