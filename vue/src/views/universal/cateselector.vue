<template>
	<div>
		<el-select v-model="value" placeholder="请选择"  @change="selectChange">
			<el-option
				v-for="item in options"
				:key="item.value" 
				:label="item.label"
				:value="item.value">
			</el-option>
		</el-select>
		<el-dialog
			title="提示"
			:visible.sync="dialogVisible"
			width="30%"
			>
			<span>这是一段信息</span>
			<span slot="footer" class="dialog-footer">
				<el-button @click="dialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="dialogVisible = false">确 定</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
  export default {
    data() {
      return {
        options: [{
          value: '选项1',
          label: '黄金糕'
        }, {
          value: '选项2',
          label: '双皮奶'
        }, {
          value: '选项3',
          label: '蚵仔煎'
        }, {
          value: '选项4',
          label: '龙须面'
        }, {
          value: '选项5',
          label: '北京烤鸭'
        }],
        value: '',
		dialogFormVisible: false,
      }
    }
  }
</script>

<style scoped>
</style>