//Matthew Bergamini - 8137225


abstract class InsurancePolicy {
  protected String policyHolderName;
  protected int id;
  protected Car car;
  protected int numberOfClaims;

 //The constuctor for the abstract class, it must contain anything things passed in the main process, clears errors there and in the children
  public InsurancePolicy (String policyHolderName, int id, Car car, int numberOfClaims){
    this.policyHolderName = policyHolderName;
    this.id = id;
    this.car = car;
    this.numberOfClaims = numberOfClaims;
  }


  abstract public double calcPayment(double flatRate);

  public void print(){
    System.out.println("Policy Holder is: " + policyHolderName +  " Car type is: "  + car.type + " Car price is: " + car.price);
  }

  public String toString(){
     return "Policy Holder is: " + policyHolderName +  " Car type is: "  + car.type + " Car price is: " + car.price; //Few things, don't have the child classes pointing back here whilst using the print method, and maybe if youre doign that make sure to not have anything you don't want printed written here
    //return null; Litrally just returns null
  }
}
