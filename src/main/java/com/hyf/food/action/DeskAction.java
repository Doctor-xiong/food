package com.hyf.food.action;

import com.hyf.food.entity.Desk;
import com.hyf.food.entity.Orderitem;
import com.hyf.food.entity.Orderitems;
import com.hyf.food.service.IDeskService;
import com.hyf.food.service.IOrderitemService;
import com.hyf.food.service.IOrderitemsService;
import com.hyf.food.utils.QUcodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;


@Slf4j
@Controller
public class DeskAction {

    @Autowired
    private IDeskService deskServiceImpl;

    @Autowired
    private IOrderitemsService orderitemsService;

    @Autowired
    private IOrderitemService orderitemService;

    /***
     * 餐桌登录
     * @param model
     * @param desk
     * @return
     */
    @RequestMapping("clientLogin.action")
    public String LoginDesk(Model model, Desk desk, HttpSession session) {
        log.info("餐桌登录---------" + desk.getD_password());
        //登录的时候 清除掉为again的session
        session.removeAttribute("again");
        log.info("desk={}", desk);
        model.addAttribute("name",desk.getD_name());
        model.addAttribute("d_id", desk.getD_id());
        Desk d = deskServiceImpl.queryDeskByIdAndPassword(desk);
        //查询是否存在未付款的订单
        List<Orderitems> osList = orderitemsService.queryOrderitemsByPosition(0, desk.getD_id());
        //查询是否存在已付款的订单
        List<Orderitems> osList1 = orderitemsService.queryOrderitemsByPosition(1, desk.getD_id());
        if (d == null) {
            //登录失败
            model.addAttribute("msg", "抱歉，登录失败，请检查ID和password！");
            return "clientLogin.jsp";
        } else {
            if (d.getD_position() == 1) {
                //当餐桌显示有客时 但实际上没有客人 我们先查询是否有状态为0的总订单 且 总订单中要有子订单
                if (osList.size() > 0) {
                    //查询总订单中是否有子订单的存在
                    List<Orderitem> oiList = orderitemService.queryItemByOsid(osList.get(0).getOs_id());//未付款总订单
                    if (oiList != null) {
                        //存在子订单
                        //有，那么进入到选择页面
                        session.setAttribute("desk", d);
                        log.info("当餐桌显示有客时 但实际上没有客人 我们先查询是否有状态为0的总订单--------有");
                        return "redirect:client/isMydesk1.jsp";
                    } else {
                        //不存在状态为0的子订单 那么就进入到空闲点餐模式 同时删除这个总订单
                        orderitemsService.deleteOrderitemsByOsid(osList.get(0).getOs_id());
                        log.info("不存在状态为0的子订单 那么就进入到空闲点餐模式 同时删除这个总订单");
                        session.setAttribute("desk", d);
                        return "redirect:queryRecommendMenu.do";
                    }
                } else if (osList1.size() > 0) {
                    //查询总订单中是否有子订单的存在
                    List<Orderitem> oiList1 = orderitemService.queryItemByOsid(osList1.get(0).getOs_id());//已付款总订单
                    if (oiList1.size() > 0) {
                        //存在子订单
                        //没有状态为0的总订 但有状态为1的  我们就进入到加餐页面
                        //当餐桌有客人时 进入是否加餐页面,如果确认进入加餐，那么也能查看到正在进行的的订单信息
                        session.setAttribute("desk", d);
                        session.setAttribute("again", "again");
                        return "redirect:client/isMydesk.jsp";
                    } else {
                        //不存在状态为1的子订单 那么就进入到空闲点餐模式 同时删除这个总订单
                        orderitemsService.deleteOrderitemsByOsid(osList1.get(0).getOs_id());
                        log.info("不存在状态为1的子订单 那么就进入到空闲点餐模式 同时删除这个总订单");
                        session.setAttribute("desk", d);
                        return "redirect:queryRecommendMenu.do";
                    }
                } else {
                    //没有状态为0或1的订单 但餐桌是有人状态  那么直接进入到空闲点餐模式
                    //或者有总订单为0 或1 但没有任何子订单信息 那么直接进入到空闲点餐模式
                    log.info("没有状态为0或1的订单 但餐桌是有人状态  那么直接进入到空闲点餐模式//或者有总订单为0 或1 但没有任何子订单信息 那么直接进入到空闲点餐模式");
                    session.setAttribute("desk", d);
                    return "redirect:.queryRecommendMenudo";
                }


            } else if (d.getD_position() == 0) {
                //当无客人时  正常点餐,并修改餐桌状态为1 有人
                session.setAttribute("desk", d);
                deskServiceImpl.updateDeskPositionByDid(1, desk.getD_id());
                log.info("登录成功，正常点餐");
                return "queryRecommendMenu.do";
            } else {
                //登录失败
                model.addAttribute("msg", "抱歉，登录失败，请检查ID和password！");
                return "clientLogin.jsp";
            }
        }

    }

