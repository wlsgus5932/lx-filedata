<template>
  <div class="spatial-form">
    <Spinner v-if="isLoading"></Spinner>
    <div v-if="!isLoading">
      <v-card
        class="text-center white--text font-weight-bold"
        height="30"
        style="margin-bottom: 30px; padding: 3px"
        color="#757575"
        >{{ $store.state.type }}
      </v-card>

      <v-card class="cards">
        <v-col cols="11">
          <v-select
            :items="indexRatioCodes"
            item-text="name"
            item-value="value"
            v-model="formParams.indexRatio"
            label="도곽 비율"
            dense
            prepend-icon="mdi-map-marker"
          ></v-select>
        </v-col>
      </v-card>

      <v-card class="cards">
        <v-col cols="11">
          <v-select
            :items="locationCodes"
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
            :items="dataTypeCodes"
            item-text="name"
            item-value="value"
            v-model="formParams.dataType"
            label="데이터 종류"
            dense
            prepend-icon="mdi-map-marker"
          ></v-select>
        </v-col>
      </v-card>

      <v-card class="cards">
        <v-col cols="11">
          <v-select
            :items="targetTypeCodes"
            item-text="name"
            item-value="value"
            v-model="formParams.targetType"
            label="대상 종류"
            dense
            prepend-icon="mdi-map-marker"
          ></v-select>
        </v-col>
      </v-card>

      <v-card class="cards">
        <v-col cols="11">
          <v-select
            v-model="formParams.faceted"
            :items="faceted"
            item-text="name"
            item-value="value"
            label="면형 이미지"
            dense
            prepend-icon="mdi-map-marker"
          ></v-select>
        </v-col>
      </v-card>

      <v-card class="cards" style="height: 50px">
        <v-checkbox
          style="margin: 2px 11px"
          label="50% 중첩 여부"
          v-on:change="initNestingChecked"
          v-model="nesting"
        >
        </v-checkbox>
      </v-card>

      <div style="float: right">
        <v-btn
          class="secondary font-weight-bold"
          @click.stop="dialog = true"
          style="margin: 10px 0px 5px; float: right; width: 120px"
          >검색</v-btn
        >
        <div>
          <v-btn
            @click="
              $store.state.rectangleList.length
                ? (downloadDialog = true)
                : showAlert()
            "
            style="margin: 10px 0px 50px"
            class="primary font-weight-bold"
            >도곽다운로드</v-btn
          >
        </div>
      </div>
    </div>

    <div class="text-center">
      <!-- 도-->
      <v-dialog v-model="downloadDialog" width="800">
        <v-card width="100%">
          <v-card-title>
            선택하신 도곽의 데이터를 다운로드 하시겠습니까?
          </v-card-title>

          <v-card-text class="font-weight-bold grey--text mb-2">
            도곽 데이터의 개수 및 사용자의 네트워크 상태에 따라 소요시간이
            길어질 수 있습니다. (평균 1분 미만)
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn color="green darken-1" text @click="downloadDialog = false">
              취소
            </v-btn>

            <v-btn
              color="green darken-1"
              text
              @click="
                ((downloadDialog = false), (dialog2 = true)),
                  downloadSelectZone()
              "
            >
              확인
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <!-- 검색 다이얼로그 -->
      <v-dialog v-model="dialog" width="800">
        <v-card width="100%">
          <v-card-title>
            도곽 비율 {{ this.formParams.indexRatio }} : 1로 검색하시겠습니까?
          </v-card-title>

          <v-card-text class="font-weight-bold grey--text mb-2">
            도곽의 개수 및 사용자의 네트워크 상태에 따라 소요시간이 길어질 수
            있습니다. (평균 1분 미만)
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn color="green darken-1" text @click="dialog = false">
              취소
            </v-btn>

            <v-btn
              color="green darken-1"
              text
              @click="((dialog = false), (dialog2 = true)), submitForm(0)"
            >
              확인
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog v-model="dialog2" width="500" persistent class="justify-center">
        <v-card width="100%" height="8%">
          <v-card-actions>
            <v-progress-circular
              :size="30"
              color="primary"
              indeterminate
            ></v-progress-circular>

            <v-card-text class="font-weight-bold grey--text">
              도곽의 인덱스들을 받아오는중입니다..
            </v-card-text>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <!-- 설명 dialog -->
      <v-dialog v-model="menuDialog" width="800" class="text-center">
        <v-card>
          <v-card-title
            class="text-h5 grey lighten-2"
            style="text-align: center"
          >
            도움말
          </v-card-title>

          <v-card-text class="font-weight-bold">
            <v-card>
              1. 노란 박스안 검색조건들 설정 후 검색 버튼 클릭
              <v-img
                src="@/assets/img/공간메뉴얼.png"
                style="width: 400px"
              ></v-img>
            </v-card>
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="menuDialog = false">
              확인
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script>
import { getSpatialList, getSelectZoneList } from '@/api/index';
import Spinner from '@/components/Spinner/Spinner.vue';
import eventBus from '@/eventBus/eventBus';

