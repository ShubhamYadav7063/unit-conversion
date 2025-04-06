import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConverterGUI extends JFrame {
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> fromUnitComboBox;
    private JComboBox<String> toUnitComboBox;
    private JTextField inputField;
    private JLabel resultLabel;

    public ConverterGUI() {
        setTitle("Unit Converter");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setUndecorated(true); // Remove OS title bar
        setLayout(null);

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0xFF5F6D),
                        getWidth(), getHeight(), new Color(0xFFC371));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(null);
        gradientPanel.setBounds(0, 0, getWidth(), getHeight());

    // Custom title bar
    JPanel titleBar = new JPanel();
    titleBar.setLayout(new BorderLayout());
    titleBar.setBounds(0, 0, getWidth(), 40);
    titleBar.setBackground(new Color(255, 255, 255, 30));
    titleBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    JLabel titleLabel = new JLabel("Unit Converter");
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setFont(new Font("Montserrat", Font.BOLD, 16));

    JButton closeButton = new JButton("X");
    closeButton.setPreferredSize(new Dimension(40, 30));
    closeButton.setFocusPainted(false);
    closeButton.setForeground(Color.WHITE);
    closeButton.setFont(new Font("Montserrat", Font.BOLD, 14));
    closeButton.setContentAreaFilled(false);
    closeButton.setBorderPainted(false);
    closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    closeButton.setToolTipText("Close");

    closeButton.addActionListener(e -> System.exit(0));

    // Drag window support
    MouseAdapter dragListener = new MouseAdapter() {
        Point initialClick;
            @Override
    public void mousePressed(MouseEvent e) {
        initialClick = e.getPoint();
    }

        @Override
    public void mouseDragged(MouseEvent e) {
        int thisX = getLocation().x;
        int thisY = getLocation().y;
        int xMoved = e.getX() - initialClick.x;
        int yMoved = e.getY() - initialClick.y;
        int X = thisX + xMoved;
        int Y = thisY + yMoved;
        setLocation(X, Y);
    }
};

    titleBar.addMouseListener(dragListener);
    titleBar.addMouseMotionListener(dragListener);

    titleBar.add(titleLabel, BorderLayout.WEST);
    titleBar.add(closeButton, BorderLayout.EAST);


        gradientPanel.add(titleBar);

        // Components
        categoryComboBox = new JComboBox<>(new String[]{"Length", "Weight", "Temperature", "Number System"});
        categoryComboBox.setBounds(50, 60, 350, 30);

        fromUnitComboBox = new JComboBox<>();
        fromUnitComboBox.setBounds(50, 100, 150, 30);

        toUnitComboBox = new JComboBox<>();
        toUnitComboBox.setBounds(250, 100, 150, 30);

        inputField = new JTextField();
        inputField.setBounds(50, 140, 350, 30);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(50, 180, 350, 30);
        convertButton.setBackground(new Color(255, 255, 255, 100));
        convertButton.setFont(new Font("Montserrat", Font.BOLD, 14));
        convertButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        resultLabel = new JLabel("Result will appear here", SwingConstants.CENTER);
        resultLabel.setBounds(50, 220, 350, 40);
        resultLabel.setFont(new Font("Montserrat", Font.BOLD, 18));
        resultLabel.setForeground(Color.WHITE);

        gradientPanel.add(categoryComboBox);
        gradientPanel.add(fromUnitComboBox);
        gradientPanel.add(toUnitComboBox);
        gradientPanel.add(inputField);
        gradientPanel.add(convertButton);
        gradientPanel.add(resultLabel);

        add(gradientPanel);

        categoryComboBox.addActionListener(e -> updateUnits());
        convertButton.addActionListener(new ConvertButtonListener());
        updateUnits();

        setVisible(true);
    }

    private void updateUnits() {
        String category = (String) categoryComboBox.getSelectedItem();
        fromUnitComboBox.removeAllItems();
        toUnitComboBox.removeAllItems();

        switch (category) {
            case "Length":
                addUnits(LengthConverter.UNITS);
                break;
            case "Weight":
                addUnits(WeightConverter.UNITS);
                break;
            case "Temperature":
                addUnits(TemperatureConverter.UNITS);
                break;
            case "Number System":
                addUnits(NumberSystemConverter.UNITS);
                break;
        }
    }

    private void addUnits(String[] units) {
        for (String unit : units) {
            fromUnitComboBox.addItem(unit);
            toUnitComboBox.addItem(unit);
        }
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String category = (String) categoryComboBox.getSelectedItem();
                String fromUnit = (String) fromUnitComboBox.getSelectedItem();
                String toUnit = (String) toUnitComboBox.getSelectedItem();
                String input = inputField.getText().trim();

                if (input.isEmpty()) {
                    resultLabel.setText("Input cannot be empty!");
                    return;
                }

                UnitConverter converter;
                switch (category) {
                    case "Length":
                        converter = new LengthConverter();
                        break;
                    case "Weight":
                        converter = new WeightConverter();
                        break;
                    case "Temperature":
                        converter = new TemperatureConverter();
                        break;
                    case "Number System":
                        converter = new NumberSystemConverter();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid category");
                }

                String resultStr;
                if (category.equals("Number System")) {
                    resultStr = ((NumberSystemConverter) converter).convertString(input, fromUnit, toUnit);
                } else {
                    double value = Double.parseDouble(input);
                    resultStr = converter.convert(value, fromUnit, toUnit);
                }

                resultLabel.setText(resultStr);

            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid numeric input!");
            } catch (IllegalArgumentException ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            } catch (Exception ex) {
                resultLabel.setText("Unknown error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConverterGUI::new);
    }
}
