<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','entry_name') || $check_field('add','entry_name') || $check_field('set','entry_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目名称" prop="entry_name">
					<el-input id="entry_name" v-model="form['entry_name']" placeholder="请输入项目名称"
							  v-if="user_group === '管理员' || (form['project_sharing_id'] && $check_field('set','entry_name')) || (!form['project_sharing_id'] && $check_field('add','entry_name'))" :disabled="disabledObj['entry_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','entry_name')">{{form['entry_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','project_type') || $check_field('add','project_type') || $check_field('set','project_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="项目类型" prop="project_type">
					<el-input id="project_type" v-model="form['project_type']" placeholder="请输入项目类型"
							  v-if="user_group === '管理员' || (form['project_sharing_id'] && $check_field('set','project_type')) || (!form['project_sharing_id'] && $check_field('add','project_type'))" :disabled="disabledObj['project_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','project_type')">{{form['project_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','affiliated_college') || $check_field('add','affiliated_college') || $check_field('set','affiliated_college')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="所属学院" prop="affiliated_college">
					<el-input id="affiliated_college" v-model="form['affiliated_college']" placeholder="请输入所属学院"
							  v-if="user_group === '管理员' || (form['project_sharing_id'] && $check_field('set','affiliated_college')) || (!form['project_sharing_id'] && $check_field('add','affiliated_college'))" :disabled="disabledObj['affiliated_college_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','affiliated_college')">{{form['affiliated_college']}}</div>
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
						<el-upload v-if="user_group === '管理员' || (form['project_sharing_id'] && $check_field('set','project_annex')) || (!form['project_sharing_id'] && $check_field('add','project_annex'))" class="upload-demo" drag
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
			<el-col v-if="user_group === '管理员' || $check_field('get','project_content') || $check_field('add','project_content') || $check_field('set','project_content')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="项目内容" prop="project_content">
					<quill-editor v-model.number="form['project_content']"
						v-if="user_group === '管理员' || (form['project_sharing_id'] && $check_field('set','project_content')) || (!form['project_sharing_id'] && $check_field('add','project_content')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','project_content')" v-html="form['project_content']"></div>
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
				field: "project_sharing_id",
				url_add: "~/api/project_sharing/add?",
				url_set: "~/api/project_sharing/set?",
				url_get_obj: "~/api/project_sharing/get_obj?",
				url_upload: "~/api/project_sharing/upload?",

				query: {
					"project_sharing_id": 0,
				},

				form: {
					"entry_name":'', // 项目名称
					"project_type":'', // 项目类型
					"affiliated_college":'', // 所属学院
					"project_annex":'', // 项目附件
					"project_content":'', // 项目内容
					"project_sharing_id": 0, // ID

				},
				disabledObj:{
					"entry_name_isDisabled": false,
					"project_type_isDisabled": false,
					"affiliated_college_isDisabled": false,
					"project_annex_isDisabled": false,
					"project_content_isDisabled": false,
				},

			}
		},
		methods: {
			/**
			 * 上传项目附件
			 * @param {Object} param文件参数
			 */
			upload_project_annex(param){
				this.uploadFile(param.file, "project_annex");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
					bl = this.$check_action('/project_sharing/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/project_sharing/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/project_sharing/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/project_sharing/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/project_sharing/view','get');
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
