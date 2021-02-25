package com.creativity.reactiveprogramming.flowable;

import com.creativity.reactiveprogramming.utils.MyItem;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ExampleFlowable {
    /*
     * La clase Flowable es una variante de la  Observable que puede decirle a la fuente
     * que emita a un ritmo especificado por las operaciones posteriores. En otras palabras,
     * puede ejercer una contrapresión sobre la fuente.
     */
    public static void main(String[] args) throws InterruptedException {
        Flowable.range(1, 999_999_999)
                .map(MyItem::new)
                .observeOn(Schedulers.io())
                .subscribe(myItem -> {
                    RxUtils.sleeps(50);
                    System.out.println("RECIBE MyItem " + myItem.id + " en hilo " + Thread.currentThread().getName());
                });
        RxUtils.sleeps(Long.MAX_VALUE);
    }
    /*
     * Puedes ver algo muy diferente en la salida con  Flowable. Omitimos algunas partes de
     * la salida, usando ...para resaltar los eventos clave. Como puede ver, se impulsaron
     * 128 emisiones desde la Flowable.range() primera y se construyeron MyItem 128 instancias.
     * Después de eso, observeOn() empujó 96 de los elementos construidos corriente abajo a
     * Subscriber. Después de que estas 96 emisiones fueron procesadas por Subscriber, otras
     * 96 fueron expulsadas de la fuente. Luego, se pasaron a otros 96 Subscriber.

     * ¿Ves un patrón todavía? La fuente comenzó impulsando 128 emisiones, y después de eso,
     * la cadena Flowable procesó un flujo constante de 96 emisiones a la vez. Es casi como
     * si toda la cadena Flowable se esforzara por no tener más de 96 emisiones en su tubería
     * en un momento dado. Efectivamente, ¡eso es exactamente lo que está sucediendo! Esto es
     * lo que llamamos contrapresión, y efectivamente introduce una dinámica de atracción a
     * la operación basada en empujar para limitar la frecuencia con la que emite la fuente.
     *
     * Pero, ¿por qué  Flowable.range() comenzó con 128 emisiones y por qué observeOn() solo
     * envió 96 flujo abajo(DownStream) antes de solicitar otras 96, dejando 32 emisiones sin
     * procesar? El lote inicial de emisiones es un poco más grande, por lo que se pone en cola
     * algo de trabajo adicional si hay tiempo de inactividad. Si (en teoría) nuestra operación
     * Flowable comenzara solicitando 96 emisiones y continuara emitiendo de manera constante a
     * 96 emisiones a la vez, habría momentos en los que las operaciones podrían esperar inactivas
     * las próximas 96. Por lo tanto, se mantiene una memoria caché adicional de 32 emisiones para
     * proporcionar trabajo durante estos momentos de inactividad, lo que puede producir un mayor
     * rendimiento. Esto es muy parecido a un almacén que tiene un poco de inventario adicional para
     * suministrar pedidos mientras espera más de la fábrica.
     *
     * Note la línea sleep(Long.MAX_VALUE). Tuvimos que agregarlo para permitir que la cadena de
     * procesamiento funcione a través de todas las emisiones. De lo contrario, debido a la
     * naturaleza asincrónica del procesamiento, la aplicación finaliza antes de que se procese un
     * número significativo de emisiones. No necesitábamos hacer esto cuando usamos Observable.range()
     * porque el hilo principal siempre estaba ocupado alimentando las emisiones al hilo Scheduler.io().
     * Con Flowable, el hilo principal emitió solo 128 emisiones antes de cambiar a escuchar
     * desde Subscriber, por lo que sin  sleep(Long.MAX_VALUE), simplemente sale. Es por eso que
     * lo detuvimos para darle al hilo Scheduler.io() la oportunidad de terminar el procesamiento,
     * para que pueda enviar una señal al hilo principal para que continúe impulsando las emisiones.

     * Lo bueno de Flowable y sus operadores es que generalmente hacen tod0 el trabajo por usted.
     * No tiene que especificar ninguna política o parámetro de contrapresión a menos que necesite
     * crear los suyos Flowable desde cero o tratar con fuentes (como Observable) que no implementen
     * contrapresión.
     *
     * Tenga en cuenta que a Flowable no está suscrito por un, Observer sino por un
     * org.reactivestreams.Subscriber: el método subscribe() de a Flowable acepta Subscriber
     *
     * De lo contrario, Flowable es como Observablecon casi todos los operadores que aprendimos
     * hasta ahora. Puede convertir Observable en Flowable o viceversa, lo cual cubriré más adelante.
     */
}
