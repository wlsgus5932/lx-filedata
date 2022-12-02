<template>
  <div class="sensor-list">
    <div
      v-if="isLoading"
      style="height: 100%; text-align: center; padding-top: 400px"
    >
      <span style="margin-top: 600px">
        <Spinner></Spinner>
      </span>
      <h4>목록을 가져오는중입니다. <br />잠시만 기다려주세요.</h4>
    </div>
    <template v-else-if="!isLoading">
      <div class="header">
        <p style="width: 120px">
          <v-checkbox
            label="전체선택"
            v-model="checked"
            @click="checked != checked, allChecked()"
            style="margin: 0px 10px; font-weight: bold"
            class="font-weight-bold; white--text;"
          ></v-checkbox>
        </p>
      </div>
      <div class="headers-div" style="margin-top: 30px; margin-bottom: 50px">
        <v-container style="background-color: #1111">
          <v-row>
            <v-col
              v-for="(item, i) in sensorList"
              :key="i"
              class="d-flex child-flex"
              cols="4"
            >
              <v-hover v-slot="{ hover }" open-delay="200">
                <label :for="i">
                  <v-card
                    :elevation="hover ? 16 : 1"
                    :class="{ 'on-hover': hover }"
                    id="contents"
                  >
                    <input
                      type="checkbox"
                      name=""
                      :id="i"
                      style=""
                      v-model="checkList"
                      :value="item"
                    />

                    <v-img
                      v-if="item.sen_tp == 'CAMERA'"
                      @mouseover="setViewerData(item), (viewerChange = true)"
                      @mouseout="viewerChange = false"
                      aspect-ratio="1"
                      class="grey lighten-2"
                      :src="textImg"
                    >
                      <!-- :src="url + item.path_nm + item.file_nm" -->
                      <template v-slot:placeholder>
                        <v-row
                          class="fill-height ma-0"
                          align="center"
                          justify="center"
                        >
                          <v-progress-circular
                            indeterminate
                            color="grey lighten-5"
                          ></v-progress-circular>
                        </v-row>
                      </template>
                    </v-img>

                    <v-img
                      v-if="item.sen_tp != 'CAMERA'"
                      @mouseover="setViewerData(item), (viewerChange = true)"
                      @mouseout="viewerChange = false"
                      :src="textImg"
                      aspect-ratio="1"
                      class="grey lighten-2"
                    >
                      <template v-slot:placeholder>
                        <v-row
                          class="fill-height ma-0"
                          align="center"
                          justify="center"
                        >
                          <v-progress-circular
                            indeterminate
                            color="grey lighten-5"
                          ></v-progress-circular>
                        </v-row>
                      </template>
                    </v-img>

                    <p class="ellipsis" style="z-index: 1; font-weight: bold">
                      {{ i + 1 }}. {{ item.file_nm }}
                    </p>
                  </v-card>
                </label>
              </v-hover>
            </v-col>
          </v-row>

          <div style="height: 120px">
            <infinite-loading @infinite="infiniteHandler" spinner="spiral" />
          </div>

          <div class="viewer" v-if="viewerChange">
            <ImageViewer :list="viewerData" :viewer="'sensor'"></ImageViewer>
          </div>
        </v-container>
        <v-card class="footers" style="padding: 10px">
          <v-row>
            <v-col cols="4">
              <v-btn
                color="secondary white--text font-weight-bold"
                width="140"
                small
                height="40"
                @click="choiceModal = true"
                >구간 다운로드</v-btn
              >
            </v-col>
            <v-col cols="4">
              <v-btn
                color="success white--text font-weight-bold"
                width="140"
                height="40"
                dark
                small
                @click="submitChoiceBtn()"
                depressed
              >
                선택 다운로드
              </v-btn>
            </v-col>
            <v-col cols="4">
              <v-btn
                height="40"
                width="140"
                color="error white--text font-weight-bold"
                @click="countDialog = true"
                small
                >랜덤 다운로드</v-btn
              >
            </v-col>
          </v-row>
          <p v-if="sensorList.length > 0">
            total: {{ sensorList[0].total_count | comma }}
          </p>
        </v-card>

        <!-- 수량다운로드 다이얼로그-->
        <v-dialog v-model="countDialog" width="500">
          <v-card>
            <v-card-title class="text-h5 grey lighten-2">
              수량 다운로드
            </v-card-title>

            <v-card-text>
              <v-form ref="form" lazy-validation>
                <v-text-field
                  v-model="countNumber"
                  label="개수"
                  :rules="rules"
                  hide-details="auto"
                  type="number"
                ></v-text-field>
                <v-card-text>
                  원하는 수량 입력 후 입력 수만큼 다운로드 받을 수 있습니다.
                </v-card-text>
              </v-form>
            </v-card-text>

            <v-divider></v-divider>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="primary"
                text
                @click="
                  (countDialog = false), (circularModal = true), countNum()
                "
              >
                download
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>

      <!-- modal zone -->
      <template class="modal-zone">
        <v-dialog width="800"> </v-dialog>

        <v-dialog v-model="choiceModal" persistent width="800">
          <choiceModal
            :title="title"
            :content="content"
            @submit="
              ((choiceModal = false), (circularModal = true)), downloadZip()
            "
            @hide="choiceModal = false"
          >
          </choiceModal>
        </v-dialog>

        <v-dialog v-model="alertModal" width="600" height="200">
          <alertModal
            :title="title"
            :content="content"
            @hide="hideModal()"
          ></alertModal>
        </v-dialog>

        <v-dialog v-model="circularModal" persistent width="500">
          <circularModal :content="circularContent"></circularModal>
        </v-dialog>
      </template>
      <!-- modal zone end. -->
    </template>
  </div>
