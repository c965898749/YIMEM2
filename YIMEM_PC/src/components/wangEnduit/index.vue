<template>
  <div id="editor" class="editor"></div>
</template>

<script>
  import E from 'wangeditor'
  import {BASE_URL} from '@/services/api'
  export default {
    props: ['catchData'],
    data() {
      return {
        editor:null,
        info:null
      }
    },
    watch: {
      catchData: function(value) {
        if (value != this.editor.txt.html()) {
          this.editor.txt.html(value)
        }
      },
    },
    mounted() {
      this.$nextTick(function() {
        this.init()
      })
    },
    methods:{
      init(){
        const _self=this

        this.editor = new E('#editor')
        this.editor.config.height = 400
        this.editor.config.uploadImgServer = BASE_URL+'/admin/imgUpload'
        this.editor.config.uploadImgMaxSize = 2 * 1024 * 1024
        this.editor.config.uploadImgMaxLength = 5
        this.editor.config.uploadImgHeaders = {
          Authorization:'Bearer '+ localStorage.getItem('token')
        }
        this.editor.config.showLinkImg = false
        // this.editor.config.uploadFileName = 'files'
        this.editor.config.uploadImgTimeout = 10 * 1000

        this.setMenus()
        this.editor.config.onchangeTimeout = 500 // 修改为 500ms
        this.editor.config.onchange = (html) => {
          _self.$emit('geteditorcontent', html)
        }
        this.editor.create()
      },
      setMenus() {
        this.editor.config.menus = [
          'head',  // 标题
          'bold',  // 粗体
          'fontSize',  // 字号
          'fontName',  // 字体
          'italic',  // 斜体
          'underline',  // 下划线
          'strikeThrough',  // 删除线
          'foreColor',  // 文字颜色
          'backColor',  // 背景颜色
          'link',  // 插入链接
          'list',  // 列表
          'justify',  // 对齐方式
          'image',  // 插入图片
          'table',  // 表格
          'undo',  // 撤销
          'redo'  // 重复
        ]
      },
    }
  }
</script>

<style>

</style>
