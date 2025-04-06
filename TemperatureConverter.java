public class TemperatureConverter extends UnitConverter {
    public static final String[] UNITS = {
        "Celsius", "Fahrenheit", "Kelvin"
    };

    @Override
    public String convert(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals(toUnit)) {
            return String.valueOf(value); // If units are the same, return the value as string
        }

        double result = 0.0;

        // Celsius to other units
        switch (fromUnit) {
            case "Celsius" -> {
                if (toUnit.equals("Fahrenheit")) result = (value * 9/5) + 32;
                else if (toUnit.equals("Kelvin")) result = value + 273.15;
            }
            case "Fahrenheit" -> {
                if (toUnit.equals("Celsius")) result = (value - 32) * 5/9;
                else if (toUnit.equals("Kelvin")) result = (value - 32) * 5/9 + 273.15;
            }
            case "Kelvin" -> {
                if (toUnit.equals("Celsius")) result = value - 273.15;
                else if (toUnit.equals("Fahrenheit")) result = (value - 273.15) * 9/5 + 32;
            }
            default -> {
            }
        }

        // Return the result as a string
        return String.valueOf(result);
    }
}
