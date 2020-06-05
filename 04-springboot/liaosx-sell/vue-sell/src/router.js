import Vue from 'vue'
import Router from 'vue-router'
import goods from './views/goods.vue'
import ratings from './views/ratings.vue'
import seller from './views/seller.vue'

Vue.use(Router)

export default new Router({
  // mode: 'history', 该模式会导致刷新404
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/goods',
      name: 'goods',
      component: goods
    },
    {
      path: '/ratings',
      name: 'ratings',
      component: ratings
    },
    {
      path: '/seller',
      name: 'seller',
      component: seller
    }
  ]
})
