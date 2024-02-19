//Matthew Bergamini - 8137225


public class ThirdPartyPolicy extends InsurancePolicy{
  protected String comments;

  public ThirdPartyPolicy (String policyHolderName, int id, Car car, int numberOfClaims,  int driverAge, int level, String comments){
    super (policyHolderName, id, car, numberOfClaims);
    this.comments = comments;

  }

  public void print(){
    System.out.println("Policy Holder is: " + policyHolderName +  " Car type is: "  + car.toString() + " Car price is: " + super.car.price + " Comment on this policy is: " + comments + " Number of claims: " + numberOfClaims);
  }
  @Override
  public String toString(){
    return super.toString()+"Policy Holder is: " + policyHolderName +  " Car type is: "  + car.toString() + " Car price is: " + super.car.price + " Comment on this policy is: " + comments + " Number of claims: " + numberOfClaims;
  }

  @Override
  public double calcPayment(double flatRate) {
    return (super.car.price / 50 + numberOfClaims * 200 + flatRate);
  }
  
}
