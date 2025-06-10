import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue'
import login from '../views/account/login.vue';

Vue.use(VueRouter)

const routes = [
	// 主页ss
	{
		path: '/',
		name: 'index',
		component: index
	},
	// 登录
	{
		path: '/account/login',
		name: 'login',
		component: login
	},
	// 忘记密码
	{
		path: '/account/forgot',
		name: 'forgot',
		component: () => import('../views/account/forgot.vue')
	},
	// 注册账号
	{
		path: '/account/register',
		name: 'register',
		component: () => import('../views/account/register.vue')
	},
	// 媒体图片
	{
		path: '/media/image',
		name: 'media_image',
		component: () => import('../views/media/image.vue')
	},
	// 音乐
	{
		path: '/media/music',
		name: 'media_music',
		component: () => import('../views/media/music.vue')
	},
	// 媒体视频
	{
		path: '/media/video',
		name: 'media_video',
		component: () => import('../views/media/video.vue')
	},
	// 文章路由
	{
		path: '/article/list',
		name: 'article_list',
		component: () => import('../views/article/list.vue')
	},
	{
		path: '/article/details',
		name: 'article_details',
		component: () => import('../views/article/details.vue')
	},
	// 浏览网站
	// 公告路由
	{
		path: '/notice/list',
		name: 'notice_list',
		component: () => import('../views/notice/list.vue')
	},
	{
		path: '/notice/details',
		name: 'notice_details',
		component: () => import('../views/notice/details.vue')
	},
	//项目申报添加路由
	{
		path: '/project_declaration/edit',
		name: '/project_declaration_edit',
		component: () => import('../views/project_declaration/edit.vue')
	},
	//项目申报列表路由
	{
		path: '/project_declaration/list',
		name: '/project_declaration_list',
		component: () => import('../views/project_declaration/list.vue')
	},
	//项目租队列表路由
	{
		path: '/project_rental_team/list',
		name: '/project_rental_team_list',
		component: () => import('../views/project_rental_team/list.vue')
	},
	//项目租队详情路由
	{
		path: '/project_rental_team/details',
		name: '/project_rental_team_details',
		component: () => import('../views/project_rental_team/details.vue')
	},
	//项目共享列表路由
	{
		path: '/project_sharing/list',
		name: '/project_sharing_list',
		component: () => import('../views/project_sharing/list.vue')
	},
	//项目共享详情路由
	{
		path: '/project_sharing/details',
		name: '/project_sharing_details',
		component: () => import('../views/project_sharing/details.vue')
	},

	// 用户路由
	{
		path: '/user/index',
		name: 'user_index',
		component: () => import('../views/user/index.vue')
	},
	// 基本信息
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue')
	},
	// 找回密码
	{
		path: '/user/password',
		name: 'user_password',
		component: () => import('../views/user/password.vue')
	},

	// 搜索
	{
		path: '/search',
		name: 'search',
		component: () => import('../views/search/index.vue')
	},
	// 局部搜索
	{
		path: '/search/details',
		name: 'search_details',
		component: () => import('../views/search/details.vue')
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.afterEach((to, from, next) => {
	let title = "大学生创新创业项目管理系统-home";
	document.title = title;
	document.logo = "大学生创新创业项目管理系统"
})

export default router
