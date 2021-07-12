package garethcxy.tk.ATC.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegularFeeObject {
    @NotNull
    @JsonProperty(value = "electricityFee")
    private double electricityFee;
    @NotNull
    @JsonProperty(value = "amazonFee")
    private double amazonFee;
    @NotNull
    @JsonProperty(value = "internetFee")
    private double internetFee;
    @NotNull
    @JsonProperty(value = "otherFee")
    private double otherFee;

    public double calculate(){ return
            (this.amazonFee
            + this.electricityFee
            + this.internetFee
            + this.otherFee) / 3;}

}
