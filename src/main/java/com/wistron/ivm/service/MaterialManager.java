package com.wistron.ivm.service;

import com.wistron.ivm.service.GenericManager;
import com.wistron.ivm.model.Material;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface MaterialManager extends GenericManager<Material, Long> {
    
}