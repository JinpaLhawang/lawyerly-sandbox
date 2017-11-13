const request = require('superagent');
const API_HOST = process.env.API_HOST || 'http://localhost';
const API_PORT = process.env.API_PORT || 9103;
const API_ENDPOINT = `${API_HOST}:${API_PORT}`;

// Fetch account data
const fetchAccountData = (id) => {
  return request
    .get(`${API_ENDPOINT}/account/` + id)
    .then((res) => {
      return res.body;
    }, (err) => {
      return err;
    });
};

module.exports = {
  fetchAccountData
};
