set +x
if [ $# -ne 1 ]; then 
 echo "Usage: $0 <Path>"
 exit 1
fi
cd $1
totalSize=$(du -s . | cut -f 1)
echo "total size $totalSize"
dulines=$(du . | sort -n)
while read -r duline; 
do 
   size=$(echo $duline | cut -d" " -f 1)
   percent=$((size * 100 / totalSize))
   label=$(echo $duline | cut -d" " -f 2-)
   echo "$percent $label"
done <<< "$dulines"
