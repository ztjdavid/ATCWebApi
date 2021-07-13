package garethcxy.tk.ATC.DAO;

import garethcxy.tk.ATC.Entity.Summary;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SummaryDao implements IDAO<Summary>{
    private final ArrayList<Summary> summaryList = new ArrayList<>();

    @Override
    public Optional<Summary> get(UUID id) {
        for (Summary summary : summaryList) {
            if(summary.getUuid().equals(id)) return Optional.of(summary);
        }
        return Optional.empty();
    }

    @Override
    public void add(Summary summary) {
        summaryList.add(summary);
    }

    @Override
    public boolean delete(UUID id) {
        return summaryList.removeIf(summary -> {
            return summary.getUuid().equals(id);
        });
    }

    @Override
    public boolean delete(Summary summary) {
        return summaryList.remove(summary);
    }

    @Override
    public List<UUID> getAllUUID() {
        ArrayList<UUID> result = new ArrayList<>();
        for (Summary summary : summaryList) {
            result.add(summary.getUuid());
        }
        return result;
    }
}
