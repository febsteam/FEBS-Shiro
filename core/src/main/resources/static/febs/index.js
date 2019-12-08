layui.extend({
    febs: 'lay/modules/febs',
    validate: 'lay/modules/validate'
}).define(['febs', 'conf'], function (exports) {
    layui.febs.initPage();
    console.log("\n %c FEBS-Shiro 2.0 %c https://github.com/wuyouzhuguli/FEBS-Shiro %c 如果该项目对您有帮助的话，还请点个star给予精神支持！🐤", "color: #fff; font-size: .84rem;background: #366ed8; padding:5px 0;", "font-size: .84rem;background: #fff; border: 2px solid #b0e0a8;border-left: none; padding:3px 0;"," font-size: .84rem;background: #fcf9ec; padding:5px 0;margin-left: 8px");
    exports('index', {});
});