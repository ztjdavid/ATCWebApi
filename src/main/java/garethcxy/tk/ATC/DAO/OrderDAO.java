package garethcxy.tk.ATC.DAO;

import garethcxy.tk.ATC.Entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderDAO implements IDAO<Order>{
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

    @Override
    public List<UUID> getAllUUID() {
        List<UUID> result = new ArrayList<>();
        orderList.forEach(order -> result.add(order.getUuid()));
        return result;
    }
}
