package com.creativity.reactiveprogramming.disposable;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;

import java.util.concurrent.TimeUnit;

public class HandlingDisposable {
    public static void main(String[] args) {
        /*
         * Pase el objeto ResourceObserver a subscribeWith () en lugar
         * de subscribe (), y obtendrá el desechable predeterminado devuelto
         */
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
        ResourceObserver<Long> myObserver = new ResourceObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
                System.out.println(aLong);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done!");
            }
        };
        //capturamos el Disposable
        Disposable disposable = source.subscribeWith(myObserver);
    }
}
