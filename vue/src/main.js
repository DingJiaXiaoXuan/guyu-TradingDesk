import Vue from 'vue'
import App from './App.vue'
import router from './router/index.js'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css'
import './plugins/axios'
//把axios实例化后引入main.js文件。挂载在axios原型链上全局使用。
import http from "./utils/http";
import VueSession from 'vue-session'
import VueCookies from 'vue-cookies'
Vue.use(VueCookies);
Vue.prototype.$ajax = http
Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(VueSession)
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
