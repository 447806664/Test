package com.test.java.stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * Created by 123 on 2016/2/1.
 */
public class PushbackInputStreamDemo {

    public static void main(String[] args) {
        try {
            PushbackInputStream pushbackInputStream = new PushbackInputStream(new FileInputStream(args[0]));

            byte[] array = new byte[2];

            int tmp = 0;
            int count = 0;

            while ((count = pushbackInputStream.read(array)) != -1) {
                //将两个字节转换成整数
                tmp = (short) ((array[0] << 8) | (array[1] & 0xff));
                tmp = tmp & 0xFFFF;

                //判断是否为BIG5，如果是则显示BIG5中文字
                if (tmp >= 0xA440 && tmp < 0xFFFF) {
                    System.out.println("BIG5: " + new String(array));
                } else {
                    //将第二个字节推回
                    pushbackInputStream.unread(array, 1, 1);
                    //显示ASCII范围的字符
                    System.out.println("ASCII: " + (char) array[0]);
                }
            }

            pushbackInputStream.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
