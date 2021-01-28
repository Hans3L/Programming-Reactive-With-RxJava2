package com.creativity.reactiveprogramming.operator.transforming;

import com.creativity.reactiveprogramming.observer.DemoObserver;
import io.reactivex.Observable;

public class ExampleCastOperator {

    /*
     * Es un operador cast() simple similar a un mapa que convierte cada
     * elemento emitido a otro tipo. Si necesitamos convertir cada valor
     * emitido por Observable<String> a un Object(y devolver un Observable<Object>),
     * podríamos usar el operador map().
     */

    private static int op;

    public static void main(String[] args) {

        op = 2;

        switch (op) {
            case 1:
                Observable<String> items = Observable.just("pelota", "guantes", "canilleras", "camiseta");
                items
                        .map(o -> (Object) o)
                        .subscribe(new DemoObserver());
                break;
            case 2:
                Observable<Object> items2 = Observable.just("pelota", "guantes", "canilleras", "camiseta");
                items2
                        .cast(String.class)
                        .subscribe(s -> {
                            System.out.println(s instanceof Object);
                        });
                break;
        }
    }
}
