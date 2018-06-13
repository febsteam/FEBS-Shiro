function updateDept() {
    var selected = $("#deptTable").bootstrapTreeTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的部门！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个部门！');
        return;
    }
    var deptId = selected[0].id;
    $.post(ctx + "dept/getDept", {"deptId": deptId}, function (r) {
        if (r.code === 0) {
            var $form = $('#dept-add');
            var $deptTree = $('#deptTree');
            $form.modal();
            var dept = r.msg;
            $("#dept-add-modal-title").html('修改部门');
            $form.find("input[name='deptName']").val(dept.deptName);
            $form.find("input[name='oldDeptName']").val(dept.deptName);
            $form.find("input[name='deptId']").val(dept.deptId);
            $deptTree.jstree('select_node', dept.parentId, true);
            $deptTree.jstree('disable_node', dept.deptId);
            $("#dept-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}