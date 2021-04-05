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
                    <div :style="{ borderBottom: '1px solid #E9E9E9' }">
                        <a-checkbox @change="onCheckAllChange">
                            全选
                        </a-checkbox>
                    </div>
                    <div class="line-boder">
                        <a-checkbox-group v-model="value" :style="{padding:'10px'}" @change="onChange">
                            <a-checkbox v-for="(item,i) in list" :value="item.id+':'+item.name">
                                {{item.name}}
                            </a-checkbox>
                        </a-checkbox-group>
                    </div>
                    <div id="selectValue" class="line-boder1">
                        <a-tag v-for="(item,i) in selectList" color="#2db7f5" :style="{float:'left'}">
                            {{item.name}} <a @click="delOrganize(item.id,item.name)">x</a>
                        </a-tag>
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
                value: [],
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
            const organize_id = this.record.organize_ids.split(",");
            const organize_name = this.record.organize_names.split(",");
            organize_id.filter((item, i) => {
                this.value.push(organize_id[i] + ':' + organize_name[i])
                if (organize_id[i] != '') {
                    this.selectList.push({ id: organize_id[i], name: organize_name[i] })
                }
            })
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
                this.value = e;
                const that = this;
                that.selectList = [];
                e.map(function (item, key, ary) {
                    const id = item.split(':')[0]
                    const name = item.split(':')[1]
                    console.log(id + "#" + name);
                    that.selectList.push({ id, name })
                });
            },
            fetch(params = {}) {
                this.loading = true;
                getTreeList({ parent_id: this.organize_id, ...params }).then((response) => {
                    // Read total count from server
                    this.list = response.data.data.list
                });
            },
            delOrganize(id, name) {
                this.removeObject(this.selectList, id)
                this.remove(this.value, id + ":" + name)
            },
            onOk() {
                const organize_ids = [];
                const organize_names = [];
                this.value.filter((item, i) => {
                    if (item != '') {
                        organize_ids.push(item.split(":")[0])
                        organize_names.push(item.split(":")[1])
                    }
                })
                this.$emit('initValue', organize_ids + ":" + organize_names, '4')
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
            remove(that, val) {
                var index = that.indexOf(val);
                if (index > -1) {
                    that.splice(index, 1);
                }
            },
            removeObject(that, val) {
                that.map(function (item, key, ary) {
                    if (item.id == val) {
                        that.splice(key, 1);
                    }
                })
            },
            onCheckAllChange(e) {
                const that = this;
                if (e.target.checked) {
                    this.list.map(function (item, index) {
                        if (that.value.indexOf(item.id + ':' + item.name) == -1) {
                            that.value.push(item.id + ':' + item.name)
                            if (item.id != '') {
                                that.selectList.push({ id: item.id, name: item.name })
                            }
                        }
                    })
                } else {
                    this.list.map(function (item, index) {
                        that.removeObject(that.selectList, item.id)
                        that.remove(that.value, item.id + ":" + item.name)
                    })
                }
            }
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