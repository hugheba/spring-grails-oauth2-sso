security {
    oauth2 {
        providers {
            wordpress {
                successUri = "/oauth2/google/success"
                failureUri = "/oauth2/google/failure"
                callback = "/oauth2/google/callback"
                api_key = "changeme_apikey"
                api_secret = "changeme_apisecret"
            }
        }
    }
}