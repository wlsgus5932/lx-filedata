<template>
  <div style="height: 100%">
    <l-map
      style="height: 100%"
      :zoom="$store.state.zoom"
      :center="center"
      :crs="crs"
      ref="map"
      id="map"
    >
      <l-rectangle
        v-for="(item, i) in $store.state.rectangleList"
        :key="i"
        :bounds="[
          [item.ltud1_crdn_val, item.lngd1_crdn_val],
          [item.ltud2_crdn_val, item.lngd2_crdn_val],
        ]"
        :color="color"
        :weight="weight"
        :fillColor="color"
        @click="
          $store.state.type != 'LDM'
            ? clickZone(item.mpfr_indx_250_val, item.mpfr_indx_val, item)
            : ''
        "
        :options="{
          id250: item.mpfr_indx_250_val,
          id1000: item.mpfr_indx_val,
        }"
      >
      </l-rectangle>
    </l-map>
  </div>
</template>

<script>
import { LMap, LRectangle } from 'vue2-leaflet';
import L from 'leaflet';
import '@/KoreanTmsProviders/lib/proj4-compressed';
import '@/KoreanTmsProviders/lib/proj4';
import '@/KoreanTmsProviders/lib/proj4leaflet';
import '@/KoreanTmsProviders/src/Leaflet.KoreanTmsProviders';
import JSZip from 'jszip';
import { saveAs } from 'file-saver';
import JSZipUtils from 'jszip-utils';
import eventBus from '@/eventBus/eventBus';

