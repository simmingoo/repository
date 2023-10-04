<template>
	<div class="tooltip" :class="classNm">
		<em>?</em>
		<span class="desc">
			{{desc}}
		</span>
	</div>
</template>
<script>
export default {
	props: ['desc', 'classNm'],
}
</script>