To basically make it production-ready, two more things need to be done but not mentioned in the assignment.

1. Authentication for API
   - The authentication can be done using JWT and Spring Security
   - The JWT should be generated using a secret key signature, which should be stored in OS environment variable or AWS Secrets Manager
   - The JWT will be added to the API response and validated by the secret key signature via Spring Security workflow

2. DataSource and Database Configuration

Squash Merge PR is good practices for more readable commits.