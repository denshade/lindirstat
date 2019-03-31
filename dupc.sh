set +x
if [ $# -ne 1 ]; then 
 echo "Usage: $0 <Path>"
 exit 1
fi
cd $1
du . | sort -n | python dupc.py