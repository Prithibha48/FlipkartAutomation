# 🛒 Flipkart Automation Testing Framework

This project is an automated test suite for Flipkart using **Selenium WebDriver**, **TestNG**, **ExtentReports**, **Apache POI (Excel for Data-driven Testing)**, and integrated with **Jenkins** and **GitHub** for CI/CD.

## 📌 Technologies Used

- **Java**
- **Selenium WebDriver**
- **TestNG**
- **ExtentReports**
- **Apache POI** (for Excel integration)
- **Jenkins** (for automation)
- **GitHub** (for version control)

---

## ✅ Test Workflow

The test performs the following actions:

1. **Verify Flipkart Logo and Page Title**
2. **Input Search Data from Excel**
   - Reads search terms from an external Excel file using a `@DataProvider`.
3. **Searches the Item**
   - Enters the provided data in the Flipkart search bar.
4. **Prints the List of All Displayed Products**
5. **Applies Price Filter**
   - Filters the product list based on price.
6. **Prints the List of Filtered Products**
7. **Selects the First Product**
8. **Navigates to Product Page**
   - Extracts and prints the product **title** and **price**.
9. **Checks “Add to Cart” Button**
   - If enabled: 
     - Prints “In Stock”.
     - Adds the item to the cart.
10. **Navigates to Cart**
    - Verifies that the selected product is added to the cart.

> ✅ Screenshots are taken at **each step** and are embedded into the **ExtentReports**.

----

---

## 📊 Reports

- **ExtentReports** are generated at:  
  `./reports/ExtentReport.html`

- Includes:
  - Step-by-step status
  - Embedded screenshots
  - Log info for debugging

---

## 📘 Data Driven Testing

- Search inputs are taken from:/resources/testdata.xlsx
- Apache POI is used to fetch data in `@DataProvider`.

---

## 📸 Screenshots

Each major test step captures a screenshot and attaches it to the Extent Report for visual validation.

---
## 📌 Author

Prithibha   
Prithibhar48((https://github.com/Prithibha48))




