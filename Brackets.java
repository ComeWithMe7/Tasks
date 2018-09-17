// Бороховский Андрей, 2 курс, 10 группа, подгруппа Б.
// Вариант 1, лабораторная работа № 2.
// Использую только круглые скобки, ибо в условии не оговорено о них ничего, а спросить я забыл об оныхю :)

import java.io.FileReader;
import java.util.Scanner;

public class Brackets{
    public static void main( String[] args) {
        try {
            Scanner in = new Scanner(new FileReader("D://Лабораторные работы по программированию//3-ий семестр//Task_2//Test.txt"));

            while (in.hasNextLine()) {
                String str = in.nextLine();
                int border = str.length();
                int cntOpen = 0, cntClose = 0;

                for (int i = 0; i < border; i++) {

                    if (str.charAt(i) == '(') {
                        cntOpen++;
                    }

                    if (str.charAt(i) == ')') {
                        cntClose++;
                    }
                }

                if ((cntClose == 0) || (cntOpen == 0)) {
                    System.out.println("Result  | " + str);
                    continue;
                }
                cntClose = 0;
                cntOpen = 0;

                int it = 0;
                int firstOpen = -1;
                int firstClose = -1;
                int firstOpenAfter = -1;
                int firstCloseAfter = -1;

                while (it < border) {

                    firstOpen = str.indexOf('(', it);
                    if (firstOpen != -1) {
                        firstClose = str.indexOf(')', firstOpen);
                        if (firstClose != -1) {

                            for (int i = firstOpen + 1; i < firstClose; i++) {
                                if (str.charAt(i) == '(') {
                                    cntOpen++;
                                }
                            }

                            if ((cntOpen != 0)) {
                                if (border - 1 != firstClose) {
                                    firstOpenAfter = str.indexOf('(', firstClose);
                                    firstCloseAfter = str.indexOf(')', firstClose + 1);
                                    //if( firstCloseAfter == -1) {
                                      //  if(firstOpenAfter != -1)
                                    //}

                                    if (((firstOpenAfter < firstCloseAfter) && (firstOpenAfter != -1))
                                            || ((firstOpenAfter != -1) && (firstCloseAfter == -1))) {
                                        if (firstOpen == 0) {
                                            str = str.substring(firstClose + 1, border);
                                            it = -1;
                                        } else {
                                            String buffer = str.substring(firstClose + 1, border);
                                            str = str.substring(0, firstOpen);
                                            str = str + buffer;
                                            it = firstOpen - 1;
                                        }
                                        border = str.length();
                                    } else {
                                        it = firstCloseAfter;
                                        firstOpen = -1;
                                        firstClose = -1;
                                        firstCloseAfter = -1;
                                        firstOpenAfter = -1;
                                    }

                                } else {// случай, когда закрывающая скобка — последний элемент
                                    if (firstOpen == 0) {
                                        str = "";
                                    } else {
                                        str = str.substring(0, firstOpen);
                                    }
                                    break;
                                }

                            } else {
                                if( firstClose != border - 1) {
                                    if (firstOpen == 0) {
                                        str = str.substring(firstClose + 1, border);
                                        it = -1;
                                    } else {
                                        String buffer = str.substring(firstClose + 1, border);
                                        str = str.substring(0, firstOpen);
                                        str = str + buffer;
                                        it = firstOpen - 1;
                                    }
                                    border = str.length();
                                }
                                else{
                                    if(firstOpen != 0){
                                        str = str.substring(0, firstOpen);
                                        border = str.length();
                                    }
                                    else{
                                        str="";
                                        border = 0;
                                    }
                                }
                            }
                        } else { // Случай, если открывающая скобка не найдена.
                            break;
                        }
                        //System.out.println(cntOpen);
                        //System.out.println(cntClose);
                    } // закрывающая скобка while, разбирающего строку.
                    it++;
                }
                System.out.println("Result  | " + str);
            }// закрывающая скобка while, читающего файл построчно.
        }
        catch (Exception excp){
            excp.printStackTrace();
        }
    }
}