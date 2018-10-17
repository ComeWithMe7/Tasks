import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        try
        {
            if (args.length!=2) {
                throw new Exception("Invalid number of arguments");
            }
            ResourceBundle message = ResourceBundle.getBundle("MSG", new Locale(args[0], args[1]));
            System.out.println(message.getString("mode"));
            int mode = in.nextInt();
            switch (mode)
            {
                case 1:
                    System.out.println(message.getString("created_Clients"));
                    Client [] mas_Clients = new Client[]
                            {
                                    new Client(new Account(100), 18001),
                                    new Client(new Account(200), 18002),
                                    new Client(new Account(300), 18003)
                            };

                    for(Client pr:mas_Clients) {
                        System.out.println(pr.toString());
                    }
                    System.out.println(message.getString("created_Admins"));
                    Admin [] mas_Admins = new Admin[]
                            {
                                    new Admin(1),
                                    new Admin(2),
                                    new Admin(3),
                                    new Admin(4),
                                    new Admin(5)
                            };
                    for(Admin app:mas_Admins){
                        System.out.println(app.toString());
                    }
                    Connector connector = new Connector(mas_Clients, mas_Admins);
                    connector.Write_ALLData_to_File("result");
                    break;
                case 2:
                    connector = new Connector();
                    connector.Read_ALLData_from_File("result");
                    mas_Clients=connector.getMas_Clients();
                    System.out.println(message.getString("loaded_Clients"));
                    for(Client pr:mas_Clients) {
                        System.out.println(pr.toString());
                    }
                    mas_Admins=connector.getMas_Admins();
                    System.out.println(message.getString("loaded_Admins"));
                    for(Admin app:mas_Admins) {
                        System.out.println(app.toString());
                    }
                    break;
                default:
                    throw new IOException(message.getString("invalid_mode"));
            }

            System.out.println("\n");
            Client client = new Client(new Account(1000),10);
            System.out.println(client);
            System.out.println("\n");

            System.out.println("for order: -20p.u.");
            client.makePay(20);
            System.out.println(client);

            System.out.println("Creating account with 100 p.u.:");
            Account acc = new Account(100);
            System.out.println(acc.getBalance());

            System.out.println("Transfer: ");
            client.transfer(acc,200);
            System.out.println(client);
            System.out.println(acc.getBalance());
            System.out.println("\n");

            System.out.println("Card blocking:");
            client.blockCard();
            System.out.println(client);
            System.out.println("\n");

            System.out.println("Account annulation: ");
            client.annulateAccount();
            System.out.println(client);
            System.out.println("\n");

            System.out.println("Card blocking: ");
            Client client1 = new Client(new Account(-100),11);
            System.out.println(client1);
            Admin admin = new Admin(20);
            admin.block(client1);
            System.out.println(client1);
        }
        catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }

        catch (MissingResourceException e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }

        catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
        }
        finally {}
    }
}
