package classloader.constantpool;

import classloader.constantpool.info.ConstantPoolInfo;
import com.sun.tools.javac.util.Pair;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class ConstantPool {
    private ConstantPoolInfo[] infos;

    public static Pair<ConstantPool, Integer> getInstance(int constantPoolCnt, byte[] in, int offset) {
        ConstantPool constantPool = new ConstantPool();
        constantPool.infos = new ConstantPoolInfo[constantPoolCnt];
        int currentOfs = offset;
        int entryCnt = 0;
        while (entryCnt < constantPoolCnt - 1) {
            Pair<ConstantPoolInfo, Integer> infoInt = ConstantPoolInfo.getConstantPoolInfo(in, currentOfs);
            ConstantPoolInfo info = infoInt.fst;
            constantPool.infos[entryCnt] = info;
            entryCnt += info.getEntryLength();
            currentOfs += infoInt.snd;
        }
        return Pair.of(constantPool, currentOfs - offset);
    }

    public ConstantPoolInfo get(int i) {
        if (i <= 0 || i > infos.length - 1) {
            throw new UnsupportedOperationException("Invalid CP index " + i);
        }

        ConstantPoolInfo info = infos[i - 1];
        if (info == null) {
            throw new UnsupportedOperationException("Invalid CP index " + i);
        }
        return info;
    }


}
