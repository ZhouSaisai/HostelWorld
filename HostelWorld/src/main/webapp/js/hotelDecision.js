$(document).ready(function () {
    // 基于准备好的dom，初始化echarts实例
    var lineChart= echarts.init(document.getElementById('line'));
    var barChart = echarts.init(document.getElementById('bar'));

    var calChart = echarts.init(document.getElementById('pic_canledar'));

    var userChart1 = echarts.init(document.getElementById('pic_user_1'));
    var userChart2 = echarts.init(document.getElementById('pic_user_2'));

    var addLine = echarts.init(document.getElementById('pic_add_line'));

    var user_option_1 = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['单人房','双人房','大套间']
        },
        series: [
            {
                name:'会员',
                type:'pie',
                selectedMode: 'single',
                radius: [0, '30%'],

                label: {
                    normal: {
                        position: 'inner'
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:335, name:'单人房', selected:true},
                    {value:679, name:'双人房'},
                    {value:1548, name:'大套间'}
                ]
            },
            {
                name:'非会员',
                type:'pie',
                radius: ['40%', '55%'],

                data:[
                    {value:335, name:'单人房'},
                    {value:310, name:'双人房'},
                    {value:234, name:'大套间'}
                ]
            }
        ]
    };

    var user_option_2 = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'right',
            data:['单人房','双人房','大套间']

        },
        series: [
            {
                name:'会员',
                type:'pie',
                selectedMode: 'single',
                radius: [0, '30%'],

                label: {
                    normal: {
                        position: 'inner'
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:335, name:'单人房', selected:true},
                    {value:679, name:'双人房'},
                    {value:1548, name:'大套间'}
                ]
            },
            {
                name:'非会员',
                type:'pie',
                radius: ['40%', '55%'],

                data:[
                    {value:335, name:'单人房'},
                    {value:310, name:'双人房'},
                    {value:234, name:'大套间'}
                ]
            }
        ]
    };

    userChart1.setOption(user_option_1);
    userChart2.setOption(user_option_2);

    var base = +new Date(2010, 9, 3);
    var oneDay = 24 * 3600 * 1000;
    var date = [];

    var data = [Math.random() * 300];

    for (var i = 1; i < 2500; i++) {
        var now = new Date(base += oneDay);
        date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));
        data.push(Math.round((Math.random() - 0.5) * 20 + data[i - 1]));
    }

    var add_option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        title: {
            left: 'center',
            text: '订单增长率',
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: date
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        dataZoom: [{
            type: 'inside',
            start: 0,
            end: 10
        }, {
            start: 0,
            end: 10,
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '80%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }],
        series: [
            {
                name:'模拟数据',
                type:'line',
                smooth:true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: 'rgb(255, 70, 131)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                        }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                        }])
                    }
                },
                data: data
            }
        ]
    };

    addLine.setOption(add_option);

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
                    text: '客栈订单数',
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
                    itemStyle:{
                        normal:{
                            color:'#5389d2'
                        }

                    },
                    data: nums
                },
                dataZoom: [{
                    "show": true,
                    "height": 30,
                    "xAxisIndex": [0],
                    "start": 30,
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
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '客栈收益'
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
                }],
                dataZoom: [{
                    "show": true,
                    "height": 30,
                    "xAxisIndex": [0],
                    "start": 30,
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
            };
            lineChart.setOption(option);
        }
    });
    //
    // var option = {
    //     visualMap: {
    //         show: false
    //         min: 0,
    //         max: 1000
    //     },
    //     calendar: {
    //         range: '2017'
    //     },
    //     series: {
    //         type: 'heatmap',
    //         coordinateSystem: 'calendar',
    //         data: [['2017-01-02', 900], ['2017-01-02', 877], ['2017-01-02', 699]]
    //     }
    // }

    var cellSize = [80, 80];
    var pieRadius = 30;

    function getVirtulData() {
        var date = +echarts.number.parseDate('2017-02-01');
        var end = +echarts.number.parseDate('2017-03-01');
        var dayTime = 3600 * 24 * 1000;
        var data = [];
        for (var time = date; time < end; time += dayTime) {
            data.push([
                echarts.format.formatTime('yyyy-MM-dd', time),
                Math.floor(Math.random() * 10000)
            ]);
        }
        return data;
    }

    function getPieSeries(scatterData, chart) {
        return echarts.util.map(scatterData, function (item, index) {
            var center = chart.convertToPixel('calendar', item);
            return {
                id: index + 'pie',
                type: 'pie',
                center: center,
                label: {
                    normal: {
                        formatter: '{c}',
                        position: 'inside'
                    }
                },
                radius: pieRadius,
                data: [
                    {name: '单人房', value: Math.round(Math.random() * 24)},
                    {name: '双人房', value: Math.round(Math.random() * 24)},
                    {name: '大套间', value: Math.round(Math.random() * 24)}
                ]
            };
        });
    }

    function getPieSeriesUpdate(scatterData, chart) {
        return echarts.util.map(scatterData, function (item, index) {
            var center = chart.convertToPixel('calendar', item);
            return {
                id: index + 'pie',
                center: center
            };
        });
    }

    var scatterData = getVirtulData();

    var cal_option = {
        tooltip : {},
        legend: {
            data: ['单人房', '双人房', '大套间'],
            bottom: 20
        },
        calendar: {
            top: 'middle',
            left: 'center',
            orient: 'vertical',
            cellSize: cellSize,
            yearLabel: {
                show: false,
                textStyle: {
                    fontSize: 30
                }
            },
            dayLabel: {
                margin: 20,
                firstDay: 1,
                nameMap: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
            },
            monthLabel: {
                show: false
            },
            range: ['2017-02']
        },
        series: [{
            id: 'label',
            type: 'scatter',
            coordinateSystem: 'calendar',
            symbolSize: 1,
            label: {
                normal: {
                    show: true,
                    formatter: function (params) {
                        return echarts.format.formatTime('dd', params.value[0]);
                    },
                    offset: [-cellSize[0] / 2 + 10, -cellSize[1] / 2 + 10],
                    textStyle: {
                        color: '#000',
                        fontSize: 14
                    }
                }
            },
            data: scatterData
        }]
    };

    var pieInitialized;
    setTimeout(function () {
        pieInitialized = true;
        calChart.setOption({
           series: getPieSeries(scatterData, calChart)
        });
    }, 10);

    calChart.setOption(cal_option);




});

