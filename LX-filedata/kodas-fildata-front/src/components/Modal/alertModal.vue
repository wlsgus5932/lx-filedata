<template>
  <v-card width="100%">
    <v-card-title>
      <div>{{ title }}</div>
    </v-card-title>

    <v-card-text class="font-weight-bold grey--text">
      {{ content }}
    </v-card-text>

    <v-card-actions>
      <v-spacer />
      <v-btn color="green darken-1" text @click="$emit('hide')"> 닫기 </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    timeout: {
      type: Number,
      default: 1000,
    },
    title: String,
    content: String,
  },
};
</script>

<style></style>
