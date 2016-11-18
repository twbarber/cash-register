# cash-register
Cash Register implementation using Spring Boot and Kotlin

## How To
### Postman
See `cash-register-public.src.main.resources.PostmanCollection.json` for importable Postman commands.
Note: localhost will need to be replaced with your cash-register-servers host.

# Notes
## TODO
Things I'd like to have completed with more time.
- Transaction class was meant to be able to log transactions. this included the request and the post
    request balance.
- Swagger Docs for Cash Register Controller
- REST Client Implementation
- Proper error handling for Web Service, should throw 400s and 500s
- Script to create Intellij Run configurations

## Project Thoughts
Thoughts on the problem, and some things I considered but decided were out of scope.
- Change making algorithm will offer change using the least amount of bills. Always.
    We hit the 20's, 10's, 5's, 2's, and 1's in that order.
- A weighting algorithm could be implemented if we have a lot for of one bill than another.
- No bills were given when asking for change, so the money that's taken is never replenished.
- No exchange rate was taken into account, which makes sense for making change.

### Builds
[![Build Status](https://travis-ci.org/twbarber/cash-register.svg?branch=master)](https://travis-ci.org/twbarber/cash-register)