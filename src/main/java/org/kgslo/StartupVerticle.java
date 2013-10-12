package org.kgslo;

import org.vertx.java.platform.Verticle;

public class StartupVerticle extends Verticle {

    public void start() {

        //getContainer().deployVerticle("org.kgslo.InVerticle");
        getContainer().deployVerticle("org.kgslo.PszyciskToMorseVerticle");
        getContainer().deployVerticle("org.kgslo.MorseToTextVerticle");

    }
}
