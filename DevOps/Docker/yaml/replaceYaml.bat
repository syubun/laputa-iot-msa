echo #######################
echo ##### begin copy ######
echo #######################

echo ###### copy laputa-upms ########
copy .\conf\prod\laputa-upms\application.yml ..\..\laputa-upms\src\main\resources\
copy .\conf\prod\laputa-upms\bootstrap.yml ..\..\laputa-upms\src\main\resources\


echo ###### copy laputa-gateway ########
copy .\conf\prod\laputa-gateway\application.yml ..\..\laputa-gateway\src\main\resources\
copy .\conf\prod\laputa-gateway\bootstrap.yml ..\..\laputa-gateway\src\main\resources\


echo ###### copy laputa-monitor ########
copy .\conf\prod\laputa-monitor\application.yml ..\..\laputa-monitor\src\main\resources\
copy .\conf\prod\laputa-monitor\bootstrap.yml ..\..\laputa-monitor\src\main\resources\


echo ###### copy laputa-picture ########
copy .\conf\prod\laputa-picture\application.yml ..\..\laputa-picture\src\main\resources\
copy .\conf\prod\laputa-picture\bootstrap.yml ..\..\laputa-picture\src\main\resources\


echo ###### copy laputa-search ########
copy .\conf\prod\laputa-search\application.yml ..\..\laputa-search\src\main\resources\
copy .\conf\prod\laputa-search\bootstrap.yml ..\..\laputa-search\src\main\resources\


echo ###### copy laputa-sms ########
copy .\conf\prod\laputa-sms\application.yml ..\..\laputa-sms\src\main\resources\
copy .\conf\prod\laputa-sms\bootstrap.yml ..\..\laputa-sms\src\main\resources\


echo ###### copy laputa-spider ########
copy .\conf\prod\laputa-spider\application.yml ..\..\laputa-spider\src\main\resources\
copy .\conf\prod\laputa-spider\bootstrap.yml ..\..\laputa-spider\src\main\resources\


echo ###### copy laputa-web ########
copy .\conf\prod\laputa-web\application.yml ..\..\laputa-web\src\main\resources\
copy .\conf\prod\laputa-web\bootstrap.yml ..\..\laputa-web\src\main\resources\
