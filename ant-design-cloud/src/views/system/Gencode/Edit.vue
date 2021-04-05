<template>
  <a-form-model
    ref="ruleForm"
    :model="form"
    :rules="rules"
    :label-col="labelCol"
    :wrapper-col="wrapperCol"
  >
    <a-card
      class="card"
      title="基础信息设置"
      :style="{ padding: '0 auto' }"
      :bordered="false"
    >
      <a-row class="form-row" :gutter="16">
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="table_name" label="表名称" prop="table_name">
            <a-input
              v-model="form.table_name"
              placeholder="表名称"
              @blur="
                () => {
                  $refs.table_name.onFieldBlur();
                }
              "
            />
          </a-form-model-item>
        </a-col>
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item
            ref="table_comment"
            label="表描述"
            prop="table_comment"
          >
            <a-input
              v-model="form.table_comment"
              placeholder="表描述"
              @blur="
                () => {
                  $refs.table_comment.onFieldBlur();
                }
              "
            />
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row class="form-row" :gutter="16">
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="temp_type" label="模板类型" prop="temp_type">
            <a-select
              v-model:value="form.temp_type"
              placeholder="模板类型"
            >
              <a-select-option value="0"> 单表 </a-select-option>
              <a-select-option value="1"> 树表 </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item
            ref="pack_path"
            label="生成包路径"
            prop="pack_path"
          >
            <a-input
              v-model="form.pack_path"
              placeholder="生成包路径"
              @blur="
                () => {
                  $refs.pack_path.onFieldBlur();
                }
              "
            />
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row class="form-row" :gutter="16">
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="mod_name" label="生成模块名" prop="mod_name">
            <a-input
              v-model="form.mod_name"
              placeholder="生成模块名"
              @blur="
                () => {
                  $refs.mod_name.onFieldBlur();
                }
              "
            />
          </a-form-model-item>
        </a-col>
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="bus_name" label="生成业务名" prop="bus_name">
            <a-input
              v-model="form.bus_name"
              placeholder="生成业务名"
              @blur="
                () => {
                  $refs.bus_name.onFieldBlur();
                }
              "
            />
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row class="form-row" :gutter="16">
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="menu_name" label="功能名称" prop="menu_name">
            <a-input
              v-model="form.menu_name"
              placeholder="功能名称"
              @blur="
                () => {
                  $refs.menu_name.onFieldBlur();
                }
              "
            />
          </a-form-model-item>
        </a-col>
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="menu_id" label="上级菜单目录" prop="menu_id">
            <a-tree-select
              v-model="form.menu_id"
              style="width: 100%"
              :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
              :tree-data="treeData"
              placeholder="菜单目录"
              tree-default-expand-all
            >
            </a-tree-select>
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row class="form-row" :gutter="16">
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="gen_type" label="生成方式" prop="gen_type">
            <a-select
              v-model:value="form.gen_type"
              placeholder="请选择生成方式"
            >
              <a-select-option value="0"> zip包下载 </a-select-option>
              <a-select-option value="1"> 生成到指定路径 </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item
            ref="gen_path"
            label="生成路径"
            v-show="form.gen_type == 1"
            prop="gen_path"
          >
            <a-input
              v-model="form.gen_path"
              placeholder="请输入生成路径"
              @blur="
                () => {
                  $refs.pack_path.onFieldBlur();
                }
              "
            />
          </a-form-model-item>
        </a-col>
      </a-row>
    </a-card>
    <a-card
      class="card"
      title="字段信息设置"
      :style="{ padding: '0 auto' }"
      :bordered="false"
    >
      <a-table :data-source="form.fieldList" :pagination="false">
        <a-table-column
          key="field_name"
          title="字段名称"
          data-index="field_name"
        />
        <a-table-column
          key="field_comment"
          title="字段描述"
          data-index="field_comment"
        >
          <template slot-scope="text, record, index">
            <span>
              <a-input
                :style="{ width: '80px' }"
                v-model="record.field_comment"
                placeholder="请输入字段描述"
              />
            </span>
          </template>
        </a-table-column>
        <!-- <a-table-column
          key="field_type"
          title="字段类型"
          data-index="field_type"
        /> -->
        <a-table-column
          key="field_operat"
          title="添加编辑"
          data-index="field_operat"
        >
          <template slot-scope="text, record, index">
            <span>
              <a-checkbox v-model="record.field_operat"></a-checkbox>
            </span>
          </template>
        </a-table-column>
        <a-table-column
          key="field_list"
          title="列表展示"
          data-index="field_list"
        >
          <template slot-scope="text, record, index">
            <span>
              <a-checkbox v-model="record.field_list"></a-checkbox>
            </span>
          </template>
        </a-table-column>
        <a-table-column
          key="field_query"
          title="查询展示"
          data-index="field_query"
        >
          <template slot-scope="text, record, index">
            <span>
              <a-checkbox v-model="record.field_query"></a-checkbox>
            </span>
          </template>
        </a-table-column>
        <a-table-column
          key="query_type"
          title="查询方式"
          data-index="query_type"
        >
          <template slot-scope="text, record, index">
            <a-select
              :style="{ width: '80px' }"
              v-model:value="record.query_type"
              placeholder="查询方式"
            >
              <a-select-option value="="> = </a-select-option>
              <a-select-option value="!="> != </a-select-option>
              <a-select-option value=">"> > </a-select-option>
              <a-select-option value=">="> >= </a-select-option>
              <a-select-option value="<"> < </a-select-option>
              <a-select-option value="<="> <= </a-select-option>
              <a-select-option value="like"> like </a-select-option>
              <a-select-option value="is null"> is null </a-select-option>
              <a-select-option value="is not null">
                is not null
              </a-select-option>
            </a-select>
          </template>
        </a-table-column>
        <a-table-column
          key="verify_rule"
          title="校验规则	"
          data-index="verify_rule"
        >
          <template slot-scope="text, record, index">
            <a-select
              :style="{ width: '100px' }"
              v-model:value="record.verify_rule"
              placeholder="校验规则"
            >
              <a-select-option value="required"> required </a-select-option>
            </a-select>
          </template>
        </a-table-column>
        <a-table-column key="show_type" title="显示类型" data-index="show_type">
          <template slot-scope="text, record, index">
            <a-select
              v-model:value="record.show_type"
              placeholder="请选择显示类型"
            >
              <a-select-option value="1"> 文本框 </a-select-option>
              <a-select-option value="2"> 文本域 </a-select-option>
              <a-select-option value="3"> 下拉框 </a-select-option>
              <a-select-option value="4"> 单选框 </a-select-option>
              <a-select-option value="5"> 复选框 </a-select-option>
              <a-select-option value="6"> 富文本 </a-select-option>
              <a-select-option value="7"> 日期控件 </a-select-option>
              <a-select-option value="8"> 上传控件 </a-select-option>
            </a-select>
          </template>
        </a-table-column>
        <a-table-column
          key="option_content"
          title="选项内容"
          data-index="option_content"
        >
          <template slot-scope="text, record, index">
            <span>
              <a-input
                v-show="
                  record.show_type == '3' ||
                  record.show_type == '4' ||
                  record.show_type == '5'
                "
                v-model="record.option_content"
                placeholder="请输入选项内容述"
              />
            </span>
          </template>
        </a-table-column>
        <a-table-column
          key="default_value"
          title="默认值"
          data-index="default_value"
        >
          <template slot-scope="text, record, index">
            <span>
              <a-input
                :style="{ width: '80px' }"
                v-model="record.default_value"
                placeholder="默认值"
              />
            </span>
          </template>
        </a-table-column>
        <a-table-column key="order_by" title="排序" data-index="order_by">
          <template slot-scope="text, record, index">
            <span>
              <a-input
                :style="{ width: '40px' }"
                v-model="record.order_by"
                placeholder="排序"
              />
            </span>
          </template>
        </a-table-column>
      </a-table>
      <div>
        说明：字段【{{
          form.excludeField
        }}】属于系统保留字段，会默认存储使用，不可设置业务操作，如需操作可后续修改代码使用！！！
        <br />
        选项内容说明：
        模式一：取值方式：[值]/[显示文字];[值]/[显示文字];...。例：【0/北京;1/上海;2/深圳】。模式二：关联字典表，选项内容填写父节点字段code值，默认值填写对应code值。<br />
        关联字段说明：【选择关联字段】会重新进行设置，上面列表中对应字段的设置情况会失效。
      </div>
    </a-card>
    <a-card
      v-show="form.temp_type=='1'"
      class="card"
      title="树结构信息设置"
      :style="{ padding: '0 auto' }"
      :bordered="false"
    >
    <a-row class="form-row" :gutter="16">
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="tree_pid" label="父节点字段" prop="tree_pid">
             <a-select
              v-model:value="form.tree_pid"
              placeholder="父节点字段"
            >
             <a-select-option v-for="(item,index) in form.fieldList" :value="item.field_name"> {{item.field_comment}} </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item
            ref="tree_name"
            label="节点名称字段"
            prop="tree_name"
          >
            <a-select
              v-model:value="form.tree_name"
              placeholder="节点名称字段"
            >
              <a-select-option v-for="(item,index) in form.fieldList" :value="item.field_name"> {{item.field_comment}} </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-col>
      </a-row>
      说明：	在树表中，【父节点字段】会重新进行设置，上面列表中对应字段的设置情况会失效。
    </a-card>
    <a-card
      class="card"
      title="其他信息设置"
      :style="{ padding: '0 auto' }"
      :bordered="false"
    >
      <a-form-model-item label="排序" prop="order_by">
        <a-input v-model="form.order_by" placeholder="排序" />
      </a-form-model-item>
      <a-form-model-item label="备注" prop="remark">
        <a-input v-model="form.remark" type="textarea" />
      </a-form-model-item>
    </a-card>
  </a-form-model>
