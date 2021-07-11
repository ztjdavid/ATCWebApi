package garethcxy.tk.ATC.Util;

import garethcxy.tk.ATC.Entity.Item;
import garethcxy.tk.ATC.Entity.Relation;
import garethcxy.tk.ATC.Entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ItemUtil {

    @Value("${global.tax.rate}")
    private double taxRate;

    public double calculatePrice(Item item){
        return ((item.getPrice() * item.getQuantity())
                * ((item.isTaxed())? taxRate + 1 : 1)
                * (1 + item.getShop().getRate())) / item.getRelation().getDivider();
    }

    public String getItemDesc(Item item){
        return String.format("%s: (%s) (%s) (%s) $%.2f * %d",
                item.getDescription(),
                item.getShop().getDesc(),
                item.getRelation().getDesc(),
                (item.isTaxed())? "有税" : "无税",
                item.getPrice(),
                item.getQuantity());
    }
}
