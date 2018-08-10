# Google Summer of Code 2018 - Mifos Initiative


# Payment Gateway Service a.k.a Middleware
Payment Gateway Service is act as a middleware which interacts with the different Mobile Money Providers(MMP's) and Fineract to manage transactions and selecting vendors for the ease of the User and the client.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Java >= 1.8
Apache Maven
MySQL
IDE: preferable: IntelliJ IDEA
Lombok Plugin
```

### Installing

A step by step series of examples that tell you how to get a development env running


### Installation on UBUNTU

Java:
```
sudo apt-get update
sudo apt-get upgrade
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
Java Installation: sudo apt-get install oracle-java8-installer
Check version: java -version
```

Maven:
```
Maven Installation: sudo apt-get install maven
Check version: mvn -version
```

MySQL:
```
Installation: sudo apt-get install mysql-server
Check version: mysql --version
```

IntelliJ Idea:
```
Installation: https://www.jetbrains.com/help/idea/install-and-set-up-product.html
```

Lombok Plugin:
```
Install Lombok plugin: https://plugins.jetbrains.com/plugin/6317-lombok-plugin
```

## Database setup:
```
sudo  mysql -u root -p
create database `payment-gateway`;
exit
```

## Project
1. Project Setup on Git: </br>
   - Fork:
   > https://github.com/openMF/payment-gateway </br>
   - Clone:
   > https://github.com/openMF/payment-gateway.git </br>
   - Main Development Branch: payment-gateway-core-functionality
   > https://github.com/openMF/payment-gateway/tree/payment-gateway-core-functionality </br>
2. Import into IDE
3. change branch: 
   > git checkout payment-gateway-core-functionality
4. mvn clean install
5. Run PaymentGateway.java file as Java Application - [PaymentGateway.java](https://github.com/openMF/payment-gateway/blob/payment-gateway-core-functionality/src/main/java/org/apache/payment/gateway/PaymentGateway.java)

### Payment Gateway API's Documentation:
```
http://localhost:8080/swagger-ui.html
or
<DomainName>/swagger-ui.html
```

### Work Done:
1. Middleware generic architecture which can be used to integrate different Mobile money providers like Beyonic, MPesa, Razorpay
2. Payment flows from API's </br>
   Main Slug:
   > /payment-gateway/api/v1/
3. Get All vendors API's
   > /vendors
4. Get All active vendors API's
   > /active-vendors
5. Get vendor by particular ID
   > /vendor/{id}
6. Get all Transactions with headers to filter search result: nextTransactionId, size, isTotalCountRequired, vendorIdList, clientPhoneNumber, clientAccountNumber
   > /transactions/search
     ```
     curl -X GET \
     http://localhost:8080/payment-gateway/api/v1/transactions/search \
     -H 'clientPhoneNumber: +80099999111'
     ```
7. Get Transaction by Transaction ID
   > /transaction/{transactionId}
8. Beyonic Integration for Collections, Collection Request and Payment API's
9. Use Cases:
   1. Mobile/Online Banking
      1. Loan Repayment from Mobile Money Account
      2. Savings Deposit from Mobile Money Account
   2. Mobile Wallet
      1. Loan Repayment
      2. Savings Deposit
      3. View list of mobile money transactions
    
### Work Left:
1. Peer to Peer transfer model
2. Interacting with Fineract API's


## Deployment on Local Machine:
See installation steps on Ubuntu

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management


## Developer

* **Sanyam Goel** - [Sanyam Goel](https://github.com/Sanyam96)

## Mentors

* **Rahul Goel** - [Rahul Goel](https://github.com/rahulg963)
* **Steve Conrad** - [Steve Conrad](https://github.com/conradsp)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the Mozilla Public License 2.0 License - see the [LICENSE.md](https://github.com/openMF/payment-gateway/blob/master/LICENSE) file for details