var cal_option_big = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    toolbox: {
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data:['蒸发量','降水量','平均温度']
    },
    xAxis: [
        {
            type: 'category',
            data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
            axisPointer: {
                type: 'shadow'
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '水量',
            min: 0,
            max: 250,
            interval: 50,
            axisLabel: {
                formatter: '{value} ml'
            }
        },
        {
            type: 'value',
            name: '温度',
            min: 0,
            max: 25,
            interval: 5,
            axisLabel: {
                formatter: '{value} °C'
            }
        }
    ],
    series: [
        {
            name:'蒸发量',
            type:'bar',
            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
        },
        {
            name:'降水量',
            type:'bar',
            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
        },
        {
            name:'平均温度',
            type:'line',
            yAxisIndex: 1,
            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
        }
    ]
};

function chooseTime(type) {
    $('.time_btn').removeClass('active');
    $('.div_btn').find('.time_btn').eq(type).addClass('active');
}

function refreshTimeMap() {
    var timeType = $('.time_btn.active').attr('id');

    var calChart = echarts.init(document.getElementById('pic_canledar'));
    var calChartBig = echarts.init(document.getElementById('pic_canledar_big'));

    if(timeType=='time0'){
        $('#pic_canledar').show();
        $('#pic_canledar_big').hide();
    }else{
        calChartBig.setOption(cal_option_big);
        $('#pic_canledar_big').show();
        $('#pic_canledar').hide();
    }
}