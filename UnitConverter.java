package javaproject;

public abstract class UnitConverter {
    // Abstract method to convert units
    public abstract double convert(double value, String fromUnit, String toUnit);

    // Common method to validate units
    protected boolean isValidUnit(String unit, String[] validUnits) {
        for (String u : validUnits) {
            if (u.equalsIgnoreCase(unit)) {
                return true;
            }
        }
        return false;
    }
}