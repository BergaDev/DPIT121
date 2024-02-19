import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class UserMenuUI {
    private JTabbedPane EditPD;
    private JComboBox DistinctCities;
    JPanel UserPanel;
    private JTextField userIDField;
    private JTextField NameField;
    private JTextField UsernameField;
    private JTextField passwordField;
    private JTextField streetNumField;
    private JTextField streetField;
    private JTextField suburbField;
    private JTextField newCityField;
    private JCheckBox newCityCheck;
    private JButton changeButton;
    private JButton logoutButton;
    private JTextField IDEnter;
    private JButton findButton;
    private JTextArea planOutput1;
    private JTable allPlansTable;
    private JTextField policyHolderNameField;
    private JTextField carModelField;
    private JTextField manufactureField;
    private JTextField carPriceField;
    private JButton submitButton;
    private JTextField claimsField;
    private JTextField commentsField;
    private JTextField ddField;
    private JTextField mmField;
    private JTextField yyField;
    private JComboBox typeCombo;
    private JButton clearButton;
    private JButton refreshPoliciesButton;
    private JRadioButton CPRadio;
    private JRadioButton TPPRadio;
    private JLabel commentsDriverAge;
    private JTextField levelField;
    private JLabel driverLevelLabel;
    private JButton editButton;
    private JButton deleteButton;
    private JTextField policyIDField;
    private JTable filterTable;
    private JTextField nameFilterField;
    private JCheckBox filterByNameCheckBox;
    private JButton sortButton;
    private JCheckBox filterByDateCheckBox;
    private JTextField dayFilter;
    private JTextField monthFilter;
    private JTextField yearFilter;
    private JRadioButton filterNameRadioButton;
    private JRadioButton filterDateRadioButton;
    private JRadioButton filterModelRadioButton;
    private JTextField modelFilterField;
    private JCheckBox sortAlphabeticlyCheckBox;
    private JButton paymentsPerModelButton;
    private JTextArea reportsOutput;
    private JTextPane planOutputArea1;

    public UserMenuUI(InsuranceCompany insuranceCompany, int userID) {

        //Below here just sets up form
        User user = insuranceCompany.getUser(userID);
        ArrayList<String> citiesArray = insuranceCompany.populateDistinctCityNames();
        String[] citiesString = new String[citiesArray.size()];
        for (String city: citiesArray){
            DistinctCities.addItem(city);
        }

        String userNum = String.valueOf(userID);
        userIDField.setText(userNum);
        userIDField.setEnabled(false);

        NameField.setText(insuranceCompany.findUser(userID).getName());
        UsernameField.setText(user.userName);
        UsernameField.setEnabled(false);
        passwordField.setText(user.userPassword);
        String streetNum = String.valueOf(user.getAddress().streetNum);
        streetNumField.setText(streetNum);
        streetField.setText(user.getAddress().street);
        suburbField.setText(user.getAddress().suburb);

        for (String city: citiesArray){
            if (city.equals(user.getAddress().city)){
                DistinctCities.setSelectedItem(city);
            }
        }
        newCityField.setText(user.getAddress().city);
        newCityField.setEnabled(false);

        fillTable(insuranceCompany, userID);
        //PlansOutput.append(insuranceCompany.findUser(userID).allPoliciesToString());
        TPPRadio.setSelected(true);
        CPRadio.setSelected(false);
        driverLevelLabel.setVisible(false);
        levelField.setVisible(false);
        policyIDField.setEnabled(false);

        nameFilterField.setEnabled(false);

        //Below here is interactable and on change stuff
        DistinctCities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Current item is: " + e);
            }
        });





        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = NameField.getText();
                user.setName(newName);
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
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserPanel.setVisible(false);
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

        newCityCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newCityCheck.isSelected()) {
                    newCityField.setEnabled(true);
                    DistinctCities.setEnabled(false);
                } else {
                    newCityField.setEnabled(false);
                    DistinctCities.setEnabled(true);
                }
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int policyID = Integer.parseInt(IDEnter.getText());
                System.out.println(insuranceCompany.findPolicy(userID, policyID, user.userName, user.userPassword));
                if (insuranceCompany.findPolicy(userID, policyID, user.userName, user.userPassword) != null){
                    String result = insuranceCompany.findPolicyToString(policyID);
                    planOutput1.append(result + "\n");
                } else {
                    planOutput1.append("A plan by this ID " + policyID + " does not exist \n");
                }

            }
        });


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Radio Staus: " + TPPRadio.isSelected() + ", " + CPRadio.isSelected());
                int newPolicyID = genID();
                policyIDField.setText(String.valueOf(newPolicyID));


                if (TPPRadio.isSelected()) {

                    if (containsAnything(policyHolderNameField.getText()) == true && containsAnything(carModelField.getText()) == true && containsAnything(carPriceField.getText()) == true && containsAnything(manufactureField.getText()) == true && containsAnything(claimsField.getText()) == true && containsAnything(commentsField.getText()) == true && containsAnything(ddField.getText()) == true && containsAnything(mmField.getText()) == true && containsAnything(yyField.getText()) == true) {

                        if (isText(carPriceField.getText()) == false && isText(manufactureField.getText()) == false && isText(claimsField.getText()) == false && isText(ddField.getText()) == false && isText(mmField.getText()) == false && isText(yyField.getText()) == false) {


                            String policyHolderName = policyHolderNameField.getText();
                            String CarModel = carModelField.getText();
                            double carPrice = Double.parseDouble(carPriceField.getText());
                            int modelYear = Integer.parseInt(manufactureField.getText());
                            int numClaims = Integer.parseInt(claimsField.getText());
                            String comments = commentsField.getText();
                            int day = Integer.parseInt(ddField.getText());
                            int month = Integer.parseInt(mmField.getText());
                            int year = Integer.parseInt(yyField.getText());


                            String type = (String) typeCombo.getSelectedItem();
                            if (type.equals("SUV")) {
                                try {
                                    insuranceCompany.createThirdPartyPolicy(user.getUserID(), policyHolderName, newPolicyID, new Car(Car.CarType.SUV, CarModel, modelYear, carPrice), numClaims, comments, new MyDate(year, month, day), user.getUserName(), user.getUserPassword());
                                } catch (PolicyException ex) {
                                    throw new RuntimeException(ex);
                                } catch (NameException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } else if (type.equals("HATCH")) {
                                try {
                                    insuranceCompany.createThirdPartyPolicy(user.getUserID(), policyHolderName, newPolicyID, new Car(Car.CarType.HATCH, CarModel, modelYear, carPrice), numClaims, comments, new MyDate(year, month, day), user.getUserName(), user.getUserPassword());
                                } catch (PolicyException ex) {
                                    throw new RuntimeException(ex);
                                } catch (NameException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } else if (type.equals("LUX")) {
                                try {
                                    insuranceCompany.createThirdPartyPolicy(user.getUserID(), policyHolderName, newPolicyID, new Car(Car.CarType.LUX, CarModel, modelYear, carPrice), numClaims, comments, new MyDate(year, month, day), user.getUserName(), user.getUserPassword());
                                } catch (PolicyException ex) {
                                    throw new RuntimeException(ex);
                                } catch (NameException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } else if (type.equals("SED")) {
                                try {
                                    insuranceCompany.createThirdPartyPolicy(user.getUserID(), policyHolderName, newPolicyID, new Car(Car.CarType.SED, CarModel, modelYear, carPrice), numClaims, comments, new MyDate(year, month, day), user.getUserName(), user.getUserPassword());
                                } catch (PolicyException ex) {
                                    throw new RuntimeException(ex);
                                } catch (NameException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            user.printPolicies(insuranceCompany.flatRate, user.userName, user.userPassword);
                        } else {
                            JOptionPane.showMessageDialog(UserPanel, "You have entered text into a digit based field somehwere");
                        }
                    } else{
                        JOptionPane.showMessageDialog(UserPanel, "There is at least one field that is not entered");
                    }
                } else if (CPRadio.isSelected()){

                    if (containsAnything(policyHolderNameField.getText()) == true && containsAnything(carModelField.getText()) == true && containsAnything(carPriceField.getText()) == true && containsAnything(manufactureField.getText()) == true && containsAnything(claimsField.getText()) == true && containsAnything(commentsField.getText()) == true && containsAnything(levelField.getText()) == true && containsAnything(ddField.getText()) == true && containsAnything(mmField.getText()) == true && containsAnything(yyField.getText()) == true) {
                        if (isText(carPriceField.getText()) == false && isText(manufactureField.getText()) == false && isText(claimsField.getText()) == false && isText(ddField.getText()) == false && isText(mmField.getText()) == false && isText(yyField.getText()) == false && isText(commentsField.getText()) == false && isText(levelField.getText()) == false) {


                            String policyHolderName = policyHolderNameField.getText();
                            String CarModel = carModelField.getText();
                            double carPrice = Double.parseDouble(carPriceField.getText());
                            int modelYear = Integer.parseInt(manufactureField.getText());
                            int numClaims = Integer.parseInt(claimsField.getText());
                            int driverAge = Integer.parseInt(commentsField.getText());
                            int level = Integer.parseInt(levelField.getText());
                            int day = Integer.parseInt(ddField.getText());
                            int month = Integer.parseInt(mmField.getText());
                            int year = Integer.parseInt(yyField.getText());


                            String type = (String) typeCombo.getSelectedItem();
                            if (type.equals("SUV")) {
                                try {
                                    insuranceCompany.createComprehensivePolicy(user.getUserID(), policyHolderName, newPolicyID, new Car(Car.CarType.SUV, CarModel, modelYear, carPrice), numClaims, driverAge, level, new MyDate(year, month, day), user.getUserName(), user.getUserPassword());
                                } catch (PolicyException ex) {
                                    throw new RuntimeException(ex);
                                } catch (NameException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } else if (type.equals("HATCH")) {
                                try {
                                    insuranceCompany.createComprehensivePolicy(user.getUserID(), policyHolderName, newPolicyID, new Car(Car.CarType.HATCH, CarModel, modelYear, carPrice), numClaims, driverAge, level, new MyDate(year, month, day), user.getUserName(), user.getUserPassword());
                                } catch (PolicyException ex) {
                                    throw new RuntimeException(ex);
                                } catch (NameException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } else if (type.equals("LUX")) {
                                try {
                                    insuranceCompany.createComprehensivePolicy(user.getUserID(), policyHolderName, newPolicyID, new Car(Car.CarType.LUX, CarModel, modelYear, carPrice), numClaims, driverAge, level, new MyDate(year, month, day), user.getUserName(), user.getUserPassword());
                                } catch (PolicyException ex) {
                                    throw new RuntimeException(ex);
                                } catch (NameException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } else if (type.equals("SED")) {
                                try {
                                    insuranceCompany.createComprehensivePolicy(user.getUserID(), policyHolderName, newPolicyID, new Car(Car.CarType.SED, CarModel, modelYear, carPrice), numClaims, driverAge, level, new MyDate(year, month, day), user.getUserName(), user.getUserPassword());
                                } catch (PolicyException ex) {
                                    throw new RuntimeException(ex);
                                } catch (NameException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            user.printPolicies(insuranceCompany.flatRate, user.userName, user.userPassword);
                        } else {
                            JOptionPane.showMessageDialog(UserPanel, "You have entered text into a digit based field somehwere");
                        }
                    } else {
                        JOptionPane.showMessageDialog(UserPanel, "There is at least one field that is not entered");
                    }
                } else {
                    JOptionPane.showMessageDialog(UserPanel, "A selection of policy type is needed");
                }
                //insuranceCompany.createThirdPartyPolicy(2, "Policy Four", 8, new Car(Car.CarType.LUX, "Ford Ranger", 2022, 190000), 0, "This is a new Policy", new MyDate(2023, 1, 21), "JohnsName", "JohnsPassword");
            }
        });



        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                policyHolderNameField.setText(null);
                carModelField.setText(null);
                carPriceField.setText(null);
                manufactureField.setText(null);
                claimsField.setText(null);
                commentsField.setText(null);
                ddField.setText(null);
                mmField.setText(null);
                yyField.setText(null);
                policyIDField.setText(null);

            }
        });
        EditPD.addComponentListener(new ComponentAdapter() {
        });

        refreshPoliciesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillTable(insuranceCompany, userID);
            }
        });
        TPPRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CPRadio.setSelected(false);
                commentsDriverAge.setText("Comments");
                driverLevelLabel.setVisible(false);
                levelField.setVisible(false);
            }
        });
        CPRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               TPPRadio.setSelected(false);
               commentsDriverAge.setText("Driver Age");
               driverLevelLabel.setVisible(true);
               levelField.setVisible(true);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = allPlansTable.getSelectedRow();
                Object policyID = allPlansTable.getModel().getValueAt(row, 1);
                int test = Integer.parseInt(policyID.toString());
                System.out.println("Selected row is: " + row + "Row 1 ID is: " + test);
                Object typeObject = allPlansTable.getModel().getValueAt(row, 6);
                String typeText = typeObject.toString();
                String type = insuranceCompany.findPolicy(userID, test, user.userName, user.userPassword).getPolicyType();

                String finalType = type;
                SwingUtilities.invokeLater(() -> {
                    JFrame editUI = new JFrame("Policy Edit UI");
                    editUI.setContentPane(new EditPolicyUI(insuranceCompany, test, userID, finalType, "User").editUI);
                    editUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    editUI.pack();
                    editUI.setVisible(true);
                });
                UserPanel.setVisible(false);
            }
        });

        EditPD.addComponentListener(new ComponentAdapter() {
        });
        filterNameRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yearFilter.setEnabled(false);
                monthFilter.setEnabled(false);
                dayFilter.setEnabled(false);
                nameFilterField.setEnabled(true);
                modelFilterField.setEnabled(false);
            }
        });
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillFilterTable(insuranceCompany, userID);
            }
        });

        filterDateRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yearFilter.setEnabled(true);
                monthFilter.setEnabled(true);
                dayFilter.setEnabled(true);
                nameFilterField.setEnabled(false);
                modelFilterField.setEnabled(false);
            }
        });
        filterModelRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yearFilter.setEnabled(false);
                monthFilter.setEnabled(false);
                dayFilter.setEnabled(false);
                nameFilterField.setEnabled(false);
                modelFilterField.setEnabled(true);

            }
        });

        sortAlphabeticlyCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterNameRadioButton.setEnabled(false);
                filterDateRadioButton.setEnabled(false);
                filterModelRadioButton.setEnabled(false);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = insuranceCompany.findUser(userID).userName;
                String userPassword = insuranceCompany.findUser(userID).userPassword;
                int row = allPlansTable.getSelectedRow();
                Object policyID = allPlansTable.getModel().getValueAt(row, 1);
                int test = Integer.parseInt(policyID.toString());
                System.out.println("Selected row is: " + row + "Row 1 ID is: " + test);
                InsurancePolicy policy = insuranceCompany.findPolicy(userID, test, userName, userPassword);

               int option = JOptionPane.showConfirmDialog(EditPD, "Are you sure you want to delete this policy?");
                if (option == 0){
                    insuranceCompany.removePolicy(userID, policy, userName, userPassword);
                    fillTable(insuranceCompany, userID);
                }
            }
        });
        paymentsPerModelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = insuranceCompany.findUser(userID).userName;
                String userPassword = insuranceCompany.findUser(userID).userPassword;
                String result = insuranceCompany.findUser(userID).reportPaymentPerModelToString(userName, userPassword);
                reportsOutput.append(result);
            }
        });
    }

    public void fillTable(InsuranceCompany insuranceCompany, int userID){
        String [] header = {"Policy Holder Name", "Policy ID", "Policy Cost", "CarModel", "CarType", "Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"};
        DefaultTableModel model = new DefaultTableModel(header, 0);
        allPlansTable.setModel(model);

        HashMap<Integer, InsurancePolicy> userPolicies = insuranceCompany.findUser(userID).getHashPolices(insuranceCompany.findUser(userID).userName, insuranceCompany.findUser(userID).userPassword);
        Set<Integer> policyKeys = userPolicies.keySet();

        model.addRow(new Object[]{"Policy Holder Name", "Policy ID", "Policy Cost", "Car Model", "Car Type", "Model Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"});
        for (Integer policy: policyKeys){

            String type = userPolicies.get(policy).getPolicyType();
            if (type.equals("TPP")){
                model.addRow(new Object[]{userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, userPolicies.get(policy).getComments(), null, null, userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
            } else if (type.equals("CP")){
                model.addRow(new Object[]{userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, null, userPolicies.get(policy).getAge(), userPolicies.get(policy).getLevel(), userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
            }

        }

    }

    public void fillFilterTable(InsuranceCompany insuranceCompany, int userID) {
        if (filterNameRadioButton.isSelected()) {
            String name = nameFilterField.getText();
            String userName = insuranceCompany.findUser(userID).getUserName();
            String userPassword = insuranceCompany.findUser(userID).getUserPassword();

            String [] header = {"Policy Holder Name", "Policy ID", "Policy Cost", "CarModel", "CarType", "Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            filterTable.setModel(model);

            HashMap<Integer, InsurancePolicy> userPolicies = insuranceCompany.filterByPolicyHolderName(name, userID, userName, userPassword);
            System.out.println("Holder Name: " + name);
            Set<Integer> policyKeys = userPolicies.keySet();

            model.addRow(new Object[]{"Policy Holder Name", "Policy ID", "Policy Cost", "Car Model", "Car Type", "Model Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"});
            for (Integer policy : policyKeys) {
                String type = userPolicies.get(policy).getPolicyType();
                if (type.equals("TPP")){
                    model.addRow(new Object[]{userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, userPolicies.get(policy).getComments(), null, null, userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
                } else if (type.equals("CP")){
                    model.addRow(new Object[]{userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, null, userPolicies.get(policy).getAge(), userPolicies.get(policy).getLevel(), userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
                }
            }

        } else if (filterDateRadioButton.isSelected()) {
            int year = Integer.parseInt(yearFilter.getText());
            int month = Integer.parseInt(monthFilter.getText());
            int day = Integer.parseInt(dayFilter.getText());
            MyDate searchDate = new MyDate(year, month, day);

            String userName = insuranceCompany.findUser(userID).getUserName();
            String userPassword = insuranceCompany.findUser(userID).getUserPassword();

            String [] header = {"Policy Holder Name", "Policy ID", "Policy Cost", "CarModel", "CarType", "Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            filterTable.setModel(model);

            HashMap<Integer, InsurancePolicy> userPolicies = insuranceCompany.filterByExpiryDate(userID, userName, userPassword, searchDate);
            System.out.println(userPolicies);
            Set<Integer> policyKeys = userPolicies.keySet();

            model.addRow(new Object[]{"Policy Holder Name", "Policy ID", "Policy Cost", "Car Model", "Car Type", "Model Year", "Price", "Comments", "Driver Age", "Driver Level", "Expiry Year", "Expiry Month", "Expiry Day"});
            for (Integer policy : policyKeys) {
                model.addRow(new Object[]{userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, "This is where I would put comments", userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
            }
        } else if (filterModelRadioButton.isSelected()){
            String userName = insuranceCompany.findUser(userID).getUserName();
            String userPassword = insuranceCompany.findUser(userID).getUserPassword();
            String searchModel = modelFilterField.getText();

            String[] header = {"Policy Holder Name", "Policy ID", "Policy Cost", "CarModel", "CarType", "Year", "Price", "Comments", "Expiry Year", "Expiry Month", "Expiry Day"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            filterTable.setModel(model);

            HashMap<Integer, InsurancePolicy> userPolicies = insuranceCompany.filterByCarModel(userID, searchModel, userName, userPassword);
            System.out.println("Car Model Hash: " + userPolicies + ", Car Model: " + searchModel);
            Set<Integer> policyKeys = userPolicies.keySet();

            model.addRow(new Object[]{"Policy Holder Name", "Policy ID", "Policy Cost", "Car Model", "Car Type", "Model Year", "Price", "Comments", "Expiry Year", "Expiry Month", "Expiry Day"});
            for (Integer policy : policyKeys) {
                String type = userPolicies.get(policy).getPolicyType();
                if (type.equals("TPP")){
                    model.addRow(new Object[]{userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, userPolicies.get(policy).getComments(), null, null, userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
                } else if (type.equals("CP")){
                    model.addRow(new Object[]{userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, null, userPolicies.get(policy).getAge(), userPolicies.get(policy).getLevel(), userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
                }
            }

            }else {
            String userName = insuranceCompany.findUser(userID).getUserName();
            String userPassword = insuranceCompany.findUser(userID).getUserPassword();

            String[] header = {"Policy Holder Name", "Policy ID", "Policy Cost", "CarModel", "CarType", "Year", "Price", "Comments", "Expiry Year", "Expiry Month", "Expiry Day"};
            DefaultTableModel model = new DefaultTableModel(header, 0);
            filterTable.setModel(model);
            User user = insuranceCompany.getUser(userID);

            HashMap<Integer, InsurancePolicy> userPolicies = user.sortPoliciesAlph();
            Set<Integer> policyKeys = userPolicies.keySet();

            model.addRow(new Object[]{"Policy Holder Name", "Policy ID", "Policy Cost", "Car Model", "Car Type", "Model Year", "Price", "Comments", "Expiry Year", "Expiry Month", "Expiry Day"});
            for (Integer policy : policyKeys) {
                String type = userPolicies.get(policy).getPolicyType();
                if (type.equals("TPP")){
                    model.addRow(new Object[]{userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, userPolicies.get(policy).getComments(), null, null, userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
                } else if (type.equals("CP")){
                    model.addRow(new Object[]{userPolicies.get(policy).policyHolderName, userPolicies.get(policy).getId(), "$" + userPolicies.get(policy).calcPayment(insuranceCompany.flatRate), userPolicies.get(policy).getCar().getModel(), userPolicies.get(policy).getCar().getType(), userPolicies.get(policy).getCar().getManufacturingYear(), userPolicies.get(policy).getCar().price, null, userPolicies.get(policy).getAge(), userPolicies.get(policy).getLevel(), userPolicies.get(policy).getExpiryDate().year, userPolicies.get(policy).getExpiryDate().month, userPolicies.get(policy).getExpiryDate().day});
                }
            }

        //End of else
        }



    }

    public static boolean isText(String input) {
        //If false be num, true text
        try {
            Double.parseDouble(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean containsAnything(String input){
        if (input.length() >= 1){
            return true;
        } else{
            return false;
        }
    }

    public int genID(){
        int max = 3999999;
        int min = 3000000;
        int genID;

        genID = (int)Math.floor(Math.random() * (max - min + 1) + min);
        //Do a check for id's that exist in the insuranceCompnay or in users?
        return genID;
    }

}
