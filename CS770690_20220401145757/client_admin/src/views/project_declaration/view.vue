<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','entry_name') || $check_field('add','entry_name') || $check_field('set','entry_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目名称" prop="entry_name">
					<el-input id="entry_name" v-model="form['entry_name']" placeholder="请输入项目名称"
							  v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','entry_name')) || (!form['project_declaration_id'] && $check_field('add','entry_name'))" :disabled="disabledObj['entry_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','entry_name')">{{form['entry_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','project_type') || $check_field('add','project_type') || $check_field('set','project_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目类型" prop="project_type">
					<el-input id="project_type" v-model="form['project_type']" placeholder="请输入项目类型"
							  v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','project_type')) || (!form['project_declaration_id'] && $check_field('add','project_type'))" :disabled="disabledObj['project_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','project_type')">{{form['project_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','affiliated_college') || $check_field('add','affiliated_college') || $check_field('set','affiliated_college')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="所属学院" prop="affiliated_college">
					<el-input id="affiliated_college" v-model="form['affiliated_college']" placeholder="请输入所属学院"
							  v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','affiliated_college')) || (!form['project_declaration_id'] && $check_field('add','affiliated_college'))" :disabled="disabledObj['affiliated_college_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','affiliated_college')">{{form['affiliated_college']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_job_number') || $check_field('add','student_job_number') || $check_field('set','student_job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学生工号" prop="student_job_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_student_job_number(form['student_job_number']) }}
							<!--<el-input id="business_name" v-model="form['student_job_number']" placeholder="请输入学生工号"-->
							<!--v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','student_job_number')) || (!form['project_declaration_id'] && $check_field('add','student_job_number'))" :disabled="disabledObj['student_job_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','student_job_number')">{{form['student_job_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','student_job_number')) || (!form['project_declaration_id'] && $check_field('add','student_job_number'))" id="student_job_number" v-model="form['student_job_number']" :disabled="disabledObj['student_job_number_isDisabled']">
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
							  v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','student_name')) || (!form['project_declaration_id'] && $check_field('add','student_name'))" :disabled="disabledObj['student_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_name')">{{form['student_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_mobile_phone') || $check_field('add','contact_mobile_phone') || $check_field('set','contact_mobile_phone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系手机" prop="contact_mobile_phone">
					<el-input id="contact_mobile_phone" v-model="form['contact_mobile_phone']" placeholder="请输入联系手机"
							  v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','contact_mobile_phone')) || (!form['project_declaration_id'] && $check_field('add','contact_mobile_phone'))" :disabled="disabledObj['contact_mobile_phone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_mobile_phone')">{{form['contact_mobile_phone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','project_identification') || $check_field('add','project_identification') || $check_field('set','project_identification')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目认定书" prop="project_identification">
					<el-upload :disabled="disabledObj['project_identification_isDisabled']" id="project_identification" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_project_identification"
						:show-file-list="false" v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','project_identification')) || (!form['project_declaration_id'] && $check_field('add','project_identification'))">
						<img v-if="form['project_identification']" :src="$fullUrl(form['project_identification'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','project_identification')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['project_identification'])" :preview-src-list="[$fullUrl(form['project_identification'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','project_annex') || $check_field('add','project_annex') || $check_field('set','project_annex')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目附件" prop="project_annex">
					<div v-if="disabledObj['project_annex_isDisabled']">
						<div v-if="$check_field('get','project_annex')">
							<el-button type="primary" @click="$download($fullUrl(form['project_annex']),form['project_annex'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','project_annex')) || (!form['project_declaration_id'] && $check_field('add','project_annex'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_project_annex" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','project_annex')">
							<el-button type="primary" @click="$download($fullUrl(form['project_annex']),form['project_annex'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','instructor') || $check_field('add','instructor') || $check_field('set','instructor')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="指导老师" prop="instructor">
						<el-select v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','instructor')) || (!form['project_declaration_id'] && $check_field('add','instructor'))" id="instructor" v-model="form['instructor']" :disabled="disabledObj['instructor_isDisabled']">
							<el-option v-for="o in list_user_instructor" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','instructor')" id="instructor" v-model="form['instructor']" :disabled="true">
							<el-option v-for="o in list_user_instructor" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','date_of_filling') || $check_field('add','date_of_filling') || $check_field('set','date_of_filling')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="填表日期" prop="date_of_filling">
					<el-date-picker :disabled="disabledObj['date_of_filling_isDisabled']" v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','date_of_filling')) || (!form['project_declaration_id'] && $check_field('add','date_of_filling'))" id="date_of_filling"
						v-model="form['date_of_filling']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','date_of_filling')">{{form['date_of_filling']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','teacher_rating') || $check_field('add','teacher_rating') || $check_field('set','teacher_rating')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="老师评分" prop="teacher_rating">
					<el-input id="teacher_rating" v-model="form['teacher_rating']" placeholder="请输入老师评分"
							  v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','teacher_rating')) || (!form['project_declaration_id'] && $check_field('add','teacher_rating'))" :disabled="disabledObj['teacher_rating_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','teacher_rating')">{{form['teacher_rating']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','teacher_review') || $check_field('add','teacher_review') || $check_field('set','teacher_review')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="老师审核" prop="teacher_review">
					<el-select id="teacher_review" v-model="form['teacher_review']"
						v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','teacher_review')) || (!form['project_declaration_id'] && $check_field('add','teacher_review'))">
						<el-option v-for="o in list_teacher_review" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','teacher_review')">{{form['teacher_review']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','hospital_level_audit') || $check_field('add','hospital_level_audit') || $check_field('set','hospital_level_audit')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="院级审核" prop="hospital_level_audit">
					<el-select id="hospital_level_audit" v-model="form['hospital_level_audit']"
						v-if="user_group === '管理员' || (form['project_declaration_id'] && $check_field('set','hospital_level_audit')) || (!form['project_declaration_id'] && $check_field('add','hospital_level_audit'))">
						<el-option v-for="o in list_hospital_level_audit" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','hospital_level_audit')">{{form['hospital_level_audit']}}</div>
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
				field: "project_declaration_id",
				url_add: "~/api/project_declaration/add?",
				url_set: "~/api/project_declaration/set?",
				url_get_obj: "~/api/project_declaration/get_obj?",
				url_upload: "~/api/project_declaration/upload?",

				query: {
					"project_declaration_id": 0,
				},

				form: {
					"entry_name":'', // 项目名称
					"project_type":'', // 项目类型
					"affiliated_college":'', // 所属学院
					"student_job_number": 0, // 学生工号
					"student_name":'', // 学生姓名
					"contact_mobile_phone":'', // 联系手机
					"project_identification":'', // 项目认定书
					"project_annex":'', // 项目附件
					"instructor": 0, // 指导老师
					"date_of_filling":'', // 填表日期
					"teacher_rating":'', // 老师评分
					"teacher_review":'', // 老师审核
					"hospital_level_audit":'', // 院级审核
					"project_declaration_id": 0, // ID

				},
				disabledObj:{
					"entry_name_isDisabled": false,
					"project_type_isDisabled": false,
					"affiliated_college_isDisabled": false,
					"student_job_number_isDisabled": false,
					"student_name_isDisabled": false,
					"contact_mobile_phone_isDisabled": false,
					"project_identification_isDisabled": false,
					"project_annex_isDisabled": false,
					"instructor_isDisabled": false,
					"date_of_filling_isDisabled": false,
					"teacher_rating_isDisabled": false,
					"teacher_review_isDisabled": false,
					"hospital_level_audit_isDisabled": false,
				},
				// 用户列表
				list_user_student_job_number: [],
				// 用户组
				group_user_student_job_number: "",
				// 用户列表
				list_user_instructor: [],
				//老师审核选项列表
				list_teacher_review: ['已通过','未通过'],
				//院级审核选项列表
				list_hospital_level_audit: ['已通过','未通过'],

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
			 * 上传项目认定书
			 * @param {Object} param图片参数
			 */
			upload_project_identification(param){
				this.uploadFile(param.file, "project_identification");
			},
			/**
			 * 上传项目附件
			 * @param {Object} param文件参数
			 */
			upload_project_annex(param){
				this.uploadFile(param.file, "project_annex");
			},
			/**
			 * 获取老师用户用户列表
			 */
			async get_list_user_instructor() {
                // if(this.user_group !== "管理员" && this.form["instructor"] === 0) {
                //     this.form["instructor"] = this.user.user_id;
                // }
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
						case "指导老师":
							if(param["instructor"] > 0){
								param["instructor"] = this.user.user_id;
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
        if (this.form["date_of_filling"].indexOf("-")===-1){
          this.form["date_of_filling"] = this.$toTime(parseInt(this.form["date_of_filling"]),"yyyy-MM-dd")
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
				if(parseInt(this.form["date_of_filling"]) > 9999){
					this.form["date_of_filling"] = this.$toTime(parseInt(this.form["date_of_filling"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/project_declaration/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/project_declaration/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/project_declaration/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/project_declaration/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/project_declaration/view','get');
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
			this.get_list_user_instructor();
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
