package com.hyf.food.action;

import com.hyf.food.entity.Desk;
import com.hyf.food.service.IDeskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.security.krb5.internal.crypto.Des;

@Slf4j
@Controller
public class UserOption {

    @Autowired
    private IDeskService deskService;

    /**
     * 返回用户注册的主页地址
     * @return
     */
    @RequestMapping("user.action")
    public String returnUserAddIndex(){
        return "redirect:client/userOption.jsp";
    }

    /**
     * 提供用户注册接口
     * @param model
     * @param desk
     * @return
     */
    @RequestMapping(value = "userAdd.action",method = RequestMethod.GET)
    public String addUser(Model model, Desk desk){
        log.info("into addUser desk={}",desk);
        if(ObjectUtils.isEmpty(desk)){
            model.addAttribute("error","输入信息不对");
            log.error("用户输入的信息不对");
            return "client/UserOption.jsp";
        }
        desk.setD_type("0");
        desk.setD_position(0L);
        desk.setD_type("0");
        desk.setD_place("0");
        deskService.addDesk(desk);
        return "clientLogin.jsp";
    }

    /**
     * 根据用户的name删除用户
     * @param model
     * @return
     */
    @RequestMapping(value = "delUser.action",method = RequestMethod.GET)
    public String delUser(Model model,@RequestParam("d_id") Long id){
        log.info("into delete user: name={}",id);
        if(StringUtils.isEmpty(id) || id < 0){
            model.addAttribute("error","输入的用户name不对,"+id);
            return "my404.jsp";
        }
        deskService.deleteDeskById(id);
        return "clientLogin.jsp";
    }

    /**
     * 查询用户信息
     */
    @RequestMapping("userInfo.action")
    public String getUserInfo(@RequestParam("d_id") Long id,Model model){
        if(ObjectUtils.isEmpty(id) || id < 0){
            model.addAttribute("error","获取用户信息失败");
            return "my404.jsp";
        }
        Desk desk = deskService.queryDeskById(id);
        if (ObjectUtils.isEmpty(desk)){
            model.addAttribute("error","查询用户信息失败");
            return "my404.jsp";
        }
        model.addAttribute("desk",desk);
        return "/client/showUser.jsp";
    }
}
