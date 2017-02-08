package com.wistron.ivm.service.impl;

import com.wistron.ivm.dao.MaterialDao;
import com.wistron.ivm.model.Material;
import com.wistron.ivm.service.MaterialManager;
import com.wistron.ivm.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("materialManager")
@WebService(serviceName = "MaterialService", endpointInterface = "com.wistron.ivm.service.MaterialManager")
public class MaterialManagerImpl extends GenericManagerImpl<Material, Long> implements MaterialManager {
    MaterialDao materialDao;

    @Autowired
    public MaterialManagerImpl(MaterialDao materialDao) {
        super(materialDao);
        this.materialDao = materialDao;
    }
}