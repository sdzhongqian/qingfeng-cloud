<template>
  <div>
    <rich-text :text="content" @editorChange="editorChange" />
    {{ content }}
  </div>
</template>
<script>
import RichText from "@/components/Common/RichText";
export default {
  components: { RichText },
  data() {
    return {
      content: "",
    };
  },
  methods: {
    editorChange: function (html) {
      this.content = html;
    },
  },
};
</script>