# Nebulake - https://nebulake.com
![nebulake_screenshot](https://user-images.githubusercontent.com/72711596/139314702-ccd606fa-ff09-4bf3-8823-18d9c6e05619.png)



I created this forum website for educational purposes such as experimenting with permission-based channels within forums, SSL/HTTPS configurations, and user sessions / cookies.

# Languages:
- Java
- JSP/CSS
- SQL

# Technologies:
- Tomcat web server 
- Nginx proxy server (handles https requests)
- Java Maven Build
- SSL

# SSL/HTTPS configuration:
- HTTPS requests are handled by nginx
- CA SSL certificates
- Secure header configuration within nginx proxy

# General:

 Nebulake enables users to create an account with an email, username, and password. Once the user's email is authenticated, they are re-directed to the main page 
 which presents a navigation panel to the user to access their profile page, nebulas (categories), which is comprised of subnebulas (sub-categories) and the posts 
 made within subnebulas.

 When nebulas are created by a user, the web page gives the user an option to make their nebula page private or public. If the private option is chosen, the user 
 is able to choose the users that are granted access to their nebula.

 Users are also able to upload new profile pictures and create personal bios.
