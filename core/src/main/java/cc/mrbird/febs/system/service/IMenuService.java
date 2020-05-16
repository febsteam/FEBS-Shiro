package cc.mrbird.febs.system.service;

import cc.mrbird.febs.common.entity.MenuTree;
import cc.mrbird.febs.system.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author MrBird
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 查找用户权限集
     *
     * @param username 用户名
     * @return 用户权限集合
     */
    List<Menu> findUserPermissions(String username);

    /**
     * 查找用户菜单集合
     *
     * @param username 用户名
     * @return 用户菜单集合
     */
    MenuTree<Menu> findUserMenus(String username);

    /**
     * 查找所有的菜单/按钮 （树形结构）
     *
     * @param menu menu
     * @return MenuTree<Menu>
     */
    MenuTree<Menu> findMenus(Menu menu);

    /**
     * 查找所有的菜单/按钮
     *
     * @param menu menu
     * @return MenuTree<Menu>
     */
    List<Menu> findMenuList(Menu menu);

    /**
     * 新增菜单（按钮）
     *
     * @param menu 菜单（按钮）对象
     */
    void createMenu(Menu menu);

    /**
     * 修改菜单（按钮）
     *
     * @param menu 菜单（按钮）对象
     */
    void updateMenu(Menu menu);

    /**
     * 删除菜单（按钮）
     *
     * @param menuIds 菜单（按钮）id
     */
    void deleteMenus(String menuIds);
}
