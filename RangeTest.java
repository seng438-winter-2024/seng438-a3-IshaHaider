package org.jfree.data;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;


public class RangeTest {

	
//---------------- METHOD BEING TESTED: combine() ---------------- //
	
	/**
    * Purpose: Checking if the combine() method results in null if two null ranges are combined
    * Type of tests involved: equivalence class testing
    */
	@Test 
	public void testCombineBothNullRanges(){
		assertNull("Combining both null ranges should result in null",Range.combine(null, null));
	}
	
	/**
    * Purpose: Checking if the combine() method results in null if the first argument is a null range
    * Type of tests involved: equivalence class testing
    */
	@Test
	public void testCombineFirstRangeIsNull(){
		Range range1 = new Range(5, 10);
		assertNull("Combining with the first range being null should result in the return of the other range",Range.combine(range1, null));
	}
	
	/**
    * Purpose: Checking if the combine() method results in null if the second argument is a null range
    * Type of tests involved: equivalence class testing
    */
	@Test 
	public void testCombineSecondRangeIsNull(){
		Range range2 = new Range(4, 6);
		assertNull("Combining with the second range being null should result in the return of the other range",Range.combine(null, range2));
			
	}
	
	/**
    * Purpose: Checking if the combine() method returns an exception if the two ranges being combined overlap
    * Type of tests involved: exception testing, boundary value testing
    */
   @Test
   public void testCombineBothRangesAreNotNullOverlapping() {
       Range range1 = new Range(1, 5);
       Range range2 = new Range(4, 8);

       try {
           Range combinedRange = Range.combine(range1, range2);
           fail("Expected IllegalArgumentException, but got combined range: " + combinedRange);
       } catch (IllegalArgumentException e) {
           // Expected IllegalArgumentException
           assertEquals("Incorrect exception message for combining overlapping ranges", "Invalid range combination: ranges must not overlap", e.getMessage());
       }
   }

   /**
    * Purpose: Checking if the combine() method returns the expected value if two non-null and non-overlapping ranges are combined
    * Type of tests involved: equivalence class testing
    */
   @Test
   public void testCombineBothRangesAreNotNullNonOverlapping() {
       Range range1 = new Range(3, 5);
       Range range2 = new Range(1, 15);
       Range expectedResult = new Range(1, 15);
       assertEquals("Combining non-null non-overlapping ranges should result in the expected range", expectedResult, Range.combine(range1, range2));
   }

   /**
    * Purpose: Checking if the combine() method returns an exception if the two ranges being combined are the same
    * Type of tests involved: exception testing, boundary value testing
    */
   @Test
   public void testCombineSameRanges() {
       Range emptyRange1 = new Range(7, 7);
       Range emptyRange2 = new Range(10, 10);

       try {
           Range combinedRange = Range.combine(emptyRange1, emptyRange2);
           assertNull("Expected IllegalArgumentException, but got combined range: " + combinedRange, combinedRange);
       } catch (IllegalArgumentException e) {
           // Expected IllegalArgumentException
           assertEquals("Incorrect exception message for combining same ranges", "Invalid range combination: ranges must not be empty", e.getMessage());
       }
   }
   

//---------------- METHOD BEING TESTED: getCentralValue() ---------------- //
   
   /**
    * Purpose: Checking if the getCentralValue() method returns the correct result for the minimum possible range value
    * Type of tests involved: boundary value testing (LB)
    */
   @Test
   public void testGetCentralValueMinimumValidRange() {
       Range range = new Range(Double.MIN_VALUE, Double.MIN_VALUE + 1);
       assertEquals("Incorrect central value for minimum valid range", Double.MIN_VALUE + 0.5, range.getCentralValue(), 0);
   }

   /**
    * Purpose: Checking if the getCentralValue() method returns the correct result for the maximum possible range value
    * Type of tests involved: boundary value testing (UB)
    */
   @Test
   public void testGetCentralValueMaximumValidRange() {
       Range range = new Range(Double.MAX_VALUE - 1, Double.MAX_VALUE);
       assertEquals("Incorrect central value for maximum valid range", Double.MAX_VALUE - 0.5, range.getCentralValue(), 0);
   }

