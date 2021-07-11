package garethcxy.tk.ATC.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private String description = "Item";
    @JsonProperty(value = "price")
    private double price;
    @JsonProperty(value = "quantity")
    private int quantity;
    @JsonProperty(value = "relation")
    private Relation relation;
    @JsonProperty(value = "isTaxed")
    private boolean isTaxed;
    @JsonProperty(value = "shop")
    private Shop shop;
}
