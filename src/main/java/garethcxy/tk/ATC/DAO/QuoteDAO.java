package garethcxy.tk.ATC.DAO;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

@Component
public class QuoteDAO{
    private final ArrayList<String> quoteList = new ArrayList<>();
    public QuoteDAO() {
        try{
            this.init();
        }catch (IOException ignored){}
    }

    private void init() throws IOException {
        File file = new File("Quotes.txt");
        if(!file.exists()) return;
        InputStreamReader in = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(in);
        String line;
        while((line = br.readLine()) != null){
            quoteList.add(line);
        }
    }

    public String getRandQuote(){
        if(quoteList.isEmpty()) return "";
        Random rand = new Random();
        return quoteList.get(rand.nextInt(quoteList.size()));
    }
}
