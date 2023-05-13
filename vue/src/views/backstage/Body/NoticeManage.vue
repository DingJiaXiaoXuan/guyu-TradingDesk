<template>
	<div>
		<el-main>
			<div style="margin-top: 10px;">
				<el-breadcrumb separator="/">
					<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
					<el-breadcrumb-item>公告管理</el-breadcrumb-item>
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
			</div>
			<el-table :data="tableData" border stripe height="700px" @selection-change="handleSelectionChange" style="width: 1656px;">
				<el-table-column type="selection" width="55">				
				</el-table-column>
				<el-table-column prop="notice_id" label="公告编号" width="200">
				</el-table-column>
				<el-table-column prop="notice_title" label="公告标题" width="200">
				</el-table-column>
				<el-table-column prop="user_name" label="发布者" width="200">
				</el-table-column>
				<el-table-column prop="notice_status" label="公告状态" width="100">
					<template slot-scope="scope">
						<el-switch  v-model="scope.row.notice_status"
							active-color="#13ce66"
							inactive-color="#ff4949"
							:active-value="1"
							:inactive-value="0"
							@change="changeStatus($event,scope.row)"></el-switch>
					</template>
				</el-table-column>
				<el-table-column prop="create_time" label="创建时间" width="200">
				</el-table-column>
				<el-table-column prop="update_time" label="修改时间" width="200">
				</el-table-column>
				<el-table-column prop="publish_time" label="发布时间" width="200">
				</el-table-column>
				<el-table-column label="操作" width="200">
					<template slot-scope="scope">
						<el-button type="success" @click="Edit(scope.row)" size="mini">编辑<i class="el-icon-edit"></i></el-button>
						<el-button type="danger" @click="del(scope.row)" size="mini">删除<i class="el-icon-remove-outline"></i></el-button>
					</template>
				</el-table-column>
			</el-table>
		</el-main>
		<div v-if="dialogFormVisible" >
			<el-dialog title="发布公告" :visible.sync="dialogFormVisible"  :append-to-body="true" width="600px" @close="reset()">
				<el-form :model="notice" ref="notice" status-icon>
					<el-form-item prop="notice_titile">
						<span>公告标题</span><br />
						<el-input v-model="notice.notice_title" autocomplete="off" class="input" clearable></el-input>
					</el-form-item>
					<el-form-item prop="user_name">
						<span>发布者名称</span><br />
						<el-input v-model="notice.user_name" autocomplete="off" class="input" clearable></el-input>
					</el-form-item>
					<el-form-item prop="content">
						<span>公告内容</span><br />
						<el-input
							type="textarea"
							:autosize="{ minRows: 4, maxRows: 6}"
							placeholder="请输入公告内容"
							v-model="notice.content">
						</el-input>
					</el-form-item>
				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button @click="reset()">取 消</el-button>
					<el-button type="primary" @click="submit()">确 定</el-button>
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
				multipleSelection:[],
				tableData:[],
				total:0,
				pageNum:1,
				pageSize:10,
				flag:0,
				dialogFormVisible:false,
				notice:{
					notice_id:null,
					notice_title:null,
					user_name:null,
					content:null,
					notice_status:0
				}
			}
		},
		methods:{
			load(){
				this.$axios.post('http://localhost:8081/NoticePage', {
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
			handleSelectionChange(val){
				this.multipleSelection = val;
			},
			reset() {			//关闭添加公告对话框
				this.dialogFormVisible = false;
				for (let key in this.notice) {
					this.notice[key] = "";
				}
			},
			submit() {
				if(!this.flag){
					this.$axios.post('http://localhost:8081/AddNotice', {
						notice_title:this.notice.notice_title,
						user_name:this.notice.user_name,
						content:this.notice.content,
						notice_status:this.notice.notice_status
					}).then(res => {
						if(res){
							this.$message.success("新增成功");
							this.load();
							// 点击取消 数据重置
							this.reset();
							this.dialogFormVisible = false;
							this.flag=0;
						}else{
							this.$message.error("新增失败");
							this.load();
							this.flag=0;
						}
					})
				}else{
					this.$axios.post('http://localhost:8081/UpdateNotice', {
						notice_id:this.notice.notice_id,
						notice_title:this.notice.notice_title,
						user_name:this.notice.user_name,
						content:this.notice.content,
						notice_status:this.notice.notice_status
					}).then(res => {
						if(res){
							this.dialogFormVisible = false;
							this.$message.success("编辑成功");
							this.load();
							// 点击取消 数据重置
							this.reset();
							this.flag=0;
						}else{
							this.$message.error("编辑失败");
							this.load();
							this.flag=0;
						}
					})
				}
			},
			Edit(data){	//编辑规格
				this.dialogFormVisible=true;
				this.notice.notice_id=data.notice_id;
				this.notice.notice_title=data.notice_title;
				this.notice.user_name=data.user_name;
				this.notice.content=data.content;
				this.flag=1;
			},
		},
	}
</script>

<style>
</style>