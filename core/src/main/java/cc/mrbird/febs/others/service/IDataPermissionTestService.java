package cc.mrbird.febs.others.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.others.entity.DataPermissionTest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * @author MrBird
 */
public interface IDataPermissionTestService extends IService<DataPermissionTest> {
    /**
     * 查询（分页）
     *
     * @param request            QueryRequest
     * @param dataPermissionTest dataPermissionTest
     * @return IPage<DataPermissionTest>
     */
    IPage<DataPermissionTest> findDataPermissionTests(QueryRequest request, DataPermissionTest dataPermissionTest);
}
