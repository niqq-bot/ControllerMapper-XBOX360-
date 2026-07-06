package example.controllermapper.ui;

import example.controllermapper.controller.ControllerManager;
import example.controllermapper.controller.ControllerState;
import example.controllermapper.ui.panels.*;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImInt;

public class ControllerApplication extends Application {

    private final ControllerManager controllerManager = new ControllerManager();
    private final Panel[] panels = {
            new ControllerPanel(controllerManager),
            new CalibrationPanel(),
            new ProfilePanel(),
            new TouchPanel(),
            new SettingsPanel()
    };

    private final String[] panelNames = {
            "Controller",
            "Calibration",
            "Profiles",
            "Touch",
            "Settings"
    };

    private final ImInt selectedPanel = new ImInt(0);

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


        renderMenuBar();

        ImGui.setNextWindowPos(0, 18);
        ImGui.setNextWindowSize(
                ImGui.getIO().getDisplaySizeX(),
                ImGui.getIO().getDisplaySizeY() - 18
        );

        ImGui.begin(
                "ControllerMapper",
                ImGuiWindowFlags.NoResize
                        | ImGuiWindowFlags.NoMove
                        | ImGuiWindowFlags.NoCollapse
        );

        ImGui.beginChild("Sidebar", 180, 0, true);

            ImGui.text("Navigation");
            ImGui.separator();

            for (int i = 0; i < panelNames.length; i++) {
                if (ImGui.selectable(panelNames[i], selectedPanel.get() == i)) {
                    selectedPanel.set(i);
                }
            }

            ImGui.separator();
            ImGui.text("Status");
            ImGui.text("Connected");

        ImGui.endChild();

        ImGui.sameLine();

        ImGui.beginChild("Content", 0, 0, true);

            panels[selectedPanel.get()].render();

        ImGui.endChild();

        ImGui.separator();

        ImGui.end();
    }

    private void renderMenuBar() {

        if (ImGui.beginMainMenuBar()) {

            if (ImGui.beginMenu("File")) {
                ImGui.menuItem("Open Profile");
                ImGui.menuItem("Save Profile");
                ImGui.separator();
                ImGui.menuItem("Exit");
                ImGui.endMenu();
            }

            if (ImGui.beginMenu("View")) {
                ImGui.menuItem("Controller");
                ImGui.menuItem("Calibration");
                ImGui.menuItem("Profiles");
                ImGui.menuItem("Touch");
                ImGui.menuItem("Settings");
                ImGui.endMenu();
            }

            if (ImGui.beginMenu("Help")) {
                ImGui.menuItem("About");
                ImGui.endMenu();
            }

            ImGui.endMainMenuBar();
        }
    }

    public static void main(final String[] args) {
        launch(new ControllerApplication());
        System.exit(0);
    }
}