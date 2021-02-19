package com.creativity.reactiveprogramming.currency.parallelization;

import com.creativity.reactiveprogramming.observer.DemoObserver;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Getter
public class UnderstandingConcurrency {
    private static int op;

    public static void main(String[] args) throws InterruptedException {
        op = 3;

        switch (op) {
            case 1:
                DateTimeFormatter f = DateTimeFormatter.ofPattern("MM:ss");
                System.out.println(LocalDateTime.now().format(f));
                Observable.interval(1, TimeUnit.SECONDS)
                        .map(i -> LocalDateTime.now().format(f) + " " + i + " Lima")
                        .subscribe(new DemoObserver());
                RxUtils.sleeps(5000);
                break;
            case 2:
                /**
                 * Observe cómo ambas emisiones observables calientes ya que cada una se
                 * ralentiza de 0 a 3 segundos en la operación map(). Más importante aún,
                 * nota cómo el primer Observable tiro Alpha, Beta y Gamma debe terminar
                 * primero y llamada onComplete() antes de disparar el segundo Observable
                 * emisor de los números 1 a través 3. Si disparamos ambos observables al
                 * mismo tiempo en lugar de esperar a que uno se complete antes de iniciar el
                 * otro, podríamos hacer esta operación mucho más rápido.
                 */
                Observable.just("Alpha", "Beta", "Gamma")
                        .map(s -> intenseCalculation(s))
                        .subscribe(System.out::println);
                Observable.range(1, 3)
                        .map(integer -> intenseCalculation(integer))
                        .subscribe(System.out::println);
                break;
            case 3:
                /**
                 * Podemos lograr esto usando el operador subscribeOn(), que sugiere a la fuente
                 * que dispare las emisiones en un Scheduler hilo específico (hilo separado).
                 * En este caso, usemos Schedulers.computation(), que agrupa un número fijo de
                 * subprocesos apropiados para operaciones de cálculo. Proporcionará un hilo para
                 * impulsar las emisiones para cada uno Observer. Cuando onComplete()se llama, el
                 * hilo se devolverá para Scheduler que pueda reutilizarse en otro lugar:
                 */
                Observable.just("Alpha", "Beta", "Gamma")
                        .subscribeOn(Schedulers.computation())
                        .map(s -> intenseCalculation(s))
                        .subscribe(System.out::println);
                Disposable fuente2 = Observable.range(1, 3)
                        .subscribeOn(Schedulers.computation())
                        .map(integer -> intenseCalculation(integer))
                        .subscribe(integer -> System.out.println(integer));
                RxUtils.sleeps(20000);
                /**
                 * Es probable que su salida sea diferente de esta debido a los tiempos de seelp
                 * aleatorios en el método intenseCalculation(). Pero observe cómo ambos observables
                 * se activan simultáneamente ahora, lo que permite que el programa termine mucho
                 * más rápido. En lugar de que el hilo principal ejecute emisiones para el primero
                 * Observable antes de pasar al segundo, ahora dispara ambos observables inmediatamente
                 * y sigue adelante. No esperará a que ninguno de los dos se Observable complete.
                 * Es por eso que tuvimos que agregar otra sleep() llamada al método al final, para que
                 * ambos subprocesos tengan tiempo de terminar.
                 * Tener múltiples procesos ocurriendo al mismo tiempo es lo que hace que una aplicación sea
                 * concurrente. Puede resultar en una eficiencia mucho mayor ya que utiliza más núcleos.
                 * La concurrencia también hace que los modelos de código sean más poderosos y más
                 * representativos de cómo funciona nuestro mundo, donde ocurren múltiples actividades
                 * simultáneamente.
                 */
                break;
            case 4:
                /**
                 * Otra cosa que es emocionante sobre RxJava son sus operadores, al
                 * menos los oficiales y los personalizados construidos correctamente.
                 * Pueden trabajar con observables en diferentes hilos de forma segura.
                 * Incluso los operadores y las fábricas que combinan múltiples observables,
                 * como merge()y zip(), combinan de manera segura las emisiones impulsadas
                 * por diferentes hilos. Por ejemplo, podemos usar zip() en nuestros dos
                 * observables en el ejemplo anterior incluso si emiten en dos subprocesos
                 * de cálculo separados
                 */
                Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma")
                        .subscribeOn(Schedulers.computation())
                        .map(s -> intenseCalculation(s));
                Observable<Integer> source2 = Observable.range(1, 3)
                        .subscribeOn(Schedulers.computation())
                        .map(integer -> intenseCalculation(integer));
                Observable.zip(source1, source2, (s, i) -> s + " - " + i)
                        .subscribe(System.out::println);
                RxUtils.sleeps(20000);
                break;
            /**
             * Cuando comienzas a hacer que las aplicaciones reactivas sean concurrentes, puede
             * aparecer una complicación sutil. De forma predeterminada, una aplicación no
             * concurrente tendrá un hilo haciendo t0do el trabajo desde el origen hasta el
             * final Observer. Pero tener múltiples hilos puede hacer que las emisiones se
             * produzcan más rápido de lo que un Observer pueden consumir. Por ejemplo, el operador zip()
             * puede tener una fuente que produce emisiones más rápidamente que la otra. Esto
             * puede sobrecargar el programa y la memoria puede agotarse ya que ciertos operadores
             * almacenan en caché las emisiones acumuladas. Cuando trabaje con un alto volumen de
             * emisiones (más de 10,000) y aproveche la simultaneidad, es probable que desee usar
             * Flowable en lugar de Observable.
             */
        }
    }

    public static <T> T intenseCalculation(T value) throws InterruptedException {
        RxUtils.sleeps(ThreadLocalRandom.current().nextInt(3000));
        return value;
    }
}