    /***
     * 我的餐桌继续加餐
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("ismydesk.do")
    public String IsMyDesk(HttpSession session, Model model) {
        Desk desk = (Desk) session.getAttribute("desk");

        //查找这个餐桌之前的一个已付款（正在进行的 状态为1）的总订单和子订单
        Orderitems os = orderitemsService.queryOrderItemsByDidAndDateDescLimit1(desk.getD_id(), 1);
        //根据总订单id查询所有子订单信息
        if (os != null) {
            log.info("查找这个餐桌之前的一个已付款总订单---------" + os.getOiList().get(0).getOs_id());
            model.addAttribute("os1", os);
            session.setAttribute("os_pay", os);
            session.setAttribute("again", "again");
            log.info("我的餐桌继续加餐-------------" + os.getOiList().get(0).getOs_id());
            return "client/againFood.jsp";
        } else {
            log.info("查询到了多个正在进行的总订单。请联系前台服务员");
            model.addAttribute("msg", "查询到了多个正在进行的总订单。请联系前台服务员");
            return "error.jsp";
        }
    }

    /***
     * 结束用餐
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("leaveDesk.do")
    public String leaveDesk(HttpSession session, Model model) {
        Desk desk = (Desk) session.getAttribute("desk");

        //获取总订单信息 并将其状态修改为2 历史订单
        Orderitems os = orderitemsService.queryOrderItemsByDidAndDateDescLimit1(desk.getD_id(), 1);
        int i = orderitemsService.updateOrderitemsPositionByOsid(2, os.getOiList().get(0).getOs_id());
        //如果总订单修改成功
        if (i == 1) {
            //获取所有子订单的信息，并将其状态修改为2 历史订单
            List<Orderitem> oiList = os.getOiList();
            int k = 0;
            for (Orderitem oi : oiList) {
                k = orderitemService.updateOrderitemPositionByOiid(2, oi.getOi_id()) + k;
            }
            log.info(k + "-------------结束用餐--------------" + oiList.size());
            if (k == oiList.size()) {
                log.info("子订单状态修改为2 成功---------");
                //将餐桌状态修改为0 空闲
                int j = deskServiceImpl.updateDeskPositionByDid(0, desk.getD_id());
                if (j == 1) {
                    log.info("餐桌状态修改成功++++++++++++++====");
                    //当所有删除订单信息删除成功后，再将desk的session删除
                    session.removeAttribute("desk");
                    //session.removeAttribute("again");
                    return "redirect:leaveDesk.jsp";
                } else {
                    log.info("餐桌状态修改失败---------------------");
                    return "error.jsp";
                }
            } else {
                log.info("子订单状态修改为2  失败——————————————————————————");
                return "error.jsp";
            }
        } else {
            log.info("修改总订单失败");
            return "error.jsp";
        }
    }

    /***
     * 清除未付款的菜单信息
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("cleanNoPayMenu.do")
    public String cleanNoPayMenu(HttpSession session, Model model) {
        Desk desk = (Desk) session.getAttribute("desk");

        //Orderitems os = orderitemsService.queryOrderItemsByDidAndDateDescLimit1(desk.getD_id(), 0);
        Orderitems os = orderitemsService.queryOrderitemsByPosition(0, desk.getD_id()).get(0);
        List<Orderitem> oiList = orderitemService.queryItemByOsid(os.getOs_id());
        if (os != null) {
            log.info("查询到需要清空的付尾款的菜单");
            //修改所有未付款的子订单信息
            int k = 0;
            for (Orderitem oi : oiList) {
                k = orderitemService.updateOrderitemPositionByOiid(3, oi.getOi_id()) + k;
            }
            if (k == oiList.size()) {
                log.info("已将所有子订单信息修改为状态 3------------");
                //继续修改总订单状态
                int i = orderitemsService.updateOrderitemsPositionByOsid(3, os.getOs_id());
                if (i == 1) {
                    log.info("总订单状态修改成功");
                    //返回页面
                    return "redirect:queryRecommendMenu.do";
                } else {
                    log.info("总订单状态修改失败");
                }
            } else {
                log.info("子订单信息修改失败---------------");
            }
        }
        log.info("为查询到总订单信息------清除未付款");
        return "error.jsp";
    }

    /***
     * 生成二维码
     * 只需要传入餐桌id
     * 然后返回二维码地址
     * @return
     * @throws Exception
     */
    @RequestMapping("createQRcode.action")
    public @ResponseBody
    String createQRcode(String d_id, HttpServletRequest request) throws Exception {
        long did = Long.parseLong(d_id);
        Desk desk = deskServiceImpl.queryDeskById(did);

        String text = "http://192.168.43.112:8082/food/clientLogin.action?d_id=" + desk.getD_id() + "&d_password=" + desk.getD_password(); //一号桌登录
        log.info("随机码： " + text);
        int width = 500; // 二维码图片的宽
        int height = 500; // 二维码图片的高
        String format = "png"; // 二维码图片的格式

        //创建生成路径及文件名
        String newName = desk.getD_id() + "password" + desk.getD_password();
        //文件存储的相对路径
        String path = "/upload/code";
        //获取需要存储的路径的绝对路径
        String realPath = request.getServletContext().getRealPath(path);
        //判断文件夹是否存在，如果不存在则创建目录
        File dayFile = new File(realPath);
        if (!dayFile.exists()) {
            dayFile.mkdirs();
        }
        //上传文件到服务器
        String newFileName = realPath + "/" + newName + ".png";
        String newFileNameRes = path + "/" + newName + ".png";
        // 生成二维码图片，并返回图片路径
        String pathName = QUcodeUtil.generateQRCode(text, width, height, format, newFileName);
        log.info("生成二维码的图片路径： " + pathName);

        String content = QUcodeUtil.parseQRCode(pathName);
        log.info(newFileName + "解析出二维码的图片的内容为： " + content);
        return newFileNameRes;
    }

