<template>

    <div id="app">
        <div style="width: 260px;font-size: 18px;">
        <span>谷雨商城后台管理系统,欢迎你！</span>
        </div>
        <div style="display: flex;align-items: center;">
        <span style="margin-right: 10px;"><el-avatar  :src="circleUrl"></el-avatar></span>
        <el-dropdown>
          <span class="el-dropdown-link">
            {{user.username}}<i class="el-icon-arrow-down el-icon--right"></i>
         </span>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
				<span @click="dialogFormVisible1 = true;">个人信息</span>
				<div v-if="dialogFormVisible1">
					<el-dialog title="个人信息" :visible.sync="dialogFormVisible1" :append-to-body="true" width="600px">
						<el-form>
							<el-form-item>
								<span>用户昵称</span><br />
								<el-input v-model="user.username" autocomplete="off" class="input"></el-input>
							</el-form-item>
							<el-form-item>
								<span>用户账号</span><br />
								<el-input v-model="user.account" autocomplete="off" class="input" :disabled="true"></el-input>
							</el-form-item>
							<el-form-item>
								<span>联系电话</span><br />
								<el-input v-model="user.userphone" autocomplete="off" class="input"></el-input>
							</el-form-item>
							<el-form-item>
								<span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</span><br />
								<el-input v-model="user.email" autocomplete="off" class="input"></el-input>
							</el-form-item>
							
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="dialogFormVisible1 = false">取 消</el-button>
							<el-button type="primary" @click="dialogFormVisible1 = false">确 定</el-button>
						</div>
					</el-dialog>
				</div>
				</el-dropdown-item>
                <el-dropdown-item><el-link :underline="false" @click="log_out()">退出登录</el-link></el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
        </div>
		
    </div>
</template>

<!--加上scoped能够防止样式之间的冲突-->
<style scoped>
	.input{
		width: 400px;
		height: 20px;
	}
</style>

<script>
	
    export default {
		data(){
			var validatePass2 = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请再次输入密码'))
				} else if (value !== this.newpassword1) {
						callback(new Error('两次输入密码不一致!'))
					} else {
						callback()
					}
			}
			return{				
				user:[],
				dialogFormVisible1:false,
				dialogFormVisible2:false,
				circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
				rules:{				
					password:[
						{ required: true, message: '请输入原密码', trigger: 'blur' },
					],
					newpassword1:[
						{ required: true, message: '请输入新密码', trigger: 'blur' },
						{ min: 6, message: '长度不得小于 6 个字符', trigger: 'blur' }
					],
					newpassword2:[
						{ required: true, message: '请再次输入密码', trigger: 'blur' },
						{ min: 6, validator: validatePass2, trigger: 'blur' }
					],
				},
			}
			
		},
		components:{

		},
		created(){
			this.load();
			this.NotPower();
		},
		methods:{
			load(){	//显示个人信息
				this.user=this.$cookies.get("user");
				this.circleUrl=require("@/assets/images/user_imges/" +this.user.image)
			},
			log_out(){	//退出登录
				this.$cookies.remove("user",'');
				this.$router.push({
					path:"/login",
				});
			},
			NotPower(){
				if(!this.user||this.user.power==0){
					this.$router.push({
						path:"/login",
					});
				}
			}
			
		},
	}		
</script>

