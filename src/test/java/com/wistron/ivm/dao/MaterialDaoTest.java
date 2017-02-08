package com.wistron.ivm.dao;

import com.wistron.ivm.dao.BaseDaoTestCase;
import com.wistron.ivm.model.Material;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class MaterialDaoTest extends BaseDaoTestCase {
    @Autowired
    private MaterialDao materialDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveMaterial() {
        Material material = new Material();

        // enter all required fields
        material.setType(697793520);

        log.debug("adding material...");
        material = materialDao.save(material);

        material = materialDao.get(material.getId());

        assertNotNull(material.getId());

        log.debug("removing material...");

        materialDao.remove(material.getId());

        // should throw DataAccessException 
        materialDao.get(material.getId());
    }
}