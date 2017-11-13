const accountClient = require('./account-client');
const ordersClient = require('./orders-client');

const USER_ID = 1;

accountClient.fetchAccountData(USER_ID).then(response => {
  console.log(response);
  ordersClient.fetchOrdersData(USER_ID).then(response2 => {
    console.log(response2);
  }, error2 => {
    console.log(error2);
  });
}, error => {
  console.error(error);
});
