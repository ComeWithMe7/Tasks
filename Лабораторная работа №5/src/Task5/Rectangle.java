package Task5;

import java.util.*;

public class Rectangle extends Figure implements Comparable<Rectangle>, Iterator<Object>{
    double height;
    double width;

    private int iter = 0;

    // Функция, проверяющая, образуют ли точки прямоугольник.
    /* Для корректной работы необходимо вводить точки в необходимой последовательности,например:
    * четырехугольник ABCD исследуется. Вводим в конструктор точки в последовательности A, B, C, D.*/
    boolean isRectangle(Point A, Point B, Point C, Point D) {
        double distanceAB = Point.distance(A, B);
        double distanceCD = Point.distance(C, D);
        double distanceBC = Point.distance(B, C);
        double distanceDA = Point.distance(D, A);
        if( (distanceAB != distanceCD) || (distanceBC!=distanceDA)) {
            return false;
        }

        // Определение факта ортогональности.
        double x_AB = B.getX() - A.getX();
        double y_AB = B.getY() - A.getY();

        double x_BC = C.getX() - B.getX();
        double y_BC = C.getY() - B.getY();

        double orthogonalMarker = x_AB*x_BC + y_AB*y_BC;
//        if(orthogonalMarker != 0){
//            return false;
//        }
//        else {
//            return true;
//        }
        return orthogonalMarker == 0;
    }

    // Конструктор.
    /* Для корректной работы необходимо вводить точки в необходимой последовательности,например:
     * четырехугольник ABCD исследуется. Вводим в конструктор точки в последовательности A, B, C, D.*/
    Rectangle(Point A, Point B, Point C, Point D) throws Exception
    {
        if(isRectangle(A, B, C, D)) {
            height = Point.distance(A,B);
            width = Point.distance(B,C);
        }
        else {
            throw new Exception("Current Points A, B, C, D are not create rectangle.");
        }
    }

    Rectangle(String str) throws Exception
    {
        if ( str == null ) {
            throw new Exception( "Null pointer passed for str" );
        }

        String [] parts = str.split("\\s");
        double bufferHeight = Double.parseDouble(parts[0]);
        double bufferWidth = Double.parseDouble(parts[1]);
        if((bufferHeight > 0) || (bufferWidth >0)){
            height = bufferHeight;
            width = bufferWidth;
        }
    }

    // Методы абстрактного класса Figure.
    double perimeter(){
        return 2*(width+height);
    }

    double area() {
        return height*width;
    }

    // Всякие
    static int sortMarker = 0;
    public void setMarker(int inMarker) {
        if((inMarker == 0) || (inMarker == 1)) {
            sortMarker = inMarker;
        }
    }
    @Override
    public int compareTo(Rectangle rect_1) {
        switch (sortMarker) {
            case 0:
                if(height > rect_1.height) {
                    return 1;
                }
                else {
                    if ( height < rect_1.height){
                        return -1;
                    }
                    else {
                        return 0;
                    }
                }

            case 1:
                if(width > rect_1.width) {
                    return 1;
                }
                else {
                    if ( width < rect_1.width){
                        return -1;
                    }
                    else{
                        return 0;
                    }
                }
            default: return -1;
        }
    }

    @Override
    public boolean hasNext() {
        return iter <= 2;
    }

    @Override
    public Object next() throws IndexOutOfBoundsException
    {
        if (iter > 2)
            throw new IndexOutOfBoundsException("Outcome of borders.");
        switch (iter)
        {
            case 0:
                iter++;
                return height;
            case 1:
                iter++;
                return width;
            default:
                return 0;
        }
    }

    @Override
    public String toString()
    {
        return (" Rectangle height: " + Double.toString(height)+"\nRectangle width: " + Double.toString(width)+"\n");
    }
}
