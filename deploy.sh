scp -i ../jenkins.pem target/football-standing-service-0.0.1-SNAPSHOT.jar ubuntu@18.191.244.179:/home/ubuntu/anirudh

#pkill -9 -f discovery
#nohup java -jar /home/ubuntu/anirudh/discovery-service-0.0.1-SNAPSHOT.jar > /home/ubuntu/anirudh/discovery.out &

pkill -9 -f football
nohup java -jar /home/ubuntu/anirudh/football-standing-service-0.0.1-SNAPSHOT.jar > /home/ubuntu/anirudh/football.out &

#pkill -9 -f gateway
#nohup java -jar /home/ubuntu/anirudh/gateway-service-0.0.1-SNAPSHOT.jar > /home/ubuntu/anirudh/gateway.out &