$(document).ready(function () {
    // 基于准备好的dom，初始化echarts实例
    var lineChart = echarts.init(document.getElementById('line'));
    var barChart = echarts.init(document.getElementById('bar'));

    var vId = $('#vId').val();
    $.ajax({
        type:'post',
        url:'/HotelWorld/get_line_data',
        data:{
            'vId':vId
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
            lineChart.setOption({
                tooltip: {
                    trigger: 'axis',
                    position: function (pt) {
                        return [pt[0], '10%'];
                    }
                },
                title: {
                    text: '每月合计消费趋势图',
                },
                legend: {
                    data:['金额']
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: date
                },
                yAxis: {
                    type: 'value',
                    boundaryGap: [0, '100%'],
                    name:'%'
                },

                series: {
                    name:'金额',
                    type:'line',
                    smooth:true,
                    symbol: 'none',
                    sampling: 'average',
                    data: moneys
                }
            });
        }
    });

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例',

        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'line',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };
    barChart.setOption(option);
});