<template>
	<el-main class="bg">
		<el-form label-position="right" :model="query" class="form p_4" label-width="120">
			<el-row>
				<el-col :xs="24" :sm="24" :lg="8">
					<el-form-item label="项目名称">
						<el-input v-model="query.entry_name"></el-input>
					</el-form-item>
				</el-col>

				<el-col :xs="24" :sm="10" :lg="8">
					<el-form-item>
						<el-button type="primary" @click="search()">查询</el-button>
						<el-button @click="reset()" style="margin-right: 74px;">重置</el-button>
						<router-link v-if="user_group == '管理员' || $check_action('/project_declaration/table','add') || $check_action('/project_declaration/view','add')" class="el-button el-button--default el-button--primary" to="./view?">添加
						</router-link>
						<el-button v-if="user_group == '管理员' || $check_action('/project_declaration/table','del') || $check_action('/project_declaration/view','del')" class="" type="danger" @click="delInfo()">删除</el-button>
					</el-form-item>
				</el-col>

			</el-row>
		</el-form>
		<el-table :data="list" @selection-change="selectionChange" @sort-change="$sortChange" style="width: 100%" id="dataTable">
			<el-table-column fixed type="selection" tooltip-effect="dark" width="55">
			</el-table-column>
			<el-table-column prop="entry_name" label="项目名称"
				v-if="user_group == '管理员' || $check_field('get','entry_name')" min-width="200">
			</el-table-column>
			<el-table-column prop="project_type" label="项目类型"
				v-if="user_group == '管理员' || $check_field('get','project_type')" min-width="200">
			</el-table-column>
			<el-table-column prop="affiliated_college" label="所属学院"
				v-if="user_group == '管理员' || $check_field('get','affiliated_college')" min-width="200">
			</el-table-column>
			<el-table-column prop="student_job_number" label="学生工号"
				v-if="user_group == '管理员' || $check_field('get','student_job_number')" min-width="200">
				<template slot-scope="scope">
					{{ get_user_student_job_number(scope.row['student_job_number']) }}
				</template>
			</el-table-column>
			<el-table-column prop="student_name" label="学生姓名"
				v-if="user_group == '管理员' || $check_field('get','student_name')" min-width="200">
			</el-table-column>
			<el-table-column prop="contact_mobile_phone" label="联系手机"
				v-if="user_group == '管理员' || $check_field('get','contact_mobile_phone')" min-width="200">
			</el-table-column>
			<el-table-column prop="project_identification" label="项目认定书"
				v-if="user_group == '管理员' || $check_field('get','project_identification')" min-width="200">
				<template slot-scope="scope">
					<el-image style="width: 100px; height: 100px" :src="$fullUrl(scope.row['project_identification'])"
						:preview-src-list="[$fullUrl(scope.row['project_identification'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</template>
			</el-table-column>
			<el-table-column prop="project_annex" label="项目附件"
				v-if="user_group == '管理员' || $check_field('get','project_annex')" min-width="200">
				<template slot-scope="scope">
					<a :href="$fullUrl(scope.row['project_annex'])" target="_blank" style="color: rgb(64, 158, 255);">点击下载</a>
				</template>
			</el-table-column>
			<el-table-column prop="instructor" label="指导老师"
				v-if="user_group == '管理员' || $check_field('get','instructor')" min-width="200">
				<template slot-scope="scope">
					{{ get_user_instructor(scope.row['instructor']) }}
				</template>
			</el-table-column>
			<el-table-column prop="date_of_filling" label="填表日期"
				v-if="user_group == '管理员' || $check_field('get','date_of_filling')" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["date_of_filling"],"yyyy-MM-dd") }}
                </template>
			</el-table-column>
			<el-table-column prop="teacher_rating" label="老师评分"
				v-if="user_group == '管理员' || $check_field('get','teacher_rating')" min-width="200">
			</el-table-column>
			<el-table-column prop="teacher_review" label="老师审核"
				v-if="user_group == '管理员' || $check_field('get','teacher_review')" min-width="200">
			</el-table-column>
			<el-table-column prop="hospital_level_audit" label="院级审核"
				v-if="user_group == '管理员' || $check_field('get','hospital_level_audit')" min-width="200">
			</el-table-column>

            <el-table-column sortable prop="create_time" label="创建时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["create_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
            </el-table-column>

			<el-table-column sortable prop="update_time" label="更新时间" min-width="200">
                <template slot-scope="scope">
                	{{ $toTime(scope.row["update_time"],"yyyy-MM-dd hh:mm:ss") }}
                </template>
			</el-table-column>
			<el-table-column fixed="right" label="操作" min-width="120" v-if="user_group == '管理员' || $check_action('/project_declaration/table','set') || $check_action('/project_declaration/view','set') || $check_action('/project_declaration/view','get') || $check_action('/中期检查/table','add') || $check_action('/中期检查/view','add')" >


				<template slot-scope="scope">
					<router-link class="el-button el-button--small is-plain el-button--success" style="margin: 5px !important;"
					v-if="user_group == '管理员' || $check_action('/project_declaration/table','set') || $check_action('/project_declaration/view','set') || $check_action('/project_declaration/view','get')"
						:to="'./view?' + field + '=' + scope.row[field]"
						 size="small">
						<span>详情</span>
					</router-link>
					<!--跨表按钮-->
					<el-button class="el-button el-button--small is-plain el-button--default" style="margin: 5px !important;" size="small" @click="to_table(scope.row,'/interim_inspection/view')" v-if="user_group == '管理员' || $check_action('/interim_inspection/table','add') || $check_action('/interim_inspection/view','add')">
						<span>中期检查</span>
					</el-button>
				</template>
			</el-table-column>

		</el-table>

		<!-- 分页器 -->
		<div class="mt text_center">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
				:current-page="query.page" :page-sizes="[7, 10, 30, 100]" :page-size="query.size"
				layout="total, sizes, prev, pager, next, jumper" :total="count">
			</el-pagination>
		</div>
		<!-- /分页器 -->

		<div class="modal_wrap" v-if="showModal">
			<div class="modal_box">
				<!-- <div class="modal_box_close" @click="closeModal">X</div> -->
				<p class="modal_box_title">重要提醒</p>
				<p class="modal_box_text">当前有数据达到预警值！</p>
				<div class="btn_box">
					<span @click="closeModal">取消</span>
					<span @click="closeModal">确定</span>
				</div>
			</div>
		</div>


	</el-main>
