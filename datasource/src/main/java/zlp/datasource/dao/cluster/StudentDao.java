package zlp.datasource.dao.cluster;

import org.apache.ibatis.annotations.Mapper;
import zlp.datasource.dao.BaseDao;
import zlp.datasource.entity.Student;

/**
 * @author zlp
 * @date 2019-11-06 15:26
 */
@Mapper
public interface StudentDao extends BaseDao<Student> {
}
