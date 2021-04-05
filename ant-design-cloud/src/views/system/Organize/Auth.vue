<template>
  <div>
    <a-transfer :data-source="mockData" :list-style="{
          width: '320px',
          height: '300px',
          marginLeft: '20px'
        }" :titles="['角色', '授权角色']" :target-keys="targetKeys" :selected-keys="selectedKeys"
      :render="item => item.title" @change="handleChange" @selectChange="handleSelectChange"/>
  </div>
</template>
<script>
  import { updateAuth, getRoleAuth } from "@/api/system/organize";
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
        mockData: [],
        targetKeys: [],
        selectedKeys: [],
      };
    },
    mounted() {
      this.getRoleAuth();
    },
    methods: {
      getRoleAuth() {
        getRoleAuth({ id: this.record.id }).then((response) => {
          console.log(response)
          let respData =  response.data.data;
          for (let i = 0; i < respData.roleLs.length; i++) {
            respData.roleLs[i].key = respData.roleLs[i].id
            respData.roleLs[i].title = respData.roleLs[i].name
            respData.roleLs[i].description = respData.roleLs[i].name
            this.mockData.push({ key: respData.roleLs[i].id, title: respData.roleLs[i].name, description: respData.roleLs[i].name })
          }
          for (let i = 0; i < respData.myRoleLs.length; i++) {
            this.mockData.push({ key: respData.myRoleLs[i].id, title: respData.myRoleLs[i].name, description: respData.myRoleLs[i].name })
            this.targetKeys.push(respData.myRoleLs[i].id)
          }
        });
      },
      onOk() {
        updateAuth({ role_ids: this.targetKeys.join(','), id: this.record.id }).then((response) => {
          // console.log(response);
        });
        this.mybol = true;
        return new Promise((resolve) => {
          resolve(this.mybol);
        });
      },
      onCancel() {
        return new Promise((resolve) => {
          resolve(true);
        });
      },
      handleChange(nextTargetKeys, direction, moveKeys) {
        this.targetKeys = nextTargetKeys;

        // console.log('targetKeys: ', nextTargetKeys);
        // console.log('direction: ', direction);
        // console.log('moveKeys: ', moveKeys);
      },
      handleSelectChange(sourceSelectedKeys, targetSelectedKeys) {
        this.selectedKeys = [...sourceSelectedKeys, ...targetSelectedKeys];

        // console.log('sourceSelectedKeys: ', sourceSelectedKeys);
        // console.log('targetSelectedKeys: ', targetSelectedKeys);
      },
    },
  };
</script>