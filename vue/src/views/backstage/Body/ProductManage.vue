<template>
    <div style="height: 901px;width: 100%">
        <el-main>
            <div style="margin-top: 10px;">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>商品管理</el-breadcrumb-item>
					<el-breadcrumb-item>商品信息</el-breadcrumb-item>
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
			<div style="margin-top: 20px;margin-bottom: 20px;" v-if="user.power==1">
				<span style="margin-right: 20px;">店铺:</span>
				<el-select v-model="store_id" placeholder="请选择店铺" clearable @change="load()"> 
					<el-option
						v-for="item in store"
						:key="item.store_id"
						:label="item.store_name"
						:value="item.store_id">
					<span>{{item.store_name}}</span>
					</el-option>
				</el-select>
			</div>
			<el-table :data="tableData" border stripe height="700px" @selection-change="handleSelectionChange" style="width: 2156px;">
				<el-table-column type="selection" width="55">				
				</el-table-column>
                <el-table-column prop="pro_id" label="商品编号" width="200">
                </el-table-column>
                <el-table-column prop="pro_name" label="商品名称" width="200">
                </el-table-column>
                <el-table-column prop="category.cate_name" label="商品种类" width="100">
                </el-table-column>
				<el-table-column prop="origin" label="商品产地" width="300">
				</el-table-column>
				<el-table-column prop="amount" label="商品销量" width="200">
				</el-table-column>
				<el-table-column prop="user.username" label="商家名称" width="200">
				</el-table-column>
				<el-table-column prop="store.store_name" label="店铺名称" width="200">
				</el-table-column>	
				<el-table-column prop="pro_status" label="上架" width="100" align="center">
					<template slot-scope="scope">
						<div v-if="scope.row.pro_status==1||scope.row.pro_status==2">
							<el-switch  v-model="scope.row.pro_status"
								active-color="#13ce66"
								inactive-color="#ff4949"
								:active-value="2"
								:inactive-value="1"
								@change="changeStatus($event,scope.row)"></el-switch>
						</div>
						<div v-else-if="scope.row.pro_status==0">
							<el-tag type="warning">审核中</el-tag>
						</div>
						<div v-else-if="scope.row.pro_status==3">
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
						<el-button type="success" @click="Edit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
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
				<el-dialog title="发布商品" :visible.sync="dialogFormVisible" :append-to-body="true" width="600px" @close="reset()">
					<el-form  :model="product" ref="product" status-icon label-position='top'>
						<el-form-item label="商品图片" prop="image">
							<img v-if="tempUrl" :src="tempUrl" class="avatar">
							<el-upload
									action="http://localhost:8081/ProImage"
									:on-change="handleChange"
									:show-file-list="false"
									:before-upload="httpRequest"
									:on-success="Image"
									:auto-upload="true"
									><!--覆盖默认上传-->						
								<el-button slot="trigger" size="small" type="button" style="margin-top: 20px;">点击上传</el-button>
							</el-upload>
						</el-form-item>
						<el-form-item prop="pro_name" label="商品名称">
							<el-input v-model="product.pro_name" autocomplete="off" class="input" clearable></el-input>
						</el-form-item>
						<el-form-item prop="title" label="货品标题">
							<el-input
								type="textarea"
								:autosize="{ minRows: 4, maxRows: 6}"
								placeholder="请输入内容"
								v-model="product.title">
							</el-input>
						</el-form-item>
						<el-form-item prop="detail" label="货品简介">
							<el-input
								type="textarea"
								:autosize="{ minRows: 4, maxRows: 6}"
								placeholder="请输入内容"
								v-model="product.detail">
							</el-input>
						</el-form-item>
						<el-form-item prop="cate_id" label="货品类别">
							<span>
								<el-select v-model="main_id" placeholder="请选择分类" clearable @change="New_Main">
									<el-option
										v-for="item in CateMain"
										:key="item.main_id"
										:label="item.main_name"
										:value="item.main_id">
									<span>{{item.main_name}}</span>
									</el-option>
								</el-select>
							</span>
							<span style="margin-left: 20px;" v-if="main_id">
								<el-select v-model="cate_id" placeholder="请选择种类" clearable  @change="New_Cate">
									<el-option
										v-for="item in Category"
										:key="item.cate_id"
										:label="item.cate_name"
										:value="item.cate_id">
									<span>{{item.cate_name}}</span>
									</el-option>
								</el-select>
							</span>
						</el-form-item>
						<el-form-item prop="price" label="商品规格" v-if="flag">
							<el-button type="success" @click="get_size()">添加规格</el-button>
							<el-dialog width="600px" title="设置规格与价格" :visible.sync="SizeVisible" append-to-body @close="reset_size()"> 
								<el-form  :model="size" ref="size" status-icon label-position='top'>
									<el-form-item label="规格描述">
										<el-input v-model="size.size_title" class="input" clearable></el-input>
									</el-form-item>
									<el-form-item label="商品单价">
										<el-input v-model="size.pro_price"  class="input" clearable></el-input>
									</el-form-item>
									<el-form-item label="单位">
										<el-input v-model="size.unit" class="input" clearable></el-input>
									</el-form-item>
									<el-form-item label="起批量">
										<el-input v-model="size.min_num" class="input" clearable></el-input>
									</el-form-item>
									<el-form-item label="最大购买量">
										<el-input v-model="size.max_num" class="input" clearable></el-input>
									</el-form-item>
									<el-form-item label="禁用/启用">
										<el-switch  v-model="size.size_status"
											active-color="#13ce66"
											inactive-color="#ff4949"
											:active-value="1"
											:inactive-value="0"
											@change="New_SizeStatus"></el-switch>
									</el-form-item>
								</el-form>
								<span slot="footer">
									<el-button type="success" @click="AddSize(size)">保存</el-button>
								</span>
							</el-dialog>
							<el-table :data="sizelist" stripe style="width: 100%" v-if="sizelist.length">
								<el-table-column prop="size_title" label="规格描述" width="180">
								</el-table-column>
								<el-table-column prop="pro_price" label="商品单价" width="180">
								</el-table-column>
								<el-table-column prop="unit" label="单位" width="180">
								</el-table-column>
								<el-table-column prop="min_num" label="起批量" width="180">
								</el-table-column>
								<el-table-column prop="max_num" label="最大购买量" width="180">
								</el-table-column>
								<el-table-column prop="size_status" label="状态" width="180">
									<template slot-scope="scope">
										<span v-if="scope.row.size_status">启用</span>
										<span v-else>禁用</span>
									</template>
								</el-table-column>
								<el-table-column label="操作" width="200">
									<template slot-scope="scope">
										<el-button type="success" @click="Edit_size(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
										<el-button type="danger" @click.native.prevent="Del_size(scope.row)">删除<i class="el-icon-remove-outline"></i></el-button>
									</template>
								</el-table-column>
							</el-table>
						</el-form-item>
						<el-form-item prop="origin" label="产地">
							<el-input v-model="product.origin" autocomplete="off" class="input" clearable></el-input>
						</el-form-item>
						<el-form-item label="所属店铺">
							<el-select v-model="New_store_id" placeholder="请选择店铺" clearable @change="New_Store">
								<el-option
									v-for="item in store"
									:key="item.store_id"
									:label="item.store_name"
									:value="item.store_id">
								<span>{{item.store_name}}</span>
								</el-option>
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
</style>
<script>
	export default{
		created(){
			this.user=this.$cookies.get("user");
			this.load();
			this.load_store();
			this.load_Main();
		},
		data(){
			return{
				user:{},
				store:{},
				CateMain:{},
				Category:{},
				main_id:null,
				cate_id:null,
				sizelist:[],
				SizeVisible:false,
				product:{
					pro_id:'',
					pro_name:'',
					cate_id:'',
					origin:'',
					amount:0,
					price:'',
					image:'',
					title:'',
					detail:'',
					pro_status:0,		//状态默认为0
					user_id:'',
					store_id:''
				},
				size:{
					size_id:null,
					pro_id:null,
					size_title:null,
					pro_price:null,
					unit:null,
					min_num:null,
					max_num:null,
					size_status:1
				},
				dialogFormVisible:false,
				multipleSelection:[],
				tableData:[],
				total:0,
				pageNum:1,
				pageSize:10,
				store_id:null,
				New_store_id:null,
				tempUrl: '',
				flag:0,
				flag_size:0,
				productUtil:{
					page:{
						pageNum:1,
						count:25
					},
					product:{
						user_id:null,
						store_id:null
					}
				},
			}
		},
		methods:{
			open(row) {		//删除商品
				this.$confirm('此操作将永久删除该商品信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.post('http://localhost:8081/ProDel', {
						pro_id : row.pro_id,
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
			dels(){			//批量删除商品
				let ids=this.multipleSelection.map(value => value.pro_id)//[{}]=>[1,2]
				//console.log(ids);
				this.$axios.post("http://localhost:8081/ProDels",ids).then(res=> {
				if (res.data) {
					this.$message.success("批量删除成功!");
					this.load();
				}else if (res.data==0){
					this.$message.error("请选择!")
					this.load();
				}
				else {
					this.$message.error("批量删除失败!")
					this.load();
				}
				})
			},
			submit() {		//添加商品
				if(!this.flag){
					this.product.user_id=this.user.userid;
					this.product.amount=0;
					this.product.pro_status=0;
					this.$axios.post('http://localhost:8081/ProAdd',this.product).then(res => {
						if(res.data==1){
							this.dialogFormVisible=false;
							this.$message({
							type: 'success',
							message: '添加商品成功!'
							});
							this.reset();
							this.load();
							this.flag=0;
						}
					}
					).catch(error => {
						console.log(error, '请求失败');
					})
				}else{
					this.$axios.post('http://localhost:8081/ProUpdate',this.product).then(res => {
						if(res){
							this.$message.success("编辑成功");
							this.dialogFormVisible = false;
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
			reset() {			//关闭添加商品对话框
				this.dialogFormVisible = false;
				for (let key in this.product) {
					this.product[key] = "";
				}
				this.tempUrl="";
			},
			handleSelectionChange(val){			//多选
				this.multipleSelection = val;
			},
			handleCurrentChange(pageNum){		//换页
				this.pageNum = pageNum;
				this.load();
			},
			load(){				//请求商品信息
				if(this.user.power==2){
					this.$axios.post('http://localhost:8081/ProPage', {
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
					if(this.store_id==null){
						this.productUtil.product.user_id=this.user.userid;
						this.$axios.post('http://localhost:8081/UserPro',this.productUtil).then(res => {
							this.tableData = res.data.data;
							this.total = res.data.countSun;
							}
						).catch(error => {
							console.log(error, '请求失败');
						})
					}else{
						this.productUtil.product.store_id=this.store_id;
						this.$axios.post('http://localhost:8081/StorePro',this.productUtil).then(res => {
							this.tableData = res.data.data;
							this.total = res.data.countSun;
							}
						).catch(error => {
							console.log(error, '请求失败');
						})
					}
				}
			},
			load_store(){	//请求店铺信息
				this.$axios.post('http://localhost:8081/QueryUserStore', {
					user_id:this.user.userid
				}).then(res => {
					this.store=res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			New_Store(data){		//店铺选择器赋值
				this.product.store_id=data;
			},
			load_Main(){	//请求分类信息
				this.$axios.post('http://localhost:8081/FindCateMain').then(res => {
					this.CateMain=res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			New_Main(data){ //分类选择器赋值
				this.load_Cate(data);
			},
			load_Cate(data){	//请求种类信息
				this.$axios.post('http://localhost:8081/FindCate', {
					main_id:data
				}).then(res => {
					this.Category=res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			New_Cate(data){		//种类选择器赋值
				this.product.cate_id=data;
			},
			changeStatus(data,row){			//修改状态
				this.$axios.post('http://localhost:8081/ProStatus', {
					pro_id : row.pro_id,
					pro_status:row.pro_status
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
				this.product.image=url;
			},
			load_size(data){		//查询商品规格
				this.$axios.post('http://localhost:8081/FindSizeAfter', {
					pro_id:data
				}).then(res => {
					this.sizelist=res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			get_size(){		//打开添加规格对话框
				this.SizeVisible=true;
				this.size.size_status=1;
			},
			AddSize(){		//添加规格
				if(!this.flag_size){
					this.size.pro_id=this.product.pro_id;
					this.$axios.post('http://localhost:8081/AddSize',this.size).then(res => {
						if(res.data==1){
							this.SizeVisible=false;
							this.$message({
							type: 'success',
							message: '添加规格成功!'
							});
							this.reset_size();
							this.load_size(this.product.pro_id);
							this.flag_size=0;
						}
					}
					).catch(error => {
						console.log(error, '请求失败');
					})
				}else{
					this.$axios.post('http://localhost:8081/UpdateSize',this.size).then(res => {
						if(res){
							this.$message.success("编辑规格成功");
							this.SizeVisible=false;
							// 点击取消 数据重置
							this.reset_size();
							this.load_size(this.product.pro_id);
							this.flag_size=0;
						}else{
							this.$message.error("编辑规格失败");
							this.flag_size=0;
						}
					})
				}
			},
			New_SizeStatus(data){		//规格状态选择器
				this.size.size_status=data;
			},
			Del_size(data){		//删除规格
				this.$axios.post('http://localhost:8081/delSize', {
					size_id:data.size_id
				}).then(res => {
					this.sizelist=res.data;
					this.load_size(this.product.pro_id);
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			Edit_size(data){	//编辑规格
				this.SizeVisible=true;
				this.size.pro_id=data.pro_id;
				this.size.size_id=data.size_id;
				this.size.size_title=data.size_title;
				this.size.pro_price=data.pro_price;
				this.size.unit=data.unit;
				this.size.min_num=data.min_num;
				this.size.max_num=data.max_num;
				this.size.size_status=data.size_status;
				this.flag_size=1;
			},
			Edit(data){		//编辑商品信息
				this.flag=1;
				this.dialogFormVisible=true;
				this.product.pro_id=data.pro_id;
				this.product.amount=data.amount;
				this.product.cate_id=data.cate_id;
				this.product.detail=data.detail;
				if(data.image){
					this.tempUrl=require("@/assets/images/pro_imges/" +data.image);
				}
				this.product.origin=data.origin;
				this.product.price=data.price;
				this.product.pro_name=data.pro_name;
				this.product.pro_status=data.pro_status;
				this.New_store_id=data.store_id;
				this.cate_id=data.cate_id;
				this.product.title=data.title;
				this.product.user_id=data.user_id;
				this.load_size(data.pro_id);
				this.inquire_cate(data.cate_id);
				this.load_store();
			},
			reset_size(){		//关闭规格对话框
				this.SizeVisible = false;
				for (let key in this.size) {
					this.size[key] = "";
				}
			},
			inquire_cate(data){			//根据种类编号查询种类信息
				this.$axios.post('http://localhost:8081/inquire_main', {
					cate_id:data
				}).then(res => {
					this.main_id=res.data.main_id;
					this.load_Cate(res.data.main_id);
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
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