package garethcxy.tk.ATC.Util;

import garethcxy.tk.ATC.Entity.Item;
import garethcxy.tk.ATC.Entity.Order;
import garethcxy.tk.ATC.Entity.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderUtil {
    @Autowired
    private ItemUtil itemUtil;
    @Autowired
    private TxtUtil txtUtil;

    public double calculateForTarget1(Order order) {
        return calculateForList(order.getTarget1())
                + calculateForList(order.getThreePeople())
                + order.getFees().calculate();
    }
    public double calculateForTarget2(Order order) {
        return calculateForList(order.getTarget2())
                + calculateForList(order.getThreePeople())
                + order.getFees().calculate();
    }

    private double calculateForList(List<Item> items){
        double result = 0;
        for(Item item : items){
            result += itemUtil.calculatePrice(item);
        }
        return new BigDecimal(result).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public List<String> getStringList(List<Item> itemList){
        List<String> result = new ArrayList<>();
        itemList.forEach(item -> {
            result.add(itemUtil.getItemDesc(item));
        });
        return result;
    }

    public Summary generateSummary(Order order){
        if(order == null) return null;
        Summary result = new Summary();
        result.setTarget1Info(order.getTarget1Info());
        result.setTarget2Info(order.getTarget2Info());
        result.setElectricityFee(order.getFees().getElectricityFee());
        result.setAmazonFee(order.getFees().getAmazonFee());
        result.setInternetFee(order.getFees().getInternetFee());
        result.setOtherFee(order.getFees().getOtherFee());
        result.setItemsForTarget1(getStringList(order.getTarget1()));
        result.setItemsForTarget2(getStringList(order.getTarget2()));
        result.setItemsForAll(getStringList(order.getThreePeople()));
        double totoal1 = calculateForTarget1(order);
        double totoal2 = calculateForTarget2(order);
        result.setTotalTarget1(totoal1);
        result.setTotalTarget2(totoal2);
        result.setTxtString(txtUtil.getTxt(order, totoal1, totoal2));

        return result;
    }
}
