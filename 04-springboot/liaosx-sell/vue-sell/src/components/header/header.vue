<template>
  <div class="header">
    <!-- 主体 -->
    <div class="content-wrapper">
      <!-- 头像 -->
      <div class="avatar">
        <img width="64" height="64" :src="seller.avatar">
      </div>
      <!-- 内容 -->
      <div class="content">
        <div class="title">
          <span class="brand"></span>
          <span class="name">{{seller.name}}</span>
        </div>
        <div class="description">
          {{seller.description}}/{{seller.deliveryTime}}分钟送达
        </div>
        <div v-if="seller.supports && seller.supports.length > 0" class="supports">
          <span class="icon" :class="typeClassMap[seller.supports[0].type].className"></span>
          <span class="text">{{seller.supports[0].description}}</span>
        </div>
      </div>
      <!-- 展开优惠详情 -->
      <div v-if="seller.supports && seller.supports.length > 0" class="supports-count" @click="showDetail">
        <span class="count">{{seller.supports.length}}个</span>
        <i class="icon-keyboard_arrow_right"></i>
      </div>
    </div>
    <!-- 公告 -->
    <div class="bulletin-wrapper" @click="showDetail">
      <span class="bulletin-title"></span>
      <span class="bulletin-text">{{seller.bulletin}}</span>
      <i class="icon-keyboard_arrow_right"></i>
    </div>
    <!-- 背景 -->
    <div class="background">
      <img :src="seller.avatar" width="100%" height="100%">
    </div>
    <!-- 详情 -->
    <transition name="fade">
      <div v-if="detailShow" class="detail">
        <div class="detail-wrapper clearfix">
          <div class="detail-main">
            <h1 class="name">{{seller.name}}</h1>
            <div class="star-wrapper">
              <star :size="48" :score="seller.score"></star>
            </div>
            <div class="title">
              <div class="line"></div>
              <div class="text">优惠信息</div>
              <div class="line"></div>
            </div>
            <ul v-if="seller.supports" class="supports">
              <li v-for="item in seller.supports"
                :key="item.description"
                class="support-item"
              >
                <span class="icon" :class="typeClassMap[item.type].className"></span>
                <span class="text">{{item.description}}</span>
              </li>
            </ul>
            <div class="title">
              <div class="line"></div>
              <div class="text">商家公告</div>
              <div class="line"></div>
            </div>
            <div class="bulletin">
              <p class="content">{{seller.bulletin}}</p>
            </div>
          </div>
        </div>
        <div class="detail-close">
          <i class="icon-close" @click="hideDetail"></i>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
import star from '../common/star'

export default {
  name: 'v-header',
  components: {
    star
  },
  props: {
    seller: {
      type: Object
    }
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
      detailShow: false
    }
  },
  methods: {
    showDetail() {
      this.detailShow = true;
    },
    hideDetail() {
      this.detailShow = false;
    }
  }
}
</script>

