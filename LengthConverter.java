public class LengthConverter extends UnitConverter {
    public static final String[] UNITS = {
        "Millimeter", "Centimeter", "Meter", "Kilometer", "Mile", "Yard", "Foot", "Inch"
    };

    @Override
    public String convert(double value, String fromUnit, String toUnit) {
        if(value>=0){
            if (fromUnit.equals(toUnit)) {
                return String.valueOf(value); // If the units are the same, return the value as string
            }

            double result = 0.0;

            // Millimeter to other units
            if (fromUnit.equals("Millimeter")) {
                switch (toUnit) {
                    case "Centimeter" -> result = value / 10;
                    case "Meter" -> result = value / 1000;
                    case "Kilometer" -> result = value / 1_000_000;
                    case "Mile" -> result = value / 1_609_344;
                    case "Yard" -> result = value / 914.4;
                    case "Foot" -> result = value / 304.8;
                    case "Inch" -> result = value / 25.4;
                    default -> {
                    }
                }
            }
            
            // Centimeter to other units
            else if (fromUnit.equals("Centimeter")) {
                switch (toUnit) {
                    case "Millimeter" -> result = value * 10;
                    case "Meter" -> result = value / 100;
                    case "Kilometer" -> result = value / 100_000;
                    case "Mile" -> result = value / 160934.4;
                    case "Yard" -> result = value / 91.44;
                    case "Foot" -> result = value / 30.48;
                    case "Inch" -> result = value / 2.54;
                    default -> {
                    }
                }
            }

            // Meter to other units
            else if (!fromUnit.equals("Meter")) if (fromUnit.equals("Kilometer")) {
                switch (toUnit) {
                    case "Millimeter" -> result = value * 1_000_000;
                    case "Centimeter" -> result = value * 100_000;
                    case "Meter" -> result = value * 1000;
                    case "Mile" -> result = value / 1.60934;
                    case "Yard" -> result = value * 1093.61;
                    case "Foot" -> result = value * 3280.84;
                    case "Inch" -> result = value * 39_370.1;
                    default -> {
                    }
                }
            }

            // Mile to other units
            else if (fromUnit.equals("Mile")) {
                switch (toUnit) {
                    case "Millimeter" -> result = value * 1_609_344;
                    case "Centimeter" -> result = value * 160934.4;
                    case "Meter" -> result = value * 1609.34;
                    case "Kilometer" -> result = value * 1.60934;
                    case "Yard" -> result = value * 1760;
                    case "Foot" -> result = value * 5280;
                    case "Inch" -> result = value * 63_360;
                    default -> {
                    }
                }
            }

            // Yard to other units
            else if (fromUnit.equals("Yard")) {
                switch (toUnit) {
                    case "Millimeter" -> result = value * 914.4;
                    case "Centimeter" -> result = value * 91.44;
                    case "Meter" -> result = value / 1.09361;
                    case "Kilometer" -> result = value / 1093.61;
                    case "Mile" -> result = value / 1760;
                    case "Foot" -> result = value * 3;
                    case "Inch" -> result = value * 36;
                    default -> {
                    }
                }
            }

            // Foot to other units
            else if (fromUnit.equals("Foot")) {
                switch (toUnit) {
                    case "Millimeter" -> result = value * 304.8;
                    case "Centimeter" -> result = value * 30.48;
                    case "Meter" -> result = value / 3.28084;
                    case "Kilometer" -> result = value / 3280.84;
                    case "Mile" -> result = value / 5280;
                    case "Yard" -> result = value / 3;
                    case "Inch" -> result = 0xc * value;
                    default -> {break;}
                }
            }

            // Inch to other units
            else if (fromUnit.equals("Inch")) {
                switch (toUnit) {
                    case "Millimeter" -> result = value * 25.4;
                    case "Centimeter" -> result = value * 2.54;
                    case "Meter" -> result = value / 39.3701;
                    case "Kilometer" -> result = value / 39_370.1;
                    case "Mile" -> result = value / 63_360;
                    case "Yard" -> result = value / 36;
                    case "Foot" -> result = value / 12;
                    default -> {break;}
                }
            }

            // Kilometer to other units
            else {
                switch (toUnit) {
                    case "Millimeter" -> result = value * 1000;
                    case "Centimeter" -> result = value * 100;
                    case "Kilometer" -> result = value / 1000;
                    case "Mile" -> result = value / 1609.34;
                    case "Yard" -> result = value * 1.09361;
                    case "Foot" -> result = value * 3.28084;
                    case "Inch" -> result = value * 39.3701;
                    default -> {
                    }
                }
            }
            // Return the result as a string
            return String.valueOf(result); 
        }

        String result="Invalid Input";
        return result;
    }
}
