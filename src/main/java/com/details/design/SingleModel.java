package com.details.design;

/***
 * 单例模式
 * @author zlp
 * @date 15:54 2020/3/19
 */
public class SingleModel {

    /**
     * volatile 保证线程安全
     * 详情看E:\zlp\github\details\feels\interview\volatile.html
     */
    private static volatile SingleModel singleModel = null;

    public SingleModel() {
    }

    /**
     * 第一种单例模式，懒汉
     *
     * @return
     */
    public static SingleModel getSingleModel1() {
        if (singleModel == null) {
            return new SingleModel();
        }
        return singleModel;
    }

    /**
     * 双重检测
     *
     * @return
     */
    public static SingleModel getSingleModel2() {
        if (singleModel == null) {
            synchronized (singleModel) {
                if (singleModel == null) {
                    return new SingleModel();
                }
            }
        }
        return singleModel;
    }

    /**
     * 静态内部类
     */
    private static class Sin{
        private static final SingleModel SINGLETON = new SingleModel();
    }

    public static SingleModel getSingleModel3(){
        return Sin.SINGLETON;
    }

}
