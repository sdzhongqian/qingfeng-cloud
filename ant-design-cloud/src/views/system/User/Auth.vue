<template>
  <div>
    <a-divider orientation="left"> 角色权限分配 </a-divider>
    <a-transfer
      :data-source="mockData"
      :list-style="{
        width: '320px',
        height: '300px',
        marginLeft: '20px',
      }"
      :titles="['角色', '授权角色']"
      :target-keys="targetKeys"
      :selected-keys="selectedKeys"
      :render="(item) => item.title"
      @change="handleChange"
      @selectChange="handleSelectChange"
    />

    <a-divider orientation="left"> 数据权限分配 </a-divider>
    <a-select
      style="width: 100%"
      v-model="currentOrganize"
      placeholder="请选择"
      @change="selectOrgChange"
    >
      <a-select-option
        v-for="org in orgData"
        :key="org.id"
        :value="org.organize_id"
      >
        {{ org.organize_name }}
      </a-select-option>
    </a-select>
    <a-table
      :columns="columns"
      :data-source="data"
      v-if="data.length"
      defaultExpandAllRows
      bordered
    >
      <span slot="showAuth" slot-scope="text">
        <a-checkbox
          :value="text"
          @change="showAuthChange"
          :defaultChecked="showAuthData.indexOf(text) != -1"
        ></a-checkbox
      ></span>
      <span slot="operaAuth" slot-scope="text">
        <a-checkbox
          :value="text"
          @change="operaAuthChange"
          :defaultChecked="operaAuthData.indexOf(text) != -1"
        ></a-checkbox
      ></span>
    </a-table>
  </div>
</template>
<script>
import { updateAuth, getRoleAuth, getOrganizeAuth } from "@/api/system/user";
const columns = [
  { title: "名称", dataIndex: "title", key: "title" },
  {
    title: "查看权限",
    dataIndex: "show_key",
    key: "show_key",
    scopedSlots: { customRender: "showAuth" },
  },
  {
    title: "操作权限（编辑/删除）",
    dataIndex: "opera_key",
    key: "opera_key",
    scopedSlots: { customRender: "operaAuth" },
  },
];

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
      columns,
      data: [],
      showAuth: [],
      operaAuth: [],

      currentOrganize: "",
      orgData: [],
      showAuthData: [],
      operaAuthData: [],
    };
  },
  mounted() {
    this.getRoleAuth();
  },
  methods: {
    getRoleAuth() {
      getRoleAuth({ id: this.record.id }).then((response) => {
        let respData =  response.data.data;
        this.orgData = respData.orgList;
        if (this.orgData.length > 0) {
          this.currentOrganize = this.orgData[0].organize_id;
          this.selectOrgChange(this.currentOrganize);
        }
        for (let i = 0; i < respData.roleLs.length; i++) {
          respData.roleLs[i].key = respData.roleLs[i].id;
          respData.roleLs[i].title = respData.roleLs[i].name;
          respData.roleLs[i].description = respData.roleLs[i].name;
          this.mockData.push({
            key: respData.roleLs[i].id,
            title: respData.roleLs[i].name,
            description: respData.roleLs[i].name,
          });
        }
        for (let i = 0; i < respData.myRoleLs.length; i++) {
          this.mockData.push({
            key: respData.myRoleLs[i].id,
            title: respData.myRoleLs[i].name,
            description: respData.myRoleLs[i].name,
          });
          this.targetKeys.push(respData.myRoleLs[i].id);
        }
      });
    },
    selectOrgChange(value) {
      getOrganizeAuth({ id: this.record.id, organize_id: value }).then(
        (response) => {
          const treeData = this.fommat({
            arrayList: response.data.data.list,
            pidStr: "pid",
          });
          this.data = treeData;
          const object = response.data.data.object;
          if (object.showAuthData != "" && object.showAuthData != null) {
            this.showAuthData = object.showAuthData.split(",");
          }
          if (object.operaAuthData != "" && object.operaAuthData != null) {
            this.operaAuthData = object.operaAuthData.split(",");
          }
        }
      );
    },
    showAuthChange(e) {
      var value = e.target.value;
      if (e.target.checked) {
        let isexist = this.showAuthData.find((c) => c == value);
        if (!isexist) {
          this.showAuthData.push(value);
        }
      } else {
        this.remove(this.showAuthData, value);
      }
      console.log(this.showAuthData);
    },
    operaAuthChange(e) {
      var value = e.target.value;
      if (e.target.checked) {
        let isexist = this.operaAuthData.find((c) => c == value);
        if (!isexist) {
          this.operaAuthData.push(value);
        }
      } else {
        this.remove(this.operaAuthData, value);
      }
    },
    onOk() {
      console.log(this.showAuthData.join(","));
      console.log(this.operaAuthData.join(","));
      updateAuth({
        role_ids: this.targetKeys.join(","),
        id: this.record.id,
        showAuthData: this.showAuthData.join(","),
        operaAuthData: this.operaAuthData.join(","),
        organize_id: this.currentOrganize,
      }).then((response) => {
        // console.log(response);
        if (response.success) {
          this.$message.success(response.msg);
          // this.$success({
          //   title: "提示：",
          //   // JSX support
          //   content: response.msg,
          //   okText: "确认",
          // });
        } else {
          this.$message.error(response.msg);
          // this.$error({
          //   title: "提示：",
          //   content: response.msg,
          //   okText: "确认",
          // });
        }
      });
      this.mybol = true;
      return new Promise((resolve) => {
        resolve(this.mybol);
      });
    },
    showAuthIndexOf(val) {
      var ret = this.showAuthData.indexOf(val);
      return ret;
    },
    operaAuthIndexOf(val) {
      var ret = this.operaAuthData.indexOf(val);
      return ret;
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
    onChange(e) {
      console.log(e);
      console.log(`checked = ${e.target.checked}`);
    },
    fommat({
      arrayList,
      pidStr = "parent_id",
      idStr = "id",
      childrenStr = "children",
    }) {
      let listOjb = {}; // 用来储存{key: obj}格式的对象
      let treeList = []; // 用来储存最终树形结构数据的数组
      // 将数据变换成{key: obj}格式，方便下面处理数据
      for (let i = 0; i < arrayList.length; i++) {
        var data = arrayList[i];
        data.key = data.id;
        data.show_key = data.id + ":" + data.org_cascade;
        data.opera_key = data.id + ":" + data.org_cascade;
        listOjb[arrayList[i][idStr]] = data;
      }
      // 根据pid来将数据进行格式化
      for (let j = 0; j < arrayList.length; j++) {
        // 判断父级是否存在
        let haveParent = listOjb[arrayList[j][pidStr]];
        if (haveParent) {
          // 如果有没有父级children字段，就创建一个children字段
          !haveParent[childrenStr] && (haveParent[childrenStr] = []);
          // 在父级里插入子项
          haveParent[childrenStr].push(arrayList[j]);
        } else {
          // 如果没有父级直接插入到最外层
          treeList.push(arrayList[j]);
        }
      }
      return treeList;
    },
    remove(that, val) {
      var index = that.indexOf(val);
      if (index > -1) {
        that.splice(index, 1);
      }
    },
  },
};
</script>