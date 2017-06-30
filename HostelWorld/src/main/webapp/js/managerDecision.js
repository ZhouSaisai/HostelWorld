$(document).ready(function () {
    var myDistrict = new district();                            //声明district对象
    myDistrict.init("#selProvince", "#selCity", "#selArea");    //初始化下拉框

    refreshTimeMap();

    // 基于准备好的dom，初始化echarts实例
    var mapChart = echarts.init(document.getElementById('pic_map'));

    var map_option = {
        title: {
            text: '各省入住率',
            left: 'right'
        },tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:['入住率']
        },
        visualMap: {
            min: 0,
            max: 100,
            left: 'left',
            top: 'bottom',
            text: ['高','低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: [{
            name: '入住率',
            type: 'map',
            mapType: 'china',
            roam: false,
            label: {
                normal: {
                    show: true
                },
                emphasis: {
                    show: true
                }
            },
            data:[]
        }]
    };
    mapChart.setOption(map_option);

    $.ajax({
        type: 'post',
        url: '/HotelWorld/manage_map_data',

        success: function (data) {
            //折线图数据处理
            var map_data_single = [];

            $.each(data, function (j, vo) {
                var pro = (vo["name"]);
                var b = (vo["singleM"]);
                map_data_single.push({name:pro,value:b});
            });
            //图表更新
            mapChart.setOption({
                series: {
                    // 根据名字对应到相应的系列
                    name: '入住率',
                    data: map_data_single
                }
            });
        }
    });
});


function randomData() {
    return Math.round(Math.random()*1000);
}

function refreshTimeMap() {
    var timeChart = echarts.init(document.getElementById('pic_time'));

    var pro = $('#selProvince option:selected').val();
    var city= $('#selCity option:selected').val();
    var area = $('#selArea option:selected').val();
    var timeType = $('.time_btn.active').attr('id');

    $.ajax({
        type: 'post',
        url: '/HotelWorld/manage_time_data',
        data: {
            'pro': pro,
            'city':city,
            'area':area,
            'timeType':timeType
        },

        success: function (data) {
            //折线图数据处理
            var time_data_date = [];
            var time_data_num = [];
            var time_data_money = [];


            $.each(data, function (j, vo) {
                var date = (vo["date"]);
                time_data_date.push(date);
                var num = (vo["num"]);
                time_data_num.push(num);
                var money = (vo["money"]);
                time_data_money.push(money);
            });
            //图表更新
            timeChart.setOption({
                title: {
                    text: '多维订单分析表',
                    left: 'right'
                },

                tooltip: {
                    trigger: 'axis',
                    axisPointer:{
                        type:'shadow',
                        textStyle: {
                            color: "#fff"
                        }
                    }
                },
                legend: {
                    data: ['订单额','订单数']
                },
                xAxis: [{
                    type: 'category',
                    axisTick: {
                        alignWithLabel: true
                    },
                    data: time_data_date
                }],
                yAxis: [{
                    type: 'value',
                    name: '订单额',
                    position: 'right',
                    axisLabel: {
                        formatter: '{value}元'
                    }
                }, {
                    type: 'value',
                    name: '订单数',
                    position: 'left'
                }],
                series: [{
                    name: '订单额',
                    type: 'line',
                    label: {
                        normal: {
                            show: true,
                            position: 'top',
                        }
                    },
                    lineStyle: {
                        normal: {
                            width: 2,
                            color: '#d26588'
                        }
                    },
                    data: time_data_money
                }, {
                    name: '订单数',
                    type: 'bar',
                    yAxisIndex: 1,
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    itemStyle:{
                        normal:{
                            color:'#5389d2'
                        }
                    },
                    data: time_data_num
                }],
                dataZoom: [{
                    "show": true,
                    "height": 30,
                    "xAxisIndex": [0],
                    bottom: 30,
                    "start": 95,
                    "end": 100,
                    handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                    handleSize: '110%',
                    handleStyle:{
                        color:"#ddd"
                    },
                    textStyle:{
                        color:"#fff"},
                    borderColor:"#eee"

                }, {
                    "type": "inside",
                    "show": true,
                    "height": 15,
                    "start": 1,
                    "end": 35
                }]
            });
        }
    });

}

function chooseTime(type) {
    $('.time_btn').removeClass('active');
    $('.div_btn').find('.time_btn').eq(type).addClass('active');
}