export default {
  components: { Spinner },

  destroyed() {
    this.$store.commit('set_rectangleList', []);
  },

  created() {
    eventBus.$on('initDialLog2', () => (this.dialog2 = false));
  },

  data() {
    return {
      isLoading: false,
      spatialList: [],
      dialog: false,
      dialog2: false,
      downloadDialog: false,
      menuDialog: false,
      formParams: {
        location: '',
        dataType: '',
        targetType: '',
        indexRatio: '250',
        faceted: '',
        randomSearchCnt: null,
        page: 1,
      },
      nesting: '',
      dataList: [],
      search: '',
      spatialListChange: false,

      faceted: [
        { name: '포함', value: '포함' },
        { name: '미포함', value: '미포함' },
      ],

      locationCodes: [
        { name: '전체', value: '' },
        { name: '안산', value: '안산' },
        { name: '판교', value: '판교' },
        { name: '전주', value: '전주' },
        { name: '제주', value: '제주' },
      ],
      dataTypeCodes: [
        { name: '전체', value: '' },
        { name: 'LAS', value: 'LAS' },
        { name: '정사영상', value: '정사영상' },
      ],

      targetTypeCodes: [
        { name: '전체', value: '' },
        { name: '노면선표시', value: '노면선표시' },
        { name: '차량방호안전시설', value: '차량방호안전시설' },
        { name: '안전표지', value: '안전표지' },
        { name: '노면표시', value: '노면표시' },
        { name: '신호등', value: '신호등' },
        { name: '과속방지턱', value: '과속방지턱' },
        { name: '지주', value: '지주' },
        { name: '건물', value: '건물' },
      ],

      indexRatioCodes: [
        { name: '250:1', value: '250' },
        { name: '1000:1', value: '1000' },
      ],
    };
  },
  methods: {
    async submitForm() {
      await this.initSelectZone();
      this.search = '';

      if (this.formParams.location)
        this.search += `&location=${this.formParams.location}`;
      if (this.formParams.dataType)
        this.search += `&dataType=${this.formParams.dataType}`;
      if (this.formParams.targetType)
        this.search += `&targetType=${this.formParams.targetType}`;
      if (this.formParams.indexRatio)
        this.search += `&indexRatio=${this.formParams.indexRatio}`;
      if (this.formParams.randomSearchCnt)
        this.search += `&randomSearchCnt=${this.formParams.randomSearchCnt}`;

      this.getData();
    },

    async getData() {
      const { data } = await getSpatialList(this.search);
      if (data.length != 0) {
        this.duplicateCheck(data);
      } else if (data.length == 0) {
        alert('데이터가 없습니다');
        this.dialog2 = false;
      }
    },

    duplicateCheck(data) {
      if (this.formParams.indexRatio == '1000') {
        this.dataList = data.filter(
          (item, index, item2) =>
            index ===
            item2.findIndex(t => t.mpfr_indx_val === item.mpfr_indx_val),
        );
      } else {
        this.dataList = data.filter(
          (item, index, item2) =>
            index ===
            item2.findIndex(
              t => t.mpfr_indx_250_val === item.mpfr_indx_250_val,
            ),
        );
      }
      this.$store.commit('set_center', {
        lat: this.dataList[0].ltud1_crdn_val,
        lng: this.dataList[0].lngd1_crdn_val,
      });
      this.$store.dispatch('set_rectangleList', this.dataList);
      this.$store.commit('set_indexRatio', this.formParams.indexRatio);
      this.dialog2 = false;
    },

    async downloadSelectZone() {
      let indexArr = [];
      let id = this.$store.state.selectZone;
      let url = '';
      if (id.length > 0) {
        id.forEach(item => {
          indexArr.push(item.id);
        });

        if (indexArr.length > 0) {
          url = this.search + `&indexValue=${indexArr}`;
        }

        const { data } = await getSelectZoneList(url);
        this.downloadSelect(data);
      } else {
        alert('도곽이 선택되지 않았습니다');
        this.dialog2 = false;
      }
    },

    downloadSelect(data) {
      eventBus.$emit('downloadSelect', data);
    },

    initSelectZone() {
      eventBus.$emit('initSelectZone');
    },

    initNestingChecked() {
      eventBus.$emit('nestingChecked', this.nesting);
    },

    showAlert() {
      alert('도곽을 선택해주세요.');
    },
  },
};
</script>

<style scoped>
.linear-class {
  position: fixed;
  left: 50%;
  top: 50%;
  background-color: white;
  width: 400px;
  height: 100px;
}

.spatial-form {
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

.v-btn {
  width: 120px;
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
