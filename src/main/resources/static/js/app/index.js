	var $breadcrumb = $(".breadcrumb");
	var $main_content = $(".main-content");
	var $card = $(".card");
	var $card_block = $(".card-block");
	$(window).on("load", function() {
	    setTimeout(function() {
	        $(".page-loader").fadeOut()
	    }, 500)
	}), $(document).ready(function() {
		 // 设置主题色
	    var theme_color = $MB.getThemeColor(theme);
	    var opacity_color = $MB.getThemeRGBA(theme,0.1);
	    var $head = $("head");
	    $("body").attr("data-ma-theme", theme);
	    $(".bg-" + theme).addClass("active").siblings().removeClass("active");
	    $head.append("<style>.toggle-switch__checkbox:checked ~ .toggle-switch__helper:after{background-color: " + theme_color + "}</style>")
	        .append("<style>.btn-save{background: " + theme_color + "; color: #fff }</style>")
	        .append("<style>.custom-control-input:checked ~ .custom-control-indicator{ border-color: " + theme_color + " }</style>")
	        .append("<style>.custom-radio .custom-control-indicator:before{background-color: " + theme_color + "}</style>")
	        .append("<style>.navigation__active > a:hover{color: " + theme_color + " !important;}</style>")
	        .append("<style>.navigation__active{color: " + theme_color + ";}</style>")
	        .append("<style>.fixed-table-pagination .pagination li.active a{ background:" + theme_color + ";border: 1px solid " + theme_color + "}</style>")
	        .append("<style>.form-group__bar:before, .form-group__bar:after {background-color: " + theme_color + "}</style>")
	        .append("<style>.daterangepicker td.active, .daterangepicker td.active:hover,.end-date {background-color: "+ theme_color +" !important}</style>")
	        .append("<style>.daterangepicker td.in-range {background-color:"+opacity_color+"}</style>");
	}), $(document).ready(function() {
	    function a(a) {
	        a.requestFullscreen ? a.requestFullscreen() : a.mozRequestFullScreen ? a.mozRequestFullScreen() : a.webkitRequestFullscreen ? a.webkitRequestFullscreen() : a.msRequestFullscreen && a.msRequestFullscreen()
	    }
	    $("body").on("click", "[data-ma-action]", function(b) {
	        b.preventDefault();
	        var c = $(this),
	            d = c.data("ma-action"),
	            e = "";
	        switch (d) {
	            case "aside-open":
	                e = c.data("ma-target"), c.addClass("toggled"), $(e).addClass("toggled"), $(".content, .header").append('<div class="ma-backdrop" data-ma-action="aside-close" data-ma-target=' + e + " />");
	                break;
	            case "aside-close":
	                e = c.data("ma-target"), $('[data-ma-action="aside-open"], ' + e).removeClass("toggled"), $(".content, .header").find(".ma-backdrop").remove();
	                break;
	        }
	    })
	}), $(document).ready(function() {
	    $("body").on("change", ".theme-switch input:radio", function() {
	        var a = $(this).val();
	        $("body").attr("data-ma-theme", a);
	        $.get(ctx + "user/theme", { "theme": a, "username": userName }, function(r) {
	            if (r.code == 0) $MB.n_success("主题更换成功，下次登录时生效！");
	        });
	    })
	}), $("body").on("click", ".navigation__sub > a", function(a) {
	    a.preventDefault(), $(this).parent().toggleClass("navigation__sub--toggled"), $(this).next("ul").slideToggle(250)
	}), $(document).ready(function() {
	    var str = "";
	    var forTree = function(o) {
	        o.length;
	        for (var i = 0; i < o.length; i++) {
	            var urlstr = "";
	            try {
	                if (!o[i]["url"]) {
	                    urlstr = "<div><span><i class='" + o[i]["icon"] + "'></i>&nbsp;&nbsp;" + o[i]["text"] + "</span><ul>";
	                } else {
	                    urlstr = "<div><span name=" + o[i]["url"] + " onclick='loadMain(this);'><i class='" + o[i]["icon"] + "'></i>&nbsp;&nbsp;" + o[i]["text"] + "</span><ul>";
	                }
	                str += urlstr;
	                if (o[i]["children"].length != 0) {
	                    forTree(o[i]["children"]);
	                }
	                str += "</ul></div>";
	            } catch (e) {
	                console.log(e);
	            }
	        }
	        return str;
	    }

	    var menuTree = function() {
	        $("#navigation ul").each(function(index, element) {
	            var ulContent = $(element).html();
	            var spanContent = $(element).siblings("span").html();
	            if (ulContent) {
	                $(element).siblings("span").html(spanContent)
	            }
	        });

	        $("#navigation").find("div span").click(function() {
	            var ul = $(this).siblings("ul").hide(300);
	            var spanStr = $(this).html();
	            var spanContent = spanStr.substr(3, spanStr.length);
	            if (ul.find("div").html() != null) {
	                if (ul.css("display") == "none") {
	                    ul.show(300);
	                } else {
	                    ul.hide(300);
	                }
	            }
	        });

	        $("#navigation").find("div>span").click(function() {
	            var ul = $(this).parent().siblings().find(">ul");
	            ul.hide(300);
	        })
	    }

	    $.post(ctx + "menu/getUserMenu", { "userName": userName }, function(r) {
	        if (r.code == 0) {
	            var data = r.msg;
	            document.getElementById("navigation").innerHTML = forTree(data.children);
	            menuTree();
	            $(".scrollbar-inner")[0] && $(".scrollbar-inner").scrollbar().scrollLock()
	        } else {
	            $MB.n_danger(r.msg);
	        }
	    })

	});

	function loadMain(obj) {
	    var $this = $(obj);
	    $(".navigation").find("span").removeClass("navigation__active");
	    $this.addClass("navigation__active").parents("ul").prev().addClass("navigation__active");

	    var breadcrumnHtml = "";
	    var target_text = $this.text();
	    var text_arr = new Array();
	    var parent = $this.parents("ul").prev().each(function() {
	        var $this = $(this);
	        text_arr.unshift($this.text());
	    });
	    for (var i = 0; i < text_arr.length; i++) {
	        breadcrumnHtml += '<li class="breadcrumb-item">' + text_arr[i] + '</li>';
	    }
	    breadcrumnHtml += '<li class="breadcrumb-item">' + target_text + '</li>';
	    $breadcrumb.html("").append(breadcrumnHtml);

	    var $name = $this.attr("name");
	    $.post(ctx + $name, {}, function(r) {
	        if (r.indexOf('账户登录') != -1) {
	            location = location;
	            return;
	        }
	        $main_content.html("").append(r);
	    });
	}

	function fullScreen(obj) {
	    var $this = $(obj);
	    var element;
	    if ($this.text() == '全屏') {
	        $this.text("退出全屏");
	        element = document.documentElement;
	        if (element.requestFullscreen) {
	            element.requestFullscreen();
	        } else if (element.mozRequestFullScreen) {
	            element.mozRequestFullScreen();
	        } else if (element.webkitRequestFullscreen) {
	            element.webkitRequestFullscreen();
	        } else if (element.msRequestFullscreen) {
	            element.msRequestFullscreen();
	        } else {
	            $MB.n_info("当前浏览器不支持全屏或已被禁用！");
	            $this.text("全屏");
	        }
	    } else {
	        elem = document;
	        $this.text("全屏");
	        if (elem.webkitCancelFullScreen) {
	            elem.webkitCancelFullScreen();
	        } else if (elem.mozCancelFullScreen) {
	            elem.mozCancelFullScreen();
	        } else if (elem.cancelFullScreen) {
	            elem.cancelFullScreen();
	        } else if (elem.exitFullscreen) {
	            elem.exitFullscreen();
	        }
	    }
	}

	$(".user__img").attr("src", avatar);
	$("#user__profile").on('click', function() {
	    $.post(ctx + "user/profile", function(r) {
	        $breadcrumb.html("").append('<li class="breadcrumb-item">个人信息</li>');
	        $main_content.html("").append(r);
	    });
	});