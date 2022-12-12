<template>
  <div class="learning-list">
    <div
      v-if="isLoading"
      style="height: 100%; text-align: center; padding-top: 50%"
    >
      <span style="margin-top: 600px">
        <Spinner></Spinner>
      </span>
      <h3>목록을 검색중입니다.</h3>
    </div>
    <template v-else>
      <div class="header">
        <v-checkbox
          label="전체선택"
          v-model="checked"
          @click="checked != checked, allChecked()"
          style="margin: 0px 10px; font-weight: bold"
          class="font-weight-bold; white--text"
        ></v-checkbox>
      </div>
      <div class="headers-div" style="margin-top: 30px; margin-bottom: 50px">
        <v-container style="background-color: #1111">
          <v-row>
            <v-col
              v-for="(item, i) in learningList"
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
                      v-if="item.data_type == 'CUBOID'"
                      @mouseover="setViewerData(item)"
                      @mouseout="viewerChange = false"
                      aspect-ratio="1"
                      class="grey lighten-2"
                      :src="textImg"
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

                    <v-img
                      v-else
                      @mouseover="setViewerData(item)"
                      @mouseout="viewerChange = false"
                      aspect-ratio="1"
                      class="grey lighten-2"
                      :src="imageImg"
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
            <infinite-loading @infinite="infiniteHandler" spinner="spiral">
              <span
                slot="no-more"
                style="
                  color: rgb(102, 102, 102);
                  font-size: 14px;
                  padding: 10px 0px;
                "
              >
                목록의 끝입니다
              </span>
            </infinite-loading>
          </div>

          <div class="viewer" v-if="viewerChange">
            <ImageViewer :list="viewerData" :viewer="'learning'"></ImageViewer>
          </div>
        </v-container>
        <div class="footers" style="padding: 10px">
          <v-row>
            <v-col cols="4">
              <v-btn
                color="secondary white--text font-weight-bold"
                width="140"
                small
                height="40"
                @click.stop="
                  (dialog = true),
                    (downloadAll = true),
                    (checked = true),
                    allCheckedBtn()
                "
                >전체 다운로드</v-btn
              >
            </v-col>
            <v-col cols="4">
              <v-btn
                color="success white--text font-weight-bold"
                width="140"
                height="40"
                dark
                small
                @click.stop="(dialog = true), (download = true)"
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
                >수량 다운로드</v-btn
              >
            </v-col>
          </v-row>
          <p v-if="learningList.length > 0">
            total: {{ learningList[0].total_count | comma }}
          </p>
        </div>

        <div class="text-center">
          <!-- 다운로드 다이얼로그 -->
          <v-dialog v-model="dialog" width="800">
            <v-card width="100%">
              <v-card-title>
                <!-- 선택된 {{ checkList.length }}개의 파일을 다운로드 하시겠습니까? -->
              </v-card-title>

              <v-card-text class="font-weight-bold grey--text mb-2">
                파일의 개수 및 사용자의 네트워크 상태에 따라 소요시간이 길어질
                수 있습니다. (평균 10분 미만)
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn color="green darken-1" text @click="dialog = false">
                  취소
                </v-btn>

                <v-btn
                  color="green darken-1"
                  text
                  @click="
                    ((dialog = false), (dialog2 = true)),
                      download ? downloadChecked() : downloadAllChecked()
                  "
                >
                  확인
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

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
                  @click="(countDialog = false), (dialog2 = true), countNum()"
                >
                  다운로드
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <v-dialog
            v-model="dialog2"
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
                  파일을 압축중입니다...
                </v-card-text>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </div>
    </template>
  </div>
</template>

<script>
import ImageViewer from '../ImageViewer.vue';
import JSZip from 'jszip';
import Spinner from '@/components/Spinner/Spinner.vue';
import { saveAs } from 'file-saver';
import JSZipUtils from 'jszip-utils';
import infiniteLoading from 'vue-infinite-loading';
import { getLearningList } from '@/api/index';
import axios from 'axios';

