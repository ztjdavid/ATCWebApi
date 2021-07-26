package garethcxy.tk.ATC.Service;

import garethcxy.tk.ATC.DAO.OrderDAO;
import garethcxy.tk.ATC.DAO.SummaryDAO;
import garethcxy.tk.ATC.Entity.Order;
import garethcxy.tk.ATC.Util.SummaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private SummaryDAO summaryDao;
    @Autowired
    private SummaryUtil summaryUtil;

    public Optional<Order> getById(String id){
        UUID uuid = UUID.fromString(id);
        return orderDAO.get(uuid);
    }

    public void addOrder(Order newOder){
        addSummary(newOder);
        orderDAO.add(newOder);
    }

    public List<UUID> getAllUUIDs(){
        return orderDAO.getAllUUID();
    }

    public boolean clear(){ return orderDAO.clear(); }

    public boolean delOrder(String id){
        UUID uuid = UUID.fromString(id);
        return orderDAO.delete(uuid);
    }

    private void addSummary(Order order){
        summaryDao.add(summaryUtil.generateSummary(order));
    }


}