    /**
     * 查询所有桌面
     *
     * @param model
     * @return
     */
    @RequestMapping("countDesk.action")
    public String countDesk(Model model) {
        List<Desk> list = deskServiceImpl.queryAllDesk();
        model.addAttribute("list", list);
        return "service/index.jsp";
    }

    /**
     * 根据id查询桌面
     *
     * @param model
     * @return
     */
    @RequestMapping("querytDeskById.action")
    public String querytDeskById(Model model, Long d_id) {
        Desk desk = deskServiceImpl.queryDeskById(d_id);
        model.addAttribute("desk", desk);
        if (desk.getD_position() == (long) 1) {
            //桌面状态为1则将信息找出来
            Orderitems order = orderitemsService.queryOrderItemsByDidAndDateDescLimit1(d_id, 1);
            log.info("----------------------------------");
            if (order != null) {
                //如果能找到这个桌子的订单则将信息传过去
                model.addAttribute("size", order.getOiList().size());
                model.addAttribute("order", order);
                Desk d = deskServiceImpl.queryDeskById(order.getD_id());
                String d_name = d.getD_name();
                model.addAttribute("d_name", d_name);
                return "service/deskManager.jsp";
            }
        }//桌面为空跳至桌面管理
        return "service/deskManager0.jsp";

    }

    /**
     * 结账销台把桌台、总订单、子订单状态修改
     */
    @RequestMapping("accountOrder.action")
    public void accountOrder(String os_id) {
        log.info("************************" + os_id);
        long id = Long.parseLong(os_id);
        log.info("---+++++++++++++++++++++++++++++++------" + id);
        Orderitems order = orderitemsService.queryOrderitemsByOsId(id);
        log.info("我收到修改请求了" + order);
        deskServiceImpl.updateDeskPositionByDid(0, order.getD_id());
        orderitemsService.updateOrderitemsPositionByOsid(2, order.getOs_id());
        List<Orderitem> list = order.getOiList();
        for (Orderitem orderitem : list) {
            orderitemService.updateOrderitemPositionByOiid(2, orderitem.getOi_id());
        }
        log.info("wo修改成功了");
    }

    /**
     * 根据桌号删除桌子
     *
     * @param name
     */
    @RequestMapping("deleteDesk.action")
    public void deleteDesk(String name) {
        //根据桌号删除桌子
        deskServiceImpl.deleteDeskByName(name);
    }

    /**
     * 添加桌子
     */
    @RequestMapping("addDesk.action")
    public void addDesk(Desk desk) {
        //添加桌子
        deskServiceImpl.addDesk(desk);
    }
    ////////////////刘超 3.15新增///////////////////////////

    /**
     * 根据桌名修改桌子状态
     */
    @RequestMapping("updateDeskPositionByName.action")
    public void updateDeskPositionByName(long d_id) {
        log.info("根据桌名修改桌子状态" + d_id);
        //修改状态为0
        int i = orderitemsService.deleteOrderitemsByDidAndPosition(d_id);
        int j = orderitemService.deleteOrderitemByOiid(d_id);
        //修改状态为0
        int k = deskServiceImpl.updateDeskPositionByDid(0, d_id);
    }
}
