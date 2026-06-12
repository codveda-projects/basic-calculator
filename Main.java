import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Basic Calculator ===");

        while (running) {
            System.out.print("Enter EXPRESSION (or type 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                running = false;
                System.out.println("Calculator closed. Goodbye!");
            } else {
                try {
                    double result = evaluateExpression(input);
                    System.out.println("Result: " + result);
                } catch (Exception e) {
                    System.out.println("Invalid expression. Please try again.");
                }
            }
        }

        scanner.close();
    }

    // Evaluate full expression
    public static double evaluateExpression(String expr) {
        List<String> tokens = tokenize(expr);
        List<String> postfix = toPostfix(tokens);
        return evalPostfix(postfix);
    }

    // Tokenize input string
    private static List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                number.append(c);
            } else if ("+-*/()".indexOf(c) != -1) {
                if (number.length() > 0) {
                    tokens.add(number.toString());
                    number.setLength(0);
                }
                tokens.add(String.valueOf(c));
            } else if (c == ' ') {
                if (number.length() > 0) {
                    tokens.add(number.toString());
                    number.setLength(0);
                }
            }
        }
        if (number.length() > 0) {
            tokens.add(number.toString());
        }
        return tokens;
    }

    // Convert infix to postfix (Shunting Yard)
    private static List<String> toPostfix(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        Map<String, Integer> precedence = Map.of(
            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2
        );

        for (String token : tokens) {
            if (token.matches("\\d+(\\.\\d+)?")) {
                output.add(token);
            } else if (precedence.containsKey(token)) {
                while (!stack.isEmpty() && precedence.containsKey(stack.peek())
                        && precedence.get(stack.peek()) >= precedence.get(token)) {
                    output.add(stack.pop());
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop(); 
            }
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }

    // Evaluate postfix expression
    private static double evalPostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix) {
            if (token.matches("\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                }
            }
        }

        return stack.pop();
    }
}
