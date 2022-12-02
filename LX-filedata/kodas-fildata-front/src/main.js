import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import VueTreeNavigation from 'vue-tree-navigation';

import JSZip from 'jszip';
import vuetify from './plugins/vuetify';

Vue.use(VueTreeNavigation);
Vue.use(JSZip);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App),
}).$mount('#app');
