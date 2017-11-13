const chai = require('chai');
const path = require('path');
const chaiAsPromised = require('chai-as-promised');
const pact = require('pact');
const expect = chai.expect;
const API_PORT = process.env.API_PORT || 9105;
const {
  fetchOrdersData
} = require('../orders-client');
chai.use(chaiAsPromised);

// Configure and import consumer API
// Note that we update the API endpoint to point at the Mock Service
const LOG_LEVEL = process.env.LOG_LEVEL || 'WARN';

const provider = pact({
  consumer: 'public-ui-js',
  provider: 'orders-edge-jvm',
  port: API_PORT,
  log: path.resolve(process.cwd(), 'logs', 'pact.log'),
  dir: path.resolve(process.cwd(), 'pacts'),
  logLevel: LOG_LEVEL,
  spec: 2
});
const ID = 2;
const ORDER = { id: 2, amount: 12 };
const ORDERS = [ ORDER ];

describe('Pact with orders-edge-jvm', () => {
  before(() => {
    return provider.setup();
  });

  describe('when a call to orders-edge-jvm is made', () => {
    before(() => {
      return provider.addInteraction({
        uponReceiving: 'a request for orders data',
        withRequest: {
          method: 'GET',
          path: '/user/' + ID + '/orders'
        },
        willRespondWith: {
          status: 200,
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          },
          body: ORDERS
        }
      });
    });

    it('can process the data', done => {
      const response = fetchOrdersData(ID);

      expect(response).to.eventually.be.a('array').notify(done);
    });

    it('should validate the interactions and create a contract', () => {
      return provider.verify();
    });
  });

  // Write pact files to file
  after(() => {
    return provider.finalize();
  });
});
