FROM ubuntu:16.04

ENV LANG C.UTF-8
ENV DEBIAN_FRONTEND=noninteractive


RUN apt-get update -y && \
    apt-get install -y software-properties-common python-software-properties
RUN add-apt-repository -y -u ppa:ondrej/php && \
    apt-get update -y && \
    apt-get install -y curl \
    unzip \
    mysql-server \
    apache2 \
    php7.1 \
    libapache2-mod-php7.1 \
    php7.1-mysql \
    php7.1-gd

ENV WP_VERSION 4.8

RUN curl -L https://www.wordpress.org/wordpress-${WP_VERSION}.tar.gz | tar xzf - -C /srv/
RUN curl -o /tmp/wp-oauth.zip -L https://downloads.wordpress.org/plugin/oauth2-provider.3.2.002.zip && \
    unzip /tmp/wp-oauth.zip -d /srv/wordpress/wp-content/plugins/
RUN curl -o /tmp/wp-oauth.zip -L https://downloads.wordpress.org/plugin/rest-api.2.0-beta15.zip && \
    unzip /tmp/wp-oauth.zip -d /srv/wordpress/wp-content/plugins/

ADD entrypoint.sh /usr/local/sbin/
ADD wordpress.conf /etc/apache2/sites-available/
ADD wp-config.php /srv/wordpress/
ADD dump.sql /tmp/dump.sql

RUN a2dissite 000-default
RUN a2ensite wordpress
RUN a2enmod rewrite
RUN mkdir -p /var/run/mysqld && chown mysql:mysql /var/run/mysqld && \
    /bin/bash -c "/etc/init.d/mysql start" && \
    sleep 5 && \
    mysql -e "CREATE DATABASE wordpress" && \
    mysql wordpress < /tmp/dump.sql && \
    /bin/bash -c "/etc/init.d/mysql stop"

ENTRYPOINT ["entrypoint.sh"]

CMD ["wordpress"]

EXPOSE 80

VOLUME /srv/wordpress
VOLUME /var/lib/mysql

