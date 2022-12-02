module.exports = {
  devServer: {
    disableHostCheck: true,
    overlay: false,
    proxy: {
      '/rest': {
        target: 'http://localhost:9000',
        ws: true,
        changeOrigin: true,
      },
      '/data': {
        target: 'http://124.194.100.230:10002',
      },
      '/test': {
        target: 'http://124.194.100.230:10002',
      },
    },
  },

  transpileDependencies: ['vuetify'],
};
