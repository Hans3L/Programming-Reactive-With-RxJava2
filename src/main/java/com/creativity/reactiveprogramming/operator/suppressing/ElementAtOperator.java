package com.creativity.reactiveprogramming.operator.suppressing;

import com.creativity.reactiveprogramming.maybe.DemoMaybe;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ElementAtOperator {
    /*
     * Una vez que se encuentra y se emite el artículo llama a onSuccess, onComplete() se
     * llama y se elimina la suscripción si no encuentra ningun indice dentro de la fuente.
     */
    public static void main(String[] args) {
        log.info("ElementAt Operator");
        Observable.fromIterable(RxUtils.positiveNumbers(10))
                .elementAt(100)
                .subscribe(new DemoMaybe());
    }
}
