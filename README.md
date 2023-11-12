# DistributedEmployeeDMS

This repository stores a project which has a distributed architecture and uses SOAP (Simple Object Access Protocol) web services in Java for a company to centrally manage employees' data. Each employee is characterized by *id*, *name*, *surname*, *email*, *phone number* (possibly more than one), and *department*. We imagine that the company wants to enable all services within its Intranet to access the central employee data uniformly.

The system supports two applications:

* `BusinessInterfaceApplet`
  * This applet allows users to search for employees based on various criteria.
* `EmployeeService`
  * This service allows employees to manage and update the content of the employee database.
