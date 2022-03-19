package com.wizardry.qingyou.controller;

import com.wizardry.qingyou.entity.User;
import com.wizardry.qingyou.service.impl.UserServiceImpl;
import com.wizardry.qingyou.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("users")
public class UserController extends BaseController {
    // 用户模块业务层
    @Autowired
    private UserServiceImpl service;

    // 设置一个常量，该常量设置为10M，用于比对，注：springMVC对于大小默认为字节
    private final Integer AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    // 设置上传文件的类型
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    // 静态代码块,目前就三种
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/webp");
    }

    private final String objName = "wizardry/imgs/avatars/";

    /**
     * 用户注册
     *
     * @param user 用户数据
     * @return Json对象
     */
    @RequestMapping("register")
    // 表示数据以Json的方式传递给前端
    public JsonResult<Void> register(@RequestBody User user) {
        System.out.println(user);
        service.register(user);
        // 假设有异常产生会自动跳转到基类当中处理的方法，注册成功为2000
        return new JsonResult<>(registerSuccess);
    }


    /**
     * login请求的一些修改
     *
     * @param user    用户对象
     * @param session 无论是哪种类型登录成功，最终只保存用户名的方式
     * @return Json对象
     */
    @RequestMapping(path = "login", method = RequestMethod.POST)
    public JsonResult<User> login(@RequestBody User user, HttpSession session) {
        System.out.println(user.toString());
        // 调用接口当中的login方法，该方法先进行查询用户的操作，查询完毕后返回一个user对象
        User data = service.login(user);
        //向session对象中完成数据的绑定,将用户的id和用户名传递给session对象(该对象是全局的)
        session.setAttribute("id", data.getId());
        session.setAttribute("username", data.getUname());
        System.out.println("登陆成功！");
        // 保存在session中的参数
        /*System.out.println("该用户的id为："+session.getAttribute("id"));
        System.out.println("该用户的uname为："+session.getAttribute("username"));*/
        //将这个user对象返回给前端，只能在该页面进行
        return new JsonResult<>(loginSuccess, data);//可写可不写
    }


    /**
     * 用户修改密码
     *
     * @param session     seesion对象
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return Json对象
     */
    @RequestMapping(path = "update/password", method = RequestMethod.POST)
    public JsonResult<Void> changePassword(HttpSession session,
                                           String oldPassword,
                                           String newPassword) {
        // session获取到用户的id
        Integer uid = getUidFromSession(session);
        // session获取到用户的uname
        service.UpdatePsw(uid, oldPassword, newPassword);
        return new JsonResult<>(2003);
    }

    @PostMapping("update/avatar")
    public JsonResult<String> updateAvatar(@RequestParam("uid") String uid,
                                           @RequestParam("uname") String uname,
                                           @RequestParam("file") MultipartFile file) {
        // 》》》文件大小限制由 Spring 来限制，Spring 可以限制一次性传入的图片大小。图片大小限制由前端拦截即可。《《《

//        // 判断文件是否为空
//        if (file.isEmpty()) {
//            throw new FileEmptyException("文件为空");
//        }
//        if (file.getSize() > AVATAR_MAX_SIZE) {
//            throw new FileSizeException("文件大小超过限制");
//        }
//        // 文件是否符合我们规定的类型
//        String contentType = file.getContentType();
//        if (!AVATAR_TYPE.contains(contentType)) {
//            throw new FileTypeException("文件类型不符合");
//        }
        // 》》》文件名称不能使用原图片名称《《《
//        String usernameType = file.getOriginalFilename();
        // 》》》文件后缀也不使用原本的文件后缀，统一为 JPG，文件的格式以及名称在上传OSS的时候会根据你的objectName那个属性来定义。《《《
//        int index = usernameType.lastIndexOf(".");
//        String sufix = usernameType.substring(index);
        // 》》》文件名称也不需要在这里就开始组合，在 service 层处理业务，不要在 controller 层处理业务《《《
//        sufix = objName + uid + "/" + uname + sufix;
        return service.updateAvatar(uid, uname, file);
    }


}