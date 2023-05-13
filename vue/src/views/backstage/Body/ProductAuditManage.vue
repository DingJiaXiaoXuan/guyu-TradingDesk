<template>
	<div>
		<el-main>
			<div style="margin-top: 10px;">
				<el-breadcrumb separator="/">
					<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
					<el-breadcrumb-item>商品管理</el-breadcrumb-item>
					<el-breadcrumb-item>商品审核</el-breadcrumb-item>
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
			</div>
			<el-table :data="tableData" border stripe height="700px" @selection-change="handleSelectionChange" style="width: 2156px;">
				<el-table-column type="selection" width="55">				
				</el-table-column>
				<el-table-column prop="pro_id" label="商品编号" width="200">
				</el-table-column>
				<el-table-column prop="pro_name" label="商品名称" width="200">
				</el-table-column>
				<el-table-column prop="category.cate_name" label="商品种类" width="100">
				</el-table-column>
				<el-table-column prop="origin" label="商品产地" width="300">
				</el-table-column>
				<el-table-column prop="amount" label="商品数量" width="200">
				</el-table-column>
				<el-table-column prop="user.username" label="商家名称" width="200">
				</el-table-column>
				<el-table-column prop="store.store_name" label="店铺名称" width="200">
				</el-table-column>	
				<el-table-column prop="pro_status" label="上架" width="100" align="center">
					<template>
						<el-tag type="warning">审核中</el-tag>
					</template>
				</el-table-column>
				<el-table-column prop="create_time" label="创建时间" width="200">
				</el-table-column>
				<el-table-column prop="update_time" label="更新时间" width="200">
				</el-table-column>
				<el-table-column label="操作" width="220">
					<template slot-scope="scope">
						<el-button type="success">查看详情</el-button>
						<el-dropdown @command="handleCommand" style="margin-left: 10px;">
							<el-button type="primary"  @click="Edit(scope.row.pro_id)">
								编辑<i class="el-icon-arrow-down el-icon--right"></i>
							</el-button>
							<el-dropdown-menu slot="dropdown">
								<el-dropdown-item command="1">审核成功</el-dropdown-item>
								<el-dropdown-item command="3">审核失败</el-dropdown-item>
							</el-dropdown-menu>
						</el-dropdown>
					</template>
				</el-table-column>
			</el-table>
			<el-pagination
					@current-change="handleCurrentChange"
					:current-page="product.page.pageNum"
					:page-sizes="[5,10,15,20]"
					:page-size="product.page.count"
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
				tableData:[],
				total:0,
				pro_id:null,
				product:{
					page:{
						pageNum:1,
						count:10
					},
					product:{
						pro_status:0
					}
				}
			}
		},
		methods:{
			load(){
				this.$axios.post('http://localhost:8081/ProductAudit',this.product).then(res => {
					this.tableData = res.data.data;
					this.total = res.data.countSun;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			handleSelectionChange(val){
				this.multipleSelection = val;
			},
			handleCurrentChange(pageNum){
				this.product.page.pageNum = pageNum;
				this.load();
			},
			handleCommand(command){	//编辑
				this.$axios.post('http://localhost:8081/ProStatus', {
					pro_id : this.pro_id,
					pro_status:command
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
			Edit(data){
				this.pro_id=data;
			},
		},
	}
	
</script>

<style>
</style>