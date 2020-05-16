package cc.mrbird.febs.common.interceptor;

import cc.mrbird.febs.common.annotation.DataPermission;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.io.StringReader;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author MrBird
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DataPermissionInterceptor extends AbstractSqlParserHandler implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        // 数据权限只针对查询语句
        if (SqlCommandType.SELECT == mappedStatement.getSqlCommandType()) {
            DataPermission dataPermission = getDataPermission(mappedStatement);
            if (shouldFilter(mappedStatement, dataPermission)) {
                String id = mappedStatement.getId();
                log.info("\n 数据权限过滤 Method -> {}", id);
                String originSql = boundSql.getSql();
                String dataPermissionSql = dataPermissionSql(originSql, dataPermission);
                metaObject.setValue("delegate.boundSql.sql", dataPermissionSql);
                log.info("\n 原始SQL -> {} \n 数据权限过滤SQL -> {}", originSql, dataPermissionSql);
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private String dataPermissionSql(String originSql, DataPermission dataPermission) {
        try {
            if (StringUtils.isBlank(dataPermission.field())) {
                return originSql;
            }
            User currentUser = FebsUtil.getCurrentUser();
            if (currentUser == null) {
                return originSql;
            }
            CCJSqlParserManager parserManager = new CCJSqlParserManager();
            Select select = (Select) parserManager.parse(new StringReader(originSql));
            PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
            Table fromItem = (Table) plainSelect.getFromItem();

            String selectTableName = fromItem.getAlias() == null ? fromItem.getName() : fromItem.getAlias().getName();
            String dataPermissionSql = String.format("%s.%s in (%s)", selectTableName, dataPermission.field(), StringUtils.defaultIfBlank(currentUser.getDeptIds(), "'WITHOUT PERMISSIONS'"));

            if (plainSelect.getWhere() == null) {
                plainSelect.setWhere(CCJSqlParserUtil.parseCondExpression(dataPermissionSql));
            } else {
                plainSelect.setWhere(new AndExpression(plainSelect.getWhere(), CCJSqlParserUtil.parseCondExpression(dataPermissionSql)));
            }
            return select.toString();
        } catch (Exception e) {
            log.warn("get data permission sql fail: {}", e.getMessage());
            return originSql;
        }
    }

    private DataPermission getDataPermission(MappedStatement mappedStatement) {
        String mappedStatementId = mappedStatement.getId();
        DataPermission dataPermission = null;
        try {
            String className = mappedStatementId.substring(0, mappedStatementId.lastIndexOf("."));
            final Class<?> clazz = Class.forName(className);
            if (clazz.isAnnotationPresent(DataPermission.class)) {
                dataPermission = clazz.getAnnotation(DataPermission.class);
            }
        } catch (Exception ignore) {
        }
        return dataPermission;
    }

    private Boolean shouldFilter(MappedStatement mappedStatement, DataPermission dataPermission) {
        if (dataPermission != null) {
            String methodName = StringUtils.substringAfterLast(mappedStatement.getId(), ".");
            String methodPrefix = dataPermission.methodPrefix();
            if (StringUtils.isNotBlank(methodPrefix) && StringUtils.startsWith(methodName, methodPrefix)) {
                return Boolean.TRUE;
            }
            String[] methods = dataPermission.methods();
            for (String method : methods) {
                if (StringUtils.equals(method, methodName)) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
}
