<template>
  <div class="cartcontrol">
    <transition name="slide-fade">
      <div class="cart-decrease" v-if="food.count > 0" @click.stop.prevent="decreaseCart">
          <i class="inner icon-remove_circle_outline"></i>
      </div>
    </transition>
    <div class="cart-count" v-show="food.count > 0">{{food.count}}</div>
    <div class="cart-add icon-add_circle" @click.stop.prevent="addCart"></div>
  </div>
</template>

<script>
export default {
  name: 'cartcontrol',
  props: {
    food: {
      type: Object
    }
  },
  components: {

  },
  data() {
    return {

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
    addCart(event) {
      if (!this.food.count) {
        this.$set(this.food, 'count', 1);
      } else {
        this.food.count++;
      }
      this.$emit('addCart', event.target);
    },
    decreaseCart() {
      if (this.food.count > 0) {
        this.food.count--;
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .cartcontrol{
    font-size: 0;
    .cart-decrease{
      display: inline-block;
      vertical-align: top;
      padding: 6px;
      &.slide-fade-enter-active, &.slide-fade-leave-active{
        transition: all .4s linear;
      }
      &.slide-fade-enter, &.slide-fade-leave-to{
        opacity: 0;
        transform: translate3d(24px, 0, 0);//动画开始，水平反向移动24px
      }
      &.slide-fade-enter-to, &.slide-fade-leave{
        opacity: 1;
        transform: translate3d(0, 0, 0);
      }
      .inner{
        display: inline-block;
        font-size: 24px;
        line-height: 24px;
        color: rgb(0, 160, 220);
      }
    }
    .cart-count{
      display: inline-block;
      vertical-align: top;
      width: 12px;
      padding-top: 6px;
      line-height: 24px;
      text-align: center;
      font-size: 10px;
      color: rgb(147, 153, 159)
    }
    .cart-add{
      display: inline-block;
      vertical-align: top;
      padding: 6px;
      font-size: 24px;
      line-height: 24px;
      color: rgb(0, 160, 220);
    }
  }
</style>
