package org.ivan.Point;

public class Point3D extends Point {
    private final double z;

    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return String.format("{%.2f;%.2f;%.2f}", getX(), getY(), z);
    }
}