</template>
<script>
import { update } from "@/api/system/gencode";
import { getTreeList } from "@/api/system/menu";

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
      form: {
        id: "",
      },
      treeData: [],
      rules: {
        name: [
          { required: true, message: "必填项不能为空", trigger: "blur" },
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
        short_name: [
          { min: 0, max: 50, message: "长度不得大于50个字符", trigger: "blur" },
        ],
        short_name: [
          { required: true, message: "必填项不能为空", trigger: "blur" },
        ],
        remark: [
          {
            min: 0,
            max: 500,
            message: "长度不得大于500个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  mounted() {
    this.form = this.record;
    for (let i = 0; i < this.form.fieldList.length; i++) {
      //字段操作
      if (
        this.form.fieldList[i].field_operat == "Y" ||
        this.form.fieldList[i].field_operat == true
      ) {
        this.form.fieldList[i].field_operat = true;
      } else {
        this.form.fieldList[i].field_operat = false;
      }
      //字段列表
      if (
        this.form.fieldList[i].field_list == "Y" ||
        this.form.fieldList[i].field_list == true
      ) {
        this.form.fieldList[i].field_list = true;
      } else {
        this.form.fieldList[i].field_list = false;
      }
      //字段查询
      if (
        this.form.fieldList[i].field_query == "Y" ||
        this.form.fieldList[i].field_query == true
      ) {
        this.form.fieldList[i].field_query = true;
      } else {
        this.form.fieldList[i].field_query = false;
      }
    }
    this.getTreeList({types:'0'});
  },
  methods: {
    getTreeList(params) {
      getTreeList(params).then((response) => {
        const treeData = this.fommat({
          arrayList: response.data.data,
          pidStr: "parent_id",
        });
        this.treeData = treeData;
      });
    },
    onOk() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          update(this.form).then((response) => {
          });
          this.mybol = true;
          return true;
        } else {
          this.mybol = false;
          console.log("error submit!!");
          return false;
        }
      });
      console.log("监听了 modal ok 事件" + this.mybol);
      return new Promise((resolve) => {
        resolve(this.mybol);
      });
    },
    onCancel() {
      console.log("监听了 modal cancel 事件");
      return new Promise((resolve) => {
        resolve(true);
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
        data.value = data.id;
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
