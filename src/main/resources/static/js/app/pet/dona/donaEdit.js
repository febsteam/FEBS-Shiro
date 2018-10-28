function updateDona() {
    var selected = $("#donaTable").bootstrapTreeTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的捐赠品！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个捐赠品！');
        return;
    }
    var donaId = selected[0].id;
    $.post(ctx + "dona/getDona", {"donaId": donaId}, function (r) {
        if (r.code === 0) {
            var $form = $('#dona-add');
            var $donaTree = $('#donaTree');
            $form.modal();
            var dona = r.msg;
            $("#dona-add-modal-title").html('修改捐赠品');
            $form.find("input[name='donaUser']").val(dona.donaUser);
            $form.find("input[name='oldDonaUser']").val(dona.donaUser);
            $form.find("input[name='donaId']").val(dona.donaId);
            $form.find("input[name='donaName']").val(dona.donaName);
            $form.find("input[name='donaDesc']").val(dona.donaDesc);
            $donaTree.jstree('select_node', dona.parentId, true);
            $donaTree.jstree('disable_node', dona.donaId);
            $("#dona-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}