   /**
    * Purpose: Checking if the getCentralValue() method returns the correct result for an arbitrary valid range
    * Type of tests involved: equivalent class testing, boundary value testing (NOM)
    */
   @Test
   public void testGetCentralValueValidRange() {
       Range range = new Range(1, 5);
       assertEquals("Incorrect central value for valid range", 3, range.getCentralValue(), 0);
   }

   /**
    * Purpose: Checking if the getCentralValue() method returns an exception if tested with an invalid range
    * Type of tests involved: exception testing
    */
   @Test
   public void testGetCentralValueInvalidRange() {
       try {
       	Range range = new Range(5, 1);
           double centralValue = range.getCentralValue();
           fail("Expected IllegalArgumentException, but got central value: " + centralValue);
       } catch (IllegalArgumentException e) {
           // Expected IllegalArgumentException
           assertEquals("Incorrect central value for invalid range", "Invalid range: end value must be greater than or equal to start value", e.getMessage());
       }
   }
   
   /**
    * Purpose: Checking if the getCentralValue() method returns the same value as the single point for a single point range
    * Type of tests involved: equivalent class testing
    */
   @Test
   public void testGetCentralValueSinglePointRange() {
       Range range = new Range(3, 3);
       assertEquals("Incorrect central value for single-point range", 3, range.getCentralValue(), 0);
   }

   /**
    * Purpose: Checking if the getCentralValue() method returns a null double value if an empty range is given
    * Type of tests involved: equivalent class testing, boundary value testing (BLB)
    */
   @Test
   public void testGetCentralValueEmptyRange() {
       Range range = new Range(5, 5);
       assertEquals("Incorrect central value for empty range", Double.NaN, range.getCentralValue(), 0);
   }

   /**
    * Purpose: Checking if the getCentralValue() method returns a null double value if an entire range of doubles is given
    * Type of tests involved: equivalent class testing, boundary value testing (BLB)
    */
   @Test
   public void testGetCentralValueEntireRangeOfDoubles() {
       Range range = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
       assertEquals("Incorrect central value for entire range of doubles", 0, range.getCentralValue(), 0);
   }

   @Test
   public void testGetCentralValueTwoRangesWithDifferentWidths() {
       Range range1 = new Range(1, 5);
       Range range2 = new Range(10, 15);

       try {
           double centralValue = Range.combine(range1, range2).getCentralValue();
           fail("Expected IllegalArgumentException, but got central value: " + centralValue);
       } catch (IllegalArgumentException e) {
           // Expected IllegalArgumentException
           assertEquals("Incorrect central value for two ranges with different widths", "Invalid ranges for combining", e.getMessage());
       }
   }
  
   
//---------------- METHOD BEING TESTED: getLength() ---------------- //

   /**
    * Purpose: Checking if the getLength() method returns the correct length for a valid positive range
    * Type of tests involved: equivalence class testing
    */
   @Test
   public void testGetLengthValidPositiveRange() {
       Range range = new Range(3, 8);
       assertEquals("Incorrect length for valid positive range", 5, range.getLength(), 0);
   }

   /**
    * Purpose: Checking if the getLength() method returns the correct length for a valid negative range
    * Type of tests involved: equivalence class testing
    */
   @Test
   public void testGetLengthValidNegativeRange() {
       Range range = new Range(-5, -2);
       assertEquals("Incorrect length for valid negative range", 3, range.getLength(), 0);
   }

   /**
    * Purpose: Checking if the getLength() method returns the correct length for a valid mixed range
    * Type of tests involved: equivalence class testing
    */
   @Test
   public void testGetLengthValidMixedRange() {
       Range range = new Range(-3, 4);
       assertEquals("Incorrect length for valid mixed range", 7, range.getLength(), 0);
   }


   @Test
   public void testgetlength() {
       Range range = new Range(5, 10);
       double length = range.getLength();
       assertEquals(5, length, 0); 
   }

   @Test
   public void testlowerequalbounds() {
       Range range = new Range(5, 5);
       double length = range.getLength();
       assertEquals(0, length, 0); 
   }

   // 70% coverage
   @Test
   public void seventyCoverage() {
       Range range = new Range(5, 5);
       double length = range.getLength();
       assertEquals(0, length, 0); 
   }

   // 60% coverage
   @Test
   public void sixtyCoverage() {
       Range range1 = new Range(5, 5);
       Range range2 = new Range(5, 10);
       double length1 = range1.getLength();
       double length2 = range2.getLength();
       assertEquals(0, length1, 0);
       assertEquals(5, length2, 0);
   }

   
//---------------- METHOD BEING TESTED: constrain() ---------------- //

