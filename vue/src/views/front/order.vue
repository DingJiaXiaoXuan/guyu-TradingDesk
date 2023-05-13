<template>
	<div>
		<div class="bread">
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item>
					<el-link :underline="false" @click="send(FrontPersonal)">个人服务</el-link>
				</el-breadcrumb-item>
				<el-breadcrumb-item>{{order_name}}</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div style="margin-top: 20px;">
			<el-card>
				<el-table :data="tableData" stripe style="width: 100%">
					<el-table-column label="商品" width="350">
						<template slot-scope="scope">
							<div style="float: left;">
								<img :src="imgformat_pro(scope.row.product.image)" style="height: 100px;width: 100px;">
							</div>
							<div style="margin-left: 110px;">
								<div style="height: 50px;">
									<el-link :underline="false" @click="submit(scope.row)">{{scope.row.product.pro_name}}</el-link>	
								</div>
								<div style="height: 50px;font-size: 12px;color: #999;">
									<span>购买规格:</span><br />
									<span>{{scope.row.size.size_title}}</span>
								</div>
							</div>
						</template>
					</el-table-column>
					<el-table-column label="单价" width="125">
						<template slot-scope="scope">
							{{scope.row.size.pro_price}}元
						</template>
					</el-table-column>
					<el-table-column label="数量" width="125">
						<template slot-scope="scope">
							×{{scope.row.pro_num}}
						</template>
					</el-table-column>
					<el-table-column label="实付金额" width="150">
						<template slot-scope="scope">
							￥{{scope.row.money}}
						</template>
					</el-table-column>
					<el-table-column label="实付金额" width="150">
						<template slot-scope="scope">
							<el-tag v-if="scope.row.ord_status==0">待付款</el-tag>
							<el-tag v-if="scope.row.ord_status==1" type="success">待发货</el-tag>
							<el-tag v-if="scope.row.ord_status==2" type="danger">待收货</el-tag>
							<el-tag v-if="scope.row.ord_status==3" type="warning">待评价</el-tag>
							<el-tag v-if="scope.row.ord_status==4" type="info">待售后</el-tag>
							<el-tag v-if="scope.row.ord_status==5">交易成功</el-tag>
							<el-tag v-if="scope.row.ord_status==6">交易关闭</el-tag>
							<el-tag v-if="scope.row.ord_status==7">退款中</el-tag>
							<el-tag v-if="scope.row.ord_status==8">退款成功</el-tag>
							<el-tag v-if="scope.row.ord_status==9">退款失败</el-tag>
							<el-tag v-if="scope.row.ord_status==10">退货</el-tag>
							<el-tag v-if="scope.row.ord_status==11">退货成功</el-tag>
						</template>
					</el-table-column>
					<el-table-column label="操作" width="210">
						<template slot-scope="scope">
							<div style="margin-top: 5px;">
								<el-link :underline="false">查看详情</el-link>
								<el-link :underline="false" v-if="scope.row.ord_status==0" style="margin-left: 5px;">付款</el-link>
								<el-link :underline="false" v-if="scope.row.ord_status==1" style="margin-left: 5px;">催发货</el-link>
								<el-link :underline="false" v-if="scope.row.ord_status==1" style="margin-left: 5px;">退款</el-link>
								<el-link :underline="false" v-if="scope.row.ord_status==2" style="margin-left: 5px;" @click="update_status(scope.row.ord_id,3)">确认收货</el-link>
								<el-link :underline="false" v-if="scope.row.ord_status==3" style="margin-left: 5px;">评价</el-link>
								<el-link :underline="false" v-if="scope.row.ord_status==2" style="margin-left: 5px;">申请售后</el-link>
							</div>
						</template>
					</el-table-column>
				</el-table>
			</el-card>
		</div>
	</div>
</template>

<script>
	export default {
		props:{
			order_name:{}
		},
		created(){
			this.status();
			this.load();
		},
		data() {
			return {
				FrontPersonal:"FrontPersonal",
				user:null,
				multipleSelection:[],
				tableData:null,
				tableDate_length:null,
				module:{
					data:null,
					pro:"FrontProduct",
				},
				orderUtil:{
					page:{
						pageNum:1,
						count:10
					},
					order:{
						user_id:null,
						ord_status:null
					}
				}
			}
		},
		methods:{
			submit(data){		//进入商品详情界面
				this.module.data=data.pro_id;
				this.$emit('fatherEvent',this.module);
			},
			status(){
				if(this.order_name=='待付款'){
					this.orderUtil.order.ord_status=0;
				}else if(this.order_name=='待发货'){
					this.orderUtil.order.ord_status=1;
				}else if(this.order_name=='待收货'){
					this.orderUtil.order.ord_status=2;
				}else if(this.order_name=='待评价'){
					this.orderUtil.order.ord_status=3;
				}else if(this.order_name=='待售后'){
					this.orderUtil.order.ord_status=4;
				}
				else if(this.order_name=='全部订单'){
					this.orderUtil.order.ord_status=-1;
				}
			},
			load(){//申请订单数据
				this.user=this.$cookies.get("user");
				this.orderUtil.order.user_id=this.user.userid;
				if(this.user==''){
					return
				}else{
					this.$axios.post('http://localhost:8081/OrderPage_3', this.orderUtil
					).then(res => {
						this.tableData = res.data.data;
						this.total = res.data.countSun;
						}
					).catch(error => {
						console.log(error, '请求失败');
					})
				}
			},
			imgformat_pro(row) { //图片显示
				let img = require("@/assets/images/pro_imges/" +row);
				return img;
			},
			update_status(id,status){
				this.$axios.post('http://localhost:8081/OrderStatus', {
					ord_id:id,
					ord_status:status
				}).then(res => {
					if(res.data==1){
						this.load();
						console.log(1);
					}
				}
				).catch(error => {
					console.log(error, '请求失败');
				})
			}
		},
	}
</script>

<style scoped>
	
</style>