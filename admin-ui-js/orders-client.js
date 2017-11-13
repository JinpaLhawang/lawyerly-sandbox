const request = require('superagent');
const API_HOST = process.env.API_HOST || 'http://localhost';
const API_PORT = process.env.API_PORT || 9105;
const API_ENDPOINT = `${API_HOST}:${API_PORT}`;

// Fetch orders data
const fetchOrdersData = (id) => {
  return request
    .get(`${API_ENDPOINT}/user/` + id + '/orders')
    .then((res) => {
      return res.body;
    }, (err) => {
      return err;
    });
};

module.exports = {
  fetchOrdersData
};
