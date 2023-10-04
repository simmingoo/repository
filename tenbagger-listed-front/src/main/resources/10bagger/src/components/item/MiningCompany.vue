<template>
	<div class="unit">
		<table>
			<colgroup>
				<col width="60"/>
				<col width="10%"/>
				<col width="9%"/>
				<col width="9%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width="10%"/>
				<col width=""/>
				<col width="9%"/>
				<col width="9%"/>
			</colgroup>
			<tr>
				<th>순위</th>
				<th>소속섹터</th>
				<th>소속테마</th>
				<th>섹터순위</th>
				<th>테마순위</th>
				<th>기술력(TSS)</th>
				<th>기술투자지수</th>
				<th>섹터평균 기술투자수</th>
				<th>시총</th>
				<th>시총순위</th>
			</tr>
			<tr>
				<td>{{this.id}}</td>
				<td>디스플레이</td>
				<td>OLED</td>
				<td>3/5</td>
				<td>5/15</td>
				<td>111</td>
				<td>30</td>
				<td>50.211</td>
				<td>600</td>
				<td>4000</td>
			</tr>
		</table>
		<div class="charts">
			<div class="bx grade">
				<h3>투자매력도</h3>
				<strong>BAA+</strong>
				<div class="bx_charm">
					<span>낮음</span><span class="bar">투자매력도<em class="dot p50">50%<!-- 포인트 위치 p0, p25, p50, p75, p100 10단위 퍼센트 --></em></span><span>높음</span>
				</div>
				<span>기술가치: 높음 / 주가: 근접</span>
			</div>
			<div class="bx_chart chart_prepare"><!--차트 완료 되면 chart_prepare 클래스는 빼주세요-->
				Chart Area
			</div>
			<div class="bx_chart chart_prepare"><!--차트 완료 되면 chart_prepare 클래스는 빼주세요-->
				Chart Area
			</div>
		</div>
	</div>
</template>

<script>
export default {
	props: ['id'],
	name: 'MiningCompany',
}
</script>