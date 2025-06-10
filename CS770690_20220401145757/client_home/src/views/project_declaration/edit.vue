<template>
	<div class="diy_edit page_project_declaration" id="project_declaration_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','entry_name') || $check_field('add','entry_name') || $check_field('get','entry_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>项目名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_entry_name" v-model="form['entry_name']" placeholder="请输入项目名称" v-if="(form['entry_name'] && $check_field('set','entry_name')) || (!form['entry_name'] && $check_field('add','entry_name'))"  :disabled="disabledObj['entry_name_isDisabled']"/>
							<span v-else-if="$check_field('get','entry_name')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','project_type') || $check_field('add','project_type') || $check_field('get','project_type')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>项目类型:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_project_type" v-model="form['project_type']" placeholder="请输入项目类型" v-if="(form['project_type'] && $check_field('set','project_type')) || (!form['project_type'] && $check_field('add','project_type'))"  :disabled="disabledObj['project_type_isDisabled']"/>
							<span v-else-if="$check_field('get','project_type')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','affiliated_college') || $check_field('add','affiliated_college') || $check_field('get','affiliated_college')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>所属学院:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_affiliated_college" v-model="form['affiliated_college']" placeholder="请输入所属学院" v-if="(form['affiliated_college'] && $check_field('set','affiliated_college')) || (!form['affiliated_college'] && $check_field('add','affiliated_college'))"  :disabled="disabledObj['affiliated_college_isDisabled']"/>
							<span v-else-if="$check_field('get','affiliated_college')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','student_job_number') || $check_field('add','student_job_number') || $check_field('get','student_job_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>学生工号:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_student_job_number" :disabled="disabledObj['student_job_number_isDisabled']" v-model="form['student_job_number']" v-if="(form['student_job_number'] && $check_field('set','student_job_number')) || (!form['student_job_number'] && $check_field('add','student_job_number'))" >
								<option v-for="o in list_user_student_job_number" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','student_job_number')">{{ form['student_job_number'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','student_name') || $check_field('add','student_name') || $check_field('get','student_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>学生姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_student_name" v-model="form['student_name']" placeholder="请输入学生姓名" v-if="(form['student_name'] && $check_field('set','student_name')) || (!form['student_name'] && $check_field('add','student_name'))"  :disabled="disabledObj['student_name_isDisabled']"/>
							<span v-else-if="$check_field('get','student_name')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','contact_mobile_phone') || $check_field('add','contact_mobile_phone') || $check_field('get','contact_mobile_phone')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>联系手机:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_contact_mobile_phone" v-model="form['contact_mobile_phone']" placeholder="请输入联系手机" v-if="(form['contact_mobile_phone'] && $check_field('set','contact_mobile_phone')) || (!form['contact_mobile_phone'] && $check_field('add','contact_mobile_phone'))"  :disabled="disabledObj['contact_mobile_phone_isDisabled']"/>
							<span v-else-if="$check_field('get','contact_mobile_phone')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','project_identification') || $check_field('add','project_identification') || $check_field('get','project_identification')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>项目认定书:
							</span>
						</div>
						<!-- 图片 -->
						<input type="file" :disabled="disabledObj['project_identification_isDisabled']" style="display: none;" id="form_img_project_identification" title="project_identification" @change="change_file($event.target.files,'project_identification')"/>
						<!-- 修改权限 -->
						<div class="diy_field diy_img" v-if="form['project_identification'] && $check_field('set','project_identification')">
							<label for="form_img_project_identification">
								<img :src="$fullUrl(form['project_identification'])" />
							</label>
						</div>
						<!-- 添加权限 -->
						<div class="diy_field diy_img" v-else-if="!form['project_identification'] && $check_field('add','project_identification')">
							<label for="form_img_project_identification">
								<div class="btn_add_img">
									<span>+</span>
								</div>
							</label>
						</div>
						<!-- 查询权限 -->
						<div class="diy_field diy_img" v-else-if="$check_field('get','project_identification')">
							<img :src="$fullUrl(form['project_identification'])" />
						</div>
					</div>
					<div v-if="$check_field('set','project_annex') || $check_field('add','project_annex') || $check_field('get','project_annex')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>项目附件:
							</span>
						</div>
						<!-- 文件 -->
						<input type="file" style="display: none;" id="form_file_project_annex" title="project_annex" @change="change_file($event.target.files,'project_annex')"/>
						<!-- 修改权限 -->
						<div class="diy_field diy_img" v-if="form['project_annex'] && $check_field('set','project_annex')">
							<label for="form_file_project_annex">
								<!--<span>{{form['project_annex']}} </span>-->
								<a :href="$fullUrl(form['project_annex'])" target="_blank" style="color: rgb(64, 158, 255);">点击下载</a>
							</label>
						</div>
						<!-- 添加权限 -->
						<div class="diy_field diy_img" v-else-if="!form['project_annex'] && $check_field('add','project_annex')">
							<label for="form_file_project_annex">
								<div class="btn_add_img">
									<span>+</span>
								</div>
							</label>
						</div>
						<!-- 查询权限 -->
						<div class="diy_field diy_img" v-else-if="$check_field('get','project_annex')">
							<span>{{form['project_annex']}} </span>
						</div>
					</div>
					<div v-if="$check_field('set','instructor') || $check_field('add','instructor') || $check_field('get','instructor')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>指导老师:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_instructor" :disabled="disabledObj['instructor_isDisabled']" v-model="form['instructor']" v-if="(form['instructor'] && $check_field('set','instructor')) || (!form['instructor'] && $check_field('add','instructor'))" >
								<option v-for="o in list_user_instructor" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','instructor')">{{ form['instructor'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','date_of_filling') || $check_field('add','date_of_filling') || $check_field('get','date_of_filling')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>填表日期:
							</span>
						</div>
						<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['date_of_filling_isDisabled']" id="form_date_of_filling" v-model="form['date_of_filling']" placeholder="请输入填表日期" v-if="(form['date_of_filling'] && $check_field('set','date_of_filling')) || (!form['date_of_filling'] && $check_field('add','date_of_filling'))" />
							<span v-else-if="$check_field('get','date_of_filling')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','teacher_rating') || $check_field('add','teacher_rating') || $check_field('get','teacher_rating')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>老师评分:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_teacher_rating" v-model="form['teacher_rating']" placeholder="请输入老师评分" v-if="(form['teacher_rating'] && $check_field('set','teacher_rating')) || (!form['teacher_rating'] && $check_field('add','teacher_rating'))"  :disabled="disabledObj['teacher_rating_isDisabled']"/>
							<span v-else-if="$check_field('get','teacher_rating')">{{ form['student_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','teacher_review') || $check_field('add','teacher_review') || $check_field('get','teacher_review')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>老师审核:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_teacher_review" v-model="form['teacher_review']" v-if="(form['teacher_review'] && $check_field('set','teacher_review')) || (!form['teacher_review'] && $check_field('add','teacher_review'))" >
								<option v-for="o in list_teacher_review" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','teacher_review')">{{ form['teacher_review'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','hospital_level_audit') || $check_field('add','hospital_level_audit') || $check_field('get','hospital_level_audit')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>院级审核:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_hospital_level_audit" v-model="form['hospital_level_audit']" v-if="(form['hospital_level_audit'] && $check_field('set','hospital_level_audit')) || (!form['hospital_level_audit'] && $check_field('add','hospital_level_audit'))" >
								<option v-for="o in list_hospital_level_audit" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','hospital_level_audit')">{{ form['hospital_level_audit'] }}</span>
						</div>
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/project_declaration/get_obj?",
				url_add: "~/api/project_declaration/add?",
				url_set: "~/api/project_declaration/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"entry_name": "",
					"project_type": "",
					"affiliated_college": "",
					"student_job_number": 0,
					"student_name": "",
					"contact_mobile_phone": "",
					"project_identification": "",
					"project_annex": "",
					"instructor": 0,
					"date_of_filling": "",
					"teacher_rating": "",
					"teacher_review": "",
					"hospital_level_audit": "",
					"project_declaration_id": 0,
				},

				obj: {
					"entry_name":'', // 项目名称
					"project_type":'', // 项目类型
					"affiliated_college":'', // 所属学院
					"student_job_number": 0, // 学生工号
					"student_name":'', // 学生姓名
					"contact_mobile_phone":'', // 联系手机
					"project_identification":'', // 项目认定书
					"project_annex":'', // 项目附件
					"instructor": 0, // 指导老师
					"date_of_filling": "1970-01-01 00:00:00",
					"teacher_rating":'', // 老师评分
					"teacher_review":'', // 老师审核
					"hospital_level_audit":'', // 院级审核
					"project_declaration_id": 0,
				},

				// 表单字段
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
					"date_of_filling": "1970-01-01 00:00:00",
					"teacher_rating":'', // 老师评分
					"teacher_review":'', // 老师审核
					"hospital_level_audit":'', // 院级审核
					"project_declaration_id": 0,

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
				// 用户列表
				list_user_instructor: [],
				//老师审核选项列表
				list_teacher_review: ['已通过','未通过'],
				//院级审核选项列表
				list_hospital_level_audit: ['已通过','未通过'],

				// ID字段
				field: "project_declaration_id",
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
			async get_user_session_student_job_number(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["student_job_number"] = user_id
								_this.disabledObj['student_job_number' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="student_job_number") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
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

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/project_declaration/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				this.form["date_of_filling"] = this.$toTime(parseInt(this.form["date_of_filling"]),"yyyy-MM-dd")
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_student_job_number();
			this.get_list_user_student_job_number();
			this.get_list_user_instructor();
		}
	}
</script>

<style>
</style>
