<template>
    <div style="height: 901px;width: 100%">
            <div style="margin-top: 10px;">
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>商品管理</el-breadcrumb-item>
					<el-breadcrumb-item>商品类别</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
            <div style="margin: 5px;margin-top: 20px;">
				<el-button type="primary" @click="dialogFormVisible = true">新增<i class="el-icon-circle-plus-outline"></i></el-button>
				<el-button type="danger" @click="dels()">批量删除<i class="el-icon-remove-outline"></i></el-button>
            </div>
			<el-tabs type="border-card" style="width:1286px ;">
				<el-tab-pane label="使用中的轮播图">
					<el-table :data="usetableData" border stripe height="376px" @selection-change="handleSelectionChange">
						<el-table-column type="selection" width="55">				
							</el-table-column>
							<el-table-column prop="slide_id" label="轮播图编号" width="200">
							</el-table-column>
							<el-table-column prop="slide_imge" label="轮播图图片" width="200">
								<template slot-scope="scope">
								<el-popover placement="right" title="" trigger="hover">
									<img :src="imgformat(scope.row.slide_imge)"  style="height: 500px;width: 1000px"/>
									<img slot="reference" :src="imgformat(scope.row.slide_imge)" :alt="imgformat(scope.row.slide_imge)" style="height: 50px;width: 100px">
								</el-popover>
								</template>
							</el-table-column>
							<el-table-column prop="create_time" label="创建时间" width="200">
							</el-table-column>
							<el-table-column prop="update_time" label="更新时间" width="200">
							</el-table-column>
							<el-table-column prop="slide_status" label="轮播图位置" width="100" align="center">
								<template slot-scope="scope">
									<el-tag type="danger" v-if="scope.row.slide_status==0">未启用</el-tag>
									<el-tag type="success" v-else-if="scope.row.slide_status==1">第一张</el-tag>
									<el-tag type="success" v-else-if="scope.row.slide_status==2">第二张</el-tag>
									<el-tag type="success" v-else-if="scope.row.slide_status==3">第三张</el-tag>
									<el-tag type="success" v-else-if="scope.row.slide_status==4">第四张</el-tag>
								</template>
							</el-table-column>
							<el-table-column label="操作" width="300">
								<template slot-scope="scope">
									<el-button type="success" >编辑<i class="el-icon-edit"></i></el-button>
									<el-dropdown @command="handleCommand" style="margin-left: 10px;">
										<el-button type="primary"  @click="Edit(scope.row)">
											使用<i class="el-icon-arrow-down el-icon--right"></i>
										</el-button>
										<el-dropdown-menu slot="dropdown">
											<el-dropdown-item command="0">放置</el-dropdown-item>
											<el-dropdown-item command="1">第一张</el-dropdown-item>
											<el-dropdown-item command="2">第二张</el-dropdown-item>
											<el-dropdown-item command="3">第三张</el-dropdown-item>
											<el-dropdown-item command="4">第四张</el-dropdown-item>
										</el-dropdown-menu>
									</el-dropdown>
									<el-button type="danger" @click="open(scope.row)" style="margin-left: 10px;">删除<i class="el-icon-remove-outline"></i></el-button>
								</template>
							</el-table-column>
					</el-table>
				</el-tab-pane>
				<el-tab-pane label="轮播图">
					<el-table :data="tableData" border stripe height="1411px" @selection-change="handleSelectionChange">
						<el-table-column type="selection" width="55">				
							</el-table-column>
							<el-table-column prop="slide_id" label="轮播图编号" width="200">
							</el-table-column>
							<el-table-column prop="slide_imge" label="轮播图图片" width="200">
								<template slot-scope="scope">
								<el-popover placement="right" title="" trigger="hover">
									<img :src="imgformat(scope.row.slide_imge)"  style="height: 500px;width: 1000px"/>
									<img slot="reference" :src="imgformat(scope.row.slide_imge)" :alt="imgformat(scope.row.slide_imge)" style="height: 50px;width: 100px">
								</el-popover>
								</template>
							</el-table-column>
							<el-table-column prop="create_time" label="创建时间" width="200">
							</el-table-column>
							<el-table-column prop="update_time" label="更新时间" width="200">
							</el-table-column>
							<el-table-column prop="slide_status" label="轮播图位置" width="100" align="center">
								<template slot-scope="scope">						
									<el-tag type="danger" v-if="scope.row.slide_status==0">未启用</el-tag>
									<el-tag type="success" v-else-if="scope.row.slide_status==1">第一张</el-tag>
									<el-tag type="success" v-else-if="scope.row.slide_status==2">第二张</el-tag>
									<el-tag type="success" v-else-if="scope.row.slide_status==3">第三张</el-tag>
									<el-tag type="success" v-else-if="scope.row.slide_status==4">第四张</el-tag>
								</template>
							</el-table-column>
							<el-table-column label="操作" width="350">
								<template slot-scope="scope">
									<el-button type="success" >编辑<i class="el-icon-edit"></i></el-button>
									<el-dropdown @command="handleCommand" style="margin-left: 10px;">
										<el-button type="primary"  @click="Edit(scope.row)">
											使用<i class="el-icon-arrow-down el-icon--right"></i>
										</el-button>
										<el-dropdown-menu slot="dropdown">
											<el-dropdown-item command="0">放置</el-dropdown-item>
											<el-dropdown-item command="1">第一张</el-dropdown-item>
											<el-dropdown-item command="2">第二张</el-dropdown-item>
											<el-dropdown-item command="3">第三张</el-dropdown-item>
											<el-dropdown-item command="4">第四张</el-dropdown-item>
										</el-dropdown-menu>
									</el-dropdown>
									<el-button type="danger" @click="open(scope.row)" style="margin-left: 10px;">删除<i class="el-icon-remove-outline"></i></el-button>
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
					
				</el-tab-pane>
			</el-tabs>
    </div>
