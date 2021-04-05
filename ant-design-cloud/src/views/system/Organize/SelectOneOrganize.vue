<!--  -->
<template>
    <div>
        <div class="table-page-search-wrapper">
            <a-form layout="inline">
              <a-row :gutter="24">
                <a-col :md="6" :sm="24">
                  <a-form-item label="组织名称">
                    <a-input v-model="queryParam.name" placeholder="" />
                  </a-form-item>
                </a-col>
                <a-col :md="6" :sm="24">
                  <a-form-item label="组织简称">
                    <a-input v-model="queryParam.short_name" placeholder="" />
                  </a-form-item>
                </a-col>
                <a-col :md="6" :sm="24">
                  <a-button
                    type="primary"
                    @click="fetch({ results: 10, ...queryParam })"
                    >查询</a-button
                  >
                  <a-button
                    style="margin-left: 8px"
                    @click="() => (queryParam = {})"
                    >重置</a-button
                  >
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
                                {{item.name}} <a @click="delOrganize(item.id,item.name)">x</a>
                            </a-tag>
                        </blockquote>

                    </div>
                </a-col>
            </a-row>
        </div>
    </div>
</template>

<script>
    import { getTreeList } from "@/api/system/organize";
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
            const organize_id = this.record.organize_id;
            const organize_name = this.record.organize_name;
            this.value = this.record.organize_id + ':' + this.record.organize_name
            this.selectList = [];
            if(organize_id!=''){
                this.selectList.push({ id:organize_id, name:organize_name })
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
                getTreeList({ parent_id: this.organize_id, ...params }).then((response) => {
                    // Read total count from server
                    this.list = response.data.data.list
                });
            },
            delOrganize(id, name) {
                this.selectList = []
                // this.selectList.pop({id,name});
                this.value = ''
            },
            onOk() {
                this.$emit('initValue', this.value, '3')
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