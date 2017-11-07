const gulp = require('gulp');
const pact = require('@pact-foundation/pact-node');
const path = require('path');

var opts = {
    pactUrls: [
      // Array of local Pact files or directories containing them. Required.
      path.resolve(process.cwd(), 'pacts')
    ],
    pactBroker: 'http://localhost',
    consumerVersion: '2.0.0',
    pactBrokerUsername: 'shanelee007',
    pactBrokerPassword: 'password123'
};

var Server = require('karma').Server;

gulp.task('createPacts', function (done) {

    new Server({
        configFile:  __dirname + '/karma.conf.js',
        singleRun: true
    }, shutdownPactServer).start();

    function shutdownPactServer() {
        pact.removeAllServers();
        done();
    };
});

gulp.task('publishPacts', ['createPacts'], function (done) {
    pact.publishPacts(opts).then(function () {
        console.log('Pacts published to broker');
    }).finally(function () {
        done();
    });
});

gulp.task('pact', ['createPacts', 'publishPacts']);
