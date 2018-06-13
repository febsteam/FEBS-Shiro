var validator;
var $jobAddForm = $("#job-add-form");

$(function () {
    validateRule();

    $("#job-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $jobAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "job/add", $jobAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "job/update", $jobAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#job-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#job-add-button").attr("name", "save");
    $MB.closeAndRestModal("job-add");
    validator.resetForm();
    $("#job-add-modal-title").html('新增任务');
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $jobAddForm.validate({
        rules: {
            beanName: {
                required: true,
                maxlength: 100
            },
            methodName: {
                required: true,
                maxlength: 100
            },
            cronExpression: {
                required: true,
                maxlength: 100,
                remote: {
                    url: "job/checkCron",
                    type: "get",
                    dataType: "json",
                    data: {
                        cron: function () {
                            return $("input[name='cronExpression']").val().trim();
                        }
                    }
                }
            }
        },
        messages: {
            beanName: {
                required: icon + "请输入Bean名称",
                maxlength: icon + "长度不能超过100个字符"
            },
            methodName: {
                required: icon + "请输入方法名称",
                maxlength: icon + "长度不能超过100个字符"
            },
            cronExpression: {
                required: icon + "请输入cron表达式",
                maxlength: icon + "长度不能超过100个字符",
                remote: icon + "cron表达式不合法"
            }
        }
    });
}