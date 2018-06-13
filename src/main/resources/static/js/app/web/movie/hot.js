$(function () {
    $.post(ctx + 'movie/getMovieHot', {}, function (r) {
        if (r.code === 0) {
            var data = JSON.parse(r.msg);
            var movie_list = data.ms;
            var movie_list_html = "";
            for (var i = 0; i < movie_list.length; i++) {
                movie_list_html += '<div class="col-xl-3 col-lg-3 col-sm-4 col-6">';
                movie_list_html += '<div class="groups__item">';
                movie_list_html += '<li class="movie-img aspectration" data-ratio="16:9" style="background-image:url(' + movie_list[i].img + ');"></li>';
                movie_list_html += '<div class="groups__info">';
                movie_list_html += '<strong>' + movie_list[i].tCn + '</strong>';
                movie_list_html += '<small>' + movie_list[i].movieType + '</small>';
                movie_list_html += getScoreHtml(movie_list[i].r);
                movie_list_html += '</div>';
                movie_list_html += '<div class="dropdown-menu dropdown-menu-right movie-action" style="min-width:100px;background-color:rgba(255,255,255,.9);z-index:1">';
                movie_list_html += '<a class="dropdown-item" onclick="getMoiveDetail(\'' + movie_list[i].id + '\');" href="javascript:void(0)">查看详情</a>';
                movie_list_html += '<a class="dropdown-item" onclick="getMoiveComments(\'' + movie_list[i].id + '\',\'' + movie_list[i].tCn + '\');" href="javascript:void(0)">查看评论</a>';
                movie_list_html += '</div></div></div>';
            }
            $(".movie-list").html("").append(movie_list_html);
            $(".groups__item").each(function () {
                var $this = $(this);
                $this.mouseenter(function () {
                    $this.find(".movie-action").show();
                });
                $this.mouseleave(function () {
                    $this.find(".movie-action").hide();
                });
            });
        } else {
            $MB.n_danger(r.msg);
        }
    });
});

function getScoreHtml(score) {
    var html = '';
    if (score >= 9) {
        html = '<div class="widget-ratings__star">' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star active"></i>&nbsp;' + score + '</div>';
    } else if (score >= 8) {
        html = '<div class="widget-ratings__star">' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star"></i>&nbsp;' + score + '</div>';
    } else if (score >= 6) {
        html = '<div class="widget-ratings__star">' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star"></i>' +
            '<i class="zmdi zmdi-star"></i>&nbsp;' + score + '</div>';
    } else if (score >= 4) {
        html = '<div class="widget-ratings__star">' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star"></i>' +
            '<i class="zmdi zmdi-star"></i>' +
            '<i class="zmdi zmdi-star"></i>&nbsp;' + score + '</div>';
    } else if (score >= 2) {
        html = '<div class="widget-ratings__star">' +
            '<i class="zmdi zmdi-star active"></i>' +
            '<i class="zmdi zmdi-star"></i>' +
            '<i class="zmdi zmdi-star"></i>' +
            '<i class="zmdi zmdi-star"></i>' +
            '<i class="zmdi zmdi-star"></i>&nbsp;' + score + '</div>';
    } else {
        html = '<div class="widget-ratings__star">' +
            '<i class="zmdi zmdi-star"></i>' +
            '<i class="zmdi zmdi-star"></i>' +
            '<i class="zmdi zmdi-star"></i>' +
            '<i class="zmdi zmdi-star"></i>' +
            '<i class="zmdi zmdi-star"></i>&nbsp;<span style="font-size:1rem">暂无评分</span></div>';
    }
    return html;
}

function getMoiveDetail(id) {
    $.post(ctx + "movie/detail", {"id": id}, function (r) {
        if (r.code === 0) {
            var data = JSON.parse(r.msg).data;
            var basic = data.basic;
            $("#img").attr("src", basic.img);
            var movieName = basic.name;
            if (basic.nameEn) {
                movieName += " / " + basic.nameEn;
            }
            $("#name").text("片名：" + movieName);
            $("#director").text("导演：" + basic.director.name);
            var curr = basic.releaseDate;
            var dateStr = curr.substr(0, 4) + "-" + curr.substr(4, 2) + "-" + curr.substr(6, 2);
            $("#releaseDate").text("上映日期：" + dateStr);
            $("#releaseArea").text("上映地区 / 国家：" + basic.releaseArea);
            $("#mins").text("片长：" + basic.mins);
            $("#is3D").text("是否3D：" + (basic.is3D ? "是" : "否"));
            $("#isIMAX").text("是否IMAX：" + (basic.isIMAX ? "是" : "否"));
            $("#movie-story").text(basic.story);
            var actors = basic.actors;
            var actors_html = "主演：";
            for (var i = 0; i < actors.length; i++) {
                if (!actors[i].name) continue;
                if (i === 0)
                    actors_html += '<a href="' + actors[i].img + '" target="_blank">' + actors[i].name + '</a>';
                else
                    actors_html += ' / <a href="' + actors[i].img + '" target="_blank">' + actors[i].name + '</a>';
            }
            $("#actors_list").html(actors_html);
            var video_html = '<a href="' + basic.video.hightUrl.replace("https", "http") + '" target="_blank">点击查看</a>';
            $("#previw span").html(video_html);
            var $form = $('#movie-detail');
            $form.modal();
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

function getMoiveComments(id, title) {
    $.post(ctx + "movie/comments", {"id": id}, function (r) {
        var data = JSON.parse(r.msg).data;
        var mini = data.mini.list;
        var plus = data.plus.list;
        if (!mini.length && !plus.length) {
            $MB.n_warning("该影片暂无评论");
            return;
        }
        $("#movie-comments-modal-title").text("《" + title + "》影评");
        var comments_html = "";
        for (var i = 0; i < mini.length; i++) {
            comments_html += '<div class="listview__item">';
            comments_html += '<label class="custom-control custom-control--char todo__item">';
            comments_html += '<span class="custom-control-char"><img src="' + mini[i].headImg + '"/></span>';
            comments_html += '<div class="todo__info">';
            comments_html += '<span style="display:inline-block">' + mini[i].nickname + '</span>&nbsp;&nbsp;';
            comments_html += '<small style="display:inline-block">' + getDate(mini[i].commentDate) + '</small>';
            comments_html += '</div><div class="comments__info" style="padding: 6px 0">';
            comments_html += '<span>' + mini[i].content + '</span></div></label></div>';
        }
        for (var i = 0; i < plus.length; i++) {
            comments_html += '<div class="listview__item">';
            comments_html += '<label class="custom-control custom-control--char todo__item">';
            comments_html += '<span class="custom-control-char"><img src="' + plus[i].headImg + '"/></span>';
            comments_html += '<div class="todo__info">';
            comments_html += '<span style="display:inline-block">' + plus[i].nickname + '</span>&nbsp;&nbsp;';
            comments_html += '<small style="display:inline-block">' + getDate(plus[i].commentDate) + '</small>';
            comments_html += '</div><div class="comments__info" style="padding: 6px 0">';
            comments_html += '<span>' + plus[i].content + '</span></div></label></div>';
        }
        $(".listview--bordered").html("").append(comments_html);
        var $form = $('#movie-comments');
        $form.modal();
    });
}

function getDate(tm) {
    return new Date(tm * 1000).toLocaleString();
}