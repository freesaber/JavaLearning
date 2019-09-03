<template>
  <div>
    <div class="shopcart">
      <div class="content">
        <div class="content-left" @click="toggleList">
          <div class="logo-wrapper">
            <div class="logo" :class="{highlight: totalCount > 0}">
              <i class="icon-shopping_cart" :class="{highlight: totalCount > 0}"></i>
            </div>
            <div class="num" v-show="totalCount > 0">{{totalCount}}</div>
          </div>
          <div class="price" :class="{highlight: totalPrice > 0}">￥{{totalPrice}}</div>
          <div class="desc">另需配送费￥{{deliveryPrice}}元</div>
        </div>
        <div class="content-right" @click="pay">
          <div class="pay" :class="payClass">{{payDesc}}</div>
        </div>
        <div class="ball-container">
          <transition-group
            name="drop"
            tag="div"
            v-on:before-enter="beforeEnter"
            v-on:enter="enter"
            v-on:after-enter="afterEnter"
          >
            <div v-for="ball in dropBalls" :key="ball.index" :data-index="ball.index" class="ball">
              <div class="inner"></div>
            </div>
          </transition-group>
        </div>
        <transition name="fold">
          <div class="shopcart-list" v-show="listShow">
            <div class="list-header">
              <h1 class="title">购物车</h1>
              <span class="empty" @click="empty">清空</span>
            </div>
            <div class="list-content" ref="listContent">
              <ul>
                <li class="food" v-for="food in selectFoods" :key="food.name">
                  <span class="name">{{food.name}}</span>
                  <div class="price">
                    <span>￥{{food.price*food.count}}</span>
                  </div>
                  <div class="cartcontrol-wrapper">
                    <cartcontrol @addCart="drop" :food="food"></cartcontrol>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </transition>
      </div>
    </div>
    <transition name="fade">
      <div class="list-mask" @click="hideList" v-if="listShow">
      </div>
    </transition>
  </div>
</template>

<script>
import Velocity from 'velocity-animate'
import cartcontrol from '../common/cartcontrol'
import BScroll from '@better-scroll/core'

export default {
  name: 'shopcart',
  props: {
    selectFoods: {
      type: Array,
      default() {
        return [];
      }
    },
    deliveryPrice: {
      type: Number,
      default: 0
    },
    minPrice: {
      type: Number,
      default: 0
    }
  },
  components: {
    cartcontrol
  },
  data() {
    return {
      balls: [],
      fold: true // 是否折叠购物车列表
    }
  },
  created() {

  },
  computed: {
    // 计算商品总价格
    totalPrice() {
      let total = 0;
      this.selectFoods.forEach(food => {
        total += food.price * food.count;
      });
      return total;
    },
    // 商品总个数
    totalCount() {
      let count = 0;
      this.selectFoods.forEach(food => {
        count += food.count;
      });
      return count;
    },
    // 起送描述
    payDesc() {
      if (this.totalPrice === 0) {
        return `￥${this.minPrice}元起送`;
      } else if (this.totalPrice < this.minPrice) {
        return `还差￥${this.minPrice - this.totalPrice}元起送`;
      } else {
        return '去结算';
      }
    },
    payClass() {
      if (this.totalPrice >= this.minPrice) {
        return 'enough';
      } else {
        return 'not-enough';
      }
    },
    dropBalls() {
      return this.balls.filter(ball => {
        return ball.show == true;
      });
    },
    // 显示购物车列表
    listShow() {
      if (this.totalCount > 0) {
        let show = !this.fold;
        // 在购物车列表显示状态下，如果改变的数量，刷新滚动组件
        if (show) {
          this.$nextTick(() => {
            this.scroll.refresh();
          });
        }
        return show;
      }
      return false;
    }
  },
  watch: {
    totalCount (val) {
      // 改变数量时候，进行滚动组件的创建
      if (!this.scroll) {
        this.scroll = new BScroll(this.$refs.listContent, {
          click: true
        });
      }
      // 在删除掉所有商品后，防止添加物品到购物车，购物车列表显示出来比较突兀
      if (val == 0) {
        this.fold = true;
      }
    }
  },
  mounted() {

  },
  methods: {
    drop(el) {
      var ball = {
        index: this.balls.length,
        el: el, // 点击的“加号”的元素
        show: true
      }
      this.balls.push(ball);
    },
    beforeEnter: function (el) {
      // 设置球的起始位置
      let ball = this.balls[el.dataset.index];
      let rect = ball.el.getBoundingClientRect();
      el.style.left = (rect.left + 10) + 'px';
      el.style.top = (rect.top + 10) + 'px';
    },
    enter: function (el, done) {
      // 计算和终点位置的差
      let ball = this.balls[el.dataset.index];
      let rect = ball.el.getBoundingClientRect();
      let translateX = -(rect.left - 25) + 'px';
      let translateY = (window.innerHeight - rect.top - 52) + 'px';
      Velocity(el,
        { translateY: translateY },
        { easing: [0.49, -0.29, 0.75, 0.41], queue: false }
      );
      Velocity(el,
        { translateX: translateX },
        { duration: 400, easing: 'easeInSine', complete: done }
      );
    },
    afterEnter: function (el) {
      let ball = this.balls[el.dataset.index];
      ball.show = false;
    },
    // 切换购物车列表的显示状态
    toggleList() {
      if (this.totalCount > 0) {
        this.fold = !this.fold;
      }
    },
    // 清空购物车
    empty() {
      this.selectFoods.forEach((food) => {
        food.count = 0;
      });
    },
    // 点击遮罩，隐藏列表
    hideList() {
      this.fold = true;
    },
    // 订单结算
    pay() {
      if (this.totalPrice < this.minPrice) {
        return;
      }
      window.alert(`支付${this.totalPrice}元`);
    }
  }
}
</script>

