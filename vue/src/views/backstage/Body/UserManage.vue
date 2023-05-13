<template>
    <div style="height: 901px;width: 100%">
        <el-main>
            <div style="margin-top: 10px;">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>用户管理</el-breadcrumb-item>
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
			<el-table :data="tableData" border stripe height="700px" @selection-change="handleSelectionChange" style="width: 1356px;">
				<el-table-column type="selection" width="55">				
				</el-table-column>
                <el-table-column prop="userid" label="id" width="200">
                </el-table-column>
                <el-table-column prop="username" label="昵称" width="200">
                </el-table-column>
                <el-table-column prop="account" label="账号" width="200">
                </el-table-column>
				<el-table-column prop="password" label="密码" width="200">
				</el-table-column>
				<el-table-column prop="logintime" label="登录时间" width="200">
				</el-table-column>
				<el-table-column prop="power" label="权限" width="100" align="center" >
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
				<el-dialog title="个人信息" :visible.sync="dialogFormVisible"  :append-to-body="true" width="600px" @close="reset()">
					<el-form :rules="rules" :model="user" ref="user" status-icon>
						<el-form-item prop="image">
							<span>用户头像：</span><br />
							<img v-if="tempUrl" :src="tempUrl" class="avatar">
							<el-upload
									action="http://localhost:8081/UserImage"
									:on-change="handleChange"
									:show-file-list="false"
									:before-upload="httpRequest"
									:on-success="Image"
									:auto-upload="true"
									><!--覆盖默认上传-->						
								<el-button slot="trigger" size="small" type="button" style="margin-top: 20px;">点击上传</el-button>
							</el-upload>
						</el-form-item>
						<el-form-item prop="username">
							<span>用户昵称</span><br />
							<el-input v-model="user.username" autocomplete="off" class="input" clearable></el-input>
						</el-form-item>
						<el-form-item prop="account">
							<span>用户账号</span><br />
							<el-input v-model="user.account" autocomplete="off" class="input" clearable></el-input>
						</el-form-item>
						<el-form-item prop="password">
							<span>用户密码</span><br />
							<el-input v-model="user.password" autocomplete="off" class="input" clearable></el-input>
						</el-form-item>
						<el-form-item prop="userphone">
							<span>联系电话</span><br />
							<el-input v-model="user.userphone" autocomplete="off" class="input" clearable></el-input>
						</el-form-item>
						<el-form-item prop="email">
							<span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</span><br />
							<el-input v-model="user.email" autocomplete="off" class="input" clearable></el-input>
						</el-form-item>
						<el-form-item prop="power">
							<span style="margin-right: 35px;">权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限</span><br />
							<el-select v-model="user.power" placeholder="请选择活动区域 ">
								<el-option label="管理员" value="2"></el-option>
								<el-option label="商家" value="1"></el-option>
								<el-option label="普通用户" value="0"></el-option>
							</el-select>
						</el-form-item>
					</el-form>
					<div slot="footer" class="dialog-footer">
						<el-button @click="reset()">取 消</el-button>
						<el-button type="primary" @click="submit()">确 定</el-button>
					</div>
				</el-dialog>
			</div>
        </el-main>
    </div>
</template>

