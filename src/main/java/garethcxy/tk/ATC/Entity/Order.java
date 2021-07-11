package garethcxy.tk.ATC.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Order {
    private LocalDateTime orderDate;
    private UUID uuid;
    private Summary summary;
    @JsonProperty(value = "Target1Info")
    private String Target1Info;
    @JsonProperty(value = "Target2Info")
    private String Target2Info;
    @JsonProperty(value = "ThreePeople")
    private List<Item> ThreePeople;
    @JsonProperty(value = "Target1")
    private List<Item> Target1;
    @JsonProperty(value = "Target2")
    private List<Item> Target2;

    public Order() {
        uuid = UUID.randomUUID();
        orderDate = LocalDateTime.now();
        summary = new Summary();
    }
}
