package moneycalculator;

import Persistence.CurrencyLoader;
import java.util.List;
import model.Currency;

public class MoneyCalculator {
    public static void main(String[] args) {
        String file = "currencies.txt";
        List<Currency> divisas = CurrencyLoader.read(file);
        for(int i = 0; i < divisas.size(); i++) {
            System.out.println(divisas.get(i));
        }
    }
    
}
