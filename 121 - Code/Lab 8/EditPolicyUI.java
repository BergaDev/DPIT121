import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class EditPolicyUI {
    JPanel editUI;
    private JButton stopEditing;
    private JTextField policyIDField;
    private JTextField policyHolderField;
    private JTextField carModelField;
    private JTextField yearManufactureField;
    private JTextField carPriceField;
    private JTextField numClaimsField;
    private JTextField commentsField;
    private JTextField dayField;
    private JTextField monthField;
    private JTextField yearField;
    private JTextField driverLevelField;
    private JLabel levelLabel;
    private JLabel sharedCommentsLabel;
    private JRadioButton TPPRadio;
    private JRadioButton CPRadio;
    private JButton updateButton;
    private JComboBox typeCombo;

    public EditPolicyUI(InsuranceCompany insuranceCompany, int policyID, int userID, String type, String fromWhere) {
        User user = insuranceCompany.getUser(userID);
        String userName = insuranceCompany.findUser(userID).userName;
        String userPassword = insuranceCompany.findUser(userID).userPassword;

        //On load





        policyIDField.setText(String.valueOf(policyID));
        policyIDField.setEnabled(false);
        policyHolderField.setText(insuranceCompany.findPolicy(userID, policyID, userName, userPassword).policyHolderName);
        carModelField.setText(insuranceCompany.findPolicy(userID, policyID, userName, userPassword).getCar().getModel());
        carPriceField.setText(String.valueOf(insuranceCompany.findPolicy(userID, policyID, userName, userPassword).getCar().getPrice()));
        yearManufactureField.setText(String.valueOf(insuranceCompany.findPolicy(userID, policyID, userName, userPassword).getCar().getManufacturingYear()));
        numClaimsField.setText(String.valueOf(insuranceCompany.findPolicy(userID, policyID, userName, userPassword).numberOfClaims));
        commentsField.setText(insuranceCompany.findPolicy(userID, policyID, userName, userPassword).getComments());
        dayField.setText(String.valueOf(insuranceCompany.findPolicy(userID, policyID, userName, userPassword).getExpiryDate().day));
        monthField.setText(String.valueOf(insuranceCompany.findPolicy(userID, policyID, userName, userPassword).getExpiryDate().month));
        yearField.setText(String.valueOf(insuranceCompany.findPolicy(userID, policyID, userName, userPassword).getExpiryDate().year));
        //Setting the type items
        driverLevelField.setVisible(false);
        levelLabel.setVisible(false);

        if (type.equals("CP")){
            driverLevelField.setVisible(false);
            levelLabel.setVisible(false);
            sharedCommentsLabel.setText("Driver Age");
            String driverAgeString = String.valueOf(insuranceCompany.findPolicy(userID, policyID, userName, userPassword).getAge());
            commentsField.setText(driverAgeString);
            System.out.println("Driver Age: " + String.valueOf(insuranceCompany.findPolicy(userID, policyID, userName, userPassword).getAge()));
            levelLabel.setVisible(true);
            driverLevelField.setVisible(true);
            driverLevelField.setText(String.valueOf(insuranceCompany.findPolicy(userID, policyID, userName, userPassword).getLevel()));
        }





        //Action based stuff
        stopEditing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (fromWhere.equals("User")) {
                    SwingUtilities.invokeLater(() -> {
                        JFrame userPanel = new JFrame("User Menu UI");
                        userPanel.setContentPane(new UserMenuUI(insuranceCompany, userID).UserPanel);
                        userPanel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        userPanel.pack();
                        userPanel.setVisible(true);
                    });
                    editUI.setVisible(false);
                } else if (fromWhere.equals("Admin")){
                    SwingUtilities.invokeLater(() -> {
                        JFrame adminFrame = new JFrame("AdminMenuUI");
                        adminFrame.setContentPane(new AdminMenuUI(insuranceCompany).AdminPanel);
                        adminFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        adminFrame.pack();
                        adminFrame.setVisible(true);
                    });
                    editUI.setVisible(false);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (containsAnything(policyHolderField.getText()) == true && containsAnything(carModelField.getText()) == true && containsAnything(carPriceField.getText()) == true && containsAnything(yearManufactureField.getText()) == true && containsAnything(numClaimsField.getText()) == true && containsAnything(commentsField.getText()) == true && containsAnything(dayField.getText()) == true && containsAnything(monthField.getText()) == true && containsAnything(yearField.getText()) == true) {

                        if (isText(carPriceField.getText()) == false && isText(yearManufactureField.getText()) == false && isText(numClaimsField.getText()) == false && isText(dayField.getText()) == false && isText(monthField.getText()) == false && isText(yearField.getText()) == false) {

                            String policyHolderName = policyHolderField.getText();
                            String CarModel = carModelField.getText();
                            double carPrice = Double.parseDouble(carPriceField.getText());
                            int modelYear = Integer.parseInt(yearManufactureField.getText());
                            int numClaims = Integer.parseInt(numClaimsField.getText());
                            String comments = commentsField.getText();
                            int day = Integer.parseInt(dayField.getText());
                            int month = Integer.parseInt(monthField.getText());
                            int year = Integer.parseInt(yearField.getText());

                            if (Pattern.matches("\\b[A-Z][a-zA-Z]*\\s[A-Z][a-zA-Z]*\\b", policyHolderName)){
                                System.out.println("Name is verified: " + policyHolderName);
                                user.getPolicy(policyID, userName, userPassword).setPolicyHolderName(policyHolderName);
                                user.getPolicy(policyID, userName, userPassword).setNumberOfClaims(numClaims);
                                user.getPolicy(policyID, userName, userPassword).setExpiryDate(new MyDate(year, month, day));


                                String type = (String) typeCombo.getSelectedItem();
                                if (type.equals("SUV")) {
                                    user.getPolicy(policyID, userName, userPassword).setCar(new Car(Car.CarType.SUV, CarModel, modelYear, carPrice));
                                } else if (type.equals("HATCH")) {
                                    user.getPolicy(policyID, userName, userPassword).setCar(new Car(Car.CarType.HATCH, CarModel, modelYear, carPrice));
                                } else if (type.equals("LUX")) {
                                    user.getPolicy(policyID, userName, userPassword).setCar(new Car(Car.CarType.LUX, CarModel, modelYear, carPrice));
                                } else if (type.equals("SED")) {
                                    user.getPolicy(policyID, userName, userPassword).setCar(new Car(Car.CarType.SED, CarModel, modelYear, carPrice));
                                }
                                user.printPolicies(insuranceCompany.flatRate, user.userName, user.userPassword);
                            } else {
                                System.out.println("Error in input or if: " + policyHolderName);
                                JOptionPane.showMessageDialog(editUI, "Policy Holder Name requires two words, first letter capitalised");
                            }


                        } else {
                            JOptionPane.showMessageDialog(editUI, "You have entered text into a digit based field somehwere");
                        }
                    } else{
                        JOptionPane.showMessageDialog(editUI, "There is at least one field that is not entered");
                    }
                }




        });
    }

    public static boolean containsAnything(String input){
        if (input.length() >= 1){
            return true;
        } else{
            return false;
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
}
