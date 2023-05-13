<template>
	<div>
		<div class="bread">
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item>
					<el-link :underline="false" @click="send(module.FrontPersonal)">个人服务</el-link>
				</el-breadcrumb-item>
				<el-breadcrumb-item>个人信息</el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="card">
			<el-tabs tab-position="left" style="height: 600px;" type="border-card">
				<el-tab-pane label="基本信息">
					<div style="margin-left: 10px;font-size: 16px;margin-top: 30px;">
						<div style="margin-bottom: 20px;">
							<span style="margin-right: 10px;">昵称</span>
							<span>{{user.username}}</span>
						</div>
						<div style="margin-bottom: 20px;">
							<span style="margin-right: 10px;">账号</span>
							<span>{{user.account}}</span>
						</div>
						<div style="margin-bottom: 20px;">
							<span style="margin-right: 10px;">电话</span>
							<span>{{user.userphone}}</span>
						</div>
						<div style="margin-bottom: 20px;">
							<span style="margin-right: 10px;">邮箱</span>
							<span>{{user.email}}</span>
						</div>
						<div style="margin-bottom: 20px;">
							<span style="margin-right: 10px;">权限</span>
							<span v-if="user.power==0">普通用户</span>
							<span v-else-if="user.power==1">商家</span>
							<span v-else-if="user.power==2">管理员</span>
						</div>
					</div>
				</el-tab-pane>
				<el-tab-pane label="收货地址">
					<div style="margin-top: 10px;"  v-for="(item, index) in address" :key="index">
						<el-card style="width: 900px;height: 100px;">
							<div style="width: 740px;float: left;" >
								<span style="font-size: 18px;">{{item.consignee}}</span>
								<span style="font-size: 16px;margin-left: 15px;">{{item.phone}}</span>
								<div style="font-size: 18px;margin-top: 5px;margin-top: 20px;" >{{item.address}}</div>
							</div>
							<div style="width: 100px;height: 70px;float: left;border-left:1px solid #d3dce6;">
								<div style="margin-left: 20px;">
									<el-button type="primary" size="mini" @click="address_redact(item)">编辑</el-button>	
								</div>
								<div style="margin-left: 20px;margin-top: 10px;">
									<el-button type="danger" size="mini" @click="address_del(item.address_id)">删除</el-button>
								</div>
							</div>
						</el-card>
					</div>
					<div style="margin-top: 20px;">
						<el-button type="primary" size="small" @click="addressFormVisible=true">添加收货地址</el-button>
					</div>
				</el-tab-pane>
			</el-tabs>
		</div>
		<div v-if="addressFormVisible">
			<el-dialog title="收货地址" :visible.sync="addressFormVisible" @close="reset()" width="600px">
				<div style="margin-top: 20px;margin-left: 35px;">
					<span style="margin-left: 15px;margin-top: 20px;">收货人名称:</span>
					<el-input placeholder="请输入名称" style="height: 25px;width: 273.5px;margin: 10px;margin-left: 40px;" autocomplete="off" v-model="addressFrom.consignee"></el-input><br />
				</div>
				<div style="margin-top: 20px;margin-left: 35px;">
					<span style="margin-left: 17px;">收货地址:</span>
					<el-input placeholder="请输入地址" style="height: 25px;width: 273.5px;margin: 10px;margin-bottom: 5px;margin-left: 50px;" autocomplete="off" v-model="addressFrom.address"></el-input><br />
				</div>
				<div style="margin-top: 20px;margin-left: 35px;">
					<span style="margin-left: 15px;margin-top: 20px;">联系电话:</span>
					<el-input placeholder="请输入电话" style="height: 25px;width: 273.5px;margin: 10px;margin-left: 50px;" autocomplete="off" v-model="addressFrom.phone" maxlength="11"></el-input><br />
				</div>
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
			this.user=this.$cookies.get("user");
			this.load();
		},
		data() {
			return {
				user:null,
				address:null,
				addressFrom:{
					address_id:null,
					consignee:null,
					address:null,
					phone:null,
					address_status:0
				},
				addressFormVisible:false,
				module:{
					FrontPersonal:"FrontPersonal",
				},
				flag:0,
			}
		},
		methods:{
			send(data){	//切换组件
				this.$emit('jump',data);
			},
			reset() {	//清空表单内容
				this.addressFormVisible = false;
				for (let key in this.addressFrom) {
					this.addressFrom[key] = "";
				}
				this.flag=0;
			},
			load(){		//获取收货地址
				this.$axios.post('http://localhost:8081/QueryAddress', {
					user_id:this.user.userid
				}).then(res => {
					this.address= res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			submit() {		//添加收货地址
				if(this.addressFrom.consignee==null||this.addressFrom.address==null||this.addressFrom.phone==null){
					this.$message.info('请输入完整收货信息');
					return;
				}
				if(!this.flag){
					this.$axios.post('http://localhost:8081/addAddress', {
						user_id:this.user.userid,
						consignee:this.addressFrom.consignee,
						address:this.addressFrom.address,
						phone:this.addressFrom.phone,
						address_status:this.addressFrom.address_status
					}).then(res => {
						if(res.data==1){
							this.$message.success('添加成功');
							this.addressFormVisible = false;
							this.load();
							this.reset();
						}
					})
				}else{
					this.$axios.post('http://localhost:8081/updataAddress', {
						address_id:this.addressFrom.address_id,
						consignee:this.addressFrom.consignee,
						address:this.addressFrom.address,
						phone:this.addressFrom.phone
					}).then(res => {
						if(res.data==1){
							this.$message.success('修改成功');
							this.addressFormVisible = false;
							this.load();
							this.reset();
							this.flag=0;
						}else{
							this.$message.error('删除失败');
						}
					})
				}
			},
			address_del(data){	//删除收货地址
				this.$axios.post('http://localhost:8081/DelAddress', {
					address_id:data
				}).then(res => {
					if(res.data==1){
						this.$message.success('删除成功');
						this.load();
					}else{
						this.$message.error('删除失败');
					}
				})
			},
			address_redact(data){	//编辑
				this.addressFrom.address_id=data.address_id;
				this.addressFrom.consignee=data.consignee;
				this.addressFrom.address=data.address;
				this.addressFrom.phone=data.phone;
				this.addressFormVisible=true;
				this.flag=1;
			}
		},
		
	}
</script>

<style scoped>
	.bread{
		font-size: 32px;
	}
	.card{
		margin-top: 20px;
	}
</style>