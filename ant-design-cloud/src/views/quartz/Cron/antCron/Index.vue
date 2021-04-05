<template>
  <div class="components-input-demo-presuffix">
    <a-input :placeholder="placeholder" :value="cron" @input="handleinput">
      <a-icon slot="prefix" type="schedule" title="corn控件" @click="openModal" />
      <a-icon v-if="cron" slot="suffix" type="close-circle" @click="handleEmpty" title="清空" />
    </a-input>
    <AntCron ref="innerVueCron" :data="afterCron" @ok="handleOK"></AntCron>
  </div>
</template>
<script>
import AntCron from "./AntCron";
import { replaceWeekName } from "./validator";
export default {
  name: "ACron",
  components: {
    AntCron
  },
  props: {
    value: {
      required: false,
      type: String,
      default: ""
    },
    placeholder: {
      required: false,
      type: String,
      default: ""
    }
  },
  data() {
    return {
      cron: this.value,
      afterCron: ""
    };
  },
  watch: {
    value(val) {
      this.cron = val;
    },
    cron(val) {
      console.log(replaceWeekName(val));
      this.afterCron = replaceWeekName(val);
      console.log(val);
      this.$emit("input", val);
    }
  },
  methods: {
    openModal() {
      this.$refs.innerVueCron.show();
    },
    handleOK(val) {
      this.cron = val;
      this.$emit("change", this.cron);
    },
    handleinput(evt) {
      this.cron = evt.target.value;
      if (this.cron !== "") {
        this.$emit("change", this.cron);
      } else {
        this.$emit("change", undefined);
      }
    },
    handleEmpty() {
      this.handleOK("");
    }
  },
  model: {
    prop: "value",
    event: "change"
  }
};
</script>
<style scoped>
.components-input-demo-presuffix .anticon-close-circle {
  cursor: pointer;
  color: #ccc;
  transition: color 0.3s;
  font-size: 12px;
}
.components-input-demo-presuffix .anticon-close-circle:hover {
  color: #f5222d;
}
.components-input-demo-presuffix .anticon-close-circle:active {
  color: #666;
}
</style>