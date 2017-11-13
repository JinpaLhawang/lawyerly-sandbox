const pact = require('@pact-foundation/pact-node');
const path = require('path');
const opts = {
  pactUrls: [
    path.resolve(__dirname, '../pacts/public-ui-js-account-edge-jvm.json'),
    path.resolve(__dirname, '../pacts/public-ui-js-orders-edge-jvm.json')
  ],
  pactBroker: 'http://localhost',
  tags: ['prod', 'test'],
  consumerVersion: '1.0.0'
};

pact.publishPacts(opts)
  .then(() => {
    console.log('Pact contract publishing complete!');
    console.log('');
    console.log('Head over to http://localhost to see your published contracts.');
  })
  .catch(e => {
    console.log('Pact contract publishing failed: ', e);
  });
