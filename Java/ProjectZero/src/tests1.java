/**
 * Created by jufey on 02/09/15.
 */

import com.pi4j.io.gpio.*;


public class tests1 {

    public static void main(String[] args) throws InterruptedException {
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinPwmOutput pin = gpio.provisionPwmOutputPin(RaspiPin.GPIO_01, "MyLED");
//        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);
        pin.setShutdownOptions(true, PinState.LOW);
//
        pin.setPwm(0);
        Thread.sleep(5000);
        pin.setPwm(20);
        Thread.sleep(5000);
        pin.setPwm(40);
        Thread.sleep(5000);
        pin.setPwm(80);
        Thread.sleep(5000);
        pin.setPwm(100);
        Thread.sleep(5000);
        pin.setPwm(0);
        Thread.sleep(5000);

//        System.out.println("--> GPIO state should be: ON");
//        Thread.sleep(5000);
//
//        pin.low();
//        System.out.println("--> GPIO state should be: OFF");
//
//        Thread.sleep(5000);
//        pin.high();
//        System.out.println("--> GPIO state should be: ON");
//
//        Thread.sleep(5000);
//
        gpio.shutdown();

    }

}
