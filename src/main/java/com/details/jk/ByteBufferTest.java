package com.details.jk;

import java.nio.ByteBuffer;

/**
 * 首先需要了解一些相关概念：
 * 1.mark 记录了当前所标记的索引下标；
 * 2.position 对于写入模式，表示当前可写入数据的下标，对于读取模式，表示接下来可以读取的数据的下标；
 * 3.limit 对于写入模式，表示当前可以写入的数组大小，默认为数组的最大长度，对于读取模式，表示当前最多可以读取的数据的位置下标；
 * 4.capacity 表示当前数组的容量大小。
 *
 * @author zlp
 * @date 2022/04/30
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        print(buffer);

        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        print(buffer);

        // 而 flip() 的作用是翻转，也就是让 flip 之后的 position 到 limit 这块区域变成之前的 0 到 position 这块，翻转就是将一个处于存数据状态的缓冲区变为一个处于准备取数据的状态
        buffer.flip();
        print(buffer);

        buffer.get();
        buffer.get();
        print(buffer);
    }

    public static void print(ByteBuffer buffer) {
        System.out.printf("position: %d, limit: %d, capacity: %d\n",
                buffer.position(), buffer.limit(), buffer.capacity());
    }

}
