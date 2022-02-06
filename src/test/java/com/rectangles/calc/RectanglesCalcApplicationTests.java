package com.rectangles.calc;

import com.rectangles.calc.entities.Point;
import com.rectangles.calc.entities.Rectangle;
import com.rectangles.calc.services.RectangleCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class RectanglesCalcApplicationTests {

	@Autowired
	RectangleCalculator rectangleCalculator;

	@Test
	void isFoundIntersectionPointsRectanglesTest() {
		Point point1R1 = new Point(1,5);
		Point point2R1 = new Point(3,5);
		Point point3R1 = new Point(1,1);
		Point point4R1 = new Point(3,1);
		Rectangle rectangle1 = new Rectangle(point1R1, point2R1, point3R1, point4R1);
		Point point1R2 = new Point(2,4);
		Point point2R2 = new Point(6,4);
		Point point3R2 = new Point(2,2);
		Point point4R2 = new Point(6,2);
		Rectangle rectangle2 = new Rectangle(point1R2, point2R2, point3R2, point4R2);
		List<Point> result = rectangleCalculator.findIntersectionPoints(rectangle2, rectangle1);
		List<Point> expectedResult = Arrays.asList(new Point(3,4), new Point(3,2));
		Collections.sort(expectedResult);
		Collections.sort(result);
		Assertions.assertArrayEquals(expectedResult.toArray(), result.toArray());
	}

	@Test
	void isNotFoundIntersectionPointsRectanglesTest() {
		Point point1R1 = new Point(0,0);
		Point point2R1 = new Point(0,2);
		Point point3R1 = new Point(2,0);
		Point point4R1 = new Point(2,2);
		Rectangle rectangle1 = new Rectangle(point2R1, point4R1, point1R1, point3R1);
		Point point1R2 = new Point(3,1);
		Point point2R2 = new Point(3,3);
		Point point3R2 = new Point(5,1);
		Point point4R2 = new Point(5,3);
		Rectangle rectangle2 = new Rectangle(point1R2, point2R2, point3R2, point4R2);
		List<Point> result = rectangleCalculator.findIntersectionPoints(rectangle2, rectangle1);
		List<Point> expectedResult = new ArrayList<>();
		Collections.sort(expectedResult);
		Collections.sort(result);
		Assertions.assertArrayEquals(expectedResult.toArray(), result.toArray());
	}

	@Test
	void isContainingRectanglesTest() {
		Point point1R1 = new Point(1,1);
		Point point2R1 = new Point(1,5);
		Point point3R1 = new Point(5,1);
		Point point4R1 = new Point(5,5);
		Rectangle rectangle1 = new Rectangle(point2R1, point4R1, point1R1, point3R1);
		Point point1R2 = new Point(2,2);
		Point point2R2 = new Point(2,4);
		Point point3R2 = new Point(4,2);
		Point point4R2 = new Point(4,4);
		Rectangle rectangle2 = new Rectangle(point2R2, point4R2, point1R2, point3R2);
		Assertions.assertEquals(true, rectangleCalculator.isContainmentRectangle(rectangle2, rectangle1));
	}

	@Test
	void isNotContainingRectanglesTest() {
		Point point1R1 = new Point(1,1);
		Point point2R1 = new Point(1,5);
		Point point3R1 = new Point(5,1);
		Point point4R1 = new Point(5,5);
		Rectangle rectangle1 = new Rectangle(point2R1, point4R1, point1R1, point3R1);
		Point point1R2 = new Point(2,2);
		Point point2R2 = new Point(2,4);
		Point point3R2 = new Point(6,2);
		Point point4R2 = new Point(6,4);
		Rectangle rectangle2 = new Rectangle(point2R2, point4R2, point1R2, point3R2);
		Assertions.assertEquals(false, rectangleCalculator.isContainmentRectangle(rectangle2, rectangle1));
	}


	@Test
	void isAdjacentRectangleSideTest(){
		Point point1R1 = new Point(1,1);
		Point point2R1 = new Point(1,4);
		Point point3R1 = new Point(4,1);
		Point point4R1 = new Point(4,4);
		Rectangle rectangle1 = new Rectangle(point2R1, point4R1, point1R1, point3R1);
		Point point1R2 = new Point(4,2);
		Point point2R2 = new Point(4,5);
		Point point3R2 = new Point(6,2);
		Point point4R2 = new Point(6,5);
		Rectangle rectangle2 = new Rectangle(point2R2, point4R2, point1R2, point3R2);
		Assertions.assertEquals(true, rectangleCalculator.isAdjacencyRectangle(rectangle2, rectangle1));
	}

	@Test
	void isNotAdjacentRectangleRightSideTest(){
		Point point1R1 = new Point(1,1);
		Point point2R1 = new Point(1,4);
		Point point3R1 = new Point(4,1);
		Point point4R1 = new Point(4,4);
		Rectangle rectangle1 = new Rectangle(point2R1, point4R1, point1R1, point3R1);
		Point point1R2 = new Point(5,2);
		Point point2R2 = new Point(5,5);
		Point point3R2 = new Point(7,2);
		Point point4R2 = new Point(7,5);
		Rectangle rectangle2 = new Rectangle(point2R2, point4R2, point1R2, point3R2);
		Assertions.assertEquals(false, rectangleCalculator.isAdjacencyRectangle(rectangle1, rectangle2));
	}

	@Test
	void isAdjacentRectangleTopTest(){
		Point point1R1 = new Point(1,3);
		Point point2R1 = new Point(4,3);
		Point point3R1 = new Point(1,1);
		Point point4R1 = new Point(4,1);
		Rectangle rectangle1 = new Rectangle(point1R1, point2R1, point3R1, point4R1);
		Point point1R2 = new Point(2,4);
		Point point2R2 = new Point(5,4);
		Point point3R2 = new Point(2,3);
		Point point4R2 = new Point(5,3);
		Rectangle rectangle2 = new Rectangle(point1R2, point2R2, point3R2, point4R2);
		Assertions.assertEquals(true, rectangleCalculator.isAdjacencyRectangle(rectangle1, rectangle2));
	}

	@Test
	void isNotAdjacentRectangleTopTest(){
		Point point1R1 = new Point(1,3);
		Point point2R1 = new Point(4,3);
		Point point3R1 = new Point(1,1);
		Point point4R1 = new Point(4,1);
		Rectangle rectangle1 = new Rectangle(point1R1, point2R1, point3R1, point4R1);
		Point point1R2 = new Point(2,5);
		Point point2R2 = new Point(5,5);
		Point point3R2 = new Point(2,4);
		Point point4R2 = new Point(5,4);
		Rectangle rectangle2 = new Rectangle(point1R2, point2R2, point3R2, point4R2);
		Assertions.assertEquals(false, rectangleCalculator.isAdjacencyRectangle(rectangle1, rectangle2));
	}
}
