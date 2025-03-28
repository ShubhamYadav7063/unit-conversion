package javaproject;

public class TemperatureConverter extends UnitConverter {
    public static final String[] UNITS = {"celsius", "fahrenheit", "kelvin"};

    @Override
    public double convert(double value, String fromUnit, String toUnit) {
        if (!isValidUnit(fromUnit, UNITS) || !isValidUnit(toUnit, UNITS)) {
            throw new IllegalArgumentException("Invalid unit for temperature conversion.");
        }

        // Convert to Celsius first
        double inCelsius = toCelsius(value, fromUnit);
        // Convert from Celsius to target unit
        return fromCelsius(inCelsius, toUnit);
    }

    private double toCelsius(double value, String fromUnit) {
        switch (fromUnit.toLowerCase()) {
            case "celsius": return value;
            case "fahrenheit": return (value - 32) * 5 / 9;
            case "kelvin": return value - 273.15;
            default: throw new IllegalArgumentException("Invalid fromUnit: " + fromUnit);
        }
    }

    private double fromCelsius(double value, String toUnit) {
        switch (toUnit.toLowerCase()) {
            case "celsius": return value;
            case "fahrenheit": return (value * 9 / 5) + 32;
            case "kelvin": return value + 273.15;
            default: throw new IllegalArgumentException("Invalid toUnit: " + toUnit);
        }
    }
}