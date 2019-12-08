package ${basePackage}.${servicePackage};

import ${basePackage}.${entityPackage}.${className};

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * ${tableComment} Service接口
 *
 * @author ${author}
 * @date ${date}
 */
public interface I${className}Service extends IService<${className}> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param ${className?uncap_first} ${className?uncap_first}
     * @return IPage<${className}>
     */
    IPage<${className}> find${className}s(QueryRequest request, ${className} ${className?uncap_first});

    /**
     * 查询（所有）
     *
     * @param ${className?uncap_first} ${className?uncap_first}
     * @return List<${className}>
     */
    List<${className}> find${className}s(${className} ${className?uncap_first});

    /**
     * 新增
     *
     * @param ${className?uncap_first} ${className?uncap_first}
     */
    void create${className}(${className} ${className?uncap_first});

    /**
     * 修改
     *
     * @param ${className?uncap_first} ${className?uncap_first}
     */
    void update${className}(${className} ${className?uncap_first});

    /**
     * 删除
     *
     * @param ${className?uncap_first} ${className?uncap_first}
     */
    void delete${className}(${className} ${className?uncap_first});
}
