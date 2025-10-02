import javax.swing.*;
import java.awt.event.*;

public class bruchrechner {
    private JTextField ausgabeTextField;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a4Button;
    private JButton a2Button;
    private JButton a6Button;
    private JButton a5Button;
    private JButton a3Button;
    private JButton a1Button;
    private JButton a0Button;
    private JButton löschenButton;
    private JButton berechnenButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton plusButton;
    private JButton minusButton;
    private JButton kürzenButton1;
    private JButton umwandelnButton;
    private JButton commaButton;
    private JButton bruchstrichButton;
    private JPanel frame;

    private String operator = "";
    private Bruch firstBruch = null;

    public bruchrechner() {
        JButton[] nums = {a0Button,a1Button,a2Button,a3Button,a4Button,a5Button,a6Button,a7Button,a8Button,a9Button};
        for(int i=0;i<10;i++) {
            int num = i;
            nums[i].addActionListener(e -> ausgabeTextField.setText(ausgabeTextField.getText() + num));
        }

        commaButton.addActionListener(e -> ausgabeTextField.setText(ausgabeTextField.getText() + "."));
        bruchstrichButton.addActionListener(e -> ausgabeTextField.setText(ausgabeTextField.getText() + "/"));

        plusButton.addActionListener(e -> setOperator("+"));
        minusButton.addActionListener(e -> setOperator("-"));
        multiplyButton.addActionListener(e -> setOperator("*"));
        divideButton.addActionListener(e -> setOperator("/"));

        löschenButton.addActionListener(e -> {
            ausgabeTextField.setText("");
            firstBruch = null;
            operator = "";
        });

        berechnenButton.addActionListener(e -> {
            String text = ausgabeTextField.getText();
            Bruch second = parseBruch(text);
            if(firstBruch != null && second != null) {
                Bruch result = null;
                switch(operator) {
                    case "+": result = firstBruch.add(second); break;
                    case "-": result = firstBruch.sub(second); break;
                    case "*": result = firstBruch.mul(second); break;
                    case "/": result = firstBruch.div(second); break;
                }
                if(result != null) {
                    result.kuerzen();
                    ausgabeTextField.setText(result.toString());
                    firstBruch = null;
                    operator = "";
                }
            }
        });

        kürzenButton1.addActionListener(e -> {
            Bruch b = parseBruch(ausgabeTextField.getText());
            if(b != null) {
                b.kuerzen();
                ausgabeTextField.setText(b.toString());
            }
        });
    }

    private void setOperator(String op) {
        firstBruch = parseBruch(ausgabeTextField.getText());
        operator = op;
        ausgabeTextField.setText("");
    }

    private Bruch parseBruch(String s) {
        if(s.contains("/")) {
            String[] parts = s.split("/");
            int z = Integer.parseInt(parts[0]);
            int n = Integer.parseInt(parts[1]);
            return new Bruch(z, n);
        } else if(s.contains(".")) {
            double d = Double.parseDouble(s);
            int denominator = 10000;
            int numerator = (int)(d * denominator);
            return new Bruch(numerator, denominator);
        } else if(!s.isEmpty()) {
            int val = Integer.parseInt(s);
            return new Bruch(val, 1);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Bruchrechner");
        bruchrechner app = new bruchrechner();
        window.setContentPane(app.frame);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}
