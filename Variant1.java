// В консоле вводите числа, руководствуясь следющей последовательностью: размер матрицы, номер столбца(строки), значение флага(0 — сортировка строк, 1 — сортировка столбцов).
// Бороховский Андрей, 2 курс, 10 группа.
// Лабораторная работа № 3, вариант 1.

import java.util.Random;
import java.util.Scanner;

public class Variant1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //System.out.print("Enter n: ");
        //int n = in.nextInt();

        int n = Integer.parseInt(args[0]);
        if (n <= 1) {
            System.err.println("Invalid n value (require: n > 1");
            System.exit(1);
        }

        int k = Integer.parseInt(args[1]);
        //int k = in.nextInt();
        if ((k <= 0) || (k > n)) {
            System.err.println("Invalid k value (require: 0 < k <= n");
            System.exit(1);
        }
        k = k - 1;

        int[][] matrix = new int[n][n];
        Random rnd = new Random();
        rnd.setSeed( System.currentTimeMillis() );

        // Заполнение матрицы.
        int indexI = 0, indexJ = 0;
        int min = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = rnd.nextInt();
                matrix[i][j] = temp % (n + 1);
                if (matrix[i][j] <= min) {
                    min = matrix[i][j];
                    indexI = i;
                    indexJ = j;
                }
            }
        }

        // Вывод исходной матрицы.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
				if(matrix[i][j] < 0){
					System.out.print(" " + matrix[i][j] + "  ");
				}
				else{
					System.out.print("  " + matrix[i][j] + "  ");
				}
            }
            System.out.print('\n');
        }
        System.out.println('\n');

		
        int buffer;
		
		// Транспонирование матрицы.
        int flag = Integer.parseInt( args[2] );
        //boolean flag = true;
        if( flag == 1){
            for(int i = 0; i < n; i++){
                for(int j = i+1; j < n; j++){
                    buffer = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = buffer;
                }
            }
        }

        //Упорядочивание строк(стобцов).
        for(int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (matrix[j][k] > matrix[j+1][k]) {
                    for (int cnt = 0; cnt < n; cnt++) {
                        buffer = matrix[j][cnt];
                        matrix[j][cnt] = matrix[j+1][cnt];
                        matrix[j+1][cnt] = buffer;
                    }
                }
            }
        }

        // Возвращение исходного расположения элементов(транспонирование).
        if( flag == 1) {
            for(int i = 0; i < n; i++) {
                for(int j = i+1; j < n; j++) {
                    buffer = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = buffer;
                }
            }
        }

        // Вывод конечной матрицы.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] < 0){
					System.out.print(" " + matrix[i][j] + "  ");
				}
				else{
					System.out.print("  " + matrix[i][j] + "  ");
				}
            }
            System.out.print('\n');
        }
            in.close();
    }
}
