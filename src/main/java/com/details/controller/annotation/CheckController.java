package com.details.controller.annotation;

import com.details.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @author zlp
 * @date 13:52 2020/1/13
 */
@RestController
@Slf4j
@RequestMapping("/annotation")
public class CheckController {

    private static final Logger logger = LoggerFactory.getLogger(CheckController.class);

    @PostMapping("/check")
    public Student check(@RequestBody Student student){
        logger.info("请求参数：1."+student);
        return student;
    }


}
