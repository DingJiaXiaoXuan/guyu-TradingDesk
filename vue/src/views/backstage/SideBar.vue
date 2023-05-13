<template>
    <div >
		<el-menu
              default-active="1"
              class="el-menu-vertical-demo"
				background-color="#545c64"
				text-color="#fff"
				>
			<el-menu-item index="1" @click="send(module.home)">
				<i class="el-icon-house"></i>
				<span slot="title">首页</span>
			</el-menu-item>
			<el-menu-item index="2" @click="send(module.slideshow)" v-if="user.power==2">
				<i class="el-icon-picture-outline"></i>
				<span slot="title">首页轮播图管理</span>
			</el-menu-item>
			<el-menu-item index="3"  @click="send(module.user)" v-if="user.power==2">
				<i class="el-icon-user"></i>
				<span slot="title">用户管理</span>
			</el-menu-item>
			<el-submenu index="4">
				<template slot="title">
					<i class="el-icon-goods"></i>
					<span>商品管理</span>
				</template>
				<el-menu-item-group>
					<el-menu-item index="4-1" @click="send(module.cate)" v-if="user.power==2">商品类别</el-menu-item>
					<el-menu-item index="4-2" @click="send(module.product)">商品信息</el-menu-item>
					<el-menu-item index="4-3" @click="send(module.productAudit)" v-if="user.power==2">商品审核</el-menu-item>
				</el-menu-item-group>
			</el-submenu>
			<el-menu-item index="5" @click="send(module.order)">
				<i class="el-icon-document"></i>
				<span slot="title">订单管理</span>
			</el-menu-item>
			<el-submenu index="6">
				<template slot="title">
					<i class="el-icon-school"></i>
					<span>店铺管理</span>
				</template>
				<el-menu-item-group>
					<el-menu-item index="6-1" @click="send(module.store)">店铺信息</el-menu-item>
					<el-menu-item index="6-2" @click="send(module.storeAudit)" v-if="user.power==2">店铺审核</el-menu-item>
				</el-menu-item-group>
			</el-submenu>
			<el-menu-item index="7"  @click="send(module.review)" v-if="user.power==2">
				<i class="el-icon-chat-dot-square"></i>
				<span slot="title">评论管理</span>
			</el-menu-item>
			<el-menu-item index="8" @click="send(module.notice)" v-if="user.power==2">
				<i class="el-icon-chat-dot-square"></i>
				<span slot="title">公告管理</span>
			</el-menu-item>
        </el-menu>
    </div>

</template>

<!--加上scoped能够防止样式之间的冲突-->
<style scoped>
  .el-menu {
    border-right: none;
  }
</style>

<script>
    export default {
		created(){
			this.user=this.$cookies.get("user");
		},
		data(){
			return{
				user:{},
				store:{},
				module:{
					home:'AppHomeManage',
					user:'AppUserManage',
					product:'AppProductManage',
					store:'AppStoreManage',
					cate:'AppCateManage',
					review:'AppReviewManage',
					slideshow:'AppSlideshowManage',
					order:'AppOrderManage',
					notice:'AppNoticeManage',
					productAudit:'AppProductAuditManage',
					storeAudit:'AppStoreAuditManage',
				}
			}
		},
        methods: {
           send(data){	//切换组件
				this.$emit('receive',data);
			},
        }
    }
</script>

