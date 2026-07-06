package example.controllermapper.ui.panels;

import example.controllermapper.controller.ControllerManager;
import example.controllermapper.controller.ControllerState;
import imgui.ImGui;

public class ControllerPanel implements Panel {

    private final ControllerManager controllerManager;

    public ControllerPanel(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }

    @Override
    public void render() {

        if (!controllerManager.hasController()) {
            ImGui.textColored(1, 0, 0, 1, "No controller connected.");
            return;
        }

        ControllerState state = controllerManager.poll();

        drawHeader();

        ImGui.spacing();

        // Two columns
        ImGui.beginTable("ControllerLayout", 2);

        ImGui.tableNextColumn();
        drawLeftStick(state);
        ImGui.spacing();
        drawButtons(state);

        ImGui.tableNextColumn();
        drawRightStick(state);
        ImGui.spacing();
        drawDPad(state);

        ImGui.endTable();

        ImGui.spacing();

        drawControls(state);

        ImGui.spacing();

        drawTriggers(state);
    }

    private void drawSticks(ControllerState state) {

        ImGui.text("Left Stick");
        ImGui.text(String.format(
                "X: %.2f   Y: %.2f",
                state.leftStickX,
                state.leftStickY));

        ImGui.spacing();

        ImGui.text("Right Stick");
        ImGui.text(String.format(
                "X: %.2f   Y: %.2f",
                state.rightStickX,
                state.rightStickY));
    }

    private void drawTriggers(ControllerState state) {

        ImGui.beginChild("Triggers", 0, 120, true);

        ImGui.text("Triggers");
        ImGui.separator();

        drawTrigger("LT", state.leftTrigger);
        drawTrigger("RT", state.rightTrigger);

        ImGui.endChild();
    }
    private void drawTrigger(String label, float value) {

        float base = 0.25f;

        float r = base + value * 0.75f;
        float g = base * (1f - value);
        float b = base * (1f - value);

        ImGui.text(label);
        ImGui.sameLine(35);

        ImGui.pushStyleColor(imgui.flag.ImGuiCol.PlotHistogram, r, g, b, 1f);

        ImGui.progressBar(
                value,
                -1,
                22,
                String.format("%.0f%%", value * 100)
        );

        ImGui.popStyleColor();
    }

    private void drawButtons(ControllerState state) {

        ImGui.beginChild("FaceButtons", 0, 170, true);

        ImGui.text("Face Buttons");
        ImGui.separator();

        // Y
        ImGui.setCursorPosX(75);
        drawButton("Y", state.y);

        // X B
        ImGui.setCursorPosX(40);
        drawButton("X", state.x);

        ImGui.sameLine();

        ImGui.setCursorPosX(115);
        drawButton("B", state.b);

        // A
        ImGui.setCursorPosX(75);
        drawButton("A", state.a);

        ImGui.endChild();
    }
    private void drawButton(String label, boolean pressed) {

        float r = 1.0f;
        float g = pressed ? 0.2f : 0.85f;
        float b = pressed ? 0.2f : 0.85f;

        ImGui.pushStyleColor(imgui.flag.ImGuiCol.Button, r, g, b, 1.0f);
        ImGui.pushStyleColor(imgui.flag.ImGuiCol.ButtonHovered, r, g, b, 1.0f);
        ImGui.pushStyleColor(imgui.flag.ImGuiCol.ButtonActive, r, g, b, 1.0f);

        ImGui.pushStyleColor(
                imgui.flag.ImGuiCol.Text,
                pressed ? 1.0f : 0.0f,
                pressed ? 1.0f : 0.0f,
                pressed ? 1.0f : 0.0f,
                1.0f
        );

        ImGui.button(label, 45, 45);

        ImGui.popStyleColor(4);
    }
    private void drawDPad(ControllerState state) {

        ImGui.beginChild("DPad", 0, 250, true);

        ImGui.text("D-Pad");
        ImGui.separator();

        ImGui.setCursorPosX(70);
        drawButton("U", state.dpadUp);

        drawButton("L", state.dpadLeft);
        ImGui.sameLine();
        ImGui.setCursorPosX(120);
        drawButton("R", state.dpadRight);

        ImGui.setCursorPosX(70);
        drawButton("D", state.dpadDown);

        ImGui.endChild();
    }
    private void drawHeader() {

        ImGui.beginChild("Header", 0, 70, true);

        ImGui.setWindowFontScale(1.15f);
        ImGui.text(controllerManager.getController().getName());
        ImGui.setWindowFontScale(1.0f);

        ImGui.textColored(0.2f, 1f, 0.2f, 1f, "Connected");


        ImGui.endChild();
    }
    private void drawLeftStick(ControllerState state) {

        ImGui.beginChild("LeftStick", 0, 170, true);

        ImGui.text("Left Stick");
        ImGui.separator();

        ImGui.text(String.format("X : %.2f", state.leftStickX));
        ImGui.text(String.format("Y : %.2f", state.leftStickY));

        ImGui.endChild();
    }
    private void drawRightStick(ControllerState state) {

        ImGui.beginChild("RightStick", 0, 170, true);

        ImGui.text("Right Stick");
        ImGui.separator();

        ImGui.text(String.format("X : %.2f", state.rightStickX));
        ImGui.text(String.format("Y : %.2f", state.rightStickY));

        ImGui.endChild();
    }

    private void drawControls(ControllerState state) {

        ImGui.beginChild("Controls", 0, 65, true);

        drawSmallButton("LB", state.lb);
        ImGui.sameLine(90);

        drawSmallButton("RB", state.rb);
        ImGui.sameLine(180);

        drawSmallButton("Back", state.back);
        ImGui.sameLine();

        drawSmallButton("Start", state.start);
        ImGui.sameLine();

        drawSmallButton("L3", state.leftThumb);
        ImGui.sameLine();

        drawSmallButton("R3", state.rightThumb);

        ImGui.endChild();
    }

    private void drawSmallButton(String label, boolean pressed) {

        float r = pressed ? 1f : 0.75f;
        float g = pressed ? 0.2f : 0.75f;
        float b = pressed ? 0.2f : 0.75f;

        ImGui.pushStyleColor(imgui.flag.ImGuiCol.Button, r, g, b, 1f);
        ImGui.pushStyleColor(imgui.flag.ImGuiCol.ButtonHovered, r, g, b, 1f);
        ImGui.pushStyleColor(imgui.flag.ImGuiCol.ButtonActive, r, g, b, 1f);

        ImGui.pushStyleColor(
                imgui.flag.ImGuiCol.Text,
                pressed ? 1f : 0f,
                pressed ? 1f : 0f,
                pressed ? 1f : 0f,
                1f);

        float width = Math.max(55, ImGui.calcTextSize(label).x + 18);

        ImGui.button(label, width, 28);

        ImGui.popStyleColor(4);
    }


}