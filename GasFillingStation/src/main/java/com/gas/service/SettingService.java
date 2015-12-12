package com.gas.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gas.dao.SettingDao;
import com.gas.model.Setting;

@Service
public class SettingService {
    @Autowired
    private SettingDao settingDao;

    public Map<String, String> findAll() {
        List<Setting> settings = settingDao.findAll();
        Map<String, String> maps = new HashMap<>();
        for (Setting setting : settings) {
            maps.put(setting.getKey(), setting.getValue());
        }
        return maps;
    }
}
