var validator;
var $areaAddForm = $("#area-add-form");

$(function () {
    validateRule();
    createAreaTree();

    $("#area-add .btn-save").click(function () {
        var name = $(this).attr("name");
        getArea();
        validator = $areaAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "area/add", $areaAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "area/update", $areaAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#area-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#area-add-button").attr("name", "save");
    $("#area-add-modal-title").html('新增区域');
    validator.resetForm();
    $MB.closeAndRestModal("area-add");
    $MB.refreshJsTree("areaTree", createAreaTree());
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $areaAddForm.validate({
        rules: {
            areaName: {
                required: true,
                minlength: 2,
                maxlength: 50,
                remote: {
                    url: "area/checkAreaName",
                    type: "get",
                    dataType: "json",
                    data: {
                        areaName: function () {
                            return $("input[name='areaName']").val().trim();
                        },
                        oldAreaName: function () {
                            return $("input[name='oldAreaName']").val().trim();
                        }
                    }
                }
            }
        },
        messages: {
            areaName: {
                required: icon + "请输入区域名称",
                minlength: icon + "区域名称长度3到10个字符",
                remote: icon + "该区域名称已经存在"
            }
        }
    });
}

function createAreaTree() {
    $.post(ctx + "area/tree", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $('#areaTree').jstree({
                "core": {
                    'data': data.children,
                    'multiple': false
                },
                "state": {
                    "disabled": true
                },
                "checkbox": {
                    "three_state": false
                },
                "plugins": ["wholerow", "checkbox"]
            });
        } else {
            $MB.n_danger(r.msg);
        }
    })

}

function getArea() {
    var ref = $('#areaTree').jstree(true);
    var areaIds = ref.get_checked();
    $("[name='parentId']").val(areaIds[0]);
}