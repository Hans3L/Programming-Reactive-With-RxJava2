package com.creativity.reactiveprogramming.currency.parallelization;

import com.creativity.reactiveprogramming.observer.DemoObserver;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.net.URL;
import java.util.Scanner;

public class subscribeOnExample {
    /**
     * El operador subscribeOn() sugiere a la fuente Observable qué Scheduler utilizar
     * y cómo ejecutar operaciones en uno de sus hilos. Si esa fuente aún no está
     * vinculada a una en particular Scheduler, usará la especificada Scheduler.
     */
    private static int op;

    public static void main(String[] args) throws InterruptedException {

        op = 4;

        switch (op) {
            case 1:
                /**
                 * No importa si lo coloca subscribeOn() justo después Observable.just()
                 * o después de uno de los operadores. El operador subscribeOn() se comunica
                 * en sentido ascendente a Observable.just() cuál Scheduler usar sin importar
                 * dónde lo coloque. Sin embargo, para mayor claridad, debe colocarlo lo más
                 * cerca posible de la fuente.
                 */
                Observable.just("Sol", "Tierra", "Saturno", "Venus", "Luna")
                        .subscribeOn(Schedulers.computation())       //Preferido
                        .map(s -> s.length())
                        .filter(integer -> integer > 5)
                        .subscribe(new DemoObserver());

                Observable.just("Sol", "Tierra", "Saturno", "Venus", "Luna")
                        .map(s -> s.length())
                        .subscribeOn(Schedulers.computation())
                        .filter(integer -> integer > 5)
                        .subscribe(new DemoObserver());

                Observable.just("Sol", "Tierra", "Saturno", "Venus", "Luna")
                        .map(s -> s.length())
                        .filter(integer -> integer > 5)
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new DemoObserver());
                break;
            case 2:
                /**
                 * Tener múltiples observadores a la misma Observable con subscribeOn()
                 * resultados en cada uno conseguir su propio hilo (o hacer que se espera
                 * de un hilo disponible si no hay ninguno disponible). En Observer, puede
                 * imprimir el nombre del hilo en ejecución llamando Thread.currentThread().getName().
                 * En el siguiente ejemplo, el nombre del hilo se imprime con cada emisión,
                 * lo que muestra que, de hecho, se están utilizando dos hilos, uno para cada uno Observer.
                 */
                Observable<Integer> lengths = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                        .subscribeOn(Schedulers.computation())
                        .map(s -> UnderstandingConcurrency.intenseCalculation(s))
                        .map(s -> s.length());
                lengths.subscribe(integer -> System.out.println("RECIBIDO: " + integer + " on thread (nombre del hilo 1) "
                        + Thread.currentThread().getName()));  //nombre del hilo 1
                lengths.subscribe(integer -> System.out.println("RECIBIDO: " + integer + " on thread (nombre del hilo 2) "
                        + Thread.currentThread().getName()));  //nombre del hilo 2
                RxUtils.sleeps(10000);
                break;
            case 3:
                /**
                 * Si queremos que solo un hilo sirva a ambos observadores, podemos multidifundir
                 * esta operación. Solo asegúrese de que subscribeOn() esté colocado antes de los
                 * operadores de multidifusión.
                 */
                Observable<Integer> lengths2 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                        .subscribeOn(Schedulers.computation())
                        .map(s -> UnderstandingConcurrency.intenseCalculation(s))
                        .map(s -> s.length())
                        .publish()
                        .autoConnect(2);
                lengths2.subscribe(integer -> System.out.println("RECIBIDO: " + integer + " on thread "
                        + Thread.currentThread().getName()));
                lengths2.subscribe(integer -> System.out.println("RECIBIDO: " + integer + " on thread "
                        + Thread.currentThread().getName()));
                RxUtils.sleeps(10000);
                break;
            case 4:
                /**
                 * La mayoría de las fábricas Observable, como Observable.fromIterable()
                 * y Observable.just(), crean un elemento Observable que emite elementos
                 * en el Scheduler especificado por subscribeOn(). Para fábricas como
                 * Observable.fromCallable() y Observable.defer(), la inicialización de
                 * estas fuentes también se ejecuta en el especificado Scheduler cuando
                 * se usa subscribeOn().
                 */
                String href = "https://api.github.com/users/thomasnield/starred";
                Observable.fromCallable(() -> getResponse(href))
                        .subscribeOn(Schedulers.io())
                        .subscribe(new DemoObserver());
                RxUtils.sleeps(10000);
                break;
            // Si usa Observable.fromCallable() para esperar una respuesta de
            // URL, puede hacer que funcione en la I/O Scheduler para que el
            // hilo principal no la bloquee y no la esté esperando
        }
    }

    public static String getResponse(String href) {
        try {
            return new Scanner(new URL(href).openStream(), "UTF-8")
                    .useDelimiter("\\A").next();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
