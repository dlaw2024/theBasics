import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class PriceCalculator {

    private JFrame mainFrame;
    private JTextField finalPriceField;
    private JButton calculateButton;

    public PriceCalculator() {
        mainFrame = new JFrame("Pre-Tax Price Calculator");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 150);
        mainFrame.setLocation(400, 400); // Set initial location for mainFrame

        // Create a panel with BoxLayout for vertical alignment
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); 

        JLabel finalPriceLabel = new JLabel("Enter Final Price:");
        finalPriceLabel.setFont(new Font("Arial", Font.PLAIN, 22)); 
        finalPriceField = new JTextField(10);
        finalPriceField.setFont(new Font("Arial", Font.PLAIN, 22)); 
        calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.PLAIN, 22)); 
        
        // Add KeyListener to the JTextField
        finalPriceField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calculateButton.doClick(); // Simulate button click on Enter press
                }
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double finalPrice = Double.parseDouble(finalPriceField.getText());
                    double taxRate = 0.07;
                    double preTaxPrice = finalPrice / (1 + taxRate); 

                    JFrame resultFrame = new JFrame("Price before tax");
                    resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    resultFrame.setSize(250, 100);
                    resultFrame.setLocation(400, 200); // Set initial location for mainFrame

                    JPanel resultPanel = new JPanel();
                    resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS)); 

                    JTextField resultField = new JTextField(20);
                    resultField.setEditable(false);
                    resultField.setText("$" + String.format("%.2f", preTaxPrice));
                    resultField.setFont(new Font("Arial", Font.PLAIN, 22)); 

                    JButton closeButton = new JButton("Close");
                    closeButton.setFont(new Font("Arial", Font.PLAIN, 22)); 
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            resultFrame.dispose();
                        }
                    });

                    resultPanel.add(resultField);
                    resultPanel.add(closeButton);
                    resultFrame.add(resultPanel); 
                    resultFrame.setVisible(true);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainPanel.add(finalPriceLabel);
        mainPanel.add(finalPriceField);
        mainPanel.add(calculateButton);

        mainFrame.add(mainPanel); 
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new PriceCalculator();
    }
}