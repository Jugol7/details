package com.details.controller.vue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(VueController.class);

    @GetMapping(value = "/vue")
    public String vueInfo(){
        logger.info("进入方法");
        return "success";
    }
}
