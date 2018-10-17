package Task5;

import java.util.Arrays;

public class Test {
    public static void main(String args[]){
        try {
            Point A = new Point(0, 0);
            Point B = new Point(0, 2);
            Point C = new Point(3, 2);
            Point D = new Point(3, 0);

            Rectangle rect_1 = new Rectangle(A, B, C, D);
            System.out.println(rect_1.height);
            System.out.println(rect_1.width);
            System.out.println(rect_1.area());
            System.out.println(rect_1.perimeter());

            Point A_1 = new Point(0, 0);
            Point B_1 = new Point(0, 5);
            Point C_1 = new Point(2, 5);
            Point D_1 = new Point(2, 0);
            Rectangle rect_2 = new Rectangle(A_1, B_1, C_1, D_1);

            System.out.println(rect_2.height);
            System.out.println(rect_2.width);
            System.out.println(rect_2.area());
            System.out.println(rect_2.perimeter());

            System.out.println(rect_2.compareTo(rect_1));

            Rectangle rect_3 = new Rectangle("21 5");
            Rectangle rect_4 = new Rectangle("19 19");
            Rectangle rect_5 = new Rectangle("98 20");
            System.out.println(rect_3.height);
            System.out.println(rect_3.width);

            Rectangle [] sortArray = new Rectangle[5];
            sortArray[0] = rect_1;
            sortArray[1] = rect_2;
            sortArray[2] = rect_3;
            sortArray[3] = rect_4;
            sortArray[4] = rect_5;

            Arrays.sort(sortArray);
            for(int i=0; i<5; i++){
                System.out.println(sortArray[i]);
            }

           // System.out.println(rect_1.toString());
        }
        catch(Exception excp){
            excp.printStackTrace();
        }
    }
}
