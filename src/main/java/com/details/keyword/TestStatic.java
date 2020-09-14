package com.details.keyword;

import lombok.Data;

/**
 * 文档：static关键字.md
 * 链接：http://note.youdao.com/noteshare?id=d3bbb4a0ec69609a66616c1399b136f9&sub=F5401E18D60C45BD8B1CFAC09FA10ECA
 * @author zlp
 * @date 2020/8/19 20:45
 */
@Data
public class TestStatic {

    private String s;

    public final double i = Math.random();
    public static double t = Math.random();
}
