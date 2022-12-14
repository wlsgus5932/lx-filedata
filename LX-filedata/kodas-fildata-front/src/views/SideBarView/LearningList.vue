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
        <p style="width: 120px">
          <v-checkbox
            label="전체선택"
            v-model="checked"
            @click="checked != checked, allChecked()"
            style="margin: 0px 10px; font-weight: bold"
            class="font-weight-bold; white--text"
          ></v-checkbox>
        </p>
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
            <infinite-loading @infinite="infiniteHandler" spinner="spiral" />
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
                @click="sectionModal = true"
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
                @click="randomModal = true"
                small
                >랜덤 다운로드</v-btn
              >
            </v-col>
          </v-row>
          <p v-if="learningList.length > 0">
            total: {{ learningList[0].total_count | comma }}
          </p>
        </div>

        <!-- modal zone -->
        <template class="modal-zone">
          <v-dialog width="500" v-model="randomModal">
            <randomModal @hide="randomModal = false" @show="randomDownload" />
          </v-dialog>

          <v-dialog v-model="choiceModal" persistent width="800">
            <choiceModal
              :title="title"
              :content="content"
              @submit="downloadZip()"
              @hide="choiceModal = false"
            />
          </v-dialog>

          <v-dialog v-model="alertModal" width="600" height="200">
            <alertModal :title="title" :content="content" @hide="hideModal()" />
          </v-dialog>

          <v-dialog v-model="circularModal" persistent width="500">
            <circularModal :content="circularContent" />
          </v-dialog>

          <v-dialog v-model="sectionModal" persistent width="600">
            <sectionModal
              @show="sectionDownload"
              @hide="hideModal"
              :total="learningList.length > 0 ? learningList[0].total_count : 0"
            />
          </v-dialog>
        </template>
        <!-- modal zone end. -->
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
import {
  getLearningList,
  setHistoryData,
  getLearningSectionList,
  getLearningRandomList,
} from '@/api/index';
import choiceModal from '../../components/Modal/choiceModal.vue';
import circularModal from '../../components/Modal/circularModal.vue';
import alertModal from '../../components/Modal/alertModal.vue';
import sectionModal from '../../components/Modal/sectionModal.vue';
import randomModal from '../../components/Modal/randomModal.vue';

export default {
  components: {
    ImageViewer,
    infiniteLoading,
    Spinner,
    choiceModal,
    circularModal,
    alertModal,
    sectionModal,
    randomModal,
  },
  props: ['formParams'],

  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
  data() {
    return {
      historyForm: {
        dataName: [],
        userId: this.$store.state.userId,
        listName: '학습',
      },
      title: '',
      content: '',
      circularContent: '파일을 압축중입니다...',
      viewerData: [],
      checkList: [],
      learningList: [],
      selected: '',
      viewerChange: false,
      checked: false,
      choiceModal: false,
      alertModal: false,
      circularModal: false,
      randomModal: false,
      sectionModal: false,
      countNumber: '',
      search: '',
      rules: [
        value => !!value || '1이상 입력해주세요. 숫자만 입력 가능합니다.',
        value => (value && value.length >= 1) || 'Min 3 characters',
      ],
      textImg: require('../../assets/img/textIcon.png'),
      imageImg: require('../../assets/img/imageIcon.png'),
      isLoading: false,
    };
  },
  computed: {
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

    async sectionDownload(section) {
      this.hideModal();
      this.formParams.startNumber = section.start;
      this.formParams.endNumber = section.end;
      this.circularContent = '압축 할 데이터를 받아오는중입니다...';
      this.circularModal = true;

      const { data } = await getLearningSectionList(this.formParams);
      if (!section.xlsx) {
        await this.downloadZip(data);
      } else {
        this.downloadExcel(data);
      }
    },

    async randomDownload(random) {
      this.hideModal();
      this.formParams.randomNumber = random.randomNum;
      this.circularContent = '압축 할 데이터를 받아오는중입니다...';
      this.circularModal = true;
      const { data } = await getLearningRandomList(this.formParams);
      if (!random.xlsx) {
        await this.downloadZip(data);
      } else {
        this.downloadExcel(data);
      }
    },

    async setHistoryData() {
      try {
        await setHistoryData(JSON.stringify(this.historyForm));
        this.historyForm.dataName = [];
      } catch (err) {
        console.log(err);
      }
    },

    downloadExcel(data) {
      let csv = [];
      let row = [];

      data.forEach(e => {
        row = [];
        row.push(e.path_nm + e.file_nm);
        csv.push(row.join(','));
      });

      this.downloadCSV(csv.join('\n'));
      this.hideModal();
    },

    downloadCSV(csv) {
      const BOM = '\uFEFF';
      csv = BOM + csv;
      let filename = 'LX_learning_data';

      let csvFile = new Blob([csv], { type: 'text/csv;charset=utf-8' });
      let downloadLink = document.createElement('a');
      downloadLink.download = filename;
      downloadLink.href = window.URL.createObjectURL(csvFile);
      downloadLink.style.display = 'none';
      document.body.appendChild(downloadLink);
      downloadLink.click();
      downloadLink.remove();
      this.hideModal();
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

    downloadZip(data) {
      this.hideModal();
      this.circularContent = '파일을 압축중입니다...';
      this.circularModal = true;
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
              this.historyForm.dataName.push(item.file_nm);
            } else {
              files.push({
                name: item.json_file_nm,
                url: this.url + item.json_file_path + item.json_file_nm,
              });
              files.push({
                name: item.image_file_nm,
                url: this.url + item.image_file_path + item.image_file_nm,
              });
              this.historyForm.dataName.push(item.file_nm);
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

              this.historyForm.dataName.push(item.file_nm);
            } else {
              files.push({
                name: item.json_file_nm,
                url: this.url + item.json_file_path + item.json_file_nm,
              });
              files.push({
                name: item.image_file_nm,
                url: this.url + item.image_file_path + item.image_file_nm,
              });

              this.historyForm.dataName.push(item.file_nm);
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
          } catch (err) {
            console.log(err);
          }
        }
        await zip.generateAsync({ type: 'blob' }).then(blob => {
          saveAs(blob, 'LearningData.zip');
        });
        this.setHistoryData();
        this.circularModal = false;
      });
    },

    async infiniteHandler($state) {
      this.formParams.page += 1;
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

    hideModal() {
      this.alertModal = false;
      this.choiceModal = false;
      this.sectionModal = false;
      this.circularModal = false;
      this.randomModal = false;
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
  min-height: 550px;
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
