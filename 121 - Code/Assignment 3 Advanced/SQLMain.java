import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class SQLMain {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/121DB";
    private static final String USERNAME = "121Code";
    private static final String PASSWORD = "121Code";

    public static void saveToDB(InsuranceCompany insuranceCompany) {
        // Create a connection
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {

            // Create a statement
            try (Statement statement = connection.createStatement()) {

                //Have to create string to put command, not quite like workspace
                String createUserTable = "CREATE TABLE IF NOT EXISTS users ("
                        + "userID INT PRIMARY KEY,"
                        + "Name VARCHAR(225),"
                        + "StreetNumber INT,"
                        + "Street VARCHAR(225),"
                        + "Suburb VARCHAR(225),"
                        + "City VARCHAR(225),"
                        + "userName VARCHAR(225),"
                        +  "userPassword VARCHAR(225)"
                        + ")";

                statement.executeUpdate(createUserTable);

                HashMap<Integer, User> allUsers = insuranceCompany.getUsersHash();
                Set<Integer> userKeys = allUsers.keySet();
                System.out.println(userKeys);

                for (Integer ID : userKeys) {
                    String name = allUsers.get(ID).getName();
                    int userID = allUsers.get(ID).getUserID();
                    System.out.println("UserID is: " + userID);
                    int streetNum = allUsers.get(ID).getAddress().getStreetNum();
                    String street = allUsers.get(ID).getAddress().getStreet();
                    String suburb = allUsers.get(ID).getAddress().getSuburb();
                    String city = allUsers.get(ID).getAddress().getCity();
                    String userName = allUsers.get(ID).getUserName();
                    String userPassword = allUsers.get(ID).getUserPassword();

                    String addUser = "INSERT INTO users (userID, Name, StreetNumber, Street, Suburb, City, userName, userPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    try (PreparedStatement preparedStatement = connection.prepareStatement(addUser)) {
                        preparedStatement.setInt(1, userID);
                        preparedStatement.setString(2, name);
                        preparedStatement.setInt(3, streetNum);
                        preparedStatement.setString(4, street);
                        preparedStatement.setString(5, suburb);
                        preparedStatement.setString(6, city);
                        preparedStatement.setString(7, userName);
                        preparedStatement.setString(8, userPassword);

                        preparedStatement.executeUpdate();

                        System.out.println("Data for UserID " + userID + " inserted successfully.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


                String createPolicyTable = "CREATE TABLE IF NOT EXISTS policies ("
                        + "userID INT,"
                        + "policyID INT PRIMARY KEY,"
                        + "PolicyHolderName VARCHAR(225),"
                        + "CarModel VARCHAR(225),"
                        + "CarPrice DOUBLE,"
                        + "ModelYear INT,"
                        + "NumClaims INT,"
                        + "PolicyType VARCHAR(50),"
                        + "DateYear INT,"
                        + "DateMonth INT,"
                        + "DateDay INT,"
                        + "DriverAge INT,"
                        + "Level INT,"
                        + "Comments VARCHAR(255),"
                        + "CarType VARCHAR(225)"
                        + ")";
                statement.executeUpdate(createPolicyTable);


                allUsers.forEach((id, user) -> {
                    HashMap<Integer, InsurancePolicy> userPolicies = user.getHashPolices(user.userName, user.userPassword);
                    userPolicies.forEach((policyID, policy) -> {
                        int userID = id;
                        int thisPolicyID = policyID;
                        String policyHolderName = policy.policyHolderName;
                        String carModel = policy.getCar().getModel();
                        double carPrice = policy.getCar().getPrice();
                        int modelYear = policy.getCar().getManufacturingYear();
                        int numClaims = policy.getNumberOfClaims();
                        String policyType = policy.getPolicyType();
                        int dateYear = policy.getExpiryDate().getYear();
                        int dateMonth = policy.getExpiryDate().getMonth();
                        int dateDay = policy.getExpiryDate().getDay();
                        String carType = String.valueOf(policy.getCar().type);

                        if (policyType.equals("TPP")) {
                            String comments = policy.getComments();

                            String addPolicyTPP = "INSERT INTO policies (userID, policyID, PolicyHolderName, CarModel, CarPrice, ModelYear, NumClaims, PolicyType, DateYear, DateMonth, DateDay, Comments, CarType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                            try (PreparedStatement preparedStatement = connection.prepareStatement(addPolicyTPP)) {
                                preparedStatement.setInt(1, userID);
                                preparedStatement.setInt(2, thisPolicyID);
                                preparedStatement.setString(3, policyHolderName);
                                preparedStatement.setString(4, carModel);
                                preparedStatement.setDouble(5, carPrice);
                                preparedStatement.setInt(6, modelYear);
                                preparedStatement.setInt(7, numClaims);
                                preparedStatement.setString(8, policyType);
                                preparedStatement.setInt(9, dateYear);
                                preparedStatement.setInt(10, dateMonth);
                                preparedStatement.setInt(11, dateDay);
                                preparedStatement.setString(12, comments);
                                preparedStatement.setString(13, carType);

                                preparedStatement.executeUpdate();

                                System.out.println("Data for policyID " + thisPolicyID + " (TPP) inserted successfully.");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else if (policyType.equals("CP")) {
                            int driverAge = policy.getAge();
                            int level = policy.getLevel();

                            String addPolicyCP = "INSERT INTO policies (userID, policyID, PolicyHolderName, CarModel, CarPrice, ModelYear, NumClaims, PolicyType, DateYear, DateMonth, DateDay, DriverAge, Level, CarType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                            try (PreparedStatement preparedStatement = connection.prepareStatement(addPolicyCP)) {
                                preparedStatement.setInt(1, userID);
                                preparedStatement.setInt(2, thisPolicyID);
                                preparedStatement.setString(3, policyHolderName);
                                preparedStatement.setString(4, carModel);
                                preparedStatement.setDouble(5, carPrice);
                                preparedStatement.setInt(6, modelYear);
                                preparedStatement.setInt(7, numClaims);
                                preparedStatement.setString(8, policyType);
                                preparedStatement.setInt(9, dateYear);
                                preparedStatement.setInt(10, dateMonth);
                                preparedStatement.setInt(11, dateDay);
                                preparedStatement.setInt(12, driverAge);
                                preparedStatement.setInt(13, level);
                                preparedStatement.setString(14, carType);

                                preparedStatement.executeUpdate();

                                System.out.println("Data for policyID " + thisPolicyID + " (CP) inserted successfully.");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });



                });
                System.out.println("Table created successfully.");


            } catch (SQLException e) {
                System.err.println("Error creating statement: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }


    public static void loadFromDB(InsuranceCompany insuranceCompany){
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {

            HashMap<Integer, User> userHash = insuranceCompany.getUsersHash();
            ArrayList<Integer> userIDs = insuranceCompany.getUserIDs();
            for (Integer id: userIDs){
                insuranceCompany.removeUser(id);
                System.out.println("User and policies removed");
            }


            // Create a statement
            try (Statement statement = connection.createStatement()) {

                String userSelect = "SELECT * FROM users";

                try (PreparedStatement preparedStatement = connection.prepareStatement(userSelect)){
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()){
                        int userID = resultSet.getInt("userID");
                        String name = resultSet.getString("Name");
                        int streetNum = resultSet.getInt("StreetNumber");
                        String street = resultSet.getString("Street");
                        String suburb = resultSet.getString("Suburb");
                        String city = resultSet.getString("City");
                        String userName = resultSet.getString("userName");
                        String userPassword = resultSet.getString("userPassword");

                        insuranceCompany.addUser(name, userID, new Address(streetNum, street, suburb, city), userName, userPassword);
                    }
                }




                String selectQuery = "SELECT * FROM policies";

                try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        int userID = resultSet.getInt("userID");
                        int policyID = resultSet.getInt("policyID");
                        String policyHolderName = resultSet.getString("PolicyHolderName");
                        String carModel = resultSet.getString("CarModel");
                        double carPrice = resultSet.getDouble("CarPrice");
                        int modelYear = resultSet.getInt("ModelYear");
                        int numClaims = resultSet.getInt("NumClaims");
                        String policyType = resultSet.getString("PolicyType");
                        int dateYear = resultSet.getInt("DateYear");
                        int dateMonth = resultSet.getInt("DateMonth");
                        int dateDay = resultSet.getInt("DateDay");
                        String carType = resultSet.getString("CarType");

                        String userName = insuranceCompany.findUser(userID).userName;
                        String userPassword = insuranceCompany.findUser(userID).userPassword;

                        if (policyType.equals("TPP")) {
                            String comments = resultSet.getString("Comments");

                            try {
                                if (carType.equals("LUX")){
                                    insuranceCompany.addPolicy(userID, new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.LUX, carModel, modelYear, carPrice), numClaims, comments, new MyDate(dateYear, dateMonth, dateDay)), userName, userPassword);
                                } else if (carType.equals("HATCH")){
                                    insuranceCompany.addPolicy(userID, new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, modelYear, carPrice), numClaims, comments, new MyDate(dateYear, dateMonth, dateDay)), userName, userPassword);
                                } else if (carType.equals("SED")){
                                    insuranceCompany.addPolicy(userID, new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.SED, carModel, modelYear, carPrice), numClaims, comments, new MyDate(dateYear, dateMonth, dateDay)), userName, userPassword);
                                } else if (carType.equals("SUV")){
                                    insuranceCompany.addPolicy(userID, new ThirdPartyPolicy(policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, modelYear, carPrice), numClaims, comments, new MyDate(dateYear, dateMonth, dateDay)), userName, userPassword);
                                }
                            } catch (PolicyException e) {
                                throw new RuntimeException(e);
                            } catch (NameException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (policyType.equals("CP")) {
                            int driverAge = resultSet.getInt("DriverAge");
                            int level = resultSet.getInt("Level");

                            try {
                                if (carType.equals("SED")){
                                    insuranceCompany.addPolicy(userID, new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.SED, carModel, modelYear, carPrice), numClaims, driverAge, level, new MyDate(dateYear, dateMonth, dateDay)), userName, userPassword);
                                } else if (carType.equals("SUV")){
                                    insuranceCompany.addPolicy(userID, new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.SUV, carModel, modelYear, carPrice), numClaims, driverAge, level, new MyDate(dateYear, dateMonth, dateDay)), userName, userPassword);
                                } else if (carType.equals("HATCH")){
                                    insuranceCompany.addPolicy(userID, new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.HATCH, carModel, modelYear, carPrice), numClaims, driverAge, level, new MyDate(dateYear, dateMonth, dateDay)), userName, userPassword);
                                } else if (carType.equals("LUX")){
                                    insuranceCompany.addPolicy(userID, new ComprehensivePolicy(policyHolderName, policyID, new Car(Car.CarType.LUX, carModel, modelYear, carPrice), numClaims, driverAge, level, new MyDate(dateYear, dateMonth, dateDay)), userName, userPassword);
                                }
                            } catch (PolicyException e) {
                                throw new RuntimeException(e);
                            } catch (NameException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            } catch (SQLException e) {
                System.err.println("Error creating statement: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
