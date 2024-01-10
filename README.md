# Project Name
Ship Rocket

![CJ2az8j0lu8CEAE-1](https://github.com/DharmendraShekhawat/Ship-Rocket/assets/142703677/f71134cd-079f-4d66-a896-d364b2feea7e)

# Frameworks and Language Used
**Spring Boot** 2.1.0
**Java** 20.0
**Maven** 3.8.1

# Data Flow
The following functions are used in the data flow of this project:


## Models

**Company:**

Attributes: CompanyId, companyName, companyWallet, companyContactNumber, companyEmail, companyPassword, companyAddress, companyCity,companyState. 
Represents a client in the Ship Rocket company.
Has a one-to-many relationship with the customer.

**Admin:**

Attributes: adminId, emailId, password, aboutUs, shipmentCharges, shipmentCollection.
Represents a owner in the Ship Rocket company.

**Customer:**

Attributes: customerId, billing_first_name, billing_last_name, billing_email, billing_phone, billing_address, billing_address_2, billing_pinCode,billing_city, billing_state,billing_country.   
Represents a cumtomer in the Ship Rocket company.
Has a one-to-many relationship with the company.

## Controller

CompanyController:

/company (POST): Handles the signup process for company. Expects a SignUpInput object in the request body and returns a SignUpOutput object.
/company (POST): Handles the signin process for company. Expects a SignInInput object in the request body and returns a SignInOutput object.
/aboutUs (GET):  Retrieves information about the Ship Rocket courier company.
/serviceCities (GET): Retrieves a list of all service cities.

AdminController:

/admin (POST): Adds a new admin to the system. Expects a admin object in the request body. Returns a string indicating the result of the addition.
/aboutUs (POST): Admin can add aboutus or details of service of the company

## Services

**CompanyOrderService**:

Manages the CompanyOrder related operations.
Autowires the ICompanyOrderRepo repository.

**AdminService**:

Manages the admin-related operations.
Autowires the IAdminRepo repository.
Provides methods to add a admin and retrieve indormation about orders.

**CompanyService**:

Manages the company-related operations.
Autowires the ICompanyRepository, AuthenticationService, and AdminService.
Provides methods for company signup and signin.
Performs password encryption using MD5.
Retrieves and validates company information.
Invokes the CompanyOrderService to fetch all company's order.

**AuthenticationService**:

Manages the authentication-related operations.
Performs authentication checks based on the user's email and password.




_**Repository:**_ The repository layer is responsible for interacting with the database. It uses Spring Data JPA to perform CRUD (create, read, update, delete) operations on entities.


# Database Structure Used
I have used MySql as Database.


For questions or feedback, please contact : Dharmendra Singh Shekhawat  
- Maild Id : dharmendrashekhawat1403@gmail.com

<h1 align="center">Thank You...<h1>
<h3 align = "center"> ***********************************************************<h3>
