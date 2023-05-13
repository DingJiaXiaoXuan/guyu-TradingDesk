<template>
	<div>
		<el-main>
			<div style="margin-top: 10px;">
				<el-breadcrumb separator="/">
					<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
					<el-breadcrumb-item>店铺管理</el-breadcrumb-item>
					<el-breadcrumb-item>店铺审核</el-breadcrumb-item>
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
			<el-table :data="tableData" border stripe height="700px" @selection-change="handleSelectionChange" style="width: 1956px;">
				<el-table-column type="selection" width="55">				
				</el-table-column>
				<el-table-column prop="store_id" label="店铺编号" width="200">
				</el-table-column>
				<el-table-column prop="store_name" label="店铺名称" width="200">
				</el-table-column>
				<el-table-column prop="store_title" label="店铺简介" width="200">
				</el-table-column>
				<el-table-column prop="store_site" label="店铺地址" width="200">
				</el-table-column>
				<el-table-column prop="user.username" label="店主昵称" width="200">
				</el-table-column>
				<el-table-column prop="pro_num" label="商品数量" width="200">
				</el-table-column>
				<el-table-column prop="store_status" label="上架" width="100" align="center">
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
						<el-button type="success" @click="redact(scope.row)">查看详情</el-button>
						<el-dropdown @command="handleCommand" style="margin-left: 10px;">
							<el-button type="primary"  @click="Edit(scope.row.store_id)">
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
					:current-page="storeUtil.page.pageNum"
					:page-sizes="[5,10,15,20]"
					:page-size="storeUtil.page.count"
					layout="total,prev, pager, next, jumper"
					:total="total">
			</el-pagination>
		</el-main>
		<div v-if="dialogFormVisible" >
			<el-dialog title="店铺信息" :visible.sync="dialogFormVisible"  :append-to-body="true" width="600px" @close="reset()">
				<el-form  :model="store" ref="store" status-icon label-position='top'>
					<el-form-item label="店铺照片" prop="store_imge" clearable>
					<img :src="imgformat_store(store.store_imge)" class="avatar" v-if="store.store_imge">
					</el-form-item>
					<el-form-item label="店铺名称" prop="store_name">
						<el-input v-model="store.store_name" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item label="店铺标题" prop="store_title">
						<el-input
							type="textarea"
							:autosize="{ minRows: 4, maxRows: 6}"
							placeholder="请输入内容"
							v-model="store.store_title"
							:disabled="true"
							>
						</el-input>
					</el-form-item>
					<el-form-item label="店铺地址" prop="store_site">
						<el-input v-model="store.store_site" :disabled="true"></el-input>
					</el-form-item>
				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button @click="reset()">取 消</el-button>
					<el-button type="primary" @click="reset()">确 定</el-button>
				</div>
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
				storeUtil:{
					page:{
						pageNum: 1,
						count:10
					},
					store:{
						store_status:0
					}
				},
				multipleSelection:[],
				tableData:[],
				total:0,
				store_id:null,
				dialogFormVisible:false,
				store:null,
			}
		},
		methods:{
			load(){		//分页显示
				this.$axios.post('http://localhost:8081/StoreAudit',this.storeUtil).then(res => {
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
			handleCurrentChange(pageNum){	//查看下一页
				this.storeUtil.page.pageNum = pageNum;
				this.load();
			},
			handleCommand(command){	//编辑
				this.$axios.post('http://localhost:8081/StoreStatus', {
					store_id : this.store_id,
					store_status:command
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
				this.store_id=data;
			},
			redact(data){		//编辑
				this.dialogFormVisible=true;
				this.store=data;
				
			},
			imgformat_store(row) { //图片显示
				if(row){
					let img = require("@/assets/images/store_imges/" +row);
					// console.log(img);
					return img;
				}
				return;
			},
			reset() {   //取消，重置数据
				this.dialogFormVisible = false;
			},
		},
	}
	
</script>

<style scoped>
	.avatar {
		width: 178px;
		height: 178px;
		display: flex;
	}
</style>