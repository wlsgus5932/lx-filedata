<template>
  <v-card width="100%" height="8%">
    <v-card-actions>
      <v-progress-circular
        :size="30"
        color="primary"
        indeterminate
      ></v-progress-circular>

      <v-card-text class="font-weight-bold grey--text">
        {{ content }}
      </v-card-text>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    content: String,
  },
};
</script>

<style></style>
