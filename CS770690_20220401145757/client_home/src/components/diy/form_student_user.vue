<template>
	<div>
		<b-form-group v-if="$check_register_field('add','student_number','/student_user/view')" id="input-group-0" label="学生学号" label-for="input-0">
			<b-form-input id="input-0" v-model="form['student_number']" type="text" placeholder="学生学号" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','student_name','/student_user/view')" id="input-group-1" label="学生姓名" label-for="input-1">
			<b-form-input id="input-1" v-model="form['student_name']" type="text" placeholder="学生姓名" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','student_gender','/student_user/view')" id="input-group-2" label="学生性别" label-for="input-2">
			<b-form-select id="input-2" v-model="form['student_gender']" :options="getDropList(list_student_gender)"></b-form-select>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','student_age','/student_user/view')" id="input-group-3" label="学生年龄" label-for="input-3">
			<b-form-input id="input-3" v-model="form['student_age']" type="text" placeholder="学生年龄" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','affiliated_college','/student_user/view')" id="input-group-4" label="所属学院" label-for="input-4">
			<b-form-input id="input-4" v-model="form['affiliated_college']" type="text" placeholder="所属学院" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','class_name','/student_user/view')" id="input-group-5" label="班级名称" label-for="input-5">
			<b-form-input id="input-5" v-model="form['class_name']" type="text" placeholder="班级名称" trim></b-form-input>
		</b-form-group>
	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		model: {
			prop: "form",
			event: "change"
		},
		props: {
			form: {
				type: Object,
				default: () => {
					return {

					}
				}
			}
		},
		data(){
			return {
				url_upload: "~/api/student_user/upload?",
						list_student_gender: "男,女",
			}
		},
		methods: {
			// 下拉数据列表转换
			getDropList(e){
				let resultArr = [];
				if(e){
					let arr = e.split(",");
					arr.forEach(item => {
						resultArr.push({
							value: item,
							text: item
						})
					});
				}
				return resultArr;
			},
			/**
			 * 上传图片
			 * @param {Object} file 文件对象
			 * @param {key} 保存键名
			 */
			uploadFile(file, key = "img") {
				var _this = this;
				var form = new FormData() // FormData 对象
				form.append('file', file[0]) // 文件对象
				this.$upload(this.url_upload, form, function(json) {
					if (json.result) {
						// _this.form[key] = json.result.url;
						_this.$delete(_this.form,key);
						_this.$set(_this.form,key,json.result.url);
						console.log(_this.form[key])
					} else {
						_this.$toast('上传失败！');
					}
				});
			},
		}
	}
</script>

<style>
</style>
