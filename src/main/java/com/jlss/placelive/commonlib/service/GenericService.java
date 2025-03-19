package com.jlss.placelive.commonlib.service;


import org.springframework.data.domain.Page;

import java.util.List;

public interface GenericService<T> {
   String deleteObject(Integer id);

   T createObject(T object);
   T objectsIdPut(Integer id, T object);
   T objectsIdGet(Integer id);
   Page<T> getListOfObjects(Integer page, Integer size, String filter, String search);



}
