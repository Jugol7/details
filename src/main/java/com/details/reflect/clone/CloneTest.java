package com.details.reflect.clone;

/**
 * @Author zlp
 * @Date 2020/5/25 10:52
 **/
public class CloneTest {

    public static void main(String[] args) {
            cloneTest();
    }

    public static void cloneTest(){
        CloneVO cloneVO = new CloneVO();
        ConleJO conleJO = new ConleJO();
        conleJO.setLike("fuck");
        cloneVO.setAge(23);
        cloneVO.setName("zlp");
        cloneVO.setConleJO(conleJO);
        Object clone = null;
        clone = cloneVO.clone();
        //浅克隆：cloneVO与clone的地址是不一样的，但是两个对象中的引用对象conleJO的地址是一致的。
        String like = (cloneVO).getConleJO().getLike();
        System.out.println(like);
        String likeClone = ((CloneVO) clone).getConleJO().getLike();
        System.out.println(likeClone);
        cloneVO.getConleJO().setLike("money");
        String like2 = (cloneVO).getConleJO().getLike();
        System.out.println(like2);
        String likeClone2 = ((CloneVO) clone).getConleJO().getLike();
        System.out.println(likeClone2);
        System.out.println(clone.equals(cloneVO));

    }


}
