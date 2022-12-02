<template>
  <div>
    <v-row>
      <v-col cols="6">
        <v-menu
          ref="menu1"
          v-model="menu1"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          max-width="290px"
          min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="startDate"
              label="운행시작일"
              persistent-hint
              v-bind="attrs"
              v-on="on"
              prepend-icon="mdi-calendar"
            ></v-text-field>
          </template>
          <v-date-picker
            v-model="startDate"
            no-title
            @input="(menu1 = false), startDateUpdate()"
          ></v-date-picker>
        </v-menu>
      </v-col>

      <v-col cols="5">
        <v-menu
          v-model="menu2"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          max-width="290px"
          min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="endDate"
              label="운행종료일"
              persistent-hint
              readonly
              v-bind="attrs"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            v-model="endDate"
            no-title
            @input="(menu2 = false), endDateUpdate()"
          ></v-date-picker>
        </v-menu>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  data: () => ({
    endDate: '',
    startDate: '2018-05-27',
    menu1: false,
    menu2: false,
  }),

  mounted() {
    this.getDate();
    this.startDateUpdate();
    this.endDateUpdate();
  },

  methods: {
    getDate() {
      let today = new Date();
      let year = today.getFullYear();
      let month = ('0' + (today.getMonth() + 1)).slice(-2);
      let day = ('0' + today.getDate()).slice(-2);

      this.endDate = year + '-' + month + '-' + day;
    },

    startDateUpdate() {
      this.$emit('startDate', this.startDate);
    },

    endDateUpdate() {
      this.$emit('endDate', this.endDate);
    },

    formatDate(date) {
      if (!date) return null;

      const [year, month, day] = date.split('-');
      return `${month}/${day}/${year}`;
    },
    parseDate(date) {
      if (!date) return null;

      const [month, day, year] = date.split('/');
      return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`;
    },
  },
};
</script>

<style scoped></style>
