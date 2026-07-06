package example.controllermapper.ui.panels;

import imgui.ImGui;


public class CalibrationPanel implements Panel {

    private String selectedInput = null;

    private final float[] originX = {0f};
    private final float[] originY = {0f};

    private final float[] radius = {0.30f};
    private final float[] deadzone = {0.05f};
    private final float[] sensitivity = {1.0f};

    @Override
    public void render() {

        ImGui.text("Calibration");
        ImGui.separator();

        if (selectedInput == null) {
            waitingScreen();
        } else {
            configurationScreen();
        }
    }

    private void waitingScreen() {

        ImGui.spacing();
        ImGui.spacing();

        ImGui.setWindowFontScale(1.4f);
        ImGui.text("Waiting for controller input...");
        ImGui.setWindowFontScale(1.0f);

        ImGui.spacing();

        ImGui.text("Press any button or move any stick.");
        ImGui.text("Hold an input to create a virtual stick.");

        ImGui.spacing();
        ImGui.separator();

        // Temporary
        if (ImGui.button("Simulate A")) {
            selectedInput = "A";
        }

        ImGui.sameLine();

        if (ImGui.button("Simulate Right Stick")) {
            selectedInput = "RS";
        }
    }

    private void configurationScreen() {

        ImGui.setWindowFontScale(1.2f);
        ImGui.text("Configuring : " + selectedInput);
        ImGui.setWindowFontScale(1.0f);

        ImGui.separator();
        ImGui.spacing();

        if (selectedInput.equals("RS")) {

            ImGui.beginChild("StickPreview", 0, 360, true);

            ImGui.text("Right Stick Preview");
            ImGui.separator();

            drawStickPreview();

            ImGui.text(String.format("Origin X : %.2f", originX[0]));
            ImGui.text(String.format("Origin Y : %.2f", originY[0]));
            ImGui.text(String.format("Radius : %.2f", radius[0]));
            ImGui.text(String.format("Deadzone : %.2f", deadzone[0]));
            ImGui.text(String.format("Sensitivity : %.2f", sensitivity[0]));

            ImGui.endChild();

            ImGui.sameLine();

            ImGui.beginChild("Properties", 0, 350, true);

            ImGui.text("Properties");
            ImGui.separator();

            ImGui.sliderFloat("Origin X", originX, -1f, 1f);
            ImGui.sliderFloat("Origin Y", originY, -1f, 1f);
            ImGui.sliderFloat("Radius", radius, 0.05f, 1f);
            ImGui.sliderFloat("Deadzone", deadzone, 0f, 0.5f);
            ImGui.sliderFloat("Sensitivity", sensitivity, 0.1f, 3f);
            ImGui.endChild();

        } else {

            ImGui.beginChild("ButtonConfig", 0, 350, true);

            ImGui.text("Button Mapping");
            ImGui.separator();

            ImGui.text("Drag the button position here later.");

            ImGui.endChild();
        }

        ImGui.spacing();

        if (ImGui.button("Save", 120, 35)) {

        }

        ImGui.sameLine();

        if (ImGui.button("Cancel", 120, 35)) {
            selectedInput = null;
        }
    }
    private void drawStickPreview() {

        float size = 250;

        float avail = ImGui.getContentRegionAvailX();
        ImGui.setCursorPosX(ImGui.getCursorPosX() + (avail - size) * 0.5f);

        ImGui.invisibleButton("StickPreviewCanvas", size, size);

        var drawList = ImGui.getWindowDrawList();

        float x = ImGui.getItemRectMinX();
        float y = ImGui.getItemRectMinY();

        float cx = x + size / 2;
        float cy = y + size / 2;

        float r = size / 2 - 10;

        drawList.addCircle(
                cx,
                cy,
                r,
                0xFFFFFFFF,
                64,
                2f
        );

        float dotX = cx + originX[0] * r;
        float dotY = cy + originY[0] * r;

        drawList.addCircleFilled(
                dotX,
                dotY,
                7,
                0xFF0000FF
        );

        drawList.addCircle(
                cx,
                cy,
                r * radius[0],
                0xFF00FFFF,
                64,
                2f
        );
    }


}