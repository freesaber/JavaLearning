module.exports = {
  devServer: {
    proxy: {
      // '/api': {
      //   target: 'http://localhost:8080',
      //   changeOrigin: true,
      //   autoRewrite: true,
      //   cookieDomainRewrite: true,
      //   pathRewrite: {
      //     '^/api': '/api'
      //   }
      // }
    }
  }
}
