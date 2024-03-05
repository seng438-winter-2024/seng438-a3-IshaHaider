**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group \#:      |  17   |
| -------------- | --- |
| Student Names: |   Zahwa Fatima  |
|                |   Isha Haider  |
|                |   Saba Sadoughi |
|                |     |

(Note that some labs require individual reports while others require one report
for each group. Please see each lab document for details.)

# 1 Introduction

In this assignment, we'll be diving into unit testing once again, but with a different focus from the previous assignment. We'll continue to use JUnit within the Eclipse IDE for our testing endeavors. Unlike before, where our main task was implementing tests, this time around, we'll concentrate on understanding and utilizing various white-box coverage criteria to enhance our test suite. The primary objective here is to understand the concept of determining the adequacy of a white-box test suite based on its coverage of the code. We'll explore different criteria such as statement coverage, decision coverage, condition coverage, path coverage, and data-flow coverage including DU pairs coverage. This lab will get us comfortable using code coverage tools to assess test adequacy, designing test cases to improve code coverage, understanding the benefits and limitations of code coverage tools, and becoming proficient in data-flow coverage, with the ability to calculate it manually. Our goal will be to gain valuable insights into the significance of comprehensive test coverage and its role in ensuring the quality and reliability of software systems.


# 2 Manual data-flow coverage calculations for X and Y methods

## DataUtilities.calculateColumnTotal method:
### The data flow graph
<img width="501" alt="Screen Shot 2024-03-04 at 10 58 54 PM" src="https://github.com/seng438-winter-2024/seng438-a3-Sabayara82/assets/113862210/b4d29fb1-1d4b-429f-a6db-e60547eb2e13">

### The def-use sets per statement
Def-path set:
  - du(1, data) = {[1], [1,2,3], [1,2,3,4,5], [1,2,3,4,5,6,7,8,4,5]}
  - du(1, column) = {[1], [1,2,3,4,5], [1,2,3,4,5,6,7,8,4,5]}
  - du(2, total) = {[2,3,4,9], [2,3,4,5,6,7,8,4,9], [2,3,4,5,6,8,4,9]}
  - du(3, rowCount) = {[3,4], [3,4,5,6,7,8,4], [3,4,5,6,8,4]}
  - du(4, r) = {[4], [4,5,6,7,8,4], [4,5,6,8,4]}
  - du(5, n) = {[5,6], [5,6,7]}
Def-pair set:
  - du(1, 1, data) = {[1]}
  - du(1, 3, data) = {[1,2,3]}
  - du(1, 5, data) = {[1,2,3,4,5], [1,2,3,4,5,6,7,8,4,5]}
  - du(1, 5, column) = {[1,2,3,4,5], [1,2,3,4,5,6,7,8,4,5],}
  - du(2, 7, total) = {[2,3,4,5,6,7]}
  - du(2, 9, total) = {[2,3,4,9], [2,3,4,5,6,7,8,4,9], [2,3,4,5,6,8,4,9]}
  - du(3, 4, rowCount) = {[3,4], [3,4,5,6,7,8,4], [3,4,5,6,8,4]}
  - du(4, 4, r) = {[4], [4,5,6,7,8,4], [4,5,6,8,4]}
  - du(4, 5, r) = {[4, 5]}
  - du(4, 8, r) = {[4,5,6,7,8], [4,5,6,8]}
  - du(5, 6, n) = {[5,6]}
  - du(5, 7, n) = {[5,6,7]}

### All DU-pairs per variable
| Variable | Defined at Node | dcu         | dpu                    |
|----------|-----------------|-------------|------------------------|
| data     | 1               |{3,5}        |     {(1,2), (1,End)}   |
| column   | 1               |{5}          |   {}                 |
| total    | 2               | {7,9}       |     {}                   |
| rowCount | 3               | {}            | {4,5}, {4,9}           |
| r        | 4               | {5,8}       | {(4,5), (4,9)}         |
| n        | 5               | {7}         | {(6,7), (6,8)}     |


### which pairs are covered
| Test                                          | Pairs Covered |
|-----------------------------------------------|-------|
| testCalculateColumnTotalTwoValues()           | dcu {3}, dpu {4,9}      |
| testCalculateColumnTotalValidInput()           | dcu {9}, dpu {4, 5}      |
| testCalculateColumnTotalEmptyDataTable()       |    dcu {9}, dpu {(1,2), (4,9)}   |
| testCalculateColumnTotalSingleValue()          |  dcu {9}, dpu {4, 5}     |
| testCalculateColumnTotaltestInvalidColumnIndex()|  dcu { }, dpu {(1,End)}     |
| testCalculateColumnTotalNullDataTable()        |   dcu { }, dpu {(1,End)}    |

