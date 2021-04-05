package ${tablePd.pack_path}.${tablePd.mod_name}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${tablePd.pack_path}.entity.${tablePd.mod_name}.${tablePd.bus_name?cap_first};
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qingfeng
 * @title: ${tablePd.bus_name?cap_first}Mapper
 * @projectName qingfeng-cloud
 * @description: TODO
 * @createTime 2021/4/3 0003 20:30
 */
public interface ${tablePd.bus_name?cap_first}Mapper extends BaseMapper<${tablePd.bus_name?cap_first}> {

    //查询数据分页列表
    IPage<${tablePd.bus_name?cap_first}> findListPage(Page page, @Param("obj") ${tablePd.bus_name?cap_first} ${tablePd.bus_name});

    //查询数据列表
    List<${tablePd.bus_name?cap_first}> findList(${tablePd.bus_name?cap_first} ${tablePd.bus_name});

}