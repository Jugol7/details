package com.details.controller.mongo;

import com.details.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/***
 * 简单操作mongodb
 * @author zlp
 * @date 13:52 2020/3/16
 */
@Slf4j
@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Resource
    private MongoTemplate mongoTemplate;

    @PostMapping("/addOrModify")
    public String addOrModify(@RequestBody Student student){
        Update update = new Update();
        update.set("Address", student.getName());
        Student stu = mongoTemplate.findAndModify(Query.query(where("name").is("lp")), update,Student.class);
        if(stu == null){
            mongoTemplate.insert(student);
        }
        return "";
    }


}