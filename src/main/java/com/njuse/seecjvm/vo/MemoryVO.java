package com.njuse.seecjvm.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MemoryVO {
    private List<ObjectVO> objects;
    private List<ClassVO> classes;

    public MemoryVO() {
        objects = new ArrayList<>();
        classes = new ArrayList<>();
    }
}
