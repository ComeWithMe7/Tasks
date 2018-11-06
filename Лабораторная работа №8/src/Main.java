import java.io.*;
import java.util.*;

public class Main {
    private final static int SIZE = 10;

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
                        generatorCode(),
                        (in.hasNextLine()) ? in.nextLine() : "_",
                        generatorCurrency(),
                        Math.random() * 10000 + 1,
                        Math.random() * 100
                ));
            }

            Connector c = new Connector("Output.dat");
            List<Long> indexList = c.createIndexList(aссountList);

            System.out.println("Backward:");
            for(int i = indexList.size() - 1; i >= 0; i--)
                System.out.println(c.readByIndex(i).toString());

            System.out.println("Sorted by accNum:");
            Integer[] sortedAccNumKeys = c.getSortedAccNumKeys(false);
            for(Integer i : sortedAccNumKeys)
                c.printByAccNum(i);

            System.out.println("Sorted by ownerSurname (backward):");
            String[] sortedBackwardSurnameKeys = c.getSortedSurnameKeys(true);
            for(String i : sortedBackwardSurnameKeys)
                c.printBySurname(i);

            System.out.println("Sorted by ownerSurname (Owner2 is deleted):");
            sortedBackwardSurnameKeys = c.getSortedSurnameKeys(false);
            c.deleteBySurname("Owner2");
            for(String i : sortedBackwardSurnameKeys)
                c.printBySurname(i);

            System.out.println("Sorted by accCode:");
            Integer[] sortedAccCodeKeys = c.getSortedAccCodeKeys(false);
            for(Integer i : sortedAccCodeKeys)
                c.printByAccCode(i);


            System.out.println("Created accounts:");
            for(BankAccount acc : aссountList) {
                System.out.println(acc.toString());
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
