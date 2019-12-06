package dgy.hbdgy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
@Mapper
@Repository
public interface MenuMapper {

    //获取所有的菜单信息
    List getAllMenu();
}
