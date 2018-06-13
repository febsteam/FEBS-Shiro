var validator;
var $userAddForm = $("#user-add-form");
var $rolesSelect = $userAddForm.find("select[name='rolesSelect']");
var $roles = $userAddForm.find("input[name='roles']");

$(function () {
    validateRule();
    initRole();
    createDeptTree();

    $("input[name='status']").change(function () {
        var checked = $(this).is(":checked");
        var $status_label = $("#status");
        if (checked) $status_label.html('可用');
        else $status_label.html('禁用');
    });

    $("#user-add .btn-save").click(function () {
        var name = $(this).attr("name");
        getDept();
        var validator = $userAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "user/add", $userAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("userTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "user/update", $userAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("userTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#user-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#user-add-button").attr("name", "save");
    validator.resetForm();
    $rolesSelect.multipleSelect('setSelects', []);
    $rolesSelect.multipleSelect("refresh");
    $userAddForm.find("input[name='username']").removeAttr("readonly");
    $userAddForm.find(".user_password").show();
    $userAddForm.find("input[name='status']").prop("checked", true);
    $("#user-add-modal-title").html('新增用户');
    $("#status").html('可用');
    $MB.resetJsTree("deptTree");
    $MB.closeAndRestModal("user-add");

}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $userAddForm.validate({
        rules: {
            username: {
                required: true,
                minlength: 3,
                maxlength: 10,
                remote: {
                    url: "user/checkUserName",
                    type: "get",
                    dataType: "json",
                    data: {
                        username: function () {
                            return $("input[name='username']").val().trim();
                        },
                        oldusername: function () {
                            return $("input[name='oldusername']").val().trim();
                        }
                    }
                }
            },
            email: {
                email: true
            },
            roles: {
                required: true
            },
            mobile: {
                checkPhone: true
            },
            ssex: {
                required: true
            }
        },
        errorPlacement: function (error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages: {
            username: {
                required: icon + "请输入用户名",
                minlength: icon + "用户名长度3到10个字符",
                remote: icon + "用户名已经存在"
            },
            roles: icon + "请选择用户角色",
            email: icon + "邮箱格式不正确",
            ssex: icon + "请选择性别"
        }
    });
}

function initRole() {
    $.post(ctx + "role/list", {}, function (r) {
        var data = r.rows;
        var option = "";
        for (var i = 0; i < data.length; i++) {
            option += "<option value='" + data[i].roleId + "'>" + data[i].roleName + "</option>"
        }
        $rolesSelect.html("").append(option);
        var options = {
            selectAllText: '所有角色',
            allSelected: '所有角色',
            width: '100%',
            onClose: function () {
                $roles.val($rolesSelect.val());
                validator.element("input[name='roles']");
            }
        };

        $rolesSelect.multipleSelect(options);
    });
}

function createDeptTree() {
    $.post(ctx + "dept/tree", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $('#deptTree').jstree({
                "core": {
                    'data': data.children,
                    'multiple': false // 取消多选
                },
                "state": {
                    "disabled": true
                },
                "checkbox": {
                    "three_state": false // 取消选择父节点后选中所有子节点
                },
                "plugins": ["wholerow", "checkbox"]
            });
        } else {
            $MB.n_danger(r.msg);
        }
    })
}

function getDept() {
    var ref = $('#deptTree').jstree(true);
    $("[name='deptId']").val(ref.get_selected()[0]);
}