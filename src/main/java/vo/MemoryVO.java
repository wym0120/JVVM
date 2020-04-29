package vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author xiao bai
 * Created on 2020-04-14
 */
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
