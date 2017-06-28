$(document).ready(function () {

    var myDistrict = new district();                            //声明district对象
    myDistrict.init("#selProvince", "#selCity", "#selArea");    //初始化下拉框
    // myDistrict.bind("江苏省", "南京市", "鼓楼区");              //绑定内容

    // 基于准备好的dom，初始化echarts实例
    var modelChart = echarts.init(document.getElementById('model'));
    var mapChart = echarts.init(document.getElementById('pic_map'));

    refreshTimeMap();

    var model_data = [];
    for(var i =0; i<5;i++){
        var x =$('#scores').find('.small_score').eq(i).text();
        model_data.push(x);
    }
    var model_option = {
        title: {
            text: '用户模型',
            left: 'right'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['用户消费模型'],
            left: 'left'
        },
        backgroundColor: '#ffffff',
        radar: {
            indicator: [{
                text: '消费水平',
                max: 100
            }, {
                text: '消费频次',
                max: 100
            }, {
                text: '消费跨度',
                max: 100
            }, {
                text: '消费广度',
                max: 100
            }, {
                text: '消费信率',
                max: 100
            }],
            splitNumber: 4,
            name: {
                textStyle: {
                    color: '#201E1F'
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#2D91BD',
                    width: 2,
                    type: 'dotted'
                },
            },
            splitLine: {
                lineStyle: {
                    color: ['#2D91BD', 'transparent', 'transparent', 'transparent'],
                    width: 2
                }
            },
            splitArea: {
                areaStyle: {
                    color: ['#B3DBF5', '#FAFAFA']
                }
            }
        },
        series: [{
            type: 'radar',
            tooltip: {
                trigger: 'item'
            },
            symbol: 'circle',
            symbolSize: 5,
            itemStyle: {
                normal: {
                    color: '#2D91BD',
                    borderColor: '#2D91BD'
                }
            },
            data: [{
                value: model_data,
                name:'用户消费模型'
            }]
        }, ]
    };
    modelChart.setOption(model_option);

    var map_option = {
        title: {
            text: '各省订单额统计',
            subtext: '单位：元',
            left: 'right'
        },tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:['单人房','双人房','大套间']
        },
        visualMap: {
            min: 0,
            max: 100000,
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
        series: [
            {
                name: '单人房',
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
                data:[
                    {name: '北京',value: randomData() },
                    {name: '天津',value: randomData() },
                    {name: '上海',value: randomData() },
                    {name: '重庆',value: randomData() },
                    {name: '河北',value: randomData() },
                    {name: '河南',value: randomData() },
                    {name: '云南',value: randomData() },
                    {name: '辽宁',value: randomData() },
                    {name: '黑龙江',value: randomData() },
                    {name: '湖南',value: randomData() },
                    {name: '安徽',value: randomData() },
                    {name: '山东',value: randomData() },
                    {name: '新疆',value: randomData() },
                    {name: '江苏',value: randomData() },
                    {name: '浙江',value: randomData() },
                    {name: '江西',value: randomData() },
                    {name: '湖北',value: randomData() },
                    {name: '广西',value: randomData() },
                    {name: '甘肃',value: randomData() },
                    {name: '山西',value: randomData() },
                    {name: '内蒙古',value: randomData() },
                    {name: '陕西',value: randomData() },
                    {name: '吉林',value: randomData() },
                    {name: '福建',value: randomData() },
                    {name: '贵州',value: randomData() },
                    {name: '广东',value: randomData() },
                    {name: '青海',value: randomData() },
                    {name: '西藏',value: randomData() },
                    {name: '四川',value: randomData() },
                    {name: '宁夏',value: randomData() },
                    {name: '海南',value: randomData() },
                    {name: '台湾',value: randomData() },
                    {name: '香港',value: randomData() },
                    {name: '澳门',value: randomData() }
                ]
            },
            {
                name: '双人房',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:[
                    {name: '北京',value: randomData() },
                    {name: '天津',value: randomData() },
                    {name: '上海',value: randomData() },
                    {name: '重庆',value: randomData() },
                    {name: '河北',value: randomData() },
                    {name: '安徽',value: randomData() },
                    {name: '新疆',value: randomData() },
                    {name: '浙江',value: randomData() },
                    {name: '江西',value: randomData() },
                    {name: '山西',value: randomData() },
                    {name: '内蒙古',value: randomData() },
                    {name: '吉林',value: randomData() },
                    {name: '福建',value: randomData() },
                    {name: '广东',value: randomData() },
                    {name: '西藏',value: randomData() },
                    {name: '四川',value: randomData() },
                    {name: '宁夏',value: randomData() },
                    {name: '香港',value: randomData() },
                    {name: '澳门',value: randomData() }
                ]
            },
            {
                name: '大套间',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:[
                    {name: '北京',value: randomData() },
                    {name: '天津',value: randomData() },
                    {name: '上海',value: randomData() },
                    {name: '广东',value: randomData() },
                    {name: '台湾',value: randomData() },
                    {name: '香港',value: randomData() },
                    {name: '澳门',value: randomData() }
                ]
            }
        ]
    };
    mapChart.setOption(map_option);


    var vId = $('#vId').val();
    $.ajax({
        type: 'post',
        url: '/HotelWorld/vip_map_data',
        data: {
            'vId': vId
        },
        success: function (data) {
            //折线图数据处理
            var map_data_single = [];
            var map_data_double = [];
            var map_data_big = [];

            $.each(data, function (j, vo) {
                var pro = (vo["name"]);
                var b = (vo["singleM"]);
                map_data_single.push({name:pro,value:b});
                var s = (vo["doubleM"]);
                map_data_double.push({name:pro,value:s});
                var k = (vo["bigM"]);
                map_data_big.push({name:pro,value:k});
            });
            //图表更新
            mapChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '单人房',
                    data: map_data_single
                },{
                    name: '双人房',
                    data: map_data_double
                },{
                    name: '大套间',
                    data: map_data_big
                }]
            });
            mapChart.on('click',function (params) {
                var name = params.name;
                loadSmallMapChart(name);
            });
        }
    });
});


