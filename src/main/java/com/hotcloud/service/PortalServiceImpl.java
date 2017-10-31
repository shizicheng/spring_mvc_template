package com.hotcloud.service;

import com.hotcloud.dao.IPortalDao;
import com.hotcloud.model.ResAreaNllj;
import com.hotcloud.model.ResAreaNlljPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("portalService")
@Transactional
public class PortalServiceImpl implements IPortalService {

    @Autowired
    private IPortalDao portalDao;

    public void setPortalDao(IPortalDao portalDao) {
        this.portalDao = portalDao;
    }

    @Override
    public List<ResAreaNllj> findAll() {
        return portalDao.findAll();
    }

    @Override
    public List<ResAreaNllj> findByDate(String dateline) {
        return portalDao.findByDate(dateline);
    }

    @Override
    public List<ResAreaNllj> findByArea(double areaguid) {
        return portalDao.findByArea(areaguid);
    }

    @Override
    public ResAreaNllj findById(ResAreaNlljPK id) {
        return portalDao.findById(id);
    }
}
