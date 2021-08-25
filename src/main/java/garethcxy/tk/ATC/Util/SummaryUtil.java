package garethcxy.tk.ATC.Util;

import garethcxy.tk.ATC.Entity.Item;
import garethcxy.tk.ATC.Entity.Order;
import garethcxy.tk.ATC.Entity.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class SummaryUtil {
    @Autowired
    private TxtUtil txtUtil;
    @Autowired
    private ItemUtil itemUtil;

    public Summary generateSummary(Order order){
        if(order == null) return null;
        Summary result = new Summary();
        result.setUuid(order.getUuid());
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

    private List<String> getStringList(List<Item> itemList){
        List<String> result = new ArrayList<>();
        itemList.forEach(item -> result.add(itemUtil.getItemDesc(item)));
        return result;
    }

    private double calculateForTarget1(Order order) {
        double value = calculateForList(order.getTarget1())
                + calculateForList(order.getThreePeople())
                + order.getFees().calculate();
        BigDecimal rounder = new BigDecimal(value);
        return rounder.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    private double calculateForTarget2(Order order) {
        double value = calculateForList(order.getTarget2())
                + calculateForList(order.getThreePeople())
                + order.getFees().calculate();
        BigDecimal rounder = new BigDecimal(value);
        return rounder.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private double calculateForList(List<Item> items){
        double result = 0;
        for(Item item : items){
            result += itemUtil.calculatePrice(item);
        }
        return result;
    }
}
