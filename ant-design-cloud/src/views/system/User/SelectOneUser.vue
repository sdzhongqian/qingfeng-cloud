<!--  -->
<template>
    <div>
        <div class="table-page-search-wrapper">
            <a-form layout="inline">
                <a-row :gutter="24">
                    <a-col :md="6" :sm="24">
                        <a-form-item label="登录账号">
                            <a-input v-model="queryParam.login_name" placeholder="" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="6" :sm="24">
                        <a-form-item label="姓名">
                            <a-input v-model="queryParam.name" placeholder="" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="6" :sm="24">
                        <a-form-item label="手机号">
                            <a-input v-model="queryParam.phone" placeholder="" />
                        </a-form-item>
                    </a-col>
                    <a-col :md="6" :sm="24">
                        <a-button type="primary" @click="fetch({ results: 10, ...queryParam })">查询</a-button>
                        <a-button style="margin-left: 8px" @click="() => (queryParam = {})">重置</a-button>
                    </a-col>
                </a-row>
            </a-form>
        </div>
        <a-divider />
        <div>
            <a-row :gutter="24">
                <a-col :md="24" :lg="6">
                    <a-card :bordered="false" :bodyStyle="{padding:'0'}">
                        <org-tree ref="child" @selectTree="selectTree"></org-tree>
                    </a-card>
                </a-col>
                <a-col :md="24" :lg="18">
                    <div class="line-boder">
                        <a-radio-group v-model="value" :style="{padding:'10px'}" @change="onChange">
                            <a-radio v-for="(item,i) in list" :value="item.id+':'+item.name">
                                {{item.name}}
                            </a-radio>
                        </a-radio-group>
                    </div>
                    <div id="selectValue" class="line-boder1">
                        <blockquote v-for="(item,i) in selectList">
                            <a-tag color="#2db7f5">
                                {{item.name}} <a @click="delUser(item.id,item.name)">x</a>
                            </a-tag>
                        </blockquote>

                    </div>
                </a-col>
            </a-row>
        </div>
    </div>
</template>

<script>
    import { getList } from "@/api/system/user";
    import OrgTree from "./Tree";
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
                queryParam: {
                    id: "",
                },
                value: 2,
                organize_id: "",
                organize_name: "",
                org_cascade: "",
                level_num: "",
                list: [],
                selectList: []
            }
        },
        components: {
            OrgTree,
        },
        //生命周期 - 创建完成（访问当前this实例）
        created() {

        },
        //生命周期 - 挂载完成（访问DOM元素）
        mounted() {
            const user_id = this.record.user_id;
            const user_name = this.record.user_name;
            this.value = this.record.user_id + ':' + this.record.user_name
            this.selectList = [];
            console.log(user_name);
            if(user_id!=''){
                this.selectList.push({ id:user_id, name:user_name })
            }
        },
        methods: {
            selectTree(id, name, value) {
                this.organize_id = id;
                this.organize_name = name;
                this.org_cascade = value.split(":")[0];
                this.level_num = value.split(":")[1];
                this.fetch({ results: 10 });
            },
            onChange(e) {
                const value = e.target.value.split(':');
                const id = value[0]
                const name = value[1]
                this.selectList = [];
                this.selectList.push({ id, name })
            },
            fetch(params = {}) {
                this.loading = true;
                getList({ organize_id: this.organize_id, ...params }).then((response) => {
                    // Read total count from server
                    this.list = response.data.data
                });
            },
            delUser(id, name) {
                this.selectList = []
                // this.selectList.pop({id,name});
                this.value = ''
            },
            onOk() {
                this.$emit('initValue', this.value, '1')
                console.log("监听了 modal ok 事件");
                return new Promise((resolve) => {
                    resolve(true);
                });
            },
            onCancel() {
                console.log("监听了 modal cancel 事件");
                return new Promise((resolve) => {
                    resolve(true);
                });
            },
        }
    }
</script>
<style scoped>
    /* @import url(); 引入css类 */
    .ant-divider-horizontal {
        margin: 0 0 24px 0;
    }

    .line-boder {
        height: 180px;
        border: 1px solid #5FB878;
        margin-top: 2px;
        border-radius: 4px;
        overflow-y: scroll;
    }

    .line-boder1 {
        height: 88px;
        border: 1px solid #5FB878;
        margin-top: 2px;
        border-radius: 4px;
        overflow-y: scroll;
        padding: 4px;
    }
</style>