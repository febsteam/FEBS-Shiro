function updateDict() {
    var selected = $("#dictTable").bootstrapTable("getSelections");
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的字典！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个字典！');
        return;
    }
    var dictId = selected[0].dictId;
    $.post(ctx + "dict/getDict", {"dictId": dictId}, function (r) {
        if (r.code === 0) {
            var $form = $('#dict-add');
            $form.modal();
            var dict = r.msg;
            $("#dict-add-modal-title").html('修改字典');
            $form.find("input[name='dictId']").val(dict.dictId);
            $form.find("input[name='keyy']").val(dict.keyy);
            $form.find("input[name='valuee']").val(dict.valuee);
            $form.find("input[name='tableName']").val(dict.tableName);
            $form.find("input[name='fieldName']").val(dict.fieldName);
            $("#dict-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}