package garethcxy.tk.ATC.DAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDAO<T> {

    Optional<T> get(UUID id);
    void add(T t);
    boolean delete(UUID id);
    boolean delete(T t);

}
