if [ "$(docker ps -aq)" != '' ]
then
  docker rm -vf $(docker ps -aq)
else
  echo "no containers"
fi