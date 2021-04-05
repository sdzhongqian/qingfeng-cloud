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
 * @date 2021/3/8 000821:37
 */
public interface ${tablePd.bus_name?cap_first}Mapper extends BaseMapper<${tablePd.bus_name?cap_first}> {

    IPage<${tablePd.bus_name?cap_first}> findListPage(Page page, @Param("obj") ${tablePd.bus_name?cap_first} ${tablePd.bus_name});

    List<${tablePd.bus_name?cap_first}> findList(${tablePd.bus_name?cap_first} ${tablePd.bus_name});

}