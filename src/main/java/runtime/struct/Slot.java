package runtime.struct;

import lombok.Data;

@Data
public class Slot {
    private JObject object;
    private Integer value;
}
