package com.sundeinfo.foundation.mvc.controller;

import com.sundeinfo.foundation.mvc.component.AbstractComponent;
import com.sundeinfo.foundation.request.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractController<T> extends AbstractComponent<T> implements Controller<T> {

    @Autowired
    protected RequestHandler requestHandler;

}
