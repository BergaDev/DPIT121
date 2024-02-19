//Matthew Bergamini - 8137225


public class ThirdPartyPolicy extends InsurancePolicy{
  protected String comments;

  public ThirdPartyPolicy (String policyHolderName, int id, Car car, int numberOfClaims, String comments, MyDate expiryDate){
    super (policyHolderName, id, car, numberOfClaims, expiryDate);
    this.comments = comments;

  }

  public void print(){
    System.out.println("Policy Holder is: " + policyHolderName + ", PolicyID is: " + id +  ", Car type is: "  + car.toString() + ", Comment on this policy is: " + comments + ", Number of claims: " + numberOfClaims + ", Expiry Date: " + expiryDate.toString());
  }
  @Override
  public String toString(){
    return super.toString()+"Policy Holder is: " + policyHolderName + ", PolicyID is: " + id +  ", Car type is: "  + car.toString() + ", Comment on this policy is: " + comments + ", Number of claims: " + numberOfClaims + ", Expiry Date: " + expiryDate.toString();
  }

  @Override
  public double calcPayment(double flatRate) {
    return (super.car.price / 50 + numberOfClaims * 200 + flatRate);
  }
  
}
