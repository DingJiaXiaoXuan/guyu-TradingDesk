<template>
	<div>
		<el-container class="container">
			<el-aside width="200px" class="aside"><app-aside class="aside-menu" @receive="getSend"></app-aside></el-aside>
			<el-container>
				<el-header style="background-color: #b3c0d1;" ><AppHeader class="header"></AppHeader></el-header>
				<el-main>
					<Component 
						:is="componentShow"
					/>
				</el-main>
			</el-container>
		</el-container>
	</div>
</template>

<style scoped>
	.aside{
		background-color: #545c64;
	}
	.container{
		position: fixed;
		left: 0;
		right: 0;
		top: 0;
		bottom: 0;
	}
	.aside-menu{
		height: 100%;
	}
	.header{
		text-align: right;
		height: 60px;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
</style>

<script>
	import AppAside from './SideBar'
	import AppHeader from './header'
	import AppUserManage from './Body/UserManage.vue'
	import AppHomeManage from './Body/HomeManage.vue'
	import AppProductManage from'./Body/ProductManage.vue'
	import AppStoreManage  from'./Body/StoreManage.vue'
	import AppCateManage  from './Body/CateManage.vue'
	import AppReviewManage from './Body/ReviewManage.vue'
	import AppSlideshowManage from './Body/SlideshowManage.vue'
	import AppOrderManage from './Body/OrderManage.vue'
	import AppNoticeManage from './Body/NoticeManage.vue'
	import AppProductAuditManage from './Body/ProductAuditManage.vue'
	import AppStoreAuditManage from './Body/StoreAuditManage.vue'
	export default {
		components:{
			AppAside,
			AppHeader,
			AppUserManage,
			AppHomeManage,
			AppProductManage,
			AppStoreManage,
			AppCateManage,
			AppReviewManage,
			AppSlideshowManage,
			AppOrderManage,
			AppNoticeManage,
			AppProductAuditManage,
			AppStoreAuditManage,
		},
		data() {
			return {
				componentShow:AppHomeManage,
				store_id:null,
			}
		},
		created(){
		},
		methods:{
			getSend(data){
				this.componentShow=data
			},
		}
	};
</script>

