package com.edu.nju.wel.service;

import com.edu.nju.wel.info.VIPAnalyse;

/**
 * Created by zs on 2017/3/24.
 */
public interface AnalyseService {
    //获得会员的统计记录
    public VIPAnalyse getVipAnalyse(int vId);

}
