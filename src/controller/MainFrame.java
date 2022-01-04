package controller;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import model.Currency;
import model.ExchangeRate;
import model.Money;
import ui.DialogSwing;
import ui.DisplaySwing;

public class MainFrame extends JFrame {
    private DialogSwing dialog;
    private DisplaySwing display;
    private List<Currency> currencies;
    private List<ExchangeRate> exchangeRates;
    public MainFrame(List<Currency> currencies, List<ExchangeRate> exchangeRates){
        this.currencies=currencies;
        this.exchangeRates=exchangeRates;
        this.setTitle("Money Calculator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(700, 80);
        dialog = new DialogSwing(currencies, this);
        display = new DisplaySwing();
        dialog.add(display);
        this.getContentPane().add(dialog);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public void updateAmount(){
        Money money = dialog.getMoney();
        Currency currencyTo = dialog.getCurrencyTo();
        display.display(calculateMoney(money,currencyTo));
    }
    
    public void emptyJText(){
        display.restart();
    }
    
    private Money calculateMoney(Money money, Currency currencyTo){
        for(int i=0; i<this.exchangeRates.size();i++){
            if((this.exchangeRates.get(i).getFrom().getCode().equals(money.getCurrency().getCode())) && 
                    (this.exchangeRates.get(i).getTo().getCode().equals(currencyTo.getCode()))){
                money.exchangeMoney(this.exchangeRates.get(i).getRate(), currencyTo);
                break;
            }
        }
        return money;
    }
}
