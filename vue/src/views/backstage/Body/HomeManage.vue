<template>
	<div>
		<el-main>
			<div style="width: 1200px;">
				<div style="float: left;">
					<el-card class="card_1">
						<div class="card_text_1">13</div>
						<div>用户数量</div>
					</el-card>
				</div>
				<div style="float: left;margin-left: 30px;">
					<el-card class="card_1">
						<div class="card_text_1">19</div>
						<div>商品数量</div>
					</el-card>
				</div>
				<div style="float: left;margin-left: 30px;">
					<el-card class="card_1">
						<div class="card_text_1">17</div>
						<div>店铺数量</div>
					</el-card>
				</div>
				<div style="float: left;margin-left: 30px;">
					<el-card class="card_1">
						<div class="card_text_1">
							<span>5</span>
						</div>
						<div>订单数量</div>
					</el-card>
				</div>
				<div style="float: left;margin-left: 30px;">
					<el-card class="card_1">
						<div class="card_text_1">432.00</div>
						<div>销售金额</div>
					</el-card>
				</div>
			</div>
		</el-main>
	</div>
</template>

<script>
</script>

<style scoped>
	.card_1{
		height: 100px;width: 200px;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.card_text_1{
		font-size: 22px;
		color: crimson;
		text-align: center;
		margin-bottom: 10px;
	}
</style>