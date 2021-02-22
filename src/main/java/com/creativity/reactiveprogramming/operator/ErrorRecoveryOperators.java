package com.creativity.reactiveprogramming.operator;

import io.reactivex.Observable;

public class ErrorRecoveryOperators {
    /**
     * Las excepciones pueden ocurrir casi en cualquier parte de la cadena
     * de los operadores Observable, y ya sabemos sobre el evento onError que
     * se comunica en la cadena Observable al Observer. Después de eso, la
     * suscripción termina y no se producen más emisiones.
     * Pero a veces, queremos interceptar las excepciones antes de que lleguen
     * al Observer e intentar alguna forma de recuperación. También podemos pretender
     * que el error nunca ocurrió y esperar seguir procesando las emisiones.
     */
    public static void main(String[] args) {
        Observable.just(5, 2, 4, 0, 3)
                .map(integer -> 10 / integer)
                .subscribe(integer -> System.out.println("RECIBIDO: " + integer),
                        throwable -> System.out.println("ERROR RECIBIDO: " + throwable));
    }
}
