sudo rm -rf build && mkdir build 

javac --release 17 -d  build/WEB-INF/classes -cp "src/main/webapp/WEB-INF/lib/*" src/main/java/*/*.java
cp src/main/java/hibernate.cfg.xml build/WEB-INF/classes/  
cp -r src/main/webapp/WEB-INF/lib build/WEB-INF/
cp -r src/main/webapp/WEB-INF/web.xml build/WEB-INF/
cp -r src/main/webapp/static build/
cp -r src/main/webapp/META-INF build/
cp -r src/main/webapp/views build/

jar -cfv ROOT.war -C build/  .
