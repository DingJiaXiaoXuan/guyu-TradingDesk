<template>
    <div style="height: 901px;width: 100%">
            <div style="margin-top: 10px;">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>商品管理</el-breadcrumb-item>
					<el-breadcrumb-item>商品类别</el-breadcrumb-item>
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
            <div style="margin: 5px;">
				<el-button type="primary" @click="dialogFormVisible = true">新增<i class="el-icon-circle-plus-outline"></i></el-button>
                <el-button type="danger" @click="dels()">批量删除<i class="el-icon-remove-outline"></i></el-button>
                <el-button type="primary">导入<i class="el-icon-bottom"></i></el-button>
                <el-button type="primary">导出<i class="el-icon-top"></i></el-button>
            </div>
			<el-tabs type="border-card" style="width:1386px ;">
				<el-tab-pane label="一级种类">
					<el-table :data="CateMainTableData" border stripe height="711px" @selection-change="handleSelectionChange">
						<el-table-column type="selection" width="55">				
						</el-table-column>
						<el-table-column prop="main_id" label="种类编号" width="200">
						</el-table-column>
						<el-table-column prop="main_name" label="分类名称" width="200">
						</el-table-column>
						<el-table-column prop="create_time" label="创建时间" width="200">
						</el-table-column>
						<el-table-column prop="update_time" label="更新时间" width="200">
						</el-table-column>
						<el-table-column prop="main_status" label="使用" width="100" align="center">
							<template slot-scope="scope">
								<el-switch  v-model="scope.row.main_status"
									active-color="#13ce66"
									inactive-color="#ff4949"
									:active-value="1"
									:inactive-value="0"
									@change="CateMainChangeStatus($event,scope.row)"></el-switch>
							</template>
						</el-table-column>
						<el-table-column label="操作" width="200">
							<template slot-scope="scope">
								<el-button type="success" @click="Edit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
								<el-button type="danger" @click="open(scope.row)">删除<i class="el-icon-remove-outline"></i></el-button>
							</template>
						</el-table-column>
					</el-table>
					<div >
						<el-pagination
								@current-change="CateMainHandleCurrentChange"
								:current-page="pageNum"
								:page-sizes="[5,10,15,20]"
								:page-size="pageSize"
								layout="total,prev, pager, next, jumper"
								:total="CateMainTotal">
						</el-pagination>
					</div>
				</el-tab-pane>
				<el-tab-pane label="二级种类">
					<el-table :data="CateTableData" border stripe height="711px" @selection-change="handleSelectionChange">
						<el-table-column type="selection" width="55">				
						</el-table-column>
						<el-table-column prop="cate_id" label="种类编号" width="200">
						</el-table-column>
						<el-table-column prop="cateMain.main_name" label="所属分类名称" width="200">
						</el-table-column>
						<el-table-column prop="cate_name" label="分类名称" width="200">
						</el-table-column>
						<el-table-column prop="create_time" label="创建时间" width="200">
						</el-table-column>
						<el-table-column prop="update_time" label="更新时间" width="200">
						</el-table-column>
						<el-table-column prop="cate_status" label="使用" width="100" align="center">
							<template slot-scope="scope">
								<el-switch  v-model="scope.row.cate_status"
									active-color="#13ce66"
									inactive-color="#ff4949"
									:active-value="1"
									:inactive-value="0"
									@change="CateChangeStatus($event,scope.row)"></el-switch>
							</template>
						</el-table-column>
					
						<el-table-column label="操作" width="200">
							<template slot-scope="scope">
								<el-button type="success" @click="Edit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
								<el-button type="danger" @click="open(scope.row)">删除<i class="el-icon-remove-outline"></i></el-button>
							</template>
						</el-table-column>
					</el-table>
					<div >
						<el-pagination
								@current-change="CateHandleCurrentChange"
								:current-page="pageNum"
								:page-sizes="[5,10,15,20]"
								:page-size="pageSize"
								layout="total,prev, pager, next, jumper"
								:total="CateTotal">
						</el-pagination>
					</div>
					
				</el-tab-pane>
			</el-tabs>
    </div>
</template>
<script>
	export default{
		created(){
			this.CateLoad();
			this.CateMainLoad();
		},
		data(){
			return{
				cate:{
					cate_id:'',
					main_id:'',
					cate_name:'',
					cate_status:'',
					create_time:'',
					update_time:''
				},
				cateMain:{
					main_id:'',
					main_name:'',
					main_status:'',
					create_time:'',
					update_time:''
				},
				dialogFormVisible:false,
				multipleSelection:[],
				CateTableData:[],
				CateMainTableData:[],
				CateTotal:0,
				CateMainTotal:0,
				pageNum:1,
				pageSize:10,
			}
		},
		methods:{
			CateLoad(){	//二级分类分页查询
				this.$axios.post('http://localhost:8081/CatePage', {
					pageNum : this.pageNum,
					count : this.pageSize
				}).then(res => {
					this.CateTableData = res.data.data;
					this.CateTotal = res.data.countSun;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			CateMainLoad(){	//一级分类分页查询
				this.$axios.post('http://localhost:8081/CateMainPage', {
					pageNum : this.pageNum,
					count : this.pageSize
				}).then(res => {
					this.CateMainTableData = res.data.data;
					this.CateMainTotal = res.data.countSun;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			CateChangeStatus(data,row){//二级分类状态按钮
				this.$axios.post('http://localhost:8081/CateStatus', {
					cate_id : row.cate_id,
					cate_status:row.cate_status
				}).then(res => {
					if(res.data==1){
						this.CateLoad();
						this.$message({
						type: 'success',
						message: '修改成功!'
						});
					}else if(res.data==0){
						this.CateLoad();
						this.$message({
						type: 'error',
						message: '修改失败!'
						});
					}
					
				})
			},
			CateMainChangeStatus(data,row){//一级分类状态按钮
				this.$axios.post('http://localhost:8081/CateMainStatus', {
					main_id : row.main_id,
					main_status:row.main_status
				}).then(res => {
					if(res.data==1){
						this.CateMainLoad();
						this.$message({
						type: 'success',
						message: '修改成功!'
						});
					}else if(res.data==0){
						this.CateMainLoad();
						this.$message({
						type: 'error',
						message: '修改失败!'
						});
					}
					
				})
			},
			CateHandleCurrentChange(pageNum){	//查看下一页
				this.pageNum = pageNum;
				this.CateLoad();
			},
			CateMainHandleCurrentChange(pageNum){	//查看下一页
				this.pageNum = pageNum;
				this.CateMainLoad();
			},
			handleSelectionChange(val){
				this.multipleSelection = val;
			},
		}
	}	
</script>

<style scoped>
</style>