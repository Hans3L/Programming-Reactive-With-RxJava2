package com.creativity.reactiveprogramming.operator.suppressing;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class ExampleTake {
    /*
     * El operador take() tiene dos versiones sobrecargadas. Uno toma el
     * número especificado de emisiones y llama onComplete() después de que todas
     * lo alcancen. También dispondrá de toda la suscripción para que no se
     * produzcan más emisiones.
     */
    private static int op;
    public static void main(String[] args) throws InterruptedException {

        op = 2;

        switch (op){
            case 1:
                Observable.just("sol","tierra","venus")
                        .take(2)
                        .subscribe(s -> System.out.println("RECIBE: " + s));
                break;
            case 2:
                /*
                 * La primera columna es la hora actual en segundos y milisegundos.
                 * Como puede ver, podemos obtener solo 6 emisiones en 2 segundos
                 * si están espaciadas en 300 milisegundos porque el primer valor
                 * también se emite después de 300 milisegundos.
                 */
                DateTimeFormatter f = DateTimeFormatter.ofPattern("ss:SSS");
                System.out.println(LocalDateTime.now().format(f));
                Observable.interval(300, TimeUnit.MILLISECONDS)
                        .take(2,TimeUnit.SECONDS)
                        .subscribe(aLong -> System.out.println(LocalDateTime.now()
                                .format(f) + " RECIBIDO: " + aLong));
                RxUtils.sleeps(5000);
                break;
        }

    }
}
