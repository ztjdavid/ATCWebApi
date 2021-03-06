package garethcxy.tk.ATC.Service;

import garethcxy.tk.ATC.DAO.OrderDAO;
import garethcxy.tk.ATC.DAO.SummaryDAO;
import garethcxy.tk.ATC.Entity.AllUUIDResponse;
import garethcxy.tk.ATC.Entity.Order;
import garethcxy.tk.ATC.Entity.Summary;
import garethcxy.tk.ATC.Util.MailUtil;
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
    @Autowired
    private MailUtil mailUtil;

    public Optional<Order> getById(String id){
        UUID uuid = UUID.fromString(id);
        return orderDAO.get(uuid);
    }

    public void addOrder(Order newOder){
        Summary summary = addSummary(newOder);
        orderDAO.add(newOder);
        mailUtil.SendNewOrderNotification(summary);
    }

    public List<AllUUIDResponse> getAllUUIDs(){
        return orderDAO.getAllUUIDResponse();
    }

    public boolean clear(){ return orderDAO.clear(); }

    public boolean delOrder(String id){
        UUID uuid = UUID.fromString(id);
        return orderDAO.delete(uuid);
    }

    private Summary addSummary(Order order){
        Summary summary = summaryUtil.generateSummary(order);
        summaryDao.add(summary);
        return summary;
    }


}
