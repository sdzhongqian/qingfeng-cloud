<template>
  <a-directory-tree
    :tree-data="treeData"
    :defaultSelectedKeys="defaultSelectedKeys"
    :defaultExpandedKeys="defaultExpandedKeys"
    @expand="onExpand"
    @select="onSelect"
  />
</template>
<script>
import { getList } from "@/api/gencode/mytree";
export default {
  data() {
    return {
      defaultSelectedKeys: ["0"],
      defaultExpandedKeys: ["0"],
      expandedKeys: [],
      treeData: [],
    };
  },
  mounted() {
    this.getTreeList({});
  },
  methods: {
    onSelect(keys, info) {
      // console.log("Trigger Select", keys, info);
      // 触发父组件中 selectTree
      this.$emit("selectTree", keys[0], info.node.title);
    },
    onExpand() {
      console.log("Trigger Expand");
    },
    getTreeList(params) {
      getList(params).then((response) => {
        const treeData = this.fommat({
          arrayList: response.data.data,
          pidStr: "parent_id",
        });
        this.treeData = treeData;
        this.defaultExpandedKeys.push(treeData[0].id);
        // 触发父组件中 selectTree
        this.$emit(
          "selectTree",
          treeData[0].id,
          treeData[0].name
        );
    })
    },
    fommat({
      arrayList,
      pidStr = "parent_id",
      idStr = "id",
      childrenStr = "children",
    }) {
      arrayList.push({'name':'测试树表','id':'1','parent_id':'0'});
      let listOjb = {}; // 用来储存{key: obj}格式的对象
      let treeList = []; // 用来储存最终树形结构数据的数组
      // 将数据变换成{key: obj}格式，方便下面处理数据
      for (let i = 0; i < arrayList.length; i++) {
        var data = arrayList[i];
        data.title = data.name;
        data.key = data.id;
        if (data.child_num == 0) {
          data.isLeaf = true;
        }
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
