// Бороховский А.В., 2 курс, 10 группа.
// Лабораторная работа №4. Вариант 1.
// Тестовый файл.
package z_4;
import z_4.VectorR3;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int m = -1;
        while(m < 3) {
            System.out.print("Input array size(>=3): ");
            m = in.nextInt();
        }

        VectorR3 Array [] = new VectorR3[m];

        // Создание массива, инициализация векторов.
        for( int i = 0; i < m; i++ ){
            Random rand = new Random();
            Array[i] = new VectorR3();
            Array[i].set_First(rand.nextInt(100));
            Array[i].set_Second(rand.nextInt(100));
            Array[i].set_Third(rand.nextInt(100));
        }


        for(int i = 0; i < m - 2; i++){
            for(int j = i + 1; j < m - 1; j++){
                for(int k = j + 1 ; k < m; k++){
                        System.out.println("(" +Array[i].get_First()+"; " + Array[i].get_Second()+"; "+Array[i].get_Third()+")");
                        System.out.println("(" +Array[j].get_First()+"; " + Array[j].get_Second()+"; "+Array[j].get_Third()+") ");
                        System.out.println("(" +Array[k].get_First()+"; " + Array[k].get_Second()+"; "+Array[k].get_Third()+") ");

                    if( VectorR3.Is_Coplanar(Array[i], Array[j], Array[k]) != true ) {
                        System.out.println(" Are not coplanar.");
                    }
                    else{
                        System.out.println(" Are coplanar.");
                    }
                    System.out.println();
                }
            }
        }

        //Пример работы конструктора с параметрами.
        VectorR3 vDemo = new VectorR3(1,2,3);
        System.out.println("(" +vDemo.get_First()+"; " + vDemo.get_Second()+"; "+vDemo.get_Third()+")");
    }
}
