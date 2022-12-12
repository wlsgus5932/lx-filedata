<template>
  <div class="learning-form">
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
            :items="learningTypeCodes"
            item-text="name"
            item-value="value"
            v-model="formParams.learningType"
            label="데이터타입"
            v-on:change="changeLearningType"
            dense
            prepend-icon="mdi-file-tree"
          ></v-select>
        </v-col>
        <v-col cols="11" v-if="formParams.learningType">
          <v-select
            :items="learningTypeCodes2"
            item-text="name"
            item-value="value"
            v-model="formParams.learningType2"
            label="데이터타입2"
            dense
            prepend-icon="mdi-file-tree"
          ></v-select>
        </v-col>
      </v-card>

      <v-card class="cards">
        <v-col cols="11">
          <v-combobox
            v-model="moveChange"
            :items="moveItem"
            label="이동체 정보 (OR)"
            multiple
            small-chips
            prepend-icon="mdi-traffic-light"
            item-text="name"
            @change="changeMove"
          >
            <template v-slot:selection="{ attrs, item, parent, selected }">
              <v-chip
                v-if="item === Object(item)"
                v-bind="attrs"
                :input-value="selected"
                label
                small
              >
                <span class="pr-2">
                  {{ item.name }}
                </span>
                <v-icon small @click="parent.selectItem(item), moveChange()">
                  $delete
                </v-icon>
              </v-chip>
            </template>
            <template v-slot:item="{ index, item }">
              <v-text-field
                v-if="editing === item"
                v-model="editing.text"
                autofocus
                flat
                background-color="transparent"
                hide-details
                solo
                @keyup.enter="edit(index, item)"
              ></v-text-field>
              <span style="font-size: 14px">{{ item.name }}</span>
            </template>
          </v-combobox>
        </v-col>
      </v-card>

      <v-card class="cards">
        <v-col cols="11">
          <v-combobox
            v-model="fixedChange"
            :items="fixedItem"
            label="고정체 정보 (OR)"
            multiple
            small-chips
            prepend-icon="mdi-traffic-light"
            item-text="name"
            @change="changeFixed"
          >
            <template v-slot:selection="{ attrs, item, parent, selected }">
              <v-chip
                v-if="item === Object(item)"
                v-bind="attrs"
                :input-value="selected"
                label
                small
              >
                <span class="pr-2">
                  {{ item.name }}
                </span>
                <v-icon small @click="parent.selectItem(item), fixedChange()">
                  $delete
                </v-icon>
              </v-chip>
            </template>
            <template v-slot:item="{ index, item }">
              <v-text-field
                v-if="editing === item"
                v-model="editing.text"
                autofocus
                flat
                background-color="transparent"
                hide-details
                solo
                @keyup.enter="edit(index, item)"
              ></v-text-field>
              <span style="font-size: 14px">{{ item.name }}</span>
            </template>
          </v-combobox>
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

        <v-card class="cards" style="margin-bottom: 30px">
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
                type="number"
                placeholder="max"
                min="0"
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

        <v-card class="cards">
          <v-col cols="11">
            <v-select
              :items="environmentCodes1"
              item-text="name"
              item-value="value"
              v-model="formParams.environment1"
              label="도로환경"
              v-on:change="changeEnvironment"
              dense
              prepend-icon="mdi-road"
            ></v-select>
          </v-col>
          <v-col cols="11" v-if="environmentCodes2 != 0">
            <v-select
              :items="environmentCodes2"
              v-model="formParams.environment2"
              item-text="name"
              item-value="value"
              label="도로환경2"
              dense
              prepend-icon="2"
            ></v-select>
          </v-col>
        </v-card>
      </template>
      <div style="float: right">
        <v-btn
          class="secondary font-weight-bold"
          @click="choiceModal = true"
          style="margin: 10px 0px 50px; width: 100px"
          >검색</v-btn
        >
      </div>
    </div>
    <LearningList
      v-if="showLearningList"
      :formParams="formParams"
      :list="learningList"
      @hide="hideLearningList()"
      ref="learning"
    ></LearningList>

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
import LearningList from '@/views/SideBarView/LearningList.vue';
import choiceModal from '../Modal/choiceModal.vue';
import circularModal from '../../components/Modal/circularModal.vue';
import alertModal from '../../components/Modal/alertModal.vue';

