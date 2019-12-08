package cc.mrbird.febs.generator.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.generator.entity.Column;
import cc.mrbird.febs.generator.entity.Table;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author MrBird
 */
public interface IGeneratorService {

    List<String> getDatabases(String databaseType);

    IPage<Table> getTables(String tableName, QueryRequest request, String databaseType, String schemaName);

    List<Column> getColumns(String databaseType, String schemaName, String tableName);
}
