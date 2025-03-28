package javaproject;

public class LengthConverter extends UnitConverter {
    public static final String[] UNITS = {"meter", "kilometer", "centimeter", "millimeter", "inch", "foot", "yard", "mile"};

    @Override
    public double convert(double value, String fromUnit, String toUnit) {
        if (!isValidUnit(fromUnit, UNITS) || !isValidUnit(toUnit, UNITS)) {
            throw new IllegalArgumentException("Invalid unit for length conversion.");
        }

        // Convert to meters first
        double inMeters = toMeters(value, fromUnit);
        // Convert from meters to target unit
        return fromMeters(inMeters, toUnit);
    }

    private double toMeters(double value, String fromUnit) {
        switch (fromUnit.toLowerCase()) {
            case "meter": return value;
            case "kilometer": return value * 1000;
            case "centimeter": return value / 100;
            case "millimeter": return value / 1000;
            case "inch": return value * 0.0254;
            case "foot": return value * 0.3048;
            case "yard": return value * 0.9144;
            case "mile": return value * 1609.34;
            default: throw new IllegalArgumentException("Invalid fromUnit: " + fromUnit);
        }
    }

    private double fromMeters(double value, String toUnit) {
        switch (toUnit.toLowerCase()) {
            case "meter": return value;
            case "kilometer": return value / 1000;
            case "centimeter": return value * 100;
            case "millimeter": return value * 1000;
            case "inch": return value / 0.0254;
            case "foot": return value / 0.3048;
            case "yard": return value / 0.9144;
            case "mile": return value / 1609.34;
            default: throw new IllegalArgumentException("Invalid toUnit: " + toUnit);
        }
    }
}