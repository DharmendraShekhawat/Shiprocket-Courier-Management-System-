package com.dharmendra.myRocket.exceptionHandling;

public class OrderNotFoundException extends Throwable{

   public OrderNotFoundException(String str){
       super(str);
   }
}
