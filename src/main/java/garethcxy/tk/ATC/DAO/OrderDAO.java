package garethcxy.tk.ATC.DAO;

import garethcxy.tk.ATC.Entity.Item;
import garethcxy.tk.ATC.Entity.Order;
import garethcxy.tk.ATC.Entity.Summary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrderDAO implements IDAO<Order>{
    private final ArrayList<Order> orderList = new ArrayList<>();

    @Override
    public Optional<Order> get(UUID id) {
        for(Order order : orderList){
            if(order.getUuid().equals(id)) return Optional.of(order);
        }
        return Optional.empty();
    }

    @Override
    public void add(Order order) {
        orderList.add(order);
    }

    @Override
    public boolean delete(UUID id) {
        return orderList.removeIf(order -> {return order.getUuid().equals(id);});
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
        orderList.forEach(order -> {
            result.add(order.getUuid());
        });
        return result;
    }
}
