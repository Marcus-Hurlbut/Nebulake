# nebulake
Forum website project for educational purposes

https://nebulake.com

I created this forum website, nebulake.com, for educational purposes as well as experimenting to create private and permission-based channels within forums.

Languages Used:
- Java
- JSP/CSS
- SQL

Technologies Used:
- Tomcat web server 
- Nginx proxy server (handles https requests)
- Java Maven Build
- SSL

SSL/HTTPS configuration:
- HTTPS requests are handled by nginx
- CA SSL certificates
- Secure header configuration within nginx server

General:
 Nebulake enables users to create an account with an email, username, and password. Once the user's email is authenticated, they are re-directed to the main page 
 which presents a navigation panel to the user to access their profile page, nebulas (categories), which is comprised of subnebulas (sub-categories) and the posts 
 made within subnebulas.

 When nebulas are created by a user, the web page gives the user an option to make their nebula page private or public. If the private option is chosen, the user 
 is able to choose the users that are granted access to their nebula.

 Users are also able to upload new profile pictures and create personal bios.