### calculate the DU-Pair coverage
- CU = 8, PU = 8

## Range.combine method:
### The data flow graph
<img width="454" alt="Screen Shot 2024-03-04 at 11 09 48 PM" src="https://github.com/seng438-winter-2024/seng438-a3-Sabayara82/assets/113862210/e7f25a43-75a2-4ea5-9f0e-48edfef39bdb">

### The def-use sets per statement
Def-path set:
- du(1, range1) = {[1], [1,3,4], [1,3,5], [1,3,5,6]}
- du(1, range2) = {[1,2], [1,3,5], [1,3,5,6]}
- du(5, l) = {[5,6,7]}
- du(6, u) = {[6,7]}
Def-pair set:
- du(1, 4, range1) = {[1,3,4]}
- du(1, 6, range1) = {[1,3,5,6]}
- du(1, 3, range2) = {[1,3]}
- du(1, 2, range2) = {[1,2]}
- du(1, 6, range2) = {[1,3,5,6]}
- du(5, 7, l) = {[5, 6, 7]}
- du(6, 7, u) = {[6,7]}
  
### All DU-pairs per variable
| Variable | Defined at Node | dcu         | dpu                    |
|----------|-----------------|-------------|------------------------|
| range1     | 1               |{4,5,6}       |  {(1,2), (1,3)}   |
| range1   | 1               |{2,5,6}          | {(3,4), (3,5)}    |
| I    | 5               | {5,7}       |     {}                   |
| u | 6              | {5,6}            | {}           |

### which pairs are covered

| Test                                          | Pairs Covered            |
|-----------------------------------------------|--------------------------|
| testCombineBothNullRanges()                   | dcu = {1}, dpu = {1,2}   |
| testCombineFirstRangeIsNull()                 | dcu = {1}, dpu = {1,2}   |
| testCombineSecondRangeIsNull()                | dcu = {4}, dpu = {1,3}   |
| testCombineBothRangesAreNotNullOverlapping()  | dcu = {7}, dpu = {1,3}   |
| testCombineBothRangesAreNotNullNonOverlapping()| dcu = {7}, dpu = {1,3}   |
| testCombineSameRanges()                       | dcu = {7}, dpu = {1,3}   |


### calculate the DU-Pair coverage
- CU = 10, PU = 4

# 3 A detailed description of the testing strategy for the new unit test

The test strategy consists of initially identifying the coverage for the test files as is. This will serve as a base point for increasing or decreasing coverage as we develop more tests and gain an understanding of the general direction we must move in. Based on the branch, line, and method coverage metrics, tests will be written to discover noticeable areas of scarcity in testing. With each test that is developed and executed, the coverage will be accordingly monitored. Once a direction of development is determined and an idea of how to increase each separate coverage metric is understood, we will split the number of test cases amongst our group members and implement pair-programming. We will be using EclEmma and the method, branch, and line coverage metrics.


# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage


Equal Objects: 
Test Case Name : testEqualsForEqualObjects

Objective: Test for equality between two Range objects with the same lower and upper bounds.
Coverage Contribution: This test case exercises the positive branch of the if (this.lower == range.lower) and if (this.upper == range.upper) conditions, increasing statement coverage in the equal objects scenario.

**Non-Equal Objects:**
Test Case Name : testEqualsForNonEqualObjects

Objective: Verify the behavior when two Range objects have different bounds.
Coverage Contribution: This test case covers the negative branch of the conditions in the equals method, ensuring that the method handles non-equal objects correctly, contributing to statement coverage.

**Equals Against Null:**
Test Case Name: testEqualsAgainstNull

Objective: Check the behavior when comparing a Range object against null.
Coverage Contribution: This test case exercises the condition if (!(obj instanceof Range)) and contributes to statement coverage when the object being compared is null.

**Equals Against Different Type:**
Test Case Name: testEqualsAgainstDifferentType

Objective: Confirm that the equals method handles comparison with objects of a different type.
Coverage Contribution: This test case covers the branch where the object being compared is not an instance of Range, increasing statement coverage for this condition.

**Equals Beyond Delta:**
Test Case Name: testEqualsBeyondDelta

Objective: Test the handling of objects that are equal within a specified delta.
Coverage Contribution: This test case explores the scenario where the upper bounds are within the defined delta, increasing statement coverage within the condition if (!(this.upper == range.upper)).




# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

### DataUtilities: 
<img width="793" alt="Screen Shot 2024-03-04 at 11 17 43 PM" src="https://github.com/seng438-winter-2024/seng438-a3-Sabayara82/assets/113862210/924d3c27-158d-40d1-8fc3-e84dfdfd19c0">

### Range
<img width="426" alt="Screen Shot 2024-03-04 at 11 19 51 PM" src="https://github.com/seng438-winter-2024/seng438-a3-Sabayara82/assets/113862210/6037d429-73ff-49b6-9a00-f2daabbfaa9a">


