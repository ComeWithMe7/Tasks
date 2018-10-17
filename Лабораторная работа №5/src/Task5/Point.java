package Task5;
import java.util.*;

import static java.lang.Math.*;

public class Point {
    private double x;
    private double y;

    // Конструктор с параметрами.
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Get-методы
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Set-методы
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Определение длины между точками.
    public static double distance(Point p1, Point p2){
        double xDistance = p1.x - p2.x;
        double yDistance = p1.y - p2.y;
        double distance = Math.sqrt(xDistance*xDistance + yDistance*yDistance);
        return distance;
    }
}