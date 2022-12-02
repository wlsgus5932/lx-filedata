<template>
  <v-card height="100%" width="65" max-width="300">
    <v-list>
      <img :src="require(`@/assets/img/LX_no.jpg`)" class="logo-img" />
      <v-list-item-group color="primary">
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          @click="changeType(item.text)"
        >
          <v-tooltip right>
            <template v-slot:activator="{ on }">
              <v-list-item-icon
                class="text-center"
                style="margin-right: 0.5px; padding: 3px"
                v-on="on"
              >
                <img
                  :src="require(`@/assets/img/${item.text}.png`)"
                  style="width: 25px"
                />
              </v-list-item-icon>
              <v-spacer></v-spacer>
            </template>
            <span>{{ item.text }}</span>
          </v-tooltip>
        </v-list-item>
      </v-list-item-group>
    </v-list>
    <SensorForm v-if="type == '센서' && formChange" />
    <LearningForm v-if="type == '학습' && formChange" />
    <SpatialForm v-if="type == '공간' && formChange" />
    <LdmForm v-if="type == 'LDM' && formChange" />
  </v-card>
</template>
<script>
import LearningForm from '@/components/SideBarForm/LearningForm.vue';
import SensorForm from '@/components/SideBarForm/SensorForm.vue';
import SpatialForm from '@/components/SideBarForm/SpatialForm.vue';
import LdmForm from '@/components/SideBarForm/LdmForm.vue';
export default {
  components: { SensorForm, LearningForm, SpatialForm, LdmForm },
  data: () => ({
    formChange: false,
    type: '',
    items: [
      { text: '센서' },
      { text: '학습' },
      { text: '공간' },
      { text: 'LDM' },
    ],
  }),

  methods: {
    changeType(type) {
      if (this.type == type && this.formChange == true) {
        this.formChange = !this.formChange;
      } else if (this.type == type && this.formChange == false) {
        this.formChange = !this.formChange;
      } else {
        this.type = type;
        this.$store.commit('set_type', type);
        this.formChange = true;
      }
    },
  },
};
</script>
<style scoped>
.logo-img {
  width: 50px;
  margin-left: 10px;
  margin: 10px;
}
</style>
