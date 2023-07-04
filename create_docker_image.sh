#!/usr/bin/env sh

echo "** run gradle clean build **"
./gradlew clean build
echo "** create folder build/libs/my_jar **"
cd build/libs
mkdir my_jar
cd my_jar
echo "** unzip jar build/libs/booking-0.0.1-SNAPSHOT.jar to $(pwd) **"
jar -xf ../booking-0.0.1-SNAPSHOT.jar
cd ../../../
echo "** build docker image booking-backend:1.0-SNAPSHOT **"
docker build -t booking-backend:1.0-SNAPSHOT .
echo "** image build successfully  **"
#rm -r build/libs/my_jar

