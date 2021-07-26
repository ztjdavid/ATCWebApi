package garethcxy.tk.ATC.Service;

import garethcxy.tk.ATC.DAO.QuoteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {
    @Autowired
    private QuoteDAO quoteDAO;

    public String getRandQuote(){
        return quoteDAO.getRandQuote();
    }
}
