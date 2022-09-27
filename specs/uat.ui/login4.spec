Login Test Cases
=====================
Created by suleyman.aktas on 9/21/2022
     
Should login as successfully with valid creadential
----------------

* Get masterpass user email as "suleyman.aktas@n11.com" and password as "Passw0rd"
* Logged in N11
* Check user can logged in

Should NOT login with invalid creadential
----------------

* Get masterpass user email as "suleyman.aktas@n11.com" and password as "Wrong-Passw0rd"
* Logged in N11
* Check user can logged in

Should NOT login without creadential
----------------

* Get anonymous masterpass user
* Logged in N11
* Check user can logged in