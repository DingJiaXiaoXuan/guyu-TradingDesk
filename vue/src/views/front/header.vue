<template>
	<div class="header">
		<div class="h_s">
			<span class="h_s_1">欢迎来到谷雨商城!</span>
			<span v-show="!Frontlogin">
				<el-link @click="dialogFormVisible=true" type="primary" class="h_s_1" :underline="false">请登录</el-link>
				<el-link @click="registerVisible=true" class="h_s_1" :underline="false">免费注册</el-link>
			</span>
			<span v-if="Frontlogin">
				<span class="h_s_1" >{{user.username}}</span>
				<el-link class="h_s_1" :underline="false" @click="log_out()">退出</el-link>
			</span>
			<div class="h_s_2">
				<span class="h_s_1">个人中心</span>
				<span class="h_s_1">卖家中心</span>
			</div>
		</div>
		<div class="h_c">
			<span class="h_c_1">
			<el-image
				style="width: 200px; height: 100px"
				:src="url"
				></el-image>
			</span>
			<span class="h_c_2">
				<el-input
					style="width: 300px;"
					size="medium"
					placeholder="请输入名称"
					prefix-icon="el-icon-search"
				>
				</el-input>
				<el-button style="margin: 5px;" type="primary" plain size="medium">搜索</el-button>
				<el-button style="margin: 5px;" type="primary" plain size="medium">免费供给</el-button>
			</span>
		</div>
		<div class="h_Nav">
			<el-menu  mode="horizontal" >
				<el-menu-item index="1" @click="send(module.home)">首页</el-menu-item>
				<el-submenu index="2">
					<template slot="title">所有商品</template>
					<el-menu-item v-for="(item, index) in main" :key="index" index="index" @click="send_Main(item)">{{item.main_name}}</el-menu-item>
				</el-submenu>
				<el-menu-item index="3" @click="send(module.notice)">公告展示</el-menu-item>
				<el-menu-item index="4" @click="send_login(module.card)">购物车</el-menu-item>
				<el-menu-item index="5" @click="send_login(module.personal)">个人服务</el-menu-item>
			</el-menu>
		</div>
		<div v-if="dialogFormVisible">
			<el-dialog title="登录" :visible.sync="dialogFormVisible"  :append-to-body="true" width="400px" @close="reset()">
				<div style="margin-left: 35px;">
					<span style="margin-left: 15px;margin-top: 20px;">账&nbsp;&nbsp;&nbsp;号:</span><br />
					<el-input placeholder="请输入账号" style="height: 25px;width: 273.5px;margin: 10px;" autocomplete="off" v-model="loginFromData.account"></el-input><br />
				</div>
				<div style="margin-top: 20px;margin-left: 35px;">
					<span style="margin-left: 17px;">密&nbsp;&nbsp;&nbsp;码:</span><br />
					<el-input placeholder="请输入密码" style="height: 25px;width: 273.5px;margin: 10px;margin-bottom: 5px;" show-password autocomplete="off" v-model="loginFromData.password"></el-input><br />
				</div>
				<div style="margin-left: 220px;margin-top: 15px;">
					<el-link type="primary">忘记密码/</el-link><el-link type="primary" @click="getReg()" >注册</el-link>
				</div>
				<span ></span>
				<el-button type="primary" style="margin-top: 25px;width: 273.5px;margin-left: 45px;" @click="submitForm()">登录</el-button><br />
			
			</el-dialog>
		</div>
		<div v-if="registerVisible">
			<el-dialog title="注册" :visible.sync="registerVisible"  :append-to-body="true" width="400px" @close="Regreset()">
				<div style="margin-left: 35px;">
					<span style="margin-left: 15px;margin-top: 20px;">用户名:</span><br />
					<el-input placeholder="请输入用户名" style="height: 25px;width: 273.5px;margin: 10px;" v-model="regFromData.username"></el-input><br />
				</div>
				<div style="margin-top: 20px;margin-left: 35px;">
					<span style="margin-left: 15px;margin-top: 20px;">账&nbsp;&nbsp;&nbsp;号:</span><br />
					<el-input placeholder="请输入账号" style="height: 25px;width: 273.5px;margin: 10px;"  v-model="regFromData.account"></el-input><br />
				</div>
				<div style="margin-top: 20px;margin-left: 35px;">
					<span style="margin-left: 17px;">密&nbsp;&nbsp;&nbsp;码:</span><br />
					<el-input placeholder="请输入密码" style="height: 25px;width: 273.5px;margin: 10px;"  v-model="regFromData.password"></el-input><br />
				</div>
				<div style="margin-top: 20px;margin-left: 35px;">
					<span style="margin-left: 15px;margin-top: 20px;">电&nbsp;&nbsp;&nbsp;话:</span><br />
					<el-input placeholder="请输入电话" style="height: 25px;width: 273.5px;margin: 10px;"  v-model="regFromData.userphone" maxlength="11"></el-input><br />
				</div>
				<el-button type="primary" style="margin-top: 25px;width: 273.5px;margin-left: 45px;" @click="Regsubmit()">注册</el-button><br />
				<div style="margin-left: 220px;margin-top: 5px;">
					<el-link type="primary" @click="getLogin()">已有账号?登录</el-link>
				</div>	
			</el-dialog>
		</div>
	</div>
