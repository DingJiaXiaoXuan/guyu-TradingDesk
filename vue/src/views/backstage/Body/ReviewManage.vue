<template>
	<div style="height: 901px;width: 100%">
		<el-main>
			<div style="margin-top: 10px;">
				<el-breadcrumb separator="/">
					<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
					<el-breadcrumb-item>评论管理</el-breadcrumb-item>
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
				<el-button type="danger" @click="dels()">批量删除<i class="el-icon-remove-outline"></i></el-button>
			</div>	
			<el-table :data="tableData" border stripe height="700px" @selection-change="handleSelectionChange" style="width: 1856px;">
				<el-table-column type="selection" width="55">				
				</el-table-column>
				<el-table-column prop="review_id" label="评论编号" width="100">
				</el-table-column>
				<el-table-column prop="product.pro_name" label="商品名称" width="200">
				</el-table-column>
				<el-table-column prop="store.store_name" label="店铺名称" width="200">
				</el-table-column>
				<el-table-column prop="content" label="评论内容" width="300">
				</el-table-column>
				<el-table-column prop="user.username" label="评论用户" width="200">
				</el-table-column>
				<el-table-column prop="review_status" label="状态" width="100" align="center">
					<template slot-scope="scope">						
						<el-tag type="warning" v-if="scope.row.review_status==0">审核中</el-tag>
						<el-tag type="success" v-else-if="scope.row.review_status==1">审核成功</el-tag>
						<el-tag type="danger" v-else-if="scope.row.review_status==2">审核失败</el-tag>
					</template>
				</el-table-column>
				<el-table-column prop="create_time" label="创建时间" width="200">
				</el-table-column>
				<el-table-column prop="update_time" label="更新时间" width="200">
				</el-table-column>
				<el-table-column label="操作" width="300">
					<template slot-scope="scope">
						<el-dropdown @command="handleCommand" style="margin-left: 10px;">
							<el-button type="primary"  @click="Edit(scope.row)">
								编辑<i class="el-icon-arrow-down el-icon--right"></i>
							</el-button>
							<el-dropdown-menu slot="dropdown">
								<el-dropdown-item command="0">审核中</el-dropdown-item>
								<el-dropdown-item command="1">审核成功</el-dropdown-item>
								<el-dropdown-item command="2">审核失败</el-dropdown-item>
							</el-dropdown-menu>
						</el-dropdown>
						<el-button type="danger" @click="open(scope.row)" style="margin-left: 10px;">删除<i class="el-icon-remove-outline"></i></el-button>
					</template>
				</el-table-column>
			</el-table>
			<div >
				<el-pagination
						@current-change="handleCurrentChange"
						:current-page="pageNum"
						:page-sizes="[5,10,15,20]"
						:page-size="pageSize"
						layout="total,prev, pager, next, jumper"
						:total="total">
				</el-pagination>
			</div>
		</el-main>
	</div>
</template>

<script>
	export default{
		created(){
			this.load();			
		},
		data(){
			return{
				review:[],
				multipleSelection:[],
				tableData:[],
				total:0,
				pageNum:1,
				pageSize:10,
			}
		},
		methods:{
			open(row) {	//删除
				this.$confirm('此操作将永久删除该商品信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.post('http://localhost:8081/ReviewDel', {
						review_id : row.review_id,
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
			dels(){	//批量删除
				let ids=this.multipleSelection.map(value => value.review_id)//[{}]=>[1,2]
				//console.log(ids);
				this.$axios.post("http://localhost:8081/ReviewDel",ids).then(res=> {
				if (res) {
					this.$message.success("批量删除成功!");
					this.load();
				}else if (res==0){
					this.$message.error("请选择!")
					this.load();
				}
				else {
					this.$message.error("批量删除失败!")
					this.load();
				}
				})
			},
			handleSelectionChange(val){
				this.multipleSelection = val;
			},
			handleCommand(command){	//编辑
				this.$axios.post('http://localhost:8081/ReviewStatus', {
					review_id:this.review.review_id,
					review_status:command
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
			Edit(row){
				this.review=row;
			},
			handleCurrentChange(pageNum){	//查看下一页
				this.pageNum = pageNum;
				this.load();
			},
			load(){		//分页显示
				this.$axios.post('http://localhost:8081/ReviewPage', {
					pageNum : this.pageNum,
					count : this.pageSize
				}).then(res => {
					this.tableData = res.data.data;
					this.total = res.data.countSun;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			
		},
	}
</script>

<style>
</style>