export default {
  components: {
    datePicker,
    Spinner,
    LearningList,
    choiceModal,
    circularModal,
    alertModal,
  },

  data() {
    return {
      editing: '',
      editingIndex: '',
      isLoading: false,
      btnLoading: false,
      learningList: [],
      searchDialog: false,
      searchDialog2: false,

      showLearningList: false,
      title: '선택한 조건으로 검색 하시겠습니까?',
      content: '사용자의 네트워크 상태에 따라 소요시간이 길어질 수 있습니다.',
      circularContent: '목록을 가져오는중입니다.',
      alertTitle: '데이터가 없습니다.',
      alertContent: '검색하신 조건에 맞는 데이터가 없습니다.',
      choiceModal: false,
      alertModal: false,
      circularModal: false,

      formParams: {
        startDate: '',
        endDate: '',
        sunlight: '',
        move: '',
        fixed: [],
        weather: '',
        location: '',
        learningType: '',
        learningType2: '',
        conditions: '',
        environment1: '',
        environment2: '',
        lowVelocity: '',
        highVelocity: '',
        page: 1,
      },
      fixedChange: [],
      moveChange: [],
      reqCheck: false,
      learningListChange: false,

      sunlightItem: [
        { name: '전체', value: '' },
        { name: '주간', value: '주간' },
        { name: '야간', value: '야간' },
      ],
      moveItem: [
        { name: '사람', value: 'HUMN_CNT' },
        { name: '승용차', value: 'PASG_CNT' },
        { name: '버스', value: 'BUS_CNT' },
        { name: '트럭', value: 'TRCK_CNT' },
        { name: '오토바이', value: 'MTCY_CNT' },
        { name: '특수차', value: 'SPAC_CNT' },
      ],

      fixedItem: [
        { name: '정지선', value: 'SPLE_CNT' },
        { name: '횡단보도', value: 'CSWK_CNT' },
        { name: '방지턱', value: 'SDBP_CNT' },
        { name: '공사중', value: 'RK_CNT' },
        { name: '신호등', value: 'TLIGT_CNT' },
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

      learningTypeCodes: [
        { name: '전체', value: '' },
        { name: 'SEGMENTATION', value: 'SEG' },
        { name: 'BBOX', value: 'BBOX' },
        { name: 'CUBOID', value: 'CUBOID' },
      ],

      learningTypeCodes2: [],

      conditionsCodes: [
        { name: '전체', code: '' },
        { name: '긴급상황(도로위 물체)', code: 'C00500001' },
        { name: '긴급상황(사람난입)', code: 'C00500002' },
        { name: '일반상황', code: 'C00500003' },
        { name: '일반상황 공사상황', code: 'C00500004' },
      ],

      environmentCodes1: [
        { name: '전체', value: '' },
        { name: '일반도로', value: '일반도로' },
        { name: '주차장', value: '주차장' },
      ],

      environmentCodes2: [],
    };
  },
  methods: {
    changeFixed(item) {
      this.formParams.fixed = [];
      this.fixedChange.filter(e => {
        e.value != item.value;
      });
      this.fixedChange.forEach(e => {
        this.formParams.fixed.push(e.value);
      });
    },
    changeMove(item) {
      this.formParams.move = [];
      this.moveChange.filter(e => {
        e.value != item.value;
      });
      this.moveChange.forEach(e => {
        this.formParams.move.push(e.value);
      });
    },
    edit(index, item) {
      if (!this.editing) {
        this.editing = item;
        this.editingIndex = index;
      } else {
        this.editing = null;
        this.editingIndex = -1;
      }
    },
    submitForm() {
      this.showLearningList = true;
      this.formParams.page = 1;
      this.$nextTick(() => {
        this.$refs.learning.submitForm();
      });
    },

    changeLearningType() {
      this.learningTypeCodes2 = [];
      this.formParams.learningType2 = '';
      switch (this.formParams.learningType) {
        case 'SEG':
          this.learningTypeCodes2 = [
            { name: '전체', value: '' },
            { name: '카메라: 전방좌측 (FrontLeft)', value: 'FrontLeft' },
            { name: '카메라: 파노라마 (Panorama)', value: 'Panorama' },
          ];
          break;

        case 'BBOX':
          this.learningTypeCodes2 = [
            { name: '전체', value: '' },
            { name: '카메라: 전방좌측 (FrontLeft)', value: 'FrontLeft' },
            { name: '카메라: 파노라마 (Panorama)', value: 'Panorama' },
          ];
          break;

        case 'CUBOID':
          this.learningTypeCodes2 = [
            { name: '전체', value: '' },
            { name: '라이다: 전방향 (VLS128)', value: 'VLS128' },
          ];
          break;

        default:
          this.learningTypeCodes2 = [];
      }
    },

    changeEnvironment() {
      this.environmentCodes2 = [];
      this.formParams.environment2 = '';
      if (this.formParams.environment1 == '일반도로') {
        this.environmentCodes2 = [
          { name: '전체', value: '' },
          {
            name: '교차로',
            value: [
              'C00600001',
              'C00600005',
              'C00600006',
              'C00600007',
              'C00600012',
            ],
          },
          {
            name: '고가차도',
            value: ['C00600003', 'C00600004', 'C00600006', 'C00600007'],
          },
          {
            name: '내리막',
            value: [
              'C00600007',
              'C00600008',
              'C00600009',
              'C00600011',
              'C00600012',
            ],
          },
          { name: '오르막', value: ['C00600007', 'C00600008', 'C00600009'] },
          { name: '터널', value: ['C00600010', 'C00600014', 'C00600016'] },
          { name: '톨게이트', value: ['C00600015', 'C00600016'] },
        ];
      }
    },

    startDateChange(startDate) {
      this.formParams.startDate = startDate;
    },

    endDateChange(endDate) {
      this.formParams.endDate = endDate;
    },

    hideLearningList() {
      this.showLearningList = false;
      this.alertTitle = '검색조건에 맞는 데이터가 없습니다.';
      this.alertContent =
        '검색조건에 맞는 데이터가 없습니다. 다시 시도해주세요.';
      this.alertModal = true;
    },
  },
};
</script>

<style scoped>
.learning-form {
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
/* ::v-deep .combobox .v-text-field {
  font-size: 12px;
} */
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
