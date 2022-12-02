<template>
  <v-card style="width: 100%; height: 100%">
    <v-card v-if="viewer == 'sensor'">
      <v-img
        v-if="list.sen_tp == 'CAMERA'"
        width="100%"
        height="400px"
        :src="urls + list.path_nm + list.file_nm"
      >
        <!-- :src="urls + list.path_nm + list.file_nm" -->
        <template v-slot:placeholder>
          <v-row class="fill-height ma-0" align="center" justify="center">
            <v-progress-circular
              indeterminate
              color="grey lighten-5"
            ></v-progress-circular>
          </v-row> </template
      ></v-img>

      <v-img
        v-if="list.sen_tp != 'CAMERA'"
        width="100%"
        height="400px"
        :src="textImg"
      >
        <!-- :src="urls + list.path_nm + list.file_nm" -->
        <template v-slot:placeholder>
          <v-row class="fill-height ma-0" align="center" justify="center">
            <v-progress-circular
              indeterminate
              color="grey lighten-5"
            ></v-progress-circular>
          </v-row> </template
      ></v-img>
    </v-card>

    <v-card v-if="viewer == 'learning'">
      <v-img
        width="100%"
        height="300px"
        :src="this.urls + list.image_file_path + list.image_file_nm"
        aspect-ratio="1"
        class="grey lighten-2"
      >
        <!-- :src="urls + list.imag_file_path + list.imag_file_nm" -->
        <template v-slot:placeholder>
          <v-row class="fill-height ma-0" align="center" justify="center">
            <v-progress-circular
              indeterminate
              color="grey lighten-5"
            ></v-progress-circular>
          </v-row>
        </template>
      </v-img>
      <!-- v-if="list.data_type == 'SEGMENTATION'" -->
      <v-img
        width="100%"
        height="300px"
        :src="
          this.urls +
          list.labl_mask_imag_file_path +
          list.labl_mask_imag_file_nm
        "
      />
    </v-card>
    <!-- :src=" urls + list.labl_mask_imag_file_path + list.labl_mask_imag_file_nm
      " -->

    <v-card-text>
      <!-- <v-card-title>{{ $store.state.type }} 주요 정보</v-card-title> -->
      <v-card-subtitle>
        <strong>파일이름 </strong> : {{ list.file_nm }} <br />
        <strong>취득일 </strong> : {{ list.oprt_ymd }} <br />
        <strong>지역 </strong> : {{ list.oprt_area_nm }} <br />
        <strong>센서종류 </strong> : {{ list.sen_tp }} <br />
        <strong>데이터타입 </strong> : {{ list.data_type }} <br />
      </v-card-subtitle>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: 'ImageViewer',
  props: ['list', 'viewer'],
  data: function () {
    return {
      testList: [{}],
      urls: 'http://124.194.100.230:10002',
      textImg: require('../assets/img/textIcon.png'),
    };
  },
  computed: {},
  methods: {},
};
</script>

<style scoped></style>
