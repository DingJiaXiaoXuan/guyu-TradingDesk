<template>
	<div>
		<div class="block">
			<img :src="imgformat_pro(pro.image)" style="height: 560px;width: 560px;">	
		</div>
		<div style="height: 650px;width: 600px;margin-left: 580px;">
			<div style="width:600px ;">
				<div style="height: 60px;">
					<div class="font_1">{{pro.pro_name}}</div>
					<div class="font_1_1">{{pro.title}}</div>
				</div>
				
				<el-divider></el-divider>
				<div class="font_2">
					价格
					<span class="font_3">{{min_price}}
						<span v-if="min_price!=max_price">
							<span>~</span>
							{{max_price}}
						</span>
						<span>元</span>
					</span>
					
				</div>
				<div class="font_2">
					发货地址
					<span style="font-size: 14px;margin-left: 19px;">{{pro.origin}}</span>
				</div>
				<div class="font_2">
					起批量
					<span style="font-size: 14px;margin-left: 35px;">{{pro_num}}{{unit}}</span>
				</div>
			</div>
			<div style="width: 600px;margin-top: 20px;">
				<div style="float: left;margin-right: 20px;font-size: 14px; ">规格选择</div>
				<div>
					<el-table
						:data="sizetableData"
						style="width: 500px"
						highlight-current-row
						@current-change="handleCurrentChange"
						>
						<el-table-column prop="size_title" label="规格描述" width="240">
						</el-table-column>
						<el-table-column prop="pro_price" label="价格" width="180">
							<template slot-scope="scope">
								<span>{{scope.row.pro_price}}</span>
								<span>元/</span>
								<span>{{scope.row.unit}}</span>
							</template>
						</el-table-column>
						<el-table-column prop="min_num" label="起批量">
							<template slot-scope="scope">
								<span>{{scope.row.min_num}}</span>
								<span>{{scope.row.unit}}</span>
							</template>
						</el-table-column>
					</el-table>
				</div>
			</div>
			<div style="margin-top: 20px;" v-if="prosize">
				<span class="font_2">购买数量</span>
				<span style="margin-left: 20px;">
					<el-input-number v-model="num" @change="handleChange" :min="num_min" :max="num_max" label="描述文字" size="mini"></el-input-number>
				</span>
				<span class="font_3">{{money}}元</span>
			</div>
			<div style="margin-top: 30px;margin-left: 76px;">
				<el-button type="warning" icon="el-icon-shopping-cart-2" @click="addcard()" >加入购物车</el-button>
				<el-button type="danger" icon="el-icon-bank-card" @click="get_inner()">立即购买</el-button>
				<el-button type="primary" icon="el-icon-star-off" @click="collect()">加入收藏</el-button>
			</div>
		</div>
		<div>
			<div >
				<el-card style="background-image: linear-gradient(180deg,#fff,#efffef);">
					<div >
						<el-link :underline="false" @click="send(pro.store.store_id)">
							<div style="text-align: center;font-size: 36px;width: 1200px;">{{pro.store.store_name}}</div>
						</el-link>
						<div style="margin-top: 20px;text-align: center;font-size: 18px;width: 1200px;">{{pro.store.store_title}}</div>
					</div>
				</el-card>
			</div>
			
			<div style="margin-top: 10px;">
				<el-tabs type="border-card">
					<el-tab-pane label="商品详情">
						{{pro.detail}}
					</el-tab-pane>
					<el-tab-pane label="商品评论">
						<div style="height: 160px;" v-if="user">
							<el-input
								type="textarea"
								:autosize="{ minRows: 4, maxRows: 6}"
								placeholder="请输入评论内容"
								v-model="content"
								>
							</el-input>
							<el-button type="primary" style="margin-top: 20px;margin-left: 1000px;" @click="review_add()">发表评论</el-button>
						</div>
						<div v-for="(item, index) in review" :key="index" style="height: 80px;width: 1200px;border-top:1px solid #d3dce6;margin-top: 30px;">
							<div style="float: left;width: 40px;height: 40px;margin-top: 20px;margin-left: 20px;">
								<el-avatar  :src="url.url_1"></el-avatar>
							</div>
							<div style="width: 950px;float: left;margin-top: 20px;">
								<div style="font-size: 16px;margin-left: 20px;">
									{{item.user.username}}
								</div>
								
								<div style="font-size: 14px;margin-top: 5px;margin-left: 20px;">
									{{item.content}}
								</div>
							</div>
							<div style="font-size: 12px;margin-top: 20px;">
								<div style="font-size: 12px;">
									{{item.create_time}}
								</div>
								<div style="margin-top: 15px;" v-if="user&&user.userid==item.user_id">
									<el-button type="danger" size="mini" @click="review_del(item.review_id)">删除评论</el-button>
								</div>
							</div>
						</div>
					</el-tab-pane>
					<el-tab-pane label="交易说明">
						<div style="font-size: 20px;margin-left: 10px;">交易流程</div>
						<div style="height: 460px;margin-left: 10px;">
							<div style="height: 60px;margin-top: 40px;">
								<div style="float: left;margin-right: 10px;">
									<el-image style="width: 41px; height: 45px" :src="url.url_2"></el-image>
								</div>
								<div style="font-size: 16px;">付款方式</div>
								<div style="font-size: 12px;margin-top: 5px;">
									买家先选择支付方式（如网上银行、快捷支付、支付宝余额等）付款到支付宝，支付宝担保货款安全；当买家收到货品并确认后，再由支付宝打款给供应商。如逾期未收到商品或商品不符合要求，买家可以提出退款申请以保障自身权益。
								</div>
							</div>
							<div style="margin-top: 20px;">
								<div style="font-size: 16px;">付款流程</div>
								<div style="margin-top: 10px;">
									<el-image style="width: 1100px; height: 80px" :src="url.url_3"></el-image>
								</div>
							</div>
							<div style="margin-top: 40px;height: 60px;">
								<div style="float: left;margin-right: 10px;">
									<el-image style="width: 41px; height: 45px" :src="url.url_4"></el-image>
								</div>
								<div style="font-size: 16px;">买家保障</div>
								<div style="font-size: 12px;margin-top: 5px;">卖家缴纳保证金为买家提供交易保障，若卖家发生违约或不诚信行为，买家可获得先行赔付。</div>
							</div>
							<div style="margin-top: 10px;">
								<el-image style="width: 1100px; height: 130.64px" :src="url.url_5"></el-image>
							</div>
						</div>
					</el-tab-pane>
				</el-tabs>
			</div>
			<div v-if="dialogVisible" >
				<el-dialog
					title="确认订单"
					:visible.sync="dialogVisible"
					width="600px">
					<el-card>
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
					</el-card>
					<span slot="footer">
						<el-card>
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
	</div>
