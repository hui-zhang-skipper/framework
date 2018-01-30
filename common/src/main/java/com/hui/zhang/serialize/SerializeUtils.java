package com.hui.zhang.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author zhanghui32
 * @date 2017/8/1
 */
public class SerializeUtils {


    public static byte[] javaSerialize(Object obj) throws Exception {
        if (obj == null) throw new NullPointerException();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(obj);
        return os.toByteArray();
    }

    public static Object javaDeserialize(byte[] by) throws Exception {
        if (by == null) throw new NullPointerException();

        ByteArrayInputStream is = new ByteArrayInputStream(by);
        ObjectInputStream in = new ObjectInputStream(is);
        return in.readObject();
    }
}
