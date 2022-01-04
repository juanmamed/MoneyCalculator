package Persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import model.Currency;
import model.ExchangeRate;
import org.json.simple.parser.ParseException;

public class ExchangeRateLoader {
    
    private static ExchangeRate exchangeRateOf(Currency currencyFrom, Currency currencyTo, String content_url) throws ParseException{
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(content_url);
        double rate = Double.parseDouble(jsonObject.get(currencyTo.getCode()).toString());
        return new ExchangeRate(rate, currencyFrom, currencyTo);
    }
    
    public static List<ExchangeRate> loadExchangeRates(String url_base, List<Currency> currencies) throws IOException, ParseException{
        List<ExchangeRate> listExchangeRates = new ArrayList<>();
        for (Currency currencyFrom: currencies) {
            for (Currency currencyTo: currencies) {
                URL url = new URL(url_base + currencyFrom.getCode() + "/" + currencyTo.getCode() + ".json");
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String content_url = "";
                while(true){
                    String inputLine = in.readLine();
                    if(inputLine == null) break;
                    content_url += inputLine;
                }
                listExchangeRates.add(exchangeRateOf(currencyFrom, currencyTo, content_url));
            }
        }
        return listExchangeRates;
    }
}