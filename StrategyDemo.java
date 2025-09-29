

// Strategy interface
interface PaymentStrategy {
    void pay(int amount);
}

// Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card " + cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    public PayPalPayment(String email) {
        this.email = email;
    }
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal (" + email + ")");
    }
}

// Context
class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    public void checkout(int amount) {
        if (paymentStrategy == null) {
            System.out.println("Please select a payment method!");
        } else {
            paymentStrategy.pay(amount);
        }
    }
}

// Client
public class StrategyDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9876"));
        cart.checkout(250);

        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(500);
    }
}
