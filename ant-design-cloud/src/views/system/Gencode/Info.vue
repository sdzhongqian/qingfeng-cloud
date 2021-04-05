<template>
  <a-form-model
    ref="ruleForm"
    :model="form"
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
              placeholder="请输入表名称"
              disabled
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
              placeholder="请输入表描述"
              disabled
            />
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row class="form-row" :gutter="16">
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="temp_type" label="模板类型" prop="temp_type">
            <a-select
              v-model:value="form.temp_type"
              placeholder="请选择模板类型"
              disabled
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
              placeholder="请输入生成包路径"
              disabled
            />
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row class="form-row" :gutter="16">
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="mod_name" label="生成模块名" prop="mod_name">
            <a-input
              v-model="form.mod_name"
              placeholder="请输入生成模块名"
              disabled
            />
          </a-form-model-item>
        </a-col>
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="bus_name" label="生成业务名" prop="bus_name">
            <a-input
              v-model="form.bus_name"
              placeholder="请输入生成业务名"
              disabled
            />
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row class="form-row" :gutter="16">
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="menu_name" label="功能名称" prop="menu_name">
            <a-input
              v-model="form.menu_name"
              placeholder="请输入功能名称"
              disabled
            />
          </a-form-model-item>
        </a-col>
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="menu_pname" label="上级菜单" prop="menu_pname">
            <a-input
              v-model="form.menu_pname"
              placeholder="请输入上级菜单"
              disabled
            />
          </a-form-model-item>
        </a-col>
      </a-row>
      <a-row class="form-row" :gutter="16">
        <a-col :lg="12" :md="12" :sm="24">
          <a-form-model-item ref="gen_type" label="生成方式" prop="gen_type">
            <a-select
              v-model:value="form.gen_type"
              placeholder="请选择生成方式"
              disabled
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
              disabled
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
              <a-checkbox disabled v-model="record.field_operat"></a-checkbox>
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
              <a-checkbox disabled v-model="record.field_list"></a-checkbox>
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
              <a-checkbox disabled v-model="record.field_query"></a-checkbox>
            </span>
          </template>
        </a-table-column>
        <a-table-column
          key="query_type"
          title="查询方式"
          data-index="query_type"
        >
        </a-table-column>
        <a-table-column
          key="verify_rule"
          title="校验规则	"
          data-index="verify_rule"
        >
        </a-table-column>
        <a-table-column key="show_type" title="显示类型" data-index="show_type">
        </a-table-column>
        <a-table-column
          key="option_content"
          title="选项内容"
          data-index="option_content"
        >
        </a-table-column>
        <a-table-column
          key="default_value"
          title="默认值"
          data-index="default_value"
        >
        </a-table-column>
        <a-table-column key="order_by" title="排序" data-index="order_by">
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
      class="card"
      title="其他信息设置"
      :style="{ padding: '0 auto' }"
      :bordered="false"
    >
      <a-form-model-item label="排序" prop="order_by">
        <a-input v-model="form.order_by" disabled placeholder="排序" />
      </a-form-model-item>
      <a-form-model-item label="备注" prop="remark">
        <a-input v-model="form.remark" disabled type="textarea" />
      </a-form-model-item>
    </a-card>
  </a-form-model>
</template>
<script>
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
  },
  methods: {},
};
</script>
