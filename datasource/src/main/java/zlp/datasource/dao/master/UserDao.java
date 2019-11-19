package zlp.datasource.dao.master;

import org.apache.ibatis.annotations.Mapper;
import zlp.datasource.dao.BaseDao;
import zlp.datasource.entity.User;

/**
 * @author zlp
 * @date 2019-11-06 15:27
 */
@Mapper
public interface UserDao extends BaseDao<User> {
}
