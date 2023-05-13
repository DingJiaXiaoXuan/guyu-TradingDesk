<template>
	<div>
		<div>
			<el-card class="box-card">
				<div class="result">
					<span>全部结果<i class="el-icon-arrow-right"></i></span>
					<span style="margin-left: 20px;">
						<el-tag type="success" v-model="main.mian_name">{{main.main_name}}</el-tag>
					</span>
					<span v-if="cate.cate_id">
						<span style="margin-left: 20px;">
							<el-tag type="success">{{cate.cate_name}}</el-tag>
						</span>
					</span>
				</div>
				<div >
					<div>
						<span style="font-size: 18px;">类别</span>
						<el-link :underline="false" style="margin-left: 62px;" @click="main_all()">全部</el-link>
						<el-button v-if="main_flag==0" type="text" @click="main_flag=1" class="but">展开</el-button>
						<el-button v-else type="text" @click="main_flag=0" class="but">收起</el-button>
					</div>
					<div v-show="main_flag" class="cateMain">
						<div v-for="(item, index) in maintableData" :key="index" class="cate_font">
							<el-link :underline="false"	@click="click_main(item)">{{item.main_name}}</el-link>
							<span v-if="index%20==0&&index!=0"><br /></span>
						</div> 
					</div>
				</div>
				<div style="400px">
					<div>
						<span style="font-size: 18px;">种类</span>
						<el-link :underline="false" style="margin-left: 62px;" @click="cate_all()">全部</el-link>
						<el-button v-if="cate_flag==0" type="text" @click="cate_flag=1" class="but">展开</el-button>
						<el-button v-else type="text" @click="cate_flag=0" class="but">收起</el-button>
					</div>
					<div v-show="cate_flag" class="cate">
						<div v-for="(item, index) in catetableData" :key="index" class="cate_font">
							<el-link :underline="false"	@click="click_cate(item)">{{item.cate_name}}</el-link>
							<span v-if="index%20==0&&index!=0"><br /></span>
						</div> 
					</div>
				</div>
			</el-card>
		</div>
		<div style="width: 1160px;">
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
					:current-page="pageNum"
					:page-sizes="[5,10,15,20]"
					:page-size="pageSize"
					layout="total,prev, pager, next, jumper"
					:total="total">
			</el-pagination>
		</div>
	</div>
</template>

<script>
	export default {
		props:{
			main:{}
		},
		created(){
			this.load_cate();
			this.load_pro();
			this.load_main();
		},
		data() {
			return {
				protableData:[],
				cate_flag:0,
				main_flag:0,
				catetableData:[],
				maintableData:[],
				cate:{
					cate_id:null,
					cate_name:null
				},
				total:0,
				pageNum:1,
				pageSize:25,
				module:{
					pro_id:null,
					pro:"FrontProduct",
				},
				productUtil:{
					page:{
						pageNum:null,
						count:null
					},
					product:{
						cate_id:null
					},
					cateMain:{
						main_in:null,
						main_name:null
					}
				}
			}
		},
		methods:{
			load_main(){
				this.$axios.post('http://localhost:8081/FindCateMain').then(res => {
					this.maintableData = res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			load_cate(){		//请求种类数据
				this.$axios.post('http://localhost:8081/FindCate', {
					main_id:this.main.main_id
				}).then(res => {
					this.catetableData = res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			load_pro(){		//	请求商品数据
				if(this.main.main_id==null){
					this.$axios.post('http://localhost:8081/AllProPage', {
						pageNum : this.pageNum,
						count : this.pageSize
					}).then(res => {
						this.protableData = res.data.data;
						this.total = res.data.countSun;
						}
					).catch(error => {
						console.log(error, '请求失败');
					})
				}else if(this.cate.cate_id==null&&this.main.main_id!=null){
					this.productUtil.page.pageNum=this.pageNum;
					this.productUtil.page.count= this.pageSize;
					this.productUtil.cateMain.main_id=this.main.main_id;
					this.$axios.post('http://localhost:8081/MainPro', this.productUtil
					).then(res => {
						this.protableData = res.data.data;
						this.total = res.data.countSun;
						}
					).catch(error => {
						console.log(error, '请求失败');
					})
				}else if(this.cate.cate_id) {
					this.productUtil.page.pageNum=this.pageNum;
					this.productUtil.page.count= this.pageSize;
					this.productUtil.product.cate_id=this.cate.cate_id;
					this.$axios.post('http://localhost:8081/CatePro', this.productUtil
					).then(res => {
						this.protableData = res.data.data;
						this.total = res.data.countSun;
						}
					).catch(error => {
						console.log(error, '请求失败');
					})
				}
			},
			handleCurrentChange(pageNum){	//换页
				this.pageNum = pageNum;
				this.load_pro();
			},
			submit(data){ //进入商品详情界面
				this.module.data=data.pro_id;
				this.$emit('fatherEvent',this.module);
			},
			click_cate(data){		//种类商品查询
				this.cate.cate_id=data.cate_id;
				this.cate.cate_name=data.cate_name;
				this.load_pro();
			},
			cate_all(){				//全部种类商品查询
				this.cate.cate_id=null;
				this.cate.cate_name=null;
				this.load_pro();
			},
			click_main(data){		//类别商品查询
				this.productUtil.cateMain.main_in=data.main_id;
				this.productUtil.cateMain.main_name=data.main_name;
				this.load_pro();
			},
			main_all(){				//全部类别商品查询
				this.main=null
				this.productUtil.cateMain.main_in=null;
				this.productUtil.cateMain.main_name=null;
				this.load_pro();
			},
			imgformat_pro(row) { //图片显示
				let img = require("@/assets/images/pro_imges/" +row);
				return img;
			},
		},
		watch:{
			main(){
				this.load_cate();
			},
			
		}
	}
	
</script>

<style scoped>
	.cate{
		height: 130px;
		
	}
	.cateMain{
		height: 100px;
	}
	.result{
		margin-bottom: 30px;
	}
	.main_font{
		margin-left: 10px;
	}
	.cate_font{
		margin-top: 5px;
		margin-left: 10px;
		width: 80px;
		float: left;
	}
	.but{
		margin-left: 960px;
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