   /**
    * Purpose: Checking if the constrain() method returns the correct value for a value within the range
    * Type of tests involved: equivalence class testing
    */
   @Test
   public void testConstrainWithinRange() {
       Range range = new Range(3, 8);
       assertEquals("Incorrect constrained value for value within the range", 5, range.constrain(5), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for a value at the lower bound
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testConstrainAtLowerBound() {
       Range range = new Range(3, 8);
       assertEquals("Incorrect constrained value for value at the lower bound", 3, range.constrain(3), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for a value at the upper bound
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testConstrainAtUpperBound() {
       Range range = new Range(3, 8);
       assertEquals("Incorrect constrained value for value at the upper bound", 8, range.constrain(8), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for a value below the lower bound
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testConstrainBelowLowerBound() {
       Range range = new Range(3, 8);
       assertEquals("Incorrect constrained value for value below the lower bound", 3, range.constrain(1), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for a value above the upper bound
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testConstrainAboveUpperBound() {
       Range range = new Range(3, 8);
       assertEquals("Incorrect constrained value for value above the upper bound", 8, range.constrain(10), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for the minimum valid range value
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testConstrainMinimumValidRangeValue() {
       Range range = new Range(0, 1);
       assertEquals("Incorrect constrained value for minimum valid range value", 0, range.constrain(Double.MIN_VALUE), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for the maximum valid range value
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testConstrainMaximumValidRangeValue() {
       Range range = new Range(Double.MAX_VALUE - 1, Double.MAX_VALUE);
       assertEquals("Incorrect constrained value for maximum valid range value", Double.MAX_VALUE - 1, range.constrain(Double.MAX_VALUE - 1), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for the minimum double value
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testConstrainMinimumDoubleValue() {
       Range range = new Range(-100, 100);
       assertEquals("Incorrect constrained value for minimum double value", -100, range.constrain(Double.MIN_VALUE), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for the maximum double value
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testConstrainMaximumDoubleValue() {
       Range range = new Range(-100, 100);
       assertEquals("Incorrect constrained value for maximum double value", 100, range.constrain(Double.MAX_VALUE), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for NaN
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testConstrainNaN() {
       Range range = new Range(0, 10);
       assertEquals("Incorrect constrained value for NaN", 0, range.constrain(Double.NaN), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for positive infinity
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testConstrainPositiveInfinity() {
       Range range = new Range(0, 10);
       assertEquals("Incorrect constrained value for positive infinity", 10, range.constrain(Double.POSITIVE_INFINITY), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for negative infinity
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testConstrainNegativeInfinity() {
       Range range = new Range(0, 10);
       assertEquals("Incorrect constrained value for negative infinity", 0, range.constrain(Double.NEGATIVE_INFINITY), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for a range with negative values
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testConstrainWithNegativeRange() {
       Range range = new Range(-5, 5);
       assertEquals("Incorrect constrained value for range with negative values", 0, range.constrain(0), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for a decimal value
    * Type of tests involved: equivalence class testing
    */
   @Test
   public void testConstrainDecimalValue() {
       Range range = new Range(1, 5);
       assertEquals("Incorrect constrained value for decimal value", 3.5, range.constrain(3.5), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for the minimum valid range
    * Type of tests involved: equivalence class testing
    */
   @Test
   public void testConstrainMinimumValidRange() {
       Range range = new Range(0, 1);
       assertEquals("Incorrect constrained value for minimum valid range", 0.5, range.constrain(0.5), 0);
   }

   /**
    * Purpose: Checking if the constrain() method returns the correct value for the maximum valid range
    * Type of tests involved: equivalence class testing
    */
   @Test
   public void testConstrainMaximumValidRange() {
       Range range = new Range(Double.MAX_VALUE - 1, Double.MAX_VALUE);
       assertEquals("Incorrect constrained value for maximum valid range", Double.MAX_VALUE - 0.5, range.constrain(Double.MAX_VALUE - 0.5), 0);
   }


// ---------------- METHOD BEING TESTED: contains() ---------------- //   

   /**
    * Purpose: Checking if the contains() method correctly identifies a value inside the range
    * Type of tests involved: equivalence class testing
    */
   @Test
   public void testContainsValueInsideRange() {
       Range range = new Range(5.0, 10.0);
       assertTrue(range.contains(7.5));
   }

   /**
    * Purpose: Checking if the contains() method correctly identifies a value at the lower bound
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testContainsValueAtLowerBound() {
       Range range = new Range(5.0, 10.0);
       assertTrue(range.contains(5.0));
   }

   /**
    * Purpose: Checking if the contains() method correctly identifies a value at the upper bound
    * Type of tests involved: boundary value testing
    */
   @Test
   public void testContainsValueAtUpperBound() {
       Range range = new Range(5.0, 10.0);
       assertTrue(range.contains(10.0));
   }

   /**
    * Purpose: Checking if the contains() method correctly identifies a value outside the range
    * Type of tests involved: equivalence class testing
    */
   @Test
   public void testContainsValueOutsideRange() {
       Range range = new Range(5.0, 10.0);
       assertFalse(range.contains(2.5));
   }

   /**
    * Purpose: Checking if the contains() method correctly identifies a value equal to the lower bound as outside the range
    * Type of tests involved: equivalence class testing
    */
   @Test
   public void testContainsValueEqualToLowerBound() {
       Range range = new Range(5.0, 10.0);
       assertFalse(range.contains(4.999));
   }

   /**
    * Purpose: Checking if the contains() method correctly identifies a value equal to the upper bound as outside the range
    * Type of tests involved: equivalence class testing
    */
   @Test
   public void testContainsValueEqualToUpperBound() {
       Range range = new Range(5.0, 10.0);
       assertFalse(range.contains(10.0001));
   }

// ---------------- ASSIGNMENT 3 Coverage tests ---------------- //
   
   // ---------------- Constructor tests ---------------- //

   @Test
   public void testValidRange() {
       // Test for a valid range, expect no exception
       Range range = new Range(1.0, 5.0);
       assertEquals(1.0, range.getLowerBound(), 0);
       assertEquals(1.0, range.getLowerBound(), 0);
       assertEquals(5.0, range.getUpperBound(), 0);
   }

   @Test
   public void testEqualBoundaries() {
       // Test for the case where lower and upper boundaries are equal, expect no exception
       Range range = new Range(3.0, 3.0);
       assertEquals(3.0, range.getLowerBound(),0);
       assertEquals(3.0, range.getUpperBound(),0);
   }

   // ---------------- getter tests ---------------- //

   private static final double DELTA = 1e-15;
   
   

   @Test
   // Statement Coverage
   public void testGetLowerBound() {
       // Test for a valid range, expect no exception
       Range range = new Range(1.0, 5.0);
       assertEquals(1.0, range.getLowerBound(), DELTA);
   }
   @Test
   public void testGetLowerBoundValidRange() {
       // Valid range, should return lower bound
       Range range = new Range(0.0, 1.0);
       assertEquals(0.0, range.getLowerBound(), 0.0001);
   }
   
   @Test
   public void testGetLowerBoundInvalidRange() {
       // Invalid range, should throw an IllegalArgumentException
       try {
           new Range(1.0, 5.0); // Invalid range where lower > upper
           fail("Expected IllegalArgumentException");
       } catch (IllegalArgumentException e) {
           // Expected
       }
   }

   @Test
   // Condition Coverage
   public void testGetLowerBoundWithValidRange() {
       Range validRange = new Range(1.0, 5.0);

       // Ensure no exception is thrown for a valid range
       assertEquals(1.0, validRange.getLowerBound(), DELTA);
   }
   @Test
   // Statement Coverage
   public void testGetUpperBound() {
       // Test for a valid range, expect no exception
       Range range = new Range(1.0, 5.0);
       assertEquals(5.0, range.getUpperBound(), DELTA);
   }

   @Test
   // Statement Coverage
   public void testGetLength() {
       // Test for a valid range, expect no exception
       Range range = new Range(1.0, 5.0);
       assertEquals(4.0, range.getLength(), DELTA);
   }

   @Test
   // Statement Coverage
   public void testGetCentralValue() {
       // Test for a valid range, expect no exception
       Range range = new Range(1.0, 5.0);
       assertEquals(3.0, range.getCentralValue(), DELTA);
   }

   // ---------------- contains tests ---------------- //

   @Test
   // Statement Coverage
   public void testContainsInsideRange() {
       Range range = new Range(1.0, 5.0);
       assertTrue(range.contains(3.0));
   }

   @Test
   // Statement Coverage
   public void testContainsOutsideRange() {
       Range range = new Range(1.0, 5.0);
       assertFalse(range.contains(6.0));
   }
   // ---------------- intersect tests ---------------- //


   @Test
   // Condition Coverage
   public void testIntersectsLowerBoundary() {
       Range range = new Range(1.0, 5.0);
       assertTrue(range.intersects(0.0, 2.0));
   }

   @Test
   // Condition Coverage
   public void testIntersectsUpperBoundary() {
       Range range = new Range(1.0, 5.0);
       assertTrue(range.intersects(4.0, 6.0));
   }

   @Test
   // Condition Coverage
   public void testIntersectsInsideRange() {
       Range range = new Range(1.0, 5.0);
       assertTrue(range.intersects(2.0, 4.0));
   }

   @Test
   // Condition Coverage
   public void testIntersectsOutsideRange() {
       Range range = new Range(1.0, 5.0);
       assertFalse(range.intersects(6.0, 8.0));
   }

   @Test
   // Condition Coverage
   public void testIntersectsOverlapLowerBoundary() {
       Range range = new Range(1.0, 5.0);
       assertTrue(range.intersects(0.0, 4.0));
   }

   @Test
   // Condition Coverage
   public void testIntersectsOverlapUpperBoundary() {
       Range range = new Range(1.0, 5.0);
       assertTrue(range.intersects(2.0, 6.0));
   }

   // ---------------- constrain new tests ---------------- //


   @Test
   // Statement Coverage
   public void testConstrainInsideRangeNew() {
       Range range = new Range(1.0, 5.0);
       assertEquals(3.0, range.constrain(3.0), DELTA);
   }

   @Test
   // Statement Coverage
   public void testConstrainBelowLowerBoundNew() {
       Range range = new Range(1.0, 5.0);
       assertEquals(1.0, range.constrain(0.0), DELTA);
   }

   @Test
   // Statement Coverage
   public void testConstrainAboveUpperBoundNew() {
       Range range = new Range(1.0, 5.0);
       assertEquals(5.0, range.constrain(6.0), DELTA);
   }

   @Test
   // Statement Coverage
   public void testConstrainInsideRangeEqualBounds() {
       Range range = new Range(3.0, 3.0);
       assertEquals(3.0, range.constrain(2.0), DELTA);
   }

   @Test
   // Statement Coverage
   public void testConstrainWithNaNRange() {
       Range range = new Range(Double.NaN, Double.NaN);
       assertEquals(Double.NaN, range.constrain(3.0), DELTA);
   }
// ---------------- METHOD BEING TESTED: combineIgnoringNaN() ---------------- //   

   @Test
   // Condition Coverage
   public void testCombineIgnoringNaNWithValidRanges() {
       Range range1 = new Range(1.0, 5.0);
       Range range2 = new Range(6.0, 10.0);
       Range result = Range.combineIgnoringNaN(range1, range2);
       assertNotNull(result);
       assertEquals(1.0, result.getLowerBound(), DELTA);
       assertEquals(10.0, result.getUpperBound(), DELTA);
   }

   @Test
   // Additional test for returning null when both ranges are NaN
   public void testCombineIgnoringNaNWithBothRangesNaN_ReturnsNull() {
       Range range1 = new Range(Double.NaN, Double.NaN);
       Range range2 = new Range(Double.NaN, Double.NaN);
       Range result = Range.combineIgnoringNaN(range1, range2);
       assertNull(result);
   }

   @Test
   // Statement Coverage
   public void testCombineIgnoringNaNWithNullRange1() {
       Range range2 = new Range(1.0, 5.0);
       Range result = Range.combineIgnoringNaN(null, range2);
       assertNotNull(result); // Adjusted assertion to ensure result is not null
       assertEquals(1.0, result.getLowerBound(), DELTA);
       assertEquals(5.0, result.getUpperBound(), DELTA);
   }

   @Test
   // Statement Coverage
   public void testCombineIgnoringNaNWithNullRange2() {
       Range range1 = new Range(1.0, 5.0);
       Range result = Range.combineIgnoringNaN(range1, null);
       assertNotNull(result); 
       assertEquals(1.0, result.getLowerBound(), DELTA);
       assertEquals(5.0, result.getUpperBound(), DELTA);
   }

   @Test
   // Statement Coverage
   public void testCombineIgnoringNaNWithBothNullRanges() {
       Range result = Range.combineIgnoringNaN(null, null);
       assertNull(result);
   }
   
   
   @Test
   public void testCombineRangesWhenRange2IsNull() {
       // Test when range2 is null

       // Mock or create a Range object for range1
       Range range1 = new Range(0.0, 1.0);

       // Ensure that if range2 is null, it returns range1
       Range result = Range.combineIgnoringNaN(range1, null);

       // Assert that the result is the same as range1
       assertEquals(range1, result);
   }

   @Test
   public void testCombineRangesWhenRange1IsNaN() {
       // Test when range1 is NaN

       // Mock or create a Range object for range2
       Range range2 = new Range(2.0, 3.0);

       // Mock a Range object for range1 with isNaNRange() returning true
       Range range1 = new Range(0.0, 0.0) {
           @Override
           public boolean isNaNRange() {
               return true;
           }
       };

       // Ensure that if range2 is not null, and range1 is NaN, it returns null
       Range result = Range.combineIgnoringNaN(range1, range2);

       // Assert that the result is null
       assertNull(result);
   }

   @Test
   public void testCombineRangesWhenNeitherRangeIsNullNorRange1IsNaN() {
       Range range1 = new Range(0.0, 1.0);
       Range range2 = new Range(2.0, 3.0);

       Range result = Range.combineIgnoringNaN(range1, range2);

       assertEquals(range1, result);
   }

// ---------------- METHOD BEING TESTED: expandToInclude() ---------------- //   

   @Test
   // Statement Coverage
   public void testExpandToIncludeWithNullRange() {
       Range result = Range.expandToInclude(null, 5.0);
       assertNotNull(result);
       assertEquals(5.0, result.getLowerBound(), DELTA);
       assertEquals(5.0, result.getUpperBound(), DELTA);
   }

   @Test
   // Branch Coverage
   public void testExpandToIncludeWithValueBelowLowerBound() {
       Range range = new Range(3.0, 7.0);
       double value = 1.0;
       Range result = Range.expandToInclude(range, value);
       assertNotNull(result);
       assertEquals(1.0, result.getLowerBound(), DELTA);
       assertEquals(7.0, result.getUpperBound(), DELTA);
   }

   @Test
   // Branch Coverage
   public void testExpandToIncludeWithValueAboveUpperBound() {
       Range range = new Range(3.0, 7.0);
       double value = 9.0;
       Range result = Range.expandToInclude(range, value);
       assertNotNull(result);
       assertEquals(3.0, result.getLowerBound(), DELTA);
       assertEquals(9.0, result.getUpperBound(), DELTA);
   }

   @Test
   // Branch Coverage
   public void testExpandToIncludeWithValueInsideRange() {
       Range range = new Range(3.0, 7.0);
       double value = 5.0;
       Range result = Range.expandToInclude(range, value);
       assertNotNull(result);
       assertEquals(3.0, result.getLowerBound(), DELTA);
       assertEquals(7.0, result.getUpperBound(), DELTA);
   }

   @Test
   // Condition Coverage
   public void testExpandToIncludeWithNullRangeAndValue() {
       Range result = Range.expandToInclude(null, Double.NaN);
       assertNotNull(result);
       assertEquals(Double.NaN, result.getLowerBound(), DELTA);
       assertEquals(Double.NaN, result.getUpperBound(), DELTA);
   }

   @Test
   // Condition Coverage
   public void testExpandToIncludeWithNullRangeAndInfinityValue() {
       Range result = Range.expandToInclude(null, Double.POSITIVE_INFINITY);
       assertNotNull(result);
       assertEquals(Double.POSITIVE_INFINITY, result.getLowerBound(), DELTA);
       assertEquals(Double.POSITIVE_INFINITY, result.getUpperBound(), DELTA);
   }

   @Test
   // Condition Coverage
   public void testExpandToIncludeWithInfinityLowerBound() {
       Range range = new Range(Double.NEGATIVE_INFINITY, 7.0);
       double value = 5.0;
       Range result = Range.expandToInclude(range, value);
       assertNotNull(result);
       assertEquals(Double.NEGATIVE_INFINITY, result.getLowerBound(), DELTA);
       assertEquals(7.0, result.getUpperBound(), DELTA);
   }

   @Test
   // Condition Coverage
   public void testExpandToIncludeWithInfinityUpperBound() {
       Range range = new Range(3.0, Double.POSITIVE_INFINITY);
       double value = 5.0;
       Range result = Range.expandToInclude(range, value);
       assertNotNull(result);
       assertEquals(3.0, result.getLowerBound(), DELTA);
       assertEquals(Double.POSITIVE_INFINITY, result.getUpperBound(), DELTA);
   }

   @Test
   // Condition Coverage
   public void testExpandToIncludeWithNaNLowerBound() {
       Range range = new Range(Double.NaN, 7.0);
       double value = 5.0;
       Range result = Range.expandToInclude(range, value);
       assertNotNull(result);
       assertEquals(Double.NaN, result.getLowerBound(), DELTA);
       assertEquals(7.0, result.getUpperBound(), DELTA);
   }

   @Test
   // Condition Coverage
   public void testExpandToIncludeWithNaNUpperBound() {
       Range range = new Range(3.0, Double.NaN);
       double value = 5.0;
       Range result = Range.expandToInclude(range, value);
       assertNotNull(result);
       assertEquals(3.0, result.getLowerBound(), DELTA);
       assertEquals(Double.NaN, result.getUpperBound(), DELTA);
   }
   
// ---------------- METHOD BEING TESTED: expand() ---------------- //   
   

   @Test
   // Statement Coverage
   public void testExpand() {
       Range baseRange = new Range(1.0, 5.0);
       double lowerMargin = 0.1;
       double upperMargin = 0.2;

       Range result = Range.expand(baseRange, lowerMargin, upperMargin);

       assertNotNull(result);
       assertEquals(0.8, result.getLowerBound(), DELTA);
       assertEquals(6.2, result.getUpperBound(), DELTA);
   }

   @Test
   // Branch Coverage
   public void testExpandWithNegativeMargins() {
       Range baseRange = new Range(1.0, 5.0);
       double lowerMargin = -0.1;
       double upperMargin = -0.2;

       Range result = Range.expand(baseRange, lowerMargin, upperMargin);

       assertNotNull(result);
       assertEquals(1.0, result.getLowerBound(), DELTA);
       assertEquals(5.0, result.getUpperBound(), DELTA);
   }
   
   @Test
	// Additional Branch Coverage for if (lower > upper)
	public void testExpandWithFlippedMargins() {
	    Range baseRange = new Range(1.0, 5.0);
	    double lowerMargin = 0.3; // choose a margin that will make lower > upper
	    double upperMargin = 0.2;
	
	    Range result = Range.expand(baseRange, lowerMargin, upperMargin);
	
	    assertNotNull(result);
	    assertTrue(result.getLowerBound() <= result.getUpperBound());
	}
   
   @Test
	// Direct Test for if (lower > upper)
	public void testExpandAdjustmentWhenLowerGreaterThanUpper() {
	    Range baseRange = new Range(1.0, 5.0);
	    double lowerMargin = 0.3; // choose a margin that will make lower > upper
	    double upperMargin = 0.2;
	
	    // Force the condition (lower > upper) to be true
	    Range result = Range.expand(baseRange, lowerMargin, upperMargin);
	    
	    // Check the adjusted values
	    assertEquals((result.getLowerBound() + result.getUpperBound()) / 2.0, result.getLowerBound(), DELTA);
	    assertEquals((result.getLowerBound() + result.getUpperBound()) / 2.0, result.getUpperBound(), DELTA);
	}
// ---------------- METHOD BEING TESTED: shift() ---------------- //   

   @Test
   // Statement Coverage
   public void testShift() {
       Range baseRange = new Range(1.0, 5.0);
       double delta = 2.0;

       Range result = Range.shift(baseRange, delta);

       assertNotNull(result);
       assertEquals(3.0, result.getLowerBound(), DELTA);
       assertEquals(7.0, result.getUpperBound(), DELTA);
   }

   @Test
   // Branch Coverage
   public void testShiftWithZeroCrossingAllowed() {
       Range baseRange = new Range(-2.0, 2.0);
       double delta = 3.0;

       Range result = Range.shift(baseRange, delta, true);

       assertNotNull(result);
       assertEquals(1.0, result.getLowerBound(), DELTA);
       assertEquals(5.0, result.getUpperBound(), DELTA);
   }

   @Test
   // Branch Coverage
   public void testShiftWithZeroCrossingNotAllowed() {
       Range baseRange = new Range(-2.0, 2.0);
       double delta = 3.0;

       Range result = Range.shift(baseRange, delta, false);

       assertNotNull(result);
       assertEquals(-1.0, result.getLowerBound(), DELTA);
       assertEquals(1.0, result.getUpperBound(), DELTA);
   }
  
   
// ---------------- METHOD BEING TESTED: ShiftWithNoZeroCrossing() ---------------- //   

//    @Test
//    // Condition Coverage for value > 0.0
//    public void testShiftWithNoZeroCrossingPositiveValue() {
//        double result = Range.shiftWithNoZeroCrossing(5.0, 2.0);
//        assertEquals(7.0, result, DELTA);
//    }

//    @Test
//    // Condition Coverage for value < 0.0
//    public void testShiftWithNoZeroCrossingNegativeValue() {
//        double result = Range.shiftWithNoZeroCrossing(-5.0, 2.0);
//        assertEquals(-3.0, result, DELTA);
//    }

//    @Test
//    // Condition Coverage for value = 0.0
//    public void testShiftWithNoZeroCrossingZeroValue() {
//        double result = Range.shiftWithNoZeroCrossing(0.0, 2.0);
//        assertEquals(2.0, result, DELTA);
//    }

   
// ---------------- METHOD BEING TESTED: shift() ---------------- //   

   @Test
   public void testScaleWithNegativeFactor() {
       Range baseRange = new Range(1.0, 5.0);

       try {
           Range result = Range.scale(baseRange, -2.0);
           fail("Expected IllegalArgumentException, but got result: " + result);
       } catch (IllegalArgumentException e) {
           // Expected exception
       }
   }

@Test
   // Condition Coverage for factor >= 0
   public void testScaleWithNonNegativeFactor() {
       Range baseRange = new Range(1.0, 5.0);
       Range result = Range.scale(baseRange, 2.0);
       assertNotNull(result);
       assertEquals(2.0, result.getLowerBound(), DELTA);
       assertEquals(10.0, result.getUpperBound(), DELTA);
   }

   @Test
   // Statement Coverage for equals
   public void testEquals() {
       Range range1 = new Range(1.0, 5.0);
       Range range2 = new Range(1.0, 5.0);
       Range range3 = new Range(2.0, 6.0);

       assertTrue(range1.equals(range2));
       assertFalse(range1.equals(range3));
   }
// ---------------- METHOD BEING TESTED: hashCode() ---------------- //   
  @Test
   public void testHashCode() {
       Range range1 = new Range(1.0, 5.0);
       Range range2 = new Range(1.0, 5.0);

       assertEquals("Hash codes should be equal", range1.hashCode(), range2.hashCode());
   }
//---------------- METHOD BEING TESTED: equals() ---------------- //   

  @Test
  // Statement Coverage
  public void testEqualsForEqualObjects() {
      Range range1 = new Range(1.0, 5.0);
      Range range2 = new Range(1.0, 5.0);
      assertTrue("Objects should be equal", range1.equals(range2));
  }

  @Test
  // Statement Coverage
  public void testEqualsForNonEqualObjects() {
      Range range1 = new Range(1.0, 5.0);
      Range range3 = new Range(2.0, 6.0);
      assertFalse("Objects should not be equal", range1.equals(range3));
  }

  @Test
  // Statement Coverage
  public void testEqualsAgainstNull() {
      Range range1 = new Range(1.0, 5.0);
      assertFalse("Object should not be equal to null", range1.equals(null));
  }

  @Test
  // Statement Coverage
  public void testEqualsAgainstDifferentType() {
      Range range1 = new Range(1.0, 5.0);
      assertFalse("Object should not be equal to a different type", range1.equals("not a Range"));
  }

  @Test
  // Statement Coverage
  public void testEqualsWithinDelta() {
      Range range1 = new Range(1.0, 5.0);
      Range range4 = new Range(1.0, 5.0 + DELTA / 2);
      assertTrue("Objects should be equal within delta", range1.equals(range4));
  }

  @Test
  // Statement Coverage
  public void testEqualsBeyondDelta() {
      Range range1 = new Range(1.0, 5.0);
      Range range5 = new Range(1.0, 5.0 + DELTA * 2);
      assertFalse("Objects should not be equal beyond delta", range1.equals(range5));
  }
  
  

}
