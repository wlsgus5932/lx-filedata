<template>
  <div class="sensor-form">
    <Spinner v-if="isLoading"></Spinner>
    <div v-if="!isLoading">
      <v-card
        class="text-center white--text font-weight-bold"
        height="30"
        style="margin-bottom: 30px; padding: 3px"
        color="#757575"
        >{{ $store.state.type }}
      </v-card>

      <v-card class="cards" style="background-color: #ddd">
        <v-col cols="11">
          <v-select
            :items="sensorTypeCodes1"
            item-text="name"
            item-value="value"
            v-model="formParams.sensorType1"
            label="센서 종류 선택"
            dense
            prepend-icon="mdi-file-tree"
            v-on:change="changeSensorType"
          ></v-select>
        </v-col>
        <v-col cols="11" v-if="sensorTypeCodes2 != 0">
          <v-select
            :items="sensorTypeCodes2"
            item-text="name"
            item-value="code"
            v-model="formParams.sensorType2"
            label="상세 센터 선택"
            dense
            prepend-icon="2"
          ></v-select>
        </v-col>
      </v-card>

      <div style="margin-bottom: 10px">
        <span
          class="secondary--text font-weight-bold text-truncate"
          style="cursor: pointer"
          @click="reqCheck = !reqCheck"
          ><i
            aria-hidden="true"
            class="v-icon notranslate mdi mdi-magnify theme--light"
          ></i>
          추가검색옵션
          <i
            v-if="reqCheck"
            class="v-icon notranslate mdi mdi-chevron-down theme--light"
          ></i>
          <i
            v-else
            class="v-icon notranslate mdi mdi-chevron-right theme--light"
          ></i
        ></span>
      </div>

      <template v-if="reqCheck">
        <v-card class="cards" style="margin-bottom: 30px">
          <v-row>
            <v-col cols="12">
              <datePicker
                @startDate="startDateChange"
                @endDate="endDateChange"
              ></datePicker>
            </v-col>
          </v-row>
        </v-card>

        <v-card style="margin-bottom: 30px" class="cards">
          <v-row>
            <v-col cols="6">
              <v-text-field
                v-model="formParams.lowVelocity"
                label="최저속도(km)"
                prepend-icon="mdi-speedometer"
                type="number"
                placeholder="1"
                min="0"
              ></v-text-field>
            </v-col>
            <v-col cols="5">
              <v-text-field
                v-model="formParams.highVelocity"
                label="최고속도(km)"
                min="0"
                type="number"
                placeholder="max"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-card>

        <v-card class="cards">
          <v-col cols="11">
            <v-select
              :items="sunlightItem"
              item-text="name"
              item-value="value"
              v-model="formParams.sunlight"
              label="일광"
              dense
              prepend-icon="mdi-theme-light-dark"
            ></v-select>
          </v-col>
        </v-card>

        <v-card class="cards">
          <v-col cols="11">
            <v-select
              :items="weatherItem"
              v-model="formParams.weather"
              item-text="name"
              item-value="value"
              label="날씨"
              dense
              prepend-icon="mdi-weather-sunset"
            ></v-select>
          </v-col>
        </v-card>

        <v-card class="cards">
          <v-col cols="11">
            <v-select
              class=""
              :items="locationCode"
              item-text="name"
              item-value="value"
              v-model="formParams.location"
              label="획득지역"
              dense
              prepend-icon="mdi-map-marker"
            ></v-select>
          </v-col>
        </v-card>

        <v-card class="cards">
          <v-col cols="11">
            <v-select
              :items="conditionsCodes"
              item-text="name"
              item-value="code"
              v-model="formParams.conditions"
              label="도로상황"
              dense
              prepend-icon="mdi-road-variant"
            ></v-select>
          </v-col>
        </v-card>
      </template>

      <div style="float: right">
        <v-btn
          color="secondary white--text font-weight-bold"
          @click="choiceModal = true"
          style="margin: 10px 0px 50px; width: 100px"
          >검색</v-btn
        >
      </div>
    </div>

    <SensorList
      v-if="showSensorList"
      :formParams="formParams"
      @hide="hideSensorList()"
      ref="sensor"
    />

    <!-- modal zone -->
    <template class="modal-zone">
      <v-dialog width="800"> </v-dialog>

      <v-dialog v-model="choiceModal" width="800">
        <choiceModal
          :title="title"
          :content="content"
          @submit="(choiceModal = false), submitForm()"
          @hide="choiceModal = false"
        >
        </choiceModal>
      </v-dialog>

      <v-dialog v-model="alertModal" persistent width="600" height="200">
        <alertModal
          :title="alertTitle"
          :content="alertContent"
          @hide="hideModal()"
        ></alertModal>
      </v-dialog>

      <v-dialog v-model="circularModal" persistent width="500">
        <circularModal :content="circularContent"></circularModal>
      </v-dialog>
    </template>
    <!-- modal zone end. -->
  </div>
