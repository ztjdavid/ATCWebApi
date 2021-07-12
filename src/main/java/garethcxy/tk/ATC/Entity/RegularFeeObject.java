package garethcxy.tk.ATC.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegularFeeObject {
    @JsonProperty(value = "electricityFee")
    private double electricityFee;
    @JsonProperty(value = "amazonFee")
    private double amazonFee;
    @JsonProperty(value = "internetFee")
    private double internetFee;
    @JsonProperty(value = "otherFee")
    private double otherFee;

    public double calculate(){ return
            (this.amazonFee
            + this.electricityFee
            + this.internetFee
            + this.otherFee) / 3;}

}
