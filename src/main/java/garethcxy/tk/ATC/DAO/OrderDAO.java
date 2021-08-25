package garethcxy.tk.ATC.DAO;

import garethcxy.tk.ATC.Entity.AllUUIDResponse;
import garethcxy.tk.ATC.Entity.Order;
import garethcxy.tk.ATC.Util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class OrderDAO implements IDAO<Order>{
    @Autowired
    private OrderUtil orderUtil;

    private final LinkedList<Order> orderList = new LinkedList<>();

    @Value("${global.order.threshold}")
    private int maxStorage;

    @Override
    public Optional<Order> get(UUID id) {
        for(Order order : orderList){
            if(order.getUuid().equals(id)) return Optional.of(order);
        }
        return Optional.empty();
    }

    @Override
    public void add(Order order) {
        if(orderList.size() >= maxStorage) orderList.pollFirst();
        orderList.addLast(order);
    }

    @Override
    public boolean delete(UUID id) {
        return orderList.removeIf(order -> order.getUuid().equals(id));
    }

    public boolean clear(){
        try{
            this.orderList.clear();
        }catch (Exception e) { return false; }
        return true;
    }

    @Override
    public boolean delete(Order order) {
        return orderList.remove(order);
    }

    public List<AllUUIDResponse> getAllUUIDResponse() {
        List<AllUUIDResponse> result = new ArrayList<>();
        orderList.forEach(order -> result.add(orderUtil.buildAllUUIDResponse(order.getUuid(), order.getOrderDate())));
        return result;
    }
}
