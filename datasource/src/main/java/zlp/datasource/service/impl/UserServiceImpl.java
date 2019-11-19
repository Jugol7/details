package zlp.datasource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zlp.datasource.dao.BaseDao;
import zlp.datasource.dao.master.UserDao;
import zlp.datasource.entity.User;
import zlp.datasource.service.UserServiceI;

/**
 * @author zlp
 * @date 2019-11-06 15:56
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserServiceI {

    @Autowired
    private UserDao userDao;

    @Override
    protected BaseDao<User> getMapper() {
        return userDao;
    }
}
