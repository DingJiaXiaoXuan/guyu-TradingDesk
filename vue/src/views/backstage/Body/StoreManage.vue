<template>
    <div style="height: 901px;width: 100%">
        <el-main>
            <div style="margin-top: 10px;">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>店铺管理</el-breadcrumb-item>
					<el-breadcrumb-item>店铺信息</el-breadcrumb-item>
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
					<template slot-scope="scope">
						<div v-if="scope.row.store_status==1||scope.row.store_status==2">
							<el-switch  v-model="scope.row.store_status"
								active-color="#13ce66"
								inactive-color="#ff4949"
								:active-value="2"
								:inactive-value="1"
								@change="changeStatus(scope.row)"></el-switch>
						</div>
						<div v-if="scope.row.store_status==0">
							<el-tag type="warning">审核中</el-tag>
						</div>
						<div v-if="scope.row.store_status==3">
							<el-tag type="danger">审核失败</el-tag>
						</div>
					</template>
				</el-table-column>
				<el-table-column prop="create_time" label="创建时间" width="200">
				</el-table-column>
				<el-table-column prop="update_time" label="更新时间" width="200">
				</el-table-column>
                <el-table-column label="操作" width="200">
					<template slot-scope="scope">
						<el-button type="success" @click="redact(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
						<el-button type="danger" @click="open(scope.row)">删除<i class="el-icon-remove-outline"></i></el-button>
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
		<div v-if="dialogFormVisible" >
			<el-dialog title="店铺信息" :visible.sync="dialogFormVisible"  :append-to-body="true" width="600px" @close="reset()">
				<el-form  :rules="rules" :model="store" ref="store" status-icon label-position='top'>
					<el-form-item label="店铺图片" prop="store_name">
						<img v-if="tempUrl" :src="tempUrl" class="avatar">
						<el-upload
								action="http://localhost:8081/StoreImage"
								:on-change="handleChange"
								:show-file-list="false"
								:before-upload="httpRequest"
								:on-success="Image"
								:auto-upload="true"
								><!--覆盖默认上传-->						
							<el-button slot="trigger" size="small" type="button" style="margin-top: 20px;">点击上传</el-button>
						</el-upload>
					</el-form-item>
					<el-form-item label="店铺名称" prop="store_name">
						<el-input v-model="store.store_name" clearable></el-input>
					</el-form-item>
					<el-form-item label="店铺标题" prop="store_title">
						<el-input
							type="textarea"
							:autosize="{ minRows: 4, maxRows: 6}"
							placeholder="请输入内容"
							v-model="store.store_title">
						</el-input>
					</el-form-item>
					<el-form-item label="店铺地址" prop="store_site">
						<el-input v-model="store.store_site" clearable></el-input>
					</el-form-item>
					<el-form-item label="是否启用" prop="store_status" v-if="user.power==2&&flag==1">
						<el-button @click="store.store_status=0" type="danger" size="mini" v-if="store.store_status==2">违规下架</el-button>
						<el-button @click="store.store_status=2" type="primary" size="mini" v-else>已下架</el-button>
					</el-form-item>
				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button @click="reset()">取 消</el-button>
					<el-button type="primary" @click="onsubmit()">确 定</el-button>
				</div>
			</el-dialog>
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
				store:{
					store_id:'',      
					store_name:'',
					store_title:'',   
					store_site:'', 
					store_imge:'',
					user_id:'',       
					pro_num:'',       
					store_status:'', 
					create_time:'',  
					update_time:''
				},
				rules:{
					store_name: [
						{ required: true, message: '请输入店铺名', trigger: 'blur' },
					],
					store_title: [
						{ required: true, message: '请输入店铺标题', trigger: 'blur' },
					],
					store_site: [
						{ required: true, message: '请输入店铺地址', trigger: 'blur' },
					],
				},
				dialogFormVisible:false,
				multipleSelection:[],
				tableData:[],
				total:0,
				pageNum:1,
				pageSize:10,
				tempUrl: '',
				user:{},
				storeImage:null,
				flag:0,
				storeUtil:{
					page:{
						pageNum: 1,
						count:10
					},
					store:{
						user_id:null
					}
				},
			}
		},
		methods:{
			open(row) {	//删除
				this.$confirm('此操作将永久删除该商品信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.post('http://localhost:8081/StoreDel', {
						store_id : row.store_id,
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
				let ids=this.multipleSelection.map(value => value.store_id)//[{}]=>[1,2]
				//console.log(ids);
				this.$axios.post("http://localhost:8081/ProDels",ids).then(res=> {
				if (res.data) {
					this.$message.success("批量删除成功!");
					this.load();
				}else if (res.data==0){
					this.$message.error("请选择!")
					this.load();
				}else {
					this.$message.error("批量删除失败!")
					this.load();
				}
				})
			},
			onsubmit() {	//新增
				if(this.store.store_name==''||this.store.store_title==''||this.store.store_site==''||this.store.store_imge==''){
					this.$message.info('请输入完整店铺信息');
					return;
				}
				if(!this.flag){
					this.dialogFormVisible = false;
					this.$axios.post('http://localhost:8081/StoreAdd', {
						store_name : this.store.store_name,
						store_title:this.store.store_title,
						store_site:this.store.store_site,
						store_imge:this.store.store_imge,
						store_status:0,
						user_id:this.user.userid
					}).then(res => {
						if(res.data==1){
							this.$message.success("新增成功");
							this.load();
							// 点击取消 数据重置
							this.reset();
							this.flag=0;
						}else{
							this.$message.error("新增失败");
							this.load();
						}
					})	
				}else{
					this.dialogFormVisible = false;
					this.$axios.post('http://localhost:8081/StoreUpdate', {
						store_id:this.store.store_id,
						store_name : this.store.store_name,
						store_title:this.store.store_title,
						store_site:this.store.store_site,
						store_imge:this.store.store_imge,
						store_status:this.store.store_status
					}).then(res => {
						if(res.data==1){
							this.$message.success("修改成功");
							this.load();
							// 点击取消 数据重置
							this.reset();
							this.flag=0;
						}else{
							this.$message.error("修改失败");
							this.load();
						}
					})	
				}
				
			},
			reset() {   //取消，重置数据
				this.dialogFormVisible = false;
				for (let key in this.store) {
					this.store[key] = "";
				}
				this.tempUrl='';
			},
			handleSelectionChange(val){
				this.multipleSelection = val;
			},
			handleCurrentChange(pageNum){	//查看下一页
				this.pageNum = pageNum;
				this.load();
			},
			load(){		//分页显示
				this.user=this.$cookies.get("user");
				if(this.user.power==2){
					this.$axios.post('http://localhost:8081/StorePage', {
						pageNum : this.pageNum,
						count : this.pageSize
					}).then(res => {
						this.tableData = res.data.data;
						this.total = res.data.countSun;
						}
					).catch(error => {
						console.log(error, '请求失败');
					})
				}else if(this.user.power==1){
					this.storeUtil.store.user_id=this.user.userid;
					this.$axios.post('http://localhost:8081/QueryStoreUser',this.storeUtil).then(res => {
						this.tableData = res.data.data;
						console.log(this.tableData);
						this.total = res.data.countSun;
						}
					).catch(error => {
						console.log(error, '请求失败');
					})
				}
				
			},
			changeStatus(row){		//修改店铺状态
				this.$axios.post('http://localhost:8081/StoreStatus', {
					store_id : row.store_id,
					store_status:row.store_status
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
			//将上传图片的原路径赋值给临时路径
			handleChange(file){
				this.tempUrl = URL.createObjectURL(file.raw);
			},
			httpRequest(item) {
				//验证图片格式大小信息
				const isJPG = item.type == 'image/jpeg' || item.file.type == 'image/png';
				const isLt2M = item.size / 1024 / 1024 < 2;
				if (!isJPG) {
					this.$message.error('上传图片只能是 JPG 或 PNG 格式!');
				}
				if (!isLt2M) {
					this.$message.error('上传图片大小不能超过 2MB!');
				}
				return isJPG && isLt2M;
			},
			
			//新图片的路径
			Image(url){
				this.store.store_imge=url;
				this.dialogFormVisible = true;
			},
			imgformat_store(row) { //图片显示
				if(row){
					let img = require("@/assets/images/store_imges/" +row);
					// console.log(img);
					return img;
				}
				return;
			},
			redact(data){		//编辑
				this.dialogFormVisible=true;
				this.store.store_id=data.store_id;
				this.store.store_name=data.store_name;
				this.store.store_title=data.store_title;
				this.store.store_site=data.store_site;
				this.tempUrl=require("@/assets/images/store_imges/" +data.store_imge);
				this.store.store_status=data.store_status;
				this.flag=1;
			},
		},
	}
</script>
<!--加上scoped能够防止样式之间的冲突-->
<style scoped>
    .el-main {
        display: block;
        flex: 1;
        flex-basis: auto;
        /* overflow: auto; */
        box-sizing: border-box;
    }
	.input{
		width: 400px;
		height: 20px;
	}
	.dialog{
		width: 600px;
		height: 400px;
	}
	.avatar {
		width: 178px;
		height: 178px;
		display: flex;
	}
	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}
</style>