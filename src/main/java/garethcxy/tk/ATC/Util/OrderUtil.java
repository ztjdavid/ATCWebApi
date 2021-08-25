package garethcxy.tk.ATC.Util;

import garethcxy.tk.ATC.Entity.AllUUIDResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class OrderUtil {
    public AllUUIDResponse buildAllUUIDResponse(UUID uuid, LocalDateTime time){
        AllUUIDResponse temp = new AllUUIDResponse();
        temp.setUuid(uuid);
        temp.setDateTime(DateTimeToString(time));
        return temp;
    }

    public String DateTimeToString(LocalDateTime localDateTime){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm E");
        return df.format(localDateTime);
    }
}
