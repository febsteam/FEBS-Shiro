<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.${mapperPackage}.${className}Mapper">

    <resultMap id="${className?uncap_first}" type="${basePackage}.${entityPackage}.${className}">
        <#if columns??>
        <#list columns as column>
            <#if column.isKey = true>
        <id column="${column.name}" jdbcType="${column.type}" property="${column.field?uncap_first}"/>
            <#else>
        <result column="${column.name}" jdbcType="${column.type}" property="${column.field?uncap_first}"/>
            </#if>
        </#list>
        </#if>
    </resultMap>
</mapper>
