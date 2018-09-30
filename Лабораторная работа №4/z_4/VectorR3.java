// Бороховский А.В., 2 курс, 10 группа.
// Лабораторная работа №4. Вариант 1.
// Файл, содержащий необходимый к построению класс.

package z_4;

public class VectorR3 {
    double first;
    double second;
    double third;

    //Конструктор без параметров
    public VectorR3(){
        first = 0;
        second = 0;
        third = 0;
    }

    // Конструктор с параметрами.
    public VectorR3(int in_first, int in_second, int in_third){
        first = (double) in_first;
        second = (double) in_second;
        third = (double) in_third;
    }


    // Set-методы
    public void set_First(int in){
        first = in;
    }

    public void set_Second(int in){
        second = in;
    }

    public void set_Third(int in){
        third = in;
    }


    // Get-методы
    public double get_First(){
        return this.first;
    }

    public double get_Second(){
        return this.second;
    }

    public double get_Third(){
        return this.third;
    }

    //Метод определения компланарности.
    public static boolean Is_Coplanar(VectorR3 V1, VectorR3 V2, VectorR3 V3){
        double determinant = (V1.first*V2.second*V3.third) +
                + (V1.third*V2.first*V3.second) + (V1.second*V2.third*V3.first) -
                (V1.third*V2.second*V3.first) - (V1.first*V2.third*V3.second) - (V1.second*V2.first*V3.third);

        if( determinant == 0 )
            return true;
        else
            return false;
    }
}
