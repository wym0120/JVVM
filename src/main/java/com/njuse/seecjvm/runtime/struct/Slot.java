package com.njuse.seecjvm.runtime.struct;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Slot {
    private JObject object;
    private Integer value;

    public Slot clone() {
        Slot toClone = new Slot();
        toClone.object = this.object;
        toClone.value = this.value;
        return toClone;
    }
}
