package lld;

import java.util.ArrayList;
import java.util.List;

abstract class Menu {
    protected String name;
    protected double price;

    Menu(String name, double price) {
        this.name = name;
        this.price = price;
    }

    abstract String describe();
}

class Pizza extends Menu {
    String toppings;

    Pizza(String name, double price, String toppings) {
        super(name, price);
        this.toppings = toppings;
    }

    @Override
    String describe() {
        return name + " pizza with " + String.join(", ", toppings) + " - Rs" + price;
    }
}

class Beverage extends Menu {
    String size;

    Beverage(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    String describe() {
        return name + " beverage with " + String.join(", ", size) + " - Rs" + price;
    }
}

interface Payment {
    boolean pay();
}

class CashPayment implements Payment {

    @Override
    public boolean pay() {
        System.out.println("Cash Payment: Thanks");
        return true;
    }
}

class CardPayment implements Payment {
    private String cardNumber;

    CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay() {
        System.out.println("Charging card ending in " + cardNumber.substring(cardNumber.length() - 4));
        return true;
    }
}

class Order {
    List<Menu> menuList = new ArrayList<>();

    public void addToCart(Menu menu) {
        menuList.add(menu);
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Menu item : menuList) {
            total += item.price;
        }
        return total;
    }

    public boolean pay(Payment paymentMethod) {
        return paymentMethod.pay();
    }
}

class Restaurant {
    public boolean processOrder(Order order, Payment payment) {
        double total = order.getTotalPrice();
        System.out.println("Order total: Rupees " + total);
        return order.pay(payment);
    }
}

public class RestaurantOrderingSystemLLDMain {
    public static void main(String[] args) {
        Payment cardPayment = new CardPayment("12345678");

        Pizza pizza = new Pizza("Corn", 399.00, "cheese");
        System.out.println(pizza.describe());
        Beverage beverage = new Beverage("Cold drink", 99.00, "small");
        System.out.println(beverage.describe());

        Order order = new Order();
        order.addToCart(pizza);
        order.addToCart(beverage);

        Restaurant restaurant = new Restaurant();
        boolean success = restaurant.processOrder(order, cardPayment);

        if (success) {
            System.out.println("Thank you!");
        } else {
            System.out.println("Payment failed. Please try another method.");
        }
    }
}
