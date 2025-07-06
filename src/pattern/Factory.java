package pattern;

class Factory {

  public static void main(String[] args) {
    PaymentService paymentService = PaymentFactory.createPaymentService("Google");
    System.out.println(paymentService.processPayment());
  }

}


interface PaymentService {
  String processPayment();
}

class GooglePaymentService implements PaymentService {

  @Override
  public String processPayment() {
    return "Pagamento processado pela Google";
  }

}


class PayPalPaymentService implements PaymentService {

  @Override
  public String processPayment() {
    return "Pagamento processado pelo PayPal";
  }

}


class ApplePaymentService implements PaymentService {

  @Override
  public String processPayment() {
    return "Pagamento processado pela Apple";
  }

}


class PaymentFactory {

  static PaymentService createPaymentService(String paymentMethod) {
    switch (paymentMethod.toUpperCase()) {
      case "GOOGLE":
        return new GooglePaymentService();
      case "PAYPAL":
        return new PayPalPaymentService();
      case "APPLE":
        return new ApplePaymentService();
      default:
        throw new IllegalArgumentException("Método de pagamento inválido");
    }
  }

}
