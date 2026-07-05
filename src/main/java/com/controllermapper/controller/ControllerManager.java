package com.controllermapper.controller;

import de.gurkenlabs.input4j.InputDevice;
import de.gurkenlabs.input4j.InputDevicePlugin;
import de.gurkenlabs.input4j.InputDevices;

public class ControllerManager {

    private final InputDevicePlugin plugin;
    private final InputDevice controller;

    public ControllerManager(){
        plugin= InputDevices.init();

        if(plugin.getAll().isEmpty()){
            throw new RuntimeException("No controller connected. ");
        }

        controller=plugin.getAll().iterator().next();
        System.out.println("Connected: "+controller.getName());
    }

    public InputDevice getController(){
        return controller;
    }

    public void close() throws Exception{
        plugin.close();
    }

    public ControllerState poll() {

        controller.poll();

        ControllerState state = new ControllerState();

        for (var component : controller.getComponents()) {

            float value = component.getData();

            switch (component.getId().toString()) {

                case "A" -> state.a = value > 0.5f;
                case "B" -> state.b = value > 0.5f;
                case "X" -> state.x = value > 0.5f;
                case "Y" -> state.y = value > 0.5f;

                case "LEFT_SHOULDER" -> state.lb = value > 0.5f;
                case "RIGHT_SHOULDER" -> state.rb = value > 0.5f;

                case "START" -> state.start = value > 0.5f;
                case "BACK" -> state.back = value > 0.5f;

                case "LEFT_TRIGGER" -> state.leftTrigger = value;
                case "RIGHT_TRIGGER" -> state.rightTrigger = value;

                case "LEFT_THUMB_X" -> state.leftStickX = value;
                case "LEFT_THUMB_Y" -> state.leftStickY = value;

                case "RIGHT_THUMB_X" -> state.rightStickX = value;
                case "RIGHT_THUMB_Y" -> state.rightStickY = value;

                case "DPAD_UP" -> state.dpadUp = value > 0.5f;
                case "DPAD_DOWN" -> state.dpadDown = value > 0.5f;
                case "DPAD_LEFT" -> state.dpadLeft = value > 0.5f;
                case "DPAD_RIGHT" -> state.dpadRight = value > 0.5f;

                case "LEFT_THUMB" -> state.leftThumb = value > 0.5f;
                case "RIGHT_THUMB" -> state.rightThumb = value > 0.5f;
            }
        }

        return state;
    }
}
