$(function() {
    $.getJSON(ctx + "file/city.json", function(r) {
        $('#search-form').autocomplete({
            hints: r,
            placeholder: '城市名',
            keyname: 'areaid',
            width: 300,
            height: 29,
            valuename: 'countyname',
            onSubmit: function(text) {
                $('#areaId').text(text);
            }
        });
    });

});

function search() {
    var areaId = $('#areaId').text();
    if (areaId.length) {
        $.post(ctx + "weather/query", { "areaId": areaId }, function(r) {
            if (r.code === 0) {
                var data = JSON.parse(r.msg);
                if (data.code == 200) {
                    var countyName = data.value[0].city;
                    var weathers = data.value[0].weathers;
                    var day_c = [];
                    var night_c = [];
                    var dateArr = [];
                    for (var i = 0; i < weathers.length; i++) {
                        if (i === weathers.length - 1) {
                            day_c.unshift(parseFloat(weathers[i].temp_day_c));
                            night_c.unshift(parseFloat(weathers[i].temp_night_c));
                            dateArr.unshift(weathers[i].date.split("-")[1] + "-" + weathers[i].date.split("-")[2]);
                        } else {
                            day_c.push(parseFloat(weathers[i].temp_day_c));
                            night_c.push(parseFloat(weathers[i].temp_night_c));
                            dateArr.push(weathers[i].date.split("-")[1] + "-" + weathers[i].date.split("-")[2]);
                        }
                    }
                    var weather3HoursDetailsInfos = data.value[0].weatherDetailsInfo.weather3HoursDetailsInfos;
                    var publishTime = data.value[0].weatherDetailsInfo.publishTime;
                    var timeArr = [];
                    var hours_c = [];
                    for (var i = 0; i < weather3HoursDetailsInfos.length; i++) {
                        var time = weather3HoursDetailsInfos[i].endTime.split(" ")[1].split(":");
                        hours_c.push(parseFloat(weather3HoursDetailsInfos[i].highestTemperature));
                        timeArr.push(time[0] + ":" + time[1]);
                    }
                    var chart = Highcharts.chart('container1', {
                        chart: {
                            type: 'line'
                        },
                        title: {
                            text: countyName + '七天气温图',
                            style: {
                                'font-size': '1.1rem',
                                'color': '#888'
                            }
                        },
                        xAxis: {
                            categories: dateArr
                        },
                        yAxis: {
                            title: {
                                text: '°C',
                                align: 'high',
                                rotation: 0
                            }
                        },
                        exporting: {
                            enabled: false
                        },
                        plotOptions: {
                            spline: {
                                dataLabels: {
                                    enabled: true
                                },
                                enableMouseTracking: true
                            }
                        },
                        credits: {
                            enabled: false
                        },
                        series: [{
                            type: 'spline',
                            color: '#FFC77F',
                            name: '高温',
                            data: day_c
                        }, {
                            type: 'spline',
                            color: '#C5F0A4',
                            name: '低温',
                            data: night_c
                        }]
                    });

                    var chart = Highcharts.chart('container2', {
                        chart: {
                            type: 'line'
                        },
                        title: {
                            text: countyName + '未来气温细节',
                            style: {
                                'font-size': '1.1rem',
                                'color': '#888'
                            }
                        },
                        xAxis: {
                            categories: timeArr
                        },
                        yAxis: {
                            title: {
                                text: '°C',
                                align: 'high',
                                rotation: 0
                            }
                        },
                        exporting: {
                            enabled: false
                        },
                        plotOptions: {
                            spline: {
                                dataLabels: {
                                    enabled: true
                                },
                                enableMouseTracking: true
                            }
                        },
                        credits: {
                            enabled: false
                        },
                        series: [{
                            type: 'spline',
                            color: '#A1D9FF',
                            name: '气温',
                            data: hours_c
                        }]
                    });

                    var realtime = data.value[0].realtime;
                    var detailHtml = '<div class="card-header">' +
                        '<h2 class="card-title">当前天气</h2>' +
                        '</div>' +
                        '<div class="card-block">' +
                        '<ul class="icon-list">' +
                        '<li>天气：' + realtime.weather + '</li>' +
                        '<li>风向：' + realtime.wd + '</li>' +
                        '<li>风力大小：' + realtime.ws + '</li>' +
                        '<li>温度：' + realtime.temp + '℃</li>' +
                        '<li>体感温度：' + realtime.sendibleTemp + '℃</li>' +
                        '<li>空气湿度百分比：' + realtime.sd + '%</li>' +
                        '<li>更新时间：' + realtime.time + '</li>' +
                        '</ul>' +
                        '</div>';
                    $("#container3").html("").append(detailHtml);

                    var weathersHtml = '<div class="card-header">' +
                        '<h2 class="card-title">未来天气</h2>' +
                        '</div>' +
                        '<div class="card-block">' +
                        '<ul class="icon-list">' +
                        '<li style="width:100%">' + weathers[6].date + '【' + weathers[6].week + '】：日出时间---' + weathers[6].sun_rise_time + '      日落时间---' + weathers[6].sun_down_time + '      天气---' + weathers[6].weather + ' </li>' +
                        '<li style="width:100%">' + weathers[0].date + '【' + weathers[0].week + '】：日出时间---' + weathers[0].sun_rise_time + '      日落时间---' + weathers[0].sun_down_time + '      天气---' + weathers[0].weather + ' </li>' +
                        '<li style="width:100%">' + weathers[1].date + '【' + weathers[1].week + '】：日出时间---' + weathers[1].sun_rise_time + '      日落时间---' + weathers[1].sun_down_time + '      天气---' + weathers[1].weather + ' </li>' +
                        '<li style="width:100%">' + weathers[2].date + '【' + weathers[2].week + '】：日出时间---' + weathers[2].sun_rise_time + '      日落时间---' + weathers[2].sun_down_time + '      天气---' + weathers[2].weather + ' </li>' +
                        '<li style="width:100%">' + weathers[3].date + '【' + weathers[3].week + '】：日出时间---' + weathers[3].sun_rise_time + '      日落时间---' + weathers[3].sun_down_time + '      天气---' + weathers[3].weather + ' </li>' +
                        '<li style="width:100%">' + weathers[4].date + '【' + weathers[4].week + '】：日出时间---' + weathers[4].sun_rise_time + '      日落时间---' + weathers[4].sun_down_time + '      天气---' + weathers[4].weather + ' </li>' +
                        '<li style="width:100%">' + weathers[5].date + '【' + weathers[5].week + '】：日出时间---' + weathers[5].sun_rise_time + '      日落时间---' + weathers[5].sun_down_time + '      天气---' + weathers[5].weather + ' </li>' +
                        '</ul>' +
                        '</div>';
                    $("#container4").html("").append(weathersHtml);


                    var indexes = data.value[0].indexes;
                    var indexesHtml = '<div class="card-header">' +
                        '<h2 class="card-title">生活指数</h2>' +
                        '</div>' +
                        '<div class="card-block">' +
                        '<ul class="icon-list">' +
                        '<li style="width:100%">化妆指数：' + indexes[0].content + '</li>' +
                        '<li style="width:100%">感冒指数：' + indexes[1].content + '</li>' +
                        '<li style="width:100%">洗车指数：' + indexes[2].content + '</li>' +
                        '<li style="width:100%">穿衣指数：' + indexes[3].content + '</li>' +
                        '<li style="width:100%">运动指数：' + indexes[5].content + '</li>' +
                        '<li style="width:100%">紫外线强度指数：' + indexes[4].content + '</li>' +
                        '</ul>' +
                        '</div>';
                    $("#container5").html("").append(indexesHtml);

                    var alarms = data.value[0].alarms;
                    $("#container6").html("");
                    if (alarms.length) {
                        alarms = alarms[0];
                        var alarmsHtml = '<div class="card-header">' +
                            '<h2 class="card-title">预警信息</h2>' +
                            '</div>' +
                            '<div class="card-block">' +
                            '<ul class="icon-list">' +
                            '<li style="width:100%">预警标题：' + alarms.alarmDesc + '</li>' +
                            '<li style="width:100%">预警类型：' + alarms.alarmTypeDesc + '</li>' +
                            '<li style="width:100%">发布时间：' + alarms.publishTime + '</li>' +
                            '<li style="width:100%">预防措施：' + alarms.precaution + '</li>' +
                            '<li style="width:100%">预警详情：' + alarms.alarmContent + '</li>' +
                            '</ul>' +
                            '</div>';
                        $("#container6").append(alarmsHtml);
                    }
                } else {
                    $MB.n_danger(data.message);
                }
            } else {
                $MB.n_danger(r.msg);
            }
        });
    } else {
        $MB.n_warning("请输入城市！");
    }

}