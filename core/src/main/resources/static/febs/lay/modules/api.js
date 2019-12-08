layui.define([],function(exports){
    exports('api',{
        getMenus: 'menu/' + currentUser.username + '?invalid_ie_cache=' + new Date().getTime()
    });
});