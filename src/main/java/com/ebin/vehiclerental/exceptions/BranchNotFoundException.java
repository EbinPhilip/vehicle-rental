package com.ebin.vehiclerental.exceptions;

public class BranchNotFoundException extends RuntimeException {
    public BranchNotFoundException()
    {
     super();
    }
    public BranchNotFoundException(String msg)
    {
     super(msg);
    }
}