$(function () {
    $.post(ctx + "one/oneList", function (r) {
        if (r.code === 0) {
            var data = JSON.parse(r.msg);
            var paint = data.data.content_list[0];
            $(".card-block").find("img").attr("src", paint.img_url);
            $(".paint-meno").text(paint.forward);
        } else {
            $MB.n_danger(r.msg);
        }
    });
});