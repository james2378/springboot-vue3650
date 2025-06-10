<template>
	<div class="diy_home diy_list diy_interim_inspection" id="diy_interim_inspection_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/interim_inspection/details?interim_inspection_id=' + o['interim_inspection_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/interim_inspection/details') && +item.is_img_list">
						<div class="diy_title diy_list_img_title">
							<span>{{item.title}}:</span>
						</div>
						<div class="diy_field diy_img">
							<img :src="$fullUrl(o[item.name])" style="width:100%;height:100%" />
						</div>
					</div>
				</div>
				<!-- 内容 -->
				<div class="diy_list_item_box">
					<div class="diy_list_item_content" v-for="(item,index) in showItemList" :key="item+index">
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/interim_inspection/details') && +item.is_img_list">
							<div class="diy_title">
								<span>{{item.title}}:</span>
							</div>
							<div class="diy_field diy_text">
								<span v-if="item.type == 'UID'" v-text="get_user_name(o[item.name])"></span>
								<span v-else-if="item.type == '日期'" v-text="$toTime(o[item.name],'yyyy-MM-dd')"></span>
								<span v-else-if="item.type == '时间'" v-text="$toTime(o[item.name],'hh:mm:ss')"></span>
								<span v-else-if="item.type == '日长'" v-text="$toTime(o[item.name],'yyyy-MM-dd hh:mm:ss')"></span>
								<span v-else v-text="o[item.name]"></span>
							</div>
						</div>
					</div>
				</div>
			</router-link>
		</div>
		<!-- 表格 -->
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
					<th class="diy_title" v-if="$check_field('get','entry_name')">项目名称
					</th>
					<th class="diy_title" v-if="$check_field('get','project_type')">项目类型
					</th>
					<th class="diy_title" v-if="$check_field('get','affiliated_college')">所属学院
					</th>
					<th class="diy_title" v-if="$check_field('get','student_number')">学生学号
					</th>
					<th class="diy_title" v-if="$check_field('get','student_name')">学生姓名
					</th>
					<th class="diy_title" v-if="$check_field('get','contact_mobile_phone')">联系手机
					</th>
					<th class="diy_title" v-if="$check_field('get','instructor')">指导老师
					</th>
					<th class="diy_title" v-if="$check_field('get','project_start_time')">项目开始时间
					</th>
					<th class="diy_title" v-if="$check_field('get','planned_completion_time')">计划完成时间
					</th>
					<th class="diy_title" v-if="$check_field('get','total_project_funds')">项目经费总额
					</th>
					<th class="diy_title" v-if="$check_field('get','research_progress')">研究进展
					</th>
					<th class="diy_title" v-if="$check_field('get','stage_results')">阶段成果
					</th>
					<th class="diy_title" v-if="$check_field('get','teacher_review')">老师审核
					</th>
					<th class="diy_title" v-if="$check_field('get','hospital_level_audit')">院级审核
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
					<td class="diy_field diy_text" v-if="$check_field('get','entry_name')">
						<span>
							{{ o["entry_name"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','project_type')">
						<span>
							{{ o["project_type"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','affiliated_college')">
						<span>
							{{ o["affiliated_college"] }}
						</span>
					</td>
					<td class="diy_field diy_uid" v-if="$check_field('get','student_number')">
						<span>
							{{ get_user_name(o['student_number']) }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','student_name')">
						<span>
							{{ o["student_name"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','contact_mobile_phone')">
						<span>
							{{ o["contact_mobile_phone"] }}
						</span>
					</td>
					<td class="diy_field diy_uid" v-if="$check_field('get','instructor')">
						<span>
							{{ get_user_name(o['instructor']) }}
						</span>
					</td>
					<td class="diy_field diy_date" v-if="$check_field('get','project_start_time')">
						<span>
							{{ $toTime(o["project_start_time"] ,"yyyy-MM-dd") }}
						</span>
					</td>
					<td class="diy_field diy_date" v-if="$check_field('get','planned_completion_time')">
						<span>
							{{ $toTime(o["planned_completion_time"] ,"yyyy-MM-dd") }}
						</span>
					</td>
					<td class="diy_field diy_number" v-if="$check_field('get','total_project_funds')">
						<span>
							{{ o["total_project_funds"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','research_progress')">
						<span>
							{{ o["research_progress"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','stage_results')">
						<span>
							{{ o["stage_results"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','teacher_review')">
						<span>
							{{ o["teacher_review"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','hospital_level_audit')">
						<span>
							{{ o["hospital_level_audit"] }}
						</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [];
				},
			},
			show: {
				type: Boolean,
				default: function(){
					return true;
				}
			}
		},
		data() {
			return {
						imgList: [
						],
						itemList: [
								{
									title: "项目名称",
									name: "entry_name",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "项目类型",
									name: "project_type",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "所属学院",
									name: "affiliated_college",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "学生学号",
									name: "student_number",
									type: "UID",
									is_img_list: "0"
								},
								{
									title: "学生姓名",
									name: "student_name",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "联系手机",
									name: "contact_mobile_phone",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "指导老师",
									name: "instructor",
									type: "UID",
									is_img_list: "0"
								},
								{
									title: "项目开始时间",
									name: "project_start_time",
									type: "日期",
									is_img_list: "0"
								},
								{
									title: "计划完成时间",
									name: "planned_completion_time",
									type: "日期",
									is_img_list: "0"
								},
								{
									title: "项目经费总额",
									name: "total_project_funds",
									type: "数字",
									is_img_list: "0"
								},
								{
									title: "研究进展",
									name: "research_progress",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "阶段成果",
									name: "stage_results",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "项目附件",
									name: "project_annex",
									type: "文件",
									is_img_list: "0"
								},
								{
									title: "老师审核",
									name: "teacher_review",
									type: "下拉",
									is_img_list: "0"
								},
								{
									title: "院级审核",
									name: "hospital_level_audit",
									type: "下拉",
									is_img_list: "0"
								},
						],
						richList: [
						],
				// 用户列表
				list_user_student_number: [],
				// 用户列表
				list_user_instructor: [],
			};
		},
		methods: {
			/**
			 * 获取学生用户用户列表
			 */
			async get_list_user_student_number() {
				var json = await this.$get("~/api/user/get_list?user_group=学生用户");
				if(json.result && json.result.list){
					this.list_user_student_number = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_name(id){
				var obj = this.list_user_student_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
			/**
			 * 获取老师用户用户列表
			 */
			async get_list_user_instructor() {
				var json = await this.$get("~/api/user/get_list?user_group=老师用户");
				if(json.result && json.result.list){
					this.list_user_instructor = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_name(id){
				var obj = this.list_user_instructor.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
		},
		created() {
			this.get_list_user_student_number();
			this.get_list_user_instructor();
		},
		computed:{
			showItemList(){
				let arr = [];
				let _type = ["视频","音频","文件"];
				this.itemList.forEach(item => {
					if(_type.indexOf(item.type) === -1 && !!+item.is_img_list){
						arr.push(item)
					}
				})
				return arr.slice(0,4);
			}
		}
	};
</script>

<style scoped>
	.media {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		flex-basis: 75%;
		min-height: 10rem;
	}

	.goods {
		display: flex;
		width: calc(25% - 1rem);
		margin: 0.5rem;
		padding: 0.5rem;
		flex-direction: column;
		justify-content: space-between;
		background-color: white;
		border-radius: 0.5rem;
	}

	.goods:hover {
		border: 0.2rem solid #909399;
		box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.15);
	}

	.goods:hover img {
		filter: blur(1px);
	}

	.price {
		font-size: 1rem;
		margin-right: 3px;
	}

	.price_ago {
		text-decoration: line-through;
		font-size: 0.5rem;
		color: #999;

	}

	.title {
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-weight: 700;
		padding: .25rem;
	}

	.icon_cart {
		color: #FF5722;
		float: right;
	}

	@media (max-width: 992px) {

		.goods {
			width: calc(33% - 1rem);
			;
		}

	}

	@media (max-width: 768px) {

		.goods {
			width: calc(50% - 1rem);
			;
		}

	}
</style>

