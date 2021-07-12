package garethcxy.tk.ATC.Util;

import garethcxy.tk.ATC.Entity.Item;
import garethcxy.tk.ATC.Entity.Order;
import garethcxy.tk.ATC.Entity.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Component
public class TxtUtil {
    @Autowired
    private ItemUtil itemUtil;
    private final String indent = "    ";
    private final String divider = "-----------------------------------\n";
    private final DateTimeFormatter dateFormatString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String getTxt(Order order, double total1, double total2){
        // Assemble info
        return getBaseInfo(order.getUuid(), order.getOrderDate(),
                order.getTarget1Info(), order.getTarget2Info()) +
                getCatagoryInfo(order.getFees().getElectricityFee(),
                        order.getFees().getAmazonFee(),
                        order.getFees().getInternetFee(),
                        order.getFees().getOtherFee()) +
                getThreePeopleInfo(order.getThreePeople()) +
                getTarget1Info(order.getTarget1(), order.getTarget1Info()) +
                getTarget2Info(order.getTarget2(), order.getTarget2Info()) +
                getSummaryInfo(order.getTarget1Info(), order.getTarget2Info(),
                        total1, total2) + "End.";
    }

    public String getBaseInfo(UUID id, LocalDateTime createTime,
                              String target1, String target2){
        return divider + "订单UUID: " + id.toString() + "\n"
                + "创建时间: " + dateFormatString.format(createTime) + "\n"
                + "账单对象: " + target1 + ", " + target2 + "\n";
    }

    public String getCatagoryInfo(double electricityFee,
                                  double amazonFee,
                                  double internetFee,
                                  double otherFee){
        return divider + "电费: " + electricityFee + "\n"
                + "Amazon费: " + amazonFee + "\n"
                + "网费: " + internetFee + "\n"
                + "其他费用: " + otherFee + "\n";
    }

    public String getThreePeopleInfo(List<Item> items){
        return getItemInfo(items, "三人物品");
    }

    public String getTarget1Info(List<Item> items, String target1){
        return getItemInfo(items, target1);
    }

    public String getTarget2Info(List<Item> items, String target2){
        return getItemInfo(items, target2);
    }

    public String getSummaryInfo(String target1, String target2,
                                 double total1, double total2){
        return divider + target1 + ": " + total1 +
                target2 + ": " + total2 + "\n";
    }

    private String getItemInfo(List<Item> items, String title){
        StringBuilder sb = new StringBuilder(divider);
        sb.append(title).append(": \n");
        items.forEach(item -> {
            sb.append(indent).append(itemUtil.getItemDesc(item)).append("\n");
        });
        return sb.toString();
    }
}
