package com.hyf.food.service;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import com.hyf.food.entity.Desk;

public interface IDeskService {
    /***
     * 根据餐桌id和密码查找餐桌
     * @param desk
     * @return
     */
    Desk queryDeskByIdAndPassword(Desk desk);

    /***
     * 修改餐桌的状态
     * @return
     * 3/1 黄逸峰
     */
    int updateDeskPositionByDid(long d_position, long d_id);

    /***
     * 根据餐桌id查找餐桌
     * @return
     */
    Desk queryDeskById(long d_id);

    /***
     * 查询所有张桌子
     * @return
     */
    List<Desk> queryAllDesk();

    /***
     * 根据id查询桌面
     * @return
     */
    Desk queryDeskById(Long d_id);

    /**
     * 根据桌名删除桌子
     *
     * @param name
     * @return
     */
    int deleteDeskByName(String name);

    /**
     * 根据用户的ID删除用户
     */
    int deleteDeskById(Long id);

    /**
     * 更改用户的状态
     */
    int changeUserStatus(Long id);
    /**
     * 添加桌子
     *
     * @param desk
     * @return
     */
    int addDesk(Desk desk);

    /**
     * 根据桌名修改桌子状态
     *
     * @param name
     */
    int updateDeskPositionByName(String name);

}
