package com.creativity.reactiveprogramming.currency.parallelization;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SchedulersComputation {
    public static void main(String[] args) {
        /**
         * Scheduler mantiene un número fijo de subprocesos según la disponibilidad
         * del procesador para su sesión de Java, lo que lo hace apropiado para
         * tareas computacionales. Las tareas computacionales (como matemáticas,
         * algoritmos y lógica compleja) pueden utilizar núcleos en su máxima extensión.
         * Por lo tanto, no hay ningún beneficio en tener más subprocesos de trabajo
         * que núcleos disponibles para realizar dicho trabajo, y la computation Scheduler lo asegura.
         */
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8)
                .subscribeOn(Schedulers.computation());
        /**
         * Cuando no esté seguro de cuántas tareas se ejecutarán al mismo tiempo o simplemente
         * Scheduler no esté seguro de cuál es la correcta, prefiera la computation por defecto.
         * Varios operadores y fábricas usarán la computation Scheduler de forma predeterminada a
         * menos que especifique uno diferente como argumento. Estos incluyen una o más de
         * las sobrecargas interval(), delay(), timer(), timeout(), buffer(), take(), skip(),
         * takeWhile(), skipWhile(), window(), y algunos otros.
         */
    }
}
