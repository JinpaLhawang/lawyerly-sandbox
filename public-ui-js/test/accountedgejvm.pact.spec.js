const chai = require('chai');
const path = require('path');
const chaiAsPromised = require('chai-as-promised');
const pact = require('pact');
const expect = chai.expect;
const API_PORT = process.env.API_PORT || 9103;
const {
  fetchAccountData
} = require('../account-client');
chai.use(chaiAsPromised);

// Configure and import consumer API
// Note that we update the API endpoint to point at the Mock Service
const LOG_LEVEL = process.env.LOG_LEVEL || 'WARN';

const provider = pact({
  consumer: 'public-ui-js',
  provider: 'account-edge-jvm',
  port: API_PORT,
  log: path.resolve(process.cwd(), 'logs', 'pact.log'),
  dir: path.resolve(process.cwd(), 'pacts'),
  logLevel: LOG_LEVEL,
  spec: 2
});
const ID = 2;
const USER = { id: ID, name: 'User' };
const PREFERENCE = { key: 'color', value: 'red' };
const PREFERENCES = [ PREFERENCE ];

describe('Pact with account-edge-jvm', () => {
  before(() => {
    return provider.setup();
  });

  describe('when a call to account-edge-jvm is made', () => {
    before(() => {
      // The Contract
      return provider.addInteraction({
        uponReceiving: 'a request for account data',
        withRequest: {
          method: 'GET',
          path: '/account/' + ID
        },
        willRespondWith: {
          status: 200,
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          },
          body: {
            user: USER,
            preferences: PREFERENCES
          }
        }
      });
    });

    // The Tests that run when AccountEdgeJVM runs ./gradlew pactVerify
    it('can process the data', done => {
      const response = fetchAccountData(ID);

      expect(response).to.eventually.have.property('user');
      expect(response).to.eventually.have.property('preferences').notify(done);
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
