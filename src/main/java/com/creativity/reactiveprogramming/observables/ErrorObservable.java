package com.creativity.reactiveprogramming.observables;

import io.reactivex.Observable;

public class ErrorObservable {

    static private int op;

    public static void main(String[] args) {
        /*
         * Este operador es usado para pruebas. Crea un Observable que genera
         * un error con la excepcion especificada.
         */

        op = 2;

        switch (op) {
            case 1:
                Observable.error(new Exception("Choque y fuego"))
                        .subscribe(o -> System.out.println(o),
                                throwable -> System.out.println("Error Capturado: " + throwable),
                                () -> System.out.println("Done!"));
            case 2:
                Observable.error(() -> new Exception("crash y burn"))
                        .subscribe(t -> System.out.println(t),
                                throwable -> System.out.println("Error capturado: " + throwable),
                                () -> System.out.println("Done!"));
        }

    }
}
