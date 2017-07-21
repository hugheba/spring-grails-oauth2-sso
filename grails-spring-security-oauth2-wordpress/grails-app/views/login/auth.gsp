<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
</head>

<body>
<oauth2:connect provider="wordpress">Wordpress</oauth2:connect>

Logged with google?
<oauth2:ifLoggedInWith provider="wordpress">yes</oauth2:ifLoggedInWith>
<oauth2:ifNotLoggedInWith provider="wordpress">no</oauth2:ifNotLoggedInWith>
</body>
</html>