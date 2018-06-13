$(function () {
    var $logTableForm = $(".log-table-form");
    var settings = {
        url: ctx + "log/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                timeField: $logTableForm.find("input[name='timeField']").val().trim(),
                username: $logTableForm.find("input[name='username']").val().trim(),
                operation: $logTableForm.find("input[name='operation']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        },
            {
                field: 'username',
                title: '操作用户'
            }, {
                field: 'operation',
                title: '描述'
            }, {
                field: 'time',
                title: '耗时（毫秒）'
            }, {
                field: 'method',
                title: '操作方法'
            }, {
                field: 'params',
                title: '参数'
            }, {
                field: 'ip',
                title: 'IP地址'
            }, {
                field: 'location',
                title: '操作地点'
            }, {
                field: 'createTime',
                title: '操作时间'
            }
        ]
    };

    $MB.initTable('logTable', settings);
    $MB.calenders('input[name="timeField"]', true, false);
});

function search() {
    $MB.refreshTable('logTable');
}

function refresh() {
    $(".log-table-form")[0].reset();
    search();
}

function deleteLogs() {
    var selected = $("#logTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的日志！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中的日志？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'log/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportLogExcel() {
    $.post(ctx + "log/excel", $(".log-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportLogCsv() {
    $.post(ctx + "log/csv", $(".log-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}