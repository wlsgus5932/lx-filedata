<template>
  <v-card width="100%">
    <v-card-title>
      {{ title }}
    </v-card-title>
    <v-card-text class="font-weight-bold grey--text mb-2">
      {{ content }}
    </v-card-text>

    <v-card-actions>
      <v-spacer />
      <v-btn color="green darken-1" text @click="$emit('hide')"> 취소 </v-btn>
      <v-btn color="green darken-1" text @click="$emit('submit')"> 확인 </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    title: String,
    content: String,
  },
};
</script>

<style></style>
