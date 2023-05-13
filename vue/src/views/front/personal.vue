<template>
	<div>
		<div>
			<el-card class="box-card-1">
				<div >
					<div class="user_image">
						<el-avatar :size="80" :src="circleUrl"></el-avatar>
					</div>
					<div class="user_font">Hi!</div>
					<div class="user_font">
						<el-link  v-if="user" :underline="false" @click="send(module.FrontUserfile)">{{user.username}}</el-link>
						<span v-else>请登录</span>
					</div>
					<div class="user_font_time">
						上一次登录时间为：				
						<span v-if="user">{{user.old_logintime}}</span>
						<span v-else></span>
					</div>
				</div>
			</el-card>
		</div>
		<div>
			<el-card class="box-card-2">
				<div>
					<span>我的订单</span>
					<el-link :underline="false" style="margin-left: 640px;" @click="send_order(order_name.name_0)">
						全部
						<i class="el-icon-arrow-right" style="font-size: 12px;"></i>
					</el-link>
				</div>
				<div class="order">
					<div class="order-font" @click="send_order(order_name.name_1)">
						<el-badge :value="OrderNum[0]" :max="99" class="item">
							<i class="el-icon-bank-card" style="font-size: 40px;margin-left: 3px;"></i>
						</el-badge>
						<div class="order-font-text">待付款</div>
					</div>
					<div class="order-font" @click="send_order(order_name.name_2)">
						<el-badge :value="OrderNum[1]" :max="99" class="item">
							<i class="el-icon-box" style="font-size: 40px;margin-left: 3px;"></i>
						</el-badge>
						<div class="order-font-text">待发货</div>
					</div>
					<div class="order-font" @click="send_order(order_name.name_3)">
						<el-badge :value="OrderNum[2]" :max="99" class="item">
							<i class="el-icon-truck" style="font-size: 40px;margin-left: 3px;"></i>
						</el-badge>
						<div class="order-font-text">待收货</div>
					</div>
					<div class="order-font" @click="send_order(order_name.name_4)">
						<el-badge :value="OrderNum[3]" :max="99" class="item">
							<i class="el-icon-chat-dot-round" style="font-size: 40px;margin-left: 3px;"></i>
						</el-badge>
						<div class="order-font-text">待评价</div>
					</div>
					<div class="order-font" @click="send_order(order_name.name_5)">
						<el-badge :value="OrderNum[4]" :max="99" class="item">
							<i class="el-icon-refresh-right" style="font-size: 40px;margin-left: 3px;"></i>
						</el-badge>
						<div class="order-font-text">待售后</div>
					</div>
				</div>
			</el-card>
		</div>
		<div>
			<el-card class="box-card-3">
				<div>
					<span>其他服务</span>
				</div>
				<div class="order">
					
					<div class="order-font" @click="send(module.FrontUserfile)">
						<i class="el-icon-user" style="font-size: 40px;margin-left: 12px;"></i>
						<div class="order-font-text">个人信息</div>
					</div>
					
					<div class="order-font" @click="send(module.FrontMyselect)">
						<el-badge :value="CollectNum" :max="99" class="item">
							<i class="el-icon-collection-tag" style="font-size: 40px;margin-left: 12px;"></i>
						</el-badge>
						<div class="order-font-text">我的收藏</div>
					</div>
					
					<div class="order-font" @click="backstage()" v-if="user.power!=0">
						<i class="el-icon-office-building" style="font-size: 40px;margin-left: 12px;"></i>
						<div class="order-font-text">进入后台</div>
					</div>
				</div>
			</el-card>
		</div>
	</div>
</template>

<script>
	export default {
		created(){
			this.load();
			this.myselect();
			this.order_num();
		},
		data() {
			return {
				user:null,
				CollectNum:null,
				OrderNum:[],
				module:{
					FrontUserfile:"FrontUserfile",
					FrontMyselect:"FrontMyselect",
				},
				order:{
					FrontOrder:"FrontOrder",
					order_name:null
				},
				order_name:{
					name_0:"全部订单",
					name_1:"待付款",
					name_2:"待发货",
					name_3:"待收货",
					name_4:"待评价",
					name_5:"待售后"
				},
				circleUrl:null,
			}
		},
		methods:{
			load(){	//显示个人信息
				this.user=this.$cookies.get("user");
				this.circleUrl=require("@/assets/images/user_imges/" +this.user.image)
			},
			send(data){	//切换组件
				this.$emit('jump',data);
			},
			send_order(data){
				this.order.order_name=data;
				this.$emit('order',this.order);
			},
			myselect(){	//我的收藏的数量
				this.$axios.post('http://localhost:8081/TotalCollect', {
					user_id:this.user.userid
				}).then(res => {
					this.CollectNum = res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			order_num(){
				//获取订单数量
				for (let i= 0; i<5; i++) {
					this.$axios.post('http://localhost:8081/OrderTotal', {
						user_id:this.user.userid,
						ord_status:i
					}).then(res => {
						this.OrderNum.push(res.data);
						}
					).catch(error => {
						console.log(error, '请求失败');
					})
				}
			},
			backstage(){	//进入后台
				this.$router.push({
					path:"/backstage/home",
				});
			}
		},
	}
</script>

<style scoped>
	.box-card-1{
		width: 300px;
		height: 320px;
		float: left;
	}
	.box-card-2{
		width: 800px;
		margin-left: 350px;
		height: 150px;
	}
	.box-card-3{
		width: 800px;
		margin-left: 350px;
		height: 150px;
		margin-top: 20px;
	}
	.user_image{
		margin-top: 30px;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.user_font{
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 20px;
	}
	.user_font_time{
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 20px;
		font-size: 12px;
	}
	.order{
		margin-left: 80px;
	}
	.order-image{
		display: flex;
		align-items: center;
	}
	.order-font{
		height: 60px;
		width: 80px;
		float: left;
		margin-right: 30px;
		margin-top: 30px;
	}
	.order-font-text{
		margin-top: 10px;
	}
</style>