<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','entry_name') || $check_field('add','entry_name') || $check_field('set','entry_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目名称" prop="entry_name">
					<el-input id="entry_name" v-model="form['entry_name']" placeholder="请输入项目名称"
							  v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','entry_name')) || (!form['final_acceptance_id'] && $check_field('add','entry_name'))" :disabled="disabledObj['entry_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','entry_name')">{{form['entry_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','project_type') || $check_field('add','project_type') || $check_field('set','project_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目类型" prop="project_type">
					<el-input id="project_type" v-model="form['project_type']" placeholder="请输入项目类型"
							  v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','project_type')) || (!form['final_acceptance_id'] && $check_field('add','project_type'))" :disabled="disabledObj['project_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','project_type')">{{form['project_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','affiliated_college') || $check_field('add','affiliated_college') || $check_field('set','affiliated_college')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="所属学院" prop="affiliated_college">
					<el-input id="affiliated_college" v-model="form['affiliated_college']" placeholder="请输入所属学院"
							  v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','affiliated_college')) || (!form['final_acceptance_id'] && $check_field('add','affiliated_college'))" :disabled="disabledObj['affiliated_college_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','affiliated_college')">{{form['affiliated_college']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_number') || $check_field('add','student_number') || $check_field('set','student_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学生学号" prop="student_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_student_number(form['student_number']) }}
							<!--<el-input id="business_name" v-model="form['student_number']" placeholder="请输入学生学号"-->
							<!--v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','student_number')) || (!form['final_acceptance_id'] && $check_field('add','student_number'))" :disabled="disabledObj['student_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','student_number')">{{form['student_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','student_number')) || (!form['final_acceptance_id'] && $check_field('add','student_number'))" id="student_number" v-model="form['student_number']" :disabled="disabledObj['student_number_isDisabled']">
								<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','student_number')" id="student_number" v-model="form['student_number']" :disabled="true">
								<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="student_number" v-model="form['student_number']" :disabled="disabledObj['student_number_isDisabled']">
							<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_name') || $check_field('add','student_name') || $check_field('set','student_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学生姓名" prop="student_name">
					<el-input id="student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
							  v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','student_name')) || (!form['final_acceptance_id'] && $check_field('add','student_name'))" :disabled="disabledObj['student_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_name')">{{form['student_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_mobile_phone') || $check_field('add','contact_mobile_phone') || $check_field('set','contact_mobile_phone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系手机" prop="contact_mobile_phone">
					<el-input id="contact_mobile_phone" v-model="form['contact_mobile_phone']" placeholder="请输入联系手机"
							  v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','contact_mobile_phone')) || (!form['final_acceptance_id'] && $check_field('add','contact_mobile_phone'))" :disabled="disabledObj['contact_mobile_phone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_mobile_phone')">{{form['contact_mobile_phone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','instructor') || $check_field('add','instructor') || $check_field('set','instructor')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="指导老师" prop="instructor">
						<el-select v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','instructor')) || (!form['final_acceptance_id'] && $check_field('add','instructor'))" id="instructor" v-model="form['instructor']" :disabled="disabledObj['instructor_isDisabled']">
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
			<el-col v-if="user_group === '管理员' || $check_field('get','contract_amount') || $check_field('add','contract_amount') || $check_field('set','contract_amount')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="合同金额" prop="contract_amount">
					<el-input-number id="contract_amount" v-model.number="form['contract_amount']"
						v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','contract_amount')) || (!form['final_acceptance_id'] && $check_field('add','contract_amount'))"></el-input-number>
					<div v-else-if="$check_field('get','contract_amount')">{{form['contract_amount']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','amount_in_place') || $check_field('add','amount_in_place') || $check_field('set','amount_in_place')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="到位金额" prop="amount_in_place">
					<el-input-number id="amount_in_place" v-model.number="form['amount_in_place']"
						v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','amount_in_place')) || (!form['final_acceptance_id'] && $check_field('add','amount_in_place'))"></el-input-number>
					<div v-else-if="$check_field('get','amount_in_place')">{{form['amount_in_place']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','actual_expenditure') || $check_field('add','actual_expenditure') || $check_field('set','actual_expenditure')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="实际支出" prop="actual_expenditure">
					<el-input-number id="actual_expenditure" v-model.number="form['actual_expenditure']"
						v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','actual_expenditure')) || (!form['final_acceptance_id'] && $check_field('add','actual_expenditure'))"></el-input-number>
					<div v-else-if="$check_field('get','actual_expenditure')">{{form['actual_expenditure']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','conclusion_annex') || $check_field('add','conclusion_annex') || $check_field('set','conclusion_annex')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="结题附件" prop="conclusion_annex">
					<div v-if="disabledObj['conclusion_annex_isDisabled']">
						<div v-if="$check_field('get','conclusion_annex')">
							<el-button type="primary" @click="$download($fullUrl(form['conclusion_annex']),form['conclusion_annex'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','conclusion_annex')) || (!form['final_acceptance_id'] && $check_field('add','conclusion_annex'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_conclusion_annex" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','conclusion_annex')">
							<el-button type="primary" @click="$download($fullUrl(form['conclusion_annex']),form['conclusion_annex'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','reply_ppt') || $check_field('add','reply_ppt') || $check_field('set','reply_ppt')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="答辩ppt" prop="reply_ppt">
					<div v-if="disabledObj['reply_ppt_isDisabled']">
						<div v-if="$check_field('get','reply_ppt')">
							<el-button type="primary" @click="$download($fullUrl(form['reply_ppt']),form['reply_ppt'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','reply_ppt')) || (!form['final_acceptance_id'] && $check_field('add','reply_ppt'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_reply_ppt" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','reply_ppt')">
							<el-button type="primary" @click="$download($fullUrl(form['reply_ppt']),form['reply_ppt'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','date_of_filling') || $check_field('add','date_of_filling') || $check_field('set','date_of_filling')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="填表日期" prop="date_of_filling">
					<el-date-picker :disabled="disabledObj['date_of_filling_isDisabled']" v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','date_of_filling')) || (!form['final_acceptance_id'] && $check_field('add','date_of_filling'))" id="date_of_filling"
						v-model="form['date_of_filling']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','date_of_filling')">{{form['date_of_filling']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','hospital_level_audit') || $check_field('add','hospital_level_audit') || $check_field('set','hospital_level_audit')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="院级审核" prop="hospital_level_audit">
					<el-select id="hospital_level_audit" v-model="form['hospital_level_audit']"
						v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','hospital_level_audit')) || (!form['final_acceptance_id'] && $check_field('add','hospital_level_audit'))">
						<el-option v-for="o in list_hospital_level_audit" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','hospital_level_audit')">{{form['hospital_level_audit']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','reason_for_conclusion') || $check_field('add','reason_for_conclusion') || $check_field('set','reason_for_conclusion')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="结题原因" prop="reason_for_conclusion">
					<el-input type="textarea" id="reason_for_conclusion" v-model="form['reason_for_conclusion']" placeholder="请输入结题原因"
						v-if="user_group === '管理员' || (form['final_acceptance_id'] && $check_field('set','reason_for_conclusion')) || (!form['final_acceptance_id'] && $check_field('add','reason_for_conclusion'))" :disabled="disabledObj['reason_for_conclusion_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','reason_for_conclusion')">{{form['reason_for_conclusion']}}</div>
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
				field: "final_acceptance_id",
				url_add: "~/api/final_acceptance/add?",
				url_set: "~/api/final_acceptance/set?",
				url_get_obj: "~/api/final_acceptance/get_obj?",
				url_upload: "~/api/final_acceptance/upload?",

				query: {
					"final_acceptance_id": 0,
				},

				form: {
					"entry_name":'', // 项目名称
					"project_type":'', // 项目类型
					"affiliated_college":'', // 所属学院
					"student_number": 0, // 学生学号
					"student_name":'', // 学生姓名
					"contact_mobile_phone":'', // 联系手机
					"instructor": 0, // 指导老师
					"contract_amount":0, // 合同金额
					"amount_in_place":0, // 到位金额
					"actual_expenditure":0, // 实际支出
					"conclusion_annex":'', // 结题附件
					"reply_ppt":'', // 答辩ppt
					"date_of_filling":'', // 填表日期
					"hospital_level_audit":'', // 院级审核
					"reason_for_conclusion":'', // 结题原因
					"final_acceptance_id": 0, // ID

				},
				disabledObj:{
					"entry_name_isDisabled": false,
					"project_type_isDisabled": false,
					"affiliated_college_isDisabled": false,
					"student_number_isDisabled": false,
					"student_name_isDisabled": false,
					"contact_mobile_phone_isDisabled": false,
					"instructor_isDisabled": false,
					"conclusion_annex_isDisabled": false,
					"reply_ppt_isDisabled": false,
					"date_of_filling_isDisabled": false,
					"hospital_level_audit_isDisabled": false,
					"reason_for_conclusion_isDisabled": false,
				},
				// 用户列表
				list_user_student_number: [],
				// 用户组
				group_user_student_number: "",
				// 用户列表
				list_user_instructor: [],
				//院级审核选项列表
				list_hospital_level_audit: ['已通过','未通过'],

			}
		},
		methods: {
			/**
			 * 获取学生用户用户列表
			 */
			async get_list_user_student_number() {
                // if(this.user_group !== "管理员" && this.form["student_number"] === 0) {
                //     this.form["student_number"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生用户");
                if(json.result && json.result.list){
                    this.list_user_student_number = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取学生用户用户组
			 */
			async get_group_user_student_number() {
				this.form["student_number"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=学生用户");
				if(json.result && json.result.obj){
					this.group_user_student_number = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_student_number(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_student_number.source_table+"/get_obj?"
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
									if (arr[i]!=="student_number") {
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
			get_user_student_number(id){
				var obj = this.list_user_student_number.getObj({"user_id":id});
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
			 * 上传结题附件
			 * @param {Object} param文件参数
			 */
			upload_conclusion_annex(param){
				this.uploadFile(param.file, "conclusion_annex");
			},
			/**
			 * 上传答辩ppt
			 * @param {Object} param文件参数
			 */
			upload_reply_ppt(param){
				this.uploadFile(param.file, "reply_ppt");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "学生学号":
							if(param["student_number"] > 0){
								param["student_number"] = this.user.user_id;
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
					bl = this.$check_action('/final_acceptance/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/final_acceptance/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/final_acceptance/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/final_acceptance/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/final_acceptance/view','get');
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
			this.get_list_user_student_number();
			this.get_group_user_student_number();
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
