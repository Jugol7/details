package com.details.linkedlist;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

/**
 * 单向链表
 *
 * @author zlp
 * @date 2019-11-14 14:01
 */
@Slf4j
public class NoSingleLinkedList<E> {

    private final static Logger logger = LoggerFactory.getLogger(NoSingleLinkedList.class);

    /**
     * 最后一个节点
     */
    private Node<E> last;

    public Node<E> getLast() {
        return last;
    }

    /**
     * 第一个节点
     */
    private Node<E> first;

    public Node<E> getFirst() {
        return first;
    }

    private int size = 0;

    public int getSize(){
        return size;
    }

    private int modCount = 0;

    public NoSingleLinkedList() {
    }

    public NoSingleLinkedList(Node<E> last, Node<E> first) {
        this.first = first;
        this.last = last;
    }

    /**
     * 新增元素
     * 默认新增下一个节点
     *
     * @param e
     * @return
     */
    public void addEleLast(E e) {
        //将已知的链表中的最后一个节点拿出来，
        Node<E> temp = last;
        //将最后一个节点给新增的节点的pre
        Node<E> newNode = new Node<>(temp, e, null);
        //把新增的节点放置到最后一个
        last = newNode;
        //判断是否是第一次新增
        if (temp == null) {
            //如果是，那么当下的first与last是同一个
            first = newNode;
        } else {
            //如果不是，那么将上一个last值（也就是现在l）得next指向新增的值，
            temp.next = newNode;
        }
        size++;
        //修改的次数
        modCount++;
    }

    /**
     * 从头节点插入元素
     *
     * @param e
     */
    public void addEleFirst(E e) {
        //将当前链表的头节点赋值给temp
        Node<E> temp = first;
        //声明新增节点
        Node<E> newNode = new Node<>(null, e, temp);
        //将新增节点给第一个
        first = newNode;
        if (temp == null) {
            //如果第一个为空，那么代表这是第一次新增数据，所有将last也赋值为此值
            last = newNode;
        } else {
            //如果不是，那么将指向的pre设置为此值
            temp.pre = newNode;
        }
        size++;
        modCount++;
    }

    public E getEle(int index) {
        if (!checkIndexIsExist(index)) {
            return null;
        }
        return getNode(index).element;
    }

    /**
     * 获取链表中node
     *
     * @param index
     * @return
     */
    public Node<E> getNode(int index) {
        if (!checkIndexIsExist(index)) {
            return null;
        }
        //查看index的大小，与size的一般相比
        if (index < (size >> 1)) {
            //从头开始找
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            //从尾开始找
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.pre;
            }
            return node;
        }
    }

    /**
     * 判断index是否符合
     *
     * @param index
     * @return
     */
    public boolean checkIndexIsExist(int index) {
        if (index < 0 || index > size) {
            return false;
        }
        return true;
    }

    /**
     * 删除指定元素
     *
     * @param e
     * @return
     */
    public boolean deleteEle(E e) {
        for (int i = 0; i < size; i++) {
            Node<E> node = getNode(i);
            if (e.equals(node)) {
                //判断删除元素的位置
                if (null == node.pre) {
                    //位置为头元素
                    node.next.pre = null;
                    first = node.next;
                } else if (null == node.next) {
                    //位置为尾元素
                    node.pre.next = null;
                    last = node.pre;
                } else {
                    //位置在中间
                    //将此对象的pre的next指向此对象的next
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                }
                size--;
                modCount++;
                return true;
            }
        }
        return false;
    }

    /**
     * 删除链表中的重复的元素
     *
     * @param noSingleLinkedList
     * @return
     */
    public NoSingleLinkedList<E> deleteTheRepeat(NoSingleLinkedList<E> noSingleLinkedList) {
        Node<E> nodeOutside = getFirst();
        for (int i = 0; i < noSingleLinkedList.size - 1; i++) {
            Node<E> nodeInside = nodeOutside.next;
            for (int j = i + 1; j < noSingleLinkedList.size; j++) {
                //判断是否是最后一个
                Node<E> next = null;
                if (i != noSingleLinkedList.size) {
                    next = nodeInside.next;
                }
                Node<E> pre = nodeInside.pre;
                if (nodeOutside.element.equals(nodeInside.element)) {
                    if (next != null) {
                        //链表中没有指向此元素的值，就表示链表中不存在此元素
                        next.pre = pre;
                        pre = next;
                    } else {
                        pre = null;
                    }
//                    nodeInside.next = null;
//                    nodeInside.pre = null;
                    size--;
                    modCount++;
                }
                nodeInside = nodeInside.next;
            }
            nodeOutside = nodeOutside.next;
        }
        return noSingleLinkedList;
    }

    public static void myLinkedList() {
        NoSingleLinkedList<Integer> myLinkedList = new NoSingleLinkedList<>();
        for (int num = 0; num < 5; num++) {
            myLinkedList.addEleLast(num);
            logger.info("/-/-/-//-/-/-/-/-新增" + (num + 1) + "个数据之后的结构");
            logger.info("" + myLinkedList.getFirst());
            logger.info("" + myLinkedList.getLast());
        }
        logger.info("/-/---/-/-/-///-/-/-///从头部新增一个元素 -1");
        myLinkedList.addEleFirst(-1);
        logger.debug("" + myLinkedList.getFirst());
        logger.debug(myLinkedList.toString());
        myLinkedList.deleteEle(3);
        logger.debug(myLinkedList.toString());
    }

    /**
     * 试用java的LinkedList
     */
    public static void javaLinkedList() {
        logger.info("/-/-//-/-/-//-/-//--/--我的LinkedList操作开始了");
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int num = 0; num < 5; num++) {
            linkedList.add(num);
            logger.info("/-/-/-//-/-/-/-/-新增" + (num + 1) + "个数据之后的结构");
            System.out.println(linkedList.getFirst());
            System.out.println(linkedList.getLast());
        }
        Integer re = 1;
        linkedList.remove(re);
        //返回第一个元素的值
        Integer peek = linkedList.peek();
        linkedList.remove(1);
        logger.info("/-/-//-/-/-//-/-//--/--我的LinkedList操作结束了");
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            stringBuffer.append(node.element + "***");
            node = node.next;
        }
        return stringBuffer.toString();
    }

    /**
     * 模仿LinkedList声明Node
     *
     * @param <E>
     */
    private class Node<E> {
        E element;
        Node<E> pre;
        Node<E> next;

        Node(Node<E> pre, E element, Node<E> next) {
            this.pre = pre;
            this.element = element;
            this.next = next;
        }
    }
}
