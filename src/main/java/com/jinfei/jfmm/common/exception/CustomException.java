package com.jinfei.jfmm.common.exception;

public class CustomException extends Exception
{
    private static final long serialVersionUID = 1L;

    public CustomException(String e)
    {
        super(e, null);
    }
}

