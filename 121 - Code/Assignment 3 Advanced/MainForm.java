import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainForm {
    public JPanel MainPanel;
    private JTextArea textArea1;
    private JButton DoButton;
    private JPasswordField passwordField1;
    private JButton loadFileButton;
    private JTextArea ConsoleOutput1;
    private JButton MessageUsers;
    private JComboBox loadSaveCombo;
    private JButton testCaseCMDButton;
    private JPanel AdminMenu;
    private JPanel MainMenuPanel;

    public MainForm(InsuranceCompany insuranceCompany) throws IOException, CloneNotSupportedException {
        ArrayList<InsurancePolicy> policies = new ArrayList<>();


        //These are created for each item, JTextField, JButton when adding a listener, such as action or focus.
        //These are cool if wanting to refresh on a focus, but I find it needs to be clicked on, not just opened as part of a pane
        DoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String userName = textArea1.getText();
                    String userPassword = passwordField1.getText();
                    System.out.println("uanme: " + userName + ", upassword: " + userPassword);
                    System.out.println("Is Admin? " + insuranceCompany.validateAdmin(userName, userPassword));
                    if (insuranceCompany.validateAdmin(userName, userPassword) == true){
                        //Make a new jForm here, or maybe add tabs
                        SwingUtilities.invokeLater(() -> {
                            JFrame adminFrame = new JFrame("AdminMenuUI");
                            adminFrame.setContentPane(new AdminMenuUI(insuranceCompany).AdminPanel);
                            adminFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            adminFrame.pack();
                            adminFrame.setVisible(true);
                        });
                        MainPanel.setVisible(false);

                    } else if (insuranceCompany.validateUser(userName, userPassword) == true){
                        //ConsoleOutput1.append("Login, welcome User \n");
                        int userID = insuranceCompany.getUserID(userName, userPassword);
                        SwingUtilities.invokeLater(() -> {
                            JFrame userFrame = new JFrame("UserMenuUI");
                            userFrame.setContentPane(new UserMenuUI(insuranceCompany, userID).UserPanel);
                            userFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            userFrame.pack();
                            userFrame.setVisible(true);
                        });
                        MainPanel.setVisible(false);
                } else {
                        //These are the customiseable messahes and input dialouges that can be brought up
                        JOptionPane.showMessageDialog(MainPanel, "Wrong login Credentials");
                    }
            }
        });
        MessageUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usersList = insuranceCompany.printUsersToString();
                JOptionPane.showMessageDialog(MainPanel, usersList);
            }
        });
        loadSaveCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loadSaveCombo.getSelectedItem().equals("Load File")){
                    var fileName = javax.swing.JOptionPane.showInputDialog("Name of save file .ser");
                    System.out.println("file name: " + fileName);

                    try {
                        insuranceCompany.load(fileName, "Admin", "Admin");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (loadSaveCombo.getSelectedItem().equals("Save File")){
                    var fileName = javax.swing.JOptionPane.showInputDialog("File name to save, .ser");
                    System.out.println("file name: " + fileName);

                    try {
                        insuranceCompany.save(fileName, insuranceCompany.getAdminUserName(), insuranceCompany.getAdminPassword());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        testCaseCMDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Options.AdminMenu(insuranceCompany, policies);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (CloneNotSupportedException ex) {
                    throw new RuntimeException(ex);
                } catch (PolicyException ex) {
                    throw new RuntimeException(ex);
                } catch (NameException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

class AdminForm {

    public AdminForm(){

    }
}
