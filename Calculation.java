public class Calculation {
    public static void main(String[] args) {
        try {
            int result = addNumbers(5, Integer.parseInt("10"));
            System.out.println("Result is: " + result);

            int divisionResult = divideNumbers(result, 1);
            System.out.println("Division Result is: " + divisionResult);

            int[] array = {1, 2, 3};
            int index = 2;
            if (index >= 0 && index < array.length) {
                System.out.println("Array element: " + array[index]);
            } else {
                System.err.println("Index out of bounds: " + index);
            }

            String text = null;
            modifyString(text);

            // Potential problem: ClassCastException
            Integer obj = Integer.valueOf(123);
            printString(obj);

        } catch (NumberFormatException e) {
            System.err.println("Number format exception occurred");
        } catch (ArithmeticException e) {
            System.err.println("Arithmetic exception occurred: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Array index out of bounds: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Null pointer exception: " + e.getMessage());
        }
    }

    public static int addNumbers(int a, int b) {
        return a + b;
    }

    public static int divideNumbers(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }

    public static void modifyString(String str) {
        if (str != null) {
            System.out.println(str.toUpperCase());
        } else {
            System.err.println("String is null, cannot modify");
        }
    }

    

public static void printString(Object obj) {
    if (obj instanceof String) {
        String str = (String) obj;
        System.out.println(str);
    } else {
        System.err.println("Object is not a String: " + obj);
    }
}
}