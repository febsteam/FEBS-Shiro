$(function () {
    var $jobTableForm = $(".job-table-form");
    var settings = {
        url: ctx + "job/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                beanName: $jobTableForm.find("input[name='beanName']").val().trim(),
                methodName: $jobTableForm.find("input[name='methodName']").val().trim(),
                status: $jobTableForm.find("select[name='status']").val()
            };
        },
        columns: [{
            checkbox: true
        },
            {
                field: 'jobId',
                title: '任务ID'
            }, {
                field: 'beanName',
                title: 'Bean名称'
            }, {
                field: 'methodName',
                title: '方法名称'
            }, {
                field: 'params',
                title: '参数'
            }, {
                field: 'cronExpression',
                title: 'cron表达式'
            }, {
                field: 'remark',
                title: '备注'
            }, {
                field: 'status',
                title: '状态',
                formatter: function (value, row, index) {
                    if (value === '1') return '<span class="badge badge-danger">暂停</span>';
                    if (value === '0') return '<span class="badge badge-success">正常</span>';
                }
            }
        ]
    };

    $MB.initTable('jobTable', settings);
});

function search() {
    $MB.refreshTable('jobTable');
}

function refresh() {
    $(".job-table-form")[0].reset();
    search();
}

function deleteJob() {
    var selected = $("#jobTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的任务！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].jobId;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中的任务？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'job/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function runJob() {
    var selected = $("#jobTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要立即执行的任务！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].jobId;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定执行选中的任务？",
        confirmButtonText: "确定执行"
    }, function () {
        $.post(ctx + 'job/run', {"jobIds": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function pauseJob() {
    var selected = $("#jobTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要暂停的任务！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].jobId;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定暂停选中的任务？",
        confirmButtonText: "确定暂停"
    }, function () {
        $.post(ctx + 'job/pause', {"jobIds": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function resumeJob() {
    var selected = $("#jobTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要恢复的任务！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].jobId;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定恢复选中的任务？",
        confirmButtonText: "确定恢复"
    }, function () {
        $.post(ctx + 'job/resume', {"jobIds": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportJobExcel() {
    $.post(ctx + "job/excel", $(".job-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportJobCsv() {
    $.post(ctx + "job/csv", $(".job-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}