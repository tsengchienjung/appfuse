package com.wistron.ivm.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.wistron.ivm.service.MaterialManager;
import com.wistron.ivm.model.Material;
import com.wistron.ivm.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/materialform*")
public class MaterialFormController extends BaseFormController {
    private MaterialManager materialManager = null;

    @Autowired
    public void setMaterialManager(MaterialManager materialManager) {
        this.materialManager = materialManager;
    }

    public MaterialFormController() {
        setCancelView("redirect:materials");
        setSuccessView("redirect:materials");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Material showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return materialManager.get(new Long(id));
        }

        return new Material();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Material material, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(material, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "materialform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (material.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            materialManager.remove(material.getId());
            saveMessage(request, getText("material.deleted", locale));
        } else {
            materialManager.save(material);
            String key = (isNew) ? "material.added" : "material.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:materialform?id=" + material.getId();
            }
        }

        return success;
    }
}
