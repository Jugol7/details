package com.details.controller;

import com.details.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 测试REST Client插件
 * @author zlp
 * @date 14:23  2019/12/2
 */
@RequestMapping(value = "/rest")
@RestController
@Slf4j
public class TestRestCientController {

    @PostMapping(value = "/test")
    public String testRestClient(@RequestBody Student student){
        //默认是info
        log.debug("-----------debug---"+student.toString());
        log.info("------------info--"+student.toString());
        return student.toString();
    }


}
