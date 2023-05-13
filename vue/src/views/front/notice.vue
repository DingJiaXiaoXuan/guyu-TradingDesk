<template>
	<div>
		<el-card>
			<el-collapse accordion>
				<el-collapse-item v-for="(item, index) in noticetableData" :key="index">
					<template slot="title">
					<span style="margin-left: 10px;font-size: 16px;">{{item.user_name}}</span>
					<span style="margin-left: 40px;width: 400px;font-size: 12px;">{{item.notice_title}}</span>
					<span style="margin-left: 350px;font-size: 12px;">发布时间:{{item.publish_time}}</span>
					</template>
					<div style="font-size: 12px;">{{item.content}}</div>
				</el-collapse-item>
			</el-collapse>
			<div style="padding-top: 20px;clear:both;height: 60px;margin-left: 450px;">
				<el-pagination
						background
						@current-change="handleCurrentChange"
						:current-page="pageNum"
						:page-sizes="[5,10,15,20]"
						:page-size="pageSize"
						layout="total,prev, pager, next, jumper"
						:total="total">
				</el-pagination>
			</div>
		</el-card>
	</div>
</template>

<script>
	export default {
		created(){
			this.load();
		},
		data() {
			return {
				noticetableData:null,
				total:0,
				pageNum:1,
				pageSize:25,
			}
		},
		methods:{
			load(){
				this.$axios.post('http://localhost:8081/NoticePage', {
					pageNum:this.pageNum,
					count:this.pageSize
				}).then(res => {
					this.noticetableData = res.data.data;
					this.total = res.data.countSun;
					}
				).catch(error => {
					console.log(error, '请求失败');
				})
			},
			handleCurrentChange(pageNum){	//换页
				this.pageNum = pageNum;
				this.load();
			},
		},
	}
</script>

<style scoped>
</style>