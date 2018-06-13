var validateUpdatePassword;
var $updatePasswordForm = $("#update-password-form");

$(function () {
    validateUpdatePasswordRule();

    $("#update-password .btn-save").click(function () {
        validateUpdatePassword = $updatePasswordForm.validate();
        var flag = validateUpdatePassword.form();
        if (flag) {
            $.post(ctx + "user/updatePassword", $updatePasswordForm.serialize(), function (r) {
                if (r.code === 0) {
                    validateUpdatePassword.resetForm();
                    $MB.closeAndRestModal("update-password");
                    $MB.n_success(r.msg);
                } else $MB.n_danger(r.msg);
            });
        }
    });

    $("#update-password .btn-close").click(function () {
        validateUpdatePassword.resetForm();
        $MB.closeAndRestModal("update-password");
    });

});

function validateUpdatePasswordRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validateUpdatePassword = $updatePasswordForm.validate({
        rules: {
            oldPassword: {
                required: true,
                remote: {
                    url: "user/checkPassword",
                    type: "get",
                    dataType: "json",
                    data: {
                        password: function () {
                            return $("input[name='oldPassword']").val().trim();
                        }
                    }
                }
            },
            newPassword: {
                required: true,
                minlength: 6
            },
            confirm: {
                required: true,
                equalTo: "#newPassword"
            }
        },
        messages: {
            oldPassword: {
                required: icon + "请输入原密码",
                remote: icon + "原密码错误"
            },
            newPassword: {
                required: icon + "请输入新密码",
                minlength: icon + "密码不能小于6个字符"
            },
            confirm: {
                required: icon + "请再次输入新密码",
                equalTo: icon + "两次密码输入不一致"
            }

        }
    });
}