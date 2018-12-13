package Window;

import Program.FileSupport;
import Program.BankAccount;

import javax.swing.*;
import javax.swing.text.html.ListView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class WindowDescription extends JFrame {
    private JPanel mainPanel;

    private final int WIDTH = 620;
    private final int HEIGHT = 495;

    public WindowDescription() throws HeadlessException {
        JFrame selfFrame = this;

        DefaultListModel<BankAccount> listModel = new DefaultListModel<>();

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Status status = new Status();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu(" File ");
        JMenuItem loadItem = new JMenuItem(" Open ");
        JMenuItem saveItem = new JMenuItem(" Save ");

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);

        JMenu sortMenu = new JMenu(" Sort ");
        JMenuItem ascendingOrder = new JMenuItem(" Ascending sort ");
        JMenuItem descendingOrder = new JMenuItem(" Descending sort ");
        sortMenu.add(ascendingOrder);
        sortMenu.add(descendingOrder);

  //      JMenu deleteItem = new JMenu(" Delete ");

        menuBar.add(fileMenu);
        menuBar.add(sortMenu);
    //    menuBar.add(deleteItem);
        setJMenuBar(menuBar);


            //deleteItem.addActionListener(new MouseListener() {
        //    @Override
         //   public void mouseClicked(MouseEvent e) {

           // }
        //});

        // Обработка загрузчика.
        loadItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(selfFrame);

                if(result != JFileChooser.APPROVE_OPTION)
                    return;

                File file = fileChooser.getSelectedFile();

                String fileName = file.getName();
                FileSupport fileDat = new FileSupport(fileName);
                try {
                    ArrayList<BankAccount> accs = fileDat.readFile();
                    for(BankAccount acc : accs)
                        listModel.addElement(acc);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                status.addStatus(" Accounts were loaded from " + fileName + " file.");
            }
        });


        // Обработка при вызове сохранения.
        saveItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(selfFrame);

                if(result != JFileChooser.APPROVE_OPTION)
                    return;

                File file= fileChooser.getSelectedFile();

                String fileName = file.getName();
                FileSupport fileDat = new FileSupport(fileName);
                try {
                    fileDat.writeAllToFile(listModel);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                status.addStatus(" List was saved to \"" + fileName + "\"");
            }
        });

        // Обработка при вызове сортировки по возрастанию.
        ascendingOrder.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<BankAccount> data = new ArrayList<>();
                for(int i = 0; i < listModel.size(); ++i)
                    data.add(listModel.get(i));
                Collections.sort(data,((one, two) ->{return one.getAccNumber()-two.getAccNumber(); }));
                listModel.clear();
                for(BankAccount acc:data)
                    listModel.addElement(acc);

                status.addStatus(" Sorted in AO. ");
            }
        });

        // Обработка при вызове сортировки по убыванию.
        descendingOrder.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<BankAccount> data = new ArrayList<>();
                for(int i = 0; i < listModel.size(); ++i)
                    data.add(listModel.get(i));
                Collections.sort(data,((one, two) ->{return two.getAccNumber()-one.getAccNumber(); }));
                listModel.clear();
                for(BankAccount acc:data)
                    listModel.addElement(acc);

                status.addStatus(" Sorted in DO. ");
            }
        });


        mainPanel = new JPanel();
        setResizable(false);
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        //Creating listPanel
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        //JLabel listLabel = new JLabel(" List of bank Accounts: ");
        //listPanel.add(listLabel);

        JList listView = new JList(listModel);
        listPanel.setLayout(new BorderLayout());
        listPanel.add(listView);

        JScrollPane scrollBar = new JScrollPane(listView);

        listPanel.add(scrollBar, BorderLayout.NORTH);
        mainPanel.add(listPanel, BorderLayout.NORTH);

       /* listView.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DELETE: {
                        if (!listView.isSelectionEmpty()) {
                            BankAccount employee = (BankAccount) listView.getSelectedValue();
                            if(employee.isDeleted()) {
                                status.addStatus(/*statusHead + *//*"object is already deleted");
                            return;
                            }
                            if (JOptionPane.showConfirmDialog(null, "Delete this object?") == 0) {
                                employee.setDeleted(true);
                                // status.setText(statusHead + "object " + employee.getTabNumFormat() + " deleted");
                            } else
                                status.addStatus(/*statusHead + *//*"deleting refused");
                        }
                    }
                }
            }*/
/*
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
*/

        // Панель ввода.
        JPanel accPanel = new JPanel();
        accPanel.setLayout(new BoxLayout(accPanel, BoxLayout.Y_AXIS));
        //accPanel.add()
        JTextField accCodeText = new JTextField();
        JLabel accCodeLabel = new JLabel(" Account code: ");
        accPanel.add(accCodeLabel);
        accPanel.add(accCodeText);

        JTextField ownerSurnameText = new JTextField();
        JLabel ownerSurnameLabel = new JLabel(" Owner surname: ");
        accPanel.add(ownerSurnameLabel);
        accPanel.add(ownerSurnameText);

        JTextField accountCurrencyText = new JTextField();
        JLabel accountCurrencyLabel = new JLabel(" Account currency:");
        accPanel.add(accountCurrencyLabel);
        accPanel.add(accountCurrencyText);

        JTextField sumText = new JTextField();
        JLabel sumLabel = new JLabel(" Sum: ");
        accPanel.add(sumLabel);
        accPanel.add(sumText);

        JTextField openingDayText = new JTextField();
        JLabel openingDayLabel = new JLabel(" Opening date(suitable form \"dd.mm.yyyy)\"): ");
        accPanel.add(openingDayLabel);
        accPanel.add(openingDayText);

        JTextField percentText = new JTextField();
        JLabel percentLabel = new JLabel(" Percent:");
        accPanel.add(percentLabel);
        accPanel.add(percentText);


        // Обработка нажатия кнопки "Enter"
        JButton enter = new JButton(" Enter ");
        accPanel.add(enter);
        enter.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int accCode;
                    try{
                        accCode = Integer.parseInt(accCodeText.getText());
                    }
                    catch(Exception excp){
                        accCode = 1234;
                    }
                    String ownerSurname = ownerSurnameText.getText();
                    String accountCurrency = accountCurrencyText.getText();
                    double sum;
                    try{
                        sum = Double.parseDouble(sumText.getText());
                    }
                    catch(Exception excp){
                        excp.printStackTrace();
                        status.addStatus("INCORRECT SUM");
                        sum = 0;
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    Date openingDay;
                    try{
                        openingDay = sdf.parse(openingDayText.getText());
                    }
                    catch(Exception excp){
                        openingDay = sdf.parse("01.01.2018");
                    }

                    double percent;
                    try{
                        percent = Double.parseDouble(percentText.getText());
                    } catch(Exception excp){
                        percent = 7.5;
                    }


                    listModel.addElement(new BankAccount(accCode, ownerSurname, accountCurrency, sum, openingDay, percent));

                    //status.addStatus(" Account was entered. ");
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });


        // Нижняя панель.
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        southPanel.add(accPanel);

        bottomPanel.add(southPanel);
        bottomPanel.add(status.getStatusPanel());

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Размещение окна по центру.
        setVisible(true);
    }
}