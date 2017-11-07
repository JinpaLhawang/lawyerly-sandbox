describe("Client", function() {

  var projectsProvider;

  beforeAll(function(done) {

    projectsProvider = Pact({
      consumer: 'ui-js',
      provider: 'edge1'
    })
    // required for slower Travis CI environment
    setTimeout(function() {
      done()
    }, 2000)

    // Required if run with `singleRun: false`
    projectsProvider.removeInteractions()
  });

  afterAll(function(done) {
    projectsProvider.finalize()
      .then(function() {
        done()
      }, function(err) {
        done.fail(err)
      })
  });

  describe("Should get account", function() {
    beforeAll(function(done) {

      projectsProvider.addInteraction({
        uponReceiving: 'an account request',
        withRequest: {
          method: 'GET',
          path: '/account/1234567890'
        },
        willRespondWith: {
          status: 200,
          headers: {
            "Content-Type": "application/json"
          },
          body: {
            user: {
              id: "1234567890",
              name: "Name",
              multipleAppsUse: "MultipleAppsUse",
              oneAppUses: "OneAppUses"
            },
            thing: {
              id: "1234567890",
              name: "Name",
              multipleAppsUse: "MultipleAppsUse"
            }
          }
        }
      }).then(function() {
        done()
      }, function(err) {
        done.fail(err)
      });
    })

    it("with account id 1234567890", function(done) {
      //Run the tests
      sendAccountRequest('1234567890')
        .then(function(data) {
          console.log("data is " + JSON.stringify(data.responseText));
          expect(JSON.parse(data.responseText)).toEqual({
            user: {
              id: "1234567890",
              name: "Name",
              multipleAppsUse: "MultipleAppsUse",
              oneAppUses: "OneAppUses"
            },
            thing: {
              id: "1234567890",
              name: "Name",
              multipleAppsUse: "MultipleAppsUse"
            }
          });
          done()
        })
        .catch(function(err) {
          done.fail(err)
        })
    });

    // verify with Pact, and reset expectations
    it('successfully verifies', function(done) {
      projectsProvider.verify()
        .then(function(a) {
          done()
        }, function(e) {
          done.fail(e)
        })
    })


  })
});

function sendAccountRequest(id) {

  //Makes a synchronous request
  var xhr = new XMLHttpRequest();
  xhr.open('GET', 'http://localhost:1234/account/' + id, false);
  xhr.send();

  return Promise.resolve(xhr);
}