# 6 Pros and Cons of coverage tools used and Metrics you report

### Metrics Used:
  - Method Coverage
  - Branch Coverage
  - Line Coverage
  - These methods worked well for the configuration in that they provide a comprehensive assessment of how effectively JFree Data Utilities and Range are being tested. Method coverage ensures that all methods within the utility classes are executed, verifying their individual functionality. Branch coverage examines different decision points within the code, ensuring that both true and false branches are traversed during testing, thus validating the logic flow. Line coverage ensures that each line of code is executed at least once, guaranteeing that all statements are tested for correct behavior. Together, these coverage metrics contribute to a thorough evaluation of the testing effectiveness, helping to identify areas of potential improvement and ensuring the reliability and robustness of JFree Data Utilities and Range.

### EclEmma Analysis Tool Pros and Cons:
#### Pros:
- Integration with Eclipse: EclEmma seamlessly integrates into the Eclipse IDE, making it convenient for developers who already use Eclipse for Java development.
- User-Friendly Interface: EclEmma provides a user-friendly interface within Eclipse, making it easy to run coverage analysis, view results, and navigate to specific code areas that need attention.
- Various Coverage Metrics: EclEmma offers a range of coverage metrics including statement coverage, branch coverage, and condition coverage, providing developers with comprehensive insights into their test suite effectiveness.
- Detailed Reporting: EclEmma generates detailed reports that highlight which parts of the code are covered by tests and which are not. This allows developers to identify areas that need additional testing.
- Support for JUnit and Java Applications: EclEmma supports both JUnit tests and Java applications, making it versatile for different types of testing scenarios.
- Open Source: EclEmma is an open-source tool, which means it's freely available for developers to use and contribute to. This fosters a community around the tool, leading to continuous improvements and updates.

#### Cons:
- Did not have condition or statement coverage (didn’t quite fit our context).
- Dependency on Eclipse: Since EclEmma is tightly integrated with Eclipse, it may not be suitable for developers who prefer using other IDEs or text editors for Java development.
- Performance Overhead: Running coverage analysis with EclEmma can sometimes introduce performance overhead, especially for large projects with extensive test suites. This may impact the speed of test execution and overall development workflow.
- Complex Configuration for Advanced Usage: While basic usage of EclEmma is straightforward, configuring advanced settings or integrating it with complex project setups may require additional effort and expertise.
- Limited Visualization Options: While EclEmma provides detailed coverage reports, the visualization options may be somewhat limited compared to standalone coverage tools.

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

A comparison of requirements-based test generation and coverage-based test generation reveals distinct advantages and disadvantages for each approach. Requirements-based test generation focuses on deriving test cases directly from the specified requirements of the software system. One of its primary advantages is its ability to ensure that the resulting test suite aligns closely with the intended functionality of the system. By directly mapping test cases to requirements, this approach facilitates comprehensive coverage of specified features and functionalities. However, a potential drawback of requirements-based test generation is its dependency on the accuracy and completeness of the requirements documentation. In cases where requirements are ambiguous or incomplete, it may lead to inadequate test coverage or the generation of irrelevant test cases. On the other hand, coverage-based test generation prioritizes achieving a certain level of coverage criteria, such as statement coverage, branch coverage, or path coverage. Its advantage lies in its ability to systematically identify areas of the code that have not been adequately exercised by existing test cases, thus guiding the generation of additional tests to improve coverage. Nonetheless, coverage-based test generation may overlook certain functional aspects of the software if the chosen coverage criteria do not align perfectly with the requirements. Additionally, achieving high coverage metrics does not guarantee the detection of all potential defects or vulnerabilities within the system. Therefore, while requirements-based test generation emphasizes alignment with system specifications, coverage-based test generation emphasizes the thoroughness of code coverage, each offering unique benefits and limitations in the context of software testing.

# 8 A discussion on how the team work/effort was divided and managed

Members of the group each read through sections 1 and 2 to learn about coverage analysis tools and configuring the system to be tested. 3.1 was done in collaboration to ensure that the initial coverage metrics were consistent across our systems. While one member manually calculated the data flow coverage, the other members used the reported metrics to design and find the general direction of development to begin executing the test plan.

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

A difficulty encountered by our group in this lab was when we hit a block in calculating the pairs covered, each member went and tried to use the data flow diagram themselves to determine a solution. This resulted in slightly different paths to follow data variables, prompting necessary discussion amongst our group to learn where and why we deviated.

# 10 Comments/feedback on the lab itself

It was great practice and good length. Given the midterm date being in two days, however, the assignment due date was very inconvenient and did not allow for full concentration on studying for the midterm. 
