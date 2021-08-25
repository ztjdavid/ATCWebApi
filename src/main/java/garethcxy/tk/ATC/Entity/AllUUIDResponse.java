package garethcxy.tk.ATC.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AllUUIDResponse {
    @JsonProperty("uuid")
    private UUID uuid;
    @JsonProperty("dateTime")
    private String dateTime;
}
