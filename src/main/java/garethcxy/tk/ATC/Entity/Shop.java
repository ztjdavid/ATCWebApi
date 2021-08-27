package garethcxy.tk.ATC.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import garethcxy.tk.ATC.Util.ConfigLoader;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Getter
public enum Shop {
    JNC("金牛城超市Online"),
    UBER( "Uber Eats"),
    NORMAL("普通超市");

    private final String desc;

    Shop(String desc){
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
