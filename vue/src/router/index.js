import Vue from 'vue' 
import Router from 'vue-router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import Home from '../views/backstage/Home.vue'
import Login from '../views/login.vue'
import Register from '../views/register.vue'
import FrontHome from '../views/front/Home.vue'
Vue.use(Router)
Vue.use(Element)
const routr = new Router({

	routes:[
	{
		path:'/backstage/home',
		name:'home',
		component:Home,
	},
	{
		path:'/register',
		name:'register',
		component:Register,
	},
	{
		path:'/login',
		name:'login',
		component:Login,
	},
	{
		path:'/',
		name:'Home',
		component:FrontHome,
	},
	],

});

export default routr;