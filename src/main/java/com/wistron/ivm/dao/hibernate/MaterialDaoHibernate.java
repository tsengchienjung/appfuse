package com.wistron.ivm.dao.hibernate;

import com.wistron.ivm.model.Material;
import com.wistron.ivm.dao.MaterialDao;
import com.wistron.ivm.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("materialDao")
public class MaterialDaoHibernate extends GenericDaoHibernate<Material, Long> implements MaterialDao {

    public MaterialDaoHibernate() {
        super(Material.class);
    }
}
