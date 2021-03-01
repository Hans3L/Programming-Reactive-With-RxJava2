package com.creativity.reactiveprogramming.currency.parallelization;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class subscribeOnAfondo {
    private static int op;

    public static void main(String[] args) throws InterruptedException {
        op = 2;
        switch (op) {
            case 1:
                /**
                 * Es importante tener en cuenta que el operador subscribeOn()
                 * no tiene ningún efecto práctico con ciertas fuentes (y mantiene
                 * un hilo de trabajo innecesariamente en espera hasta que finaliza
                 * esa operación). Esto puede deberse a que Observable ya utiliza un
                 * Scheduler. Por ejemplo, Observable.interval() usará Schedulers.computation()
                 * e ignorará cualquiera subscribeOn() que especifique
                 */
                //Observable.just(1,2,3,4,5)
                Observable.interval(1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(aLong -> System.out.println("RECIBIDO: " + aLong +
                                " on Thread " + Thread.currentThread().getName()));
                RxUtils.sleeps(5000);
                //Como puede ver, el hilo utilizado es Computation uno y no un newThread.
                break;
            case 2:
                /**
                 * Si desea cambiarla, puede proporcionar una Scheduler como tercer
                 * argumento para especificar una diferente Scheduler a su uso. Aquí,
                 * Observable.interval() está configurado para usar el método Scheduler
                 * creado por la fábrica Schedulers.newThread()
                 */
                Observable.interval(1, TimeUnit.SECONDS, Schedulers.newThread())
                        .subscribe(aLong -> System.out.println("RECIBIDO: " + aLong +
                                " on Thread " + Thread.currentThread().getName()));
                //Como era de esperar, el hilo usado es NewThread ahora!
                RxUtils.sleeps(5000);
                break;
            case 3:
                /**
                 * Esto trae a colación el siguiente punto: si tiene varias llamadas
                 * subscribeOn() en una Observable cadena determinada , la más alta, o la más
                 * cercana a la fuente, ganará y hará que las siguientes no tengan ningún
                 * efecto práctico (aparte del uso innecesario de recursos) .Si subscribeOn()
                 * se usa con Schedulers.computation() y en sentido descendente, se usa otro
                 * subscribeOn() con Schedulers.io() (o cualquier otro método Scheduler de fábrica)
                 * , el Computation hilo permanece en uso.
                 */
                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                        .subscribeOn(Schedulers.computation()) //siempre que el programador este cerca de fuente ganara
                        .filter(s -> s.length() == 5)
                        .subscribeOn(Schedulers.io())
                        .subscribe(s -> System.out.println("RECIBE: " + s +
                                " on thread " + Thread.currentThread().getName()));
                RxUtils.sleeps(5000);
                break;
            /**
             * En resumen, subscribeOn() especifica qué Scheduler fuente Observable
             * debe usar y utilizará un trabajador de esta Scheduler para impulsar
             * las emisiones hasta la final Observer. Pero tenga cuidado si ya hay
             * un operador subscribeOn() en uso en sentido ascendente y esto no
             * permite una anulación.
             */
        }
    }
}
