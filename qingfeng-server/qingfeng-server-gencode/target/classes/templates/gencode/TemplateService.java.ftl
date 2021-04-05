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

    IPage<${tablePd.bus_name?cap_first}> findListPage(${tablePd.bus_name?cap_first} ${tablePd.bus_name}, QueryRequest request);

    List<${tablePd.bus_name?cap_first}> findList(${tablePd.bus_name?cap_first} ${tablePd.bus_name});

    void save${tablePd.bus_name?cap_first}(${tablePd.bus_name?cap_first} ${tablePd.bus_name});

    void update${tablePd.bus_name?cap_first}(${tablePd.bus_name?cap_first} ${tablePd.bus_name});

}