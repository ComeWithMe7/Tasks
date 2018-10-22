import java.io.*;
import java.util.*;

public class Connector {
    private String filename;

    public Connector(String filename) {

        this.filename = filename;
    }

    public List<Long> createIndexList(List<BankAccount> accsList) throws IOException {
        List<Long> indexList = new ArrayList<>();
        RandomAccessFile outFile = new RandomAccessFile(filename, "rw");
        for(BankAccount acc : accsList) {
            indexList.add(outFile.getFilePointer());
            outFile.write(acc.toByteArray());
        }
        outFile.close();
        return indexList;
    }


    public BankAccount readPos(int index, List<Long> indexList) throws IOException {
        RandomAccessFile inFile = new RandomAccessFile(filename, "r");
        inFile.seek(indexList.get(index));
        byte[] byteArray = new byte[1024];
        inFile.read(byteArray);
        inFile.close();
        return BankAccount.fromByteArray(byteArray);
    }
}
