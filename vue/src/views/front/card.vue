<template>
	<div>
		<el-card class="box-card">
			<div>
				<div style="float: left;margin-left: 20px;font-size: 32px;">
					<i class="el-icon-shopping-cart-2" style="font-size: 36px;"></i>
					购物车({{tableDate_length}})
				</div>
				<div style="margin-left: 640px;">
					<span style="float: left;margin-top: 12px;">已选商品(不含运费)</span>
					<div style ="font-size: 24px;color: #ff442e;width:200px;float: left;margin-left: 10px;margin-top: 5px;">{{money}}元</div>
					<span style="margin-left:10px;">
						<el-button type="danger" @click="buy_pro()">结算</el-button>
					</span>
				</div>
				
				<el-divider></el-divider>
			</div>
			<div>
				<el-table
					ref="multipleTable"
					:data="tableData"
					tooltip-effect="dark"
					style="width: 100%"
					height="837px"
					@selection-change="handleSelectionChange">
					<el-table-column type="selection" width="55">
					</el-table-column>
					<el-table-column label="商品信息" width="500">
					<template slot-scope="scope">
					<div style="float: left;">
						<img :src="imgformat_pro(scope.row.product.image)" style="height: 100px;width: 100px;">
					</div>
					<div style="margin-left: 110px;">
						<div class="title">
							<el-link :underline="false" @click="submit(scope.row)">{{scope.row.product.pro_name}}</el-link>	
						</div>
						<div style="height: 50px;font-size: 12px;color: #999;">
							<span>购买规格:</span><br />
							<span>{{scope.row.size.size_title}}</span>
						</div>
					</div>
					</template>
					</el-table-column>
					<el-table-column prop="size.pro_price" label="单价" width="120">
					</el-table-column>
					<el-table-column prop="pro_num" label="数量" width="200" align="center">
						<template slot-scope="scope">
							<el-input-number v-model="scope.row.pro_num" @change="handleChange(scope.row)" :min="scope.row.size.min_num" :max="scope.row.size.max_num" label="描述文字" size="mini"></el-input-number>
						</template>
						
					</el-table-column>
					<el-table-column prop="pro_sum" label="金额" width="120">
					</el-table-column>
					<el-table-column label="操作" width="120">
						<template slot-scope="scope">
							<div style="width: 120px;">
								<el-button type="success" size="mini" @click="collect(scope.row)">收藏</el-button>
							</div>
							<div style="width: 120px;margin-top: 5px;">
								<el-button type="danger" size="mini" @click="del(scope.row)">删除</el-button>
							</div>
						</template>
					</el-table-column>
				</el-table>	
			</div>
		</el-card>
		<div v-if="dialogVisible" >
			<el-dialog
				title="确认订单"
				:visible.sync="dialogVisible"
				width="1000px">
				<span style="font-size: 16px;">收货地址：</span>
				<el-select v-model="Selected_address" placeholder="请选择收货地址" clearable @change="NewAddress" style="width: 300px;" >
					<el-option
						v-for="item in address"
						:key="item.address_id"
						:label="item.consignee+'  \\  '+item.phone+'  \\  '+item.address"
						:value="item.address_id">
					<span style="font-size: 18px;">{{item.consignee}}</span>
					<span style="font-size: 16px;margin-left: 15px;">{{item.phone}}</span>
					<span style="font-size: 16px;margin-left: 15px;">{{item.address}}</span>
					</el-option>
				</el-select>
				<div style="margin-top: 20px;">
					<el-card style="height: 700px;">
						<div style="font-size:16px;">商品列表：</div>
					<el-table
						ref="multipleTable"
						:data="multipleSelection"
						tooltip-effect="dark"
						style="width: 100%"
						height="600px">
						<el-table-column label="商品信息" width="500">
						<template slot-scope="scope">
						<div style="float: left;">
							<img :src="imgformat_pro(scope.row.product.image)" style="height: 100px;width: 100px;">
						</div>
						<div style="margin-left: 110px;">
							<div class="title">
								<el-link :underline="false" @click="submit(scope.row)">{{scope.row.product.pro_name}}</el-link>	
							</div>
							<div style="height: 50px;font-size: 12px;color: #999;">
								<span>购买规格:</span><br />
								<span>{{scope.row.size.size_title}}</span>
							</div>
						</div>
						</template>
						</el-table-column>
						<el-table-column prop="size.pro_price" label="单价" width="100" align="center">
							<template slot-scope="scope">
								<span>￥</span>
								<span>{{scope.row.size.pro_price}}</span>
							</template>
						</el-table-column>
						<el-table-column prop="pro_num" label="数量" width="100" align="center">
							<template slot-scope="scope">
								<span>×</span>
								<span>{{scope.row.pro_num}}</span>
							</template>
						</el-table-column>
						<el-table-column prop="pro_sum" label="金额" width="200" align="center">
							<template slot-scope="scope">
								<span>￥</span>
								<span>{{scope.row.pro_sum}}</span>
							</template>
						</el-table-column>
					</el-table>	
					</el-card>
				</div>
				<span slot="footer">
					<el-card>
						<span style="font-size: 12px;color: #999;">共{{multipleSelection.length}}件</span>
						<span style="font-size: 14px;margin-left: 5px;">合计：</span>
						<span style="font-size: 14px;color: #ff442e;">￥</span>
						<span style="font-size: 24px;color: #ff442e;">{{money}}</span>
						<span style="margin-left: 20px;">
							<el-button type="danger" round @click="innerVisible=true">提交订单</el-button>
						</span>
					</el-card>
				</span>
				<el-dialog
					width="600px"
					title="收银台"
					:visible.sync="innerVisible"
					append-to-body>
					<div style="height: 200px;width:600px;display: table-cell;vertical-align: middle;text-align: center;">
						<div style="font-size: 18px;margin-bottom: 5px;">支付金额</div>
						<span style="font-size: 18px;">￥</span>
						<span style="font-size: 36px;">{{money}}</span>
					</div>
					<el-card style="height: 80px;">
						<div style="float: left;">
							<el-avatar :src="pay_image.zfb"  shape="square" ></el-avatar>
						</div>
						<div style="width: 50px;float: left;margin-top: 10px;margin-left: 10px;">
							支付宝
						</div>
						<div style="float: left;margin-left: 390px;margin-top: 10px;">
							<el-radio v-model="radio" label="1"><span></span></el-radio>
						</div>
					</el-card>
					<el-card style="height: 80px;margin-top: 5px;">
						<div style="float: left;">
							<el-avatar :src="pay_image.wx"  shape="square"></el-avatar>
						</div>
						<div style="width: 50px;float: left;margin-top: 10px;margin-left: 10px;">
							微信
						</div>
						<div style="float: left;margin-left: 390px;margin-top: 10px;">
							<el-radio v-model="radio" label="2"><span></span></el-radio>
						</div>
					</el-card>
					<el-card style="height: 80px;margin-top: 5px;">
						<div style="float: left;">
							<el-avatar :src="pay_image.ysf" shape="square"></el-avatar>
						</div>
						<div style="width: 50px;float: left;margin-top: 10px;margin-left: 10px;">
							云闪付
						</div>
						<div style="float: left;margin-left: 390px;margin-top: 10px;">
							<el-radio v-model="radio" label="3"><span></span></el-radio>
						</div>
					</el-card>
					<span slot="footer">
						<el-button type="danger" round @click="AddOrder(1)">确认支付</el-button>
						<el-button type="warning" round @click="AddOrder(0)">取消支付</el-button>
					</span>
				</el-dialog>
			</el-dialog>
		</div>
	</div>
