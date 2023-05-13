<template>
	<div>
		<div>
			<el-card style="height: 120px;background-image: linear-gradient(180deg,#fff,#efffef)">
				<div style="float: left;margin-right: 10px;margin-left: 20px;margin-right: 20px;">
					<el-image style="width: 80px; height: 80px" :src="imgformat_store(store.store_imge)"></el-image>
				</div>
				<div style="font-size: 26px;">{{store.store_name}}</div>
				<div style="font-size: 18px;margin-top: 10px;">{{store.store_title}}</div>
				<div style="font-size: 16px;margin-top: 5px;">{{store.store_site}}</div>
			</el-card>
		</div>
		<div style="width: 1160px;margin-top: 20px;">
			<div class="Card"  v-for="(item, index) in protableData" :key="index" >
				<el-card :body-style="{ padding: '0px' }" shadow="hover">
					<img :src="imgformat_pro(item.image)" class="image">
					<div style="padding: 14px;">
						<span class="card_name">{{item.pro_name}}</span>
							<div class="bottom clearfix">
							<time class="time">{{ item.store.store_name }}</time>
							<el-button type="text" class="button"  @click="submit(item)">查看商品</el-button>
							</div>
						</div>
				</el-card>
			</div>
		</div>
		<div style="padding-top: 20px;clear:both;height: 60px;margin-left: 450px;">
			<el-pagination
					background
					@current-change="handleCurrentChange"
					:current-page="productUtil.page.pageNum"
					:page-sizes="[5,10,15,20]"
					:page-size="productUtil.page.count"
					layout="total,prev, pager, next, jumper"
					:total="total">
			</el-pagination>
		</div>
	</div>
</template>

<script>
	export default {
		props:{
			store_id:{}
		},
		created(){
			this.store_load();
			this.load_pro();
		},
		data() {
			return {
				store:{},
				protableData:null,
				total:0,
				url:require('../../assets/images/user.png'),
				productUtil:{
					page:{
						pageNum:1,
						count:25
					},
					product:{
						store_id:null,
					}
				},
				module:{
					pro_id:null,
					pro:"FrontProduct",
				},
			}
		},
		methods:{
			store_load(){			//商品信息
				this.$axios.post('http://localhost:8081/FindStore', {
					store_id:this.store_id
				}).then(res => {
					this.store = res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			handleCurrentChange(pageNum){	//换页
				this.productUtil.page.pageNum=pageNum;
				this.load_pro();
			},
			submit(data){ //进入商品详情界面
				this.module.data=data.pro_id;
				this.$emit('fatherEvent',this.module);
			},
			load_pro(){		//请求商品数据
				this.productUtil.product.store_id=this.store_id;
				this.$axios.post('http://localhost:8081/StorePro',this.productUtil).then(res => {
					this.protableData = res.data.data;
					this.total = res.data.countSun;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			imgformat_pro(row) { //图片显示
				let img = require("@/assets/images/pro_imges/" +row);
				return img;
			},
			imgformat_store(row) { //图片显示
				let img = require("@/assets/images/store_imges/" +row);
				return img;
			},
		},
	}
</script>

<style scoped>
	.Card{
		float: left;
		width: 210px;
		height: 300px;
		margin: 10px;
	}
	.image{
		width: 210px;
		height: 210px;
	}
	.card_name{
		font-size: 15px;
		display: -webkit-box;
		-webkit-line-clamp: 1;
		-webkit-box-orient: vertical;
		overflow: hidden;
	}
	.button {
		padding: 0;
		float: right;
	}
	.time {
		font-size: 13px;
		color: #999;
	}  
</style>