</template>

<script>
import ImageViewer from '../ImageViewer.vue';
import JSZip from 'jszip';
import { saveAs } from 'file-saver';
import JSZipUtils from 'jszip-utils';
import infiniteLoading from 'vue-infinite-loading';
// import { getSensorList } from '@/api/index';
import { getSensorList } from '@/api/index';
import axios from 'axios';
import choiceModal from '../../components/Modal/choiceModal.vue';
import circularModal from '../../components/Modal/circularModal.vue';
import alertModal from '../../components/Modal/alertModal.vue';
import Spinner from '@/components/Spinner/Spinner.vue';

export default {
  components: {
    ImageViewer,
    infiniteLoading,
    choiceModal,
    circularModal,
    alertModal,
    Spinner,
  },
  props: ['list', 'formParams'],

  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },

  data() {
    return {
      title: '',
      content: '',
      circularContent: '파일을 압축중입니다...',
      viewerData: [],
      checkList: [],
      sensorList: [],
      selected: '',
      viewerChange: false,
      checked: false,
      choiceModal: false,
      alertModal: false,
      circularModal: false,
      countDialog: false,
      countNumber: '',
      search: '',
      rules: [
        value => !!value || '1이상 입력해주세요. 숫자만 입력 가능합니다.',
        value => (value && value.length >= 1) || 'Min 3 characters',
      ],
      // url: 'http://124.194.100.230:10002',
      textImg: require('../../assets/img/text.png'),
      imageImg: require('../../assets/img/image.png'),
      isLoading: false,
    };
  },

  mounted() {
    // this.sensorList.concat(this.list);
    // console.log('list:::', this.list);
    this.isLoading = true;
    console.log('formparams:::', this.formParams);
    // this.submitForm2();
    // this.submitInfiniteHandler();
    // console.log('page:::', this.formParams.page);
  },

  computed: {
    // totalCount() {
    //   return this.sensorList[0].total_count;
    // },
    url() {
      return this.$store.state.url;
    },
  },

  methods: {
    setViewerData(data) {
      this.viewerData = data;
    },

    urlToPromise(url) {
      return new Promise((resolve, reject) => {
        JSZipUtils.getBinaryContent(url, (err, data) => {
          if (err) {
            reject(err);
          } else {
            resolve(data);
          }
        });
      });
    },

    downloadZip(data) {
      this.$nextTick(async () => {
        const files = [];
        if (!data) {
          this.checkList.forEach(item => {
            files.push({
              name: item.file_nm,
              url: this.url + item.path_nm + item.file_nm,
            });
          });
        } else {
          data.forEach(item => {
            files.push({
              name: item.file_nm,
              url: this.url + item.path_nm + item.file_nm,
            });
          });
        }

        const fileset = new Set();
        for (const item of files) {
          fileset.add(item);
        }
        const zip = new JSZip();
        for (const item of fileset) {
          try {
            const binary = await this.urlToPromise(item.url);
            zip.file(item.name, binary, { binary: true });
            // this.setReportData(item.fileNm);
            let requestData = `&listNm=센서&dataNm=${item.name}&utlzUserId=${this.loginUserId}`;
            axios.post(`/rest/api/historyList?${requestData}`);
          } catch (err) {
            console.log(err);
          }
        }
        await zip.generateAsync({ type: 'blob' }).then(blob => {
          this.content = '최종 압축중입니다. 잠시만 기다려주세요.';
          saveAs(blob, 'SensorData.zip');
          this.circularModal = false;
        });
      });
    },

    async infiniteHandler($state) {
      console.log('infiniteHandler');
      this.formParams.page += 1;
      const { data } = await getSensorList(JSON.stringify(this.formParams));
      this.sensorList = this.sensorList.concat(data);
      console.log(this.sensorList);
      if (data.length < 49) {
        $state.complete();
      } else {
        $state.loaded();
      }
    },

    allChecked() {
      this.checkList = [];
      if (this.checked) {
        for (let i in this.list) {
          this.checkList.push(this.list[i]);
        }
      }
    },

    submitChoiceBtn() {
      if (this.checkList.length <= 0) {
        this.title = '선택 된 파일이 없습니다.';
        this.content = '다운로드 할 파일을 1개 이상 선택 후 다시 시도해주세요.';
        this.alertModal = true;
      } else {
        this.title = `${this.checkList.length}개의 파일을 다운로드 하시겠습니까?`;
        this.content =
          '파일의 개수 및 사용자의 네트워크 상태에 따라 소요시간이 길어질 수 있습니다.';
        this.choiceModal = true;
        this.download = true;
      }
    },

    async submitForm() {
      this.isLoading = true;
      try {
        const { data } = await getSensorList(JSON.stringify(this.formParams));
        this.$emit('success');
        this.sensorList = data;
        this.isLoading = false;
      } catch (err) {
        this.title = '데이터가 없습니다.';
        this.content = '검색하신 데이터가 없습니다.';
        this.alertModal = true;
      }
    },

    setReportData(fileName) {
      console.log('진입ddddddddd');
      let requestData = `&entity.listNm=${fileName}&entity.dataNm=센서&entity.utlzUserId=${this.loginUserId}`;
      const { data } = axios.post(`/rest/api/historyList/${requestData}`);
      console.log(data);
    },

    hideModal() {
      this.alertModal = false;
      this.choiceModal = false;
      this.circularModal = false;
    },
  },
};
</script>

