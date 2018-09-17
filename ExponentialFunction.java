// Лабораторная работа №1
// Бороховский Андрей,2 курс, 10 группа, подгруппа б.
// Вариант 1.

import java.util.Scanner;

public class ExponentialFunction {

public static void main(String[] args)
{
	System.out.println("\n\n");
	Scanner in = new Scanner(System.in);
	
	//System.out.println("Input number of aftercomma symbols(k>1): ");
	//int k=1;
	int k = Integer.parseInt(args[0]);
	while(k<2)
	{
		System.out.println("Invalid value(k>1). Repeat input: ");
		k = in.nextInt();
	}
	System.out.println("Value k: " + k);

	double eps = Math.pow(10,-k);
	System.out.println("\nEps value: "+eps);

	
	//System.out.println("\nInput x: ");
	//int x=in.nextInt();
	double x = Double.parseDouble(args[1]);
	System.out.println("Value x: " + x);
	
	double sum_ach = 1, prev = 1, current;
	int iter = 1;
	boolean flag = true;
	
	while(true)
	{
		current = prev*x/iter;
		
		if(Math.abs(current)<eps) {
			break;
		}
		
		sum_ach = sum_ach+current;
		prev = current;
		iter = iter+1;
	}
	
	double sum_real = Math.exp(x);
	System.out.printf("\nAchieved |%20."+k+"f\n", sum_ach);
	System.out.printf("Real     |%20."+k+"f\n", sum_real);	
	System.out.printf("\nDistance |%-30.15f\n ", Math.abs(sum_real-sum_ach));
	
	in.close();
	}
}