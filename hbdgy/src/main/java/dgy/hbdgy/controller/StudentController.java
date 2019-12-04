package dgy.hbdgy.controller;

import dgy.hbdgy.entity.Student;
import dgy.hbdgy.mapper.StudentMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin  //springboot中解决跨域问题
@RequestMapping("student")
public class StudentController {
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private StudentMapper studentMapper;

    @PostMapping("get")
    public Student getBySno(String sno){
        Student s  = studentMapper.findBySno(sno);
        return  s;
    }

    @CrossOrigin   //解决跨域的问题
    @RequestMapping("list")
    public List<Student> getAll(){
        List<Student> list = new ArrayList<Student>();
        list = studentMapper.findAll();
       // for(Student s : list){
         //   System.out.println("list="+s);
       // }

        return  list;
    }

    @PostMapping("save")
    public void saveInfo(Student student){
       try{
           studentMapper.insertInfo(student);
       }catch(Exception e){
           System.err.println("插入studennt数据异常");
       }
    }

    @PostMapping("delete")
    public void removeInfo(String sno){
        try{
            studentMapper.deleteInfo(sno);
        }catch(Exception e){
            System.err.println("根据学号删除studennt数据异常");
        }
    }

    @CrossOrigin   //解决跨域的问题
    @RequestMapping("page")
    public List<Student> getPageAll(int currentPage,int size){
        List<Student> list = new ArrayList<Student>();
        //分页数据计算
        int first = (currentPage-1)*size;
        int second = size;
        list = studentMapper.findPageAll(first,second);
        // for(Student s : list){
        //   System.out.println("list="+s);
        // }
        // Result<Student> rs = new Result<Student>();


        return  list;
    }
}
