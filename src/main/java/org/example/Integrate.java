package org.example;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Random;

public class Integrate implements RequestHandler<Object, Object> {

    public static long integrate(double a, double b, long pointCount){
        long startTime = System.currentTimeMillis();
        double sum = 0;
        Random rand = new Random();
        for(long i = 0; i < pointCount; i++){
            double pointX = rand.nextDouble();
            double pointY = Math.sin(pointX) / pointX;
            sum += pointY;
        }
        double area = (sum / pointCount) * (b-a);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long integrate_time(double a, double b, long duration){
        long startTime = System.currentTimeMillis();
        double sum = 0;
        long pointCount = 0;
        Random rand = new Random();
        for(; System.currentTimeMillis() < startTime + duration; pointCount++){
            double pointX = rand.nextDouble();
            double pointY = Math.sin(pointX) / pointX;
            sum += pointY;
        }
        double area = (sum / pointCount) * (b-a);
        return pointCount;
    }

    @Override
    public Object handleRequest(Object o, Context context) {
        System.out.println(integrate_time(-1,1, 8000));
        return null;
    }
}