$(function() {
    initTreeTable();
});

function initTreeTable() {
    var setting = {
        id: 'areaId',
        code: 'areaId',
        url: ctx + 'area/list',
        expandAll: true,
        expandColumn: "2",
        ajaxParams: {
            deptName: $(".area-table-form").find("input[name='areaName']").val().trim()
        },
        columns: [{
            field: 'selectItem',
            checkbox: true
        },
            {
                title: '编号',
                field: 'areaId',
                width: '50px'
            },
            {
                title: '名称',
                field: 'areaName'
            },
            {
                title: '创建时间',
                field: 'createTime'
            }
        ]
    };

    $MB.initTreeTable('areaTable', setting);
}

function search() {
    initTreeTable();
}

function refresh() {
    $(".area-table-form")[0].reset();
    search();
    $MB.refreshJsTree("areaTree", createAreaTree());
}

function deleteAreas() {
    var ids = $("#areaTable").bootstrapTreeTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $MB.n_warning("请勾选需要删除的区域！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $MB.confirm({
        text: "确定删除选中区域？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'area/delete', { "ids": ids_arr }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportAreaExcel(){
    $.post(ctx+"area/excel",$(".area-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportAreaCsv(){
    $.post(ctx+"area/csv",$(".area-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}