package moneycalculator;

import Persistence.CurrencyLoader;
import Persistence.ExchangeRateLoader;
import controller.MainFrame;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import model.Currency;
import model.ExchangeRate;
import org.json.simple.parser.ParseException;

public class MoneyCalculator {
    public static void main(String[] args) throws IOException, ParseException {
        String file = "currencies.txt";
        String url_base = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/";
        List<Currency> divisas = CurrencyLoader.loadCurrencies(file);
        
        //for(int i = 0; i < divisas.size(); i++) {
        //    System.out.println(divisas.get(i));
        //}
        
        List<ExchangeRate> exchangeRates = ExchangeRateLoader.loadExchangeRates(url_base, divisas);
        
        //for(int i = 0; i < exchangeRates.size(); i++) {
        //    System.out.println(exchangeRates.get(i).getFrom() + " to " + exchangeRates.get(i).getTo() + " rate: " + exchangeRates.get(i).getRate());
        //}
        
        MainFrame gui = new MainFrame(divisas, exchangeRates);
    }
}
