package com.hotcloud.dao;

import com.hotcloud.model.ResAreaNllj;
import com.hotcloud.model.ResAreaNlljPK;

import java.util.List;

public interface IPortalDao {
    List<ResAreaNllj> findAll();

    List<ResAreaNllj> findByDate(String dateline);

    List<ResAreaNllj> findByArea(double areaguid);

    ResAreaNllj findById(ResAreaNlljPK id);


}
