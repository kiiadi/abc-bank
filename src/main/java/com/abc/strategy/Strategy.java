package com.abc.strategy;

/**
 * Interface defining a method i.e. execute. Kind of Strategy. Can be implemented by any Class.
 * In this exercise an abstract class with implement this method and will make it final(AS in Template).
 *
 * Created by Archana on 9/14/14.
 */
public interface Strategy<T,V> {
  public V execute(T t);
}