</template>
<script>
	export default{
		created(){
			this.load();
			this.useload();
		},
		data(){
			return{	
				slide:[],
				multipleSelection:[],
				usetableData:[],
				tableData:[],
				total:0,
				pageNum:1,
				pageSize:10,
			}
		},
		methods:{
			open(row) {	//删除
				this.$confirm('此操作将永久删除该商品信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.post('http://localhost:8081/SlideshowDel', {
						slide_id : row.slide_id
					}).then(res => {
						if(res.data==1){
							this.load();
							this.useload();
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
				let ids=this.multipleSelection.map(value => value.slide_id)//[{}]=>[1,2]
				//console.log(ids);
				this.$axios.post("/SlideshowDels",ids).then(res=> {
				if (res) {
					this.$message.success("批量删除成功!");
					this.load();
					this.useload();
				}else if (res==0){
					this.$message.error("请选择!")
					this.load();
					this.useload();
				}
				else {
					this.$message.error("批量删除失败!")
					this.load();
					this.useload();
				}
				})
			},
			submit(formName) {	//新增
					
					this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$message.success('添加成功');
						this.dialogFormVisible = false;
						// this.$axios.post('http://localhost:8081/StoreAdd', {
						// 	store_name:this.store_name,
						// 	store_title:this.store_title,
						// 	store_site:this.store_site,
						// 	user_id:'10001'
						// }).then(res => {
						// 	console.log(res.data)
						// 	this.load();
						// 	// 点击取消 数据重置
						// 	this.$refs[formName].resetFields();
						// 	}
						// 	)
						console.log(this.store)
					}
					});
			},
			reset() {   //取消，重置数据
				this.dialogFormVisible = false;
				for (let key in this.store) {
					this.store[key] = "";
				}
			},
			handleSelectionChange(val){
				this.multipleSelection = val;
			},
			handleCurrentChange(pageNum){	//查看下一页
				this.pageNum = pageNum;
				this.load();
			},
			load(){		//分页显示
				this.$axios.post('http://localhost:8081/SlideshowPage', {
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
			useload(){		//在用轮播图
				this.$axios.post('http://localhost:8081/SlideshowFind', {
				}).then(res => {
					this.usetableData = res.data;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			handleCommand(command){	//编辑
				this.$axios.post('http://localhost:8081/SlideshowStatus', {
					slide_id:this.slide.slide_id,
					slide_status:command
				}).then(res => {
					if(res.data==1){
						this.load();	
						this.useload();
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
			Edit(row){
				this.slide=row;
			},
			imgformat(row) {
				let img = require("@/assets/images/" +row);
				return img;
			},

		},
	}
</script>

<style scoped>
</style>