package com.creativity.reactiveprogramming.operator.conditional;

import io.reactivex.Observable;

public class ExampledefaultIfEmpty {
    /*
     * Si queremos recurrir a una única emisión cuando una determinada
     * Observable resulta vacía, podemos utilizar defaultIfEmpty(). Por
     * ejemplo, si tenemos Observable<String> y filtramos solo elementos
     * que comienzan con Z, podemos recurrir a emitir None
     */
    public static void main(String[] args) {
        Observable<String> items = Observable.just("Palta","Lechuga");
        items.filter(s -> s.startsWith("Z"))
                .defaultIfEmpty("Ninguno")
                .subscribe(s -> System.out.println(s));
    }
    /*
     * Por supuesto, si se produjeran emisiones, nunca veríamos el mensaje Ninguno.
     * Ocurre solo cuando la fuente Observable está vacía
     */
}
