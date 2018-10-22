import java.util.*;
import java.io.*;

public class Test {
    private final static int SIZE = 5;

    public static String generatorCurrency(){
        String [] curType = {"USD", "EUR", "CHF", "GBP", "RUB", "BYN"};
        int typeMarker = 6;
        while(typeMarker > 5){
            typeMarker = (int)(Math.random()*10);
        }
        return curType[typeMarker];
    }

    public static int generatorCode(){
        int buffer = (int)(Math.random()*1000);
        if(buffer < 100){
            buffer = buffer*100;
        }

        if((buffer >= 100) && (buffer < 1000)){
            buffer = buffer*10;
        }
        return buffer;
    }


    public static void main(String[] args) {
        List<BankAccount> aссountList = new ArrayList<>();
        try {
            Scanner in = new Scanner(new File("Input.txt"));
            for(int i = 0; i < SIZE; i++) {
                aссountList.add(new BankAccount(
                        (i + 1802001),
                        generatorCode(),
                        (in.hasNextLine()) ? in.nextLine() : "_",
                        generatorCurrency(),
                        Math.random() * 10000 + 1,
                        Math.random() * 100
                ));
            }

            System.out.println("Created accounts:");
            for(BankAccount acc : aссountList) {
                System.out.println(acc.toString());
            }


            Connector c = new Connector("Output.dat");
            List<Long> indexList = c.createIndexList(aссountList);
            
            
            // Последовательный вывод всех данных, считываемых из файла.
            System.out.println("\nRecorded from the \"Output.dat\":");
            for(int i = 0; i < indexList.size(); i++) {
                BankAccount tmpAccount = c.readPos(i, indexList);
                System.out.println(tmpAccount.toString());
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
