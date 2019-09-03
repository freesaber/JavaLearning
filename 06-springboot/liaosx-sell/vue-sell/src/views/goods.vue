<template>
  <div class="goods">
    <!-- 菜单 -->
    <div class="menu-wrapper" ref="menuWrapper">
      <ul>
        <li v-for="(item, index) in goods"
            :key="item.name"
            class="menu-item"
            :class="{'current':index == currentIndex}"
            @click="selectMenu(index)">
          <span class="text border-1px">
            <span v-if="item.type > 0" class="icon" :class="typeClassMap[item.type].className">
            </span>
            {{item.name}}
          </span>
        </li>
      </ul>
    </div>
    <!-- 商品 -->
    <div class="foods-wrapper" ref="foodsWrapper">
      <ul>
        <li v-for="item in goods" :key="item.name" class="food-list food-list-hook">
          <h1 class="title">{{item.name}}</h1>
          <ul>
            <li @click="selectFood(food,$event)" v-for="food in item.foods" :key="food.name" class="food-item border-1px">
              <div class="icon">
                <img width="57" height="57" :src="food.icon">
              </div>
              <div class="content">
                <h2 class="name">{{food.name}}</h2>
                <p class="desc">{{food.description}}</p>
                <div class="extra">
                  <span class="count">月售{{food.sellCount}}份</span>
                  <span>好评率{{food.rating}}%</span>
                </div>
                <div class="price">
                  <span class="now">￥{{food.price}}</span>
                  <span v-show="food.oldPrice" class="old">￥{{food.oldPrice}}</span>
                </div>
                <div class="cartcontrol-wrapper">
                  <cartcontrol :food="food" @addCart="addCart"></cartcontrol>
                </div>
              </div>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <shopcart ref="shopcart" :select-foods="selectFoods" :delivery-price="seller.deliveryPrice" :min-price="seller.minPrice"></shopcart>
    <food ref="food" :food="selectedFood" @addCart="addCart"></food>
  </div>
</template>

<script>
import BScroll from '@better-scroll/core'
import shopcart from '../components/goods/shopcart'
import cartcontrol from '../components/common/cartcontrol'
import food from '../components/goods/food'

export default {
  name: 'goods',
  props: {
    seller: Object
  },
  components: {
    shopcart,
    cartcontrol,
    food
  },
  data () {
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
      goods: [],
      listHeight: [],
      scrollY: 0,
      selectedFood: {}
    }
  },
  created() {
    this.axios.get('/buyer/product/list').then((response) => {
      var res = response.data;
      if (res.code == 0) {
        this.goods = res.data;
        this.$nextTick(() => {
          this.initScroll();
          this.calculateHeight();
        });
      }
    })
  },
  computed: {
    // 根据滚动位置计获取位于商品分类的索引
    currentIndex() {
      for (let i = 0; i < this.listHeight.length; i++) {
        let height1 = this.listHeight[i];
        let height2 = this.listHeight[i + 1];
        if (!height2 || (this.scrollY >= height1 && this.scrollY < height2)) {
          return i;
        }
      }
      return 0;
    },
    // 选择的商品
    selectFoods() {
      let foods = [];
      this.goods.forEach(good => {
        var temp = good.foods.filter(food => {
          return food.count && food.count > 0;
        });
        foods.push(...temp);
      });
      return foods;
    }
  },
  watch: {

  },
  mounted() {
  },
  methods: {
    // 初始化滚动
    initScroll() {
      this.menuScroll = new BScroll(this.$refs.menuWrapper, {
        click: true
      });
      this.foodsScroll = new BScroll(this.$refs.foodsWrapper, {
        probeType: 3,
        click: true
      });
      this.foodsScroll.on('scroll', (pos) => {
        this.scrollY = Math.abs(Math.round(pos.y));// 实时获取滚动位置
      })
    },
    // 计算商品每个分类的高度区间
    calculateHeight() {
      let foodList = this.$refs.foodsWrapper.getElementsByClassName('food-list-hook');
      let height = 0;
      this.listHeight.push(height);
      for (let i = 0; i < foodList.length; i++) {
        let item = foodList[i];
        height += item.clientHeight;
        this.listHeight.push(height);
      }
    },
    // 点击菜单，定位商品dom
    selectMenu(index) {
      let foodList = this.$refs.foodsWrapper.getElementsByClassName('food-list-hook');
      let el = foodList[index];
      this.foodsScroll.scrollToElement(el, 300);
    },
    // 添加购物车事件
    addCart(target) {
      this.$refs.shopcart.drop(target);
    },
    selectFood(food, event) {
      this.selectedFood = food;
      this.$refs.food.show();
    }
  }
}
</script>

<style lang="scss" scoped>
  @import "../assets/scss/mixins";

  .goods{
    display: flex;
    position: absolute;
    top: 177px;
    bottom: 46px;
    width: 100%;
    overflow: hidden;
  }

  // 菜单
  .menu-wrapper{
    flex: 0 0 80px;
    width: 80px;
    background: #f3f5f7;
    .menu-item{
      display: table;
      height: 54px;
      width: 56px;
      line-height: 14px;
      padding: 0 12px;
      &.current{
        position: relative;
        z-index: 10;
        margin-top: -1px;
        background: #fff;
        .text{
           @include border-none();
           font-weight: 700;
        }
      }
      .icon{
        display: inline-block;
        width: 12px;
        height: 12px;
        vertical-align: top;
        margin-right: 2px;
        background-size: 12px 12px;
        background-repeat: no-repeat;
        &.decrease{
          @include bg-image('../assets/img/decrease_3');
        }
        &.discount{
          @include bg-image('../assets/img/discount_3');
        }
        &.guarantee{
          @include bg-image('../assets/img/guarantee_3');
        }
        &.invoice{
          @include bg-image('../assets/img/invoice_3');
        }
        &.special{
          @include bg-image('../assets/img/special_3');
        }
      }
      .text{
        display: table-cell;
        width: 56px;
        vertical-align: middle;
        @include border-1px(rgba(7,17,27,0.1));
        font-size: 12px;
      }
    }
  }

  // 商品
  .foods-wrapper{
    flex: 1;
    .food-list{
      .title{
        padding-left: 14px;
        height: 26px;
        line-height: 26px;
        border-left: 2px solid #d9dde1;
        font-size: 12px;
        color: rgb(147, 153, 159);
        background: #f3f5f7;
      }
      .food-item{
        display: flex;
        margin: 18px;
        padding-bottom: 18px;
        @include border-1px(rgba(7,17,27,0.1));
        &:last-child{
          @include border-none();
          margin-bottom: 0;
        }
        .icon{
          flex: 0 0 57px;
          margin-right: 10px;
        }
        .content{
          flex: 1;
          position: relative;
          .name{
            margin: 2px 0 8px 0;
            height: 14px;
            line-height: 14px;
            font-size: 14px;
            color: rgb(7, 17, 27);
          }
          .desc, .extra{
            line-height: 10px;
            font-size: 10px;
            color: rgb(147, 153, 159)
          }
          .desc{
            line-height: 12px;
            margin-bottom: 8px;
          }
          .extra{
            .count{
              margin-right: 12px;
            }
          }
          .price{
            line-height: 24px;
            .now{
              font-weight: 700;
              margin-right: 8px;
              font-size: 14px;
              color: rgb(240, 20, 20);
            }
            .old{
              font-weight: 700;
              text-decoration: line-through;
              font-size: 10px;
              color: rgb(147, 153, 159);
            }
          }
          .cartcontrol-wrapper{
            position: absolute;
            right: 0;
            bottom: 12px;
          }
        }
      }
    }
  }
</style>