</template>

<script>
	export default {
		created(){
			this.login();
			this.load_Main();
		},
		data() {
			return {
				Frontlogin:false,
				dialogFormVisible:false,
				registerVisible:false,
				user:{},
				main:{},
				loginFromData:{
					account:'',
					password:''
				},
				regFromData:{
					username:'',
					account:'',
					password:'',
					userphone:'',
				},
				url:require('../../assets/images/logo.jpg'),
				module:{
					home:'FrontIndex',	
					card:'FrontCard',
					personal:'FrontPersonal',
					notice:'FrontNotice',
				},
				cate:{
					Allpro:'FrontAllPro',
					data:[],
				}
			}
		},
		methods:{
           send(data){	//切换组件
				this.$emit('header',data);
			},
			send_Main(data){
				this.cate.data=data;
				this.$emit('cate_mian',this.cate);
			},
			send_login(data){
				if(this.user){
					this.$emit('header',data);
				}else{
					this.dialogFormVisible=true;
				}	
			},
			reset() {   //取消，重置数据
				this.dialogFormVisible = false;
				for (let key in this.loginFromData) {
					this.loginFromData[key] = "";
				}
			},
			Regreset(){
				this.registerVisible=false;
				for (let key in this.regFromData) {
					this.regFromData[key] = "";
				}
			},
			login(){	//登录状态
				this.user=this.$cookies.get("user");
				if(this.$cookies.get("user")==null){
					this.Frontlogin=false;
				}else{
					this.Frontlogin=true;
				}
			},
			submitForm(){	//登录
				this.$axios.post('http://localhost:8081/login', this.loginFromData).then(res => {
					let code = res.data.state;
					this.user=res.data.data;
					this.$cookies.set("user",res.data.data);
					if (code == '1000'){
						this.dialogFormVisible=false;
						this.$message.success('账号登录成功');
						this.login();
						this.reset();
					}else if (code == '400'){
						this.$message.error('账号或密码错误');
					}
				})
			},
			log_out(){	//退出登录
				this.$cookies.remove("user",'');
				this.user=null;
				this.Frontlogin=false;
			},
			Regsubmit(){		//注册
				this.$axios.post('http://localhost:8081/register', this.regFromData).then(res => {
					if(res.data=='注册成功'){
						this.$message.success("注册成功");
					}
				}).catch(error => {
					console.log(error);
					this.$message.error("注册失败,请重试");
				})
			},
			getLogin(){		//去登录界面
				this.dialogFormVisible=true;
				this.registerVisible=false;
			},
			getReg(){		//去注册界面
				this.dialogFormVisible=false;
				this.registerVisible=true;
			},
			load_Main(){	//分类选择
				this.$axios.post('http://localhost:8081/FindCateMain', {
				}).then(res => {
					this.main = res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
		},
		
	}
</script>

<style scoped>
	.header{
		width: 1200px;
	}
	.h_s{
		width: 1200px;
		height: 24px;
		background-color: #d3d7d4;
		display: flex;
	}
	.h_s_1{
		font-size: 12px;
		margin-right: 20px;
		line-height: 24px;
	}
	.h_s_2{
		width: 140px;
		height: 24px;
		position:fixed;
		margin-left: 1060px;
	}
	.h_c{
		width: 1200px;
		height: 180px;
		background-color: aliceblue;
		display: flex;
		margin-top: 20px;
	}
	.h_c_1{
		padding-top: 40px;
		margin-left: 20px;
	}
	.h_c_2{
		
		padding-top: 40px;
		margin-left: 160px;
	}
	.h_Nav{
		height: 50px;
		width: 1200px;
	}
	.el-menu.el-menu--horizontal {
		border-top: solid 1px #e6e6e6 ;
		border-bottom: solid 1px #e6e6e6;
	}
</style>