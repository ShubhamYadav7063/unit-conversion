public class NumberSystemConverter extends UnitConverter {
    public static final String[] UNITS = {"Binary", "Decimal", "Octal", "Hexadecimal"};

    @Override
    public String convert(double value, String fromUnit, String toUnit) {
        String input = String.valueOf((int) value);  // Convert double to String (integer part)
        return convertString(input, fromUnit, toUnit);
    }

    public String convertString(String input, String fromUnit, String toUnit) {
        if (fromUnit.equals(toUnit)) {
            return input;
        }

        int decimalValue = 0;

        // Convert input to decimal
        switch (fromUnit) {
            case "Binary" -> decimalValue = Integer.parseInt(input, 2);
            case "Octal" -> decimalValue = Integer.parseInt(input, 8);
            case "Decimal" -> decimalValue = Integer.parseInt(input);
            case "Hexadecimal" -> decimalValue = Integer.parseInt(input, 16);
            default -> throw new IllegalArgumentException("Invalid input format");
        }

        // Convert decimal to target unit
        switch (toUnit) {
            case "Binary" -> {
                return Integer.toBinaryString(decimalValue);
            }
            case "Octal" -> {
                return Integer.toOctalString(decimalValue);
            }
            case "Decimal" -> {
                return String.valueOf(decimalValue);
            }
            case "Hexadecimal" -> {
                return Integer.toHexString(decimalValue).toUpperCase();
            }
            default -> throw new IllegalArgumentException("Invalid target format");
        }
    }
}
