<template>
	<div>
		<div class="bread">
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item>
					<el-link :underline="false" @click="send(FrontPersonal)">个人服务</el-link>
				</el-breadcrumb-item>
				<el-breadcrumb-item>我的收藏</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div style="margin-top: 20px;">
			<el-card>
				<div style="float: left;margin-left: 20px;font-size: 32px;">
					<i class="el-icon-collection-tag" style="font-size: 36px;"></i>
					我的收藏({{tableDate_length}})
				</div>
				<div style="width: 100%;margin-top: 60px;">
					<el-divider></el-divider>
				</div>
				
				<el-table :data="tableData" stripe style="width: 100%">
					<el-table-column label="商品" width="900">
						<template slot-scope="scope">
							<div style="float: left;">
								<img :src="imgformat_pro(scope.row.product.image)" style="height: 100px;width: 100px;">
							</div>
							<div style="margin-left: 110px;">
								<div style="height: 50px;">
									<el-link :underline="false" @click="submit(scope.row)">{{scope.row.product.pro_name}}</el-link>	
								</div>
							</div>
						</template>
					</el-table-column>
					<el-table-column label="操作" width="210">
						<template slot-scope="scope">
							<div style="width: 120px;margin-top: 5px;">
								<el-link :underline="false" @click="del(scope.row)">移除收藏</el-link>
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
		created(){
			this.user=this.user=this.$cookies.get("user");
			this.load();
		},
		data() {
			return {
				user:null,
				tableData:null,
				tableDate_length:null,
				FrontPersonal:"FrontPersonal",
				module:{
					data:[],
					pro:"FrontProduct",
				},
				url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
			}
		},
		methods:{
			send(data){	//切换组件
				this.$emit('jump',data);
			},
			load(){//收藏数据申请
				this.$axios.post('http://localhost:8081/QueryCollect', {
					user_id:this.user.userid
				}).then(res => {
					this.tableData=res.data;
					this.tableDate_length=res.data.length;
				})
			},
			submit(data){		//进入商品详情界面
				this.module.data=data.pro_id;
				this.$emit('fatherEvent',this.module);
			},
			del(data){
				this.$axios.post('http://localhost:8081/DelCollect',{
					collect_id:data.collect_id
				}).then(res => {
					if (res.data == 1){
						this.load();
						this.$message.success('移除成功！');
					}
				})
			},
			imgformat_pro(row) { //图片显示
				let img = require("@/assets/images/pro_imges/" +row);
				return img;
			},
		},
	}
</script>

<style scoped>
	
</style>