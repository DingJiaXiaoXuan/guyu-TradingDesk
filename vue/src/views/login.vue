<template>
	<div class="login">
		<div class="login1">
			<div style="height: 60px;width: 369px;"></div>
			<div style="text-align: center;">
			<span style="font-size: 35px;">登录界面</span>
			</div>
			<div style="margin-top: 60px;margin-left: 35px;">
				<span style="margin-left: 15px;margin-top: 20px;">账&nbsp;&nbsp;&nbsp;号:</span><br />
				<el-input placeholder="请输入账号" style="height: 25px;width: 273.5px;margin: 10px;" autocomplete="off" v-model="loginFromData.account"></el-input><br />
			</div>
			<div style="margin-top: 20px;margin-left: 35px;">
				<span style="margin-left: 17px;">密&nbsp;&nbsp;&nbsp;码:</span><br />
				<el-input placeholder="请输入密码" style="height: 25px;width: 273.5px;margin: 10px;margin-bottom: 5px;" show-password autocomplete="off" v-model="loginFromData.password"></el-input><br />
			</div>
			<div style="margin-left: 220px;margin-top: 15px;">
				<el-link type="primary">忘记密码/</el-link><el-link type="primary" href="http://localhost:8080/#/register" >注册</el-link>
			</div>
			<span ></span>
			<el-button type="primary" style="margin-top: 25px;width: 273.5px;margin-left: 45px;" @click="submitForm()">登录</el-button><br />
		</div>
	</div>
</template>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<style type="text/css">
	.login{
		height: 100vh;
		display: flex;
		background:url("../assets/images/login.png") no-repeat;
		background-size:cover;
		
		
	}
	.login1{
		height: 485px;
		width: 369px;
		margin-top: 15vh;
		margin-left: 80vh;
		background-color: aliceblue;
	}
	.login_form{

	}
</style>
<script>
	export default {
		name:"login",
		data() {
			return {
				loginFromData:{
					account:'',
					password:''
				},
			}
		},
		methods:{
			//登录方法
			submitForm(){
				this.$axios.post('http://localhost:8081/login', this.loginFromData).then(res => {
					let code = res.data.state;
					if (code == '1000'){
						if(res.data.data.power==0){
							alert("当前用户权限不足，请核实身份后再试");
						}else if(res.data.data.power==1||res.data.data.power==2){
							this.$cookies.set("user",res.data.data);
							alert("登录成功，点击跳转首页");
							this.$router.push({
								path:"/backstage/home",
							});
						}
					}else if (code == '400'){
						alert("当前用户名或密码错误");
					}
				}).catch(error => {
					console.log(error, '请求失败');
				})

			},
		}
	}
</script>

