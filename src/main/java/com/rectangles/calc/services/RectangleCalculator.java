package com.rectangles.calc.services;

import com.rectangles.calc.entities.Point;
import com.rectangles.calc.entities.Rectangle;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RectangleCalculator {

    /**
     *
     * @param rec1
     * @param rec2
     * @return list of intersection points
     * if the list is empty, there is not intersection
     * if the list contains 4 points means that there is a total intersection
     * if the list contains 3 points means that there is a partial intersection
     */
    public List<Point> findIntersectionPoints(Rectangle rec1, Rectangle rec2)
    {

        List<Point> intersectionPoints = new ArrayList<>();
        int bottomLeftX = Math.max(rec1.getBottomLeft().getX(), rec2.getBottomLeft().getX()); //2
        int bottomLeftY = Math.max(rec1.getBottomLeft().getY(), rec2.getBottomLeft().getY()); //2

        int topRightX = Math.min(rec1.getTopRight().getX(), rec2.getTopRight().getX()); //3
        int topRightY = Math.min(rec1.getTopRight().getY(), rec2.getTopRight().getY()); //4

        if (!(bottomLeftX < topRightX) || !(bottomLeftY < topRightY))
        {
            //the rectangles do not have any intersection
            return intersectionPoints;
        }

        Point topRight = new Point(topRightX, topRightY);
        Point bottomLeft = new Point(bottomLeftX, bottomLeftY);
        Point topLeft = new Point(bottomLeftX, topRightY);
        Point bottomRight = new Point(topRightX, bottomLeftY);

        //two rectangles must match in at list two intersection points
        //if they do not we should look up´in the next rectangle
        //if the next rectangle return 4 points is because they match all of them
        intersectionPoints = getIntersectionPoints(rec2, topRight, bottomLeft, topLeft, bottomRight);
        if (intersectionPoints.size() > 2){
            intersectionPoints = getIntersectionPoints(rec1, topRight, bottomLeft, topLeft, bottomRight);
        }

        return intersectionPoints;
    }

    private List<Point> getIntersectionPoints(Rectangle rectangle, Point topRight, Point bottomLeft, Point topLeft, Point bottomRight) {
        List<Point> intersectionPoints;
        List<Point> rectanglePoints = Arrays.asList(rectangle.getBottomLeft(), rectangle.getBottomRight(),
                rectangle.getTopLeft(), rectangle.getTopRight());

        List<Point> intersectionRectangle = Arrays.asList(topLeft, topRight, bottomLeft, bottomRight);

        intersectionPoints = intersectionRectangle.stream()
                .filter(recInter -> !rectanglePoints.stream().anyMatch(recExter -> recInter.equals(recExter)))
                .collect(Collectors.toList());
        return intersectionPoints;
    }

    /**
     *
     * @param rec1
     * @param rec2
     * @return a boolean which indicates if there is containment among the rectangles
     * true if there is containment
     * false if there is not containment
     */
    public boolean isContainmentRectangle(Rectangle rec1, Rectangle rec2){
        if(isContainRectangle(rec1, rec2) || (isContainRectangle(rec2, rec1))){
            return true;
        }
        return false;
    }

    private boolean isContainRectangle(Rectangle rec1, Rectangle rec2) {
                // look x - axis
        return (rec2.getBottomLeft().getX() >= rec1.getBottomLeft().getX()
                && rec1.getTopRight().getX() >= rec2.getTopRight().getX())
                // look y - axis
                && (rec2.getBottomLeft().getY() >= rec1.getBottomLeft().getY()
                && rec1.getTopRight().getY() >= rec2.getTopRight().getY());
    }

    /**
     *
     * @param rect1
     * @param rect2
     * @return a boolean to indicate if there is adjacency among the rectangles
     * false there is not adjacency
     * true there is adjacency
     */
    public boolean isAdjacencyRectangle(Rectangle rect1, Rectangle rect2){

        //cover edge case if rectangle is adjacent inside
        if(isContainmentRectangle(rect1, rect2)){
            return false;
        }

        if(isAdjacentRightSide(rect1, rect2) || isAdjacentRightSide(rect2, rect1)){
            return true;
        }

        if(isAdjacentTopSide(rect1, rect2) || isAdjacentTopSide(rect2, rect1)){
            return true;
        }

        return false;
    }

    /**
     *
     * @param rect1
     * @param rect2
     * @return a boolean indicates if there is adjacency in top or bottom side
     */
    private boolean isAdjacentTopSide(Rectangle rect1, Rectangle rect2) {
        return (rect2.getBottomLeft().getY() == rect1.getTopRight().getY())
                && rect2.getBottomLeft().getX() > rect1.getTopLeft().getX()
                && rect2.getBottomLeft().getX() < rect1.getTopRight().getX();
    }

    /**
     *
     * @param rect1
     * @param rect2
     * @return a boolean indicates if there is adjacency in right or left side
     */
    private boolean isAdjacentRightSide(Rectangle rect1, Rectangle rect2) {
        return  (rect1.getBottomRight().getX() == rect2.getBottomLeft().getX())
                && rect2.getBottomLeft().getY() > rect1.getBottomRight().getY()
                && rect2.getBottomLeft().getY() < rect1.getTopLeft().getY();
    }

}
