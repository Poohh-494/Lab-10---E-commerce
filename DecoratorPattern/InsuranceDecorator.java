package DecoratorPattern;
import FactoryMethodPattern.*;
import DataModels.*;

public class InsuranceDecorator extends ShipmentDecorator {

    private final Order order;
    public InsuranceDecorator(Shipment WrappedShipment, Order order) {
        super(WrappedShipment);
        this.order = order;
    }

    @Override
    public String getInfo() {
       return super.getInfo() + " + Inurance";
    }

    @Override
    public double getCost() {
        // ค่าประกัน 10% ของราคาสินค้า
        return super.getCost() * (order.getTotalPrice() * 0.10);
    }
}