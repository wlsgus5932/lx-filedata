<template>
  <div>
    <v-app>
      <div>
        <SideBar style="z-index: 1100; position: absolute"></SideBar>
      </div>
      <Lmap></Lmap>
    </v-app>
  </div>
</template>

<script>
import SideBar from '@/views/SideBar.vue';
import Lmap from '@/views/Lmap.vue';
export default {
  components: { SideBar, Lmap },
  data() {
    return {};
  },
  mounted() {
    this.checkLogin;
  },
  methods: {
    checkLogin() {
      if (
        document.referrer === 'http://kodas.ml/' ||
        document.referrer === 'http://data.kodas.or.kr/'
      ) {
        const url = new URL(window.location.href);
        const urlParams = url.searchParams;
        this.loginUserId = urlParams.get('uid');
      } else {
        location.href = 'http://kodas.ml/';
        alert('KODAS 로그인 후 이용하실 수 있습니다.');
      }
    },
  },
};
</script>
<style>
html {
  font-family: 'fantasy', 'Bitstream Vera Serif', serif;
}

div::-webkit-scrollbar {
  width: 7px;
}
div::-webkit-scrollbar-thumb {
  background-color: #cecfcf;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
div::-webkit-scrollbar-track {
  background-color: white;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
</style>
