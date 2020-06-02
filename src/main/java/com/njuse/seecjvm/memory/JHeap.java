package com.njuse.seecjvm.memory;

import com.njuse.seecjvm.runtime.struct.JObject;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Setter
@Getter
public class JHeap {
    private static JHeap jHeap = new JHeap();
    private static Set<JObject> objects;
    private static int maxSize = 50;
    private static int currentSize = 0;
    private static Map<Integer, Boolean> objectState;//true to present object is new added

    public static JHeap getInstance() {
        return jHeap;
    }

    private JHeap() {
        objects = new LinkedHashSet<>();
        objectState = new LinkedHashMap<>();
    }

    public void addObj(JObject obj) {
        if (currentSize >= maxSize) throw new OutOfMemoryError();
        objects.add(obj);
        objectState.put(obj.getId(), true);
        currentSize++;
    }

    public Set<JObject> getObjects() {
        return objects;
    }

    public static Map<Integer, Boolean> getObjectState() {
        return objectState;
    }

    public static void reset() {
        objects = new LinkedHashSet<>();
        objectState = new LinkedHashMap<>();
    }
}
