package garethcxy.tk.ATC.DAO;

import garethcxy.tk.ATC.Entity.Summary;
import garethcxy.tk.ATC.Util.ConfigLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SummaryDAO implements IDAO<Summary>{
    private final LinkedList<Summary> summaryList = new LinkedList<>();
    private final int maxStorage;

    @Autowired
    public SummaryDAO(ConfigLoader config){
        maxStorage = config.getContent("Properties", "OrderThreshold", int.class);
    }

    @Override
    public Optional<Summary> get(UUID id) {
        for (Summary summary : summaryList) {
            if(summary.getUuid().equals(id)) return Optional.of(summary);
        }
        return Optional.empty();
    }

    @Override
    public void add(Summary summary) {
        if(summaryList.size() >= maxStorage) summaryList.pollFirst();
        summaryList.addLast(summary);
    }

    @Override
    public boolean delete(UUID id) {
        return summaryList.removeIf(summary -> summary.getUuid().equals(id));
    }

    @Override
    public boolean delete(Summary summary) {
        return summaryList.remove(summary);
    }

    public List<UUID> getAllUUID() {
        ArrayList<UUID> result = new ArrayList<>();
        for (Summary summary : summaryList) {
            result.add(summary.getUuid());
        }
        return result;
    }

    public Summary getLatest() {
        return summaryList.peekLast();
    }
}
