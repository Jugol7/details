package zlp.datasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zlp.datasource.entity.Student;
import zlp.datasource.service.StudentServiceI;

import javax.annotation.Resource;

/**
 * @author zlp
 * @date 2019-11-06 15:57
 */
@RestController
@RequestMapping(value = "/api/stu")
public class StudentController {

    @Resource
    private StudentServiceI studentServiceI;

    @PostMapping(value = "insert")
    public String insert(@RequestBody Student student) {
        try {
            if(studentServiceI.insert(student)){
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

}
