package Persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Currency;

public class CurrencyLoader {
    
    private static Currency currencyOf(String lineText) {
        String[] splitLine = lineText.split(",");
        return new Currency(splitLine[0], splitLine[1], splitLine[2]);
    }
    
    public static List<Currency> read(String file){
        List<Currency> listCurrencies = new ArrayList<>();
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
            while (true){
                String lineText = reader.readLine();
                if (lineText == null) break;
                listCurrencies.add(currencyOf(lineText));
            }
        }
        
        catch (FileNotFoundException excepcion) {
            System.out.println("ERROR CurrencyLoaderArchive: loadAllCurrencies FileNotFoundException, " + excepcion);
        } 
        catch (IOException ex) {
            System.out.println("ERROR CurrencyLoaderArchive: loadAllCurrencies IOException, " + ex);
        }
        return listCurrencies;
    }
}
