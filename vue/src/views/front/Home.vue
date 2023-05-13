<template>
	<div class="Home">
		<el-container >
			<el-header class="header" height="300px"><FrontHeader @header="getSend" @cate_mian="send_Main"></FrontHeader></el-header>
			<el-main class="main">
				<Component 
					:is="componentShow" 
					:pro_id="pro_id" 
					@fatherEvent="btnclick" 
					:main="cate_main" 
					@jump="getSend"
					:order_name="order_name"
					@order="orderclick"
					@store="send_store"
					:store_id="store_id"
				/>
			</el-main>
			<el-footer></el-footer>
		</el-container>
	</div>
</template>
<script>
	import FrontHeader from './header.vue'
	import FrontIndex  from './index.vue'
	import FrontProduct from './product.vue'
	import FrontCard	from './card.vue'
	import FrontAllPro	from './allproduct.vue'
	import FrontPersonal from './personal.vue'
	import FrontUserfile from './userfile.vue'
	import FrontMyselect from './myselect.vue'
	import FrontNotice from './notice.vue'
	import FrontOrder from './order.vue'
	import FrontStore  from './store.vue'
	export default {
		components:{
			FrontHeader,
			FrontIndex,
			FrontProduct,
			FrontCard,
			FrontAllPro,
			FrontPersonal,
			FrontUserfile,
			FrontMyselect,
			FrontNotice,
			FrontOrder,
			FrontStore,
		},
		data() {
			return {
				componentShow:FrontIndex,
				pro_id:null,
				cate_main:null,
				order_name:null,
				store_id:null,
			}
		},
		created(){

		},
		methods:{
			send_store(data){
				this.componentShow=data.FrontStore;
				this.store_id=data.store_id;
			},
			getSend(data){
				this.componentShow=data;
			},
			send_Main(data){
				this.componentShow=data.Allpro;
				this.cate_main=data.data;
			},
			btnclick(data){
				this.componentShow=data.pro;
				this.pro_id=data.data;
			},
			orderclick(data){
				this.componentShow=data.FrontOrder;
				this.order_name=data.order_name;
			}
		}
	};
</script>
<style scoped>
	.Home{
		width: 1200px;
		margin-left: 26%;
	}
	.header{
		width: 1200px;
		height: 300px;
	}
	.main{
		margin-left: 20px;
		width: 1200px;
	}


</style>