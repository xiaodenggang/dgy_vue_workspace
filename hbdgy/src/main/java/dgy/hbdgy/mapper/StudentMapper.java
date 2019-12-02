package dgy.hbdgy.mapper;

import dgy.hbdgy.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    //根据学生号查询学生相关信息
    Student findBySno(String sno);

    //获取学生表中的所有信息
    List<Student> findAll();

    //根据学号删除学生相关信息
    Integer deleteBySno(String sno);

    //插入学生信息
    Integer insertInfo(Student student);

    //根据学号删除相关学生信息
    Integer deleteInfo(String sno);

    //分页获取学生表中的所有信息
    List<Student> findPageAll(int first,int second);

}
