var validator;
var $donaAddForm = $("#dona-add-form");

$(function () {
    validateRule();
    createDonaTree();

    $("#dona-add .btn-save").click(function () {
        var name = $(this).attr("name");
        getDona();
        validator = $donaAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "dona/add", $donaAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "dona/update", $donaAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#dona-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#dona-add-button").attr("name", "save");
    $("#dona-add-modal-title").html('新增捐赠品');
    validator.resetForm();
    $MB.closeAndRestModal("dona-add");
    $MB.refreshJsTree("donaTree", createDonaTree());
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $donaAddForm.validate({
        rules: {
            donaUser: {
                required: true,
                minlength: 1,
                maxlength: 20,
                remote: {
                    url: "dona/checkDonaUser",
                    type: "get",
                    dataType: "json",
                    data: {
                        donaUser: function () {
                            return $("input[name='donaUser']").val().trim();
                        },
                        oldDonaUser: function () {
                            return $("input[name='oldDonaUser']").val().trim();
                        }
                    }
                }
            }
        },
        messages: {
            donaUser: {
                required: icon + "请输入捐赠人",
                minlength: icon + "捐赠人名不超过20个字",
                remote: icon + "该捐赠人已经存在"
            },
            donaName: {
                required: icon + "请输入捐赠品"
            }
        }
    });
}

function createDonaTree() {
    $.post(ctx + "dona/tree", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $('#donaTree').jstree({
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

function getDona() {
    var ref = $('#donaTree').jstree(true);
    var donaIds = ref.get_checked();
    $("[name='parentId']").val(donaIds[0]);
}