</template>

<script>
import datePicker from '@/components/DatePicker/datePicker.vue';
import Spinner from '@/components/Spinner/Spinner.vue';
import SensorList from '@/views/SideBarView/SensorList.vue';
import choiceModal from '../Modal/choiceModal.vue';
import circularModal from '../../components/Modal/circularModal.vue';
import alertModal from '../../components/Modal/alertModal.vue';
// import eventBus from '@/eventBus/eventBus';

export default {
  components: {
    datePicker,
    Spinner,
    SensorList,
    choiceModal,
    circularModal,
    alertModal,
  },

  data() {
    return {
      title: '선택한 조건으로 검색 하시겠습니까?',
      content: '사용자의 네트워크 상태에 따라 소요시간이 길어질 수 있습니다.',
      circularContent: '목록을 가져오는중입니다.',
      alertTitle: '데이터가 없습니다.',
      alertContent: '검색하신 조건에 맞는 데이터가 없습니다.',
      choiceModal: false,
      alertModal: false,
      circularModal: false,
      isLoading: false,
      reqCheck: false,
      sensorList: [],
      formParams: {
        startDate: '',
        endDate: '',
        lowVelocity: '',
        highVelocity: '',
        sunlight: '',
        conditions: '',
        weather: '',
        location: '',
        sensorType1: '',
        sensorType2: '',
        page: 1,
        startNumber: '',
        endNumber: '',
        randomNumber: '',
      },
      showSensorList: false,

      sunlightItem: [
        { name: '전체', value: '' },
        { name: '주간', value: '주간' },
        { name: '야간', value: '야간' },
      ],

      weatherItem: [
        { name: '전체', value: '' },
        { name: '맑음', value: '맑음' },
        { name: '비', value: '비' },
        { name: '비/안개', value: '비/안개' },
      ],

      locationCode: [
        { name: '전체', value: '' },
        { name: '과천시', value: '과천' },
        { name: '군포시', value: '군포' },
        { name: '분당', value: '분당' },
        { name: '성남시', value: '성남' },
        { name: '수원시', value: '수원' },
        { name: '시흥시', value: '시흥' },
        { name: '아산시', value: '아산' },
        { name: '안산시', value: '안산' },
        { name: '안양시', value: '안양' },
        { name: '전주시', value: '전주' },
        { name: '제주도', value: '제주' },
        { name: '의왕시', value: '의왕' },
        { name: '판교', value: '판교' },
        { name: '평택시', value: '평택' },
        { name: '화성시', value: '화성' },
      ],

      sensorTypeCodes1: [
        { name: '전체', value: '' },
        { name: 'CAMERA', value: 'CAMERA' },
        { name: 'LIDAR', value: 'LIDAR' },
        { name: 'RADAR', vlaue: 'RADAR' },
      ],

      sensorTypeCodes2: [],

      conditionsCodes: [
        { name: '전체', code: '' },
        { name: '일반상황', code: 'C00500003' },
        { name: '일반상황/공사상황', code: 'C00500004' },
        { name: '긴급상황(사람난입)', code: 'C00500002' },
        { name: '긴급상황(도로위 물체)', code: 'C00500001' },
      ],
    };
  },

  methods: {
    submitForm() {
      this.showSensorList = true;
      this.formParams.page = 1;
      // this.circularModal = true;
      this.$nextTick(() => {
        this.$refs.sensor.submitForm();
      });
    },

    changeSensorType() {
      this.sensorTypeCodes2 = [];
      this.formParams.sensorType2 = '';
      switch (this.formParams.sensorType1) {
        case 'CAMERA':
          this.sensorTypeCodes2 = [
            { name: '전체', code: '' },
            { name: '카메라: 후방중앙 (RearMid)', code: 'RearMid' },
            { name: '카메라: 전방중앙 (FrontMid)', code: 'FrontMid' },
            { name: '카메라: 전방좌측 (FrontLeft)', code: 'FrontLeft' },
            { name: '카메라: 전방우측 (FrontRight)', code: 'FrontRight' },
            { name: '카메라: 측면좌측 (BSDCAM_L)', code: 'BSDCAM_L' },
            { name: '카메라: 측면우측 (BSDCAM_R)', code: 'BSDCAM_R' },
            { name: '카메라: 파노라마 (Panorama)', code: 'Panorama' },
          ];
          break;

        case 'LIDAR':
          this.sensorTypeCodes2 = [
            { name: '전체', code: '' },
            {
              name: '라이다: 전방향 (VLS128, 20년 이후 데이터)',
              code: 'VLS128',
            },
            { name: '라이다: 전방향 (HDL32, 20년 이전 데이터)', code: 'HDL32' },
            { name: '라이다: 전방좌측 (LDMRS01)', code: 'LDMRS01' },
            { name: '라이다: 전방우측 (LDMRS02)', code: 'LDMRS02' },
            { name: '라이다: 후방좌측 (BSLIDAR_L, VLP16)', code: 'BSDLiDAR_L' },
            { name: '라이다: 후방우측 (BSLIDAR_R, VLP16)', code: 'BSDLiDAR_R' },
          ];
          break;

        case 'RADAR':
          this.sensorTypeCodes2 = [
            { name: '전체', code: '' },
            { name: '레이더: 전방중앙 (RadarF)', code: 'RadarF' },
            { name: '레이더: 후방우측 (RadarRR)', code: 'RadarRR' },
            { name: '레이더: 후방좌측 (RadarRL)', code: 'RadarRL' },
          ];
          break;

        default:
          this.sensorTypeCodes2 = [];
      }
    },
    startDateChange(startDate) {
      // this.formParams.startDate = startDate.substring(2, startDate.length);
      this.formParams.startDate = startDate;
    },

    endDateChange(endDate) {
      // this.formParams.endDate = endDate.substring(2, endDate.length);
      this.formParams.endDate = endDate;
    },

    hideModal() {
      this.alertModal = false;
      this.circularModal = false;
      this.choiceModal = false;
    },

    hideSensorList() {
      this.showSensorList = false;
      this.alertTitle = '검색조건에 맞는 데이터가 없습니다.';
      this.alertContent =
        '검색조건에 맞는 데이터가 없습니다. 다시 시도해주세요.';
      this.alertModal = true;
    },
  },
};
</script>

<style scoped>
.sensor-form {
  left: 65px;
  width: 330px;
  position: absolute;
  background-color: white;
  height: 100%;
  top: 0px;
  bottom: 0px;
  border: 1px solid #ddd;
  overflow: auto;
  padding: 20px;
}

.v-text-field >>> input {
  font-size: 13px;
  font-weight: bold;
}
.v-text-field >>> label {
  font-size: 13px;
  font-weight: bold;
}
.cards {
  text-align: center;
  font-weight: bold;
  margin-bottom: 20px;
  padding: 10px 0px 0px 0px;
  background-color: #1111;
}
.v-select {
  font-size: 12px;
  font-weight: bold;
}
</style>
