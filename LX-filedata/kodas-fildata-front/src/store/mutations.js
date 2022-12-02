export default {
  set_type(state, type) {
    state.type = type;
  },
  set_spatialList(state, list) {
    state.spatialList = list;
  },

  set_rectangleList(state, list) {
    state.rectangleList = list;
  },

  set_indexRatio(state, ratio) {
    state.indexRatio = ratio;
  },

  set_selectZone(state, select) {
    state.selectZone = select;
  },

  set_checkSpatial(state, select) {
    state.checkSpatial = select;
  },

  set_zoom(state, zoom) {
    state.zoom = zoom;
  },

  set_center(state, center) {
    state.center = center;
  },

  set_userId(state, userId) {
    state.userId = userId;
  },

  // set_center(state, center) {
  //   state.center.lat = center.lat;
  //   store.center.lng = center.lng;
  // },

  // set_latLng(state, latLng) {
  //   state.LatLng.lat = latLng.lat;
  //   state.LatLng.lng = latLng.lng;
  // },

  // set_area(state, area) {
  //   state.area = area.area;
  //   state.changeLV2 = area.changeLV;
  // },

  // set_sensorList(state, sensorList) {
  //   state.sensorList = sensorList;
  //   state.changeLV3 = true;
  // },

  // set_learningList(state, learningList) {
  //   state.learningList = learningList;
  // },

  // set_imgUrl(state, listData) {
  //   state.listData = listData;
  // },

  // set_spatialList(state, spatialList) {
  //   state.spatialList = spatialList;
  // },

  // set_showRectangle(state, bool) {
  //   state.showRectangle = bool;
  // },

  // set_rectangle(state, rectangle) {
  //   state.rectangle = rectangle;
  // },
};
