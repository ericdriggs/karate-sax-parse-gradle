function karateConfig() {
    var env = karate.env; // get java system property 'karate.env'
    karate.log('karate.env system property was:', env);
    if (!env) {
        env = 'dev'; // a custom 'intelligent' default
    }

    karate.log('env:', env);
    var config = { // base config JSON
        env: env
    };

    karate.configure('logPrettyResponse', true);
    return config;
}