<style lang="scss" scoped>
  @import "../../assets/scss/mixins";

  // 头部整体样式
  .header{
    position: relative;
    overflow: hidden;
    color: #fff;
    background: rgba(7, 17, 27, 0.5)
  }

  // 头部主体
  .content-wrapper{
    display: flex;
    position: relative;
    padding: 24px 12px 18px 24px;
    // 店铺头像
    .avatar{
      width: 64px;
      img{
        border-radius: 2px;
      }
    }
    // 店铺内容详情
    .content{
      flex: 1;
      margin-left: 16px;
      .title{
        margin: 2px 0 8px 0;
        .brand{
          display: inline-block;
          vertical-align: top;
          width: 30px;
          height: 18px;
          @include bg-image('../../assets/img/brand');
          background-size: 30px 18px;
          background-repeat: no-repeat;
        }
        .name{
          margin-left: 6px;
          font-size: 16px;
          line-height: 18px;
          font-weight: bold;
        }
      }
      .description{
        margin-bottom: 8px;
        line-height: 12px;
        font-size: 12px;
      }
      .supports{
        .icon{
          display: inline-block;
          vertical-align: middle;
          width: 12px;
          height: 12px;
          margin-right: 4px;
          background-size: 12px 12px;
          background-repeat: no-repeat;
          &.decrease{
            @include bg-image('../../assets/img/decrease_1');
          }
          &.discount{
            @include bg-image('../../assets/img/discount_1');
          }
          &.guarantee{
            @include bg-image('../../assets/img/guarantee_1');
          }
          &.invoice{
            @include bg-image('../../assets/img/invoice_1');
          }
          &.special{
            @include bg-image('../../assets/img/special_1');
          }
        }
        .text{
          line-height: 12px;
          font-size: 10px;
        }
      }
    }
    // 优惠详情
    .supports-count{
      position: absolute;
      right: 12px;
      bottom: 18px;
      padding: 0 6px 0 10px;
      height: 24px;
      line-height: 24px;
      border-radius: 14px;
      background-color: rgba(0, 0, 0, 0.2);
      text-align: center;
      .count{
        font-size: 10px;
      }
      .icon-keyboard_arrow_right{
        vertical-align: middle;
        line-height: 24px;
        font-size: 10px;
      }
    }
  }

  // 公告样式
  .bulletin-wrapper{
    position: relative;
    height: 28px;
    line-height: 28px;
    padding: 0 24px 0 12px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    background: rgba(7, 17, 27, 0.2);
    // 公告图标
    .bulletin-title{
      display: inline-block;
      vertical-align: top;
      margin-top: 9px;
      width: 22px;
      height: 12px;
      @include bg-image('../../assets/img/bulletin');
      background-size: 22px 12px;
      background-repeat: no-repeat;
    }
    // 公告内容
    .bulletin-text{
      margin: 0 4px;
      font-size: 10px;
    }
    // 展开箭头
    .icon-keyboard_arrow_right{
      position: absolute;
      font-size: 10px;
      right: 12px;
      top: 9px;
    }
  }

  // 背景图片
  .background{
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
    height: 100%;
    z-index: -1;
    filter: blur(10px)
  }

  // 详情浮层
  .detail{
    position: fixed;
    z-index: 100;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background: rgba(7, 17, 27, 0.85);
    &.fade-enter-active, &.fade-leave-active{
      transition: opacity 0.5s;
    }
    &.fade-enter, &.fade-leave-to{
      opacity: 0;
    }
    .detail-wrapper{
      min-height: 100%;
      width: 100%;
      .detail-main{
        margin-top: 64px;
        padding-bottom: 64px;
        .name{
          line-height: 16px;
          text-align: center;
          font-size: 16px;
          font-weight: 700;
        }
        .star-wrapper{
          margin-top: 18px;
          padding: 2px 0;
          text-align: center;
        }
        .title{
          display: flex;
          width: 80%;
          margin: 28px auto 24px auto;
          .line{
            flex: 1;
            position: relative;
            top: -6px;
            border-bottom: 1px solid rgba(255, 255, 255, 0.2);
          }
          .text{
            padding: 0 12px;
            font-weight: 700px;
            font-size: 14px;
          }
        }
        .supports{
          width: 80%;
          margin: 0 auto;
          .support-item{
            padding: 0 12px;
            margin-bottom: 12px;
            &:last-child{
              margin-bottom: 0;
            }
            .icon{
              display: inline-block;
              width: 16px;
              height: 16px;
              vertical-align: top;
              margin-right: 6px;
              background-size: 16px 16px;
              background-repeat: no-repeat;
              &.decrease{
                @include bg-image('../../assets/img/decrease_2');
              }
              &.discount{
                @include bg-image('../../assets/img/discount_2');
              }
              &.guarantee{
                @include bg-image('../../assets/img/guarantee_2');
              }
              &.invoice{
                @include bg-image('../../assets/img/invoice_2');
              }
              &.special{
                @include bg-image('../../assets/img/special_2');
              }
            }
            .text{
              line-height: 16px;
              font-size: 12px;
            }
          }
        }
        .bulletin{
          width: 80%;
          margin: 0 auto;
          .content{
            padding: 0 12px;
            line-height: 24px;
            font-size: 12px;
          }
        }
      }
    }
    .detail-close{
      position: relative;
      width: 32px;
      height: 32px;
      margin: -64px auto 0 auto;
      clear: both;
      font-size: 32px;
    }
  }
</style>
