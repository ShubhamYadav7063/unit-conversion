package javaproject;

public class WeightConverter extends UnitConverter {
    public static final String[] UNITS = {"gram", "kilogram", "milligram", "pound", "ounce"};

    @Override
    public double convert(double value, String fromUnit, String toUnit) {
        if (!isValidUnit(fromUnit, UNITS) || !isValidUnit(toUnit, UNITS)) {
            throw new IllegalArgumentException("Invalid unit for weight conversion.");
        }

        // Convert to grams first
        double inGrams = toGrams(value, fromUnit);
        // Convert from grams to target unit
        return fromGrams(inGrams, toUnit);
    }

    private double toGrams(double value, String fromUnit) {
        switch (fromUnit.toLowerCase()) {
            case "gram": return value;
            case "kilogram": return value * 1000;
            case "milligram": return value / 1000;
            case "pound": return value * 453.592;
            case "ounce": return value * 28.3495;
            default: throw new IllegalArgumentException("Invalid fromUnit: " + fromUnit);
        }
    }

    private double fromGrams(double value, String toUnit) {
        switch (toUnit.toLowerCase()) {
            case "gram": return value;
            case "kilogram": return value / 1000;
            case "milligram": return value * 1000;
            case "pound": return value / 453.592;
            case "ounce": return value / 28.3495;
            default: throw new IllegalArgumentException("Invalid toUnit: " + toUnit);
        }
    }
}