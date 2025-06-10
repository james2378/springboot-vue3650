<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="校园资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/interim_inspection/list', 'get')"
				:list="result_interim_inspection_entry_name"
				title="中期检查项目名称"
				source_table="interim_inspection"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student_user/list', 'get')"
				:list="result_student_user_student_number"
				title="学生用户学生学号"
				source_table="student_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/teacher_user/list', 'get')"
				:list="result_teacher_user_teacher_job_number"
				title="老师用户老师工号"
				source_table="teacher_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/hospital_level_approver/list', 'get')"
				:list="result_hospital_level_approver_approver_job_number"
				title="院级审批者审批者工号"
				source_table="hospital_level_approver"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/project_declaration/list', 'get')"
				:list="result_project_declaration_entry_name"
				title="项目申报项目名称"
				source_table="project_declaration"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/final_acceptance/list', 'get')"
				:list="result_final_acceptance_entry_name"
				title="结题验收项目名称"
				source_table="final_acceptance"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/project_rental_team/list', 'get')"
				:list="result_project_rental_team_entry_name"
				title="项目租队项目名称"
				source_table="project_rental_team"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/project_sharing/list', 'get')"
				:list="result_project_sharing_entry_name"
				title="项目共享项目名称"
				source_table="project_sharing"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_interim_inspection_entry_name":[],
			"result_student_user_student_number":[],
			"result_teacher_user_teacher_job_number":[],
			"result_hospital_level_approver_approver_job_number":[],
			"result_project_declaration_entry_name":[],
			"result_final_acceptance_entry_name":[],
			"result_project_rental_team_entry_name":[],
			"result_project_sharing_entry_name":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取entry_name
	 */
	get_interim_inspection_entry_name(){
		this.$get("~/api/interim_inspection/get_list?like=0", { page: 1, size: 10, "entry_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_interim_inspection_entry_name = json.result.list;
			result_interim_inspection_entry_name.map(o => o.title = o['entry_name'])
	  			this.result_interim_inspection_entry_name = result_interim_inspection_entry_name
		 	}
		});
	},
	/**
	 * 获取student_number
	 */
	get_student_user_student_number(){
		this.$get("~/api/student_user/get_list?like=0", { page: 1, size: 10, "student_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_user_student_number = json.result.list;
			result_student_user_student_number.map(o => o.title = o['student_number'])
	  			this.result_student_user_student_number = result_student_user_student_number
		 	}
		});
	},
	/**
	 * 获取teacher_job_number
	 */
	get_teacher_user_teacher_job_number(){
		this.$get("~/api/teacher_user/get_list?like=0", { page: 1, size: 10, "teacher_job_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_user_teacher_job_number = json.result.list;
			result_teacher_user_teacher_job_number.map(o => o.title = o['teacher_job_number'])
	  			this.result_teacher_user_teacher_job_number = result_teacher_user_teacher_job_number
		 	}
		});
	},
	/**
	 * 获取approver_job_number
	 */
	get_hospital_level_approver_approver_job_number(){
		this.$get("~/api/hospital_level_approver/get_list?like=0", { page: 1, size: 10, "approver_job_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_hospital_level_approver_approver_job_number = json.result.list;
			result_hospital_level_approver_approver_job_number.map(o => o.title = o['approver_job_number'])
	  			this.result_hospital_level_approver_approver_job_number = result_hospital_level_approver_approver_job_number
		 	}
		});
	},
	/**
	 * 获取entry_name
	 */
	get_project_declaration_entry_name(){
		this.$get("~/api/project_declaration/get_list?like=0", { page: 1, size: 10, "entry_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_project_declaration_entry_name = json.result.list;
			result_project_declaration_entry_name.map(o => o.title = o['entry_name'])
	  			this.result_project_declaration_entry_name = result_project_declaration_entry_name
		 	}
		});
	},
	/**
	 * 获取entry_name
	 */
	get_final_acceptance_entry_name(){
		this.$get("~/api/final_acceptance/get_list?like=0", { page: 1, size: 10, "entry_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_final_acceptance_entry_name = json.result.list;
			result_final_acceptance_entry_name.map(o => o.title = o['entry_name'])
	  			this.result_final_acceptance_entry_name = result_final_acceptance_entry_name
		 	}
		});
	},
	/**
	 * 获取entry_name
	 */
	get_project_rental_team_entry_name(){
		this.$get("~/api/project_rental_team/get_list?like=0", { page: 1, size: 10, "entry_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_project_rental_team_entry_name = json.result.list;
			result_project_rental_team_entry_name.map(o => o.title = o['entry_name'])
	  			this.result_project_rental_team_entry_name = result_project_rental_team_entry_name
		 	}
		});
	},
	/**
	 * 获取entry_name
	 */
	get_project_sharing_entry_name(){
		this.$get("~/api/project_sharing/get_list?like=0", { page: 1, size: 10, "entry_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_project_sharing_entry_name = json.result.list;
			result_project_sharing_entry_name.map(o => o.title = o['entry_name'])
	  			this.result_project_sharing_entry_name = result_project_sharing_entry_name
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_interim_inspection_entry_name();
		this.get_student_user_student_number();
		this.get_teacher_user_teacher_job_number();
		this.get_hospital_level_approver_approver_job_number();
		this.get_project_declaration_entry_name();
		this.get_final_acceptance_entry_name();
		this.get_project_rental_team_entry_name();
		this.get_project_sharing_entry_name();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_interim_inspection_entry_name();
	  this.get_student_user_student_number();
	  this.get_teacher_user_teacher_job_number();
	  this.get_hospital_level_approver_approver_job_number();
	  this.get_project_declaration_entry_name();
	  this.get_final_acceptance_entry_name();
	  this.get_project_rental_team_entry_name();
	  this.get_project_sharing_entry_name();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
