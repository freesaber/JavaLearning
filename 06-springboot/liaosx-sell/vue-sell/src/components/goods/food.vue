<template>
  <transition name="move">
    <div v-show="showFlag" class="food" ref="food">
      <div class="food-content">
        <div class="image-header">
          <img :src="food.image">
          <div class="back" @click="hide">
            <i class="icon-arrow_lift"></i>
          </div>
        </div>
        <div class="content">
          <h1 class="title">{{food.name}}</h1>
          <div class="detail">
            <span class="sell-count">月售{{food.sellCount}}份</span>
            <span class="rating">好评率{{food.rating}}%</span>
          </div>
          <div class="price">
            <span class="now">￥{{food.price}}</span>
            <span class="old" v-show="food.oldPrice">￥{{food.oldPrice}}</span>
          </div>
          <div class="cartcontrol-wrapper">
            <cartcontrol @addCart="addCart" :food="food" v-show="food.count"></cartcontrol>
          </div>
          <div @click.stop.prevent="addFirst($event)" class="buy" v-show="!food.count || food.count === 0">加入购物车</div>
        </div>
        <split v-show="food.info"></split>
        <div class="info" v-show="food.info">
          <h1 class="title">商品信息</h1>
          <p class="text">{{food.info}}</p>
        </div>
        <split></split>
        <div class="rating">
          <h1 class="title">商品评价</h1>
          <ratingselect
            :select-type="selectType"
            :only-content="onlyContent"
            :desc="desc"
            :ratings="food.ratings"
            @ratingTypeChange="ratingTypeChange"
            @toggleContent="toggleContent"
          ></ratingselect>
        </div>
        <div class="rating-wrapper">
          <ul v-if="food.ratings && food.ratings.length">
            <li v-show="needShow(rating)" v-for="rating in food.ratings" :key="rating.rateTime" class="rating-item border-1px">
              <div class="user">
                <span class="name">{{rating.username}}</span>
                <img class="avatar" width="12" height="12" :src="rating.avatar">
              </div>
              <div class="time">
                {{formatRateTime(rating.rateTime)}}
              </div>
              <p class="text">
                <span class="icon-thumb_up" v-if="rating.rateType === 0"></span>
                <span class="icon-thumb_down" v-else></span>
                {{rating.text}}
              </p>
            </li>
          </ul>
          <div class="no-rating" v-else>暂无评价</div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import BScroll from '@better-scroll/core'
import cartcontrol from '../common/cartcontrol'
import split from '../common/split'
import ratingselect from '../common/ratingselect'
import { formatDate } from '../../assets/js/date'

const POSITIVE = 0; // 正面评价
const NEGATIVE = 1; // 负面评价
const ALL = 2; // 所有

export default {
  name: 'food',
  props: {
    food: {
      type: Object
    }
  },
  components: {
    cartcontrol,
    split,
    ratingselect
  },
  data() {
    return {
      showFlag: false,
      selectType: ALL,
      onlyContent: false,
      desc: {
        all: '全部',
        positive: '推荐',
        negative: '吐槽'
      }
    }
  },
  created() {

  },
  computed: {

  },
  watch: {

  },
  mounted() {

  },
  methods: {
    show() {
      this.showFlag = true;

      // 重置
      this.selectType = ALL;
      this.onlyContent = false;

      // 滚动
      this.$nextTick(() => {
        if (!this.scroll) {
          this.scroll = new BScroll(this.$refs.food, {
            click: true
          });
        } else {
          this.scroll.refresh();
        }
      });
    },
    hide() {
      this.showFlag = false;
    },
    addFirst(event) {
      this.$set(this.food, 'count', 1);
      this.$emit('addCart', event.target);
    },
    addCart(target) {
      this.$emit('addCart', target);
    },
    ratingTypeChange(type) {
      this.selectType = type;
      this.$nextTick(() => {
        this.scroll.refresh();
      })
    },
    toggleContent() {
      this.onlyContent = !this.onlyContent;
      this.$nextTick(() => {
        this.scroll.refresh();
      })
    },
    needShow(rating) {
      if (this.onlyContent) {
        if (this.selectType == ALL) {
          return rating.text;
        }
        return rating.rateType == this.selectType && rating.text;
      } else {
        if (this.selectType == ALL) {
          return true;
        }
        return rating.rateType == this.selectType;
      }
    },
    formatRateTime(rateTime) {
      var time = new Date(rateTime);
      return formatDate(time, 'yyyy-MM-dd hh-mm');
    }
  }
}
</script>

