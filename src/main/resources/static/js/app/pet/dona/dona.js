$(function() {
    initTreeTable();
});
function initTreeTable() {
    var setting = {
        id: 'donaId',
        code: 'donaId',
        url: ctx + 'dona/list',
        expandAll: true,
        expandColumn: "2",
        ajaxParams: {
            donaUser: $(".dona-table-form").find("input[name='donaUser']").val().trim()
        },
        columns: [{
            field: 'selectItem',
            checkbox: true
        },
            {
                title: '编号',
                field: 'donaId',
                width: '50px'
            },
            {
                title: '捐赠人',
                field: 'donaUser'
            },
            {
                title: '捐赠品',
                field: 'donaName'
            },
            {
                title: '描述',
                field: 'donaDesc'
            },
            {
                title: '捐赠时间',
                field: 'createTime'
            }
        ]
    };

    $MB.initTreeTable('donaTable', setting);
}
function search() {
    initTreeTable();
}

function refresh() {
    $(".dona-table-form")[0].reset();
    search();
    $MB.refreshJsTree("donaTree", createDonaTree());
}

function deleteDonas() {
    var ids = $("#donaTable").bootstrapTreeTable("getSelections");
    var ids_arr = "";
    if (!ids.length) {
        $MB.n_warning("请勾选需要删除的物品！");
        return;
    }
    for (var i = 0; i < ids.length; i++) {
        ids_arr += ids[i].id;
        if (i !== (ids.length - 1)) ids_arr += ",";
    }
    $MB.confirm({
        text: "确定删除选中物品？",
        confirmButtonText: "确定物品"
    }, function() {
        $.post(ctx + 'dona/delete', { "ids": ids_arr }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportDonaExcel(){
    $.post(ctx+"dona/excel",$(".dona-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportDonaCsv(){
    $.post(ctx+"dona/csv",$(".dona-table-form").serialize(),function(r){
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}
