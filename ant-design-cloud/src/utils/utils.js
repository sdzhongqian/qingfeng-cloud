exports.install = function (Vue, options) {
    Vue.prototype.$test = function () { //全局函数1
        return '全局参数测试';
    };
};
