describe("Client", function() {

  var projectsProvider;

  beforeAll(function(done) {

    projectsProvider = Pact({
      consumer: 'consumer3',
      provider: 'provider1'
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

  describe("Should get user", function() {
    beforeAll(function(done) {

      projectsProvider.addInteraction({
        uponReceiving: 'a user request',
        withRequest: {
          method: 'GET',
          path: '/user/1234567890'
        },
        willRespondWith: {
          status: 200,
          headers: {
            "Content-Type": "application/json"
          },
          body: {
            id: "1234567890",
            name: "Name",
            multipleAppsUse: "MultipleAppsUse"
          }
        }
      }).then(function() {
        done()
      }, function(err) {
        done.fail(err)
      });
    })

    it("with user id 1234567890", function(done) {
      //Run the tests
      sendUserRequest('1234567890')
        .then(function(data) {
          console.log("data is " + JSON.stringify(data.responseText));
          expect(JSON.parse(data.responseText)).toEqual({
            id: "1234567890",
            name: "Name",
            multipleAppsUse: "MultipleAppsUse"
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

function sendUserRequest(id) {

  //Makes a synchronous request
  var xhr = new XMLHttpRequest();
  xhr.open('GET', 'http://localhost:1234/user/' + id, false);
  xhr.send();

  return Promise.resolve(xhr);
}
