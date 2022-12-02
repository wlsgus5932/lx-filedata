export default {
  set_sensorList({ commit }, sensorList) {
    commit('set_sensorList', sensorList);
  },

  set_learningList({ commit }, learningList) {
    commit('set_learningList', learningList);
  },

  set_spatialList({ commit }, spatialList) {
    commit('set_spatialList', spatialList);
  },

  set_rectangleList({ commit }, list) {
    commit('set_rectangleList', list);
  },
};