</template>

<script>
	export default {
		created(){
			this.load();
		},
		data() {
			return {
				money:0,
				radio:1,
				tableData:null,
				tableDate_length:0,
				dialogVisible:false,
				innerVisible:false,
				user:[],
				address:null,
				Selected_address:null,
				New_address:null,
				multipleSelection:[],
				module:{
					data:null,
					pro:"FrontProduct",
				},
				pay_image:{
					zfb:require("@/assets/images/支付宝.jpg"),
					wx:require("@/assets/images/微信.jpg"),
					ysf:require("@/assets/images/云闪付.jpg")
				}
			}
		},
		methods:{
			load(){//申请购物车数据
				this.user=this.$cookies.get("user");
				if(this.user==''){
					return
				}
				this.$axios.post('http://localhost:8081/CartPage', {
					user_id:this.user.userid
				}).then(res => {
					this.tableData=res.data;
					this.tableDate_length=res.data.length;
				})
			},
			handleChange(data){	//计算金额
				data.pro_sum = data.pro_num*data.size.pro_price
				data.pro_sum = data.pro_sum.toFixed(2);
				this.$axios.post('http://localhost:8081/UpdateCartNum', {
					cart_id:data.cart_id,
					pro_num:data.pro_num,
					pro_sum:data.pro_sum
				}).then(res => {
					if(res.data==1){
						this.load()
					}
					
				})
			},
			handleSelectionChange(val){	//计算总金额
				if(val==""){
					this.money=0;
				}else{
					for(let i=0;i<val.length;i++){
						this.money=this.money+val[i].pro_sum;
					}
				}
				this.multipleSelection = val;
			},
			collect(data){		//收藏商品
				this.$axios.post('http://localhost:8081/AddCollect',{
					pro_id:data.pro_id,
					user_id:data.user_id
				}).then(res => {
					if (res.data == 1){
						this.$message.success('收藏成功');
					}else if(res.data == 2){
						this.$message.warning('该商品已收藏');
					}else{
						this.$message.error('收藏失败');
					}
				})
			},
			submit(data){		//进入商品详情界面
				this.module.data=data.pro_id;
				this.$emit('fatherEvent',this.module);
			},
			del(data){		//移除购物车
				this.$axios.post('http://localhost:8081/DelCart',{
					cart_id:data.cart_id
				}).then(res => {
					if (res.data == 1){
						this.load();
					}else{
						this.$message.error('删除失败');
					}
				})
			},
			imgformat_pro(row) { //图片显示
				let img = require("@/assets/images/pro_imges/" +row);
				return img;
			},
			buy_pro(){		//结算界面
				if(this.money!=0){
					this.dialogVisible=true;
					this.load_address();
				}else{
					this.$message.info('您还没有选择宝贝哦！');
				}
				
			},
			load_address(){		//获取收货地址
				this.$axios.post('http://localhost:8081/QueryAddress', {
					user_id:this.user.userid
				}).then(res => {
					this.address= res.data;
					this.Selected_address=res.data[0];
					this.New_address=res.data[0];
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			NewAddress(data){	//查询选中的收货地址
				this.$axios.post('http://localhost:8081/findAddress', {
					address_id:data
				}).then(res => {
					this.New_address=res.data;
					console.log(this.New_address.consignee);
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			AddOrder(data){		//添加订单
				this.multipleSelection.forEach(item=>{
					this.$axios.post('http://localhost:8081/OrderAdd', {
						pro_id:item.pro_id,
						pro_num:item.pro_num,
						size_id:item.size_id,
						store_id:item.product.store_id,
						money:item.pro_sum,
						user_id:this.user.userid,
						consignee:this.New_address.consignee,
						phone:this.New_address.phone,
						address:this.New_address.address,
						ord_status:data
					}).then(res => {
						if(res.data==1){
							this.innerVisible=false;
							this.dialogVisible=false;
							this.del(item);
							this.load();
						}
					}).catch(error => {
						console.log(error, '请求失败');
					})
				})
			}
		},
	}
</script>

<style scoped>
	.title{
		height: 50px;
		font-size: 15px;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
		overflow: hidden;
	}
</style>