<script>
    export default {
		components:{

		},
        data() {
			
            return {
				user:{
					userid:'',
					username:'',
					account:'',
					password:'',
					image:'',
					userphone:'',
					email:'',
					power:''			
				},
				tempUrl: '',
				flag:0,
				dialogFormVisible:false,
				multipleSelection:[],
				rules:{
					username:[
						{ required: true, message: '请输入用户昵称', trigger: 'blur' },
					],
					account:[
						{ required: true, message: '请输入用户账号', trigger: 'blur' },
						{ min: 6, message: '长度不得小于 6 个字符', trigger: 'blur' }
					],
					password:[
						{ required: true, message: '请输入用户密码', trigger: 'blur' },
						{ min: 6, message: '长度不得小于 6 个字符', trigger: 'blur' }
					],
					userphone:[
						{ required: true, message: '请输入联系电话', trigger: 'blur' },
						{ trigger: 'blur',
							validator: (rule, value, callback) => {
							var passwordreg = /^(((13[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(17[3-8]{1})|(18[0-9]{1})|(19[0-9]{1})|(14[5-7]{1}))+\d{8})$/
							if (!passwordreg.test(value)) {
								callback(
									new Error(
										'请输入正确的手机号码哦!'
									)
								)
							} else {
							callback()
								}
							}
					}	
					],
					email:[
						{ required: true, message: '请输入邮箱地址', trigger: 'blur' },
						{ type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
					],
					power:[
						{ required: true, message: '请输入用户权限', trigger: 'blur' },
					]
				},
                tableData:[],
				total:0,
				pageNum:1,
				pageSize:10,
            }
        },
		created(){
			this.load();			
		},
		methods:{
			open(row) {
				this.$confirm('此操作将永久删除该用户信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.post('http://localhost:8081/deluser', {
						account : row.account,
					}).then(res => {
						if(res){
							this.load();
							this.$message.account("删除成功");
						}else{
							this.load();
							this.$message.error("删除失败");
						}
					})			
				});
			},
			dels(){
				let ids=this.multipleSelection.map(value => value.userid)//[{}]=>[1,2]
				console.log(ids);
				this.$axios.post("http://localhost:8081/userdels",ids).then(res=> {
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
			submit() {
				if(!this.flag){
					this.dialogFormVisible = false;
					this.$axios.post('http://localhost:8081/usersave', {
						username:this.user.username,
						account: this.user.account,
						password:this.user.password,
						image:this.user.image,
						userphone:this.user.userphone,
						email:this.user.email,
						power:this.user.power
					}).then(res => {
						if(res){
							this.$message.success("新增成功");
							this.load();
							// 点击取消 数据重置
							this.reset();
							this.flag=0;
						}else{
							this.$message.error("新增失败");
							this.load();
							this.flag=0;
						}
					})
				}else{
					this.dialogFormVisible = false;
					this.$axios.post('http://localhost:8081/UpdateUser', {
						userid:this.user.userid,
						username:this.user.username,
						account: this.user.account,
						password:this.user.password,
						image:this.user.image,
						userphone:this.user.userphone,
						email:this.user.email,
						power:this.user.power
					}).then(res => {
						if(res){
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
			reset() {
				this.dialogFormVisible = false;
				for (let key in this.user) {
					this.user[key] = "";
				}
				this.tempUrl='';
			},
			handleSelectionChange(val){
				this.multipleSelection = val;
			},
			handleCurrentChange(pageNum){
				this.pageNum = pageNum;
				this.load();
			},
			load(){
				this.$axios.post('http://localhost:8081/PagingUser', {
					pageNum : this.pageNum,
					count : this.pageSize
				}).then(res => {
					this.tableData = res.data.data;
					this.power(this.tableData);
					this.total = res.data.countSun;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			power(date){ //power转换为文字
				for (let x in date) {
					if(date[x].power == 0){
						date[x].power = '普通用户';
					}else if(date[x].power == 1){
						date[x].power = '商家';
					}else if(date[x].power == 2){
						date[x].power = '管理员';
					}
				}
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
				this.user.image=url;
			},
			imgformat_user(row) { //图片显示
				if(row){
					let img = require("@/assets/images/user_imges/" +row);
					// console.log(img);
					return img;
				}
				return;
			},
			redact(data){		//编辑
				this.dialogFormVisible=true;
				if(data.power=="管理员"){
					this.user.power=2;
				}else if(data.power=="商家"){
					this.user.power=1;
				}else if(data.power=="普通用户"){
					this.user.power=0;
				}
				this.user.userid=data.userid;
				this.user.account=data.account;
				this.user.username=data.username;
				this.user.password=data.password;
				this.user.email=data.email;
				this.user.image=data.image;
				this.user.userphone=data.userphone;
				if(data.image){
					this.tempUrl=require("@/assets/images/user_imges/" +data.image);
				}
				this.flag=1;
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
</style>
