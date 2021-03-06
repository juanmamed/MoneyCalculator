package model;
public class Money {
    private double amount;
    private Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    public double getAmount(){
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
    
    public void exchangeMoney(double rate, Currency currencyTo) {
        this.amount=this.amount*rate;
        this.currency = currencyTo;
    }
}
