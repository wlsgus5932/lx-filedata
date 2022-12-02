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
        <v-col cols="11" v-if="formParams.learningType == 'SEGMENTATION'">
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
            v-model="formParams.move"
            :items="moveItem"
            item-text="name"
            item-value="value"
            label="이동체 정보 (OR)"
            multiple
            small-chips
            right
            prepend-icon="mdi-car-hatchback"
          ></v-combobox>
        </v-col>
      </v-card>

      <v-card class="cards">
        <v-col cols="11">
          <v-combobox
            v-model="formParams.fixed"
            :items="fixedItem"
            item-text="name"
            item-value="value"
            label="고정체 정보 (OR)"
            multiple
            small-chips
            prepend-icon="mdi-traffic-light"
          ></v-combobox>
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
          @click="searchDialog = true"
          style="margin: 10px 0px 50px; width: 100px"
          >검색</v-btn
        >
      </div>
    </div>
    <LearningList
      v-if="learningListChange"
      :formParams="formParams"
      :list="learningList"
    ></LearningList>

    <!-- search 다이얼로그 -->
    <v-dialog v-model="searchDialog" width="800">
      <v-card width="100%">
        <v-card-title> 선택한 조건으로 검색하시겠습니까? </v-card-title>

        <v-card-text class="font-weight-bold grey--text mb-2">
          사용자의 네트워크 상태에 따라 소요시간이 길어질 수 있습니다. (평균
          30초 미만)</v-card-text
        >

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="green darken-1" text @click="searchDialog = false">
            취소
          </v-btn>

          <v-btn
            color="green darken-1"
            text
            @click="
              (searchDialog = false), (searchDialog2 = true), submitForm(0)
            "
          >
            확인
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog
      v-model="searchDialog2"
      width="500"
      persistent
      class="justify-center"
    >
      <v-card width="100%" height="8%">
        <v-card-actions>
          <v-progress-circular
            :size="30"
            color="primary"
            indeterminate
          ></v-progress-circular>

          <v-card-text class="font-weight-bold grey--text">
            목록을 가져오는중입니다...
          </v-card-text>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import datePicker from '@/components/DatePicker/datePicker.vue';
import { getLearningList } from '@/api/index';
import Spinner from '@/components/Spinner/Spinner.vue';
import LearningList from '@/views/SideBarView/LearningList.vue';

