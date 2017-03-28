$(document).ready(function () {
    // 基于准备好的dom，初始化echarts实例
    var lineChart= echarts.init(document.getElementById('line'));
    var barChart = echarts.init(document.getElementById('bar'));

    var hId = $('#hId').val();
    $.ajax({
        type:'post',
        url:'/HotelWorld/get_line_data',
        data:{
            'id':hId,
            'type':2
        },
        success:function(data){
            //折线图数据处理
            var date = [];
            var nums = [];
            var moneys = [];

            $.each(data,function(j,vo){
                var b=(vo["mon"]);
                date.push(b);
                var s=(vo["num"]);
                nums.push(s);
                var k = (vo["money"]);
                moneys.push(k);
            });
            //图表更新
            barChart.setOption({
                tooltip : {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'line',
                        label: {
                            backgroundColor: '#6a7985'
                        }
                    }
                },
                title: {
                    text: '每月被消费次数一览柱状图',
                },
                legend: {
                    left:'400',
                    data:['次数']
                },
                grid: {
                    left: '3%',
                    right: '7%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: true,
                    data: date
                },
                yAxis: {
                    type: 'value'
                },

                series: {
                    name:'次数',
                    type:'bar',
                    smooth:true,
                    symbol: 'none',
                    sampling: '20',
                    barMaxWidth:'60',
                    data: nums
                }
            });
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '每月合计收益趋势曲线图'
                },
                legend: {
                    left:'400',
                    data:['消费额']
                },
                tooltip : {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'line'
                    }
                },
                grid: {
                    left: '5%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : {
                    type : 'category',
                    boundaryGap : true,
                    data : date
                },
                yAxis : {
                    type : 'value'
                },
                series: [{
                    name: '消费额',
                    type: 'line',
                    data: moneys,
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                }]
            };
            lineChart.setOption(option);
        }
    });


});