package javaproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterGUI extends JFrame {
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> fromUnitComboBox;
    private JComboBox<String> toUnitComboBox;
    private JTextField inputField;
    private JLabel resultLabel;

    public ConverterGUI() {
        setTitle("Unit Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Category selection
        add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>(new String[]{"Length", "Weight", "Temperature", "Number System"});
        add(categoryComboBox);

        // From unit selection
        add(new JLabel("From Unit:"));
        fromUnitComboBox = new JComboBox<>();
        add(fromUnitComboBox);

        // To unit selection
        add(new JLabel("To Unit:"));
        toUnitComboBox = new JComboBox<>();
        add(toUnitComboBox);

        // Input field
        add(new JLabel("Enter Value:"));
        inputField = new JTextField();
        add(inputField);

        // Result label
        add(new JLabel("Result:"));
        resultLabel = new JLabel();
        add(resultLabel);

        // Convert button
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonListener());
        add(convertButton);

        // Update units when category changes
        categoryComboBox.addActionListener(e -> updateUnits());

        // Initialize units
        updateUnits();

        setVisible(true);
    }

    private void updateUnits() {
        String category = (String) categoryComboBox.getSelectedItem();
        fromUnitComboBox.removeAllItems();
        toUnitComboBox.removeAllItems();

        switch (category) {
            case "Length":
                for (String unit : LengthConverter.UNITS) {
                    fromUnitComboBox.addItem(unit);
                    toUnitComboBox.addItem(unit);
                }
                break;
            case "Weight":
                for (String unit : WeightConverter.UNITS) {
                    fromUnitComboBox.addItem(unit);
                    toUnitComboBox.addItem(unit);
                }
                break;
            case "Temperature":
                for (String unit : TemperatureConverter.UNITS) {
                    fromUnitComboBox.addItem(unit);
                    toUnitComboBox.addItem(unit);
                }
                break;
            case "Number System":
                for (String unit : NumberSystemConverter.UNITS) {
                    fromUnitComboBox.addItem(unit);
                    toUnitComboBox.addItem(unit);
                }
                break;
        }
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String category = (String) categoryComboBox.getSelectedItem();
                String fromUnit = (String) fromUnitComboBox.getSelectedItem();
                String toUnit = (String) toUnitComboBox.getSelectedItem();
                String inputText = inputField.getText().trim();

                // Check for empty input
                if (inputText.isEmpty()) {
                    throw new IllegalArgumentException("Input field cannot be empty.");
                }

                double value = Double.parseDouble(inputText);

                UnitConverter converter = null;
                switch (category) {
                    case "Length": converter = new LengthConverter(); break;
                    case "Weight": converter = new WeightConverter(); break;
                    case "Temperature": converter = new TemperatureConverter(); break;
                    case "Number System": converter = new NumberSystemConverter(); break;
                    default: throw new IllegalArgumentException("Invalid category selected.");
                }

                double result = converter.convert(value, fromUnit, toUnit);
                if (category.equals("Number System")) {
                    resultLabel.setText(String.format("%s", (int) result)); // Display as integer for number systems
                } else {
                    resultLabel.setText(String.format("%.2f %s", result, toUnit));
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input! Please enter a valid number.");
            } catch (IllegalArgumentException ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        }
    }
}