var provinces = {
    '北京':11,
    '天津':12,
    "河北":13,
    "山西":14,
    "内蒙古":15,
    "辽宁":21,
    "吉林":22,
    "黑龙江":23,
    "上海":31,
    "江苏":32,
    "浙江":33,
    "安徽":34,
    "福建":35,
    "江西":36,
    "山东":37,
    "河南":41,
    "湖北":42,
    "湖南":43,
    "广东":44,
    "广西":45,
    "海南":46,
    "重庆":50,
    "四川":51,
    "贵州":52,
    "云南":53,
    "西藏":54,
    "陕西":61,
    "甘肃":62,
    "青海":63,
    "宁夏":64,
    "新疆":65
};
function randomData() {
    return Math.round(Math.random()*1000);
}

function loadSmallMapChart(name) {
    var pId = provinces[name];
    if(pId === undefined){
        return;
    }
    $('.pic_remind').hide();
    var small_map_chart = echarts.init(document.getElementById('pic_small_map'));

    var vId = $('#vId').val();
    $.ajax({
        type: 'post',
        url: '/HotelWorld/vip_small_map_data',
        data: {
            'vId': vId,
            'pId': pId
        },
        success: function (data) {
            //折线图数据处理
            var small_map_data_single = [];
            var small_map_data_double = [];
            var small_map_data_big = [];

            $.each(data, function (j, vo) {
                var pro = (vo["name"]);
                var b = (vo["singleM"]);
                small_map_data_single.push({name: pro, value: b});
                var s = (vo["doubleM"]);
                small_map_data_double.push({name: pro, value: s});
                var k = (vo["bigM"]);
                small_map_data_big.push({name: pro, value: k});
            });

            var map_js = pId+'';
            //图表更新
            small_map_chart.setOption({
                title: {
                    text: '单省订单额统计',
                    subtext: '单位：元',
                    left: 'right'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['单人房', '双人房', '大套间']
                },
                visualMap: {
                    min: 0,
                    max: 20000,
                    left: 'left',
                    top: 'bottom',
                    text: ['高', '低'],           // 文本，默认为数值文本
                    calculable: true
                },

                series: [
                    {
                        name: '单人房',
                        type: 'map',
                        mapType: name,
                        roam: false,
                        label: {
                            normal: {
                                show: true
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: small_map_data_single
                    },
                    {
                        name: '双人房',
                        type: 'map',
                        mapType: name,
                        label: {
                            normal: {
                                show: true
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: small_map_data_double
                    },
                    {
                        name: '大套间',
                        type: 'map',
                        mapType: name,
                        label: {
                            normal: {
                                show: true
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: small_map_data_big
                    }
                ]
            });
        }
    });

}

function refreshTimeMap() {
    var timeChart = echarts.init(document.getElementById('pic_time'));

    var pro = $('#selProvince option:selected').val();
    var city= $('#selCity option:selected').val();
    var area = $('#selArea option:selected').val();
    var timeType = $('.time_btn.active').attr('id');

    var vId = $('#vId').val();
    $.ajax({
        type: 'post',
        url: '/HotelWorld/vip_time_data',
        data: {
            'vId': vId,
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