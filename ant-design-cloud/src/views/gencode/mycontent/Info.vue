<template>
    <a-form-model
        ref="ruleForm"
        :model="form"
        :rules="rules"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
    >
                        <a-form-model-item label="标题" prop="title">
                    <a-input disabled v-model="form.title" type="textarea" />
                </a-form-model-item>
                <a-form-model-item ref="intro" label="简介" prop="intro">
                    <div v-for="(item, index) in introFileList" :style="{ lineHeight: '30px' }">
                        <a @click="downloadintroFile(item)"><a-icon type="link" />{{ item.name }}</a>
                        <a @click="delintroFile(item)" :style="{ paddingLeft: '10px', color: 'red' }"><a-icon type="delete"/></a>
                    </div>
                </a-form-model-item>
                <a-form-model-item ref="content" label="内容" prop="content">
                    <a-select disabled v-model="form.content" placeholder="请选择内容">
                        <a-select-option v-for="(item, index) in content_data" :value="item.id"> {{item.name}} </a-select-option>
                    </a-select>
                </a-form-model-item>
                <a-form-model-item ref="read_num" label="阅读数量" prop="read_num">
                    <a-checkbox-group disabled v-model="form.read_num">
                        <a-checkbox v-for="(item, index) in read_num_data" :value="item.id">{{item.name}}</a-checkbox>
                    </a-checkbox-group>
                </a-form-model-item>
                <a-form-model-item ref="order_by" label="排序" prop="order_by">
                    <a-input
                        disabled
                        v-model="form.order_by"
                        @blur="() => {
                            $refs.order_by.onFieldBlur();
                        }"
                    />
                </a-form-model-item>
                <a-form-model-item ref="remark" label="备注" prop="remark">
                    <rich-text disabled :text="form.remark" @editorChange="editorChange" />
                </a-form-model-item>
    </a-form-model>
</template>
<script>
    import { saveOrUpdate } from "@/api/gencode/mycontent";
    import { findDictionaryList } from "@/api/system/dictionary";
            import { upload } from "@/api/common/upload";
            import RichText from "@/components/Common/RichText";
    export default {
        // 声明当前子组件接收父组件传递的属性
        props: {
            record: {
                type: Object,
                default: null,
            },
        },
        data() {
            return {
                labelCol: { span: 4 },
                wrapperCol: { span: 14 },
                other: "",
                mybol: false,
                headers: {
                    authorization: "authorization-text",
                },
                uploading: false,
                form: {
                    id: "",
                    title:"",
                    intro:"",
                    content:"",
                    read_num:[],
                    order_by:"",
                    remark:"",
                },
                 content_data:[],
      read_num_data:[],
                introFileList: [],
                content:[],
                read_num:[],
                rules: {
                title: [
                    { required: true, message: "必填项不能为空", trigger: "blur" },
                    { min: 0, max: 120, message: "长度不得大于120个字符", trigger: "blur" },
                ],
            },
        }
    },
    components: {
        RichText
    },
    mounted() {
        if(this.record.id != undefined) {
            this.form = this.record;
        }
            if (this.record.introFileList != undefined) {
                this.introFileList = this.record.introFileList
            }
        this.initData();
    },
    methods: {
        delintroFile(record,fileList) {
            let index = this.introFileList.findIndex((ele) => {
                return ele.id === record.id;
            })
            //假设没有找到
            if (index === -1) {
                return console.log("删除失败");
            }
            //删除元素
            this.introFileList.splice(index, 1);
            this.form.intro = this.introFileList.map((map) => {
                return map.id;
            })
        },
        downloadintroFile(record) {
            window.location.href =
                process.env.VUE_APP_API_URL +
                "/system/upload/downloadFile?name=" +
                record.name +
                "&file_path=" +
                record.file_path;
        },
        editorChange: function (html) {
            this.content = html;
        },
        resetForm() {
            this.$refs.ruleForm.resetFields();
        },
        initData(){
                this.form.title = '1';
                findDictionaryList({parent_code:'fl1001'}).then((response) => {
                    this.content_data = response.data;
                })
                const options = [
                        { id: '0', name: '北京' },
                        { id: '1', name: '上海' },
                        { id: '2', name: '深圳' },
                ];
                this.read_num_data = options;
                this.form.read_num = ['1'];
        },
    },
  };
</script>
