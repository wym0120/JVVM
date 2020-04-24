package runtime.struct;

import lombok.Data;

@Data
public class Slot {
    private JObject object;
    private Integer value;

    public Slot clone(){
        Slot toClone = new Slot();
        toClone.object = this.object;
        toClone.value = this.value;
        return toClone;
    }
}
