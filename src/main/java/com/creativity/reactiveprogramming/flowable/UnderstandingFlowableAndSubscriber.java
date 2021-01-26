package com.creativity.reactiveprogramming.flowable;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class UnderstandingFlowableAndSubscriber {
    /*
     * En el lado de la fábrica, hay  Flowable.range(), Flowable.just(),
     *  Flowable.fromIterable(), y Flowable.interval(). La mayoría de estas fuentes
     * apoyan la contrapresión. Su uso es generalmente el mismo que el Observable equivalente.
     * Sin embargo, considere lo  Flowable.interval()que impulsa las emisiones basadas en el
     * tiempo a intervalos de tiempo fijos. ¿Se puede contrapresionar esto? Contempla el hecho
     * de que cada emisión está ligada al tiempo que emite. Si disminuimos la velocidad
     * Flowable.interval(), nuestras emisiones ya no reflejarían el intervalo de tiempo especificado
     *  y se volverían engañosas. Por lo tanto, Flowable.interval()es uno de esos pocos casos en la
     * API estándar que puede producirse en MissingBackpressureExceptionel momento en que el
     * downstream comienza a contrapresión. En el siguiente ejemplo, si emitimos cada milisegundo
     * contra una ralentización intenseCalculation() que se produce después observeOn(),
     * obtendremos un error.
     */
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .map(aLong -> intenseCalculation(aLong))
                .subscribe(aLong -> System.out.println(aLong),
                        throwable -> System.out.println(throwable));
        RxUtils.sleeps(Long.MAX_VALUE);
    }

    /*
     * Con este metodo ralentizamos mas la emision de del metodo Flowable.interval()
     */
    public static <T> T intenseCalculation(T value) throws InterruptedException {
        RxUtils.sleeps(ThreadLocalRandom.current().nextInt(3000));
        return value;
    }
    /*
     * Para solucionar este problema, puede usar operadores como  onBackpresureDrop() o
     * onBackPressureBuffer(). Flowable.interval()es una de esas fábricas que lógicamente
     * no se puede contrapresión en la fuente, por lo que puede agregar a la cadena de
     * procesamiento los operadores para manejar la contrapresión por usted. De lo contrario,
     *  la mayoría de las otras Flowablefábricas soportan la contrapresión.
     */
}
