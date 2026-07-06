package example.controllermapper.ui.panels;

import imgui.ImGui;

public class TouchPanel implements Panel {

    @Override
    public void render() {

        ImGui.text("Controller");

    }
}