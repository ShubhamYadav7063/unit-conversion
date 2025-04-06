public class WeightConverter extends UnitConverter {
    public static final String[] UNITS = {
        "Gram", "Kilogram", "Ton", "Quintal", "Pound"
    };

    @Override
    public String convert(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals(toUnit)) {
            return String.valueOf(value); // If units are the same, return the value as string
        }

        double result = 0.0;

        // Gram to other units
        switch (fromUnit) {
            case "Gram" -> {
                switch (toUnit) {
                    case "Kilogram" -> result = value / 1000;
                    case "Ton" -> result = value / 1_000_000;
                    case "Quintal" -> result = value / 100000;
                    case "Pound" -> result = value * 0.00220462;
                    default -> {
                        
                    }
                }
            }
            case "Kilogram" -> {
                switch (toUnit) {
                    case "Gram" -> result = value * 1000;
                    case "Ton" -> result = value / 1000;
                    case "Quintal" -> result = value / 100;
                    case "Pound" -> result = value * 2.20462;
                    default -> {
                        
                    }
                }
            }
            case "Ton" -> {
                switch (toUnit) {
                    case "Gram" -> result = value * 1_000_000;
                    case "Kilogram" -> result = value * 1000;
                    case "Quintal" -> result = value * 100;
                    case "Pound" -> result = value * 2204.62;
                    default -> {
                    }
                }
            }
            case "Quintal" -> {
                switch (toUnit) {
                    case "Gram" -> result = value * 100000;
                    case "Kilogram" -> result = value * 100;
                    case "Ton" -> result = value / 100;
                    case "Pound" -> result = value * 220.462;
                    default -> {
                        
                    }
                }
            }
            case "Pound" -> {
                switch (toUnit) {
                    case "Gram" -> result = value * 453.592;
                    case "Kilogram" -> result = value / 2.20462;
                    case "Ton" -> result = value / 2204.62;
                    case "Quintal" -> result = value / 220.462;
                    default -> {
                    }
                }
            }
            default -> {
            }
        }

        // Return the result as a string
        return String.valueOf(result);
    }
}
