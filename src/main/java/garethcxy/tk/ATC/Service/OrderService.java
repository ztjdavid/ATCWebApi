package garethcxy.tk.ATC.Service;

import garethcxy.tk.ATC.DAO.OrderDAO;
import garethcxy.tk.ATC.Entity.Item;
import garethcxy.tk.ATC.Entity.Order;
import garethcxy.tk.ATC.Entity.Summary;
import garethcxy.tk.ATC.Util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderUtil orderUtil;

    public Optional<Order> getById(String id){
        UUID uuid = UUID.fromString(id);
        return orderDAO.get(uuid);
    }

    public void addOrder(Order newOder){
        setSummary(newOder);
        orderDAO.add(newOder);
    }

    public List<UUID> getAllUUIDs(){
        return orderDAO.getAllUUID();
    }

    public Summary getSummaryById(String id){
        UUID uuid = UUID.fromString(id);
        return orderDAO.getSummaryById(uuid);
    }

    public void setSummary(String id){
        UUID uuid = UUID.fromString(id);
        orderDAO.setSummary(uuid,
                orderUtil.generateSummary(orderDAO.get(uuid).orElse(null)));
    }

    public void setSummary(Order order){
        orderDAO.setSummary(order, orderUtil.generateSummary(order));
    }

    public boolean delOrder(String id){
        UUID uuid = UUID.fromString(id);
        return orderDAO.delete(uuid);
    }
}
