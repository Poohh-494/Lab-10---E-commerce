import DataModels.*;
import DecoratorPattern.*;
import FactoryMethodPattern.*;
import ObserverPattern.*;
import StrategyPattern.*;
import java.util.List;

public class ManualTestRunner {
    public static void main(String[] args) {
        System.out.println("--- E-commerce System Simulation ---");

        //--- 1. Setup ---
        Product bed = new Product("P001", "Bed", 2400);
        Product sofa = new Product("P002", "Sofa", 600);
        Order myOrder = new Order("ORD-001", List.of(bed, sofa), "customerEmail");

        OrderCalculator calculator = new OrderCalculator();
        ShipmentFactory shipmentFactory = new ShipmentFactory();
        OrderProcessor orderProcessor = new OrderProcessor();

        InventoryService inventory = new InventoryService();
        EmailService emailer = new EmailService();
        orderProcessor.register(inventory);
        orderProcessor.register(emailer);

        System.out.println("\n--- 2. Testing Strategy Pattern (Discounts) ---");
        double originalPrice = myOrder.getTotalPrice();
        System.out.println("Original Price: " + originalPrice);

        DiscountStrategy tenPercentOff = new PercentageDiscount(10);
        double priceAfterFixed = calculator.calculateFinalPrice(myOrder, tenPercentOff);
        System.out.println("Price with 10% discount: " + priceAfterPercentage);

        DiscountStrategy fiveHundredOff = new FixedDiscount(500);
        double priceAfterFixed = calculator.calculateFinalPrice(myOrder, fiveHundredOff);
        System.out.println("Price with 500 THB discount: " + priceAfterFixed);

        System.out.println("\n--- 3. Testing Factory and Decorator Patterns (Shipment) ---");
        //สร้างแบบจัดส่งแบบมาตรฐาน
        Shipment standardShipment = shipmentFactory.createShipment("STANDARD");
        System.out.println("Base Shipment: " + standardShipment.getInfo() + ", Cost: " + standardShipment);

        //"ห่อ" ด้วยบริการห่อของขวัญ
        Shipment giftWrapped = new GiftWrapDecorator(standardShipment);
        System.out.println("Decorated: " + giftWrapped.getInfo() + ", Cost: " + giftWrapped.getCost());

        //"ห่อ" ทับด้วยบริการประกันสินค้า
        Shipment fullyloaded = new InsuranceDecorator(giftWrapped, myOrder);
        System.out.println("Decorated: " + fullyloaded.getInfo() + ", Cost: " + fullyloaded.getCost());

        System.out.println("\n--- 4. Printing Final Summary ---");
        double finalPrice = priceAfterPercentage; // สมมติว่าใช้ส่วนลด 10%
        double totalCost = finalPrice + fullyloaded.getCost();
        System.out.println("Final price after discount: " + finalPrice);
        System.out.println("Final shipment cost: " + fullyloaded.getCost());
        System.out.println("TOTAL TO PAY: " + totalCost);

        // --- 5. Testing Observer Pattern (Processing Order) ---
        orderProcessor.processOrder(myOrder);
    }
}
