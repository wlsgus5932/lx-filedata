<template>
  <v-card width="100%">
    <v-card-title class="text-h5 grey lighten-2">
      <div>랜덤 다운로드</div>
    </v-card-title>
    <v-card-text class="font-weight-bold">
      <v-row>
        <v-col cols="6" md="12">
          <v-text-field
            placeholder="1"
            v-model="randomNum"
            :rules="rules"
            label="개수"
            type="number"
            min="1"
            required
            oninput="javascript: this.value = this.value.replace(/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' );"
          ></v-text-field>
        </v-col>
      </v-row>
      <br />
      <p>
        원하는 수량 입력 후 입력 수만큼 데이터를 랜덤하게 다운로드 받을 수
        있습니다.
      </p>
    </v-card-text>

    <v-card-actions>
      <v-spacer />
      <v-btn color="green darken-1" text @click="hideMe()"> 취소 </v-btn>
      <v-btn
        color="green darken-1"
        text
        @click="$emit('show', randomNum)"
        :disabled="randomNum < 1"
      >
        확인
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data: () => ({
    rules: [
      v => !!v || '숫자 1이상 입력해주세요.',
      v => v >= 1 || '숫자 1이상 입력해주세요.',
    ],
    randomNum: '',
  }),
  methods: {
    hideMe() {
      this.$emit('hide');
      this.randomNum = '';
    },
  },
};
</script>
