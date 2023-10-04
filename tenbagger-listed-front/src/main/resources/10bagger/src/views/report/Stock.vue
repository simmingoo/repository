<template>
  <div class="stokreport">
    <embed :src="'/report/stockReport?bizNo='+bizno+'&isuSrtCd='+isuSrtCd "/>
  </div>
</template>
<script>
export default {
  name: 'ReportStock',
  data() {
    return{
      bizno:'',
      isuSrtCd:'',
    }
  },
  mounted() {
    this.bizno = sessionStorage.getItem('detailBizNo');
    this.isuSrtCd = sessionStorage.getItem('detailIsuSrtCd');
    console.log('bizno : '+this.bizno + " / isuSrtCd : "+this.isuSrtCd)
  },
}
</script>
<style lang="scss">
body, #app {
  height: 100%;
}
.stokreport{
  width: 100%;
  height: 824px;
  display: flex;
  justify-content: center;
  align-items: center;
  embed{
    width: 100%;
    height: 824px;
  }
}
</style>