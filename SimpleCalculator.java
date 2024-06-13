import java.awt.*;
import java.awt.event.*;
public class SimpleCalculator extends Frame implements ActionListener {
    // Declare components
    private TextField display;
    private Button[] buttons;
    private String lastOperator;
    private double result;

    // Constructor to set up the UI
    public SimpleCalculator() {
        setLayout(null);
        display = new TextField();
        display.setEditable(false);
	display.setBounds(20, 30, 230, 50); // Position and size of the display
        add(display);
        //add(display, BorderLayout.NORTH);

	String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        buttons = new Button[16];

        int x = 20, y = 90; // Initial position for the first button
        int width = 50, height = 40; // Size for each button

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(buttonLabels[i]);
            buttons[i].setBounds(x, y, width, height); // Set position and size
            buttons[i].addActionListener(this);
            add(buttons[i]);

            x += 60; // Move x position for the next button
            if ((i + 1) % 4 == 0) {
                x = 20; // Reset x position after every 4 buttons
                y += 50; // Move y position for the next row
            }
        }

        setTitle("Simple Calculator");
        setSize(400, 400);
        setVisible(true);

        lastOperator = "";
        result = 0;

	addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            display.setText("");
            result = 0;
            lastOperator = "";
        } else if (command.equals("=")) {
            calculate(Double.parseDouble(display.getText()));
            display.setText("" + result);
            lastOperator = "";
        } else {
            if (!lastOperator.equals("")) {
                calculate(Double.parseDouble(display.getText()));
                display.setText("" + result);
            } else {
                result = Double.parseDouble(display.getText());
            }
            lastOperator = command;
            display.setText("");
        }
    }

    private void calculate(double n) {
        switch (lastOperator) {
            case "+":
                result += n;
                break;
            case "-":
                result -= n;
                break;
            case "*":
                result *= n;
                break;
            case "/":
                if (n != 0) {
                    result /= n;
                } else {
                    display.setText("Error");
                }
                break;
        }
    }
}