export default {
  components: { datePicker, Spinner, LearningList },

  data() {
    return {
      isLoading: false,
      btnLoading: false,
      learningList: [],
      searchDialog: false,
      searchDialog2: false,
      formParams: {
        startDate: '',
        endDate: '',
        sunlight: '',
        move: '',
        fixed: '',
        weather: '',
        location: '',
        sensorType1: '',
        sensorType2: '',
        learningType: '',
        learningType2: '',
        conditions: '',
        environment1: '',
        environment2: '',
        lowVelocity: '',
        highVelocity: '',
        page: 1,
      },
      reqCheck: false,
      search: '',
      learningListChange: false,

      sunlightItem: [
        { name: '전체', value: '' },
        { name: '주간', value: '주간' },
        { name: '야간', value: '야간' },
      ],
      moveItem: [
        { name: '사람', value: '사람' },
        { name: '승용차', value: '승용차' },
        { name: '버스', value: '버스' },
        { name: '트럭', value: '트럭' },
        { name: '오토바이', value: '오토바이' },
        { name: '특수차', value: '특수차' },
      ],

      fixedItem: [
        { name: '정지선', value: '정지선' },
        { name: '횡단보도', value: '횡단보도' },
        { name: '방지턱', value: '방지턱' },
        { name: '공사중', value: '공사중' },
        { name: '신호등', value: '신호등' },
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
        { name: '전주시', value: '전주시' },
        { name: '제주도', value: '제주' },
        { name: '의왕시', value: '의왕' },
        { name: '판교', value: '판교' },
        { name: '평택시', value: '평택' },
        { name: '화성시', value: '화성' },
      ],

      learningTypeCodes: [
        { name: '전체', value: '' },
        { name: 'SEGMENTATION', value: 'SEGMENTATION' },
        { name: 'ANNOTATION', value: 'ANNOTATION_2D' },
        { name: 'CUBOID', value: 'cuboid' },
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
    async submitForm(nums) {
      if (nums == 0) {
        this.learningList = [];
        this.search = [];
        this.formParams.page = 1;
      }
      if (this.formParams.page == 1) {
        if (this.formParams.startDate) {
          let convertDate = this.formParams.startDate.substring(
            2,
            this.formParams.startDate.length,
          );
          this.search += `&startDate=${convertDate}`;
        }
        if (this.formParams.endDate) {
          let convertDate = this.formParams.endDate.substring(
            2,
            this.formParams.endDate.length,
          );
          this.search += `&endDate=${convertDate}`;
        }
        if (this.formParams.lowVelocity == '') this.search += `&lowVelocity=0`;
        else if (this.formParams.lowVelocity > 0)
          this.search += `&lowVelocity=${this.formParams.lowVelocity}`;
        if (this.formParams.highVelocity)
          this.search += `&highVelocity=${this.formParams.highVelocity}`;
        if (this.formParams.sunlight)
          this.search += `&sunlight=${this.formParams.sunlight}`;
        if (this.formParams.weather)
          this.search += `&weather=${this.formParams.weather}`;
        if (this.formParams.conditions)
          this.search += `&conditions=${this.formParams.conditions}`;
        if (this.formParams.environment1 && !this.formParams.environment2)
          this.search += `&environment1=${this.formParams.environment1}`;
        else if (this.formParams.environment2)
          this.search += `&environment2=${this.formParams.environment2}`;
        if (this.formParams.location)
          this.search += `&location=${this.formParams.location}`;
        if (this.formParams.sensorType1 && !this.formParams.sensorType2)
          this.search += `&senTp=${this.formParams.sensorType1}`;
        else if (this.formParams.sensorType1 && this.formParams.sensorType2)
          this.search += `&pstnNo=${this.formParams.sensorType2}`;
        if (this.formParams.learningType)
          this.search += `&learningType=${this.formParams.learningType}`;
        if (this.formParams.learningType2)
          this.search += `&learningType2=${this.formParams.learningType2}`;

        for (var i = 0; i < this.formParams.move.length; i++) {
          switch (this.formParams.move[i].name) {
            case '사람':
              this.search += `&person=사람`;
              break;
            case '승용차':
              this.search += `&car=승용차`;
              break;
            case '버스':
              this.search += `&bus=버스`;
              break;
            case '트럭':
              this.search += '&truck=트럭';
              break;
            case '오토바이':
              this.search += '&motorCycle=오토바이';
              break;
            case '특수차':
              this.search += '&specialCar=특수차';
              break;
          }
        }

        for (var j = 0; j < this.formParams.fixed.length; j++) {
          switch (this.formParams.fixed[j].name) {
            case '정지선':
              this.search += `&stopLine=정지선`;
              break;
            case '횡단보도':
              this.search += `&crossWalk=횡단보도`;
              break;
            case '방지턱':
              this.search += `&bump=방지턱`;
              break;
            case '공사중':
              this.search += '&construction=트럭';
              break;
            case '신호등':
              this.search += '&traffic=신호등';
              break;
          }
        }
        console.log('search::', this.search);

        this.learningListChange = false;
      }

      const { data } = await getLearningList(this.search, this.formParams.page);
      if (data.length != 0 || nums != 0) {
        this.learningList = this.learningList.concat(data);
        this.learningListChange = true;
        this.formParams.page += 1;
      } else if (data.length == 0) {
        alert('데이터가 없습니다');
      }
      this.searchDialog2 = false;
      console.log('learningList:::', this.learningList);

      return this.learningList;
    },

    changeLearningType() {
      this.learningTypeCodes2 = [];
      this.formParams.learningType2 = '';
      switch (this.formParams.learningType) {
        case 'SEGMENTATION':
          this.learningTypeCodes2 = [
            { name: '전체', value: '' },
            { name: 'Camera_FrontLeft', value: 'Camera01' },
            { name: 'Panorama', value: 'Panorama' },
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
