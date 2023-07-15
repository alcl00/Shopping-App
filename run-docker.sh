echo "Removing containers that may be running"
sudo docker-compose down
echo "Compiling project with maven"
mvn install -DskipTests
echo "Building image"
sudo docker build -t the-a-shop .
echo "Composing containers"
sudo docker-compose up
echo "Removing containers"
sudo docker-compose down