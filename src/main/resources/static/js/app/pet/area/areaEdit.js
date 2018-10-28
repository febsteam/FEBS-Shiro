function updateArea() {
    var selected = $("#areaTable").bootstrapTreeTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的区域！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个区域！');
        return;
    }
    var areaId = selected[0].id;
    $.post(ctx + "area/getArea", {"areaId": areaId}, function (r) {
        if (r.code === 0) {
            var $form = $('#area-add');
            var $areaTree = $('#areaTree');
            $form.modal();
            var area = r.msg;
            $("#area-add-modal-title").html('修改区域');
            $form.find("input[name='areaName']").val(area.areaName);
            $form.find("input[name='oldAreaName']").val(area.areaName);
            $form.find("input[name='areaId']").val(area.areaId);
            $areaTree.jstree('select_node', area.parentId, true);
            $areaTree.jstree('disable_node', area.areaId);
            $("#area-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}