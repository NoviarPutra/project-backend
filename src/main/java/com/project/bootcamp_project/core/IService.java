package com.project.bootcamp_project.core;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

/**
 * LOG CODES
 * save : 001 - 010
 * update : 011 - 020
 * delete : 021 - 030
 * findById : 031 - 040
 * findAll : 041 - 050
 */

public interface IService<T> {
    public ResponseEntity<Object> save(T t, HttpServletRequest request);

    public ResponseEntity<Object> update(UUID id, T t, HttpServletRequest request);

    public ResponseEntity<Object> delete(UUID id, HttpServletRequest request);

    public ResponseEntity<Object> findById(UUID id, HttpServletRequest request);

    public ResponseEntity<Object> findAll(HttpServletRequest request);
}
