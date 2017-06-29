$(document).ready(function () {
    // 基于准备好的dom，初始化echarts实例
    refreshTimeMap();

    var userChart1 = echarts.init(document.getElementById('pic_user_1'));
    var userChart2 = echarts.init(document.getElementById('pic_user_2'));

    var addLine = echarts.init(document.getElementById('pic_add_line'));
    var hId = $('#hId').val();


    $.ajax({
        type:'post',
        url:'/HotelWorld/get_hotel_pie_data',
        async: false,
        data:{
            'hId':hId
        },
        success:function(data){
            var s_vip = data['singleVIP'];
            var d_vip = data['doubleVIP'];
            var b_vip = data['bigVIP'];
            var s_user = data['singleUser'];
            var d_user = data['doubleUser'];
            var b_user = data['bigUser'];

            var s_vip_m = data['singleVIPM'];
            var d_vip_m = data['doubleVIPM'];
            var b_vip_m = data['bigVIPM'];
            var s_user_m = data['singleUserM'];
            var d_user_m = data['doubleUserM'];
            var b_user_m = data['bigUserM'];

            userChart1.setOption({
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
                            {value:s_vip, name:'单人房', selected:true},
                            {value:d_vip, name:'双人房'},
                            {value:b_vip, name:'大套间'}
                        ]
                    },
                    {
                        name:'非会员',
                        type:'pie',
                        radius: ['40%', '55%'],

                        data:[
                            {value:s_user, name:'单人房'},
                            {value:d_user, name:'双人房'},
                            {value:b_user, name:'大套间'}
                        ]
                    }
                ]
            });
            userChart2.setOption({
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
                            {value:s_vip_m, name:'单人房', selected:true},
                            {value:d_vip_m, name:'双人房'},
                            {value:b_vip_m, name:'大套间'}
                        ]
                    },
                    {
                        name:'非会员',
                        type:'pie',
                        radius: ['40%', '55%'],

                        data:[
                            {value:s_user_m, name:'单人房'},
                            {value:d_user_m, name:'双人房'},
                            {value:b_user_m, name:'大套间'}
                        ]
                    }
                ]
            });
        }
    });

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



});


function chooseTime(type) {
    $('.time_btn').removeClass('active');
    $('.div_btn').find('.time_btn').eq(type).addClass('active');
}

function refreshTimeMap() {
    var timeType = $('.time_btn.active').attr('id');
    var hId = $('#hId').val();

    var lineChart= echarts.init(document.getElementById('line'));
    var barChart = echarts.init(document.getElementById('bar'));
    var calChartBig = echarts.init(document.getElementById('pic_canledar_big'));

    $.ajax({
        type:'post',
        url:'/HotelWorld/get_hotel_line_data',
        async: false,
        data:{
            'hId':hId,
            'timeType':timeType
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
            // 指定图表的配置项和数据
            lineChart.setOption({
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

    $.ajax({
        type:'post',
        url:'/HotelWorld/get_hotel_type_data',
        async: false,
        data:{
            'hId':hId,
            'timeType':timeType
        },
        success:function(data){
            //折线图数据处理
            var date = [];
            var singleM = [];
            var doubleM = [];
            var bigM = [];
            var money = [];

            $.each(data,function(j,vo){
                var b=(vo["date"]);
                date.push(b);
                var s=(vo["singleM"]);
                singleM.push(s);
                var k = (vo["doubleM"]);
                doubleM.push(k);
                var j = (vo["bigM"]);
                bigM.push(j);
                var z = (vo["money"]);
                money.push(z);
            });

            calChartBig.setOption({
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
                    data:['单人房','双人房','大套间','金额']
                },
                xAxis: [
                    {
                        type: 'category',
                        data: date,
                        axisPointer: {
                            type: 'shadow'
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '数量',

                        axisLabel: {
                            formatter: '{value} '
                        }
                    },
                    {
                        type: 'value',
                        name: '金额',

                        axisLabel: {
                            formatter: '{value} 元'
                        }
                    }
                ],
                series: [
                    {
                        name:'单人房',
                        type:'bar',
                        data:singleM
                    },
                    {
                        name:'双人房',
                        type:'bar',
                        data:doubleM
                    },{
                        name:'大套间',
                        type:'bar',
                        data:bigM
                    },
                    {
                        name:'金额',
                        type:'line',
                        yAxisIndex: 1,
                        data:money
                    }
                ],
                dataZoom: [{
                    "show": true,
                    "height": 30,
                    "xAxisIndex": [0],
                    "start": 98,
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