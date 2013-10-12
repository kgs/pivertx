package org.kgslo;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.vertx.java.platform.Verticle;

public class InVerticle extends Verticle {

    public void start() {

        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00,
                "pszycisk",
                PinPullResistance.PULL_DOWN);

        myButton.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.println("zmiana stanu: " + event.getPin() + " = "
                        + event.getState());

                vertx.eventBus().send("pszycisk", event.getState().isHigh());
            }
        });

    }

}
