import java.util.*;
import java.io.*;
public class Connector {
    private ArrayList<Client> mas_Clients = new ArrayList<Client>();
    private ArrayList<Admin> mas_Admins = new ArrayList<Admin>();

    public Connector() {}

    public Connector(Client[] mas_Clients, Admin[] mas_Admins) {
        for (Client pr : mas_Clients)
            this.mas_Clients.add(pr);
        for (Admin app : mas_Admins)
            this.mas_Admins.add(app);
    }

    public Client[] getMas_Clients() {
        Client[] mas = new Client[mas_Clients.size()];
        for (int i = 0; i < mas.length; i++)
            mas[i] = mas_Clients.get(i);
        return mas;
    }

    public void setMas_Clients(Client[] mas_Clients) {
        if (!this.mas_Clients.isEmpty())
            this.mas_Clients.clear();
        for (Client pr : mas_Clients)
            this.mas_Clients.add(pr);
    }

    public void add_Client(Client Client) {
        mas_Clients.add(Client);
    }

    public void clear_Clients() {
        if (!this.mas_Clients.isEmpty())
            mas_Clients.clear();
    }

    public Admin[] getMas_Admins() {
        Admin[] mas = new Admin[mas_Admins.size()];
        for (int i = 0; i < mas.length; i++)
            mas[i] = mas_Admins.get(i);
        return mas;
    }

    public void setMas_Admins(Admin[] mas_Admins) {
        if (!this.mas_Admins.isEmpty())
            this.mas_Admins.clear();
        for (Admin app : mas_Admins)
            this.mas_Admins.add(app);
    }

    public void add_Admin(Admin Admin) {
        mas_Admins.add(Admin);
    }

    public void clear_Admin() {
        if (!this.mas_Admins.isEmpty())
            mas_Admins.clear();
    }

    public void Write_ALLData_to_File(String filename) throws Exception {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeInt(mas_Clients.size());
            for (Client pr : mas_Clients) {
                os.writeObject(pr);
            }
            os.writeInt(mas_Admins.size());
            for (Admin app : mas_Admins) {
                os.writeObject(app);
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public void Read_ALLData_from_File(String filename) throws Exception {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
            int length = is.readInt();
            if (!this.mas_Clients.isEmpty())
                mas_Clients.clear();
            for (int i = 0; i < length; i++)
                mas_Clients.add((Client) is.readObject());
            length = is.readInt();
            if (!this.mas_Admins.isEmpty())
                mas_Admins.clear();
            for (int i = 0; i < length; i++)
                mas_Admins.add((Admin) is.readObject());
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Write_Clients_to_File(String filename) throws Exception {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeInt(mas_Clients.size());
            for (Client pr : mas_Clients)
                os.writeObject(pr);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public void Read_Client_from_File(String filename) throws Exception {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
            int length = is.readInt();
            if (!this.mas_Clients.isEmpty())
                mas_Clients.clear();
            for (int i = 0; i < length; i++)
                mas_Clients.add((Client) is.readObject());
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Write_Admins_to_File(String filename) throws Exception {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeInt(mas_Admins.size());
            for (Admin app : mas_Admins)
                os.writeObject(app);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public void Read_Admin_from_File(String filename) throws Exception {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
            int length = is.readInt();
            if (!this.mas_Admins.isEmpty())
                mas_Admins.clear();
            for (int i = 0; i < length; i++)
                mas_Admins.add((Admin) is.readObject());
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
