package com.qingfeng.gencode.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.qingfeng.base.controller.BaseController;
import com.qingfeng.entity.MyResponse;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.gencode.Mytree;
import com.qingfeng.entity.system.UserOrganize;
import com.qingfeng.exception.MyException;
import com.qingfeng.gencode.service.IMytreeService;
import com.qingfeng.system.service.IUserOrganizeService;
import com.qingfeng.system.service.IUserService;
import com.qingfeng.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.*;

/**
* @title: MytreeController
* @projectName: MytreeController
* @description: TODO
* @author: qingfeng
* @date: 2021/2/21 0021 22:11
*/
@Slf4j
@Validated
@RestController
@RequestMapping("mytree")
public class MytreeController extends BaseController {

    @Autowired
    private IMytreeService mytreeService;
    @Autowired
    private IUserOrganizeService userOrganizeService;
    @Autowired
    private IUserService userService;

    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:32
     */
    @GetMapping("/findListPage")
    @PreAuthorize("hasAnyAuthority('mytree:info')")
    public MyResponse findListPage(QueryRequest queryRequest, Mytree mytree) {
        String userParams = SecurityContextHolder.getContext().getAuthentication().getName();
        //处理数据权限
        String user_id = userParams.split(":")[1];
        UserOrganize uoParam = new UserOrganize();
        uoParam.setUser_id(user_id);
        UserOrganize userOrganize = userOrganizeService.findUserOrganizeInfo(uoParam);
        List<String> auth_organize_ids = new ArrayList<String>();
        if(Verify.verifyIsNotNull(userOrganize)){
            if(Verify.verifyIsNotNull(userOrganize.getAuthOrgIds())){
                auth_organize_ids = Arrays.asList(userOrganize.getAuthOrgIds().split(","));
            }
        }
        mytree.setAuth_user(user_id);
        mytree.setAuth_organize_ids(auth_organize_ids);
        Map<String, Object> dataTable = MyUtil.getDataTable(mytreeService.findListPage(mytree, queryRequest));
        return new MyResponse().data(dataTable);
    }

    /**
     * @title findList
     * @description 查询数据列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:32
     */
    @GetMapping("/findList")
    public MyResponse findList(QueryRequest queryRequest, Mytree mytree) {
        String userParams = SecurityContextHolder.getContext().getAuthentication().getName();
        //处理数据权限
        String user_id = userParams.split(":")[1];
        UserOrganize uoParam = new UserOrganize();
        uoParam.setUser_id(user_id);
        UserOrganize userOrganize = userOrganizeService.findUserOrganizeInfo(uoParam);
        List<String> auth_organize_ids = new ArrayList<String>();
        if(Verify.verifyIsNotNull(userOrganize)){
            if(Verify.verifyIsNotNull(userOrganize.getAuthOrgIds())){
                auth_organize_ids = Arrays.asList(userOrganize.getAuthOrgIds().split(","));
            }
        }
        mytree.setAuth_user(user_id);
        mytree.setAuth_organize_ids(auth_organize_ids);
        List<Mytree> mytreeList = mytreeService.findList(mytree);
        return new MyResponse().data(mytreeList);
    }

    /**
     * @title save
     * @description 保存方法
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:32
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('mytree:add')")
    public void save(@Valid @RequestBody Mytree mytree) throws MyException {
        try {
            this.mytreeService.saveMytree(mytree);
        } catch (Exception e) {
            String message = "新增信息失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title update
     * @description 更新方法
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:32
     */
    @PutMapping
    @PreAuthorize("hasAnyAuthority('mytree:edit')")
    public void update(@Valid @RequestBody Mytree mytree) throws MyException {
        try {
            this.mytreeService.updateMytree(mytree);
        } catch (Exception e) {
            String message = "修改信息失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title delete
     * @description 删除方法
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:32
     */
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAnyAuthority('mytree:del')")
    public void delete(@NotBlank(message = "{required}") @PathVariable String ids) throws MyException {
        try {
            String[] del_ids = ids.split(StringPool.COMMA);
            this.mytreeService.removeByIds(Arrays.asList(del_ids));
        } catch (Exception e) {
            String message = "删除失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title updateStatus
     * @description 更新状态
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:33
     */
    @PostMapping("/updateStatus")
    @PreAuthorize("hasAnyAuthority('mytree:status')")
    public void updateStatus(@Valid @RequestBody Mytree mytree) throws MyException {
        try {
            this.mytreeService.updateById(mytree);
        } catch (Exception e) {
            String message = "状态修改失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

}
