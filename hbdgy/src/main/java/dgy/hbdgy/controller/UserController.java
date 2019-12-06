package dgy.hbdgy.controller;

import dgy.hbdgy.entity.User;
import dgy.hbdgy.entity.UserToken;
import dgy.hbdgy.mapper.UserMapper;
import dgy.hbdgy.mapper.UserTokenMapper;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
@RequestMapping("user")
@CrossOrigin  //springboot中解决跨域问题
public class UserController {

    @Resource
    public UserMapper userMapper;

    @Resource
    private UserTokenMapper userTokenMapper;

    @PostMapping("save")
    public void addUserInfo(User user){
        if(user.getPassword().equals(0)||user.getUsername().isEmpty()){
            System.out.print("输入信息不全");
            return;
        }
        //利用MD5的方式进行加密，首先生成salt
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        String md5Password
                = getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        user.setPassword(md5Password);
        user.setCreateTime(new Date());
        userMapper.addUser(user);
        //在user_token里插入一个user_id,便于Login以后的token验证
        userTokenMapper.insertUserId(user.getUserId());

    }

    @PostMapping("login")
    public Map<String,Object> Login(User user){
        //首先对传输过来的信息进行验证
        User u= userMapper.getUserInfoByUserName(user.getUsername());
        //通过新传入的用户信息生成新的md5password与数据库的进行核对
        String newMd5password = getMd5Password(user.getPassword(),u.getSalt());
        Map<String,Object> map = new HashMap<String, Object>();
        if (newMd5password.equals(u.getPassword())){
            System.out.print("密码正确");
            //将后端生成的token传往前端
            String token = makeNewToken();
            //由于是登陆，将新生成的token传入user_token表里
            UserToken userToken = new UserToken();
            //生成当前时间
            Date now = new Date();
            //15分钟
            long time = 15*60*1000;
            //60秒后的时间
            Date afterDate = new Date(now .getTime() + time);
            //插入修改时间
            userToken.setUpdateTime(now);
            //插入token的过期时间
            userToken.setExpireTime(afterDate);
            userTokenMapper.updateTokenByUserId(userToken);
            //将前端生成的token和userId返回前端
            map.put("userId",user.getUserId());
            map.put("token",token);
            map.put("msg","登陆成功！");
            return  map;
        }
        //接受前端传递过来的token，并验证
        map.put("msg","密码不正确！");
        return  map;
    }

    //Md5password生成方法
    private String getMd5Password(
            String password, String salt) {
        // 加密规则
        // 1. 将盐值添加到原文的左侧
        // 2. 执行加密10次
        String str = salt + password;
        for (int i = 0; i < 10; i++) {
            str = DigestUtils
                    .md5DigestAsHex(str.getBytes())
                    .toUpperCase();
        }
        return str;
    }

    //token生成方法
    private  String makeNewToken(){
            String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
            try {
                MessageDigest md = MessageDigest.getInstance("md5");
                byte md5[] =  md.digest(token.getBytes());
                BASE64Encoder encoder = new BASE64Encoder();
                return encoder.encode(md5);
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
}
