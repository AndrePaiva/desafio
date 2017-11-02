package com.cotec.desafio.controller;

import com.cotec.desafio.service.BasicCrudService;

public abstract class BasicRestController<T> {

    abstract BasicCrudService getService();

}
