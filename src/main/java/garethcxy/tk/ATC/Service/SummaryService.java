package garethcxy.tk.ATC.Service;

import garethcxy.tk.ATC.DAO.SummaryDao;
import garethcxy.tk.ATC.Entity.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SummaryService {
    @Autowired
    private SummaryDao summaryDao;

    public Summary get(String uuid){
        UUID id = UUID.fromString(uuid);
        return summaryDao.get(id).orElse(null);
    }
}
