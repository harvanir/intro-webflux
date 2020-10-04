package org.harvanir.demo.introwebflux.webmvc.service;

/** @author Harvan Irsyadi */
public class DataNotFoundException extends RuntimeException {

  public DataNotFoundException(String message) {
    super(message);
  }
}
