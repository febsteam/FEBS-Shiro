package cc.mrbird.febs.others.mapper;

import cc.mrbird.febs.common.annotation.DataPermission;
import cc.mrbird.febs.others.entity.DataPermissionTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author MrBird
 */
@DataPermission(methods = {"selectPage"})
public interface DataPermissionTestMapper extends BaseMapper<DataPermissionTest> {

}