</template>
<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				// 弹框
				showModal: false,
				// 获取数据地址
				url_get_list: "~/api/project_declaration/get_list?like=0",
				url_del: "~/api/project_declaration/del?",

				// 字段ID
				field: "project_declaration_id",

				// 查询
				query: {
					"size": 7,
					"page": 1,
					"entry_name": "",
					"login_time": "",
					"create_time": "",
					"orderby": `create_time desc`
				},
				// 数据
				list: [],
				// 用户列表
				list_user_student_job_number: [],
				// 用户列表
				list_user_instructor: [],
				// 老师审核列表
				list_teacher_review: ['已通过','未通过'],
				// 院级审核列表
				list_hospital_level_audit: ['已通过','未通过'],
			}
		},
		methods: {
			// 关闭弹框
			closeModal(){
				this.showModal = false;
				},
			get_list_before(param){
				var user_group = this.user.user_group;
				if(user_group != "管理员"){
					switch(user_group){
						case "学生用户":
						param["student_job_number"] = this.user.user_id;
						break;
						case "老师用户":
						param["instructor"] = this.user.user_id;
						break;
					}
				}
				return param;
			},

			open_tip() {
				const h = this.$createElement;

				var message = "";
				var list = this.list;

				var ifs = [ ];
				for (var n = 0; n < ifs.length; n++) {
					var o = ifs[n];
					for (var i = 0; i < list.length; i++) {
						var lt = list[i];
						if (o.type == "数内") {
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								if (lt[o.factor] > o.start && lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start || o.start === 0) {
								if (lt[o.factor] > o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end || o.end === 0) {
								if (lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "数外") {
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								if (lt[o.factor] < o.start || lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start || o.start === 0) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end || o.end === 0) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "日内") {
							if ((o.start) && (o.end)) {
								if (lt[o.factor] > o.start && lt[o.factor] < o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						} else if (o.type == "日外") {
							if (o.start && o.end) {
								if (lt[o.factor] < o.start || lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.start) {
								if (lt[o.factor] < o.start) {
									o["idx"] = o["idx"] + 1;
								}
							} else if (o.end) {
								if (lt[o.factor] > o.end) {
									o["idx"] = o["idx"] + 1;
								}
							}
						}
					}

					if (o["idx"]) {
						message += o.title;
						if (o["type"] == "数内") {
							if (o.start || o.start === 0) {
								message += "大于" + o.start;
							}
							if ((o.start || o.start === 0) && (o.end || o.end === 0)) {
								message += "并且";
							}
							if (o.end || o.end === 0) {
								message += "小于" + o.end;
							}
						} else if (o["type"] == "数外") {
							if (o.start || o.start === 0) {
								message += "小于" + o.start;
							}
							if ((o.start || o.start === 0) || (o.end || o.end === 0)) {
								message += "或者";
							}
							if (o.end || o.end === 0) {
								message += "大于" + o.end;
							}
						} else if (o["type"] == "日内") {
							if (o.start) {
								message += "在" + o.start + "之后";
							}
							if (o.start && o.end) {
								message += "并且";
							}
							if (o.end) {
								message += "在" + o.end + "之前";
							}
						} else if (o["type"] == "日外") {
							if (o.start) {
								message += "在" + o.start + "之前";
							}
							if (o.start || o.end) {
								message += "或者";
							}
							if (o.end) {
								message += "在" + o.end + "之后";
							}
						}
						message += "的有" + o["idx"] + "条";
					}
				}

				if (message) {
					message += "，需要处理、请尽快处理。";
					this.showModal = true;
					// this.$notify({
					// 	title: '提醒',
					// 	dangerouslyUseHTMLString: true,
					// 	message: h('i', {
					// 		style: 'color: teal'
					// 	}, message)
					// });
				}
			},
			/**
			 * 获取学生用户用户列表
			 */
			async get_list_user_student_job_number() {
				var json = await this.$get("~/api/user/get_list?user_group=学生用户");
				if(json.result && json.result.list){
					this.list_user_student_job_number = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			get_user_student_job_number(id){
				var obj = this.list_user_student_job_number.getObj({"user_id":id});
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

			get_user_instructor(id){
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
			deleteRow(index, rows) {
				rows.splice(index, 1);
			}

		},
		created() {
			this.get_list_user_student_job_number();
			this.get_list_user_instructor();
			setTimeout(() => {
				this.open_tip();
			}, 1000)
		}
	}
</script>

<style type="text/css">
	.bg {
		background: white;
	}

	.form.p_4 {
		padding: 1rem;
	}

	.form .el-input {
		width: initial;
	}

	.mt {
		margin-top: 1rem;
	}

	.text_center {
		text-align: center;
	}

	.float-right {
		float: right;
	}


	.modal_wrap{
		width: 100vw;
		height: 100vh;
		position: fixed;
		top: 0;
		left: 0;
		background: rgba(0,0,0,0.5);
		z-index: 9999999999;
	}
	.modal_wrap .modal_box{
		width: 400px;
		height: 200px;
		background: url("../../assets/modal_bg.jpg") no-repeat center;
		background-size: cover;
		position: absolute;
		top: 50%;
		left: 50%;
		margin-left: -200px;
		margin-top: -100px;
		border-radius: 10px;
		}
	.modal_wrap .modal_box .modal_box_close{
		font-size: 20px;
		position: absolute;
		top: 10px;
		right: 10px;
		cursor: pointer;
		}
	.modal_wrap .modal_box .modal_box_title{
	  text-align: center;
    font-size: 18px;
    margin: 16px auto;
    color: #fff;
    border-bottom: 1px solid rgba(117, 116, 116,0.5);
    padding-bottom: 16px;
    width: 356px;
		}
	.modal_wrap .modal_box .modal_box_text{
			text-align: center;
		font-size: 15px;
		color: #fff;
		margin-top: 25px;
		}
	.modal_wrap .modal_box .btn_box{
		display: flex;
		flex-direction: row;
		justify-content: center;
		margin-top: 42px;
		}
			.modal_wrap .modal_box .btn_box span{
				display: inline-block;
				width: 80px;
				height: 30px;
				line-height: 30px;
				text-align: center;
				border: 1px solid #ccc;
				font-size: 14px;
				cursor: pointer;
				color: #fff;
			}
	.modal_wrap .modal_box .btn_box span:nth-child(2){
		background: #409EFF;
		color: #fff;
		border-color: #409EFF;
		margin-left: 15px;
	}
</style>
