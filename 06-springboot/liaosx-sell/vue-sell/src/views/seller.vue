<template>
  <div class="seller" ref="seller">
    <div class="seller-content">
      <div class="overview">
        <h1 class="title">{{seller.name}}</h1>
        <div class="desc border-1px">
          <star :size="36" :score="seller.score"></star>
          <span class="text">({{seller.ratingCount}})</span>
          <span class="text">月售{{seller.sellCount}}单</span>
        </div>
        <ul class="remark">
          <li class="block">
            <h2>起送价</h2>
            <div class="content">
              <span class="stress">{{seller.minPrice}}</span>元
            </div>
          </li>
          <li class="block">
            <h2>商家配送</h2>
            <div class="content">
              <span class="stress">{{seller.deliveryPrice}}</span>元
            </div>
          </li>
          <li class="block">
            <h2>平均配送时间</h2>
            <div class="content">
              <span class="stress">{{seller.deliveryTime}}</span>分钟
            </div>
          </li>
        </ul>
        <div class="favorite" @click="toggleFavorite">
          <span class="icon-favorite" :class="{'active':favorite}"></span>
          <span class="text">{{favoriteText}}</span>
        </div>
      </div>
      <split></split>
      <div class="bulletin">
        <h1 class="title">公告与活动</h1>
        <div class="content-wrapper border-1px">
          <p class="content">{{seller.bulletin}}</p>
        </div>
        <ul v-if="seller.supports" class="supports">
          <li v-for="item in seller.supports"
            :key="item.description"
            class="support-item border-1px"
          >
            <span class="icon" :class="typeClassMap[item.type].className"></span>
            <span class="text">{{item.description}}</span>
          </li>
        </ul>
      </div>
      <split></split>
      <div class="pics">
        <h1 class="title">商家实景</h1>
        <div class="pic-wrapper" ref="picWrapper">
          <ul class="pic-list" :style="{'width': ulWidth+'px'}">
            <li class="pic-item" v-for="pic in seller.pics" :key="pic">
              <img :src="pic" width="120" height="90">
            </li>
          </ul>
        </div>
      </div>
      <split></split>
      <div class="info">
        <h1 class="title border-1px">商家信息</h1>
        <ul>
          <li class="info-item" v-for="info in seller.infos" :key="info">{{info}}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import BScroll from '@better-scroll/core'
import star from '../components/common/star'
import split from '../components/common/split'
import { saveToLocal, loadFromLocal } from '../assets/js/store'

export default {
  name: 'seller',
  props: {
    seller: {
      type: Object
    }
  },
  components: {
    star,
    split
  },
  data() {
    return {
      typeClassMap: {
        '0': {
          className: 'decrease',
          classDesc: '满减'
        },
        '1': {
          className: 'discount',
          classDesc: '折扣'
        },
        '2': {
          className: 'special',
          classDesc: '特价'
        },
        '3': {
          className: 'invoice',
          classDesc: '发票'
        },
        '4': {
          className: 'guarantee',
          classDesc: '安全保障'
        }
      },
      ulWidth: 0,
      favorite: false
    }
  },
  created() {

  },
  computed: {
    favoriteText() {
      return this.favorite ? '已收藏' : '收藏';
    }
  },
  watch: {

  },
  mounted() {
    this.initScroll();
    this.initPicScroll();
    this.favorite = loadFromLocal(this.seller.id, 'favorite', false);
  },
  methods: {
    initScroll() {
      if (!this.scroll) {
        this.scroll = new BScroll(this.$refs.seller, {
          click: true
        });
      } else {
        this.scroll.refresh();
      }
    },
    initPicScroll() {
      if (!this.seller.pics) {
        return;
      }

      // 根据图片数量计算ul，撑开ul宽度
      let picWidth = 120;
      let margin = 6;
      this.ulWidth = (picWidth + margin) * this.seller.pics.length - margin;
      this.$nextTick(() => {
        if (!this.picScroll) {
          this.picScroll = new BScroll(this.$refs.picWrapper, {
            scrollX: true,
            eventPassthrough: 'vertical'
          });
        } else {
          this.picScroll.refresh();
        }
      });
    },
    toggleFavorite() {
      this.favorite = !this.favorite;
      saveToLocal(this.seller.id, 'favorite', this.favorite);
    }
  }
}
</script>

