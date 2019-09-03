module.exports = {
  root: true,
  env: {
    node: true
  },
  'extends': [
    'plugin:vue/essential',
    '@vue/standard'
  ],
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'semi': 0,// 结尾可以有分号
    'space-before-function-paren': 0,// 方法后可以不需要空格
    'eqeqeq':0,// 可以使用==进行判断
    'no-unused-vars':0,// 可以有变量不被使用
    'no-unused-components':0,//可以有注册但未被使用的组件
    'no-undef':0//可以有未定义使用的变量
  },
  parserOptions: {
    parser: 'babel-eslint'
  }
}
