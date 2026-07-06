package example.controllermapper.mapping;

public class VirtualStick {

    private final float centerX;
    private final float centerY;
    private final float radius;

    public VirtualStick(float centerX, float centerY, float radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public float getX(float rx){
        return centerX +rx * radius;
    }

    public float getY(float ry){
        return centerY -ry * radius;
    }



}
