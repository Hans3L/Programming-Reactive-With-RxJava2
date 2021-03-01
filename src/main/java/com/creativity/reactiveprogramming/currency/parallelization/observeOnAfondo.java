package com.creativity.reactiveprogramming.currency.parallelization;

import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.io.*;

public class observeOnAfondo {
    /**
     * El operador subscribeOn() indica a la fuente sobre qué Observable
     * Scheduler emitir emisiones. Si subscribeOn() es la única
     * operación simultánea en una cadena Observable, el hilo de ese Scheduler
     * funcionará en toda la cadena Observable, empujando las emisiones desde
     * la fuente hasta la final Observer. El operador observeOn(), sin
     * embargo, interceptará las emisiones en ese punto de la cadena Observable
     * y que cambiar a otra Scheduler en el futuro.
     */
    private static int op;

    public static void main(String[] args) throws InterruptedException {
        op = 2;
        switch (op) {
            case 1:
                /**
                 * A diferencia de subscribeOn() la ubicación de las observeOn().
                 * Deja todas las operaciones en sentido ascendente en el valor predeterminado
                 * o subscribeOn() definido Scheduler, pero cambia a un Scheduler flujo
                 * descendente diferente . En el siguiente ejemplo, un Observable emite una
                 * serie de cuerdas (que son /valores Separados), que luego se dividen en
                 * una Scheduler I/O. Pero después de eso, el observeOn() operador
                 * cambia a un cálculo Scheduler para filtrar solo números y calcular su suma
                 */
                Observable.just("WHISKEY/27653/TANGO", "6555/BRAVO", "232352/5675675/FOXTROT")
                        .subscribeOn(Schedulers.io()) //Inicia el programador E/S
                        .flatMap(s -> Observable.fromArray(s.split("/")))
                        .observeOn(Schedulers.computation()) //Swichea a el programador Computación
                        .filter(s -> s.matches("[0-9]+"))
                        .map(Integer::valueOf)  //.map(s -> Integer.valueOf(s))
                        .reduce((total, next) -> total + next)
                        .subscribe(integer -> System.out.println("RECIBE: " + integer + " en hilo "
                                + Thread.currentThread().getName()));
                RxUtils.sleeps(1000);
                /**
                 * Puede usarlo observeOn() para una situación como la emulada anteriormente.
                 * Si desea leer una o más fuentes de datos y esperar a que regrese la respuesta,
                 * querrá hacer esa parte Schedulers.io() y es probable que aproveche subscribeOn()
                 * ya que esa es la operación inicial. Pero una vez que tenga esos datos, es posible
                 * que desee ejecutar cálculos intensivos con ellos, y es posible que Scheduler.io()
                 * ya no sea apropiado. Querrá limitar estas operaciones a unos pocos subprocesos que
                 * utilizarán completamente la CPU. Por lo tanto, se utiliza observeOn() para redirigir
                 * datos a Schedulers.computation().
                 */
                break;
            case 2:
                /**
                 * De hecho, puede utilizar varios operadores observeOn() para cambiar Schedulers más
                 * de una vez. Continuando con nuestro ejemplo anterior, digamos que queremos escribir
                 * nuestra suma calculada en un disco y escribirla en un archivo. Vamos a suponer que
                 * se trataba de una gran cantidad de datos en lugar de un solo número  y queremos sacar
                 * esta operación de escritura en disco del Programador computation  y poner de
                 * nuevo en el Programador I/O. Podemos lograr esto introduciendo un segundo observeOn().
                 * Agreguemos también los operadores doOnNext()y doOnSuccess()(debido a Maybe) para echar
                 * un vistazo a qué hilo está usando cada operación
                 */
                Observable.just("WHISKEY/27653/TANGO", "6555/BRAVO", "232352/5675675/FOXTROT")
                        .subscribeOn(Schedulers.io())
                        .flatMap(s -> Observable.fromArray(s.split("/")))
                        .doOnNext(s -> System.out.println("Split out " + s + " en hilo " +
                                Thread.currentThread().getName()))
                        .observeOn(Schedulers.computation()) //Sucede el programador de computacion

                        .filter(s -> s.matches("[0-9]+"))
                        .map(Integer::valueOf)
                        .reduce((total, next) -> total + next)
                        .doOnSuccess(integer -> System.out.println("Calculando suma " + integer +
                                " en hilo " + Thread.currentThread().getName()))
                        .observeOn(Schedulers.io())
                        .map(integer -> integer.toString())
                        .doOnSuccess(s -> System.out.println("Escribir " + s + " archivar en hilo " +
                                Thread.currentThread().getName()))
                        .subscribe(s -> write(s, "output.txt"));
                RxUtils.sleeps(1000);
                break;
            /**
             * Si observa de cerca la salida, verá que las emisiones String se empujaron y
             * dividieron inicialmente en la Scheduler I/O a através del hilo RxCachedThreadScheduler-1
             * Después de eso, cada emisión se cambió al Scheduler computation  y se introdujo en
             * un cálculo de suma, que se realizó en el hilo RxComputationThreadPool-1 .Luego, esa
             * suma se cambió a la Scheduler I/O para que se escribiera en un archivo de texto, y ese trabajo se
             * realizó en el hilo RxCachedThreadScheduler-1(que resultó ser el hilo que impulsó las emisiones
             * iniciales y fue reutilizado!).
             */
        }
    }

    public static void write(String text, String path) {
        BufferedWriter writer = null;
        try {
            //crea un archivo temporal
            File file = new File(path);
            writer = new BufferedWriter(new FileWriter(file));
            writer.append(text);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
