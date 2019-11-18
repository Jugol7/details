package com.details.linkedlist;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zlp
 * @date 2019-11-18 10:22
 */
@Slf4j
public class AppLinkedList {

    private static final Logger logger = LoggerFactory.getLogger(AppLinkedList.class);

    public static void main(String[] args) {
        deleteRepeatData();
    }

    public static void deleteRepeatData() {
        NoSingleLinkedList<Integer> myLinkedListRepeat = new NoSingleLinkedList<>();
        logger.info("/-/-/-//-/-/-/-/-初始化数据");
        myLinkedListRepeat.addEleLast(1);
        myLinkedListRepeat.addEleLast(2);
        myLinkedListRepeat.addEleLast(3);
        myLinkedListRepeat.addEleLast(4);
        myLinkedListRepeat.addEleLast(3);
        myLinkedListRepeat.addEleLast(1);
        logger.debug("before-----------"+myLinkedListRepeat.toString());
        NoSingleLinkedList<Integer> integerNoSingleLinkedList = myLinkedListRepeat.deleteTheRepeat(myLinkedListRepeat);
        logger.debug("after------------"+integerNoSingleLinkedList.toString());
        logger.debug("after------------"+myLinkedListRepeat.toString());
    }

}
