package com.abc.account;

/**
 * Interface declaring methods for lockable behaviour. Lockable meaning not in
 * any business sense. Any object that implements this interface is saying to the world
 * that using these methods lock / unlock methods of this interface, any client's thread
 * can invoke functions without adversely affecting the internal state of this implementing
 * object of any issues from other client's threads. Think about in the context of a customer
 * transferring from one account to another account. In this case, the customer thread can
 * acquire or lock the two account objects and then do the transfer. More like atomic unit of work.
 *
 * Created by Archana on 9/16/14.
 */
public interface Lockable {
  /**
   *  method to lock the object and get exclusive write permissions on the object.
   */
  public void lock();

  /**
   * method to unlock the object and release the exclusive write permissions on the object.
   */
  public void unlock();
}
