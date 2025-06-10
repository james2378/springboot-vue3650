<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','entry_name') || $check_field('add','entry_name') || $check_field('set','entry_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目名称" prop="entry_name">
					<el-input id="entry_name" v-model="form['entry_name']" placeholder="请输入项目名称"
							  v-if="user_group === '管理员' || (form['project_rental_team_id'] && $check_field('set','entry_name')) || (!form['project_rental_team_id'] && $check_field('add','entry_name'))" :disabled="disabledObj['entry_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','entry_name')">{{form['entry_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','project_type') || $check_field('add','project_type') || $check_field('set','project_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目类型" prop="project_type">
					<el-input id="project_type" v-model="form['project_type']" placeholder="请输入项目类型"
							  v-if="user_group === '管理员' || (form['project_rental_team_id'] && $check_field('set','project_type')) || (!form['project_rental_team_id'] && $check_field('add','project_type'))" :disabled="disabledObj['project_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','project_type')">{{form['project_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_job_number') || $check_field('add','student_job_number') || $check_field('set','student_job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学生工号" prop="student_job_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_student_job_number(form['student_job_number']) }}
							<!--<el-input id="business_name" v-model="form['student_job_number']" placeholder="请输入学生工号"-->
							<!--v-if="user_group === '管理员' || (form['project_rental_team_id'] && $check_field('set','student_job_number')) || (!form['project_rental_team_id'] && $check_field('add','student_job_number'))" :disabled="disabledObj['student_job_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','student_job_number')">{{form['student_job_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['project_rental_team_id'] && $check_field('set','student_job_number')) || (!form['project_rental_team_id'] && $check_field('add','student_job_number'))" id="student_job_number" v-model="form['student_job_number']" :disabled="disabledObj['student_job_number_isDisabled']">
								<el-option v-for="o in list_user_student_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','student_job_number')" id="student_job_number" v-model="form['student_job_number']" :disabled="true">
								<el-option v-for="o in list_user_student_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="student_job_number" v-model="form['student_job_number']" :disabled="disabledObj['student_job_number_isDisabled']">
							<el-option v-for="o in list_user_student_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_name') || $check_field('add','student_name') || $check_field('set','student_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学生姓名" prop="student_name">
					<el-input id="student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
							  v-if="user_group === '管理员' || (form['project_rental_team_id'] && $check_field('set','student_name')) || (!form['project_rental_team_id'] && $check_field('add','student_name'))" :disabled="disabledObj['student_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_name')">{{form['student_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_mobile_phone') || $check_field('add','contact_mobile_phone') || $check_field('set','contact_mobile_phone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系手机" prop="contact_mobile_phone">
					<el-input id="contact_mobile_phone" v-model="form['contact_mobile_phone']" placeholder="请输入联系手机"
							  v-if="user_group === '管理员' || (form['project_rental_team_id'] && $check_field('set','contact_mobile_phone')) || (!form['project_rental_team_id'] && $check_field('add','contact_mobile_phone'))" :disabled="disabledObj['contact_mobile_phone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_mobile_phone')">{{form['contact_mobile_phone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','demand_number') || $check_field('add','demand_number') || $check_field('set','demand_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="需求人数" prop="demand_number">
					<el-input id="demand_number" v-model="form['demand_number']" placeholder="请输入需求人数"
							  v-if="user_group === '管理员' || (form['project_rental_team_id'] && $check_field('set','demand_number')) || (!form['project_rental_team_id'] && $check_field('add','demand_number'))" :disabled="disabledObj['demand_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','demand_number')">{{form['demand_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','team_requirements') || $check_field('add','team_requirements') || $check_field('set','team_requirements')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="组队需求" prop="team_requirements">
					<el-input type="textarea" id="team_requirements" v-model="form['team_requirements']" placeholder="请输入组队需求"
						v-if="user_group === '管理员' || (form['project_rental_team_id'] && $check_field('set','team_requirements')) || (!form['project_rental_team_id'] && $check_field('add','team_requirements'))" :disabled="disabledObj['team_requirements_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','team_requirements')">{{form['team_requirements']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','project_content') || $check_field('add','project_content') || $check_field('set','project_content')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目内容" prop="project_content">
					<el-input type="textarea" id="project_content" v-model="form['project_content']" placeholder="请输入项目内容"
						v-if="user_group === '管理员' || (form['project_rental_team_id'] && $check_field('set','project_content')) || (!form['project_rental_team_id'] && $check_field('add','project_content'))" :disabled="disabledObj['project_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','project_content')">{{form['project_content']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else-if="$check_field('get','examine_state')">{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "project_rental_team_id",
				url_add: "~/api/project_rental_team/add?",
				url_set: "~/api/project_rental_team/set?",
				url_get_obj: "~/api/project_rental_team/get_obj?",
				url_upload: "~/api/project_rental_team/upload?",

				query: {
					"project_rental_team_id": 0,
				},

				form: {
					"entry_name":'', // 项目名称
					"project_type":'', // 项目类型
					"student_job_number": 0, // 学生工号
					"student_name":'', // 学生姓名
					"contact_mobile_phone":'', // 联系手机
					"demand_number":'', // 需求人数
					"team_requirements":'', // 组队需求
					"project_content":'', // 项目内容
					"examine_state": "未审核",
					"project_rental_team_id": 0, // ID

				},
				disabledObj:{
					"entry_name_isDisabled": false,
					"project_type_isDisabled": false,
					"student_job_number_isDisabled": false,
					"student_name_isDisabled": false,
					"contact_mobile_phone_isDisabled": false,
					"demand_number_isDisabled": false,
					"team_requirements_isDisabled": false,
					"project_content_isDisabled": false,
				},
				// 用户列表
				list_user_student_job_number: [],
				// 用户组
				group_user_student_job_number: "",

			}
		},
		methods: {
			/**
			 * 获取学生用户用户列表
			 */
			async get_list_user_student_job_number() {
                // if(this.user_group !== "管理员" && this.form["student_job_number"] === 0) {
                //     this.form["student_job_number"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生用户");
                if(json.result && json.result.list){
                    this.list_user_student_job_number = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取学生用户用户组
			 */
			async get_group_user_student_job_number() {
				this.form["student_job_number"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
				if(json.result && json.result.obj){
					this.group_user_student_job_number = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_student_job_number(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_student_job_number.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="student_job_number") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_student_job_number(id){
				var obj = this.list_user_student_job_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "学生工号":
							if(param["student_job_number"] > 0){
								param["student_job_number"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/project_rental_team/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/project_rental_team/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/project_rental_team/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/project_rental_team/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/project_rental_team/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_student_job_number();
			this.get_group_user_student_job_number();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