<style lang="scss" scoped>
  @import "../../assets/scss/mixins";

  .shopcart{
    position: fixed;
    left: 0;
    bottom: 0;
    z-index: 50;
    width: 100%;
    height: 48px;
    .content{
      display: flex;
      position: relative;
      background: #141d27;
      font-size: 0;
      color: rgba(255, 255, 255, 0.4);
    }
  }
  .content-left{
    flex: 1;
    .logo-wrapper{
      display: inline-block;
      vertical-align: top;
      position: relative;
      top: -10px;
      margin: 0 12px;
      padding: 6px;
      width: 56px;
      height: 56px;
      box-sizing: border-box;
      border-radius: 50%;
      background: #141d27;
      .logo{
        width: 100%;
        height: 100%;
        font-size: 16px;
        border-radius: 50%;
        text-align: center;
        background: #2b343c;
        &.highlight{
          background: rgb(0, 160, 220);
          cursor: pointer;
        }
        .icon-shopping_cart{
          line-height: 44px;
          font-size: 24px;
          color: #80858a;
          &.highlight{
            color: #fff;
          }
        }
      }
      .num{
        position: absolute;
        top: 0;
        right: 0;
        width: 24px;
        height: 16px;
        line-height: 16px;
        text-align: center;
        border-radius: 16px;
        font-size: 9px;
        font-weight: 700;
        color: #fff;
        background: rgb(240, 20, 20);
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.4);
      }
    }
    .price{
      display: inline-block;
      vertical-align: top;
      margin-top: 12px;
      line-height: 24px;
      padding-right: 12px;
      box-sizing: border-box;
      border-right: 1px solid rgba(255, 255, 255, 0.1);
      font-size: 16px;
      font-weight: 700;
      &.highlight{
        color: #fff;
      }
    }
    .desc{
      display: inline-block;
      vertical-align: top;
      margin:12px 0 0 12px;
      line-height: 24px;
      font-size: 10px;
    }
  }
  .content-right{
    flex: 0 0 105px;
    width: 105px;
    .pay{
      height: 48px;
      line-height: 48px;
      text-align: center;
      font-size: 12px;
      font-weight: 700;
      background: #2b333b;
      &.not-enough{
        background: #2b333b;
      }
      &.enough{
        background: #00b43c;
        color: #fff;
        cursor: pointer;
      }
    }
  }
  .ball-container{
    .ball{
      position: fixed;
      z-index: 200;
      .inner{
        width: 16px;
        height: 16px;
        border-radius: 50%;
        background: rgb(0, 160, 220);
      }
    }
  }
  .shopcart-list{
    position: absolute;
    left: 0;
    top: 0;
    z-index: -1;
    width: 100%;
    transform: translate3d(0, -100%, 0);
    &.fold-enter-active, &.fold-leave-active{
      transition: all .5s linear;
    }
    &.fold-enter, &.fold-leave-to{
      transform: translate3d(0, 0, 0);
    }
    &.fold-enter-to, &.fold-leave{
      transform: translate3d(0, -100%, 0);
    }
    .list-header{
      height: 40px;
      line-height: 40px;
      padding: 0 18px;
      background: #f3f5f7;
      border-bottom: 1px solid rgba(7, 17, 27, 0.1);
      .title{
        float: left;
        font-size: 14px;
        color: rgb(7, 17, 27);
      }
      .empty{
        float: right;
        font-size: 12px;
        color: rgb(0, 160, 220);
      }
    }
    .list-content{
      padding: 0 18px;
      max-height: 217px;
      overflow: hidden;
      background: #fff;
      .food{
        position: relative;
        padding: 12px 0;
        box-sizing: border-box;
        @include border-1px(rgba(7,17,27,0.1));
        .name{
          font-size: 14px;
          line-height: 24px;
          color: rgb(7, 17, 27);
        }
        .price{
          position: absolute;
          right: 90px;
          bottom: 12px;
          font-size: 14px;
          font-weight: 700;
          line-height: 24px;
          color: rgb(240, 20, 20);
        }
        .cartcontrol-wrapper{
          position: absolute;
          right: 0;
          bottom: 6px;
        }
      }
    }
  }

  .list-mask{
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 40;
    backdrop-filter: blur(10px);
    background: rgba(7, 17, 27, 0.6);
    &.fade-enter-active, &.fade-leave-active{
      transition: all .5s linear;
    }
    &.fade-enter, &.fade-leave-to{
      opacity: 0;
      background: rgba(7, 17, 27, 0); // css的权重大于上面的0.6，动画开始时全透明
    }
    &.fade-enter-to, &.fade-leave{
      opacity: 1;
      background: rgba(7, 17, 27, 0.6);
    }
  }
</style>
