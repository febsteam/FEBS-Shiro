$(function () {
    $.post(ctx + "one/oneList", function (r) {
        if (r.code === 0) {
            var data = JSON.parse(r.msg);
            var yuwen = data.data.content_list[1];
            var item_id = yuwen.item_id;
            var id = yuwen.id;
            getYuWenDetail(item_id, id);
        } else {
            $MB.n_danger(r.msg);
        }
    });
});

function getYuWenDetail(item_id, id) {
    $.post(ctx + "one/yuwenDetail", {"itemId": item_id, "id": id}, function (r) {
        if (r.code === 0) {
            var data = JSON.parse(r.msg);
            var $cardBlock = $(".card-block");
            data = data.data;
            $(".card-title").text(data.hp_title);
            var html = '<span>' + data.maketime +
                '&nbsp;&nbsp;' + data.hp_author + '</span>';
            $(".card-subtitle").html("").append(html);
            $cardBlock.html("").append(data.hp_content);
            html = '<span style="font-weight: 600">' + data.hp_author_introduce +
                '&nbsp;&nbsp;最后编辑时间：' + data.last_update_date + '</span>';
            $cardBlock.append(html);
            $("#yuwen-comments").text("网友评论：");
            getYuWenComments(item_id);
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

function getYuWenComments(item_id) {
    $.post(ctx + "one/yuwenComments", {"itemId": item_id}, function (r) {
        if (r.code === 0) {
            var data = JSON.parse(r.msg);
            data = data.data.data;
            var comments_html = "";
            for (var i = data.length - 1; i >= 0; i--) {
                if (!data[i].quote.length) {
                    comments_html += '<div class="listview__item" id="' + data[i].user.user_id + '">';
                    comments_html += '<label class="custom-control custom-control--char todo__item">';
                    comments_html += '<span class="custom-control-char"><img src="' + data[i].user.web_url + '"/></span>';
                    comments_html += '<div class="todo__info">';
                    comments_html += '<span style="display:inline-block">' + data[i].user.user_name + '</span>&nbsp;&nbsp;';
                    comments_html += '<small style="display:inline-block">' + data[i].input_date + '</small>';
                    comments_html += '</div><div class="comments__info" style="padding: 6px 0">';
                    comments_html += '<span>' + data[i].content + '</span></div></label></div>';
                }
            }
            $(".listview--bordered").html("").append(comments_html);
            for (var i = data.length - 1; i >= 0; i--) {
                if (data[i].quote.length) {
                    var replay_id = data[i].touser.user_id;
                    var replay_html = "";
                    replay_html += '<div class="listview__item">';
                    replay_html += '<label class="custom-control custom-control--char todo__item">';
                    replay_html += '<div class="todo__info">';
                    replay_html += '<span style="display:inline-block">' + data[i].user.user_name + '</span>&nbsp;&nbsp;';
                    replay_html += '<small style="display:inline-block">' + data[i].input_date + '</small>';
                    replay_html += '</div><div class="comments__info" style="padding: 6px 0">';
                    replay_html += '<span>' + data[i].content + '</span></div>';
                    replay_html += '<span class="custom-control-char"><img src="' + data[i].user.web_url + '"/></span>';
                    replay_html += '</label></div>';
                    $("#" + replay_id).after(replay_html);
                }
            }
        } else {
            $MB.n_danger(r.msg);
        }
    });
}