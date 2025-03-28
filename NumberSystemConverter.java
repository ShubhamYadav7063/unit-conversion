package javaproject;

public class NumberSystemConverter extends UnitConverter {
    public static final String[] UNITS = {"binary", "decimal", "hexadecimal", "octal"};

    @Override
    public double convert(double value, String fromUnit, String toUnit) {
        // Number system conversions don't involve numerical calculations, so we treat the value as a string
        String valueStr = String.valueOf((int) value); // Treat value as an integer
        if (!isValidUnit(fromUnit, UNITS) || !isValidUnit(toUnit, UNITS)) {
            throw new IllegalArgumentException("Invalid unit for number system conversion.");
        }

        // Validate input for negative values
        if (value < 0) {
            throw new IllegalArgumentException("Negative values are not supported for number system conversions.");
        }

        // Convert to decimal first
        int decimalValue = toDecimal(valueStr, fromUnit);
        // Convert from decimal to target unit
        return fromDecimal(decimalValue, toUnit);
    }

    private int toDecimal(String value, String fromUnit) {
        switch (fromUnit.toLowerCase()) {
            case "binary": return Integer.parseInt(value, 2);
            case "decimal": return Integer.parseInt(value, 10);
            case "hexadecimal": return Integer.parseInt(value, 16);
            case "octal": return Integer.parseInt(value, 8);
            default: throw new IllegalArgumentException("Invalid fromUnit: " + fromUnit);
        }
    }

    private int fromDecimal(int value, String toUnit) {
        switch (toUnit.toLowerCase()) {
            case "binary": 
                return Integer.parseInt(Integer.toBinaryString(value));
            case "decimal": 
                return value;
            case "hexadecimal": 
                return Integer.parseInt(Integer.toHexString(value), 16);
            case "octal": 
                return Integer.parseInt(Integer.toOctalString(value), 8);
            default: 
                throw new IllegalArgumentException("Invalid toUnit: " + toUnit);
        }
    }
}