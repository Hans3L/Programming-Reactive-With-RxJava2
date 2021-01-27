package com.creativity.reactiveprogramming.operator.transforming;

import io.reactivex.Observable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExampleMapOperator {
    /*
     * El operador map() transforma un valor emitido del tipo T en
     * un valor del tipo R (que puede o no ser del mismo tipo T) utilizando
     * la Function<T,R> expresión lambda proporcionada.
     */
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
        Observable.just("1/3/2016", "2/8/2015", "10/12/2020")
                .map(s -> LocalDate.parse(s, dtf))
                .subscribe(localDate -> System.out.println("RECIBIDO: " + localDate));
    }

    /*
     * El operador map() realiza una conversión uno a uno de cada valor emitido.
     * Si necesita hacer una conversión de uno a varios (convertir una emisión en varias emisiones),
     * puede usar flatMap() o concatMap().
     */
}
