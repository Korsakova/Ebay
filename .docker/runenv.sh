#!/usr/bin/env bash
echo "============================== Check and Download last Selenoid release ============"
grep -v "^#" selenoid.yml|grep "image:"|awk 'BEGIN{FS="image:"};{print $2}'|xargs -I{} docker pull {}
docker pull selenoid/video-recorder:latest-release
echo "============================== Check browser images ================================"
cat browsers.json | jq -r '..|.image?|strings' | xargs -I{} docker pull {}
echo "============================== Start selenoid ======================================"
docker-compose -f selenoid.yml up -d selenoid
echo "============================== Start selenoid-ui ==================================="
docker-compose -f selenoid.yml up -d selenoid-ui
echo "======================= Please execute runtest.sh after  =========================="