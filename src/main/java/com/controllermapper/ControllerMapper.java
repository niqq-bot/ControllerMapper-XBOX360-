
package com.controllermapper;

import com.controllermapper.controller.ControllerManager;
import com.controllermapper.controller.ControllerState;
import com.controllermapper.mapping.VirtualStick;

public class ControllerMapper {

    public static void main(String[] args) throws Exception {

        ControllerManager manager = new ControllerManager();
        VirtualStick stick =new VirtualStick(1600,800,120);
        while (true) {

            ControllerState state = manager.poll();

            float x = stick.getX(state.rightStickX);
            float y = stick.getY(state.rightStickY);

            System.out.printf(
                    "RS(%.2f,%.2f) -> Touch(%.2f,%.2f)%n",
                    state.rightStickX,
                    state.rightStickY,
                    x,
                    y
            );

            Thread.sleep(5000);
        }
    }

}
