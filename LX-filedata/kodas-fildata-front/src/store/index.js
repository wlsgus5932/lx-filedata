import Vue from 'vue';
import Vuex from 'vuex';
import mutations from './mutations';
import actions from './actions';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    zoom: 8,
    center: [37.315, 126.86],
    type: '',
    rectangleList: [],
    indexRatio: '',
    selectZone: [],
    checkSpatial: [],
    url: 'http://data.kodas.or.kr',
    // LatLng: { lat: 35.83870732602445, lng: 127.05842471398813 },
    userId: 'default',
  },
  mutations,
  actions,
});
