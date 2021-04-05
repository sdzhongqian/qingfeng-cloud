<template>
  <div>
    <a-card
      style="width:100%"
      :tab-list="tabListNoTitle"
      :active-tab-key="noTitleKey"
      @tabChange="key => onTabChange(key, 'noTitleKey')"
    >
        <block v-for="(item,index) of vodeList" :key="index">
             <p v-if="noTitleKey === item.name">
                <pre v-html="item.content"></pre>
            </p>
        </block>
      <a slot="tabBarExtraContent" @click="downloadCode()">下载</a>
    </a-card>
  </div>
</template>

<script>
import { getViewCode } from "@/api/system/gencode";
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
      tabListNoTitle: [],
      key: 'tab1',
      noTitleKey: '',
      vodeList:[]
    };
  },
  mounted() {
    this.findViewCode()
  },
  methods: {
    onTabChange(key, type) {
      console.log(key, type);
      this[type] = key;
    },

    findViewCode(){
        getViewCode(this.record.id).then((response) => {
            var data = response.data.data;
            this.vodeList = data;
            for(var i=0;i<data.length;i++){
                if(i==0){
                    this.noTitleKey = data[i].name;
                }
                this.tabListNoTitle.push({key:data[i].name,tab:data[i].name})
                
            }
        });
    },
    downloadCode(){
        window.location.href = process.env.VUE_APP_API_URL + "/gencode/gencode/downloadCode?id="+this.record.id;
    }
  },
};
</script>