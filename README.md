# Selenium Testing for Magento Website

This repository hosts Selenium WebDriver automated tests for the Magento website. The tests cover user signup, login, logout, and adding items to the cart with random selections.

## Test Cases


### Test Case 1:
. **User Signup**:
   - Navigate to [Magento Signup Page](https://magento.softwaretestingboard.com/customer/account/create/).
   - Randomly fill signup details and verify successful registration.

### Test Case 2:
. **User Logout**:
   - Perform user logout after login.
   - Verify successful logout.

### Test Case 3:
. **User Login**:
   - Navigate to [Magento Login Page](https://magento.softwaretestingboard.com/customer/account/login/).
   - Randomly input login credentials and verify successful login.

### Test Case 4:
. **Adding Item to Cart**:
   - Navigate to [Magento Item Page](https://magento.softwaretestingboard.com/men/tops-men/jackets-men.html).
   - Randomly select item attributes (color, size) and add the item to the cart.
   - Print the name and price of the added item.
