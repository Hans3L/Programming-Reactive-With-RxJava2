package com.creativity.reactiveprogramming.operator.collections;

import com.creativity.reactiveprogramming.single.DemoSingleObserver;
import io.reactivex.Observable;

import java.util.concurrent.ConcurrentHashMap;

public class ExampleToMap {
    /**
     * Para un  Observable<T>, el operador toMap() recopila los valores recibidos
     * en Map<K,T>, donde K es el tipo de clave. La clave es generada por la
     * Function<T,K> función proporcionada como argumento.
     */
    private static int op;

    public static void main(String[] args) {

        op = 4;

        switch (op) {
            case 1:
                // Si queremos recopilar cadenas en Map<Char, String>, donde cada cadena tiene
                // su primer carácter, podemos hacerlo así
                // El argumento lambda s -> s.charAt(0) toma cada valor String recibido y deriva la clave
                // para emparejarlo. En este caso, estamos haciendo que el primer carácter de cada
                // valor String sea la clave.
                Observable<String> planetas = Observable.just("Saturno", "Marte", "Venus");
                planetas
                        .toMap(s -> s.charAt(0))
                        .subscribe(new DemoSingleObserver());
                break;
            case 2:
                /**
                 * Si decidimos producir un valor diferente al recibido para asociarlo con
                 * la clave, podemos proporcionar un segundo argumento lambda que mapea cada
                 * valor recibido con uno diferente. Podemos, por ejemplo, mapear cada tecla
                 * de la primera letra con la longitud del String objeto recibido
                 */
                Observable.just("Saturno", "Marte", "Venus")
                        .toMap(s -> s.charAt(0), String::length)
                        .subscribe(new DemoSingleObserver());
                break;
            case 3:
                /**
                 * De forma predeterminada, toMap() utiliza la clase HashMap como implementación Map
                 * de la interfaz. También puede proporcionar un tercer argumento para especificar
                 * una Map implementación diferente . Por ejemplo, podemos proporcionar en
                 * ConcurrentHashMap lugar de HashMap como la implementación deseada de la Map interfaz
                 */
                Observable.just("Saturno", "Marte", "Venus")
                        .toMap(s -> s.charAt(0), String::length,
                                ConcurrentHashMap::new)
                        .subscribe(new DemoSingleObserver());
                break;
            case 4:
                /**
                 * Tenga en cuenta que si hay una clave que se asigna a varios valores
                 * recibidos, el último valor de esa clave reemplazará a los anteriores.
                 * Por ejemplo, hagamos que la longitud de la cadena sea la clave para
                 * cada valor recibido. Luego,  Venus será reemplazado por Marte
                 */
                Observable.just("Saturno", "Marte", "Venus")
                        .toMap(String::length)
                        .subscribe(new DemoSingleObserver());
                break;
        }
    }
}
