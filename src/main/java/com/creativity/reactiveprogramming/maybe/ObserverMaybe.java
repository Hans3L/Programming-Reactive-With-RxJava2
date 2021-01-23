package com.creativity.reactiveprogramming.maybe;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public class ObserverMaybe {
    /*
     * El  Maybe es como a Single, excepto que tampoco
     * permite que se produzcan emisiones (por lo tanto Maybe).
     * El  Maybe Observer es muy parecido a un estándar Observer,
     * pero en vez de onNext() se llama a onSuccess() en su lugar
     */

    private static int op;

    public static void main(String[] args) {

        op = 3;

        switch (op) {
            case 1:
                /*
                 *
                 */
                System.out.println("Complete 1!  no surge porque no hay ambigüedad: el observable Maybe no puede emitir más de un ítem, por lo que se completa implícitamente.");
                Maybe<Integer> source = Maybe.just(100);
                source.subscribe(integer -> System.out.println("Process 1: " + integer),
                        e -> System.out.println(e),
                        () -> System.out.println("Complete 1!"));

                Maybe<Integer> empty = Maybe.empty();
                empty.subscribe(i -> System.out.println("Process 2: " + i),
                        e -> System.out.println(e),
                        () -> System.out.println("Complete 2"));
                break;
            case 2:
                Observable<Integer> source2 = Observable.just(500);
                source2.subscribe(integer -> System.out.println("Process 1: " + integer),
                        e -> System.out.println(e),
                        () -> System.out.println("Complete 1"));

                Observable<Integer> empty2 = Observable.empty();
                empty2.subscribe(integer -> System.out.println("Process 2: " + integer),
                        e -> System.out.println(e),
                        () -> System.out.println("Complete 2"));
                break;
            default:
                /*
                 * Ciertos operadores Observable que mostrare más adelante
                 * producen a Maybe. Un ejemplo es el operador firstElement(),
                 * que es similar a first(), pero devuelve un resultado vacío si no se emiten elementos
                 */
                Observable<String> source3 = Observable.just("alfa", "corona");
                source3.firstElement()
                        .subscribe(s -> System.out.println("RECIBE: " + s),
                                e -> System.out.println("Error: " + e),
                                () -> System.out.println("No emite el Complete !"));
                /*
                 * Tenga en cuenta que el evento onComplete no se generó esta vez porque
                 * el Observable no tiene idea de que el procesamiento se ha detenido
                 * después de la primera emisión
                 */
        }
    }
}
