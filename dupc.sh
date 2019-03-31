set +x
if [ $# -ne 1 ]; then 
 echo "Usage: $0 <Path>"
 exit 1
fi
cd $1
echo " ${TMPDIR-/tmp}"
du . | sort -n | python -c '
import sys
elements = []
for line in sys.stdin:
    (size, label) = line.split("\t")
    elements.append((size, label))
    totalsize = size
for size, label in elements:
    print(str(("{0:0.2f}".format(int(size)*100/int(totalsize))) + "% " + label.rstrip()))
'