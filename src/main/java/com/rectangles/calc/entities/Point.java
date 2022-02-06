package com.rectangles.calc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point implements Comparable<Point>{
    private int x;
    private int y;

    @Override
    public int compareTo(Point point) {
         int comp = Integer.compare(x, point.getX());
         if (comp == 0){
             comp = Integer.compare(y, point.getY());
         }
         return comp;
    }
}
