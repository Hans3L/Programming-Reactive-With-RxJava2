package com.creativity.reactiveprogramming.operator.collections;

import com.creativity.reactiveprogramming.single.DemoSingleObserver;
import com.google.common.collect.ImmutableList;
import io.reactivex.Observable;

import java.util.HashSet;

public class ExampleCollect {
    /**
     * Cuando ninguno de los operadores de recolección puede hacer
     * lo que necesita, siempre puede utilizar el operador collect()
     * para especificar un tipo diferente para recolectar elementos.
     */
    private static int op;

    public static void main(String[] args) {
        op = 2;
        switch (op) {
            case 1:
                /**
                 * Digamos que necesita recopilar valores String en una
                 * implementación Set<String>. Para lograr eso, puede
                 * especificar el primer argumento, la función que produce
                 * un valor inicial de la implementación Set<String> que
                 * le gustaría usar, y el segundo argumento, la función que
                 * va a recopilar los valores (lo que necesite recopilar) en
                 * ese implementación Set<String> que ha elegido.
                 */
                Observable.just("Alpha", "Beta", "Gamma", "Beta")
                        //.collect(HashSet<String>::new, HashSet::add)
                        .collect(HashSet<String>::new, (strings, s) -> strings.add(s))
                        .subscribe(new DemoSingleObserver());
                /**
                 * El operador collect() en nuestro ejemplo ahora emite un único
                 * HashSet<String> objeto que contiene todos los valores emitidos,
                 * excepto los duplicados (tenga en cuenta que Beta fue emitido dos
                 * veces por la fuente Observable); esa es la naturaleza de la clase HashSet.
                 * Cuando necesite recopilar valores en un objeto mutable y necesite una nueva
                 * semilla de objeto mutable cada vez, utilice collect() en lugar del operador reduce().
                 */
                break;
            case 2:
                /**
                 * Para crear un ImmutableList, debe llamar a su fábrica builder() para obtener un
                 * ImmutableList.Builder<T>. Luego llama a su método add() para colocar elementos
                 * en el constructor, seguido de una llamada a build(), que devuelve un final sellado
                 * ImmutableList<T>que no se puede modificar.
                 * Para lograr eso, puede proporcionar un ImmutableList.Builder<T> para su primer
                 * argumento lambda y luego agregar cada elemento a través de su add()método en el
                 * segundo argumento. Esto se emitirá ImmutableList.Builder<T> una vez que esté
                 * completamente poblado, y puede transformarlo usando el operador map() y su método
                 * build(), que produce el objeto ImmutableList<T>.
                 */
                Observable.just("Alpha","Beta","Gamma")
                        .collect(ImmutableList::builder,ImmutableList.Builder::add)
                        .map(ImmutableList.Builder::build)
                        .subscribe(objects -> System.out.println("RECIBIDO " + objects));
                break;
        }
    }
}
