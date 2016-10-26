# MOD250 Compulsory 3
##Setup
Setup instructions is located in the "Instructions" directory, in the "Setup.pdf" file.

##JavaEE application
The JavaEE application is included in the directory named "AnthraxMarketplaceApplication". It contains the following:

###Deployment ready packages
The "Packages" directory contains the deployment-ready application, both archived (.ear) and "exploded" (deploy the folder). If changing the configuration .XML-files before deployment is necessary, the easiest approach is to edit the files in the "exploded" package, and then deploying it with the "choose folder" option in Glassfish.

###Source
The "Source" directory contains the source code for the application

###Images
The "Images" directory contains the images for the dummy data. Place these in the "images" folder (default: C:\Temp\AnthraxMedia\images\, see setup instructions to change).

##Standalone Client 
The "AnthraxStandaloneClient" directory contains the standalone client which uses JMS and SOAP-services.
