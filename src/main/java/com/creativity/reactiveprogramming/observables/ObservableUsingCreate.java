package com.creativity.reactiveprogramming.observables;

import com.creativity.reactiveprogramming.models.Shape;
import com.creativity.reactiveprogramming.observer.DemoObserver;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import java.util.ArrayList;
import java.util.List;

public class ObservableUsingCreate {

    public static void main(String[] args) {
        List<Shape> shapes = RxUtils.shapes(15);
        //crea un Observable desde cero llamando a los métodos del observador mediante programación
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> observableEmitter) throws Exception {
                try {
                    //shapes.forEach((x)-> observableEmitter.onNext(x.getColor()+ " " + x.getShape()) );
                    shapes.forEach(observableEmitter::onNext);
                } catch (Exception e) {
                    observableEmitter.onError(e);
                }
                observableEmitter.onComplete();
            }
        }).subscribe(new DemoObserver());
    }
}
