package garethcxy.tk.ATC.Util;

import garethcxy.tk.ATC.Entity.Item;
import garethcxy.tk.ATC.Entity.Relation;
import garethcxy.tk.ATC.Entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ItemUtil {
    private final double taxRate;
    private final HashMap<Shop, Double> shopRateMap = new HashMap<>();

    @Autowired
    public ItemUtil(ConfigLoader config){
        taxRate = config.getContent("Properties", "taxRate", double.class);
        shopRateMap.put(Shop.JNC, config.getContent("ShopRate", "JNC", Double.class));
        shopRateMap.put(Shop.NORMAL, config.getContent("ShopRate", "NORMAL", Double.class));
        shopRateMap.put(Shop.UBER, config.getContent("ShopRate", "UBER", Double.class));
    }

    public double calculatePrice(Item item){
        return ((item.getPrice() * item.getQuantity())
                * ((item.isTaxed())? taxRate + 1 : 1)
                * (1 + shopRateMap.get(item.getShop()))) / item.getRelation().getDivider();
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
