package zlp.datasource.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zlp.datasource.entity.User;
import zlp.datasource.service.UserServiceI;

import javax.annotation.Resource;

/**
 * @author zlp
 * @date 2019-11-06 17:15
 */
@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Resource
    private UserServiceI userServiceI;

    @PostMapping(value = "insert")
    public String insert(@RequestBody User user){
        try {
            if(userServiceI.insert(user)){
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
