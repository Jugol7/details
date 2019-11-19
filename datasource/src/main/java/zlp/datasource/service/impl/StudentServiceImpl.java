package zlp.datasource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zlp.datasource.dao.BaseDao;
import zlp.datasource.dao.cluster.StudentDao;
import zlp.datasource.entity.Student;
import zlp.datasource.service.StudentServiceI;

/**
 * @author zlp
 * @date 2019-11-06 15:34
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentServiceI {

    @Autowired
    private StudentDao studentDao;

    @Override
    protected BaseDao<Student> getMapper() {
        return studentDao;
    }
}
