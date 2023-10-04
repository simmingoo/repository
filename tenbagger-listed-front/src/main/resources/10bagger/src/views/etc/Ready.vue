<template>
  <div class="ready">
    <h1><em>🚧</em>준비중입니다.</h1>
  </div>
</template>

<script>

export default {
  name: 'Ready',
  beforeCreate() {
		document.getElementsByTagName("html")[0].className = "p_ready";
      // document.getElementsByTagName("nav")[0].classList.add("on");
  },
	metaInfo: {
		title: '준비중 | 10bagger',
	}
}
</script>
<style lang="scss">
  body, #app {
    height: 100%;
  }
  .ready{
    width: 100%;
    height: 500px;
    display: flex;
    justify-content: center;
    align-items: center;
    h1{
      font-size: 26px;
      text-align: center;
      font-weight: 100;
      color:#aaa;
      em{
        font-size: 70px;
        display: block;
      }
    }
  }
</style>