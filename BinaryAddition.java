public class BinaryAddition {

    public static String addBinaryStrings(String a, String b) {
        // Input validation, ensuring a and b are non-empty binary string
        if (a.isEmpty() || b.isEmpty()) {
            throw new IllegalArgumentException("Empty binary string not allowed.");
        } 
        if (!isValidBinaryString(a) || !isValidBinaryString(b)) {
            throw new IllegalArgumentException("Invalid binary string input.");
        }
        
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;  // Used for carrying over values in addition

        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            carry = sum > 1 ? 1 : 0;    // Calculate carry for next iteration
            result.append(sum % 2); // Append result of teh sum % 2
        }

        if (carry != 0) result.append(carry);
        return result.reverse().toString();
    }

    // Check if string is a valid binary
    public static boolean isValidBinaryString(String str) {
        for (char c : str.toCharArray()) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }

    // Convert binary to decimal integer
    public static int toDecimal(String binary) {
        int decimal = 0;
        int length = binary.length();
        for (int i = 0; i < length; i++) {
            int bitValue = binary.charAt(i) - '0';
            decimal += bitValue * Math.pow(2, length - 1 - i);
        }
        return decimal;
    }

    public static String subtractBinaryStrings(String a, String b) {
        // Input validation
        if (a.isEmpty() || b.isEmpty()) {
            throw new IllegalArgumentException("Empty binary string not allowed.");
        }
        if (!isValidBinaryString(a) || !isValidBinaryString(b)) {
            throw new IllegalArgumentException("Invalid binary string input.");
        }
    
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int borrow = 0;
    
        while (i >= 0 || j >= 0) {
            int bitA = i >= 0 ? a.charAt(i) - '0' : 0;
            int bitB = j >= 0 ? b.charAt(j) - '0' : 0;
            bitB = bitB + borrow;
    
            if (bitA < bitB) {
                borrow = 1;
                bitA = bitA + 2;
            } else {
                borrow = 0;
            }
    
            result.append(bitA - bitB);
            i--;
            j--;
        }
    
        // Remove leading zeros from the result
        while (result.length() > 1 && result.charAt(result.length() - 1) == '0') {
            result.deleteCharAt(result.length() - 1);
        }
    
        return result.reverse().toString();
    }

    public static void main(String[] args) {
    String binaryString1 = "1101";  // 13 in decimal
    String binaryString2 = "1010";  // 10 in decimal

    try {
        // Add the two binary strings
        String binaryResult = addBinaryStrings(binaryString1, binaryString2);
        System.out.println("Addition Result Binary String: " + binaryResult);

        // Convert the binary addition result to decimal
        int decimalResult = toDecimal(binaryResult);
        System.out.println("Decimal Value of Addition Result: " + decimalResult);

        binaryResult = subtractBinaryStrings(binaryString1, binaryString2);
        System.out.println("Subtraction Result Binary String: " + binaryResult);


    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
}