export default {
  components: { ImageViewer, infiniteLoading, Spinner },
  props: ['list', 'formParams'],

  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  data() {
    return {
      isLoading: false,
      learningList: '',
      textImg: require('../../assets/img/textIcon.png'),
      imageImg: require('../../assets/img/imageIcon.png'),

      viewerData: [],
      checkList: [],
      selected: '',
      viewerChange: false,
      dialog: false,
      dialog2: false,
      download: false,
      downloadAll: false,
      checked: false,
      countDialog: false,
      countNumber: '',
      search: '',
      rules: [
        value => !!value || '1이상 입력해주세요. 숫자만 입력 가능합니다.',
        value => (value && value.length >= 1) || 'Min 3 characters',
      ],
      // url: 'http://124.194.100.230:10002',
    };
  },
  computed: {
    totalCount() {
      return this.list[0].total_count;
    },
    url() {
      return this.$store.state.url;
    },
  },

  methods: {
    async submitForm() {
      this.isLoading = true;
      try {
        const { data } = await getLearningList(this.formParams);
        if (data) {
          this.learningList = data;
          this.isLoading = false;
        } else {
          this.$emit('hide');
        }
      } catch (err) {
        this.$emit('hide');
      }
    },
    setViewerData(data) {
      this.viewerChange = true;
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

    downloadChecked(data) {
      this.$nextTick(async () => {
        const files = [];
        if (!data) {
          this.checkList.forEach(item => {
            if (item.data_type == 'SEGMENTATION') {
              files.push({
                name: item.image_file_nm,
                url: this.url + item.image_file_path + item.image_file_nm,
              });

              files.push({
                name: item.labl_mask_imag_file_nm,
                url:
                  this.url +
                  item.labl_mask_imag_file_path +
                  item.labl_mask_imag_file_nm,
              });

              files.push({
                name: item.insn_mask_imag_file_nm,
                url:
                  this.url +
                  item.insn_mask_imag_file_path +
                  item.insn_mask_imag_file_nm,
              });

              files.push({
                name: item.json_file_nm,
                url: this.url + item.json_file_path + item.json_file_nm,
              });
            } else {
              files.push({
                name: item.json_file_nm,
                url: this.url + item.json_file_path + item.json_file_nm,
              });

              files.push({
                name: item.image_file_nm,
                url: this.url + item.image_file_path + item.image_file_nm,
              });
            }
          });
        } else {
          data.forEach(item => {
            if (item.data_type == 'SEGMENTATION') {
              files.push({
                name: item.image_file_nm,
                url: this.url + item.image_file_path + item.image_file_nm,
              });

              files.push({
                name: item.labl_mask_imag_file_nm,
                url:
                  this.url +
                  item.labl_mask_imag_file_path +
                  item.labl_mask_imag_file_nm,
              });

              files.push({
                name: item.insn_mask_imag_file_nm,
                url:
                  this.url +
                  item.insn_mask_imag_file_path +
                  item.insn_mask_imag_file_nm,
              });

              files.push({
                name: item.json_file_nm,
                url: this.url + item.json_file_path + item.json_file_nm,
              });
            } else {
              files.push({
                name: item.json_file_nm,
                url: this.url + item.json_file_path + item.json_file_nm,
              });

              files.push({
                name: item.image_file_nm,
                url: this.url + item.image_file_path + item.image_file_nm,
              });
            }
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
            let requestData = `&listNm=학습&dataNm=${item.file_nm}&utlzUserId=${this.loginUserId}`;
            axios.post(`/rest/api/historyList?${requestData}`);
            // this.setReportData(item.fileNm);
          } catch (err) {
            console.log(err);
          }
        }
        await zip.generateAsync({ type: 'blob' }).then(blob => {
          saveAs(blob, 'LearningData.zip');
          this.dialog2 = false;
          this.download = false;
        });
      });
    },

    // downloadChecked() {
    //   this.$nextTick(async () => {
    //     const files = [];
    //     this.checkList.forEach(item => {
    //       files.push({
    //         name: item.image_file_nm,
    //         url: this.url + `test/camera/` + item.image_file_nm,
    //       });

    //       files.push({
    //         name: item.labl_mask_imag_file_nm,
    //         url: this.urls + `test/label/` + item.labl_mask_imag_file_nm,
    //       });

    //       files.push({
    //         name: item.insn_mask_imag_file_nm,
    //         url: this.urls + `test/instance/` + item.insn_mask_imag_file_nm,
    //       });

    //       files.push({
    //         name: item.json_file_nm,
    //         url: this.urls + `test/json/` + item.json_file_nm,
    //       });
    //     });

    //     const fileset = new Set();
    //     for (const item of files) {
    //       fileset.add(item);
    //     }
    //     const zip = new JSZip();
    //     for (const item of fileset) {
    //       try {
    //         const binary = await this.urlToPromise(item.url);
    //         zip.file(item.name, binary, { binary: true });
    //         // this.setReportData(item.fileNm);
    //       } catch (err) {
    //         console.log(err);
    //       }
    //     }
    //     await zip.generateAsync({ type: 'blob' }).then(blob => {
    //       saveAs(blob, 'SensorData.zip');
    //       this.dialog2 = false;
    //     });
    //   });
    // },

    async infiniteHandler($state) {
      const { data } = await getLearningList(this.formParams);
      this.learningList = this.learningList.concat(data);
      console.log(data.length);
      if (data.length < 49) {
        $state.complete();
      } else {
        $state.loaded();
      }
    },

    allChecked() {
      if (this.checked) {
        for (let i in this.list) {
          this.checkList.push(this.list[i]);
        }
      } else {
        this.checkList = [];
      }
    },

    async countNum() {
      this.search = '';
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
      if (this.formParams.learningType)
        this.search += `&learningType=${this.formParams.learningType}`;
      this.search += `&countNumber=${this.countNumber}`;

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

      const { data } = await getLearningList(this.search, 1);
      this.downloadChecked(data);
      console.log(data);
    },

    allCheckedBtn() {
      this.checkList = [];
      for (let i in this.list) {
        this.checkList.push(this.list[i]);
      }
    },

    downloadAllChecked() {
      this.downloadAll = false;
      this.downloadChecked();
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
}

.learning-list {
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
  margin-top: 20px;
  height: 800px;
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
}
</style>
