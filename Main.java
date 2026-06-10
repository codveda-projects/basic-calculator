public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Calculator calc = new Calculator();

            System.out.println("=== Basic Calculator ===");
            System.out.print("Enter first number: ");
            double num1 = scanner.nextDouble();

            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();

            System.out.print("Choose operation (+, -, *, /): ");
            char operation = scanner.next().charAt(0);

            if (operation == '+') {
                System.out.println("Result: " + calc.add(num1, num2));
            } else if (operation == '-') {
                System.out.println("Result: " + calc.subtract(num1, num2));
            } else if (operation == '*') {
                System.out.println("Result: " + calc.multiply(num1, num2));
            } else if (operation == '/') {
                System.out.println("Result: " + calc.divide(num1, num2));
            } else {
                System.out.println("Invalid operation selected.");
            }
        }
    }
}

class Calculator {
    public double add(double a, double b) { return a + b; }
    public double subtract(double a, double b) { return a - b; }
    public double multiply(double a, double b) { return a * b; }
    public double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero is not allowed.");
            return Double.NaN;
        }
        return a / b;
    }
}
