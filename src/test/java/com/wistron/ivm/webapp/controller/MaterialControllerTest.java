package com.wistron.ivm.webapp.controller;

import com.wistron.ivm.service.MaterialManager;
import com.wistron.ivm.model.Material;

import com.wistron.ivm.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class MaterialControllerTest extends BaseControllerTestCase {
    @Autowired
    private MaterialController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("materialList"));
        assertTrue(((List) m.get("materialList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        MaterialManager materialManager = (MaterialManager) applicationContext.getBean("materialManager");
        materialManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("materialList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}