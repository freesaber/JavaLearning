<template>
  <div class="star" :class="starType">
    <span v-for="(itemClass,key) in itemClasses"
      :class="itemClass"
      class="star-item"
      :key="key"
    ></span>
  </div>
</template>

<script>
const LENGTH = 5;
const CLS_ON = 'on';
const CLS_HALF = 'half';
const CLS_OFF = 'off';

export default {
  name: 'star',
  props: {
    size: Number,
    score: Number
  },
  components: {},
  data() {
    return {}
  },
  created() {

  },
  computed: {
    starType() {
      return 'star-' + this.size;
    },
    itemClasses() {
      let result = [];
      let score = Math.floor(this.score * 2) / 2;
      let hasDecimal = score % 1 !== 0;
      let integer = Math.floor(score);
      for (let index = 0; index < integer; index++) {
        result.push(CLS_ON);
      }
      if (hasDecimal) {
        result.push(CLS_HALF);
      }
      while (result.length < LENGTH) {
        result.push(CLS_OFF);
      }
      return result;
    }
  },
  watch: {

  },
  mounted() {

  },
  methods: {

  }
}
</script>

<style lang="scss" scoped>
  @import "../../../assets/scss/mixins";

  .star{
    .star-item{
      display: inline-block;
      background-repeat: no-repeat;
    }
    &.star-48{
      .star-item{
        width: 20px;
        height: 20px;
        margin-right: 22px;
        background-size: 20px 20px;
        &:last-child{
          margin-right: 0;
        }
        &.on{
          @include bg-image('../../../assets/img/star48_on');
        }
        &.half{
          @include bg-image('../../../assets/img/star48_half');
        }
        &.off{
          @include bg-image('../../../assets/img/star48_off');
        }
      }
    }
    &.star-36{
      .star-item{
        width: 15px;
        height: 15px;
        @media only screen and (max-width: 320px){
          margin-right: 5px!important;
        }
        margin-right: 10px;
        background-size: 15px 15px;
        &:last-child{
          margin-right: 0;
        }
        &.on{
          @include bg-image('../../../assets/img/star36_on');
        }
        &.half{
          @include bg-image('../../../assets/img/star36_half');
        }
        &.off{
          @include bg-image('../../../assets/img/star36_off');
        }
      }
    }
    &.star-24{
      .star-item{
        width: 10px;
        height: 10px;
        margin-right: 3px;
        background-size: 10px 10px;
        &:last-child{
          margin-right: 0;
        }
        &.on{
          @include bg-image('../../../assets/img/star24_on');
        }
        &.half{
          @include bg-image('../../../assets/img/star24_half');
        }
        &.off{
          @include bg-image('../../../assets/img/star24_off');
        }
      }
    }
  }
</style>
