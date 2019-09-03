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
    })
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
