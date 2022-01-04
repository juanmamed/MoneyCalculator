package ui;

import controller.MainFrame;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Currency;
import model.Money;

public class DialogSwing extends JPanel implements Dialog {
    private JLabel LabelFrom;
    private JLabel LabelTo;
    private JComboBox<String> ComboFrom;
    private JComboBox<String> ComboTo;
    private JTextField TextAmountMoney;
    private Currency currencyTo;
    private Currency currencyFrom;
    private double amount;
    private List<Currency> currencies;
    private MainFrame controller;
    public DialogSwing(List<Currency> currencies, MainFrame controller){
        this.currencies = currencies;
        this.controller=controller;
        currencyTo=currencies.get(0);
        currencyFrom=currencies.get(0);
        initComponents(currencies);
    }

    private void initComponents(List<Currency> currencies){
        LabelFrom = new JLabel();
        LabelFrom.setText("FROM:");
        
        ComboFrom = new JComboBox<String>();
        this.loadJComboBox(ComboFrom, currencies);
        ComboFrom.setBackground(Color.WHITE);
        ComboFrom.addActionListener(new ListenerJcomboBoxFrom());
        
        TextAmountMoney = new JTextField(20);
        TextAmountMoney.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        TextAmountMoney.getDocument().addDocumentListener(new ListenerTextAmount());
        
        LabelTo = new JLabel();
        LabelTo.setText("TO:");
        
        ComboTo = new JComboBox<String>();
        this.loadJComboBox(ComboTo, currencies);
        ComboTo.setBackground(Color.WHITE);
        ComboTo.addActionListener(new ListenerJcomboBoxTo());
        
        this.setLayout(new FlowLayout());
        this.add(LabelFrom);
        this.add(ComboFrom);
        this.add(TextAmountMoney);
        this.add(LabelTo);
        this.add(ComboTo);
    }
    
    private void loadJComboBox(JComboBox<String> jcombo, List<Currency> currencies){
        for (int i=0; i<currencies.size(); i++){
            jcombo.addItem(currencies.get(i).toString());
        }
    }
    
    private void setAmount(Double update_amount){
        amount = update_amount;
        
        controller.updateAmount();
    }
    
    private void setCurrencyTo(Currency c){
        currencyTo=c;
        try {
            Double cash = Double.parseDouble(TextAmountMoney.getText());
            if (cash < 0){
                controller.emptyJText();
            } else{
                controller.updateAmount();
            }
        } catch (NumberFormatException ex){controller.emptyJText();}
    }
    
    private void setCurrencyFrom(Currency c){
        currencyFrom=c;
        try {
            Double cash = Double.parseDouble(TextAmountMoney.getText());
            if (cash < 0){
                controller.emptyJText();
            } else{
                controller.updateAmount();
            }
        } catch (NumberFormatException ex){controller.emptyJText();}
    }
    
    @Override
    public Money getMoney() {
        return new Money(amount,currencyFrom);
    }

    @Override
    public Currency getCurrencyTo() {
        return currencyTo;
    }
    
    private class ListenerJcomboBoxFrom implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String symbol_from = (String) ComboFrom.getSelectedItem();
            for (int i=0; i<currencies.size(); i++){
                if(currencies.get(i).toString().equals(symbol_from)){
                    setCurrencyFrom(currencies.get(i));
                }
            }
        }
    
    }
    
    private class ListenerJcomboBoxTo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String symbol_to = (String) ComboTo.getSelectedItem();
            for (int i=0; i<currencies.size(); i++){
                if(currencies.get(i).toString().equals(symbol_to)){
                    setCurrencyTo(currencies.get(i));
                }
            }
        }
    
    }
    
    private class ListenerTextAmount implements DocumentListener {

        @Override
        public void changedUpdate(DocumentEvent e) {
            getContent(e);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            getContent(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            getContent(e);
        }
        
        public void getContent(DocumentEvent e){
            try {
                Double amount = Double.parseDouble(TextAmountMoney.getText());
                if (amount < 0){
                    controller.emptyJText();
                } else {
                    setAmount(amount);
                }
            } catch (NumberFormatException ex){controller.emptyJText();}
        }
        
    }
}
