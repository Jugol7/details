package com.details.linkedlist;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FIFO 先进先出
 * 当缓存已满之后，总是删除第一个元素
 *
 * @author zlp
 * @date 2019-11-19 15:37
 */
@Slf4j
public class FIFO {

    private static Logger logger = LoggerFactory.getLogger(FIFO.class);

    public static void main(String[] args) {
        NoSingleLinkedList<Integer> noSingleLinkedList = new NoSingleLinkedList<>();
        for (int i = 0; i < 9; i++) {
//            logger.debug("---------------FIFO开始--------args：" + noSingleLinkedList.toString());
            fifo(noSingleLinkedList, 2, i);
            logger.debug("---------------FIFO结束--------args：" + noSingleLinkedList.toString());
        }
    }

    public static void fifo(NoSingleLinkedList noSingleLinkedList, int size, Integer i) {
        if (noSingleLinkedList.getSize() < size) {
            noSingleLinkedList.addEleLast(i);
        } else {
            noSingleLinkedList.deleteEle(noSingleLinkedList.getFirst());
            noSingleLinkedList.addEleLast(i);
        }
    }
}
