<template>
	<div>
		<el-main>
			<div style="margin-top: 10px;">
				<el-breadcrumb separator="/">
					<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
					<el-breadcrumb-item>订单管理</el-breadcrumb-item>
				</el-breadcrumb>
			</div>
			<div style="margin: 5px;">
				<el-input
					style="width: 150px;"
					placeholder="请输入名称"
					prefix-icon="el-icon-search"
				>
				</el-input>
				<el-button style="margin: 5px;" type="primary" plain>搜索</el-button>
				<span style="margin: 5px;">
					<el-button type="primary" @click="dialogFormVisible = true">新增<i class="el-icon-circle-plus-outline"></i></el-button>
					<el-button type="danger" @click="dels()">批量删除<i class="el-icon-remove-outline"></i></el-button>
				</span>
			</div>
			<div style="margin-top: 20px;margin-bottom: 20px;">
				<el-radio-group v-model="radio" size="medium" @change="order_radio">
					<el-radio-button label="全部订单"></el-radio-button>
					<el-radio-button label="待付款"></el-radio-button>
					<el-radio-button label="待发货"></el-radio-button>
					<el-radio-button label="待收货"></el-radio-button>
					<el-radio-button label="待评价"></el-radio-button>
					<el-radio-button label="待售后"></el-radio-button>
				</el-radio-group>
			</div>
			<el-table :data="tableData" border stripe height="700px" @selection-change="handleSelectionChange" style="width: 2156px;">
				<el-table-column type="selection" width="55">				
				</el-table-column>
				<el-table-column prop="ord_id" label="订单编号" width="100">
				</el-table-column>
				<el-table-column prop="product.pro_name" label="商品名称" width="200">
				</el-table-column>
				<el-table-column prop="store.store_name" label="店铺名称" width="200">
				</el-table-column>
				<el-table-column prop="size.size_title" label="商品规格" width="200">
				</el-table-column>
				<el-table-column prop="pro_num" label="商品数量" width="200">
				</el-table-column>
				<el-table-column prop="money" label="金额" width="200">
				</el-table-column>
				<el-table-column prop="user.username" label="付款人" width="200">
				</el-table-column>
				<el-table-column prop="ord_status" label="订单状态" width="100">
					<template slot-scope="scope">
						<el-tag v-if="scope.row.ord_status==0">待付款</el-tag>
						<el-tag v-if="scope.row.ord_status==1" type="success">待发货</el-tag>
						<el-tag v-if="scope.row.ord_status==2" type="danger">待收货</el-tag>
						<el-tag v-if="scope.row.ord_status==3" type="warning">待评价</el-tag>
						<el-tag v-if="scope.row.ord_status==4" type="info">待售后</el-tag>
					</template>
				</el-table-column>
				<el-table-column prop="create_time" label="创建时间" width="200">
				</el-table-column>
				<el-table-column prop="update_time" label="更新时间" width="200">
				</el-table-column>
				<el-table-column label="操作" width="300">
					<template slot-scope="scope">
						<el-button type="primary" @click="Edit(scope.row)" size="mini">查看详情<i class="el-icon-edit"></i></el-button>
						<el-button type="success" @click="Edit(scope.row)" size="mini">编辑<i class="el-icon-edit"></i></el-button>
						<el-button type="danger" @click="del(scope.row)" size="mini">删除<i class="el-icon-remove-outline"></i></el-button>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination
					@current-change="handleCurrentChange"
					:current-page="orderUtil.page.pageNum"
					:page-sizes="[5,10,15,20]"
					:page-size="orderUtil.page.count"
					layout="total,prev, pager, next, jumper"
					:total="total">
			</el-pagination>
		</el-main>
	</div>
</template>

<script>
	export default {
		created(){
			this.load();
		},
		data() {
			return {
				multipleSelection:[],
				tableData:[],
				total:0,
				radio:'全部订单',
				orderUtil:{
					page:{
						pageNum:1,
						count:10
					},
					order:{
						ord_status:-1
					}
				}
			}
		},
		methods:{
			load(){
				this.$axios.post('http://localhost:8081/OrderPage_1', this.orderUtil).then(res => {
					this.tableData = res.data.data;
					this.total = res.data.countSun;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			changeStatus(data,row){		//修改店铺状态
				this.$axios.post('http://localhost:8081/NoticeStatus', {
					notice_id : row.notice_id,
					notice_status:row.notice_status
				}).then(res => {
					if(res.data==1){
						this.load();
						this.$message({
						type: 'success',
						message: '修改成功!'
						});
					}else if(res.data==0){
						this.$message({
						type: 'error',
						message: '修改失败!'
						});
					}
					
				})
			},
			del(row) {	//删除
				this.$confirm('此操作将永久删除该商品信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.post('http://localhost:8081/DelNotice', {
						notice_id : row.notice_id,
					}).then(res => {
						if(res.data==1){
							this.load();
							this.$message({
							type: 'success',
							message: '删除成功!'
							});
						}else if(res.data==0){
							this.$message({
							type: 'error',
							message: '删除失败!'
							});
						}
						
					})
								
				});
			},
			handleCurrentChange(pageNum){	//查看下一页
				this.orderUtil.page.pageNum=pageNum;
				this.load();
			},
			handleSelectionChange(val){
				this.multipleSelection = val;
			},
			order_radio(){
				if(this.radio=='待付款'){
					this.orderUtil.order.ord_status=0;
				}else if(this.radio=='待发货'){
					this.orderUtil.order.ord_status=1;
				}else if(this.radio=='待收货'){
					this.orderUtil.order.ord_status=2;
				}else if(this.radio=='待评价'){
					this.orderUtil.order.ord_status=3;
				}else if(this.radio=='待售后'){
					this.orderUtil.order.ord_status=4;
				}else if(this.radio=='全部订单'){
					this.orderUtil.order.ord_status=-1;
				}
				this.load();
			}
		},
	}
</script>

<style scoped>
</style>