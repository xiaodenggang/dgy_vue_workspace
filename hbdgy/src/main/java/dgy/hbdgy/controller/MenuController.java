package dgy.hbdgy.controller;

import dgy.hbdgy.mapper.MenuMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin  //springboot中解决跨域问题
@RequestMapping("menu")
public class MenuController {

    @Resource
    private MenuMapper menuMapper;

    @PostMapping("list")
    public List getAllMenuInfo(){
        List ls = menuMapper.getAllMenu();
        return ls;
    }
}