<style lang="scss" scoped>
  @import "../assets/scss/mixins";

  .seller{
    position: absolute;
    top: 174px;
    bottom: 0;
    left: 0;
    width: 100%;
    overflow: hidden;
  }

  .overview{
    position: relative;
    padding: 18px;
    .title{
      margin-bottom: 8px;
      line-height: 14px;
      color: rgb(7, 17, 27);
      font-size: 14px;
    }
    .desc{
      padding-bottom: 18px;
      @include border-1px(rgba(7,17,27,0.1));
      font-size: 0;
      .star{
        margin-right: 8px;
        display: inline-block;
        vertical-align: top;
      }
      .text{
        margin-right: 12px;
        display: inline-block;
        vertical-align: top;
        line-height: 18px;
        font-size: 10px;
        color: rgb(77, 85, 93);
      }
    }
    .remark{
      display: flex;
      padding-top: 18px;
      .block{
        flex: 1;
        text-align: center;
        border-right: 1px solid rgba(7, 17, 27, 0.1);
        &:last-child{
          border: none;
        }
        h2{
          margin-bottom: 4px;
          line-height: 10px;
          font-size: 10px;
          color: rgb(147, 153, 159);
        }
        .content{
          line-height: 24px;
          font-size: 10px;
          color: rgb(7, 17, 27);
          .stress{
            font-size: 24px;
          }
        }
      }
    }
    .favorite{
      position: absolute;
      width: 50px;
      right: 11px;
      top: 18px;
      text-align: center;
      .icon-favorite{
        display: block;
        margin-bottom: 4px;
        line-height: 24px;
        font-size: 24px;
        color: #d4d6d9;
        &.active{
          color: rgb(240, 20, 20);
        }
      }
      .text{
        display: block;
        line-height: 10px;
        font-size: 10px;
        color: rgb(77, 85, 93);
      }
    }
  }

  .bulletin{
    padding: 18px 18px 0 18px;
    .title{
      margin-bottom: 8px;
      line-height: 14px;
      color: rgb(7, 17, 27);
      font-size: 14px;
    }
    .content-wrapper{
      padding: 0 12px 16px 12px;
      @include border-1px(rgba(7,17,27,0.1));
      .content{
        line-height: 24px;
        font-size: 12px;
        color: rgb(240, 20, 20);
      }
    }
    .supports{
      .support-item{
        padding: 16px 12px;
        @include border-1px(rgba(7,17,27,0.1));
        font-size: 0;
        &:last-child{
          @include border-none();
        }
        .icon{
          display: inline-block;
          vertical-align: top;
          margin-right: 6px;
          width: 16px;
          height: 16px;
          background-size: 16px 16px;
          background-repeat: no-repeat;
          &.decrease{
            @include bg-image('../assets/img/decrease_4');
          }
          &.discount{
            @include bg-image('../assets/img/discount_4');
          }
          &.guarantee{
            @include bg-image('../assets/img/guarantee_4');
          }
          &.invoice{
            @include bg-image('../assets/img/invoice_4');
          }
          &.special{
            @include bg-image('../assets/img/special_4');
          }
        }
        .text{
          line-height: 16px;
          font-size: 12px;
          color: rgb(7, 17, 27);
        }
      }
    }
  }

  .pics{
    padding: 18px;
    .title{
      margin-bottom: 12px;
      line-height: 14px;
      color: rgb(7, 17, 27);
      font-size: 14px;
    }
    .pic-wrapper{
      width: 100%;
      overflow: hidden;
      white-space: nowrap;
      .pic-list{
        font-size: 0;
        .pic-item{
          display: inline-block;
          margin-right: 6px;
          width: 120px;
          height: 90px;
          &:last-child{
            margin-right: 0;
          }
        }
      }
    }
  }

  .info{
    padding: 18px 18px 0 18px;
    .title{
      padding-bottom: 12px;
      line-height: 14px;
      @include border-1px(rgba(7,17,27,0.1));
      color: rgb(7, 17, 27);
      font-size: 14px;
    }
    .info-item{
      padding: 16px 12px;
      line-height: 16px;
      @include border-1px(rgba(7,17,27,0.1));
      color: rgb(7, 17, 27);
      font-size: 12px;
      &:last-child{
        @include border-none();
      }
    }
  }
</style>
