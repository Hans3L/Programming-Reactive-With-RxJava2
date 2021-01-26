package com.creativity.reactiveprogramming.operator.suppressing;

import io.reactivex.Observable;

public class ExampleSkip {
    public static void main(String[] args) {
        /*
         * El operador skip() hace lo contrario del take()operador. Ignora el
         * número especificado de emisiones y luego emite las siguientes.
         */
        Observable.range(1,100)
                .skip(90)
                .subscribe(integer -> System.out.println("RECIBE: " + integer));
    }
}
