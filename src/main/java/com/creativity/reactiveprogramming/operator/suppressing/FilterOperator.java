package com.creativity.reactiveprogramming.operator.suppressing;

import com.creativity.reactiveprogramming.models.Shape;
import com.creativity.reactiveprogramming.utils.RxUtils;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class FilterOperator {
    public static void main(String[] args) {
        List<Shape> shapes = RxUtils.shapes(10);
        for (Shape shape : shapes) {
            log.info("shape -> {} " , shape);
        }
        Observable.fromIterable(shapes)
                .filter(shape -> shape.getColor().equals("blue"))
                .subscribe(shape -> System.out.println("RECIBIDO: " + shape));
    }
}
