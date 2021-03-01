package com.creativity.reactiveprogramming.flowable;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class InterfaceSubscriber {

    private static int op;

    /*
     * En lugar de un Observer, Flowable usa a Subscriber para consumir emisiones y eventos
     * al final de una cadena Flowable.
     * Si solo pasa argumentos de evento lambda (y no un Subscriber objeto completo ), subscribe()
     * no devuelve un  Disposable, sino un org.reactivestreams.Subscription, que se puede eliminar
     * llamando en cancel() lugar de dispose(). Subscription también puede servir para otro propósito;
     * comunica en sentido ascendente cuántos elementos se desean utilizando su método request().
     * Subscription también se puede aprovechar en el método onSubscribe() de Subscriber, que puede
     * llamar al método request() (y pasar el número de elementos solicitados) en el momento en que esté
     * listo para recibir emisiones.
     */
    public static void main(String[] args) throws InterruptedException {

        op = 3;

        switch (op) {
            case 1:
                Flowable.range(1, 1000)
                        .doOnNext(integer -> System.out.println("Fuente Pusheada " + integer))
                        .observeOn(Schedulers.io())
                        .map(integer -> intenseCalculation(integer))
                        .subscribe(integer -> System.out.println("Subscriber RECIBIDO " + integer),
                                throwable -> System.out.println(throwable),
                                () -> System.out.println("HECHO!"));
                RxUtils.sleeps(20000);
                /**
                 * Por supuesto, se puede poner en práctica su propia Subscriber, así, que tendría los métodos
                 * onNext(), onError()y onComplete(), así como onSubscribe(). Sin embargo, esto no es tan
                 * sencillo como la implementación de un Observer porque hay que llamar request() en
                 * Subscription las emisiones de petición en el momento oportuno.
                 */
                break;
            case 2:
                /**
                 * La forma más rápida y más fácil de poner en práctica una Subscriber es tener la
                 * llamada onSubscribe() al método request(Long.MAX_VALUE) en Subscription que dice
                 * esencialmente el de flujo arriba no me quiere tod0 ahora. Aunque los operadores
                 * anteriores  Subscriber solicitarán emisiones a su propio ritmo de contrapresión,
                 * no existirá contrapresión entre el último operador y el Subscriber. Esto suele
                 * estar bien ya que los operadores flujos arriba restringen el flujo de todos modos.
                 */
                Flowable.range(1, 1000)
                        .doOnNext(integer -> System.out.println(" " + integer))
                        .observeOn(Schedulers.io())
                        .map(integer -> intenseCalculation(integer))
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onSubscribe(Subscription subscription) {
                                subscription.request(Long.MAX_VALUE);
                            }

                            @SneakyThrows
                            @Override
                            public void onNext(Integer integer) {
                                RxUtils.sleeps(50);  // 50 milisegundo de espera por cada emision
                                log.info("Subscriber recibido " + integer);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                throwable.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                log.info("Hecho");
                            }
                        });
                RxUtils.sleeps(20000);  //20 segundos
                break;
            /**
             * Si desea Subscriber establecer una relación explícita de contrapresión
             * con el operador que le precede, deberá microgestionar las llamadas request().
             * Digamos, para alguna situación extrema, decide que desea  Subscriber solicitar
             * 40 emisiones inicialmente y luego 20 emisiones a la vez después de eso.
             */
            case 3:
                /**
                 * Tenga en cuenta que la fuente todavía emite 128 emisiones inicialmente y luego todavía
                 * empuja 96 emisiones a la vez. Pero nuestro Subscriber recibe solo 40 emisiones, según
                 * lo especificado, y luego solicita constantemente 20 más. La llamada request() en nuestro
                 * Subscriber solo se comunica con el operador inmediato flujo arriba (upstream), que es map().
                 * Es map() probable que el operador transmita esa solicitud a observeOn(), que almacena elementos
                 * en caché y solo elimina 40 y luego 20, según lo solicitado por Subscriber. Cuando su caché se
                 * agota o se borra, solicita otros 96 del flujo ascendente (upstream).
                 *
                 * Esta es una advertencia: no debe confiar en estos números exactos de emisiones solicitadas,
                 * como 128 y 96. Se trata de una implementación interna que observamos, y estos números pueden
                 * cambiarse para ayudar a optimizar la implementación en el futuro.
                 *
                 * Esta implementación personalizada en realidad puede reducir el rendimiento, pero demuestra
                 * cómo administrar la contrapresión personalizada con una Subscriber implementación personalizada.
                 * Solo tenga en cuenta que las llamadas request() no van en sentido ascendente. Solo van al operador
                 * anterior, que decide cómo retransmitir esa solicitud en sentido ascendente.
                 */
                Flowable.range(1, 1000)
                        .doOnNext(integer -> System.out.println("Fuente pusheada " + integer))
                        .observeOn(Schedulers.io())
                        .map(integer -> intenseCalculation(integer))
                        .subscribe(new Subscriber<Integer>() {
                            Subscription subscription;
                            AtomicInteger count = new AtomicInteger(0);   //contador para clases anonimas

                            @Override
                            public void onSubscribe(Subscription subscription) {
                                this.subscription = subscription;
                                log.info("Solicitando 40 items!");
                                subscription.request(40);
                            }

                            @SneakyThrows
                            @Override
                            public void onNext(Integer integer) {
                                RxUtils.sleeps(50); // 50 milisegundo de espera por cada emision
                                log.info("Subscriber Recibiendo " + integer);
                                if (count.incrementAndGet() % 20 == 0 && count.get() >= 40) { // condicionamos a que el resto sea el mismo
                                    // valor del incremento hasta obtener 0 con la division 20/20
                                    System.out.println("Solicitando 20 mas! ");               //y obtener los 40 subscritos por el  metodo request
                                    subscription.request(20);
                                }
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                throwable.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                log.info("Hecho");
                            }
                        });
                RxUtils.sleeps(20000);  //20 segundos de espera para funcionar el programa
                break;
        }
    }

    public static <T> T intenseCalculation(T value) throws InterruptedException {
        //dormir hasta 200 milisegundos
        RxUtils.sleeps(ThreadLocalRandom.current().nextInt(200));
        return value;
    }
}
