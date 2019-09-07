package ${basePackage}.${entityPackage};

<#if hasDate = true>
import java.util.Date;
</#if>
<#if hasBigDecimal = true>
import java.math.BigDecimal;
</#if>

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * ${tableComment} Entity
 *
 * @author ${author}
 * @date ${date}
 */
@Data
@TableName("${tableName}")
public class ${className} {

    <#if columns??>
        <#list columns as column>
    /**
     * ${column.remark}
     */
    <#if column.isKey = true>
    @TableId(value = "${column.name}", type = IdType.AUTO)
    <#else>
    @TableField("${column.name}")
    </#if>
    <#if (column.type = 'varchar' || column.type = 'text' || column.type = 'uniqueidentifier'
        || column.type = 'varchar2' || column.type = 'nvarchar' || column.type = 'VARCHAR2'
        || column.type = 'VARCHAR'|| column.type = 'CLOB' || column.type = 'char')>
    private String ${column.field?uncap_first};

    </#if>
    <#if column.type = 'timestamp' || column.type = 'date' || column.type = 'datetime'||column.type = 'TIMESTAMP' || column.type = 'DATE' || column.type = 'DATETIME'>
    private Date ${column.field?uncap_first};

    </#if>
    <#if column.type = 'int' || column.type = 'smallint'>
    private Integer ${column.field?uncap_first};

    </#if>
    <#if column.type = 'bigint'>
    private Long ${column.field?uncap_first};

    </#if>
    <#if column.type = 'double'>
    private Double ${column.field?uncap_first};

    </#if>
    <#if column.type = 'tinyint'>
    private Byte ${column.field?uncap_first};

    </#if>
    <#if column.type = 'decimal' || column.type = 'numeric'>
    private BigDecimal ${column.field?uncap_first};
    </#if>
        </#list>
    </#if>
}
