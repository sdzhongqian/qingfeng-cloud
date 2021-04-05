<!--  -->
<template>
    <div>
        <a-divider orientation="left">
            单选用户
        </a-divider>
        <a-input v-model="user.user_id" placeholder="用户id" />
        <a-input v-model="user.user_name" placeholder="用户名称" />
        <a-button @click="selectOneUser" type="primary">
            选择单用户
        </a-button>

        <a-divider orientation="left">
            多选用户
        </a-divider>
        <a-input v-model="users.user_ids" placeholder="用户ids" />
        <a-input v-model="users.user_names" placeholder="用户名称s" />
        <a-button @click="selectMoreUser" type="primary">
            选择多用户
        </a-button>

        <a-divider orientation="left">
            单选组织
        </a-divider>
        <a-input v-model="organize.organize_id" placeholder="组织id" />
        <a-input v-model="organize.organize_name" placeholder="组织名称" />
        <a-button @click="selectOneOrganize" type="primary">
            选择单组织
        </a-button>

        <a-divider orientation="left">
            多选组织
        </a-divider>
        <a-input v-model="organizes.organize_ids" placeholder="组织ids" />
        <a-input v-model="organizes.organize_names" placeholder="组织名称s" />
        <a-button @click="selectMoreOrganize" type="primary">
            选择多组织
        </a-button>
    </div>
</template>

<script>
    import SelectOneUser from "@/views/system/User/SelectOneUser";
    import SelectMoreUser from "@/views/system/User/SelectMoreUser";
    import SelectOneOrganize from "@/views/system/Organize/SelectOneOrganize";
    import SelectMoreOrganize from "@/views/system/Organize/SelectMoreOrganize";
    export default {
        name: "",
        data() {
            return {
                user: {
                    user_id: '',
                    user_name: '',
                },
                users: {
                    user_ids: '',
                    user_names: '',
                },
                organize: {
                    organize_id: '',
                    organize_name: '',
                },
                organizes: {
                    organize_ids: '',
                    organize_names: '',
                },
            }
        },
        //生命周期 - 创建完成（访问当前this实例）
        created() {

        },
        //生命周期 - 挂载完成（访问DOM元素）
        mounted() {

        },
        methods: {
            selectOneUser() {
                const user = this.user
                this.dialog(SelectOneUser, user)
            },
            selectMoreUser() {
                const users = this.users
                this.dialog(SelectMoreUser, users)
            },
            selectOneOrganize() {
                const organize = this.organize
                this.dialog(SelectOneOrganize, organize)
            },
            selectMoreOrganize() {
                const organizes = this.organizes
                this.dialog(SelectMoreOrganize, organizes)
            },
            dialog(component, record) {
                console.log(component, record);
                const that = this
                this.$dialog(
                    component,
                    // component props
                    {
                        record,
                        on: {
                            ok() {
                                console.log("ok 回调");
                            },
                            cancel() {
                                console.log("cancel 回调");
                            },
                            close() {
                                console.log("modal close 回调");
                            },
                            initValue(value, type) {
                                if (type == '1') {
                                    that.user.user_id = value.split(':')[0]
                                    that.user.user_name = value.split(':')[1]
                                } else if (type == '2') {
                                    that.users.user_ids = value.split(':')[0]
                                    that.users.user_names = value.split(':')[1]
                                }else if (type == '3') {
                                    console.log(value);
                                    that.organize.organize_id = value.split(':')[0]
                                    that.organize.organize_name = value.split(':')[1]
                                } else if (type == '4') {
                                    that.organizes.organize_ids = value.split(':')[0]
                                    that.organizes.organize_names = value.split(':')[1]
                                }
                            }
                        },
                    },
                    // modal props
                    {
                        title: "操作",
                        width: 800,
                        height: 500,
                        centered: true,
                        maskClosable: false,
                        okText: "确认",
                        cancelText: "取消",
                    }
                );
            }

        }

    }
</script>
<style scoped>
    /* @import url(); 引入css类 */
</style>