package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;

public class FromCallableObservable {

    private static int op;

    public static void main(String[] args) {
        /*
         * Para realizar un cálculo o alguna otra acción y luego emitir
         * el resultado, puedes usar Observable.just()(o Single.just() o Maybe.just())
         */
        op = 3;

        switch (op) {
            case 1:
                Observable.just(1 / 0)
                        .subscribe(integer -> System.out.println("RECIBE: " + integer),
                                throwable -> System.out.println("ERROR GENERADO: " + throwable));
                break;
            case 2:
                // Este genera un error procesado por el evento onError() por que
                // se creo el observable primero.
                Observable.just(1)   // <- first observable create
                        .map(integer -> integer / 0)
                        .subscribe(integer -> System.out.println("RECIBE: " + integer),
                                throwable -> System.out.println("ERROR GENERADO: " + throwable));
                break;
            case 3:
                // este metodo permite hacer la creacion del observable y hacer junto un
                // evento onError() tod0 en 1
                Observable.fromCallable(()-> 1/0)
                        .subscribe(integer -> System.out.println("RECIBE: " + integer),
                                throwable -> System.out.println("ERROR GENERADO: " + throwable));
                break;
        }
    }
}
