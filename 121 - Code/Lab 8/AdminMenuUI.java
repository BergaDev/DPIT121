import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AdminMenuUI {
    JPanel AdminPanel;
    private JTabbedPane tabbedPane1;
    private JTextArea ConsoleOutput1;
    private JButton ReportUsers;
    private JButton adminMenuCMDButton;
    private JTabbedPane EditPD;
    private JComboBox DistinctCities;
    private JTextField NameField;
    private JTextField UsernameField;
    private JTextField passwordField;
    private JTextField streetNumField;
    private JTextField streetField;
    private JTextField suburbField;
    private JTextField newCityField;
    private JCheckBox newCityCheck;
    private JButton changeButton;
    private JTextField policyHolderNameField;
    private JTextField carModelField;
    private JTextField manufactureField;
    private JTextField carPriceField;
    private JButton submitButton;
    private JTextField claimsField;
    private JLabel commentsDriverAge;
    private JTextField commentsField;
    private JTextField ddField;
    private JTextField mmField;
    private JTextField yyField;
    private JComboBox typeCombo;
    private JButton clearButton;
    private JRadioButton TPPRadio;
    private JRadioButton CPRadio;
    private JLabel driverLevelLabel;
    private JTextField levelField;
    private JTextField policyIDField;
    private JTextField IDEnter;
    private JTextArea planOutput1;
    private JButton findButton;
    private JTable allPlansTable;
    private JButton refreshPoliciesButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable filterTable;
    private JButton sortButton;
    private JTextField dayFilter;
    private JTextField nameFilterField;
    private JTextField monthFilter;
    private JTextField yearFilter;
    private JRadioButton filterNameRadioButton;
    private JRadioButton filterDateRadioButton;
    private JRadioButton filterModelRadioButton;
    private JTextField modelFilterField;
    private JButton paymentsPerModelButton;
    private JTextArea reportsOutput;
    private JComboBox userIDCombo;
    private JTextField userIDGenField;
    private JTextField UsersNameField;
    private JTextField createUserName;
    private JTextField createUserPassword;
    private JTextField createUserStreetNum;
    private JTextField createUserStreet;
    private JTextField createUserSuburb;
    private JButton createUserButton;
    private JTextArea companyReportOutput;
    private JButton button1Button;
    private JButton button2Button;
    private JComboBox removeIDCombo;
    private JTextField removeUserNameField;
    private JButton removeUserButton;
    private JTextField flatRateField;
    private JButton submitChangesButton;
    private JTextField companyNameField;
    private JTextField companyUsernameField;
    private JTextField companyUserPasswordField;
    private JButton logoutButton;
    private JTextField findForUserIDField;
    private JCheckBox searchAllUsersCheckBox;
    private JTextPane companyReportsOutput;
    private JButton report1Button;


    public AdminMenuUI(InsuranceCompany insuranceCompany) {
        String adminUsername = insuranceCompany.getAdminUserName();
        String adminPassword = insuranceCompany.getAdminPassword();
        //On Load
        ArrayList<InsurancePolicy> policies = new ArrayList<>();
        HashMap<Integer, User> users = insuranceCompany.getUsersHash();
        users.forEach((userID, user) -> userIDCombo.addItem(userID));
        users.forEach((userID, user) -> removeIDCombo.addItem(userID));

        flatRateField.setText(String.valueOf(insuranceCompany.flatRate));
        companyNameField.setText(insuranceCompany.name);
        companyUsernameField.setText(insuranceCompany.getAdminUserName());
        companyUserPasswordField.setText(insuranceCompany.getAdminPassword());
        fillTable(insuranceCompany);




        //Action based stuff below
        AdminPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
            }
        });
        ReportUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsoleOutput1.append(insuranceCompany.printUsersToString());
            }
        });
        adminMenuCMDButton.addActionListener(new ActionListener() {
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
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object userIDObject = userIDCombo.getSelectedItem();
                int userID = (int) userIDObject;
                User user = insuranceCompany.getUser(userID);
                String newName = NameField.getText();
                user.setName(newName);
                String newUserName = UsernameField.getText();
                user.setUserName(newUserName);
                String newPassword = passwordField.getText();
                //user.setUserPassword(newPassword);
                insuranceCompany.findUser(userID).setUserPassword(newPassword);
                System.out.println("New PWord in: " + passwordField.getText());
                System.out.println("Called pword: " + user.userPassword);
                //user.setStreetNum(streetNumField.getText().);
                String newStreet = streetField.getText();
                user.setStreet(newStreet);
                String newSuburb = suburbField.getText();
                user.setSuburb(newSuburb);
                if (newCityCheck.isSelected()){
                    String newCity = newCityField.getText();
                    user.setCity(newCity);
                } else {
                    String newCity = (String) DistinctCities.getSelectedItem();
                    user.setCity(newCity);
                }
            }
        });

        userIDCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object userIDObject = userIDCombo.getSelectedItem();
                int userID = (int) userIDObject;
                User user = insuranceCompany.getUser(userID);
                NameField.setText(user.getName());
                UsernameField.setText(user.userName);
                passwordField.setText(user.userPassword);
                streetNumField.setText(String.valueOf(user.getAddress().streetNum));
                streetField.setText(user.getAddress().street);
                suburbField.setText(user.getAddress().suburb);
                ArrayList<String> cityNames = insuranceCompany.populateDistinctCityNames();
                cityNames.forEach(city -> DistinctCities.addItem(city));

            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int policyID = Integer.parseInt(IDEnter.getText());
                    String result = insuranceCompany.findPolicyToString(policyID);
                    if (result.isBlank()){
                        planOutput1.append("A policy by this ID could not be found \n");
                    } else{
                        if (searchAllUsersCheckBox.isSelected()){
                            planOutput1.append(result + "\n");
                        } else {
                            User user = insuranceCompany.findUser(Integer.parseInt(findForUserIDField.getText()));
                            InsurancePolicy policy = user.findPolicy(policyID, user.userName, user.userPassword);
                           result = InsurancePolicy.printPolicy(policy, insuranceCompany.flatRate);
                           planOutput1.append(result + "\n");
                        }
                    }

            }
        });


        refreshPoliciesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillTable(insuranceCompany);
            }
        });
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillFilterTable(insuranceCompany);
            }
        });
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creating user here
                String name = UsersNameField.getText();
                String userName = createUserName.getText();
                String userPassword = createUserPassword.getText();
                int streetNum = Integer.parseInt(createUserStreetNum.getText());
                String street = createUserStreet.getText();
                String suburb = createUserSuburb.getText();
                //Will fix this up in a sec
                String city = null;
                int userID = insuranceCompany.userIDGen();

                insuranceCompany.addUser(name, userID, new Address(streetNum, street, suburb, city), userName, userPassword);
                JOptionPane.showMessageDialog(AdminPanel, "User created, ID is : " + userID);
            }
        });


        button1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> cities = insuranceCompany.populateDistinctCityNames();
                ArrayList<Double> payments = insuranceCompany.getTotalPaymentsPerCity(cities);
               String output = insuranceCompany.reportPaymentPerCityToString(cities, payments);
                companyReportOutput.append(output + "\n");
            }
        });
        button2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String output = insuranceCompany.reportPaymentsPerCarModelToString() + "\n";
                companyReportOutput.append(output);
            }
        });
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userID = (int) removeIDCombo.getSelectedItem();
                int response = JOptionPane.showConfirmDialog(AdminPanel, "Are you sure you want to remove user: " + userID);
                if (response == 0){
                    insuranceCompany.removeUser(userID);
                }
            }
        });
        tabbedPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
            }
        });
        removeIDCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userID = (int) removeIDCombo.getSelectedItem();
                removeUserNameField.setText(users.get(userID).getName());
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = allPlansTable.getSelectedRow();
                Object policyID = allPlansTable.getModel().getValueAt(row, 2);
                int test = Integer.parseInt(policyID.toString());
                Object userObject = allPlansTable.getModel().getValueAt(row, 0);
                int userID = Integer.parseInt(userObject.toString());
                User user = insuranceCompany.findUser(userID);
                System.out.println("Selected row is: " + row + "Row 1 ID is: " + test);
                Object typeObject = allPlansTable.getModel().getValueAt(row, 6);
                String typeText = typeObject.toString();
                String type = insuranceCompany.findPolicy(userID, test, user.userName, user.userPassword).getPolicyType();

                String finalType = type;
                SwingUtilities.invokeLater(() -> {
                    JFrame editUI = new JFrame("Policy Edit UI");
                    editUI.setContentPane(new EditPolicyUI(insuranceCompany, test, userID, finalType, "Admin").editUI);
                    editUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    editUI.pack();
                    editUI.setVisible(true);
                });
                AdminPanel.setVisible(false);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = allPlansTable.getSelectedRow();
                Object policyID = allPlansTable.getModel().getValueAt(row, 2);
                int test = Integer.parseInt(policyID.toString());
                Object userObject = allPlansTable.getModel().getValueAt(row, 0);
                int userID = Integer.parseInt(userObject.toString());
                String userName = insuranceCompany.findUser(userID).userName;
                String userPassword = insuranceCompany.findUser(userID).userPassword;
                System.out.println("Selected row is: " + row + "Row 1 ID is: " + test);
                InsurancePolicy policy = insuranceCompany.findPolicy(userID, test, userName, userPassword);

                int option = JOptionPane.showConfirmDialog(AdminPanel, "Are you sure you want to delete this policy?");
                if (option == 0){
                    insuranceCompany.removePolicy(userID, policy, userName, userPassword);
                    fillTable(insuranceCompany);
                }
            }
        });
        submitChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //int response = (JOptionPane.showConfirmDialog(AdminPanel, "Are you sure you want to change this? Don't forget to save company file to fully save change"));
                String newName = companyNameField.getText();
                String newCompanyUserName = companyUsernameField.getText();
                String newCompanyPassword = companyUserPasswordField.getText();
                int newCompanyFlatRate = Integer.parseInt(flatRateField.getText());

                insuranceCompany.setName(newName);
                insuranceCompany.setFlatRate(newCompanyFlatRate);
                insuranceCompany.setAdminPassword(newCompanyPassword);
                insuranceCompany.setAdminUsername(newCompanyUserName);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPanel.setVisible(false);
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame("MainForm");

                    try {
                        frame.setContentPane(new MainForm(insuranceCompany).MainPanel);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }

                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                });
            }
        });
        searchAllUsersCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchAllUsersCheckBox.isSelected()){
                    findForUserIDField.setEnabled(false);
                } else {
                    findForUserIDField.setEnabled(true);
                }
            }
        });
    }

    public void fillTable(InsuranceCompany insuranceCompany){
        String [] header = {"userID", "Policy Holder Name", "Policy ID", "Policy Cost", "CarModel", "CarType", "Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"};
        DefaultTableModel model = new DefaultTableModel(header, 0);
        allPlansTable.setModel(model);

        HashMap<Integer, InsurancePolicy> allPolicies = insuranceCompany.allPolicies();
        Set<Integer> policyKeys = allPolicies.keySet();

        model.addRow(new Object[]{"userID", "Policy Holder Name", "Policy ID", "Policy Cost", "Car Model", "Car Type", "Model Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"});
        for (Integer policy: policyKeys){
            int userID = insuranceCompany.findUserID(policy);
            String type = allPolicies.get(policy).getPolicyType();
            if (type.equals("TPP")){
                model.addRow(new Object[]{userID, allPolicies.get(policy).policyHolderName, allPolicies.get(policy).getId(), "$" + allPolicies.get(policy).calcPayment(insuranceCompany.flatRate), allPolicies.get(policy).getCar().getModel(), allPolicies.get(policy).getCar().getType(), allPolicies.get(policy).getCar().getManufacturingYear(), allPolicies.get(policy).getCar().price, allPolicies.get(policy).getComments(), null, null, allPolicies.get(policy).getExpiryDate().year, allPolicies.get(policy).getExpiryDate().month, allPolicies.get(policy).getExpiryDate().day});
            } else if (type.equals("CP")){
                model.addRow(new Object[]{userID, allPolicies.get(policy).policyHolderName, allPolicies.get(policy).getId(), "$" + allPolicies.get(policy).calcPayment(insuranceCompany.flatRate), allPolicies.get(policy).getCar().getModel(), allPolicies.get(policy).getCar().getType(), allPolicies.get(policy).getCar().getManufacturingYear(), allPolicies.get(policy).getCar().price, null, allPolicies.get(policy).getAge(), allPolicies.get(policy).getLevel(), allPolicies.get(policy).getExpiryDate().year, allPolicies.get(policy).getExpiryDate().month, allPolicies.get(policy).getExpiryDate().day});
            }

        }

    }

    public void fillFilterTable(InsuranceCompany insuranceCompany) {
        if (filterNameRadioButton.isSelected()) {
            String name = nameFilterField.getText();

            String [] header = {"userID", "Policy Holder Name", "Policy ID", "Policy Cost", "CarModel", "CarType", "Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            filterTable.setModel(model);

            HashMap<Integer, InsurancePolicy> userPolicies = insuranceCompany.allPolicies();
            System.out.println("Holder Name: " + name);
            Set<Integer> policyKeys = userPolicies.keySet();

            model.addRow(new Object[]{"userID", "Policy Holder Name", "Policy ID", "Policy Cost", "Car Model", "Car Type", "Model Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"});
            for (Integer policy : policyKeys) {
                String type = userPolicies.get(policy).getPolicyType();
                int userID = insuranceCompany.findUserID(policy);
                String policyHolderName = userPolicies.get(policy).policyHolderName;
                if (name.equals(policyHolderName)) {
                    if (type.equals("TPP")) {
                        model.addRow(new Object[]{userID, userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, userPolicies.get(policy).getComments(), null, null, userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
                    } else if (type.equals("CP")) {
                        model.addRow(new Object[]{userID, userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, null, userPolicies.get(policy).getAge(), userPolicies.get(policy).getLevel(), userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
                    }
                }
            }

        } else if (filterDateRadioButton.isSelected()) {
            int year = Integer.parseInt(yearFilter.getText());
            int month = Integer.parseInt(monthFilter.getText());
            int day = Integer.parseInt(dayFilter.getText());
            MyDate searchDate = new MyDate(year, month, day);

            String [] header = {"userID", "Policy Holder Name", "Policy ID", "Policy Cost", "CarModel", "CarType", "Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            filterTable.setModel(model);

            String adminName = insuranceCompany.getAdminUserName();
            String adminPassword = insuranceCompany.getAdminPassword();
            ArrayList<InsurancePolicy> filteredPoliciesArray = insuranceCompany.filterByExpiryDate(searchDate);
            HashMap<Integer, InsurancePolicy> userPolicies = new HashMap<>();
            for (InsurancePolicy policy: filteredPoliciesArray){
                userPolicies.put(policy.getId(), policy);
            }

            //HashMap<Integer, InsurancePolicy> userPolicies = insuranceCompany.filterPoliciesByExpiryDate(searchDate, adminName, adminPassword);
            System.out.println(userPolicies);
            Set<Integer> policyKeys = userPolicies.keySet();

            model.addRow(new Object[]{"userID", "Policy Holder Name", "Policy ID", "Policy Cost", "Car Model", "Car Type", "Model Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"});
            for (Integer policy : policyKeys) {
                int userID = insuranceCompany.findUserID(policy);
                String type = userPolicies.get(policy).getPolicyType();
                if (type.equals("TPP")){
                    model.addRow(new Object[]{userID, userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, userPolicies.get(policy).getComments(), null, null, userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
                } else if (type.equals("CP")){
                    model.addRow(new Object[]{userID, userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, null, userPolicies.get(policy).getAge(), userPolicies.get(policy).getLevel(), userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
                }
            }
        } else if (filterModelRadioButton.isSelected()){
            String searchModel = modelFilterField.getText();

            String[] header = {"userID", "Policy Holder Name", "Policy ID", "Policy Cost", "CarModel", "CarType", "Year", "Price", "Comments", "Expiry Year", "Expiry Month", "Expiry Day"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            filterTable.setModel(model);

            HashMap<Integer, InsurancePolicy> userPolicies = insuranceCompany.filterByCarModel(searchModel);
            System.out.println("Car Model Hash: " + userPolicies + ", Car Model: " + searchModel);
            Set<Integer> policyKeys = userPolicies.keySet();

            model.addRow(new Object[]{"Policy Holder Name", "Policy ID", "Policy Cost", "Car Model", "Car Type", "Model Year", "Price", "Comments", "Expiry Year", "Expiry Month", "Expiry Day"});
            for (Integer policy : policyKeys) {
                int userID = insuranceCompany.findUserID(policy);
                String type = userPolicies.get(policy).getPolicyType();
                if (type.equals("TPP")){
                    model.addRow(new Object[]{userID, userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, userPolicies.get(policy).getComments(), null, null, userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
                } else if (type.equals("CP")){
                    model.addRow(new Object[]{userID, userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, null, userPolicies.get(policy).getAge(), userPolicies.get(policy).getLevel(), userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
                }
            }

        }else {
            String [] header = {"userID", "Policy Holder Name", "Policy ID", "Policy Cost", "CarModel", "CarType", "Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            filterTable.setModel(model);

            HashMap<Integer, InsurancePolicy> allPolicies = insuranceCompany.allPolicies();
            Set<Integer> policyKeys = allPolicies.keySet();

            model.addRow(new Object[]{"userID", "Policy Holder Name", "Policy ID", "Policy Cost", "Car Model", "Car Type", "Model Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"});
            for (Integer policy: policyKeys){
                int userID = insuranceCompany.findUserID(policy);
                String type = allPolicies.get(policy).getPolicyType();
                if (type.equals("TPP")){
                    model.addRow(new Object[]{userID, allPolicies.get(policy).policyHolderName, allPolicies.get(policy).getId(), "$" + allPolicies.get(policy).calcPayment(insuranceCompany.flatRate), allPolicies.get(policy).getCar().getModel(), allPolicies.get(policy).getCar().getType(), allPolicies.get(policy).getCar().getManufacturingYear(), allPolicies.get(policy).getCar().price, allPolicies.get(policy).getComments(), null, null, allPolicies.get(policy).getExpiryDate().year, allPolicies.get(policy).getExpiryDate().month, allPolicies.get(policy).getExpiryDate().day});
                } else if (type.equals("CP")){
                    model.addRow(new Object[]{userID, allPolicies.get(policy).policyHolderName, allPolicies.get(policy).getId(), "$" + allPolicies.get(policy).calcPayment(insuranceCompany.flatRate), allPolicies.get(policy).getCar().getModel(), allPolicies.get(policy).getCar().getType(), allPolicies.get(policy).getCar().getManufacturingYear(), allPolicies.get(policy).getCar().price, null, allPolicies.get(policy).getAge(), allPolicies.get(policy).getLevel(), allPolicies.get(policy).getExpiryDate().year, allPolicies.get(policy).getExpiryDate().month, allPolicies.get(policy).getExpiryDate().day});
                }

            }



            //End of else
        }



    }
}
