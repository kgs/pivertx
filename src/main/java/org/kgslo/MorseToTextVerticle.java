package org.kgslo;

import org.crumbleworks.mcdonnough.morsecoder.Decoder;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class MorseToTextVerticle extends Verticle {

    private StringBuilder sb = new StringBuilder();
    private final Decoder morseDecoder = new Decoder();

    public void start() {
        vertx.eventBus().registerHandler("morse-chars", new Handler<Message<String>>() {
            @Override
            public void handle(Message<String> morseChar) {
                System.out.println("morseChar=" + morseChar.body());
                switch (morseChar.body()) {
                    case "dot":
                        sb.append(".");
                        break;
                    case "dash":
                        sb.append("-");
                        break;
                    case "pause":
                        System.out.println(morseDecoder.decode(sb.toString()));
                        sb.setLength(0);
                        break;
                }
            }
        });
    }
}
