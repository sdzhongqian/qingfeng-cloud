<template>
  <page-header-wrapper>
    <a-row :gutter="16">
      <a-col class="gutter-row" :span="12">
        <a-card>
          <div class="title">CPU</div>
          <div class="gutter-box">
            <a-table :columns="cup_columns" :data-source="cup_data" :pagination="false"> </a-table>
          </div>
        </a-card>
      </a-col>
      <a-col class="gutter-row" :span="12">
        <a-card>
          <div class="title">内存</div>
          <div class="gutter-box">
            <a-table :columns="nc_columns" :data-source="nc_data" :pagination="false"> </a-table>
          </div>
        </a-card>
      </a-col>
    </a-row>
    <a-divider style="margin: 10px 0px" />
    <a-card>
      <div class="title">服务器消息</div>
      <div class="gutter-box">
        <a-table :columns="fwq_columns" :data-source="fwq_data" :pagination="false"> </a-table>
      </div>
    </a-card>
    <a-divider style="margin: 10px 0px" />
    <a-card>
      <div class="title">Java虚拟机信息</div>
      <div class="gutter-box">
        <a-table :columns="xnj_columns" :data-source="xnj_data" :pagination="false"> </a-table>
      </div>
    </a-card>
    <a-divider style="margin: 10px 0px" />
    <a-card>
      <div class="title">磁盘状态</div>
      <div class="gutter-box">
        <a-table :columns="cp_columns" :data-source="cp_data" :pagination="false"> </a-table>
      </div>
    </a-card>
    <a-divider style="margin: 10px 0px" />
  </page-header-wrapper>
</template>

<script>
import { getData } from "@/api/monitor/server";
//CPU
const cup_columns = [
  {
    title: "属性",
    dataIndex: "name",
    key: "name",
    ellipsis: true,
  },
  {
    title: "值",
    dataIndex: "value",
    key: "value",
  },
];
//内存
const nc_columns = [
  {
    title: "属性",
    dataIndex: "name",
    key: "name",
    ellipsis: true,
  },
  {
    title: "内存",
    dataIndex: "nc_value",
    key: "nc_value",
  },
  {
    title: "JVM",
    dataIndex: "jvm_value",
    key: "jvm_value",
  },
];
//服务器信息
const fwq_columns = [
  {
    title: "服务器名称",
    dataIndex: "computerName",
    key: "computerName",
    ellipsis: true,
  },
  {
    title: "服务器IP",
    dataIndex: "computerIp",
    key: "computerIp",
  },
  {
    title: "操作系统",
    dataIndex: "osName",
    key: "osName",
  },
  {
    title: "系统架构",
    dataIndex: "osArch",
    key: "osArch",
  },
];
//Java虚拟机信息
const xnj_columns = [
  {
    title: "属性",
    dataIndex: "name1",
    key: "name1",
    ellipsis: true,
  },
  {
    title: "值",
    dataIndex: "value1",
    key: "value1",
  },
  {
    title: "属性",
    dataIndex: "name2",
    key: "name2",
    ellipsis: true,
  },
  {
    title: "值",
    dataIndex: "value2",
    key: "value2",
  },
];
//磁盘状态
const cp_columns = [
  {
    title: "盘符路径",
    dataIndex: "dirName",
    key: "dirName",
    ellipsis: true,
  },
  {
    title: "文件系统",
    dataIndex: "sysTypeName",
    key: "sysTypeName",
  },
  {
    title: "盘符类型",
    dataIndex: "typeName",
    key: "typeName",
    ellipsis: true,
  },
  {
    title: "总大小",
    dataIndex: "total",
    key: "total",
  },
  {
    title: "可用大小",
    dataIndex: "free",
    key: "free",
  },
  {
    title: "已用大小",
    dataIndex: "used",
    key: "used",
  },
  {
    title: "已用百分比",
    dataIndex: "usage",
    key: "usage",
  },
];

export default {
  data() {
    return {
      pagination:false,
      cp_columns,
      nc_columns,
      fwq_columns,
      xnj_columns,
      cup_columns,
      cup_data: [],
      nc_data: [],
      fwq_data: [],
      xnj_data: [],
      cp_data: [],
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
      getData().then((response) => {
        let data = response.data.data;
        //cup信息
        let cup_data1 = {name:'核心数',value:data.cpu.cpuNum+'个'}
        let cup_data2 = {name:'用户使用率',value:data.cpu.used+'%'}
        let cup_data3 = {name:'系统使用率',value:data.cpu.sys+'%'}
        let cup_data4 = {name:'当前空闲率',value:data.cpu.free+'%'}
        this.cup_data.push(cup_data1);
        this.cup_data.push(cup_data2);
        this.cup_data.push(cup_data3);
        this.cup_data.push(cup_data4);
         //cup信息
        let nc_data1 = {name:'总内存',nc_value:data.mem.total+'GB',jvm_value:data.jvm.total+'MB'}
        let nc_data2 = {name:'已用内存',nc_value:data.mem.used+'GB',jvm_value:data.jvm.used+'MB'}
        let nc_data3 = {name:'剩余内存',nc_value:data.mem.free+'GB',jvm_value:data.jvm.free+'MB'}
        let nc_data4 = {name:'使用率',nc_value:data.mem.usage+'%',jvm_value:data.jvm.usage+'%'}
        this.nc_data.push(nc_data1);
        this.nc_data.push(nc_data2);
        this.nc_data.push(nc_data3);
        this.nc_data.push(nc_data4);
        //服务器信息
        let fwq_data1 = {computerName:data.sys.computerName,computerIp:data.sys.computerIp,osName:data.sys.osName,osArch:data.sys.osArch}
        this.fwq_data.push(fwq_data1);
        //Java虚拟机信息
        let xnj_data1 = {name1:'Java名称',value1:data.jvm.name,name2:'Java版本',value2:data.jvm.version}
        let xnj_data2 = {name1:'启动时间',value1:data.jvm.startTime,name2:'运行时长',value2:data.jvm.runTime}
        let xnj_data3 = {name1:'安装路径',value1:data.jvm.home,name2:'项目路径',value2:data.sys.userDir}
        this.xnj_data.push(xnj_data1);
        this.xnj_data.push(xnj_data2);
        this.xnj_data.push(xnj_data3);
        //磁盘信息
        this.cp_data=data.sysFiles
      });
    },
  },
};
</script>

<style lang="less" scoped>
.title {
  color: rgba(0, 0, 0, 0.85);
  font-size: 16px;
  font-weight: 500;
  margin-bottom: 16px;
}
</style>
