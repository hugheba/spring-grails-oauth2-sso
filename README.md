# sso-demo

This project is a demo of SSO across a Spring and Grails application authenticating against Wordpress.

## Start Wordpress

The Wordpress installation is a simple self-contained Docker container.

### Prerequisites

- Docker (https://www.docker.com/get-docker)
- Docker Compose (https://github.com/docker/compose)
- Wordpress Docker image (https://github.com/sspreitzer/docker-wp-full)

Install Docker and Docker Container and clone the Wordpress Docker repo.

Add the following `docker-compose.yml` to the root of the Wordpress Docker project.

    version: "2"
    services:
      wp:
        build: ./
        ports:
          - "8081:80"
          
In a terminal, start the Wordpress Docker container with the command:

    docker-compose up -d
    

### Configure Wordpress oAuth2 Server

Setup Wordpress with a new user and download and install the following plugins:
 
- **WP OAuth Sever by Justin Greer** (https://wordpress.org/plugins/oauth2-provider/).
- **WP API** ()

In the Wordpress Oauth Server settings, create a new clients, one for Spring and one for Grails, and copy their clientId and secret to the respect conf files for the projects:

For the Spring client use:

    Client Name:        Spring
    Redirect URI:       http://localhost:8082/login
    Client Description: Spring Project

Update the Spring project config file `sso-demo/src/main/resources/application.yml`, setting `security.oauth.client[clientId,clientSecret]`

And for the Grails client use:

    Client Name:        Grails
    Redirect URI:       http://localhost:8083/login
    Client Description: Grails Project

Update the Grails project config file `sso-grails-demo/grails-app/application.yml`, setting `org.grails.springsecurity.oauth.providers[clientId,clientSecret]`

You'll also need to update the Wordpress settings for permalinks  at `Admin -> Settings -> Permalinks -> Common Settings` and choose *Post name* and Save.