package com.controllermapper.ui;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.flag.ImGuiConfigFlags;

public class ControllerApplication extends Application {

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

        ImGui.text("Waiting for controller...");

        ImGui.end();
    }

    public static void main(final String[] args) {
        launch(new ControllerApplication());
        System.exit(0);
    }
}