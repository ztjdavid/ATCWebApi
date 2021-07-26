package garethcxy.tk.ATC.Service;

import garethcxy.tk.ATC.DAO.SummaryDAO;
import garethcxy.tk.ATC.Entity.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SummaryService {
    @Autowired
    private SummaryDAO summaryDao;

    public Summary get(String uuid){
        UUID id = UUID.fromString(uuid);
        return summaryDao.get(id).orElse(null);
    }
    public Optional<Summary> getLatest(){
        Summary latest = summaryDao.getLatest();
        if(latest == null) return Optional.empty();
        return Optional.of(latest);
    }
}