export default {
  components: {
    LMap,
    LRectangle,
  },
  mounted() {
    this.onChangeMap('DaumMap');
  },
  data() {
    return {
      group: '',
      color: 'blue',
      weight: 1,
      crs: L.Proj.CRS.Daum,
      indexList: [],
      selectZone: [],
      colorChange: [],
      nestingChecked: false,
    };
  },

  created() {
    eventBus.$on('downloadSelect', data => {
      this.downloadSelect(data);
    });
    eventBus.$on('initSelectZone', () => {
      this.selectZone = [];
    });
    eventBus.$on('nestingChecked', data => {
      this.nestingChecked = data;
    });
  },

  computed: {
    center() {
      return this.$store.state.center;
    },
    url() {
      return this.$store.state.url;
    },
  },

  methods: {
    onChangeMap() {
      this.group = new L.layerGroup();

      L.tileLayer.koreaProvider('DaumMap.Street').addTo(this.group);
      this.zoom = 6;
      this.$nextTick(() => {
        this.group.addTo(this.$refs['map'].mapObject);
      });

      this.$nextTick(() => {
        L.control
          .zoom({ position: 'bottomright' })
          .addTo(this.$refs['map'].mapObject);
      });
    },

    clickZone(data250, data1000) {
      let layer = this.$refs['map'].mapObject._layers;
      if (!data250) {
        for (let i in layer) {
          if (layer[i].options.id1000 == data1000) {
            if (layer[i].options.color == 'blue') {
              layer[i]
                .setStyle({ color: 'red', weight: 4, fillColor: 'red' })
                .bringToFront();

              this.selectZone.push({
                id: layer[i].options.id1000,
              });
            } else {
              layer[i].setStyle({
                color: 'blue',
                weight: 1,
                fillColor: 'blue',
              });
              this.selectZone = this.selectZone.filter(
                item => item.id !== data1000,
              );
            }
          }
        }
      } else if (data250) {
        for (let i in layer) {
          if (layer[i].options.id250 == data250) {
            if (layer[i].options.color == 'blue') {
              layer[i]
                .setStyle({ color: 'red', weight: 4, fillColor: 'red' })
                .bringToFront();

              this.selectZone.push({
                id: layer[i].options.id250,
              });
            } else {
              layer[i].setStyle({
                color: 'blue',
                weight: 1,
                fillColor: 'blue',
              });
              this.selectZone = this.selectZone.filter(
                item => item.id !== data250,
              );
            }
          }
        }
      }
      this.$store.commit('set_selectZone', this.selectZone);
      console.log(this.$store.state.selectZone);
    },

    downloadSelect(data) {
      console.log('selectDAta:::', data);
      this.$nextTick(async () => {
        const files = [];
        data.forEach(item => {
          files.push({
            name: item.img_las_file_nm,
            url: this.url + item.img_las_file_ph + item.img_las_file_nm,
          });
          files.push({
            name: item.img_shp_file_nm,
            url: this.url + item.img_shp_file_ph + item.img_shp_file_nm,
          });
          files.push({
            name: item.dpf_file_nm,
            url: this.url + item.file_path + item.dbf_file_nm,
          });
          files.push({
            name: item.prj_file_nm,
            url: this.url + item.file_path + item.prj_file_nm,
          });
          files.push({
            name: item.sbn_file_nm,
            url: this.url + item.file_path + item.sbn_file_nm,
          });
          files.push({
            name: item.sbx_file_nm,
            url: this.url + item.file_path + item.sbx_file_nm,
          });
          files.push({
            name: item.shp_file_nm,
            url: this.url + item.file_path + item.shp_file_nm,
          });
          files.push({
            name: item.shx_file_nm,
            url: this.url + item.file_path + item.shx_file_nm,
          });
          files.push({
            name: item.json_file_nm,
            url: this.url + item.json_file_ph + item.json_file_nm,
          });

          if (this.nestingChecked) {
            if (
              item.prj_file_e_nm != null &&
              item.prj_file_s_nm != null &&
              item.prj_file_se_nm != null
            ) {
              files.push({
                name: item.prj_file_e_nm,
                url: this.url + item.file_path + item.prj_file_e_nm,
              });
              files.push({
                name: item.prj_file_s_nm,
                url: this.url + item.file_path + item.prj_file_s_nm,
              });
              files.push({
                name: item.prj_file_se_nm,
                url: this.url + item.file_path + item.prj_file_se_nm,
              });
            }

            if (
              item.sbn_file_e_nm != null &&
              item.sbn_file_s_nm != null &&
              item.sbn_file_se_nm != null
            ) {
              files.push({
                name: item.sbn_file_e_nm,
                url: this.url + item.file_path + item.sbn_file_e_nm,
              });
              files.push({
                name: item.sbn_file_s_nm,
                url: this.url + item.file_path + item.sbn_file_s_nm,
              });
              files.push({
                name: item.sbn_file_se_nm,
                url: this.url + item.file_path + item.sbn_file_se_nm,
              });
            }

            if (
              item.sbx_file_e_nm != null &&
              item.sbx_file_s_nm != null &&
              item.sbx_file_se_nm != null
            ) {
              files.push({
                name: item.sbx_file_e_nm,
                url: this.url + item.file_path + item.sbx_file_e_nm,
              });
              files.push({
                name: item.sbx_file_s_nm,
                url: this.url + item.file_path + item.sbx_file_s_nm,
              });
              files.push({
                name: item.sbx_file_se_nm,
                url: this.url + item.file_path + item.sbx_file_se_nm,
              });
            }

            if (
              item.shp_file_e_nm != null &&
              item.shp_file_s_nm != null &&
              item.shp_file_se_nm != null
            ) {
              files.push({
                name: item.shp_file_e_nm,
                url: this.url + item.file_path + item.shp_file_e_nm,
              });
              files.push({
                name: item.shp_file_s_nm,
                url: this.url + item.file_path + item.shp_file_s_nm,
              });
              files.push({
                name: item.shp_file_se_nm,
                url: this.url + item.file_path + item.shp_file_se_nm,
              });
            }

            if (
              item.shx_file_e_nm != null &&
              item.shx_file_s_nm != null &&
              item.shx_file_se_nm != null
            ) {
              files.push({
                name: item.shx_file_e_nm,
                url: this.url + item.file_path + item.shx_file_e_nm,
              });
              files.push({
                name: item.shx_file_s_nm,
                url: this.url + item.file_path + item.shx_file_s_nm,
              });
              files.push({
                name: item.shx_file_se_nm,
                url: this.url + item.file_path + item.shx_file_se_nm,
              });
            }
          }
        });

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
          saveAs(blob, 'SpatialData.zip');
        });
        this.initDialLog2();
      });
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

    initDialLog2() {
      eventBus.$emit('initDialLog2');
    },
  },
};
</script>
