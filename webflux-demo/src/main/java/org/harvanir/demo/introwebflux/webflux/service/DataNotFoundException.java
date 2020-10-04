package org.harvanir.demo.introwebflux.webflux.service;

/** @author Harvan Irsyadi */
public class DataNotFoundException extends RuntimeException {

  public DataNotFoundException(String message) {
    super(message);
  }
}
