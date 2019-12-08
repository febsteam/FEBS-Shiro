//视图路由
layui
    .extend({
        loadBar: 'lay/modules/loadBar',
        dropdown: 'lay/modules/dropdown'
    })
    .define(
        ['jquery', 'laytpl', 'element', 'form', 'loadBar', 'dropdown'],
        function (exports) {
            var $ = layui.jquery;
            var laytpl = layui.laytpl;
            var conf = layui.conf;
            conf.viewTabs = currentUser.isTab === '1';
            var loadBar = layui.loadBar;
            var self = {
                ie8:
                    navigator.appName === 'Microsoft Internet Explorer' &&
                    navigator.appVersion.split(';')[1].replace(/[ ]/g, '') === 'MSIE8.0',
                container: $('#' + conf.container),
                containerBody: null
            };
            self.loadBar = loadBar;
            /**
             * 字符串是否含有html标签的检测
             * @param htmlStr
             */
            self.checkHtml = function (htmlStr) {
                var reg = /<[^>]+>/g;
                return reg.test(htmlStr)
            };
            self.parse = function (container) {
                if (container === undefined) container = self.containerBody;
                var template =
                    container.get(0).tagName === 'SCRIPT'
                        ? container
                        : container.find('[template]');

                var renderTemplate = function (template, data, callback) {
                    laytpl(template.html()).render(data, function (html) {
                        try {
                            html = $(
                                self.checkHtml(html) ? html : '<span>' + html + '</span>'
                            )
                        } catch (err) {
                            html = $('<span>' + html + '</span>')
                        }

                        html.attr('is-template', true);
                        template.after(html);
                        if ($.isFunction(callback)) callback(html)
                    })
                };

                layui.each(template, function (index, t) {
                    var tem = $(t);
                    var url = tem.attr('lay-url') || '';
                    var api = tem.attr('lay-api') || '';
                    var type = tem.attr('lay-type') || 'get';
                    var data = new Function('return ' + tem.attr('lay-data'))();
                    var done = tem.attr('lay-done') || '';

                    if (url || api) {
                        //进行AJAX请求
                        self.request({
                            url: url,
                            api: api,
                            type: type,
                            data: data,
                            success: function (res) {
                                templateData = data;
                                renderTemplate(tem, res.data);
                                if (done) new Function(done)()
                            }
                        })
                    } else {
                        renderTemplate(
                            tem,
                            {},
                            self.ie8
                                ? function (elem) {
                                    if (elem[0] && elem[0].tagName !== 'LINK') return;
                                    container.hide();
                                    elem.load(function () {
                                        container.show()
                                    })
                                }
                                : null
                        );
                        if (done) new Function(done)()
                    }
                })
            };
            self.loading = function (elem) {
                elem.append(
                    (this.elemLoad = $(
                        '<i class="layui-anim layui-anim-rotate layui-anim-loop layui-icon layui-icon-loading febs-loading"></i>'
                    ))
                )
            };
            self.loadend = function () {
                this.elemLoad && this.elemLoad.remove()
            };

            self.setTitle = function (title) {
                $(document).attr({title: title + ' - ' + conf.name})
            };
            self.clear = function () {
                self.containerBody.html('')
            };

            self.modal = {};

            self.modal.base = function (msg, params) {
                params = params || {};
                params.titleIcoColor = params.titleIcoColor || '#5a8bff';
                params.titleIco = params.titleIco || 'exclaimination';
                params.title = params.title || [
                    '<i class="layui-icon layui-icon-' +
                    params.titleIco +
                    '" style="font-size:12px;background:' +
                    params.titleIcoColor +
                    ';display:inline-block;position:relative;top:-2px;height:21px;line-height:21px;text-align:center;width:21px;color:#fff;border-radius:50%;margin-right:12px;"></i>' +
                    params.titleValue,
                    'background:#fff;border:none;font-weight:bold;font-size:16px;color:#08132b;padding-top:20px;height:36px;line-height:46px;padding-bottom:0;'
                ];
                params = $.extend(
                    {
                        skin: 'layui-layer-admin-modal febs-alert',
                        area: [$(window).width() <= 750 ? '60%' : '400px'],
                        closeBtn: 0,
                        shadeClose: false
                    },
                    params
                );
                layer.alert(msg, params);
            };

            self.notify = function (title, msg, yes, params) {
                params = params || {};
                params.titleIco = 'exclaimination';
                params.titleIcoColor = '#ffc107';
                params.titleValue = title;
                params.shadeClose = false;
                params = $.extend({
                    btn: ['确定']
                    , yes: function (index, layero) {
                        yes && (yes)();
                        layer.close(index);
                    }
                }, params);
                self.modal.base(msg, params);
            };

            self.loadHtml = function (url, callback) {
                url = url || conf.entry;
                loadBar.start();
                var queryIndex = url.indexOf('?');
                if (queryIndex !== -1) url = url.slice(0, queryIndex);
                $.ajax({
                    url:
                        (url.indexOf(conf.base) === 0 ? '' : conf.views) +
                        url +
                        conf.engine +
                        '?v=' +
                        conf.v,
                    type: 'get',
                    data: {
                        'invalid_ie_cache': new Date().getTime()
                    },
                    dataType: 'html',
                    success: function (r) {
                        var result;
                        try {
                            result = JSON.parse(r);
                        } catch (e) {
                            result = {'code': 'err'};
                        }
                        if (result.code === 401) {
                            self.notify('登录失效', '登录已失效，请重新登录', function () {
                                window.location.reload();
                                window.location.hash = '';
                            });
                            loadBar.finish();
                            return;
                        }
                        if (result.code === 403) {
                            self.tab.change('/403');
                            loadBar.finish();
                            return;
                        }
                        if (result.code === 404) {
                            self.tab.change('/404');
                            loadBar.finish();
                            return;
                        }
                        if (result.code === 500) {
                            self.tab.change('/500');
                            loadBar.finish();
                            return;
                        }
                        callback({html: r, url: url});
                        loadBar.finish()
                    },
                    error: function (res) {
                        if (res.status === 404) {
                            self.tab.change('/404');
                        }
                        if (res.status === 403) {
                            self.tab.change('/403');
                        }
                        if (res.status === 500) {
                            self.tab.change('/500');
                        }
                        self.log(
                            '请求视图文件异常\n文件路径：' + url + '\n状态：' + res.status
                        );
                        loadBar.error();
                    }
                })
            };

            self.tab = {
                isInit: false,
                data: [],
                tabMenuTplId: 'TPL-app-tabsmenu',
                minLeft: null,
                maxLeft: null,
                wrap: '.febs-tabs-wrap',
                menu: '.febs-tabs-menu',
                next: '.febs-tabs-next',
                prev: '.febs-tabs-prev',
                step: 200,
                init: function () {
                    var tab = this;
                    var btnCls = tab.wrap + ' .febs-tabs-btn';

                    layui.dropdown.render({
                        elem: '.febs-tabs-down',
                        click: function (name) {
                            if (name === 'all') {
                                tab.delAll();
                            }
                            if (name === 'other') {
                                tab.delOther();
                            }
                            if (name === 'current') {
                                tab.del(layui.febs.route.fileurl);
                            }
                            if (name === 'refresh') {
                                tab.refresh();
                            }
                        },
                        options: [
                            {
                                name: 'current',
                                title: '关闭当前选项卡'
                            },
                            {
                                name: 'other',
                                title: '关闭其他选项卡'
                            },
                            {
                                name: 'all',
                                title: '关闭所有选项卡'
                            },
                            {
                                name: 'refresh',
                                title: '刷新当前选项卡'
                            }
                        ]
                    });

                    $(document).on('click', btnCls, function (e) {
                        var url = $(this).attr('lay-url');
                        if ($(e.target).hasClass('febs-tabs-close')) {
                            tab.del(url)
                        } else {
                            var type = $(this).attr('data-type');
                            if (type === 'page') {
                                tab.change(tab.has(url))
                            } else if (type === 'prev' || type === 'next') {
                                tab.menuElem = $(tab.menu);
                                var menu = tab.menuElem;
                                tab.minLeft = tab.minLeft || parseInt(menu.css('left'));
                                tab.maxLeft = tab.maxLeft || $(tab.next).offset().left;

                                var left = 0;
                                if (type === 'prev') {
                                    left = parseInt(menu.css('left')) + tab.step;
                                    if (left >= tab.minLeft) left = tab.minLeft
                                } else {
                                    left = parseInt(menu.css('left')) - tab.step;
                                    var last = menu.find('li:last');
                                    if (last.offset().left + last.width() < tab.maxLeft) return
                                }
                                menu.css('left', left)
                            }
                        }
                    });

                    $('.febs-tabs-hidden').addClass('layui-show');
                    this.isInit = true
                },
                has: function (url) {
                    var exists = false;
                    layui.each(this.data, function (i, data) {
                        if (data.fileurl === url) return (exists = data)
                    });
                    return exists
                },
                delAll: function (type) {
                    var tab = this;
                    var menuBtnClas = tab.menu + ' .febs-tabs-btn';
                    $(menuBtnClas).each(function () {
                        var url = $(this).attr('lay-url');
                        if (url === conf.entry) return true;
                        tab.del(url)
                    })
                },
                delOther: function () {
                    var tab = this;
                    var menuBtnClas = tab.menu + ' .febs-tabs-btn';
                    $(menuBtnClas + '.febs-tabs-active')
                        .siblings()
                        .each(function () {
                            var url = $(this).attr('lay-url');
                            tab.del(url)
                        })
                },
                del: function (url, backgroundDel) {
                    var tab = this;
                    if (tab.data.length <= 1 && backgroundDel === undefined) return;
                    layui.each(tab.data, function (i, data) {
                        if (data.fileurl === url) {
                            tab.data.splice(i, 1);
                            return true
                        }
                    });

                    var lay = '[lay-url="' + url + '"]';
                    var thisBody = $(
                        '#' + conf.containerBody + ' > .febs-tabs-item' + lay
                    );
                    var thisMenu = $(this.menu).find(lay);
                    thisMenu.remove();
                    thisBody.remove();

                    if (backgroundDel === undefined) {
                        if (thisMenu.hasClass('febs-tabs-active')) {
                            $(this.menu + ' li:last').click()
                        }
                    }
                },
                refresh: function (url) {
                    url = url || layui.febs.route.fileurl;
                    if (this.has(url)) {
                        this.del(url, true);
                        self.renderTabs(url)
                    }
                },
                clear: function () {
                    this.data = [];
                    this.isInit = false;
                    $(document).off('click', this.wrap + ' .febs-tabs-btn')
                },
                change: function (route, callback) {
                    if (typeof route == 'string') {
                        route = layui.router('#' + route);
                        route.fileurl = '/' + route.path.join('/')
                    }
                    var fileurl = route.fileurl;
                    var tab = this;
                    if (tab.isInit === false) tab.init();

                    var changeView = function (lay) {
                        $('#' + conf.containerBody + ' > .febs-tabs-item' + lay)
                            .show()
                            .siblings()
                            .hide()
                    };

                    var lay = '[lay-url="' + fileurl + '"]';

                    var activeCls = 'febs-tabs-active';

                    var existsTab = tab.has(fileurl);
                    if (existsTab) {
                        var menu = $(this.menu);
                        var currentMenu = menu.find(lay);

                        if (existsTab.href !== route.href) {
                            tab.del(existsTab.fileurl, true);
                            tab.change(route);
                            return false
                            //tab.del(route.fileurl)
                        }
                        currentMenu
                            .addClass(activeCls)
                            .siblings()
                            .removeClass(activeCls);

                        changeView(lay);

                        this.minLeft = this.minLeft || parseInt(menu.css('left'));

                        var offsetLeft = currentMenu.offset().left;
                        if (offsetLeft - this.minLeft - $(this.next).width() < 0) {
                            $(this.prev).click()
                        } else if (offsetLeft - this.minLeft > menu.width() * 0.5) {
                            $(this.next).click()
                        }
                        $(document).scrollTop(-100);

                        layui.febs.navigate(route.href)
                    } else {
                        self.loadHtml(fileurl, function (res) {
                            var htmlElem = $(
                                "<div><div class='febs-tabs-item' lay-url='" +
                                fileurl +
                                "'>" +
                                res.html +
                                '</div></div>'
                            );
                            var params = self.fillHtml(fileurl, htmlElem, 'prepend');
                            route.title = params.title;
                            tab.data.push(route);
                            layui.febs.render(tab.tabMenuTplId);

                            var currentMenu = $(tab.menu + ' ' + lay);
                            currentMenu.addClass(activeCls);

                            changeView(lay);

                            if ($.isFunction(callback)) callback(params)
                        })
                    }
                    layui.febs.sidebarFocus(route.href);
                    return false
                },
                onChange: function () {
                }
            };

            self.fillHtml = function (url, htmlElem, modeName) {
                var fluid = htmlElem.find('.layui-fluid[lay-title]');
                var title = '';
                if (fluid.length > 0) {
                    title = fluid.attr('lay-title');
                    // self.setTitle(title)
                }

                var container = self.containerBody || self.container;
                container[modeName](htmlElem.html());
                if (modeName === 'prepend') {
                    self.parse(container.children('[lay-url="' + url + '"]'))
                } else {
                    self.parse(container)
                }
                return {title: title, url: url, htmlElem: htmlElem}
            };
            //解析普通文件
            self.render = function (fileurl, callback) {
                self.loadHtml(fileurl, function (res) {
                    var htmlElem = $('<div>' + res.html + '</div>');
                    var params = self.fillHtml(res.url, htmlElem, 'html');
                    if ($.isFunction(callback)) callback(params)
                })
            };
            //加载 tab
            self.renderTabs = function (route, callback) {
                var tab = self.tab;
                tab.change(route, callback)
            };
            //加载layout文件
            self.renderLayout = function (callback, url) {
                if (url === undefined) url = 'layout';
                self.containerBody = null;

                self.render(url, function (res) {
                    self.containerBody = $('#' + conf.containerBody);
                    if (conf.viewTabs === true) {
                        self.containerBody.addClass('febs-tabs-body')
                    }
                    layui.febs.appBody = self.containerBody;
                    if ($.isFunction(callback)) callback()
                })
            };
            //加载单页面
            self.renderIndPage = function (fileurl, callback) {
                self.renderLayout(function () {
                    self.containerBody = null;
                    if ($.isFunction(callback)) callback()
                }, fileurl)
            };
            self.log = function (msg, type) {
                if (conf.debug === false) return;
                if (type === undefined) type = 'error';
                console.error(msg)
            };
            self.createRequestParams = function (params) {
                var success = params.success;
                var error = params.error;

                if (params.api) {
                    if (!layui.api[params.api]) {
                        self.log('请求错误 api.' + params.api + ' 不存在');
                        return
                    }
                    params.url = conf.requestUrl + layui.api[params.api]
                } else if (params.url) {
                    params.url = conf.requestUrl + params.url
                }

                var defaultParams = {
                    timeout: 5000,
                    type: 'get',
                    dataType: 'json',
                    headers: conf.requestHeaders || {},
                    success: function (res) {
                        if ($.isFunction(success)) success(res)
                    },
                    error: function (res) {
                        if (res.status === conf.logoutHttpCode) {
                            // do nothing
                        } else {
                            layer.msg('请检查您的网络连接');
                            self.log('请检查您的网络连接，错误信息：' + JSON.stringify(res))
                        }

                        if ($.isFunction(error)) error(res)
                    }
                };

                if (self.ie8) {
                    if (conf.debug) $.support.cors = true;
                    defaultParams.cache = false
                }
                delete params.success;
                delete params.error;

                return $.extend(defaultParams, params)
            };
            self.request = function (params) {
                params = self.createRequestParams(params);
                $.ajax(params)
            };
            exports('view', self)
        }
    );
