@ECHO OFF

set @launchLocation=%0
set @launchLocation=%@launchLocation:~1,-16%
echo Launched in: %@launchLocation%

cd %@launchLocation%

call mvn install:install-file -Dfile=core-1.0.jar -DgroupId=org.processing -DartifactId=core -Dversion=1.0 -Dpackaging=jar

call mvn install:install-file -Dfile=unfolding-0.9.3.jar -DgroupId=org.processing -DartifactId=unfolding -Dversion=0.9.3 -Dpackaging=jar

pause