//Matthew Bergamini - 8137225

import java.io.Serializable;

public class ThirdPartyPolicy extends InsurancePolicy implements Serializable{

  protected String comments;

  public ThirdPartyPolicy (String policyHolderName, int id, Car car, int numberOfClaims, String comments, MyDate expiryDate) throws PolicyException, NameException{
    super (policyHolderName, id, car, numberOfClaims, expiryDate);
    this.comments = comments;

  }

  /*
  ThirdPartyPolicy (ThirdPartyPolicy tppCopy){
    this.comments = tppCopy.comments;
  }
  */

  public void print(){
    System.out.println("Policy Holder is: " + policyHolderName + ", PolicyID is: " + id +  ", Car type is: "  + car.toString() + ", Comment on this policy is: " + comments + ", Number of claims: " + numberOfClaims + ", Expiry Date: " + expiryDate.toString());
  }
  @Override
  public String toString(){
    return super.toString()+"Policy Holder is: " + policyHolderName + ", PolicyID is: " + id +  ", Car type is: "  + car.toString() + ", Comment on this policy is: " + comments + ", Number of claims: " + numberOfClaims + ", Expiry Date: " + expiryDate.toString();
  }

  @Override
  public double calcPayment(double flatRate) {
    return (super.car.price / 100 + numberOfClaims * 200 + flatRate);
  }

  @Override
  public int getAge() {
    return 0;
  }

  @Override
  public int getLevel(){
    return 0;
  }

  public String toDelimitedString(){
    return "TPP," + super.toDelimitedString() + "," + comments;
  }

  public String getPolicyType(){
    return "TPP";
  }

  @Override
  public String getComments(){
    return comments;
  }
  
}
