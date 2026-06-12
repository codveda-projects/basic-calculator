import java.util.Scanner;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            System.out.println("=== Advanced Calculator ===");

            while (running) {
                System.out.print("Enter expression (or type 'exit' to quit): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    running = false;
                    System.out.println("Calculator closed. Goodbye!");
                } else {
                    try {
                        Expression expression = new ExpressionBuilder(input).build();
                        double result = expression.evaluate();
                        System.out.println("Result: " + result);
                    } catch (Exception e) {
                        System.out.println("Invalid expression. Please try again.");
                    }
                }
            }
        }
    }
}
