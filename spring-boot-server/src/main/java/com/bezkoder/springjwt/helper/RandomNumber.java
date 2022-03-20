package com.bezkoder.springjwt.helper;

import java.util.Random;

public class RandomNumber {

    public static int getRandomNumber()
    {
        Random random=new Random();
        return  random.nextInt(10000000);

    }


    public static int getRandomNumberByDigits(int digits)
    {
        Random random=new Random();
        return  random.nextInt(digits);

    }


    public static String getRandomNumberDynamically(int n)
    {
        String AlphaNumericString =  "0123456789";


        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {


            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());


            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }


   public static Long getRandomNumberLongType()
   {
       Random rd = new Random(); // creating Random object
       return rd.nextLong();
   }
}
