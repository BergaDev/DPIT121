//Matthew Bergamini - 8137225


public class ComprehensivePolicy extends InsurancePolicy {
  protected int driverAge;
  protected int level;


  public ComprehensivePolicy (String policyHolderName, int id, Car car, int numberOfClaims,  int driverAge, int level, MyDate expiryDate){
    super (policyHolderName, id, car, numberOfClaims, expiryDate);
    this.driverAge = driverAge;
    this.level = level;

  }

  @Override
  public void print(){
    System.out.println("Policy Holder is: " + policyHolderName +  ", Car type is: "  + car.toString() + ", Driver age is: " + driverAge + ", Level of policy is: " + level + ", Expiry Date is: " + expiryDate);
  }

  @Override
  public String toString(){
    return super.toString()+"Policy Holder is: " + policyHolderName +  ", Car type is: "  + car.toString() +  ", Driver age is: " + driverAge + ", Level of policy is: " + level + ", Expiry Date is: " + expiryDate;
  }

  @Override
  public double calcPayment(double flatRate){
    if (driverAge < 30){
      return ((super.car.price / 50 + numberOfClaims * 200 + flatRate) + ((30 - driverAge) * 50));
    } else {
      return super.car.price / 50 + numberOfClaims * 200 + flatRate;
    }
  }
 
}
