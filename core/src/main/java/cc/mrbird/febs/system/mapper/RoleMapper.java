package cc.mrbird.febs.system.mapper;

import cc.mrbird.febs.system.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MrBird
 */
public interface RoleMapper extends BaseMapper<Role> {
    Long countRole(@Param("role") Role role);

    /**
     * 通过用户名查找用户角色
     *
     * @param username 用户名
     * @return 用户角色集合
     */
    List<Role> findUserRole(String username);

    /**
     * 查找角色详情
     *
     * @param page 分页
     * @param role 角色
     * @return IPage<User>
     */
    <T> IPage<Role> findRolePage(Page<T> page, @Param("role") Role role);
}
