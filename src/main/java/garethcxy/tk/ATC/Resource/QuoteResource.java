package garethcxy.tk.ATC.Resource;

import garethcxy.tk.ATC.Service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/atc/api/{version}")
public class QuoteResource {
    @Autowired
    private QuoteService quoteService;

    @GetMapping("/quote")
    public String getQuote(){
        return quoteService.getRandQuote();
    }
}
