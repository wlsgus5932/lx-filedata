import axios from 'axios';

const ins = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
  headers: { 'Content-Type': `application/json` },
});

// sensor api.
const getSensorList = param => ins.post(`sensorList`, param);

const getSensorSectionList = param => ins.post(`sensorSectionList`, param);

const getSensorRandomList = param => ins.post(`sensorRandomList`, param);

// learning api.
const getLearningList = (param, page) =>
  ins.post(`learningList?${param}&page=${page}`);

// spatial api.
const getSpatialList = param => ins.post(`spatialList?${param}`);
// end.

const getSelectZoneList = param => ins.post(`spatialList?${param}`);

const getLocationCode = () => ins.get('areaList');

const getLdmList = () => ins.get('ldmList');

const setHistoryData = param => ins.post('historyList', param);

export {
  getSensorList,
  getSensorSectionList,
  getSensorRandomList,
  getLearningList,
  getLocationCode,
  getSpatialList,
  getSelectZoneList,
  getLdmList,
  setHistoryData,
};
