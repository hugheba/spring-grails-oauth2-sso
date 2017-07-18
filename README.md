# sso-demo

This project is a demo of SSO across a Spring and Grails application authenticating against Wordpress.

## Start Wordpress

The Wordpress installation is a simple self-contained Docker container.

### Prerequisites

- Docker (https://www.docker.com/get-docker)
- Docker Compose (https://github.com/docker/compose)
- Wordpress Docker image (https://github.com/sspreitzer/docker-wp-full) *** If building manually ***

#### Wordpress Docker

The project contains preconfigured Docker resources for a Wordpress LAMP container.

Use *docker-compose* to start the container from the `./sso-wordpress`

In a terminal, start the Wordpress Docker container with the following command:

    docker-compose up -d
    
You should be able to reach the Wordpress installation from the browser at http://localhost:8091.

You can login with the username/password `admin` and `admin`.
    
#### Configure Wordpress oAuth2 Server Manually

Setup Wordpress with a new user and download and install the following plugins:
 
- **WP OAuth Sever by Justin Greer** (https://wordpress.org/plugins/oauth2-provider/).
- **WP API** (https://wordpress.org/plugins/rest-api/)

In the Wordpress Oauth Server settings, create a new clients<sup>1</sup>, one for Spring and one for Grails, and copy their clientId and secret to the respect conf files for the projects:

For the Spring client use:

    Client Name:        Spring
    Redirect URI:       http://localhost:8092/login
    Client Description: Spring Project

Update the Spring project config file `sso-demo/src/main/resources/application.yml`, setting `security.oauth.client[clientId,clientSecret]`

And for the Grails client use:

    Client Name:        Grails
    Redirect URI:       http://localhost:8093/login
    Client Description: Grails Project

Update the Grails project config file `sso-grails-demo/grails-app/application.yml`, setting `org.grails.springsecurity.oauth.providers[clientId,clientSecret]`

You'll also need to update the Wordpress settings for permalinks  at `Admin -> Settings -> Permalinks -> Common Settings` and choose `Post name` and Save.

_<sup>1</sup> Unregisted version of the WP OAuth Server allows only one client at a time, so you'll have to toggle between the two to test. Be sure to update the new clientID and clientSecret._

## Start SpringBoot

Start the SpringBoot application in a new terminal with the following command:

    ./gradlew :sso-demo:bootRun
    
You should now be able to browse the SpringBoot application at http://localhost:8092.
    
## Start Grails

Start the Grails application in a new terminal with the following command:

    ./gradlew :sso-grails-demo:bootRun
    
You should now be able to browse the Grails application at http://localhost:8093.

# Usage

At the `/` (root) of each of the applications (SpringBoot and Grails) there should be a page with a login button.

Clicking the button will take you to a secured page in the application.

If you have not authenticated you will be redirected to the Wordpress site to login, otherwise you will be automatically 
logged in and your username will appear in the secured welcome page.  