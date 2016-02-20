package com.gas.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gas.dao.SettingDao;
import com.gas.model.Setting;
import com.gas.model.SettingVO;

@Service
public class SettingService {
    @Autowired
    private SettingDao settingDao;

    public Map<String, String> findAll() {
        List<Setting> settings = settingDao.findAll();
        Map<String, String> maps = new HashMap<>();
        for (Setting setting : settings) {
            maps.put(setting.getSettingkey(), setting.getValue());
        }
        return maps;
    }

    public boolean update(SettingVO settingvo) {
        Map<String, String> settings = settingvo.toMap();
        Setting setting = null;
        for (String key : settings.keySet()) {
            setting = settingDao.findBySettingkey(key);
            setting.setValue(settings.get(key));
            settingDao.save(setting);
        }
        return true;
    }
}