</template>

<script>
	export default {
		props:{
			pro_id:{}
		},
		created(){
			this.user=this.$cookies.get("user");
			this.pro_load();
			this.size_load();
			this.review_load();
		},
		data() {
			return {
				min_price:0,
				max_price:0,
				pro_num:0,
				dialogVisible:false,
				innerVisible:false,
				unit:"",
				num: 1,
				radio:1,
				prosize:null,
				money:null,
				num_min:0,
				num_max:0,
				sizetableData:[],
				user:null,
				store:null,
				review:null,
				pro:{},
				content:null,
				url:{
					url_1:require('../../assets/images/user.png'),
					url_2:require('../../assets/images/2241afc.png'),
					url_3:require('../../assets/images/b36ea0f.png'),
					url_4:require('../../assets/images/c922aac.png'),
					url_5:require('../../assets/images/9e1494e.png')
				},
				pay_image:{
					zfb:require("@/assets/images/支付宝.jpg"),
					wx:require("@/assets/images/微信.jpg"),
					ysf:require("@/assets/images/云闪付.jpg")
				},
				module:{
					FrontStore:"FrontStore",
					store_id:null,
				},
				address:null,
				Selected_address:null,
				New_address:null,
			}
		},
		methods:{
			send(data){	//切换组件
				this.module.store_id=data;
				this.$emit('store',this.module);
			},
			handleCurrentChange(val) {		//规格点击事件
				this.prosize = val;
				this.num_min=val.min_num;
				this.num_max=val.max_num;
				this.num=val.min_num;
				this.money=val.pro_price * val.min_num;
				// this.money = this.money.toFixed(2);
			},
			size_load(){		//商品规格
				this.$axios.post('http://localhost:8081/FindSize', {
					pro_id:this.pro_id
				}).then(res => {
					this.sizetableData = res.data;
					this.treat(res.data);
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			pro_load(){			//商品信息
				this.$axios.post('http://localhost:8081/findPro', {
					pro_id:this.pro_id
				}).then(res => {
					this.pro = res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			review_load(){			//评论信息
				this.$axios.post('http://localhost:8081/FindReview', {
					pro_id:this.pro_id,
					review_status:1
				}).then(res => {
					this.review = res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			handleChange(value){//计算金额
				this.money=this.prosize.pro_price * value;
				this.money = this.money.toFixed(2);
			},
			treat(data){		//数量选择器
				let arr = [];
				let art	= [];
				let flag=1;
				for(let i=0;i<data.length;i++){
					arr.push(data[i].pro_price);
					art.push(data[i].min_num);
					if(flag==1){
						this.unit=data[i].unit;
						flag=0;
					}
				}
				this.min_price=Math.min.apply(null,arr);
				this.max_price=Math.max.apply(null,arr);
				this.pro_num=Math.max.apply(null,art);
			},
			addcard(){		//添加购物车
				if(this.money!=null&&this.user!=null){
					this.$axios.post('http://localhost:8081/AddCart', {
						user_id:this.user.userid,
						pro_id:this.pro_id,
						pro_num:this.num,
						size_id:this.prosize.size_id,
						pro_sum:this.money
					}).then(res => {
						if(res.data==1){
							this.$message({
							type: 'success',
							message: '该商品已加入购物车!'
							});
						}
					})
				}else{
					this.$message({
					type:"warning",
					message: '请选择商品规格!'
					});
				}
			},
			collect(){		//收藏商品
				this.user=this.$cookies.get("user");
				this.$axios.post('http://localhost:8081/AddCollect',{
					pro_id:this.pro_id,
					user_id:this.user.userid
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
			review_add(){
				if(this.content){		//添加评论
					this.$axios.post('http://localhost:8081/ReviewAdd',{
						pro_id:this.pro_id,
						user_id:this.user.userid,
						store_id:this.pro.store_id,
						content:this.content
					}).then(res => {
						if (res.data == 1){
							this.$message.success('评论成功');
						}else{
							this.$message.error('评论失败');
						}
					})
				}
			},
			review_del(data){	//删除评论
				this.$axios.post('http://localhost:8081/ReviewDel',{
					review_id:data
				}).then(res => {
					if (res.data == 1){
						this.review_load();
						this.$message.success('删除评论成功');
					}else{
						this.$message.error('删除评论失败');
					}
				})
			},
			imgformat_pro(row) { //图片显示
				let img = require("@/assets/images/pro_imges/" +row);
				return img;
			},
			AddOrder(data){		//添加订单
				this.$axios.post('http://localhost:8081/OrderAdd', {
					pro_id:this.pro.pro_id,
					pro_num:this.pro_num,
					size_id:this.prosize.size_id,
					store_id:this.pro.store_id,
					money:this.money,
					user_id:this.user.userid,
					consignee:this.New_address.consignee,
					phone:this.New_address.phone,
					address:this.New_address.address,
					ord_status:data
				}).then(res => {
					if(res.data==1){
						this.innerVisible=false;
						this.dialogVisible=false;
					}
				}).catch(error => {
					console.log(error, '请求失败');
				})
			},
			get_inner(){	//确认订单
				if(this.money==null){
					this.$message.info('请至少购买一件商品！');
				}else{
					this.dialogVisible=true;
					this.load_address();
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
		}
	}
</script>

<style scoped>
	.block{
		width: 560px;
		float: left;
	}
	.el-carousel__item h3 {
		color: #475669;
		font-size: 14px;
		opacity: 0.75;
		line-height: 150px;
		margin: 0;
	}
	.el-carousel__item:nth-child(2n) {
		background-color: #99a9bf;
	}  
	.el-carousel__item:nth-child(2n+1) {
		background-color: #d3dce6;
	}
	.font_1{
		font-size: 20px;
		margin-left: 10px;
	}
	.font_1_1{
		font-size: 14px;
		margin-top: 15px;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
		overflow: hidden;
	}
	.font_2{
		font-size: 14px;
		margin-top: 20px;
	}
	.font_3{
		font-size: 24px;
		color: #ff442e;
		margin-left: 45px;
	}
</style>