package com.creativity.reactiveprogramming.observables;

import com.creativity.reactiveprogramming.observer.DemoObserver;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObservableUsingJust {

    public static void main(String[] args) {
        // ¿Cuántos elementos al máximo que se pueden observar usando solo se pueden crear?
        // 10
        // El operador Just convierte un elemento en un Observable que emite ese elemento.
        //convertir un objeto o un conjunto de objetos en un Observable que emite ese o esos objetos
        Observable.just("a","b","c","d","e","f","g","h","i","j")
        .subscribe(new DemoObserver());

        Observable just = Observable.just("a","b","c","d","e","f","g","h","i","j");
        System.out.println("Clase que proviene : " + just.getClass());
        System.out.println("Instancia de : " + just instanceof String);
    }
}
