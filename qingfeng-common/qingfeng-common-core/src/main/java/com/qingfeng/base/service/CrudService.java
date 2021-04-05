package com.qingfeng.base.service;

import com.github.pagehelper.PageHelper;
import com.qingfeng.base.dao.CrudDao;
import com.qingfeng.utils.Page;
import com.qingfeng.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName CrudService
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:27
 */
public class CrudService<Dao extends CrudDao<T>,T> {
    @Autowired
    protected Dao curdDao;

    /**
     * @Description: findListPage 分页信息查询
     * @Param: [page]
     * @return: java.util.List<T>
     * @Author: anxingtao
     * @Date: 2020-9-22 21:03
     */
    public List<T> findListPage(Page page) {
        PageHelper.startPage(page.getIndex(), page.getShowCount());
        return curdDao.findListPage(page);
    }

    /**
     * @Description: findListSize 分页总条数
     * @Param: [page]
     * @return: java.lang.Integer
     * @Author: anxingtao
     * @Date: 2020-9-22 21:03
     */
    public Integer findListSize(Page page){
        return curdDao.findListSize(page);
    }

    /**
     * @Description: findList 列表信息查询
     * @Param: [pd]
     * @return: java.util.List<T>
     * @Author: anxingtao
     * @Date: 2020-9-22 21:03
     */
    public List<T> findList(PageData pd){
        List<T> list = curdDao.findList(pd);
        return list;
    }

    /**
     * @Description: findInfo 详情信息查询
     * @Param: [pd]
     * @return: T
     * @Author: anxingtao
     * @Date: 2020-9-22 21:03
     */
    public T findInfo(PageData pd){
        return curdDao.findInfo(pd);
    }

    /**
     * @Description: save 保存方法
     * @Param: [t]
     * @return: int
     * @Author: anxingtao
     * @Date: 2020-9-22 21:03
     */
    public int save(T t){
        return curdDao.save(t);
    }


    /**
     * @Description: update 更新方法
     * @Param: [t]
     * @return: int
     * @Author: anxingtao
     * @Date: 2020-9-22 21:04
     */
    public int update(T t){
        return curdDao.update(t);
    }

    /**
     * @Description: delForParam 根据对象删除
     * @Param: [pd]
     * @return: void
     * @Author: anxingtao
     * @Date: 2020-9-24 18:12
     */
    public void delForParam(PageData pd){
        curdDao.delForParam(pd);
    }

    /**
     * @Description: del 根据ids集合删除
     * @Param: [ids]
     * @return: void
     * @Author: anxingtao
     * @Date: 2020-9-22 21:04
     */
    @Transactional
    public void del(String[] ids){
        curdDao.del(ids);
    }

}
