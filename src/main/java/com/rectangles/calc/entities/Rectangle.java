package com.rectangles.calc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rectangle {
    private Point topLeft;
    private Point topRight;
    private Point bottomLeft;
    private Point bottomRight;
}
