<template>
  <div class="ldm-form">
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
        <v-card-text class="font-weight-bold"> 3D 모델링 </v-card-text>
        <v-btn
          color="secondary"
          @click.stop="dialog = true"
          small
          @click="btnName = '3D 모델링'"
          >다운로드</v-btn
        >
      </v-card>

      <v-card class="cards">
        <v-card-text class="font-weight-bold">
          가상주행영상 + 어노테이션2D
        </v-card-text>
        <v-btn
          color="secondary"
          @click.stop="dialog = true"
          small
          @click="btnName = '가상주행영상 + 어노테이션2D'"
          >다운로드</v-btn
        >
      </v-card>

      <v-card class="cards">
        <v-card-text class="font-weight-bold">
          가상주행영상 + 세그멘테이션
        </v-card-text>
        <v-btn
          color="secondary"
          @click.stop="dialog = true"
          small
          @click="btnName = '가상주행영상 + 세그멘테이션'"
          >다운로드</v-btn
        >
      </v-card>

      <v-card class="cards">
        <v-card-text class="font-weight-bold"> 고정체 정보 </v-card-text>
        <v-btn
          color="secondary"
          @click.stop="dialog = true"
          small
          @click="btnName = '고정체 정보'"
          >다운로드</v-btn
        >
      </v-card>
    </div>

    <div class="text-center">
      <v-dialog v-model="dialog" width="800">
        <v-card width="100%">
          <v-card-title>
            {{ btnName }} 데이터를 다운로드 받으시겠습니까?
          </v-card-title>

          <v-card-text class="font-weight-bold grey--text mb-2">
            파일의 개수 및 사용자의 네트워크 상태에 따라 소요시간이 길어질 수
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
              @click="(dialog = false), (dialog2 = true), downloadChecked()"
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
              {{ btnName }}의 데이터를 다운로드중입니다..
            </v-card-text>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script>
import { getLdmList } from '@/api/index';
import JSZip from 'jszip';
import { saveAs } from 'file-saver';
import JSZipUtils from 'jszip-utils';
import Spinner from '@/components/Spinner/Spinner.vue';

export default {
  mounted() {
    this.initLdmList();
  },

  destroyed() {
    this.$store.commit('set_rectangleList', []);
    this.$store.commit('set_center', [37.315, 126.86]);
  },

  data() {
    return {
      isLoading: false,
      btnLoading: false,
      ldmList: [],
      dialog: false,
      dialog2: false,
      btnName: '',
    };
  },

  components: {
    Spinner,
  },

  methods: {
    async initLdmList() {
      this.isLoading = true;
      const { data } = await getLdmList();
      this.isLoading = false;
      this.$store.commit('set_rectangleList', data);
      this.$store.commit('set_center', {
        lat: data[0].ltud1_crdn_val,
        lng: data[0].lngd1_crdn_val,
      });
      this.ldmList = data;
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

    downloadChecked() {
      this.$nextTick(async () => {
        const files = [];
        if (this.btnName == '3D 모델링') {
          files.push({
            name: '3DS_Models',
            url: '/test/ldm/ldm3ds_models.zip',
          });
        } else if (this.btnName == '가상주행영상 + 어노테이션2D') {
          files.push({
            name: '가상주행영상 + 어노테이션2D',
            url: this.$store.state.url + this.ldmList[0].bounding_box_file_nm,
          });
        } else if (this.btnName == '가상주행영상 + 세그멘테이션') {
          files.push({
            name: '가상주행영상 + 세그멘테이션',
            url: this.$store.state.url + this.ldmList[0].segmentation_file_nm,
          });
        } else {
          files.push({
            name: '고정체 정보',
            url: this.$store.state.url + this.ldmList[0].static_file_nm,
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
          } catch (err) {
            console.log(err);
          }
        }
        await zip.generateAsync({ type: 'blob' }).then(blob => {
          saveAs(blob, 'Ldm-Data.zip');
          this.dialog2 = false;
        });
      });
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

.ldm-form {
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
  width: 100px;
  font-weight: bold;
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
  font-weight: bold;
  height: 100px;
  margin-bottom: 20px;
  background-color: #1111;
  text-align: center;
}
.v-select {
  font-size: 12px;
  font-weight: bold;
}
</style>
