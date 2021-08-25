package garethcxy.tk.ATC.Resource;

import garethcxy.tk.ATC.Entity.AllUUIDResponse;
import garethcxy.tk.ATC.Entity.Order;
import garethcxy.tk.ATC.Entity.Summary;
import garethcxy.tk.ATC.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/atc/api/{version}")
public class OrderResource {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public UUID addOrder(@RequestBody Order order){
        orderService.addOrder(order);
        return order.getUuid();
    }

    @GetMapping("/order/all")
    public List<AllUUIDResponse> getAllUUIDs(){
        return orderService.getAllUUIDs();
    }

    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable("id") String id){
        return orderService.getById(id).orElse(null);
    }

    @PostMapping("/order/del/{id}")
    public boolean delOrder(@PathVariable("id") String id){
        return orderService.delOrder(id);
    }

    @PostMapping("/order/clear")
    public boolean clear(){ return orderService.clear(); }
}
