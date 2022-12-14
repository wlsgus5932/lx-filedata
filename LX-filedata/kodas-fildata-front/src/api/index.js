import axios from 'axios';

const ins = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
  headers: { 'Content-Type': `application/json` },
});

// 센서 api
const getSensorList = param => ins.post(`sensorList`, param);

const getSensorSectionList = param => ins.post(`sensorSectionList`, param);

const getSensorRandomList = param => ins.post(`sensorRandomList`, param);

// 학습 api
const getLearningList = param => ins.post(`learningList`, param);

const getLearningSectionList = param => ins.post(`learningSectionList`, param);

const getLearningRandomList = param => ins.post(`learningRandomList`, param);

// 공간 api
const getSpatialList = param => ins.post(`spatialList?${param}`);

const getSelectZoneList = param => ins.post(`spatialList?${param}`);

const getLocationCode = () => ins.get('areaList');

const getLdmList = () => ins.get('ldmList');

const setHistoryData = param => ins.post('historyList', param);

export {
  getSensorList,
  getSensorSectionList,
  getSensorRandomList,
  getLearningList,
  getLearningSectionList,
  getLearningRandomList,
  getLocationCode,
  getSpatialList,
  getSelectZoneList,
  getLdmList,
  setHistoryData,
};
