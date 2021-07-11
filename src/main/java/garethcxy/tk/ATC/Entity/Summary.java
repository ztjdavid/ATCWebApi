package garethcxy.tk.ATC.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Summary {
    private List<String> ItemsForTarget1;
    private List<String> ItemsForTarget2;
    private List<String> ItemsForAll;
    private double TotalTaregt1;
    private double TotalTaregt2;
    private String txtString;
    private String Target1Info;
    private String Target2Info;
}
