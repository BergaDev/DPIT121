//Matthew Bergamini - 8137225


public class Car {
 
  public enum CarType {SUV,SED,LUX,HATCH};
  private String model;
  private int ManufacturingYear;
  protected double price;
  protected CarType type;
  //Need to move these out of static, breaks other things 

  public Car (CarType type, String model, int ManufacturingYear, double price){
    this.type = type;
    this.model = model; 
    this.ManufacturingYear = ManufacturingYear;
    this.price = price;

  }
}
