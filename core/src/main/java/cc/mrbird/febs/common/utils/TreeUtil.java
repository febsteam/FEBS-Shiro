package cc.mrbird.febs.common.utils;


import cc.mrbird.febs.common.entity.DeptTree;
import cc.mrbird.febs.common.entity.MenuTree;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MrBird
 */
public class TreeUtil {

    private static final String TOP_NODE_ID = "0";

    public static <T> MenuTree<T> buildMenuTree(List<MenuTree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<MenuTree<T>> topNodes = new ArrayList<>();
        nodes.forEach(children -> {
            String pid = children.getParentId();
            if (pid == null || TOP_NODE_ID.equals(pid)) {
                topNodes.add(children);
                return;
            }
            for (MenuTree<T> parent : nodes) {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChilds().add(children);
                    children.setHasParent(true);
                    parent.setHasChild(true);
                    return;
                }
            }
        });

        MenuTree<T> root = new MenuTree<>();
        root.setId(TOP_NODE_ID);
        root.setParentId(StringUtils.EMPTY);
        root.setHasParent(false);
        root.setHasChild(true);
        root.setChecked(true);
        root.setChilds(topNodes);
        Map<String, Object> state = new HashMap<>(16);
        root.setState(state);
        return root;
    }

    public static <T> List<DeptTree<T>> buildDeptTree(List<DeptTree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<DeptTree<T>> result = new ArrayList<>();
        nodes.forEach(children -> {
            String pid = children.getParentId();
            if (pid == null || TOP_NODE_ID.equals(pid)) {
                result.add(children);
                return;
            }
            for (DeptTree<T> n : nodes) {
                String id = n.getId();
                if (id != null && id.equals(pid)) {
                    if (n.getChildren() == null) {
                        n.initChildren();
                    }
                    n.getChildren().add(children);
                    children.setHasParent(true);
                    n.setHasChild(true);
                    return;
                }
            }
        });

        return result;
    }

    public static <T> List<MenuTree<T>> buildList(List<MenuTree<T>> nodes, String idParam) {
        if (nodes == null) {
            return new ArrayList<>();
        }
        List<MenuTree<T>> topNodes = new ArrayList<>();
        nodes.forEach(children -> {
            String pid = children.getParentId();
            if (pid == null || idParam.equals(pid)) {
                topNodes.add(children);
                return;
            }
            nodes.forEach(parent -> {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChilds().add(children);
                    children.setHasParent(true);
                    parent.setHasChild(true);
                }
            });
        });
        return topNodes;
    }
}