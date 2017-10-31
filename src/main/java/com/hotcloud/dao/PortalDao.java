package com.hotcloud.dao;

import com.hotcloud.model.ResAreaNllj;
import com.hotcloud.model.ResAreaNlljPK;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("portalDao")
public class PortalDao extends BaseDao implements IPortalDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<ResAreaNllj> findAll() {
        return getSession().createQuery("from ResAreaNllj").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ResAreaNllj> findByDate(String dateline) {
        Query query = getSession().createQuery("from ResAreaNllj where dateline=:dateline");
        query.setParameter("dateline", dateline);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ResAreaNllj> findByArea(double areaguid) {
        Query query = getSession().createQuery("from ResAreaNllj where areaguid=:areaguid");
        query.setParameter("areaguid", areaguid);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResAreaNllj findById(ResAreaNlljPK id) {
        Query query = getSession().createQuery("from ResAreaNllj where areaguid=:areaguid and dateline=:dateline");
        query.setParameter("areaguid", id.getAreaguid());
        query.setParameter("dateline", id.getDateline());
        return (ResAreaNllj) query.uniqueResult();
    }
}
