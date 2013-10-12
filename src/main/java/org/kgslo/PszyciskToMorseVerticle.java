package org.kgslo;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class PszyciskToMorseVerticle extends Verticle {

    private static final long DOT_MAX_TIME = 300;
    private static final long PAUSE_MIN_TIME = 700;
    private long lastHigh = 0;
    private long lastLow = System.currentTimeMillis();

    public void start() {
        vertx.eventBus().registerHandler("pszycisk", new Handler<Message<Boolean>>() {
            @Override
            public void handle(Message<Boolean> isHigh) {
                boolean isHighBool = isHigh.body();
                //System.out.println("stan = " + isHighBool);

                if (pszyciskReleased(isHighBool)) {
                    handlePszyciskReleased();
                } else if (pszyciskPressed(isHighBool)) {
                    handlePszyciskPressed();
                }
            }

            private void handlePszyciskReleased() {
                long highTime = System.currentTimeMillis() - lastHigh;

                if (highTime < DOT_MAX_TIME) {
                    sendDot();
                } else {
                    sendDash();
                }
                lastHigh = 0;
                lastLow = System.currentTimeMillis();
            }

            private void handlePszyciskPressed() {
                long lowTime = System.currentTimeMillis() - lastLow;
                if (lowTime >= PAUSE_MIN_TIME) {
                    sendPause();
                }
                lastLow = 0;
                lastHigh = System.currentTimeMillis();
            }

            private void sendPause() {
                sendMorseChar("pause");
            }

            private void sendDot() {
                sendMorseChar("dot");
            }

            private void sendDash() {
                sendMorseChar("dash");
            }

            private EventBus sendMorseChar(String morseChar) {
                System.out.println("sending morse char " + morseChar);
                return vertx.eventBus().publish("morse-chars", morseChar);
            }

            private boolean pszyciskReleased(boolean highBool) {
                return !highBool && lastHigh != 0;
            }

            private boolean pszyciskPressed(boolean highBool) {
                return highBool && lastLow != 0;
            }
        });


    }

}
