<template>
	<div class="login">
		<div class="login1">
			<div style="height: 60px;width: 369px;"></div>
			<div style="text-align: center;margin-bottom: 30px;">
			<span style="font-size: 35px;">注册界面</span>
			</div>
			<div class="input">
				<span style="margin-left: 15px;margin-top: 20px;">用户名:</span><br />
				<el-input placeholder="请输入用户名" style="height: 25px;width: 273.5px;margin: 10px;" size="mini" v-model="regFromData.username"></el-input><br />
			</div>
			<div class="input">
				<span style="margin-left: 15px;margin-top: 20px;">账&nbsp;&nbsp;&nbsp;号:</span><br />
				<el-input placeholder="请输入账号" style="height: 25px;width: 273.5px;margin: 10px;" size="mini" v-model="regFromData.account"></el-input><br />
			</div>
			<div class="input">
				<span style="margin-left: 17px;">密&nbsp;&nbsp;&nbsp;码:</span><br />
				<el-input placeholder="请输入密码" style="height: 25px;width: 273.5px;margin: 10px;"  size="mini" v-model="regFromData.password"></el-input><br />
			</div>
			<div class="input">
				<span style="margin-left: 15px;margin-top: 20px;">电&nbsp;&nbsp;&nbsp;话:</span><br />
				<el-input placeholder="请输入电话" style="height: 25px;width: 273.5px;margin: 10px;" size="mini" v-model="regFromData.userphone" maxlength="11"></el-input><br />
			</div>
			<el-button type="primary" style="margin-top: 15px;width: 273.5px;margin-left: 45px;" size="mini" @click="submitForm()">注册</el-button><br />
			<div style="margin-left: 220px;margin-top: 5px;">
				<el-link type="primary" href="http://localhost:8080/#/login">已有账号?登录</el-link>
			</div>
		</div>
	</div>
</template>
<style type="text/css" scoped>
	.login{
		height: 100vh;
		display: flex;
		background:url("../assets/images/login.png") no-repeat;
		background-size:cover;
	}
	.login1{
		height: 520px;
		width: 369px;
		margin-top: 15vh;
		margin-left: 80vh;
		background-color: aliceblue;
	}
	.input{
		margin-top:5px;
		margin-left:35px;
	}
</style>
<script>
	export default {
		name:"register",
		data() {
			return {
				regFromData:{
					username:'',
					account:'',
					password:'',
					userphone:'',
				},
			}
		},
		methods:{
			submitForm(){
				this.$axios.post('http://localhost:8081/register', this.regFromData).then(res => {
					if(res.data=='注册成功'){
						this.$message.success("注册成功");
					}
				}).catch(error => {
					console.log(error);
					this.$message.error("注册失败,请重试");
				})
	
			},
		}
	}
</script>