<style scoped>
.header {
  height: 30px;
  position: fixed;
  background-color: #eee;
  width: 485px;
  margin-right: -10px;
  z-index: 3;
}

.sensor-list {
  width: 500px;
  left: 390px;
  top: 0px;
  position: fixed;
  background-color: white;
  height: 100%;
  border: 1px solid #ddd;
  overflow-x: hidden;
}

.viewer {
  /* text-align: center; */
  margin-top: 20px;
  height: 600px;
  width: 700px;
  position: fixed;
  top: 0;
  left: 895px;
  overflow-x: hidden;
  background-color: white;
  box-shadow: 4px 11px 8px 0px rgba(34, 60, 80, 0.2);
  -webkit-box-shadow: 4px 11px 8px 0px rgba(34, 60, 80, 0.2);
  -moz-box-shadow: 4px 11px 8px 0px rgba(34, 60, 80, 0.2);
  background-color: rgb(255, 255, 255);
}

#contents {
  cursor: pointer;
}

.ellipsis {
  width: 120px;
  font-size: 12px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; /* 말줄임 적용 */
}

input[type='checkbox'] {
  zoom: 1.3;
  float: left;
  position: absolute;
  z-index: 1;
  left: 0;
}

.footers {
  position: fixed;
  text-align: right;
  font-weight: bold;
  background-color: #d4d3d3;
  bottom: 0;
  height: 80px;
  width: 485px;
  margin-right: -10px;
  z-index: 3;
}
</style>
