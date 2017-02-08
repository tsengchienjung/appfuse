package com.wistron.ivm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wistron.ivm.dao.MaterialDao;
import com.wistron.ivm.model.Material;
import com.wistron.ivm.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaterialManagerImplTest extends BaseManagerMockTestCase {
    private MaterialManagerImpl manager = null;
    private MaterialDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(MaterialDao.class);
        manager = new MaterialManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetMaterial() {
        log.debug("testing get...");

        final Long id = 7L;
        final Material material = new Material();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(material));
        }});

        Material result = manager.get(id);
        assertSame(material, result);
    }

    @Test
    public void testGetMaterials() {
        log.debug("testing getAll...");

        final List materials = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(materials));
        }});

        List result = manager.getAll();
        assertSame(materials, result);
    }

    @Test
    public void testSaveMaterial() {
        log.debug("testing save...");

        final Material material = new Material();
        // enter all required fields
        material.setType(970492507);
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(material)));
        }});

        manager.save(material);
    }

    @Test
    public void testRemoveMaterial() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}