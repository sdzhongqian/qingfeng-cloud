<template>
  <a-tree
    v-model="checkedKeys"
    checkable
    v-if="treeData.length"
    defaultExpandAll
    :tree-data="treeData"
    @expand="onExpand"
    @select="onSelect"
  />
</template>
<script>
import { getTreeList } from "@/api/system/menu";
import { updateAuth, findRoleMenuList } from "@/api/system/role";

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
      checkedKeys: [],
      treeData: [],
    };
  },
  watch: {
    checkedKeys(val) {
      console.log("onCheck", val);
    },
  },
  mounted() {
    this.getTreeList({});
    this.getRoleMenuList({ role_id: this.record.id });
  },
  methods: {
    onOk() {
      updateAuth({
        role_id: this.record.id,
        ids: this.checkedKeys.join(","),
      }).then((response) => {
        // console.log(response);
        if (response.status==200) {
          this.$message.success("权限分配成功。");
        } else {
          this.$message.error("权限分配失败。");
        }
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
    onExpand(expandedKeys) {
      console.log("onExpand", expandedKeys);
      // if not set autoExpandParent to false, if children expanded, parent can not collapse.
      // or, you can remove all expanded children keys.
      this.expandedKeys = expandedKeys;
      this.autoExpandParent = false;
    },
    onCheck(checkedKeys) {
      console.log("onCheck", checkedKeys);
      this.checkedKeys = checkedKeys;
    },
    onSelect(selectedKeys, info) {
      console.log("onSelect", info);
      this.selectedKeys = selectedKeys;
    },
    getRoleMenuList(params) {
      findRoleMenuList(params).then((response) => {
        console.log('------------------')
        console.log(response)
        this.checkedKeys = response.data.data.map((menu_id) => {
          return menu_id;
        });
        // this.selectedKeys = response.data;
      });
    },

    getTreeList(params) {
      getTreeList(params).then((response) => {
        const treeData = this.fommat({
          arrayList: response.data.data,
          pidStr: "parent_id",
        });
        this.treeData = treeData;
      });
    },
    fommat({
      arrayList,
      pidStr = "parent_id",
      idStr = "id",
      childrenStr = "children",
    }) {
      arrayList.push({
        title: "菜单信息",
        id: "1",
        parent_id: "0",
        menu_cascade: "menu_1_",
        level_num: "0",
        type: "0",
      });
      let listOjb = {}; // 用来储存{key: obj}格式的对象
      let treeList = []; // 用来储存最终树形结构数据的数组
      // 将数据变换成{key: obj}格式，方便下面处理数据
      for (let i = 0; i < arrayList.length; i++) {
        var data = arrayList[i];
        data.key = data.id;
        data.value = data.menu_cascade + ":" + data.level_num + ":" + data.type;
        if (data.child_num == 0) {
          data.isLeaf = true;
        }
        data.icon = "";
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
  },
};
</script>