<style lang="scss" scoped>
  @import "../../assets/scss/mixins";

  .food{
    position: fixed;
    left: 0;
    top: 0;
    bottom: 48px;
    z-index: 30;
    width: 100%;
    background: #fff;
    &.move-enter-active, &.move-leave-active{
      transition: all .2s linear;
    }
    &.move-enter, &.move-leave-to{
      transform: translate3d(100%, 0, 0);
    }
    &.move-enter-to, &.move-leave{
      transform: translate3d(0, 0, 0);
    }

    .food-content{
      .image-header{
        position: relative;
        width: 100%;
        height: 0;
        padding-top: 100%;
        img{
          position: absolute;
          top: 0;
          left: 0;
          width: 100%;
          height: 100%;
        }
        .back{
          position: absolute;
          top: 10px;
          left: 0;
          .icon-arrow_lift{
            display: block;
            padding: 10px;
            font-size: 20px;
            color: #fff;
          }
        }
      }

      .content{
        position: relative;
        padding: 18px;
        .title{
          line-height: 14px;
          margin-bottom: 8px;
          font-size: 14px;
          font-weight: 700;
          color: rgb(7, 17, 27);
        }
        .detail{
          margin-bottom: 18px;
          line-height: 10px;
          font-size: 0;
          height: 10px;
          .sell-count, .rating{
            font-size: 10px;
            color: rgb(147, 153, 159);
          }
          .sell-count{
            margin-right: 12px;
          }
        }
        .price{
          font-weight: 700;
          line-height: 24px;
          .now{
            margin-right: 8px;
            font-size: 14px;
            color: rgb(240, 20, 20);
          }
          .old{
            text-decoration: line-through;
            font-size: 10px;
            color: rgb(147, 153, 159);
          }
        }
        .cartcontrol-wrapper{
          position: absolute;
          right: 12px;
          bottom: 12px;
        }
        .buy{
          position: absolute;
          right: 18px;
          bottom: 18px;
          z-index: 10;
          height: 24px;
          line-height: 24px;
          padding: 0 12px;
          box-sizing: border-box;
          border-radius: 12px;
          font-size: 10px;
          color: #fff;
          background: rgb(0, 160, 220);
        }
      }

      .info{
        padding: 18px;
        .title{
          line-height: 14px;
          margin-bottom: 6px;
          font-size: 14px;
          color: rgb(7, 17, 27);
        }
        .text{
          line-height: 24px;
          padding: 0 8px;
          font-size: 12px;
          color: rgb(77, 85, 93);
        }
      }

      .rating{
        padding-top: 18px;
        .title{
          line-height: 14px;
          margin-left: 18px;
          font-size: 14px;
          color: rgb(7, 17, 27);
        }
      }

      .rating-wrapper{
        padding: 0 18px;
        .rating-item{
          position: relative;
          padding: 16px 0;
          @include border-1px(rgba(7, 17, 27, 0.1));
          .user{
            position: absolute;
            right: 0;
            top: 16px;
            font-size: 0;
            line-height: 12px;
            .name{
              display: inline-block;
              vertical-align: top;
              margin-right: 6px;
              font-size: 10px;
              color: rgb(147, 153, 157);
            }
            .avatar{
              display: inline-block;
              vertical-align: top;
              border-radius: 50%;
            }
          }
          .time{
            margin-bottom: 6px;
            line-height: 12px;
            font-size: 10px;
            color: rgb(147, 153, 159);
          }
          .text{
            line-height: 16px;
            font-size: 12px;
            color: rgb(7, 17, 27);
            .icon-thumb_up, .icon-thumb_down{
              margin-right: 4px;
              line-height: 16px;
              font-size: 12px;
            }
            .icon-thumb_up{
              color: rgb(0, 160, 220);
            }
            .icon-thumb_down{
              color: rgb(147, 153, 159);
            }
          }
        }
        .no-rating{
          padding: 16px 0;
          font-size: 12px;
          color: rgb(147, 153, 159);
        }
      }
    }
  }
</style>
