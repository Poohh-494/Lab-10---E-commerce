package FactoryMethodPattern;

public class ExpressShipment implements Shipment {

    @Override
    public String getInfo() {
        return "Expess Delivery";
    }

    @Override
    public double getCost() {
        return 150.0;
    }
}