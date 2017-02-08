package com.wistron.ivm.webapp.controller;

import com.wistron.ivm.webapp.controller.BaseControllerTestCase;
import com.wistron.ivm.model.Material;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaterialFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private MaterialFormController form;
    private Material material;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/materialform");
        request.addParameter("id", "-1");

        material = form.showForm(request);
        assertNotNull(material);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/materialform");
        request.addParameter("id", "-1");

        material = form.showForm(request);
        assertNotNull(material);

        request = newPost("/materialform");

        material = form.showForm(request);
        // update required fields
        material.setType(542989416);

        BindingResult errors = new DataBinder(material).getBindingResult();
        form.onSubmit(material, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/materialform");
        request.addParameter("delete", "");
        material = new Material();
        material.setId(-2L);

        BindingResult errors = new DataBinder(material).getBindingResult();
        form.onSubmit(material, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
