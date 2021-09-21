package garethcxy.tk.ATC.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class CheckIn {
    @JsonProperty(value = "cronTimerStr")
    private String cronTimeStr;
    @JsonProperty(value = "name")
    private String name = null;

    private HashMap<String, String> orderMap;

    @JsonCreator
    public CheckIn(@JsonProperty(value = "participants") List<String> participants){
        // initialize map
        for(int i = 0; i < participants.size() - 1; i++){
            orderMap.put(participants.get(i), participants.get(i + 1));
        }
        orderMap.put(participants.get(participants.size() - 1), participants.get(0));
    }

}
