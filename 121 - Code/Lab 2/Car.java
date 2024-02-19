//Matthew Bergamini - 8137225

import java.util.ArrayList;

public class Car {
 
  public enum CarType {SUV,SED,LUX,HATCH};
  private String model;
  private int ManufacturingYear;
  protected double price;
  protected CarType type;
  

  public Car (CarType type, String model, int ManufacturingYear, double price){
    this.type = type;
    this.model = model; 
    this.ManufacturingYear = ManufacturingYear;
    this.price = price;

  }
  //This is a set, it's a (this.thing = thing) thing 
  public void setType(CarType type){
    this.type = type;
  }
  public void setPrice(double price){
    this.price = price;
  }
  public void setManufacturingYear(int ManufacturingYear){
    this.ManufacturingYear = ManufacturingYear;
  }
  public void setModel(String model){
    this.model = model;
  }
  //This is a get, it returns the variable when promoted
  public CarType getType(){
    return type;
  }
  public double getPrice(){
    return price;
  }
  public int getManufacturingYear(){
    return ManufacturingYear;
  }
  public String getModel(){
    return model;
  }

  public String toString(){
    return "Car Type: " + type + " Model: " + model + " Manufacturing Year: " + ManufacturingYear + " Car Price: " + price;
  }

  public void print(){
    System.out.println("Car Type: " + type + " Model: " + model + " Manufacturing Year: " + ManufacturingYear + " Car Price: " + price);
  }

  //Price rise method/ No STATIC!
  public void priceRise(double risePercent){
    //car.setPrice(car.getPrice()*(1+risePrecent));
    //Is just price, no need to do car.price or anything 
    double priceBefore = (price * (1 + risePercent));
    //price = (price * (1 + risePercent));
    price = Math.round(priceBefore * 100);
    price = price/100;
  }

  static ArrayList<InsurancePolicy> filterByCarModel (ArrayList<InsurancePolicy>policies, String carModel){
    ArrayList<InsurancePolicy> filteredCars = new ArrayList<InsurancePolicy>();
    for (InsurancePolicy policy:policies){
      if (policy.car.model.contains(carModel)){
        filteredCars.add(policy);
      }
    }
    return filteredCars;
  }


}
