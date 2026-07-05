package com.controllermapper.ui;

import com.controllermapper.controller.ControllerManager;
import com.controllermapper.controller.ControllerState;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.flag.ImGuiConfigFlags;

public class ControllerApplication extends Application {

    private final ControllerManager controllerManager =
            new ControllerManager();

    @Override
    protected void configure(final Configuration config) {
        config.setTitle("ControllerMapper");
    }

    @Override
    protected void initImGui(final Configuration config) {
        super.initImGui(config);

        final ImGuiIO io = ImGui.getIO();

        io.setIniFilename(null);

        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);

        io.setConfigViewportsNoTaskBarIcon(true);
    }

    @Override
    public void process() {

        ImGui.begin("ControllerMapper");

        ImGui.text("ControllerMapper started!");

        ImGui.separator();

        ControllerState state = controllerManager.poll();

        ImGui.text(String.format("LX : %.2f", state.leftStickX));
        ImGui.text(String.format("LY : %.2f", state.leftStickY));

        ImGui.text(String.format("RX : %.2f", state.rightStickX));
        ImGui.text(String.format("RY : %.2f", state.rightStickY));

        ImGui.separator();

        ImGui.text("A  : " + state.a);
        ImGui.text("B  : " + state.b);
        ImGui.text("X  : " + state.x);
        ImGui.text("Y  : " + state.y);

        ImGui.text("LB : " + state.lb);
        ImGui.text("RB : " + state.rb);

        ImGui.text(String.format("LT : %.2f", state.leftTrigger));
        ImGui.text(String.format("RT : %.2f", state.rightTrigger));

        ImGui.end();
    }

    public static void main(final String[] args) {
        launch(new ControllerApplication());
        System.exit(0);
    }
}