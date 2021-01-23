package com.creativity.reactiveprogramming.observables;

import com.creativity.reactiveprogramming.observer.DemoObserver;
import io.reactivex.Observable;

import java.util.concurrent.Callable;

public class ObservableUsingFrom {
    //from: convertir varios otros objetos y tipos de datos en Observables
    public static String doSomething(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Hola";
    }
    public static void main(String[] args) {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return doSomething();
            }
        };
        Observable.fromCallable(callable).subscribe(new DemoObserver());
    }
}
