# Project Estimation  template

Authors: Magnani Simone, Marchi Riccardo, Palumbo Daniele, Postolov Enrico

Date: 28/05/2019

Version: 1.0

# Contents

- [Data from your LaTazza project](#data-from-your-latazza-project)
- [Estimate by product decomposition](#estimate-by-product-decomposition)
- [Estimate by activity decomposition ](#estimate-by-activity-decomposition)



# Data from your LaTazza project

###
|||
| ----------- | ------------------------------- | 
|         Total person hours  worked by your  team, considering period March 5 to May 26, considering ALL activities (req, des, code, test,..)    |   |             
|Total Java LoC delivered on May 26 (only code, without Exceptions, no Junit code) | 619 |
| Total number of Java classes delivered on May 26 (only code, no Junit code, no Exception classes)| 5 |
| Productivity P =| 5 |
|Average size of Java class A = | 124 |

# Estimate by product decomposition



### 

|             | Estimate                        |             
| ----------- | ------------------------------- |  
| Estimated n classes NC (no Exception classes) |            20                  |             
| Estimated LOC per class  (Here use Average A computed above )      |           124                 |                
| Estimated LOC (= NC * A) | 2480 |
| Estimated effort  (person days) (Here use productivity P)  |             496                         |      
| Estimated calendar time (calendar weeks) (Assume team of 4 people, 8 hours per day, 5 days per week ) |        4            |               


# Estimate by activity decomposition



### 

|         Activity name    | Estimated effort    |             
| ----------- | ------------------------------- | 
|Requirements| 30 |
|Design | 16 |
|Coding | 240 |
|Testing | 160 |


###

![Estimated Gantt Table](Gantt_table.png)

*Figure 1: Estimated Gantt Table*


![Estimated Gantt Chart](Gantt_chart.png)

*Figure 2: Estimated Gantt Chart*
