package com.details.algorithm;

import java.util.Arrays;

/***
 * @author zlp
 * @date 20:23 2020/4/3
 */
public class TestInt {

    public static void main(String[] args) {
        TestInt t = new TestInt();
        int[] method = t.method();
        System.out.println(Arrays.toString(method));
    }


    public int[] method() {
        int[] ints = {6, 4, -3, 5, -2, -1, 0, 1, -9};
        int[] result = new int[ints.length];
        int k = 0;
        int l = ints.length - 1;
        for (int i = 0; i < ints.length; i++) {
            System.out.println("i==" + i + "      k==" + k + "         l==" + l);
            if (ints[i] < 0) {
                result[k++] = ints[i];
            } else {
                result[l--] = ints[i];
            }
        }
        return result;

    }

    public void nodeMethod() {
        int[] ints = {6, 4, -3, 5, -2, -1, 0, 1, -9};
        NodeTest<Integer> nodeTest = new NodeTest(null,ints[0],null);
        for (int i = 0; i < ints.length; i++) {
            if(ints[i] < 0){

            }else {

            }
        }


    }


}
class NodeTest<E>{
    E element;
    NodeTest<E> pre;
    NodeTest<E> next;

    NodeTest(NodeTest<E> pre, E element, NodeTest<E> next) {
        this.pre = pre;
        this.element = element;
        this.next = next;
    }
}