package ${tablePd.pack_path}.${tablePd.mod_name}.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import ${tablePd.pack_path}.entity.${tablePd.mod_name}.${tablePd.bus_name?cap_first};

import java.util.List;

/**
 * @author qingfeng
 * @title: I${tablePd.bus_name?cap_first}Service
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:12
 */
public interface I${tablePd.bus_name?cap_first}Service extends IService<${tablePd.bus_name?cap_first}> {

    //查询数据分页列表
    IPage<${tablePd.bus_name?cap_first}> findListPage(${tablePd.bus_name?cap_first} ${tablePd.bus_name}, QueryRequest request);

    //查询数据列表
    List<${tablePd.bus_name?cap_first}> findList(${tablePd.bus_name?cap_first} ${tablePd.bus_name});

    //保存数据
    void save${tablePd.bus_name?cap_first}(${tablePd.bus_name?cap_first} ${tablePd.bus_name});

    //更新数据
    void update${tablePd.bus_name?cap_first}(${tablePd.bus_name?cap_first} ${tablePd.bus_name});

}