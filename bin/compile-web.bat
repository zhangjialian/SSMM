echo ***********compile***********

cd ../ssm-maven-web

call mvn clean install -Denv=release4Dev -Dmaven.test.skip


goto end


if errorlevel 1 goto error

:error
echo Error Happen
:end
echo *****************************************
echo *               OK!		     *
echo ***************************************** 
pause



