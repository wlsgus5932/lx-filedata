<template>
  <v-card width="100%">
    <v-card-title class="text-h5 grey lighten-2"> 구간 다운로드 </v-card-title>
    <br />
    <v-card-text class="font-weight-bold">
      <p>
        시작범위와 종료 범위를 입력 후 <br />원하는 데이터의 범위를 설정할 수
        있습니다. <br /><br />파일의 개수 및 사용자의 네트워크 상태에 따라
        <br />소요시간이 길어질 수 있습니다.
      </p>
      <v-row>
        <v-col cols="6" md="6">
          <v-text-field
            placeholder="1"
            v-model="section.start"
            :rules="startNumberRules"
            label="시작구간"
            type="number"
            :maxlength="String(total).length"
            min="1"
            required
            oninput="javascript: this.value = this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' );
            if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
          ></v-text-field>
        </v-col>

        <v-col cols="6" md="6">
          <v-text-field
            v-model="section.end"
            label="종료구간"
            type="number"
            min="2"
            :placeholder="String(total)"
            :maxlength="String(total).length"
            :rules="endNumberRules"
            oninput="
            this.value = this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ); 
            if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-checkbox
        class="checkbox"
        v-model="section.xlsx"
        label="데이터를 목록(엑셀 형식)으로 다운로드 받기"
        color="primary"
        hide-details
      ></v-checkbox>
    </v-card-text>

    <v-card-actions>
      <v-spacer />
      <v-btn color="green darken-1" text @click="hideMe()"> 취소 </v-btn>
      <v-btn
        color="green darken-1"
        text
        @click="$emit('show', section)"
        :disabled="check"
      >
        <!-- section.start < 1 || section.end < 2 || section.start >= section.end -->
        확인
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      check: false,
      checked: false,
      startNumberRules: [v => this.startCheck(v)],
      endNumberRules: [v => this.endCheck(v)],
      section: {
        start: 1,
        end: 2,
        xlsx: false,
      },
    };
  },
  props: {
    total: Number,
  },
  methods: {
    hideMe() {
      this.$emit('hide');
      this.section.start = 1;
      this.section.end = 2;
      this.section.xlsx = false;
    },
    startCheck() {
      let start = Number(this.section.start);
      let end = Number(this.section.end);
      if (start < 1) {
        this.check = true;
        return '숫자 1이상 입력해주세요.';
      } else if (start > end) {
        this.section.end > 1
          ? (this.section.start = 1)
          : (this.section.start = 1);
        this.check = false;
        return true;
      } else {
        return true;
      }
    },
    endCheck() {
      let start = Number(this.section.start);
      let end = Number(this.section.end);
      if (end <= start) {
        this.check = true;
        return '시작구간보다 높은 수를 입력해주세요.';
      } else if (end > this.total) {
        this.check = true;
        this.section.end = this.total;
        return true;
      } else {
        this.check = false;
        return true;
      }
    },
  },
};
</script>

<style scoped>
::v-deep .checkbox .v-label {
  font-size: 12px;
}
/* 
::v-deep .checkbox {
  margin-left: 10px;
} */
</style>
