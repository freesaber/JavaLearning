<template>
  <div id="app">
    <v-header :seller="seller"></v-header>
    <div class="tab">
      <div class="tab-item">
        <router-link to="goods">商品</router-link>
      </div>
      <div class="tab-item">
        <router-link to="ratings">评论</router-link>
      </div>
      <div class="tab-item">
        <router-link to="seller">商家</router-link>
      </div>
    </div>
    <keep-alive>
      <router-view :seller="seller"></router-view>
    </keep-alive>
  </div>
</template>

<script>
import header from './components/header/header'
import { urlParse } from './assets/js/util'
import { saveToLocal, loadFromLocal } from './assets/js/store'

export default {
  name: 'app',
  components: {
    'v-header': header
  },
  data() {
    return {
      seller: {
        id: (() => {
          let queryParam = urlParse();
          return queryParam.id;
        })()
      }
    }
  },
  created() {
    this.axios.get('/api/seller.json').then((response) => {
      var res = response.data;
      if (res.code == 0) {
        this.seller = Object.assign({}, this.seller, res.data);
      }
    });

    // 判断是否存在用户openid,如果不存在
    var openid = loadFromLocal('user', 'openid', null);
    if(!openid){
      // 如果url中可以获取
      let queryParam = urlParse();
      if(queryParam.openid){
        saveToLocal('user', 'openid', queryParam.openid);
      }else{
        // 测试
        // window.location.href="http://freesaber.natapp1.cc/wechat/authorize?returnUrl=http://freesaber.natapp1.cc";
        // 发布
        window.location.href="http://liaosxsell.freesaber.cn/wechat/authorize?returnUrl=http://liaosxsell.freesaber.cn";
      }
    }
  },
  computed: {

  },
  watch: {

  },
  mounted() {
    // 首页载入完成后，加载goods路由
    this.$router.push('/goods');
  },
  methods: {

  }
}
</script>

<style lang="scss" scoped>
  @import "./assets/scss/mixins";

  .tab{
    display: flex;
    height: 40px;
    line-height: 40px;
    @include border-1px(rgba(7,17,27,0.1));
    .tab-item{
      flex: 1;
      text-align: center;
      a{
        display: block;
        font-size: 14px;
        color: rgb(77, 85, 93);
        &.router-link-active {
          color: rgb(240, 20, 20);
        }
      }
    }
  }
</style>
