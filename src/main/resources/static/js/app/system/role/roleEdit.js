function updateRole() {
    var selected = $("#roleTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的角色！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个角色！');
        return;
    }
    var roleId = selected[0].roleId;
    $.post(ctx + "role/getRole", {"roleId": roleId}, function (r) {
        if (r.code === 0) {
            var $form = $('#role-add');
            var $menuTree = $('#menuTree');
            $form.modal();
            var role = r.msg;
            $("#role-add-modal-title").html('修改角色');
            $form.find("input[name='roleName']").val(role.roleName);
            $form.find("input[name='oldRoleName']").val(role.roleName);
            $form.find("input[name='roleId']").val(role.roleId);
            $form.find("input[name='remark']").val(role.remark);
            var menuArr = [];
            for (var i = 0; i < role.menuIds.length; i++) {
                menuArr.push(role.menuIds[i]);
            }
            $menuTree.jstree('select_node', menuArr, true);
            $menuTree.jstree().close_all();
            $("#role-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}