<template>
	<view>
		<uni-popup ref="popupBottom" type="bottom" :custom="true" >
			<view class="uni-bottom-popup">
				<view class="uni-bottom-content">
					<view v-for="(item, index) in itemData" :key="index" class="uni-bottom-content-box">
						<button :open-type="item.openType" plain="true" style="border:0px;">
							<view class="uni-bottom-content-image">
								<uni-icons :type="item.icon" class="icon" :color="item.color" size="30" />
							</view>
						</button>
						<view class="uni-bottom-content-text">{{ item.text }}</view>
					</view>
				</view>
			</view>
		</uni-popup>	
	</view>
</template>

<script>
	import uniPopup from "@/components/uni-popup/uni-popup.vue"
	import uniIcons from '@/components/uni-icons/uni-icons.vue'
	export default {
		components: {
			uniPopup,
			uniIcons
		},
        props: {
            itemData:{
                type: Array
            }
        },		
		data() {
			return {
				
			}
		},
		methods: {
			open() {
				this.$refs.popupBottom.open();
			}
		}
	}
</script>

<style>
	.uni-bottom-popup {
		background: #fff;
	}
	
	.uni-bottom-content {
		display: flex;
		flex-wrap: wrap;
		padding: 15px;
	}
	
	.uni-bottom-content-box {
		display: flex;
		flex-direction: column;
		align-items: center;
		width: 25%;
		box-sizing: border-box;
	}
	
	.uni-bottom-content-image {
		display: flex;
		justify-content: center;
		align-items: center;
		overflow: hidden;
		border-radius: 10upx;
	}
	
	.uni-bottom-content-text {
		font-size: 20upx;
		color: #333;
		padding-top: 2px;
		padding-bottom: 10px;
	}
</style>
