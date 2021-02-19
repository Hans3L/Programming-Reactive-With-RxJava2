package com.creativity.reactiveprogramming.currency.parallelization;

public class SchedulersInputOutput {
    public static void main(String[] args) {
        /**
         * Las tareas de E/S, como leer y escribir en bases de datos, solicitudes web y
         * almacenamiento en disco, utilizan poca potencia de CPU y, a menudo, tienen tiempo
         * de inactividad esperando a que se envíen o reciban los datos. Esto permite que
         * los subprocesos se creen de manera más generosa y Schedulers.io() es apropiado para
         * esto. Mantiene tantos subprocesos como tareas y aumenta dinámicamente el número de
         * subprocesos, los almacena en caché y descarta los subprocesos cuando no son necesarios.
         *
         * Por ejemplo, puede utilizar Schedulers.io()para realizar operaciones SQL utilizando RxJava-JDBC
         */
        // Database db = Database.from(conn);
        // Observable<String> customerNames =
        // db.select("select name from customer")
           //.getAs(String.class)
           //.subscribeOn(Schedulers.io());
    }
    /**
     * ¡Pero tienes que tener cuidado! Como regla general, suponga que cada suscripción da
     * como resultado un nuevo hilo.
     */
}
