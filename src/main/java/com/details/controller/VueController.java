package com.details.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * vue 测试调用接口
 * @author zlp
 * @date 2019-11-04 15:50
 */
@RestController
@RequestMapping(value = "/vue")
public class VueController {

    @GetMapping(value = "/vue")
    public String vueInfo(){
        return "success";
    }
}
