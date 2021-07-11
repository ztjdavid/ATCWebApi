package garethcxy.tk.ATC.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Getter
public enum Shop {
    JNC(0.05, "金牛城超市Online"),
    UBER(0.113, "Uber Eats"),
    NORMAL(0, "普通超市");

    private final double rate;
    private final String desc;

    Shop(double rate, String desc){
        this.rate = rate;
        this.desc = desc;
    }

    @JsonCreator
    public static Shop get(String s) {
        if(s.isEmpty()) return NORMAL;
        for(Shop shop : Shop.values()){
            if(shop.name().equalsIgnoreCase(s))
                return shop;
        }
        return NORMAL;
    }

}
