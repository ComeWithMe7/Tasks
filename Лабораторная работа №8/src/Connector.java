import java.util.*;
import java.io.*;

public class Connector {
        private String filename;
        private boolean fileIsCreated;

        public List<Long> indexList;
        public Map<Integer, long[]> accNumIndexMap;
        public Map<String, long[]> surnameIndexMap;
        public Map<Integer, long[]> accCodeIndexMap;

        public Connector(String filename) {
                this.filename = filename;
                this.fileIsCreated = false;

                this.indexList = new ArrayList<>();
                this.accNumIndexMap = new HashMap<>();
                this.surnameIndexMap = new HashMap<>();
                this.accCodeIndexMap = new HashMap<>();
        }

        public List<Long> createIndexList(List<BankAccount> accountsList) throws IOException {
                RandomAccessFile outFile = new RandomAccessFile(filename, "rw");
                for(BankAccount account : accountsList) {
                        this.indexList.add(outFile.getFilePointer());

                        if(this.accNumIndexMap.containsKey(account.getAccNumber())){
                                this.accNumIndexMap.replace(account.getAccNumber(), this.accNumIndexMap.get(account.getAccNumber()),
                                        Connector.insertValue(this.accNumIndexMap.get(account.getAccNumber()), outFile.getFilePointer()));
                }
                        else {
                                this.accNumIndexMap.put(account.getAccNumber(), new long[] {outFile.getFilePointer()});
                        }

                        if(this.surnameIndexMap.containsKey(account.getOwnerSurname()))
                                this.surnameIndexMap.replace(account.getOwnerSurname(), this.surnameIndexMap.get(account.getOwnerSurname()),
                                        Connector.insertValue(this.surnameIndexMap.get(account.getOwnerSurname()), outFile.getFilePointer()));
                        else this.surnameIndexMap.put(account.getOwnerSurname(), new long[] {outFile.getFilePointer()});

                        if(this.accCodeIndexMap.containsKey(account.getAccountCode()))
                                this.accCodeIndexMap.replace(account.getAccountCode(), this.accCodeIndexMap.get(account.getAccountCode()),
                                        Connector.insertValue(this.accCodeIndexMap.get(account.getAccountCode()), outFile.getFilePointer()));
                        else this.accCodeIndexMap.put(account.getAccountCode(), new long[] {outFile.getFilePointer()});


                        outFile.writeInt(account.toByteArray().length);
                        outFile.write(account.toByteArray());
                }
                outFile.close();
                this.fileIsCreated = true;
                return this.indexList;
        }

private BankAccount readPos(long pos) throws IOException {
        if (this.fileIsCreated) {
        RandomAccessFile inFile = new RandomAccessFile(filename, "r");
        inFile.seek(pos);
        byte[] byteArray = new byte[inFile.readInt()];
        inFile.read(byteArray);
        inFile.close();
        return BankAccount.fromByteArray(byteArray);
        }
        else throw new FileNotFoundException();
}

        public BankAccount readByIndex(int index) throws IOException {
                if(index < 0 || index >= this.indexList.size())
                        throw new IndexOutOfBoundsException();
                return this.readPos(this.indexList.get(index));
        }

        public void printByAccNum(int accNum) throws IllegalArgumentException, IOException {
                if(this.accNumIndexMap.containsKey(accNum)) {
                        for (long pos : this.accNumIndexMap.get(accNum)) {
                                BankAccount tempAccount = readPos(pos);
                                if(!tempAccount.isDeleted())
                                        System.out.println(tempAccount.toString());
                        }
                }
                else throw new IllegalArgumentException();
        }

        public void printBySurname(String surname) throws IllegalArgumentException, IOException {
                if(this.surnameIndexMap.containsKey(surname)) {
                        for (long pos : this.surnameIndexMap.get(surname)) {
                                BankAccount tempAccount = readPos(pos);
                                if(!tempAccount.isDeleted())
                                        System.out.println(tempAccount.toString());
                        }
                }
                else throw new IllegalArgumentException();
        }

        public void printByAccCode(int accCode) throws IllegalArgumentException, IOException {
                if(this.accCodeIndexMap.containsKey(accCode)) {
                        for (long pos : this.accCodeIndexMap.get(accCode)) {
                                BankAccount tempAccount = readPos(pos);
                                if(!tempAccount.isDeleted())
                                        System.out.println(tempAccount.toString());
                        }
                }
                else throw new IllegalArgumentException();
        }

        private void deletePos(long pos) {
                try {
                        if(this.fileIsCreated) {
                                RandomAccessFile edFile = new RandomAccessFile(filename, "rw");
                                edFile.seek(pos);
                                byte[] byteArray = new byte[edFile.readInt()];
                                edFile.read(byteArray);
                                BankAccount tempAccount = BankAccount.fromByteArray(byteArray);
                                if (!tempAccount.isDeleted()) {
                                        tempAccount.setDeleted(true);
                                        edFile.seek(pos);
                                        edFile.writeInt(byteArray.length);
                                        edFile.write(tempAccount.toByteArray());
                                }
                                edFile.close();
                        }
                        else throw new FileNotFoundException();
                }
                catch(Exception e) {
                        e.printStackTrace();
                }
        }

        public void deleteByIndex(int index) {
                if(index < 0 || index >= this.indexList.size())
                        throw new IndexOutOfBoundsException();
                deletePos(this.indexList.get(index));
        }

        public void deleteByAccNum(int accNum) {
                if(this.accNumIndexMap.containsKey(accNum))
                        for (long pos : this.accNumIndexMap.get(accNum))
                                this.deletePos(pos);
        }

        public void deleteBySurname(String surname) {
                if(this.surnameIndexMap.containsKey(surname))
                        for (long pos : this.surnameIndexMap.get(surname))
                                this.deletePos(pos);
        }

        public void deleteByAccCode(int accCode) {
                if(this.accCodeIndexMap.containsKey(accCode))
                        for (long pos : this.accCodeIndexMap.get(accCode))
                                this.deletePos(pos);
        }

        private static long[] insertValue(long[] arr, long value) {
                int length = (arr == null) ? 0 : arr.length;
                long[] result = new long[length + 1];
                for(int i = 0; i < length; i++) {
                        result[i] = arr[i];
                }
                result[length] = value;
                return result;
        }

        public Integer[] getSortedAccNumKeys(boolean backward) {
                Set<Integer> resultSet = this.accNumIndexMap.keySet();
                Integer[] result = resultSet.toArray(new Integer[0]);
                Arrays.sort(result, new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                                return (backward) ? o2.compareTo(o1) : o1.compareTo(o2); }});
                return result;
        }

        public String[] getSortedSurnameKeys(boolean backward) {
                Set<String> resultSet = this.surnameIndexMap.keySet();
                String[] result = resultSet.toArray(new String[0]);
                Arrays.sort(result, new Comparator<String>() {@Override public int compare(String o1, String o2) {
        return (backward) ? o2.compareTo(o1) : o1.compareTo(o2); }});
                return result;
        }

        public Integer[] getSortedAccCodeKeys(boolean backward) {
                Set<Integer> resultSet = this.accCodeIndexMap.keySet();
                Integer[] result = resultSet.toArray(new Integer[0]);
                Arrays.sort(result, new Comparator<Integer>() { @Override public int compare(Integer o1, Integer o2) {
                        return (backward) ? o2.compareTo(o1) : o1.compareTo(o2); }});
                return result;
        }
}