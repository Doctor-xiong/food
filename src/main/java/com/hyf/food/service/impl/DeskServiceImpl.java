package com.hyf.food.service.impl;

import com.hyf.food.entity.Desk;
import com.hyf.food.mapper.DeskMapper;
import com.hyf.food.service.IDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeskServiceImpl implements IDeskService {

    @Autowired
    private DeskMapper deskMapper;

    /***
     * 根据餐桌id和密码查找餐桌
     * @param desk
     * @return
     */
    @Override
    public Desk queryDeskByIdAndPassword(Desk desk) {
        return deskMapper.queryDeskByIdAndPassword(desk);
    }

    /***
     * 修改餐桌的状态
     * @return
     * 3/1 黄逸峰
     */
    @Override
    public int updateDeskPositionByDid(long d_position, long d_id) {
        return deskMapper.updateDeskPositionByDid(d_position, d_id);
    }


    /***
     * 根据餐桌id查找餐桌
     * @return
     */
    @Override
    public Desk queryDeskById(long d_id) {
        return deskMapper.queryDeskById(d_id);
    }

/***
 * 查询总共多少张桌子
 * @return
 */
    /***
     * 查询所有张桌子
     * @return
     */
    @Override
    public List<Desk> queryAllDesk() {
        return deskMapper.queryAllDesk();
    }

    /***
     * 根据id查询桌面
     * @return
     */
    @Override
    public Desk queryDeskById(Long d_id) {
        return deskMapper.queryDeskById(d_id);
    }

    /**
     * 根据桌名删除桌子
     *
     * @param name
     * @return
     */
    @Override
    public Desk deleteDeskByName(String name) {
        return deskMapper.deleteDeskByName(name);
    }

    @Override
    public Desk deleteDeskById(Long id) {
        return deskMapper.deleteDeskById(id);
    }

    @Override
    public int changeUserStatus(Long id) {
        return deskMapper.changeUserStatus(id);
    }

    /**
     * 添加桌子
     *
     * @param desk
     * @return
     */
    @Override
    public int addDesk(Desk desk) {
        return deskMapper.addDesk(desk);
    }

    /**
     * 根据桌名修改桌子状态
     *
     * @param name
     */
    @Override
    public int updateDeskPositionByName(String name) {
        return deskMapper.updateDeskPositionByName(name);
    }
}
