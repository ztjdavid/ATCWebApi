package garethcxy.tk.ATC.Resource;

import garethcxy.tk.ATC.Entity.Summary;
import garethcxy.tk.ATC.Service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/atc/api/{version}")
public class SummaryResource {
    @Autowired
    private SummaryService summaryService;

    @GetMapping("/summary/{id}")
    public Summary get(@PathVariable("id") String uuid){
        return summaryService.get(uuid);
    }
}
