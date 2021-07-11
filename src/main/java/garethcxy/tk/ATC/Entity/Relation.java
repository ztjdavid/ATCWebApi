package garethcxy.tk.ATC.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Relation {
    INDIVIDUAL(1, "个人"),
    SHARED(2, "双人"),
    ALL(3, "三人");

    private final double divider;
    private final String desc;

    Relation(int divider, String desc){
        this.divider = divider;
        this.desc = desc;
    }

    @JsonCreator
    public static Relation get(String s) {
        if(s.isEmpty()) return ALL;
        for(Relation relation : Relation.values()){
            if(relation.name().equalsIgnoreCase(s))
                return relation;
        }
        return ALL;
    }
}
