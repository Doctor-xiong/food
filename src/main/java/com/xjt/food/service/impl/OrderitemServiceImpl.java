package com.xjt.food.service.impl;

import com.xjt.food.entity.Menu;
import com.xjt.food.entity.Orderitem;
import com.xjt.food.mapper.MenuMapper;
import com.xjt.food.mapper.OrderitemMapper;
import com.xjt.food.mapper.OrderitemsMapper;
import com.xjt.food.service.IOrderitemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderitemServiceImpl implements IOrderitemService {

    @Autowired
    private OrderitemMapper orderitemMapper;
    @Autowired
    private OrderitemsMapper orderitemsMapper;
    @Autowired
    private MenuMapper menuMapper;

    /***
     * 根据总订单id查找子订单信息
     * @param os_id
     * @return
     */
    @Override
    public List<Orderitem> queryItemByOsid(Long os_id) {
        return orderitemMapper.queryItemByOsid(os_id);
    }

    /***
     * 插入一个子订单同时修改总订单总价
     * @param oi
     * @return
     */
    @Override
    public int addOrderitem(Orderitem oi) {
        //插入一个子订单
        log.info("总订单的价格正在修改----------------------------------------------");
        int i = orderitemMapper.addOrderitem(oi);
        if (i == 1) {
            //获取菜品单价
            Menu menu = menuMapper.queryMenuById(oi.getM_id());
            //再修改总订单的总价
            int j = orderitemsMapper.updateOrderitemsPriceByOsid(menu.getM_price(), oi.getOs_id());
            log.info("总订单的价格已修改---------------------------------------");
            return j;
        } else {
            return i;
        }
    }

    /***
     * 每次减少一份，并修改子订单和总订单总价
     * @param oi
     * @return
     */
    @Override
    public int updateOrderitemDec(Orderitem oi) {
        int i = orderitemMapper.updateOrderitemDec(oi);
        //同时修改总订单总价
        if (i == 1) {
            //获取菜品单价
            Menu menu = menuMapper.queryMenuById(oi.getM_id());
            int j = orderitemsMapper.updateOrderitemsPriceByOsid(-menu.getM_price(), oi.getOs_id());
            log.info("总订单的价格已修改---------------------------------------");
            return j;
        }
        return i;
    }

    /***
     * 每次增加一份，并修改子订单和总订单总价
     * @param oi
     * @return
     */
    @Override
    public int updateOrderitemAdd(Orderitem oi) {
        int i = orderitemMapper.updateOrderitemAdd(oi.getOs_id(),oi.getM_id());
        //同时修改总订单总价
        if (i == 1) {
            //获取菜品单价
            Menu menu = menuMapper.queryMenuById(oi.getM_id());
            int j = orderitemsMapper.updateOrderitemsPriceByOsid(menu.getM_price(), oi.getOs_id());
            log.info("总订单的价格已修改--++++++++++++++++++++++++++++++++++++++++------" + orderitemsMapper.queryOrderitemsByOsId(oi.getOs_id()).getOs_allprice());
            return j;
        }
        return i;
    }

    /***
     * 根据总订单id和菜品id获取子订单信息
     * @param m_id
     * @param os_id
     * @return
     */
    @Override
    public Orderitem queryOrderitemByMidAndOsid(Long m_id, Long os_id) {
        return orderitemMapper.queryOrderitemByMidAndOsid(m_id, os_id);
    }

    /***
     * 修改子订单的状态
     * @return
     */
    @Override
    public int updateOrderitemPositionByOiid(long oi_position, long os_id) {
        return orderitemMapper.updateOrderitemPositionByOiid(oi_position, os_id);
    }

    /***
     * 批量修改子订单的总订单id
     * @return
     */
    @Override
    public int updateOrderitemOsidByOiid(long os_id, long oi_id) {
        return orderitemMapper.updateOrderitemOsidByOiid(os_id, oi_id);
    }

    /***
     * 根据oiid彻底删除该子订单
     * @param oi_id
     * @return
     */
    @Override
    public int deleteOrderitemByOiid(long oi_id) {
        return orderitemMapper.deleteOrderitemByOiid(oi_id);
    }

    /***
     * 根据总订单ID计算有多少条子订单数据
     * @return
     */
    @Override
    public int count(Long os_id) {
        return orderitemMapper.count(os_id);
    }

    /***
     * 根据总订单id查找子订单信息(并获取菜品信息)分页
     * @param os_id
     * @return
     */
    @Override
    public List<Orderitem> queryItemByOsidAndLimit(Long os_id, int before, int after) {
        return orderitemMapper.queryItemByOsidAndLimit(os_id, before, after);
    }

}
