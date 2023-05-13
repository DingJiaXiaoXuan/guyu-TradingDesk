<template>
	<div>
		<div class="car">
			<el-carousel height="400px">
				<el-carousel-item v-for="(item, index) in usetableData" :key="index" >
					<img :src="imgformat(item.slide_imge)" style="width: 1200px;height: 400px;">
				</el-carousel-item>
			</el-carousel>
		</div>
		<div style="width: 1160px;height: 360px;">
			<div style="height: 40px;margin-top: 5px;margin-bottom: 5px;">
				<div style="float: left;font-size: 32px;">
					芒果
				</div>
				<div style="margin-left: 1100px;">
					更多<i class="el-icon-arrow-right" style="font-size: 18px;"></i>
				</div>
			</div>
			<div class="Card"  v-for="(item, index) in protableData.data_1" :key="index" >
				<el-card :body-style="{ padding: '0px' }" shadow="hover">
					<img :src="imgformat_pro(item.image)" class="image">
					<div style="padding: 14px;">
						<span class="card_name">{{item.pro_name}}</span>
							<div>
							<time class="time">{{ item.store.store_name }}</time>
							<el-button type="text" class="button"  @click="submit(item)">查看商品</el-button>
							</div>
						</div>
				</el-card>
			</div>
		</div>
		<div style="width: 1160px;height: 360px;">
			<div style="height: 40px;margin-top: 5px;margin-bottom: 5px;">
				<div style="float: left;font-size: 32px;">
					橙子
				</div>
				<div style="margin-left: 1100px;">
					更多<i class="el-icon-arrow-right" style="font-size: 18px;"></i>
				</div>
			</div>
			<div class="Card"  v-for="(item, index) in protableData.data_2" :key="index" >
				<el-card :body-style="{ padding: '0px' }" shadow="hover">
					<img :src="imgformat_pro(item.image)" class="image">
					<div style="padding: 14px;">
						<span class="card_name">{{item.pro_name}}</span>
							<div>
							<time class="time">{{ item.store.store_name }}</time>
							<el-button type="text" class="button"  @click="submit(item)">查看商品</el-button>
							</div>
						</div>
				</el-card>
			</div>
		</div>
		<div style="width: 1160px;height: 360px;">
			<div style="height: 40px;margin-top: 5px;margin-bottom: 5px;">
				<div style="float: left;font-size: 32px;">
					荔枝
				</div>
				<div style="margin-left: 1100px;">
					更多<i class="el-icon-arrow-right" style="font-size: 18px;"></i>
				</div>
			</div>
			<div class="Card"  v-for="(item, index) in protableData.data_3" :key="index" >
				<el-card :body-style="{ padding: '0px' }" shadow="hover">
					<img :src="imgformat_pro(item.image)" class="image">
					<div style="padding: 14px;">
						<span class="card_name">{{item.pro_name}}</span>
							<div>
							<time class="time">{{ item.store.store_name }}</time>
							<el-button type="text" class="button"  @click="submit(item)">查看商品</el-button>
							</div>
						</div>
				</el-card>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name:"front_index",
		created(){
			this.useload();	
			this.pro_load();
			
		},
		data() {
			return {
				usetableData:[],
				protableData:[],
				module:{
					pro_id:null,
					pro:"FrontProduct",
				},
			}
		},
		methods:{
			useload(){		//在用轮播图
				this.$axios.post('http://localhost:8081/SlideshowFind', {
				}).then(res => {
					this.usetableData = res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			pro_load(){		//推荐商品
				this.$axios.post('http://localhost:8081/RecommendPro', {
				}).then(res => {
					this.protableData = res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			imgformat(row) { //图片显示
				let img = require("@/assets/images/" +row);
				return img;
			},
			imgformat_pro(row) { //图片显示
				let img = require("@/assets/images/pro_imges/" +row);
				return img;
			},
			submit(data){ //进入商品详情界面
				this.module.data=data.pro_id;
				this.$emit('fatherEvent',this.module);
			},
		},
	}
	
</script>

<style scoped>
	.car{
		margin-bottom: 20px;
	}
	.block{
		width: 1200px;
	}
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
	.time {
		font-size: 13px;
		color: #999;
	}  
	.button {
		padding: 0;
		float: right;
	}
	.card_name{
		font-size: 15px;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
		overflow: hidden;
	}
</style>