<template>
  <a-card :bordered="false">
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="12">
          <a-col :md="6" :sm="12">
            <a-form-item label="表名称">
              <a-input v-model="queryParam.name" placeholder="" />
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="12">
            <a-form-item label="表描述">
              <a-input v-model="queryParam.table_comment" placeholder="" />
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="12">
            <a-button
              type="primary"
              @click="fetch({ pageSize: 10, ...queryParam })"
              >查询</a-button
            >
            <a-button style="margin-left: 8px" @click="() => (queryParam = {})"
              >重置</a-button
            >
          </a-col>
        </a-row>
      </a-form>
    </div>
    <a-table
      :row-selection="rowSelection"
      :columns="columns"
      :data-source="data"
      :pagination="pagination"
      :loading="loading"
      @change="handleTableChange"
      bordered
    >
      <template slot-scope="text, record, index">
        <div :key="col">
          {{ text }}
        </div>
      </template>
    </a-table>
  </a-card>
</template>
<script>
import { getTableListPage, save } from "@/api/system/gencode";
import { formatDate } from "@/utils/date.js";
const columns = [
  {
    title: "表名称",
    dataIndex: "table_name",
    sorter: true,
    ellipsis: true,
  },
  {
    title: "表描述",
    dataIndex: "table_comment",
    ellipsis: true,
  },
  {
    title: "创建时间",
    dataIndex: "create_time",
    customRender: (text, row, index) => {
      if (text == "" || text == null) {
        return "";
      } else {
        return formatDate(text);
      }
    },
  },
  {
    title: "更新时间",
    dataIndex: "update_time",
    customRender: (text, row, index) => {
      if (text == "" || text == null) {
        return "";
      } else {
        return formatDate(text);
      }
    },
  },
];

export default {
  data() {
    return {
      data: [],
      pagination: {},
      loading: false,
      columns,
      selectedRows: [],
      queryParam: {
        name: "",
        table_comment: "",
      },
    };
  },
  components: {},
  computed: {
    rowSelection() {
      return {
        onChange: (selectedRowKeys, selectedRows) => {
          this.selectedRows = selectedRows;
          console.log(
            `selectedRowKeys: ${selectedRowKeys}`,
            "selectedRows: ",
            selectedRows
          );
        },
        getCheckboxProps: (record) => ({
          props: {
            disabled: record.name === "Disabled User", // Column configuration not to be checked
            name: record.name,
          },
        }),
      };
    },
  },
  mounted() {
    this.fetch({ pageSize: 10 });
  },
  methods: {
    handleTableChange(pagination, filters, sorter) {
      console.log(pagination);
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      this.fetch({
        pageSize: pagination.pageSize,
        pageNum: pagination.current,
        ...filters,
      });
    },
    fetch(params = {}) {
      this.loading = true;
      getTableListPage(params).then((response) => {
        const pagination = { ...this.pagination };
        pagination.total = response.data.count;
        this.loading = false;
        this.data = response.data.data;
        this.pagination = pagination;
      });
    },
    onOk() {
      const table_names = this.selectedRows.map((map) => {
        return map.table_name;
      });
      if (table_names.length > 0) {
        save({ table_names: table_names.join(",") }).then((response) => {
          if (response.status==200) {
            this.$message.success("导入成功。");
          } else {
            this.$message.error("导入失败");
          }
        });
        this.mybol = true;
      } else {
        this.$error({
          title: "提示：",
          content: "请选择需要导入的数据。",
          okText: "确认",
        });
        this.mybol = false;
      }
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
  },
};
</script>
<style scoped>
</style>