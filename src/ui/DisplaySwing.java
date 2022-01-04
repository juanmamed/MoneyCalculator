package ui;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Money;

public class DisplaySwing extends JPanel implements Display {
    private JTextField currencyTo;
    public DisplaySwing() {
        initComponents();
    }
    
    private void initComponents(){
        currencyTo = new JTextField(20);
        currencyTo.setEditable(false);
        currencyTo.setBackground(Color.WHITE);
        currencyTo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.add(currencyTo);
    }
    
    @Override
    public void display(Money money) {
        //double amount_round = Math.round(money.getAmount()*100)/100;
        currencyTo.setText("" + money.getAmount());
    }
    
    public void restart(){
        currencyTo.setText("");
    }
}
