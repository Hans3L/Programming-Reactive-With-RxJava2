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
        Observable.range(1,999_999_999)
                .map(MyItem::new)
                .observeOn(Schedulers.io())
                .subscribe(myItem -> {
                    RxUtils.sleeps(50);
                    System.out.println("RECIBE MyItem " + myItem.id);
                });
        RxUtils.sleeps(Long.MAX_VALUE);
    }
    /*
     * Tenga en cuenta que a Flowable no está suscrito por un, Observer sino por un
     * org.reactivestreams.Subscriber: el método subscribe() de a Flowable acepta Subscriber
     */
}
